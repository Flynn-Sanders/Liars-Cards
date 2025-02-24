import java.awt.*;
import java.awt.image.BufferedImage;

public class Card {
    private String name;
    private BufferedImage cardImage;
    private Font cardFont = new Font("Arial", Font.BOLD, 50);
    private boolean selected = false;


    
    public Card(String nm){
        name=nm;
    }
    

    public void drawMe(Graphics g, int x, int y){
        g.setColor(Color.WHITE);
        if(selected){
            g.setColor(Color.YELLOW);
        }
        g.fillRect(x, y, 100, 140);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, 100, 140);
        g.setFont(cardFont);
        g.drawString(name, x+35, y+90);
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
}
