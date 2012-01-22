package vierGewinnt.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;


/**
 * Das JFrame welches entweder das GamePanel (Spielfeld)
 * oder das MainPanel (Hauptmenü) beinhaltet
 */

public class View extends JFrame implements Runnable {
	private static final long serialVersionUID = -5039197034371869551L;
	
	private int frameWidth = 0;
	private int frameHeight = 0;
	private GamePanel gamePanel;
	private MainPanel mainPanel;

	@Override
	public void run() {
		//////////////////////// Optik usw. ;)
		try {UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");} catch (Exception e){}
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setTitle("Vier Gewinnt");
		/////////////////////////////////
		
		//Fenstergröße
		frameWidth  = 800;
		frameHeight = 600;
		
		setSize(frameWidth,frameHeight);
		frameWidth  -= 8;
		frameHeight -= 34;
		gamePanel = new GamePanel(frameWidth, frameHeight, this);
		mainPanel = new MainPanel(frameWidth, frameHeight, gamePanel, this);
		setContentPane(mainPanel);
		
		setLocationRelativeTo(null);

		setVisible(true);
	}
	
	
	
	
	public void refresh() {
		getContentPane().repaint();
	}
	

	public void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
	
	
	
	public void showMainPanel(){
		setContentPane(mainPanel);
	}
	
	public void showGamePanel() {
		setContentPane(gamePanel);
		if(gamePanel.getButtons().get(0)!= null)gamePanel.getButtons().get(0).requestFocus();	
	}

	public void displayPlayer(int nr) {
		if(nr==1){
			gamePanel.displayPlayer("Spieler 1 (gelb)");
		}
		else if(nr==2){
			gamePanel.displayPlayer("Spieler 2 (rot)");
		}
		else{
			System.err.println("Vier Gewinnt hat nur 2 Spieler, View.displayPlayer");
		}
	}
	
}
