package vierGewinnt.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import vierGewinnt.Controller;

/**
 * Spieloberfläche (Spielfeld)
 * für die Spielmodi PlayerVsPlayer, PlayerVsRandom, RandomVsRandom
 * nicht für Reinforcment Learning!
 */

public class GamePanel extends JPanel implements KeyListener {

	private static final long serialVersionUID = 1L;
	private int fieldSize; //feldgröße
	private int rand = 10; //rand, abstand
	private int buttonHeight = 30;
	private int statusBarWidth = 100;
	private JLabel anzeigeSpieler = new JLabel(); 
	private Controller ctrl = Controller.getInstance();
	private View view = null;
	
	private List<JButton> buttons = new ArrayList<JButton>();
	
	public List<JButton> getButtons() {return buttons;}
	public void setButtons(List<JButton> buttons) {this.buttons = buttons;}
	
	public GamePanel(int frameWidth, int frameHeight, View view) {
		setSize(frameWidth ,frameHeight);
		
		int zeilen  = ctrl.getModel().getFieldHeight();
		int spalten = ctrl.getModel().getFieldWidth();
		
		int fieldWidth = (int) Math.round((double)(frameWidth - statusBarWidth - rand)/(double)spalten);
		int fieldHeight = (int) Math.round((double)(frameHeight - buttonHeight - rand)/(double)zeilen);
		
		fieldSize = (fieldWidth <= fieldHeight ? fieldWidth : fieldHeight);
		
		this.view = view;
		requestFocus();
		setLayout(null);
		view.addKeyListener(this);
		
		
		
		//frameWidth = (spalten*fieldSize) + rand + statusBarSize;
		//frameHeight = (zeilen*fieldSize)  + rand + buttonHeight;
		

		JLabel aktuellerSpieler = new JLabel("aktueller Spieler:");
		aktuellerSpieler.setBounds(frameWidth-statusBarWidth+5, fieldSize, fieldSize, 30);
		anzeigeSpieler.setBounds(frameWidth-statusBarWidth+5, fieldSize+35, fieldSize, 30);
		add(aktuellerSpieler);
		add(anzeigeSpieler);
		
		/**
		 * Aktions-Buttons erstellen
		 */
		JButton butt;
		for (byte i = 0; i < spalten; i++) {
			butt = new SpielaktionsButton(i);
			butt.addKeyListener(this);
			butt.setBounds(i*fieldSize+rand/2, frameHeight-buttonHeight, fieldSize, buttonHeight);
			buttons.add(i, butt);
			add(butt);
		}
	}
	
	public void displayPlayer(String anzeigeSpieler) {
		this.anzeigeSpieler.setText(anzeigeSpieler);
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
				
		//Backbuffer, damit das Bild nicht flackert
		Image backbuffer = this.createImage(fieldSize*7+rand, fieldSize*6+rand);
		//Methode um das Spielfeld auf den Backbuffer zu zeichnen
		ViewUtility.zeichneSpielfeld(backbuffer, fieldSize, rand, ctrl.getModel().getMatrix());
		//den Backbuffer auf die GUI zeichnen
		g.drawImage(backbuffer, 0, 0, null);
		
	}
	
	
	// Braucht man nicht aber das Interface schreibt es vor
	@Override public void keyPressed(KeyEvent arg0) {}
	@Override public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {
		//Escape -> zurück zum Hauptfenster
		if(arg0.getKeyChar()==KeyEvent.VK_ESCAPE){
			view.showMainPanel();
			ctrl.getModel().reset();
		}
		
		
	}

}
