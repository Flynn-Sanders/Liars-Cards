import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Table extends JPanel implements ActionListener, MouseListener{
	
	private JButton deal;
	private CardGame cgame;

	public Table() {
		cgame = new CardGame();

		//setup buttons
		setLayout(null);
		deal = new JButton("DEAL");
		deal.setBounds(400,300,100,30); //x,y,width,height
		deal.addActionListener(this);
		add(deal);

		setFocusable(true);

	}
	
	public Dimension getPreferredSize() {
		//Sets the size of the panel
		return new Dimension(800,600);
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	
		
	}
	

	public void actionPerformed(ActionEvent e) {
        if(e.getSource()==deal){
            deal.setVisible(false);
            cgame.dealPlayers();
        }
	}
	public void mousePressed(MouseEvent e) {
        //Print location of x and y
        //System.out.println("X: " + e.getX() + ", Y: " + e.getY());
        
        repaint();
    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}

	
}
