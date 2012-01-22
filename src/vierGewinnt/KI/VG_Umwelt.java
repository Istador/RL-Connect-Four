package vierGewinnt.KI;

import jade.core.behaviours.SequentialBehaviour;

import vierGewinnt.Model;
import Agent.A_Aktion;
import Agent.A_Situation;
import Agent.A_Umwelt;


/**
 * Steuerung des Spielablaufs
 * @author Daniel
 *
 */

public class VG_Umwelt extends A_Umwelt {

	public static long blocktime = 200;

	private static final long serialVersionUID = 1L;
	vierGewinnt.KI.VG_GUI gui;

	public VG_Umwelt() {
		super(new VG_Uebergabe());
		gui = new vierGewinnt.KI.VG_GUI();
	}

	@Override
	protected void setup() {
		super.setup();
		
		// Die beiden Spieler unendlich oft abwechselnd ein Spielzug machen lassen 
		// Diese Methode wird von Jade ausgeführt

//		long msall = 0;
//		long msn = 0;
		
		SequentialBehaviour seq = new SequentialBehaviour();
		for (;;) {
			seq.addSubBehaviour(new Sende_Reward(VG_Agenten.NAME_AGENT_1));
			seq.addSubBehaviour(new Sende_Episode(VG_Agenten.NAME_AGENT_1));
			seq.addSubBehaviour(new Sende_Situation(VG_Agenten.NAME_AGENT_1));
			seq.addSubBehaviour(new Kommunikation(null));
			aktualisiere_GUI();
			
			try { Thread.sleep(blocktime); } catch (Exception e) {}
			seq.addSubBehaviour(new Sende_Reward(VG_Agenten.NAME_AGENT_2));
			seq.addSubBehaviour(new Sende_Episode(VG_Agenten.NAME_AGENT_2));
			seq.addSubBehaviour(new Sende_Situation(VG_Agenten.NAME_AGENT_2));
			seq.addSubBehaviour(new Kommunikation(null));
			aktualisiere_GUI();
			
//			System.out.println("Durchschnittszeit: "+Double.toString((double)msall/(double)msn));
			
			try { Thread.sleep(blocktime); } catch (Exception e) {}
		}

	}
	
	@Override
	public void aktualisiere_GUI() {
		// hol das aktuelle Spielfeld
		VG_Situation sit = (VG_Situation)super.getSituation();
		
		// aktualisiere die Oberfläche
		gui.refresh(sit.getMatrix());
		
	}

	@Override
	public boolean aktion_Moeglich(A_Aktion a_Aktion, String agent) {
		// wie der Name es schon sagt ... Ist die Aktion in diesem Zustand möglich ?
		// Das ist bei 4G der Fall wenn in der Spalte noch ein Platz frei ist
		
		// lese die aktuelle Situation und die Aktion der Agent ausführen möchte
		VG_Situation situation = (VG_Situation) getSituation();
		byte[][] matrix = situation.getMatrix();
		byte spalte = (byte) a_Aktion.definiere_ID();
		
		return ! VG_Utility.istReiheVoll(matrix, spalte);
	}

	
	// Keine Ahnung wofür die Methode da sein soll (gedacht ist)... Debug-Ausgabe ?
	@Override public A_Situation gib_Situation_Agent() {return null;}

	
	// ob ein Spiel vorbeit ist oder weitergeht
	@Override public boolean istEpisodeVorbei(){
		return zuende;
	}
	
	private boolean zuende = false;
	
	@Override public Double berechne_Reward(String agent) {
		
		// Nach jedem Zug erhält der Agent eine Belohnung
		// diese kann sowohl positiv, negativ(Strafe) oder neutral(0) sein
		// Wenn das Spiel zuende ist muss die Methode super.neue_Episode();
		// ausgeführt werden, damit eine neue Runde gestartet wird
		
		VG_Situation situation = (VG_Situation) getSituation();
		byte[][] matrix = situation.getMatrix();
		int istzuende = Model.spielZuende(matrix); // spielfeld auswerten
		
		// Welcher Agent ist am Zug ?
		int agentNr = ( agent.equals(VG_Agenten.NAME_AGENT_1) ? 1 : 2 );
		
		
		// Das Spiel läuft noch
		if ( istzuende==-1 ){
			zuende = false;
			return 0.0; // weder Belohnung noch Bestrafung
		}
		// Draw / Unentschieden
		else if(istzuende==0){
			zuende = true;
			return 0.0; // weder Belohnung noch Bestrafung
		}
		// hat Zug des aktuellen Agenten zum Sieg geführt ?
		else if( istzuende==agentNr ){
			zuende = true;
			return 100.0; // Belohnung
		}
		
		// der Gegner hat gewonnen
		else {
			zuende = true;
			return -100.0; // Bestrafung
		}
	}
	
	@Override
	public void set_Inital(){
		// Anfangszustand wiederherstellen ....
		byte[][] matrix = new byte [7][6]; // leeres Spielfeld
		setSituation(new VG_Situation(matrix));
		//System.err.println("RESET");
	}
	
	/*
	private int spielZuende(int[][] matrix) {
		return Model.spielZuende(matrix);
	}
	*/
}
