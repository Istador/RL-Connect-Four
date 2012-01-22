package Agent;

/**
 * Factory-Klasse, die von der Anwendung bei bedarf überschrieben werden kann,
 * um Objekte einer eigenen A_Situation_Aktion Klasse zu erzeugen und verwenden
 *
 */
public class A_Factory {
	
	/**
	 * Erzeugt aus einer A_Situation und einer A_Aktion eine A_Situation_Aktion
	 */
	public A_Situation_Aktion newSituationsAktion(A_Situation s, A_Aktion a, int episode){
		return new A_Situation_Aktion(s, a, episode);
	}
	
}
