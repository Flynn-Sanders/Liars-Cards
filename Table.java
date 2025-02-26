
import java.awt.Dimension;
import java.awt.Graphics;
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
			deal.setBounds(300,275,200,50); //x,y,width,height
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
		cardGame.drawGame(g);
		
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