package vierGewinntGui.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;


/**
 * Das Frame was die Panel beinhaltet 
 * @author Daniel
 *
 */

public class View extends JFrame implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	private int panelSizeX=0;
	private int panelSizeY=0;
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
		
		panelSizeX=800;
		panelSizeY=600;
		
		gamePanel=new GamePanel(this);
		mainPanel=new MainPanel(panelSizeX, panelSizeX,gamePanel,this);
		setSize(panelSizeX+8,panelSizeY+34);
		setContentPane(mainPanel);
		
		
		setLocationRelativeTo(null);

		setVisible(true);
	}
	public void showMainPanel(){
		setContentPane(mainPanel);
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
	public void refresh() {
		getContentPane().repaint();
	}
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
	public void showGamePanel() {
		setContentPane(gamePanel);
		if(gamePanel.getButtons().get(0)!= null)gamePanel.getButtons().get(0).requestFocus();
		
	}

}
