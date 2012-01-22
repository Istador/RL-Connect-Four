package vierGewinnt;

import vierGewinnt.KI.VG_Utility;


/**
 * Daten des Spielfeldes und Methoden für die
 * Spielregeln
 */

public class Model {
	
	/**
	 * Spielfeld
	 * matrix[spalte][zeile]
	 * spalte: von links (0) nach rechts (6)
	 * zeile: von oben (0) nach unten (5)
	 */
	private byte[][] feldMatrix;
	
	
	public Model() {
		feldMatrix = new byte[7][6];
		reset();
	}
	
	public byte[][] getMatrix() {return feldMatrix;}
	public int getFieldWidth() {return feldMatrix.length;}
	public int getFieldHeight() {return feldMatrix[0].length;}
	
	
	
	
	/**
	 *  Alle Felder der Feld-Matrix mit leer (0) initialisieren 
	 */
	public void reset() {
		feldMatrix = new byte[feldMatrix.length][feldMatrix[0].length];
	}
	
	
	/**
	 * Spieler X wirft ein Steine in Reihe Y ein
	 * @return Restkapazität der Reihe
	 */
	public void steinEinwerfenIn(byte reihe, byte spieler) {
		this.feldMatrix = VG_Utility.getFolgeSpielfeldFarbe(feldMatrix, reihe, spieler);
	}
	
	
	/**
	 * ermittelt ob das Spiel zuende ist 
	 * return value - Bedeutung
	 * -1	-	Spiel noch nicht zuende
	 * 0	-	Unentschieden
	 * 1	-	Spieler 1 hat gewonnen
	 * 2	-	Spieler 2 hat gewonnen
	 */
	public static int spielZuende(byte[][] matrix) {
		//variable die false wird, wenn noch eine Reihe offene Felder hat
		boolean draw = true;
		
		//alle Spalten (links nach rechts)
		for (int i = 0; i < matrix.length; i++) {
			
			//gucken ob oberstes Feld leer ist
			if(matrix[i][0]==0)
				draw=false;
			
			//alle Zeilen (oben nach unten)
			for (int j = 0; j < matrix[i].length; j++) {
				// horizotale 4er-Reihe suchen (links nach rechts)
				if(i<matrix.length-3){
					if(matrix[i][j]==1 && matrix[i+1][j]==1 && matrix[i+2][j]==1 && matrix[i+3][j]==1)
						return 1; //spieler 1 gewonnen
					else if(matrix[i][j]==2 && matrix[i+1][j]==2 && matrix[i+2][j]==2 && matrix[i+3][j]==2)
						return 2; //spieler 2 gewonnen
				}
				// vertikale 4er-Reihe suchen (oben nach unten)
				if(j< matrix[i].length-3 ){
					if(matrix[i][j]==1 && matrix[i][j+1]==1 && matrix[i][j+2]==1 && matrix[i][j+3]==1)
						return 1; //spieler 1 gewonnen
					else if(matrix[i][j]==2 && matrix[i][j+1]==2 && matrix[i][j+2]==2 && matrix[i][j+3]==2)
						return 2; //spieler 2 gewonnen
				}

				// diagonale 4er-Reihe suchen (von rechts oben nach links unten)
				if(i<4 && j<3){
					if(matrix[i][j]==1 && matrix[i+1][j+1]==1 && matrix[i+2][j+2]==1 && matrix[i+3][j+3]==1)
						return 1; //spieler 1 gewonnen
					if(matrix[i][j]==2 && matrix[i+1][j+1]==2 && matrix[i+2][j+2]==2 && matrix[i+3][j+3]==2)
						return 2; //spieler 2 gewonnen
				}
				
				// diagonale 4er-Reihe suchen (von links oben nach rechts unten)
				if(i>=3 && j<3){
					if(matrix[i][j]==1 && matrix[i-1][j+1]==1 && matrix[i-2][j+2]==1 && matrix[i-3][j+3]==1)
						return 1; //spieler 1 gewonnen
					if(matrix[i][j]==2 && matrix[i-1][j+1]==2 && matrix[i-2][j+2]==2 && matrix[i-3][j+3]==2)
						return 2; //spieler 2 gewonnen
				}	
			}
		}
		// Ist das Spielfeld voll und keiner hat gewonnen ?
		if(draw) return 0;
		
		// das spiel läuft weiter
		return -1;	
	}
	

}
