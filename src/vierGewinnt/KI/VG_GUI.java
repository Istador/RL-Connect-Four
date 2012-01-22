package vierGewinnt.KI;


import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;

import vierGewinnt.view.ViewUtility;

/** 
 * Visualisierung des RL-Trainings
 * @author Daniel
 *
 */



public class VG_GUI extends JFrame{
	

	private static final long serialVersionUID = 1L;
	
	private int sizeField= 50;
	private int smaller = 10;
	
	//Backbuffer, damit das Bild nicht flackert
	private Image bb1 = null;
	private Image bb2 = null;
	private boolean bb = true;
	
	public VG_GUI() {
		setTitle("Vier Gewinnt GUI");
		this.setSize(360, 337);
		this.setResizable(false);
		this.setVisible(true);
		//Backbuffer in passender Größe erstellen
		bb1 = this.createImage(sizeField*7+smaller, sizeField*6+smaller);
		bb2 = this.createImage(sizeField*7+smaller, sizeField*6+smaller);
		repaint();
	}
		
	@Override
	public void paint(Graphics g) {
		update(g);
	}
	
	@Override
	public void update( Graphics g ) {
		//den Backbuffer auf die GUI zeichnen
		if(bb)
			g.drawImage(bb1, 0, 25, null);
		else
			g.drawImage(bb2, 0, 25, null);
//		n++;
//		if(last==0) last=System.currentTimeMillis();
//		long now = System.currentTimeMillis();
//		nall += now-last;
//		last = now;
	}
//	long last = 0;
//	long n = 0;
//	long nall = 0;
	
	public void refresh(byte[][] matrix) {
		//Backbuffer neu zeichnen, basierend auf der Spielmatrix
		if(!bb)
			ViewUtility.zeichneSpielfeld(bb1, sizeField, smaller, matrix);
		else
			ViewUtility.zeichneSpielfeld(bb2, sizeField, smaller, matrix);
		bb=!bb;
		repaint();
//		System.out.println(((double)nall/(double)n));
	}
	
		

}
