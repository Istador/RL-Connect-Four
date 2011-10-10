package Agent;

import java.util.ArrayList;
import java.util.HashMap;

public class Sarsa extends Lernelement{

	int i =0;
	boolean erste_Aktion = true;
	Double epsilon;
	Double alpha;
	ArrayList<Double> reward_History = new ArrayList<Double>();
	ArrayList<A_Aktion> aktionen;
	A_Situation_Aktion letzte_S_A;
		public void verbotene_Aktion(Leistungselement leistungselement){
			

			leistungselement.verbotene_Aktion(leistungselement.getNeuste_Situation(), leistungselement.getAktuelle_Aktion());
			leistungselement.berechne_Neue_Aktion(leistungselement.getNeuste_Situation());
		}
		public Sarsa(Double epsilon, Double alpha, ArrayList<A_Aktion> aktionen) {
			super(alpha, alpha, aktionen);
				this.epsilon = epsilon;
				this.alpha = alpha;
				this.aktionen = aktionen;
		}
			public void aufruf(A_Situation situation_1, Double reward, Leistungselement leistungselement, Problemgenerator problemgenerator){
				leistungselement.neue_Situation(situation_1);
				if(erste_Aktion){
					erste_Aktion = false;
					leistungselement.erste_Aktion(situation_1);
				}
		
					System.out.println("UPDATE DER WERE");
					leistungselement.berechne_Neue_Aktion(situation_1);
					HashMap<A_Situation_Aktion, Double> werte = leistungselement.getWerte();
					ArrayList<A_Situation_Aktion_Mit_Episode> history = leistungselement.getHistroy();
				if(history.size() > 2){
					A_Aktion a_1 = history.get(history.size() -1 ).getAktion();
					A_Aktion a_0 = history.get(history.size() -2 ).getAktion();
					A_Situation s_1 = history.get(history.size() -1 ).getSituation();
					A_Situation s_0 = history.get(history.size()- 2 ).getSituation();
					Double alter_Wert = werte.get(new A_Situation_Aktion(s_0, a_0)) ;
					Double wert_a_s_1 = werte.get(new A_Situation_Aktion(s_1, a_1)) ;
					Double neuer_Wert= alter_Wert + alpha * (reward + epsilon * wert_a_s_1  - alter_Wert);
					if(neuer_Wert!=0.0)
					System.out.println("Neuer Wert: " + neuer_Wert);
					werte.put(new A_Situation_Aktion(s_0, a_0), neuer_Wert);
					System.out.println(s_0.toString() + " mit " + a_0.toString()+ " upgedatet mit Wert: " + neuer_Wert + " bei einem Reward von " + reward );
					leistungselement.setWerte(werte);
				
				}
				problemgenerator.start_Generator(leistungselement);
			}
		
}
