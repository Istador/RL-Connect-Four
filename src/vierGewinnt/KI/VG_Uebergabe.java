package vierGewinnt.KI;

import Agent.A_Aktion;
import Agent.A_Situation;
import Agent.A_Situation_Aktion;
import Agent.A_Uebergabe;


/**
 * Initialisierung der Umwelt
 * @author Daniel
 *
 */

public class VG_Uebergabe extends A_Uebergabe{

	public VG_Uebergabe() {
		this.factory = new Agent.A_Factory(){
			@Override
			public A_Situation_Aktion newSituationsAktion(A_Situation s, A_Aktion a, int episode){
				return new VG_Situation_Aktion(s, a, episode);
			}
		};
		
		// Diese Methode initialisiert das Spiel 
		this.aktionen_ID = new java.util.HashSet<A_Aktion>();
		this.agenten = new java.util.HashSet<String>();
		
		// Aktionen von 4 Gewinnt erstellen(Steine einwerfen Spalte 0 - 6)
		for (byte id = 0; id <= 6; id++)
			aktionen_ID.add(new VG_Aktion_SteinWerfenInSpalteNr(id));
		
		// Agenten erstellen bzw. ihnen einen Namen geben
		this.agenten.add(VG_Agenten.NAME_AGENT_1);
		this.agenten.add(VG_Agenten.NAME_AGENT_2);
		
		// leer Spielfeld (alles 0) erstellen
		byte[][] matrix = new byte[7][6];
		
		// Den Agenten den Startzustand zuweisen Sie in einer Liste speichern
		this.situation = new VG_Situation(matrix);
	}
}
