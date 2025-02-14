

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;


public class CardGame {
    private ArrayList<Card> deck;
	private ArrayList<Card> p1Cards;
	private ArrayList<Card> p2Cards;
    private BufferedImage cardback;


    public CardGame(){
        deck = new ArrayList<Card>();
        p1Cards = new ArrayList<Card>();
		p2Cards = new ArrayList<Card>();

        String name = "";

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

        try{
            cardback = ImageIO.read(new File("cardback.png"));
        }
        catch(IOException e){
            System.out.println(e);
        }

    }

    public void start(){
        shuffle();
    }


    public void drawGame(Graphics g){
        
		g.setColor(Color.green);
		g.fillRect(0,0,800,600);


		//player 
		Font font = new Font("Arial", Font.PLAIN, 30);
      	g.setFont(font);
		g.setColor(Color.black);
		g.drawString("Player 2: ", 20, 40);


		int x = 20;
		int y = 50;


		for(int i=0; i<p2Cards.size(); i++){
			p2Cards.get(i).drawMe(g,x,y);
			x += 110;
		}


		//player 1
		font = new Font("Arial", Font.PLAIN, 30);
      	g.setFont(font);
		g.setColor(Color.black);
		g.drawString("Player 1: ", 20, 390);


		x = 20;
		y = 400;
		for(int i=0; i<p1Cards.size(); i++){
			p1Cards.get(i).drawMe(g,x,y);
			x += 110;
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
}
