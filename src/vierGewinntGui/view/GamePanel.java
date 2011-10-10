package vierGewinntGui.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import vierGewinntGui.Controller;
import vierGewinntGui.enums.Feld;

/**
 * Spieloberfläche (Spielfeld)
 * @author Daniel
 *
 */

public class GamePanel extends JPanel implements KeyListener {

	private static final long serialVersionUID = 1L;
	private int sizeField= 90;
	private int smaller = 10;
	private int buttonSizeX= sizeField;
	private int buttonSizeY= 30;
	private int statusBarSize = 100;
	private JLabel anzeigeSpieler = new JLabel(); 
	private Controller ctrl= Controller.getInstance();
	private View view = null;
	public List<JButton> getButtons() {
		return buttons;
	}

	public void setButtons(List<JButton> buttons) {
		this.buttons = buttons;
	}

	private List<JButton> buttons = new ArrayList<JButton>();
	
	public GamePanel(View view) {
		
		this.view=view;
		requestFocus();
		setLayout(null);
		view.addKeyListener(this);
		
		int sizeX=(ctrl.getModel().getFieldWidth()*sizeField)+smaller+statusBarSize;
		int sizeY=(ctrl.getModel().getFieldHeight()*sizeField)+smaller+buttonSizeY;
		setSize(sizeX ,sizeY);

		JLabel aktuellerSpieler = new JLabel("aktueller Spieler:");
		aktuellerSpieler.setBounds(sizeX-statusBarSize+5, sizeField, sizeField, 30);
		anzeigeSpieler.setBounds(sizeX-statusBarSize+5, sizeField+35, sizeField, 30);
		add(aktuellerSpieler);
		add(anzeigeSpieler);
		JButton j=null;
		int x=0;
		for (int i = 0; i < ctrl.getModel().getFieldWidth(); i++) {
			 j = new Button_Cul(i);
			 j.setText("einwerfen");
			 j.addKeyListener(this);
			 if(i==ctrl.getModel().getFieldWidth()-1){
				x= buttonSizeX+smaller;
			 }
			 else{
				 x= buttonSizeX;
			 }
			j.setBounds(i*buttonSizeX, sizeY-buttonSizeY, x, buttonSizeY);
			buttons.add(i, j);
			add(j);
		}
	}
	
	public void displayPlayer(String anzeigeSpieler) {
		this.anzeigeSpieler.setText(anzeigeSpieler);
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		for (int x = 0; x < ctrl.getModel().getMatrix().length; x++) {
			for (int y = 0; y < ctrl.getModel().getMatrix()[0].length; y++) {
				int feldSizeX=sizeField;
				int feldSizeY=sizeField;
				
				if(x==ctrl.getModel().getMatrix().length-1){
					feldSizeX+=smaller;
				}
				if(y==ctrl.getModel().getMatrix()[0].length-1){
					feldSizeY+=smaller;
				}
				
				g2.setColor(Color.BLUE);
				g2.fillRect(sizeField*x, sizeField*y, feldSizeX, feldSizeY);
				if(ctrl.getModel().getMatrix()[x][y]==Feld.leer){
					g2.setColor(Color.lightGray);
				}
				else if(ctrl.getModel().getMatrix()[x][y]==Feld.spieler1){
					g2.setColor(Color.YELLOW);
				}
				else{
					g2.setColor(Color.RED);
				}
				g2.fillOval((sizeField*x)+smaller, (sizeField*y)+smaller, sizeField-smaller, sizeField-smaller);
			}
		}
		
	}
	
	
	// Braucht man nicht aber das Interface schreibt es vor
	@Override public void keyPressed(KeyEvent arg0) {}
	@Override public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {
		if(arg0.getKeyChar()==KeyEvent.VK_ESCAPE){
			view.showMainPanel();
			ctrl.getModel().reset();
		}
		
		
	}

}
