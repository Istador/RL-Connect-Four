package Agent;

import java.util.ArrayList;
import java.util.Random;

public class Problemgenerator {
	ArrayList<A_Aktion> aktionen;
	int epsilon;
	public Problemgenerator(ArrayList<A_Aktion> aktionen, int epsilon) {
		this.aktionen = aktionen;
		this.epsilon = epsilon;
	}
	public boolean start_Generator(Leistungselement leistungselement){
		try {
			
	
		 Random rnd = new Random();
		 //System.out.println("Problem start " + (aktionen.size()-1) );
		int rnd_z =rnd.nextInt()%100;
		if(rnd_z < 0)
			rnd_z = rnd_z *-1;
		if( rnd_z>epsilon){
			System.out.println("EXPLORIEREN");
				explorieren(leistungselement);			
				return true;
		}
		
		
		//leistungselement.berechne_Neue_Aktion(leistungselement.getNeuste_Situation());
	
	} catch (Exception e) {
		System.err.println("GENERATOR");
	}
			return false;
	}
	public void explorieren(Leistungselement leistungselement){
		Random rnd = new Random();
		int wahl = rnd.nextInt() % (aktionen.size());
		if(wahl < 0)
			wahl = wahl *-1;
		//System.out.println("Wahl " + wahl + " aktionen.size()= " + aktionen.size());
		//System.err.println("Probieren START");
		A_Situation_Aktion tmp = new A_Situation_Aktion( leistungselement.getNeuste_Situation(), aktionen.get(wahl));
		while(leistungselement.getVerbotene_Aktionen().get(tmp)){
			
			wahl = rnd.nextInt() % aktionen.size();
			
			if(wahl < 0)
				wahl = wahl *-1;
		//	System.out.println("In Schleife Wahl " + wahl);
			tmp = new A_Situation_Aktion( leistungselement.getNeuste_Situation(), aktionen.get(wahl));
		}

		leistungselement.setAktuelle_Aktion(wahl);
	//	System.err.println("Probieren ENDE");
		
	}
}
