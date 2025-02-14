
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Table extends JPanel implements ActionListener, MouseListener {

	private JButton switchPlayerButton;
    private JButton continueButton;

	private CardGame cardGame;

	public Table() {

			cardGame = new CardGame();	

			//setup buttons
			setLayout(null);


			switchPlayerButton = new JButton("Switch Player");
			switchPlayerButton .setBounds(400,350,200,30); //x,y,width,height
			switchPlayerButton .addActionListener(this);
			add(switchPlayerButton);

            continueButton = new JButton("Continue");
			continueButton .setBounds(200,350,200,30); //x,y,width,height
			continueButton .addActionListener(this);
			add(continueButton);
            continueButton.setVisible(false);


			
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
        
		repaint();
	}

	public void mousePressed(MouseEvent e) {
        repaint();
    }


    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}

	
	
}