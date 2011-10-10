package vierGewinntKI;

import Agent.A_Situation;

/** 
 * Darstellung des Zustand
 * in diesem Fall die Spielfeld-Belegung
 * aus der auch hervor geht welcher Spieler am Zug ist
 * (Anzahl Steine = gerade -> Spieler1 sonst Spieler2)
 * @author Daniel
 *
 */


public class VG_Situation extends A_Situation {
	
	private static final long serialVersionUID = 1L;
	
	private int[][] matrix;// das Spielfeld

	public int[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(int[][] matrix) {
		if(matrix==null)return;
		this.matrix = matrix;
	}
	
	public VG_Situation(int[][] matrix) {
		this.matrix=matrix;
	}

	@Override
	public long defeniere_ID() {
		// Eine eindeutige ID für jeden Zustand des Spielstands
		// Es gibt 42 Felder jedes hat 3 mögliche Belegungen (0=leer, 1=Spieler1, 2=Spieler2)
		// Spielstand wird im 3er-ZahlenSystem dargestellt:
		// 3^41 * Belegung_von_Feld_42(0,1,2) +  3^40 * Belegung_von_Feld_41 + ... + bis Feld1(3^0)
		
		long id=0;
		int countField=1;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				id+=Math.pow(3.0, countField)*matrix[i][j];
				countField++;
			}
		}
		return id;
	}
	
	@Override
	public String toString() {
		// wird auf der Agenten-GUI benutzt
		return " " + defeniere_ID() + " ";
	}




}
