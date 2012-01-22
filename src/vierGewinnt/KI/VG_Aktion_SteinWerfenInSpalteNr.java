package vierGewinnt.KI;

import Agent.A_Aktion;
import Agent.A_Situation;

public class VG_Aktion_SteinWerfenInSpalteNr extends A_Aktion {
	
	/**
	 * Möglich Aktion sind : Stein in Spalte 1-7
	 * diese Klasse fasst diese Aktionen in einer 
	 * Klasse zusammen, die Spalte wird über den Konstruktor 
	 * gesetzt
	 */


	private static final long serialVersionUID = 1L;
	
	private byte id = -1;

	public VG_Aktion_SteinWerfenInSpalteNr(byte id) {
		super();
		this.id=id;
	}

	@Override
	public int definiere_ID() {
		return this.id;
	}

	@Override
	public A_Situation fuehre_Aus(A_Situation situation, String agent) {
		//Map<String, A_Situation> new_sit = new java.util.HashMap<String, A_Situation>(situation);
		
		byte farbe = (byte) ( agent.equals(VG_Agenten.NAME_AGENT_1) ? 1 : 2 );
		
		//stein in spalte einwerfen
		byte[][] matrix = VG_Utility.getFolgeSpielfeld(((VG_Situation)situation).getMatrix(), id, farbe);
		
		// Neuen Zustand speichern
		return new VG_Situation(matrix);
	}



}
