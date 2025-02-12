import java.awt.*;
import java.util.ArrayList;
public class Slapjack {
   
    private ArrayList<Card> deck,p1Hand,p2Hand;

    public Slapjack(){
        deck = new ArrayList<>();
        p1Hand = new ArrayList<>();
        p2Hand = new ArrayList<>();
        String suit= "";
        for(int i= 0; i<4; i++){
            switch (i){
                case 0 -> suit="spade";
                case 1 -> suit="heart";
                case 2 -> suit="club";
                case 3 -> suit="diamond";
            }
            deck.add(new Card("A", 11, suit));
            deck.add(new Card("2", 2, suit));
            deck.add(new Card("3", 3, suit));
            deck.add(new Card("4", 4, suit));
            deck.add(new Card("5", 5, suit));
            deck.add(new Card("6", 6, suit));
            deck.add(new Card("7", 7, suit));
            deck.add(new Card("8", 8, suit));
            deck.add(new Card("9", 9, suit));
            deck.add(new Card("10", 10, suit));
            deck.add(new Card("J", 10, suit));
            deck.add(new Card("Q", 10, suit));
            deck.add(new Card("K", 10, suit));
        }
        
    }
    public void drawGame(Graphics g){
        
		g.setColor(Color.green);
		g.fillRect(0,0,800,600);
		
		
	}

    public void shuffle(){
        for(int i= 0; i < deck.size(); i++){
            int swapper= (int)(Math.random()*deck.size());
            Card temp= deck.get(i);
            deck.set(i, deck.get(swapper));
            deck.set(swapper, temp);
        }
    }
    public void dealPlayers(){
        shuffle();
        for(int i = 0; i<deck.size();i++){
            if(i%2==0){
                p1Hand.add(deck.get(i));
            }
            else{
                p2Hand.add(deck.get(i));
            }
        }
    }
    public void print(){
        for(Card c : deck){
            System.out.print(c+", ");
        }
        System.out.println();
        for(Card c : p1Hand){
            System.out.print(c+", ");
        }
        System.out.println();
        for(Card c : p2Hand){
            System.out.print(c+", ");
        }
        System.out.println();
    }
}
