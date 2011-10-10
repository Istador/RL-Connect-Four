package vierGewinntKI;

import java.util.HashMap;

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
	
	private int id = -1;

	public VG_Aktion_SteinWerfenInSpalteNr(int id) {
		super(id);
		this.id=id;
	}

	@Override
	public int defeniere_ID() {
		return this.id;
	}

	@Override
	public HashMap<String, A_Situation> fuehre_Aus(
			HashMap<String, A_Situation> situation, String agent) {
		
		// Auslesen welche Aktion es ist also in welche Spalte der Stein eingeworfen wird
		int spalte = defeniere_ID();
		// Aktuelles Spielfeld auslesen
		VG_Situation sit=(VG_Situation)situation.get(agent);
		int[][] intMatrix= sit.getMatrix();
		// Stein in die Spalte einwerfen (tiefste Position, der Stein fällt bei 4Gewinnt nach unten)
		for (int i = 5; i >= 0 ; i--) {
			if(intMatrix[spalte][i]==0){
				if(agent.equals(VG_Agenten.NAME_AGENT_1))intMatrix[spalte][i]=1;
				else intMatrix[spalte][i]=2;
				break;
			}
		}
		// Neuen Zustand speichern
		sit.setMatrix(intMatrix);
		situation.remove(agent);
		situation.put(agent, sit);

		return situation;
	}



}
