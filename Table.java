import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Table extends JPanel implements ActionListener{
	
	private JButton deal;
	private Slapjack sjack;

	public Table() {
		sjack = new Slapjack();

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
            sjack.dealPlayers();
			sjack.print();
        }
	}

	
}
