
import java.awt.*;
import java.awt.image.BufferedImage;



public class Card {
    private String name;
    private BufferedImage cardImage;
    private Font cardFont = new Font("Arial", Font.BOLD, 50);


    
    public Card(String nm){
            name=nm;
        }
    

    public void drawMe(Graphics g, int x, int y){
        g.setColor(Color.WHITE);
        g.fillRect(x, y, 100, 140);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, 100, 140);
        g.setFont(cardFont);
        g.drawString(name, x+35, y+90);
    }

    public String getName(){
        return name;
    }
}
