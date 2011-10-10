package vierGewinntKI;

import java.util.ArrayList;
import java.util.HashMap;

import Agent.A_Aktion;
import Agent.A_Situation;
import Agent.A_Uebergabe;


/**
 * Initialisierung der Umwelt
 * @author Daniel
 *
 */

public class VG_Uebergabe extends A_Uebergabe{
	
	//-------- Variablen + Get/Setter --------\\
	
	HashMap<String, A_Situation> situationen;
	ArrayList<A_Aktion> aktionen_ID;
	HashMap<String, A_Aktion> aktionen = new HashMap<String, A_Aktion>();
	ArrayList<String> agenten;
	
	
	public HashMap<String, A_Aktion> getAktionen() {
		return aktionen;
	}
	public void setAktionen(HashMap<String, A_Aktion> aktionen) {
		this.aktionen = aktionen;
	}
	public ArrayList<A_Aktion> getAktionen_ID() {
		return aktionen_ID;
	}
	public void setAktionen_ID(ArrayList<A_Aktion> aktionen_ID) {
		this.aktionen_ID = aktionen_ID;
	}
	public HashMap<String, A_Situation> getSituationen() {
		return  situationen;
	}
	public void setSituationen(HashMap<String, A_Situation> situationen) {
		this.situationen = situationen;
	}
	public ArrayList<String> getAgenten() {
		return agenten;
	}
	public void setAgenten(ArrayList<String> agenten) {
		this.agenten = agenten;
	}

	public VG_Uebergabe() {
		// Diese Methode initialisiert das Spiel 
		this.aktionen_ID = new ArrayList<A_Aktion>();
		this.situationen = new HashMap<String, A_Situation>();// (Agent,Situation)
		this.agenten = new ArrayList<String>();
		
		// Aktionen von 4 Gewinnt erstellen(Steine einwerfen Spalte 0 - 6)
		for (int id = 0; id <= 6; id++)
			aktionen_ID.add(new VG_Aktion_SteinWerfenInSpalteNr(id));
		
		// Agenten erstellen bzw. ihnen einen Namen geben
		this.agenten.add(VG_Agenten.NAME_AGENT_1);
		this.agenten.add(VG_Agenten.NAME_AGENT_2);
		
		// leer Spielfeld (alles 0) erstellen
		int[][] matrix = new int[7][6];
		
		// Den Agenten den Startzustand zuweisen Sie in einer Liste speichern
		this.situationen.put(VG_Agenten.NAME_AGENT_1, new VG_Situation(matrix));
		this.situationen.put(VG_Agenten.NAME_AGENT_2, new VG_Situation(matrix));

	}
}
