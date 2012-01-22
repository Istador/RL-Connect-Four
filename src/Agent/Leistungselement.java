package Agent;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import static Agent.A_Utility.*;

/**
 * Das Leistungselement speichert die vom Lernelement berechneten Werte,
 * und berechnet die nächst-beste Aktion für den aktuellen Zustand 
 */
public class Leistungselement {
	//boolean explorierend = false;
	//int episode=0;
	int aktuelle_Episode=0;
	//int vorrige_Episode=0;

	A_Factory factory = null;
	
	A_Aktion aktuelle_Aktion = null;
	A_Situation neuste_Situation = null;
	//A_Situation_Aktion letzte_A_S = null;
	//Set<A_Situation> bekannte_Situationen = new java.util.HashSet<A_Situation>();
	
	/*
	 * E-Werte werden für die Lambda-Lernverfahren benötigt
	 */
	Map<A_Situation_Aktion, Double> e_werte= new java.util.HashMap<A_Situation_Aktion, Double>();

	//A_Aktion[] aktionen;
	Set<A_Aktion> aktionen;
	Map<A_Situation_Aktion, Boolean> verbotene_Aktionen = new java.util.HashMap<A_Situation_Aktion, Boolean>();

	/**
	 * Q-Werte: Situation x Aktion -> Bewertung
	 */
	Map<A_Situation_Aktion, Double> q_werte = new java.util.HashMap<A_Situation_Aktion, Double>();
	
	/**
	 * History von Situations-Aktionen, in geordneter Reihenfolge und Episoden-Information
	 */
	List<A_Situation_Aktion_Mit_Episode> history = new java.util.ArrayList<A_Situation_Aktion_Mit_Episode>();
	
	
	
	
	public Leistungselement(A_Factory factory, Set<A_Aktion> aktionen) {
		//this.aktionen = aktionen;
		this.factory = factory;
		this.aktionen = aktionen;//(A_Aktion[]) aktionen.toArray((A_Aktion[]) java.lang.reflect.Array.newInstance(A_Aktion.class, aktionen.size()));
	}
	

	
	
	
	
	public int getAktuelle_Episode() {return aktuelle_Episode;}
	public void setAktuelle_Episode(int aktuelle_Episode) {
		if(this.aktuelle_Episode != aktuelle_Episode){
			//leere für die nächste Episode die Menge der Verbotenen Aktionen (um Speicher zu sparen)
			verbotene_Aktionen_Reset();
			this.aktuelle_Episode = aktuelle_Episode;
		}
	}
	
	public Map<A_Situation_Aktion, Double> getEWerte() {return e_werte;}
	
	public Map<A_Situation_Aktion, Boolean> getVerbotene_Aktionen() {return verbotene_Aktionen;}

	public List<A_Situation_Aktion_Mit_Episode> getHistory() {return history;}

	public A_Situation getNeuste_Situation() {return neuste_Situation;}
	public void setNeuste_Situation(A_Situation neuste_Situation) {this.neuste_Situation = neuste_Situation;}
	
	public A_Aktion getAktuelle_Aktion() {return aktuelle_Aktion;}
	public void setAktuelle_Aktion(A_Aktion neue_Aktion) {this.aktuelle_Aktion = neue_Aktion;}
	
	public Map<A_Situation_Aktion, Double> getQWerte() {return q_werte;}

	
	
	
	
	
	
	/**
	 * Situation hat sich verändert
	 */
	public void neue_Situation(A_Situation situation){
		neuste_Situation = situation;
	}
	
	
	/**
	 * Aktuelle Situations-Aktion in die History eintragen
	 */
	public void aktualisiere_History(){
		history.add(new A_Situation_Aktion_Mit_Episode(factory.newSituationsAktion(neuste_Situation, aktuelle_Aktion, aktuelle_Episode), aktuelle_Episode));
	}
	
	
	/**
	 * Aktion in der Situation als Verboten markieren
	 */
	public void verbotene_Aktion(A_Situation situation, A_Aktion aktion ){
		verbotene_Aktionen.put(factory.newSituationsAktion(situation, aktion, aktuelle_Episode), true);
	}
	
	
	/**
	 * Vergessen welche Aktionen verboten waren
	 */
	public void verbotene_Aktionen_Reset(){
		verbotene_Aktionen = new java.util.HashMap<A_Situation_Aktion, Boolean>();
	}
	
	
	/**
	 * Aufforderung von außen eine neue Aktion zu wählen/berechnen
	 */
	public void berechne_Neue_Aktion(A_Situation situation){
		neuste_Situation = situation;
		aktuelle_Aktion = gib_Beste_Aktion(situation);
	}
	
	/**
	 * Die beste Aktion entsprechend ihrere Bewertung auswählen.
	 * wenn gleichwertig -> zufällig 
	 */
	public A_Aktion gib_Beste_Aktion(A_Situation situation){
		Random rnd = new Random();
		
		A_Aktion tmp_A = null;
		Double tmp_reward = Double.MIN_VALUE;
		
		for(A_Aktion a : aktionen){
			//A_Situation_Aktion tmp_sa = factory.newSituationsAktion(situation, aktionen[i], aktuelle_Episode);
			A_Situation_Aktion tmp_sa = factory.newSituationsAktion(situation, a, aktuelle_Episode);
			if(!getBoolFalse(verbotene_Aktionen, tmp_sa)){
				Double tmp_wert = getDouble(q_werte, tmp_sa);
				//den besseren Wert hinzufügen
				if(tmp_A == null || Double.compare(tmp_wert, tmp_reward) > 0){
					tmp_reward = tmp_wert;
					tmp_A = a;
					}
				//wenn gleichwertig
				else if(tmp_wert.equals(tmp_reward) ){
					/**
					 * zufällig (zu 50%) den neuen wählen
					 * Achtung: dadurch ungleiche Verteilung des Zufalls
					 * zugunste der weiter hinten liegenden Aktionen
					 */
					if(rnd.nextBoolean()){
						tmp_reward = tmp_wert;
						tmp_A = a;
					}
				}
			}	
		}
		
		/**
		 * Fehler: es wurde keine Aktion gewählt (es gibt keine?)
		 */
		if(tmp_A == null){
			System.err.println("FEHLER: Leistungsselement hat keine Aktion ausgewählt.");
			//System.err.println(aktionen.toString());
			for(A_Aktion a : aktionen){
				A_Situation_Aktion sa = factory.newSituationsAktion(situation, a, aktuelle_Episode);
				System.err.println("S:"+situation+", A:"+a+" -> "+sa+", verboten:"+getBoolFalse(verbotene_Aktionen,sa));
			}
		}
		return tmp_A;
	}
	
	
	
}
