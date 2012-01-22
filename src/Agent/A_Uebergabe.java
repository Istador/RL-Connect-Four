package Agent;

import java.util.Set;

/**
 * Die Übergabe wird von der Anwendung konkret initialisiert 
 */
public class A_Uebergabe {
	//Abbildung: Agent -> A_Situation, in welcher Situation ein Agent ist
	protected A_Situation situation;
	
	//alle möglichen Aktionen aus denen die Agenten wählen können
	protected Set<A_Aktion> aktionen_ID;
	
	//welche Agenten es gibt
	protected Set<String> agenten;
	
	//factory zum erzeugen von A_Situation_Aktion Objekten, kann überschrieben werden.
	protected A_Factory factory = new A_Factory();

	
	
	public Set<A_Aktion> getAktionen_ID() {return aktionen_ID;}
	//public void setAktionen_ID(Set<A_Aktion> aktionen_ID) {this.aktionen_ID = aktionen_ID;}
	
	public A_Situation getSituation() {return situation;}
	//public void setSituationen(Map<String, A_Situation> situationen) {this.situationen = situationen;}
	
	public Set<String> getAgenten() {return agenten;}
	//public void setAgenten(Set<String> agenten) {this.agenten = agenten;}
	
	public A_Factory getFactory(){return this.factory;}
	//public void setFactory(A_Factory factory){this.factory = factory;}
}
