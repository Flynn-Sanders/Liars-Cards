
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Table extends JPanel implements ActionListener, MouseListener {

	private JButton playCards, confirmSwitch, deal, call;

	private boolean hasDealed;

	private CardGame cardGame;

	

	public Table() {

			cardGame = new CardGame();
			hasDealed=false;
				

			//setup buttons
			setLayout(null);


			playCards = new JButton("Play Selected Cards");
			playCards .setBounds(640,285,150,30); //x,y,width,height
			playCards .addActionListener(this);
			add(playCards);
			playCards.setVisible(false);

            confirmSwitch = new JButton("Confirm Switch");
			confirmSwitch .setBounds(640,285,150,30); //x,y,width,height
			confirmSwitch .addActionListener(this);
			add(confirmSwitch);
            confirmSwitch.setVisible(false);


			deal = new JButton("Deal");
			deal.setBounds(300,400,200,50); //x,y,width,height
			deal.addActionListener(this);
			add(deal);

			call = new JButton("Call Liar");
			call .setBounds(10,285,150,30); //x,y,width,height
			call .addActionListener(this);
			add(call);
			call.setVisible(false);
            


			
			addMouseListener(this);

			setFocusable(true);


	}
	
	public Dimension getPreferredSize() {
		//Sets the size of the panel
		return new Dimension(800,600);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Font titleFont = new Font("Verdana", Font.BOLD, 46);
		
		cardGame.drawGame(g);
		if(!hasDealed){
			g.setFont(titleFont);
			g.setColor(Color.BLACK);
			g.drawString("LIAR'S DECK", 236, 150);
			Font descFont = new Font("Verdana", Font.PLAIN, 24);
			g.setFont(descFont);
			FontMetrics metrics = g.getFontMetrics(descFont);
			int descWidth = metrics.stringWidth("A game of deception and strategy.");
			int x = (800 - descWidth) / 2;
			g.drawString("A game of deception and strategy.", x, 250);
			descWidth = metrics.stringWidth("Play cards from your hand on your turn.");
			x = (800 - descWidth) / 2;
			g.drawString("Play cards from your hand on your turn.", x, 275);
			descWidth = metrics.stringWidth("If the opponent thinks you are bluffing, they can call liar.");
			x = (800 - descWidth) / 2;
			g.drawString("If the opponent thinks you are bluffing, they can call liar.", x, 300);
			descWidth = metrics.stringWidth("Jokers are wild.");
			x = (800 - descWidth) / 2;
			g.drawString("Jokers are wild.", x, 325);
			
		}
		
	}

	public boolean getDealed(){
		return hasDealed;
	}
	




	public void actionPerformed(ActionEvent e) {
        if(e.getSource()==deal){
			hasDealed=true;
			cardGame.setBlankScreen(false);
			deal.setVisible(false);
			cardGame.start();
			call.setVisible(true);
			playCards.setVisible(true);
		}
		else if(e.getSource() == playCards){
            //display a blank screen
            if(cardGame.switchPlayer()){
				cardGame.setBlankScreen(true);
				confirmSwitch.setVisible(true);
				playCards.setVisible(false);
				call.setVisible(false);
			}
            
        }

        else if(e.getSource() == confirmSwitch){
            //display a blank screen
            cardGame.setBlankScreen(false);
            confirmSwitch.setVisible(false);
            playCards.setVisible(true);
			call.setVisible(true);
        }
		else if(e.getSource() == call){
            System.out.println("Player "+cardGame.callBluff()+" wins!");
			cardGame.setBlankScreen(true);
			deal.setVisible(true);
			playCards.setVisible(false);
			call.setVisible(false);
			hasDealed=false;
        }

		repaint();
	}

	public void mousePressed(MouseEvent e) {
        cardGame.selectCard(e.getX(), e.getY());
        repaint();
    }


    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}

	
	
}