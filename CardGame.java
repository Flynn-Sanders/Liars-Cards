

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;


public class CardGame {
    private ArrayList<Card> deck, p1Cards, p2Cards, selectedCards, playerCards;
    private BufferedImage cardback;
    private boolean blankScreen = false;
    private int turn = 1;


    public CardGame(){
        deck = new ArrayList<Card>();
        p1Cards = new ArrayList<Card>();
		p2Cards = new ArrayList<Card>();
        selectedCards = new ArrayList<>();
        playerCards = new ArrayList<>();
        

        

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
    }

    public void setBlankScreen(boolean b){
        blankScreen = b;
    }

    public void switchPlayer(){
        playerCards.clear();
        if(turn == 1){
            turn = 2;
            for(Card c : p2Cards){
                playerCards.add(c);
            }
        } else if (turn == 2){
            turn = 1;
            for(Card c : p1Cards){
                playerCards.add(c);
            }
        }
    }


    public void drawGame(Graphics g){
        
		g.setColor(Color.green);
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
                g.drawString("Player 2: "+p2Cards.size()+" cards", 20, 40);
                


                x = 20;
                y = 50;


                for(int i=0; i<p2Cards.size(); i++){
                    p2Cards.get(i).drawMe(g,x,y);
                    x += 130;
                }
                x=20;
                y=400;
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
        }
	}
    public void selectCard(int xInput, int yInput){
        int x = 20;
        int y=400;
        if(turn==2){
            y=50;
        }
        
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
    }
}
