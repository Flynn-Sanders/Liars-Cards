

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;





public class CardGame {

    private ArrayList<Card> deck, p1Cards, p2Cards, selectedCards, playerCards,betCards;
    private BufferedImage cardback;

    private boolean blankScreen = true, ended=false;
    private int turn = 1;

    private String table, tableText;

    private int currentBet;

    public CardGame(){
        deck = new ArrayList<Card>();
        p1Cards = new ArrayList<Card>();
		p2Cards = new ArrayList<Card>();
        selectedCards = new ArrayList<Card>();
        playerCards = new ArrayList<Card>();
        betCards = new ArrayList<Card>();
        

        

        try{
            cardback = ImageIO.read(new File("cardback.png"));
        }
        catch(IOException e){
            System.out.println(e);
        }

    }

   

    public void start(){
        reset();
        shuffle();
        deal();
        playerCards=new ArrayList<>(p1Cards);

        int tableInt = (int)(Math.random()*3);
			switch(tableInt){
				case 0: table="Q"; tableText="Queen"; break;
				case 1: table="K"; tableText="King"; break;
				case 2: table="A"; tableText="Ace"; break;
				default: table="x"; tableText="x"; break;
			}
        System.out.println(tableText+"'s Table");
    }

    public void setBlankScreen(boolean b){
        blankScreen = b;
    }

    public boolean switchPlayer(){
        if(selectedCards.size()>=1 && selectedCards.size()<=3){
            currentBet=selectedCards.size();
            betCards = new ArrayList<Card>(selectedCards);
            for(Card c : selectedCards){
                for(int i = 0 ; i<playerCards.size();i++){
                    if(playerCards.get(i)==c){
                        playerCards.remove(c);
                        break;
                    }
                    
                }
            }
            selectedCards.clear();

            if(turn == 1){
                p1Cards = new ArrayList<>(playerCards); 
                
                playerCards.clear();
                turn = 2;
                for(Card c : p2Cards){
                    playerCards.add(c);
                }
                
            } else if (turn == 2){
                p2Cards = new ArrayList<>(playerCards); 
                playerCards.clear();
                turn = 1;
                for(Card c : p1Cards){
                    playerCards.add(c);
                }
                
            }
            return true;
        }
        else{
            System.out.println("Can only play 1-3 cards.");
            for(Card c : playerCards){
                //System.out.println(c.getName());
            }
            return false;
        }
    }

    public int callBluff(){
        ended=true;
        boolean hasFoundFalse = false;
        for (Card c : betCards) {
            if (!c.getName().equals(table)&&!c.getName().equals("J")) {
                c.setChecker(false, true);
                hasFoundFalse = true;
                
            }
            else{
                c.setChecker(true, false);
            }
        }
        if(hasFoundFalse){
            System.out.println("WIN BY CORRECTLY ACCUSING");
            return turn;
        }
        System.out.println("WIN BY FALSELY ACCUSING");
        if(turn==1){
            return 2;
        }
        else if(turn==2){
            return 1;
        }
        return 0;
        
    }

    public void cheatKey(){
        betCards.clear();
        betCards.add(new Card("Q"));
        betCards.add(new Card("K"));
        callBluff();
    }


    public void drawGame(Graphics g){
        
		g.setColor(new Color(96,0,0));
		g.fillRect(0,0,800,600);

        if(blankScreen == false){

            Font font = null; 
            int x = 0;
            int y = 0;

            if(turn == 2){
                //player 2
                
                font = new Font("Arial", Font.PLAIN, 30);
                g.setFont(font);
                g.setColor(Color.black);
                g.drawString("Player 2: "+p2Cards.size()+" cards", 20, 390);
                g.drawString("Player 1: "+p1Cards.size()+" cards", 20, 30);
                


                x = 20;
                y = 400;


                for(int i=0; i<p2Cards.size(); i++){
                    p2Cards.get(i).drawMe(g,x,y);
                    x += 130;
                }
                x=20;
                y=50;
                for(int i=0; i<p1Cards.size(); i++){
                    g.drawImage(cardback, x, y, 100,140,null);
                    x += 130;
                }
            }

            //player 1

            if(turn == 1){
                font = new Font("Arial", Font.PLAIN, 30);
                g.setFont(font);
                g.setColor(Color.black);
                g.drawString("Player 1: "+p1Cards.size()+" cards", 20, 390);
                g.drawString("Player 2: "+p2Cards.size()+" cards", 20, 30);
    
    
                x = 20;
                y = 400;
                for(int i=0; i<p1Cards.size(); i++){
                    p1Cards.get(i).drawMe(g,x,y);
                    x += 130;
                }
                x=20;
                y=50;
                for(int i=0; i<p2Cards.size(); i++){
                    g.drawImage(cardback, x, y, 100,140,null);
                    x += 130;
                }
            }   
            font = new Font("Arial", Font.BOLD, 30);
            g.setColor(Color.black);
            g.setFont(font);
            if(currentBet>1){
                g.drawString("Current Bet: "+currentBet+" "+tableText+"s", 250, 310);
            }
            else{
                g.drawString("Current Bet: "+currentBet+" "+tableText, 250, 310);
            }
            
        }
	}
    public void selectCard(int xInput, int yInput){
        int x = 20;
        int y=400;
        
        for(int i=0; i<playerCards.size(); i++){
            if(xInput >= x && xInput <= x+100 && yInput <= y+140 && yInput >= y){
                playerCards.get(i).select();
                if(playerCards.get(i).getSelected()){
                    selectedCards.add(playerCards.get(i));
                }
                else{
                    selectedCards.remove(playerCards.get(i));
                }
            }
            x+=130;
        }
        
    }
	
    public void shuffle(){
		for(int i = 0; i < deck.size(); i++){
            int swapper = (int)(Math.random()*deck.size());
            Card temp = deck.get(i);
            deck.set(i, deck.get(swapper));
            deck.set(swapper, temp);
        }
	}
    public void deal(){
        for(int i = 0; i<5;i++){
            p1Cards.add(deck.get(0));
            deck.remove(0);
        }
        for(int i = 0; i<5;i++){
            p2Cards.add(deck.get(0));
            deck.remove(0);
        }
        for(Card c : p1Cards){
            playerCards.add(c);
        }
    }
    public void reset(){
        deck.clear();
        p1Cards.clear();
        p2Cards.clear();
        selectedCards.clear();
        playerCards.clear();
        betCards.clear();
        currentBet=0;
        turn=1;

        String name = "";

        for(int i= 0; i<3; i++){
            switch (i){
                case 0 -> name="Q";
                case 1 -> name="K";
                case 2 -> name="A";
            }
            for(int j = 0; j<6; j++){
                deck.add(new Card(name));
            }
        }
        deck.add(new Card("J"));
        deck.add(new Card("J"));
    }
    public boolean getEnded(){
        return ended;
    }
    public ArrayList<Card> getLastHand(){
        return betCards;
    }
    public int size1(){
        return p1Cards.size();
    }
    public int size2(){
        return p2Cards.size();
    }
    public int getBet(){
        return currentBet;
    }
    public String getTable(){
        return table;
    }
}
