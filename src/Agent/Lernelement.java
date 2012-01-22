package Agent;

import java.util.Set;

/**
 * Abstrakte Klasse ("Interface") für die verschiedenen Lernverfahren,
 * die von den spezifischen Lernverfahren geerbt wird
 */
public abstract class Lernelement {

	
	/**
	 * Alpha
	 * Learning Rate
	 * Zu welchem Anteil neue Informationen (Belohnung) alte überschreiben
	 * Wertebereich: 0.0 ... 1.0
	 * 0: nicht dazulernen (alte Werte behalten)
	 * 1: alte Werte nicht in den aktuellen Wert mit reinnehmen (nur neue Werte) 
	 */
	protected double alpha;
	
	/**
	 * Gamma
	 * Discount Factor
	 * Wichtigkeit neuer Belohnungen gegenüber alten Belohnungen
	 * Wertebereich: 0.0 ... 1.0
	 * 0: neue Belohnungen wichtig
	 * 1: alte Belohnungen wichtig
	 */
	protected double gamma;
	
	
	/**
	 * alle theoretisch möglichen Aktionen
	 */
	protected Set<A_Aktion> aktionen;
	
	
	/**
	 * Factory um Situations-Aktionen zu erstellen
	 */
	protected A_Factory factory;
	
	
	/**
	 * Konstruktor
	 */
	protected Lernelement(A_Factory factory, double alpha, double gamma, Set<A_Aktion> aktionen) {
		this.factory = factory;
		this.alpha = alpha;
		this.gamma = gamma;
		this.aktionen = aktionen;
	}
	
	/**
	 * Aufruf des Lernelements, mit der end-Aktion und der Belohnung 
	 */
	public abstract void aufruf(A_Situation situation_1, double reward, Leistungselement leistungselement);
	
}