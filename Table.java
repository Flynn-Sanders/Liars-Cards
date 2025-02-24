
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Table extends JPanel implements ActionListener, MouseListener {

	private JButton switchPlayer, confirmSwitch, deal;

	private CardGame cardGame;

	public Table() {

			cardGame = new CardGame();	

			//setup buttons
			setLayout(null);


			switchPlayer = new JButton("Switch Player");
			switchPlayer .setBounds(640,285,150,30); //x,y,width,height
			switchPlayer .addActionListener(this);
			add(switchPlayer);
			switchPlayer.setVisible(true);

            confirmSwitch = new JButton("Confirm Switch");
			confirmSwitch .setBounds(640,285,150,30); //x,y,width,height
			confirmSwitch .addActionListener(this);
			add(confirmSwitch);
            confirmSwitch.setVisible(false);


			deal = new JButton("Deal");
			deal.setBounds(200,275,200,50); //x,y,width,height
			deal.addActionListener(this);
			add(deal);
            


			
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
	




	public void actionPerformed(ActionEvent e) {
        if(e.getSource()==deal){
			deal.setVisible(false);
			cardGame.start();
		}
		if(e.getSource() == switchPlayer){
            //display a blank screen
            cardGame.setBlankScreen(true);
            cardGame.switchPlayer();
            confirmSwitch.setVisible(true);
            switchPlayer.setVisible(false);
        }

        if(e.getSource() == confirmSwitch){
            //display a blank screen
            cardGame.setBlankScreen(false);
            confirmSwitch.setVisible(false);
            switchPlayer.setVisible(true);
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