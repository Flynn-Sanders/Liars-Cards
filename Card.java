import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Card {
    private String name;
    private BufferedImage cardImage;
    private Font cardFont = new Font("Arial", Font.PLAIN, 30);
    private boolean selected = false, isCorrect = false, isIncorrect = false;


    
    public Card(String nm){
        name=nm;
        try{
            cardImage = ImageIO.read(new File(name+".png"));
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
    

    public void drawMe(Graphics g, int x, int y){
        g.setColor(Color.black);
        if(selected){
            g.setColor(Color.YELLOW);
        }
        if(isCorrect){
            g.setColor(Color.green);
        }
        if(isIncorrect){
            g.setColor(Color.red);
        }
        g.drawImage(cardImage, x, y, 100,140,null);
        g.setFont(cardFont);
        FontMetrics metrics = g.getFontMetrics(cardFont);
		int height = 30;
        int width = metrics.stringWidth(name);
        g.drawString(name, x, y+height);
        g.drawString(name, x+100-width-2, y+140-2);
        g.drawRect(x, y, 100, 140);
        
        //System.out.println("Drawing card at" +x+","+y);
    }

    public String getName(){
        return name;
    }
    public void select(){
        selected = !selected;
    }
    public boolean getSelected(){
        return selected;
    }
    public void setChecker(boolean isGreen, boolean isRed){
        isCorrect=isGreen;
        isIncorrect=isRed;
    }
    @Override
    public String toString(){
        switch(name){
            case "J" -> {
                return "Joker";
            }
            case "Q" -> {
                return "Queen";
            }
            case "K" -> {
                return "King";
            }
            case "A" -> {
                return "Ace";
            }
            default -> {
                return "null";
            }
        }
    }
}
