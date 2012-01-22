package Agent;

import java.util.Random;
import java.util.Set;

/**
 * Der Problemgenerator exploriert (mal was neues ausprobieren, unabhängig 
 * von den berechneten Werten) ab und zu zu einem bestimmten
 * Prozentsatz (epsilon) andere Aktionen
 *
 */
public class Problemgenerator {
	//Set<A_Aktion> aktionen;
	A_Aktion[] aktionen;
	double epsilon;
	
	A_Factory factory;
	
	
	public Problemgenerator(A_Factory factory, Set<A_Aktion> aktionen, double epsilon) {
		//this.aktionen = aktionen;
		this.factory = factory;
		this.aktionen = (A_Aktion[]) aktionen.toArray((A_Aktion[]) java.lang.reflect.Array.newInstance(A_Aktion.class, aktionen.size()));
		this.epsilon = epsilon;
	}
	
	
	/**
	 * Zufällig explorieren (random Aktion, nicht nach bewertung) oder nicht
	 * true, wenn exploriert wurde.
	 * false, wenn nicht exploriert wurde.
	 */
	public boolean start_Generator(Leistungselement leistungselement){
		Random rnd = new Random();
		//System.out.println("Problem start " + (aktionen.size()-1) );
		double rnd_z = rnd.nextDouble();
		if( Double.compare(rnd_z, epsilon) <= 0 ){
			System.out.println("EXPLORIEREN");
			try{
				A_Aktion action = explorieren(leistungselement);
				leistungselement.setAktuelle_Aktion(action);
				return true;
			}
			catch(NullPointerException e){
				return false;
			}
		}
		//leistungselement.berechne_Neue_Aktion(leistungselement.getNeuste_Situation());
		return false;
	}
	
	/**
	 * Eine zufällige Aktion auswählen, ohne Berücksichtigung der Bewertung
	 * (es werden keine Aktionen gewählt die bereits als Verboten bekannt sind)
	 */
	public A_Aktion explorieren(Leistungselement le) throws NullPointerException {
		//Endlosschleife verhindern, falls alle Aktionen verboten (Spielfeld voll)
		assert(aktionen.length != le.getVerbotene_Aktionen().size());
		
		A_Situation sit = le.getNeuste_Situation();
		int epi = le.getAktuelle_Episode();
		Random rnd = new Random();

		A_Aktion tmp;
		do{
			//wähle zufälllig eine Aktion
			tmp = aktionen[ Math.abs(rnd.nextInt() % aktionen.length) ];
		}
		//solange eine verbotene Aktion gewählt wurde
		while(le.getVerbotene_Aktionen().get(factory.newSituationsAktion(sit, tmp, epi)));
		
		return tmp;
	}
	
	
	
}
