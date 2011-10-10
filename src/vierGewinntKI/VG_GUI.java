package vierGewinntKI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;

/** 
 * Visualisierung des RL-Trainings
 * @author Daniel
 *
 */



public class VG_GUI extends JFrame{
	

	private static final long serialVersionUID = 1L;
	
	
	private int sizeField= 50;
	private int smaller = 10;
	private int[][] matrix = new int[7][6];

	public VG_GUI() {
		setTitle("Vier Gewinnt GUI");
		this.setSize(360, 337);
		this.setVisible(true);
		repaint();
	}
	
	
	public int[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}
	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		int offsetY = 25;
		Graphics2D g2 = (Graphics2D) g;
		int feldSizeX=sizeField;
		int feldSizeY=sizeField;
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		for (int x = 0; x < getMatrix().length; x++) {
			for (int y = 0; y < getMatrix()[0].length; y++) {
				
				if(x==getMatrix().length-1){
					feldSizeX+=smaller;
				}
				if(y==getMatrix()[0].length-1){
					feldSizeY+=smaller;
				}
				
				g2.setColor(Color.BLUE);
				g2.fillRect(sizeField*x, sizeField*y+offsetY, feldSizeX, feldSizeY);
				if(getMatrix()[x][y]==0){
					g2.setColor(Color.GRAY);
				}
				else if(getMatrix()[x][y]==1){
					g2.setColor(Color.YELLOW);
				}
				else{
					g2.setColor(Color.RED);
				}
				g2.fillOval((sizeField*x)+smaller, (sizeField*y)+smaller+offsetY, sizeField-smaller, sizeField-smaller);
			}
		}
	}
	public void refresh(int[][] matrix) {
		setMatrix(matrix);
		repaint();
	}
	
		

}
