import java.awt.*;
import java.util.ArrayList;
public class Slapjack {
   
    private ArrayList<Card> deck,p1Hand,p2Hand;

    public Slapjack(){
        deck = new ArrayList<>();
        p1Hand = new ArrayList<>();
        p2Hand = new ArrayList<>();
        String name= "";
        for(int i= 0; i<3; i++){
            switch (i){
                case 0 -> name="Q";
                case 1 -> name="K";
                case 2 -> name="A";
            }
            for(int j = 0; i<6; j++){
                deck.add(new Card(name));
            }
        }
        //deck.add(new Card("J"));
        //deck.add(new Card("J"));
        
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
