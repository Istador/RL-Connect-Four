package Agent;

import java.util.ArrayList;
import java.util.HashMap;

public class Q_Learning_Off_Policy extends Lernelement {
	Double epsilon;
	Double alpha;
	Double gamma;
	Double lambda;
	public Q_Learning_Off_Policy(Double epsilon, Double alpha,Double gamma, Double lambda,  ArrayList<A_Aktion> aktionen) {
		super(alpha, alpha, aktionen);
		this.epsilon = epsilon;
		this.alpha = alpha;
		this.aktionen = aktionen;
		this.gamma = gamma;
		this.lambda = lambda;
}
	@Override
	public void verbotene_Aktion(Leistungselement leistungselement){
		

		leistungselement.verbotene_Aktion(leistungselement.getNeuste_Situation(), leistungselement.getAktuelle_Aktion());
		//leistungselement.berechne_Neue_Aktion(leistungselement.getNeuste_Situation());
	}

	@Override
	public void aufruf(A_Situation situation_1, Double reward, Leistungselement leistungselement, Problemgenerator problemgenerator) {
		
		leistungselement.neue_Situation(situation_1);
		if(erste_Aktion){
	
			leistungselement.erste_Aktion(situation_1);
		}
		else{
	
				
			
			//ArrayList<A_Aktion> aktionen = leistungselement.getAktionen();
			//HashMap<A_Situation_Aktion, Boolean> verbotene_Aktionen =leistungselement.getVerbotene_Aktionen();
			HashMap<A_Situation_Aktion, Double> werte = leistungselement.getWerte();
			//HashMap<A_Situation_Aktion, Double> e_werte = leistungselement.getE_Werte();
			ArrayList<A_Situation_Aktion_Mit_Episode> history = leistungselement.getHistroy();
			if(history.size() > 1){
			A_Aktion a_0 = history.get(history.size()-2).getAktion();
			
			A_Situation s_0 = history.get(history.size()-2).getSituation();
			A_Situation s_1 = history.get(history.size()-1).getSituation();
			A_Aktion a_1=null;
			//Double wert_Tmp=0.0;
			a_1 = leistungselement.gib_Beste_Aktion(s_1);
	
			Double alter_Wert_0 = werte.get(new A_Situation_Aktion(s_0, a_0));
			
			Double alter_Wert_1 =  werte.get(new A_Situation_Aktion(s_1, a_1));
			
			Double neuer_Wert = alter_Wert_0 + alpha *(reward + gamma * alter_Wert_1 - alter_Wert_0);
			werte.put(new A_Situation_Aktion(s_0, a_0), neuer_Wert);
			if(neuer_Wert > 0.0)
				System.out.println("Verteilen von " + neuer_Wert + " mit " + alter_Wert_1 + " für die Situation: " + s_0  + " und die Aktion " + a_0);
			} 
			
		}}
		
}
