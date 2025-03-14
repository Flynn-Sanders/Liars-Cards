
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Table extends JPanel implements ActionListener, MouseListener, KeyListener {

	private JButton playCards, confirmSwitch, deal, call;

	private boolean hasDealed;

	private CardGame cardGame;

	private int winner;

	

	public Table() {

			cardGame = new CardGame();
			hasDealed=false;
				

			//setup buttons
			setLayout(null);


			playCards = new JButton("Play Selected Cards");
			playCards .setBounds(640,285,150,30); //x,y,width,height
			playCards .addActionListener(this);
			add(playCards);
			playCards.setFocusable(false);
			playCards.setVisible(false);

            confirmSwitch = new JButton("Confirm Switch");
			confirmSwitch .setBounds(640,285,150,30); //x,y,width,height
			confirmSwitch .addActionListener(this);
			add(confirmSwitch);
			confirmSwitch.setFocusable(false);
            confirmSwitch.setVisible(false);


			deal = new JButton("Deal");
			deal.setBounds(300,375,200,50); //x,y,width,height
			deal.addActionListener(this);
			deal.setFocusable(false);
			add(deal);

			call = new JButton("Call Liar");
			call .setBounds(10,285,150,30); //x,y,width,height
			call .addActionListener(this);
			add(call);
			call.setFocusable(false);
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

			if(cardGame.getEnded()){
				deal.setBounds(450,375,200,50); 
				g.drawString("Previous winner: Player "+winner, 10, 400);
				g.drawString("Last Hand Played:", 10, 435);
				ArrayList<Card> hand = cardGame.getLastHand();
				int cardWidth=100;
				x = (int)(170-(0.5*hand.size()*cardWidth));
				int y = 450;
				for(Card c : hand){
					c.drawMe(g, x, y);
					x+=(int)(cardWidth+10);
        		}
			}
			
		}
		
		
	}

	public boolean getDealed(){
		return hasDealed;
	}
	
	private void playSounds(){
		try {
			URL url = this.getClass().getClassLoader().getResource(cardGame.getBet()+".wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(url));
			clip.start();
		} catch (Exception exc) {
			exc.printStackTrace(System.out);
		}
		if(cardGame.getBet()==1){
			try {
				URL url = this.getClass().getClassLoader().getResource(cardGame.getTable()+"-s"+".wav");
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(url));
				clip.start();
			} catch (Exception exc) {
				exc.printStackTrace(System.out);
			}
		}
		else{
			try {
				URL url = this.getClass().getClassLoader().getResource(cardGame.getTable()+".wav");
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(url));
				clip.start();
			} catch (Exception exc) {
				exc.printStackTrace(System.out);
			}
		}
		if((int)(Math.random()*5)==2){
			try {
				URL url = this.getClass().getClassLoader().getResource("hint.wav");
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(url));
				clip.start();
			} catch (Exception exc) {
				exc.printStackTrace(System.out);
			}
		}
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
			int size1 = cardGame.size1();
			int size2 = cardGame.size2();
			if(size1==0||size2==0){
				winner=cardGame.callBluff();
                System.out.println("Player "+winner+" wins, automatic call bluff due to last card played.");
				cardGame.setBlankScreen(true);
				deal.setVisible(true);
				playCards.setVisible(false);
				call.setVisible(false);
				hasDealed=false;

			}
			else{
				playSounds();
			}
			
        }
		else if(e.getSource() == call){
			try {
				URL url = this.getClass().getClassLoader().getResource("liar.wav");
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(url));
				clip.start();
			} catch (Exception exc) {
				exc.printStackTrace(System.out);
			}
			winner=cardGame.callBluff();
            System.out.println("Player "+winner+" wins!");
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

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println(e.getKeyChar());
		if(e.getKeyChar()=='o'){
			cardGame.cheatKey();
		}
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	
	
}