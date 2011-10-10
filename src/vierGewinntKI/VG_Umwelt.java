package vierGewinntKI;

import jade.core.behaviours.SequentialBehaviour;

import java.util.ArrayList;

import Agent.A_Aktion;
import Agent.A_Situation;
import Agent.A_Umwelt;


/**
 * Steuerung des Spielablaufs
 * @author Daniel
 *
 */

public class VG_Umwelt extends A_Umwelt {


	private static final long serialVersionUID = 1L;
	vierGewinntKI.VG_GUI gui;

	public VG_Umwelt() {
		super(new VG_Uebergabe());
		gui = new vierGewinntKI.VG_GUI();
		
	}

	@Override
	protected void setup() {
		super.setup();
		
		// Die beiden Spieler unendlich oft abwechselnd ein Spielzug machen lassen 
		// Diese Methode wird von Jade ausgeführt
		
		long msall = 0;
		long msn = 0;
		
		SequentialBehaviour seq = new SequentialBehaviour();
		for (;;) {
			
			long a = System.currentTimeMillis();
			seq.addSubBehaviour(new Sende_Reward(VG_Agenten.NAME_AGENT_1));
			long b = System.currentTimeMillis()-a;
			msall+=b;
			msn++;
			
			seq.addSubBehaviour(new Sende_Situation(VG_Agenten.NAME_AGENT_1));

			seq.addSubBehaviour(new Sende_Episode(VG_Agenten.NAME_AGENT_1));
			seq.addSubBehaviour(new Kommunikation(null));
			aktualisiere_GUI();
			
			
			seq.addSubBehaviour(new Sende_Situation(VG_Agenten.NAME_AGENT_2));
			a = System.currentTimeMillis();
			seq.addSubBehaviour(new Sende_Reward(VG_Agenten.NAME_AGENT_2));
			b = System.currentTimeMillis()-a;
			msall+=b;
			msn++;
			seq.addSubBehaviour(new Sende_Episode(VG_Agenten.NAME_AGENT_2));
			seq.addSubBehaviour(new Kommunikation(null));
			aktualisiere_GUI();
			
			System.out.println("Durchschnittszeit: "+Double.toString((double)msall/(double)msn));
		}

	}
	
	@Override
	public void aktualisiere_GUI() {
		// hol das aktuelle Spielfeld
		VG_Situation sit = null;
		if(super.getName().equals(VG_Agenten.NAME_AGENT_1)){
			sit = (VG_Situation)super.getSituationen().get(VG_Agenten.NAME_AGENT_1);
		}
		else{
			sit = (VG_Situation)super.getSituationen().get(VG_Agenten.NAME_AGENT_2);
		}
		
		// aktualisiere die Oberfläche
		gui.refresh(sit.getMatrix());
		
	}

	@Override
	public boolean aktion_Moeglich(A_Aktion a_Aktion, String agent) {
		// wie der Name es schon sagt ... Ist die Aktion in diesem Zustand möglich ?
		// Das ist bei 4G der Fall wenn in der Spalte noch ein Platz frei ist
		
		// lese die aktuelle Situation und die Aktion der Agent ausführen möchte
		VG_Situation situation = (VG_Situation)super.getSituationen().get(agent);
		int[][] intMatrix = situation.getMatrix();
		VG_Aktion_SteinWerfenInSpalteNr aktion=(VG_Aktion_SteinWerfenInSpalteNr) a_Aktion;
		int spalte = aktion.getId();
		
		// Ist das möglich ?
		if(intMatrix[spalte][0]==0){
			return true;
		}
		return false;
	}

	@Override
	// Keine Ahnung wofür die Methode da ist... Debug-Ausgabe ?
	public A_Situation gib_Situation_Agent() {return null;}
	
	@Override
	public Double berechne_Reward(String agent) {
		
		// Nach jedem Zug erhält der Agent eine Belohnung
		// diese kann sowohl positiv, negativ(Strafe) oder neutral(0) sein
		// Wenn das Spiel zuende ist muss die Methode super.neue_Episode();
		// ausgeführt werden, damit eine neue Runde gestartet wird
		
		int agentNr = 1;
		//double reward = 0.0;
		VG_Situation situation = (VG_Situation)super.getSituationen().get(agent);
		
		// Welcher Agent ist am Zug ?
		if(!agent.equals(VG_Agenten.NAME_AGENT_1)){
			agentNr = 2;
		} 
		
		// hat Zug des aktuellen Agenten zum Sieg geführt ?
		if(       (spielZuende(situation.getMatrix())==1 &&  (agentNr==1)) 
			||  (spielZuende(situation.getMatrix())==2 &&  (agentNr==2))  ){
			super.neue_Episode();
			return 100.0;
		}
		// Hat der Gegner gewonnen ? Strafe !!!!!!!
		else if(       (spielZuende(situation.getMatrix())==1 &&  (agentNr==2)) 
			||  (spielZuende(situation.getMatrix())==2 &&  (agentNr==1))  ){
			super.neue_Episode();
			return -100.0;
		}
		// Draw
		else if(spielZuende(situation.getMatrix())==0){
				super.neue_Episode();
				return 0.0;
			}
		// Das Spiel läuft noch
		else{
			/*// Wenn nein, für jeden gesetzten Stein -1 als Strafe geben
			int[][] matrix = situation.getMatrix();
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix[i].length; j++) {
					if(matrix[i][j]==agentNr){
						reward-=1.0;
					}
				}
			}*/
			
			// weder Belohnung noch Bestrafung
			return 0.0;
		}
		//return reward;
	}
	@Override
	public void set_Inital(){
		// Anfangszustand wiederherstellen ....
		ArrayList<String> agenten = super.getAgenten();
		int[][] matrix = new int [7][6]; // leeres Spielfeld
		for(int i=0; i< agenten.size();i++)
			super.getSituationen().put(agenten.get(i), new VG_Situation(matrix));
		System.err.println("RESET");
	}
	
	private int spielZuende(int[][] matrix) {
		
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
