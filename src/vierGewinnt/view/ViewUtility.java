package vierGewinnt.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;


public class ViewUtility {

	/**
	 * Zeichnet das Spielfeld auf ein Image-Objekt 
	 * destruktiv (verändert das übergebene Objekt)
	 * @param backbuffer	Image auf das gezeichnet wird
	 * @param sizeField		Höhe und Breite eines einzelnen Felds
	 * @param smaller		Rand und Abstände
	 * @param spielmatrix	Spielzustand
	 */
	public static void zeichneSpielfeld(Image backbuffer, int sizeField, int smaller, byte[][] spielmatrix){
		Graphics2D g = (Graphics2D) backbuffer.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		//blauer hintergrund
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, sizeField*7+smaller, sizeField*6+smaller);
		
		//löcher
		for (int x = 0; x < spielmatrix.length; x++) { // alle Spalten
			for (int y = 0; y < spielmatrix[0].length; y++) { // alle Zeilen
				// Feld unbelegt / leer -> Graues Loch
				if(spielmatrix[x][y]==0){
					g.setColor(Color.lightGray);
				}
				// Feld mit Stein von Spieler 1 -> Gelber Stein
				else if(spielmatrix[x][y]==1){
					g.setColor(Color.YELLOW);
				}
				// Feld mit Stein von Spieler 2 -> Roter Stein
				else{
					g.setColor(Color.RED);
				}
				// Zeichne Kreis in der entsprechenden Farbe 
				g.fillOval((sizeField*x)+smaller, (sizeField*y)+smaller, sizeField-smaller, sizeField-smaller);
			}
		}
	}
}
