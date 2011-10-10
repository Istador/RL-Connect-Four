package vierGewinntGui;

import vierGewinntGui.enums.Feld;

/**
 * Daten des Spielfeldes und Methoden für die
 * Spielregeln
 * @author Daniel
 *
 */

public class Model {
	
	private Feld[][] feldMatrix; // feldMatrix[Spalte(X-Richtung)][Reihe(Y-Richtung)]
	
	
	public Model() {
		feldMatrix = new Feld[7][6];
		reset();
	}
	
	public Feld[][] getMatrix() {
		return feldMatrix;

	}
	public int getFieldWidth() {
		return feldMatrix.length;

	}
	public int getFieldHeight() {
		return feldMatrix[0].length;

	}
	
	public int[][] getIntMatrix() {
		int[][] matrix = new int[getFieldWidth()][getFieldHeight()];
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = feldMatrix[i][j].ordinal();
			}
		}
		
		return matrix;

	}
	
	
	
	
	// Alle Felder der Feld-Matrix mit leer initialisieren 
	public void reset() {
		for (int i = 0; i < feldMatrix.length; i++) {
			for (int j = 0; j < feldMatrix[i].length; j++) {
				 feldMatrix[i][j]=Feld.leer;
			}
		}
	}
	
	
	// Spieler X wirft ein Steine in Reihe Y ein
	// Return = Restkapazität der Reihe
	public int steinEinwerfenIn(int reihe,Feld spieler) throws Exception{
		if(spieler==Feld.leer)throw new Exception("Spieler == leer ????????");
		for (int i = feldMatrix[reihe].length-1; i >=0 ; i--) {
			if(feldMatrix[reihe][i]!=Feld.leer){
				continue;
			}
			feldMatrix[reihe][i]=spieler;
			return getFieldHeight()-i;
			
		}
		return 0;
	}
	
	public int spielZuende() {
		
		int[][] matrix = getIntMatrix();
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				
				// horizotale 4er-Reihe suchen
				if(i<matrix.length-3){
					if(matrix[i][j]==1 && matrix[i+1][j]==1 && matrix[i+2][j]==1 && matrix[i+3][j]==1){
						return 1;
					}
					else if(matrix[i][j]==2 && matrix[i+1][j]==2 && matrix[i+2][j]==2 && matrix[i+3][j]==2){
						return 2;
					}
				}
				// vertikale 4er-Reihe suchen
				if(j< matrix[i].length-3 ){
					if(matrix[i][j]==1 && matrix[i][j+1]==1 && matrix[i][j+2]==1 && matrix[i][j+3]==1){
						return 1;
					}
					else if(matrix[i][j]==2 && matrix[i][j+1]==2 && matrix[i][j+2]==2 && matrix[i][j+3]==2){
						return 2;
					}
				}

				// diagonale 4er-Reihe suchen (von rechts oben nach links unten)
				if(i<4 && j<3){
					if(matrix[i][j]==1 && matrix[i+1][j+1]==1 && matrix[i+2][j+2]==1 && matrix[i+3][j+3]==1){
						return 1;
					}
					if(matrix[i][j]==2 && matrix[i+1][j+1]==2 && matrix[i+2][j+2]==2 && matrix[i+3][j+3]==2){
						return 2;
					}
				}
				
				// diagonale 4er-Reihe suchen (von links oben nach rechts unten)
				if(i>=3 && j<3){
					if(matrix[i][j]==1 && matrix[i-1][j+1]==1 && matrix[i-2][j+2]==1 && matrix[i-3][j+3]==1){
						return 1;
					}
					if(matrix[i][j]==2 && matrix[i-1][j+1]==2 && matrix[i-2][j+2]==2 && matrix[i-3][j+3]==2){
						return 2;
					}
				}
				
				
				
			}
		}
		// Ist das Spielfeld voll und keiner hat gewonnen ?
		boolean draw= true;
		for (int i = 0; i < 7; i++) {
			if(matrix[i][0]==0){
				draw = false;
				break;
			}
		}
		if(draw)return 0;
		else return -1;
		
	}
	

}
