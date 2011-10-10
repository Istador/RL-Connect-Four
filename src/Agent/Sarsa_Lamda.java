package Agent;

import java.util.ArrayList;
import java.util.HashMap;



public class Sarsa_Lamda extends Lernelement{
	Double epsilon;
	Double alpha;
	Double gamma;
	Double lambda;
	public Sarsa_Lamda(Double epsilon, Double alpha,Double gamma, Double lambda,  ArrayList<A_Aktion> aktionen) {
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
			
			//int episode = leistungselement.getVorrige_Episode();
			ArrayList<A_Situation_Aktion_Mit_Episode> history = leistungselement.getHistroy();
			HashMap<A_Situation_Aktion, Double> werte = leistungselement.getWerte();
			HashMap<A_Situation_Aktion, Double> ewerte = leistungselement.getE_Werte();
			if(history.size()>1){
				A_Aktion a_0 = history.get(history.size()-2).getAktion();
				A_Aktion a_1 = history.get(history.size()-1).getAktion();
				A_Situation s_0 = history.get(history.size()-2).getSituation();
				A_Situation s_1 = history.get(history.size()-1).getSituation();
			
		
				Double alter_Wert_0 = werte.get(new A_Situation_Aktion(s_0, a_0));
				Double alter_Wert_1 =  werte.get(new A_Situation_Aktion(s_1, a_1));
			
				double delta = reward + (gamma * alter_Wert_1 - alter_Wert_0);
				ewerte.put(new A_Situation_Aktion(s_1, a_1), ewerte.get(new A_Situation_Aktion(s_1, a_1)) + 1);
				int episode_T = leistungselement.vorrige_Episode;
				if(history.size() > 2){
				int i = history.size()-1;
		
				while(i > 1 && episode_T == history.get(i).getEpisode() && episode_T == history.get(i-1).getEpisode()){
					//System.out.println("DRIN mit " + i);
					s_0 = history.get(i).getSituation();
					a_0 = history.get(i).getAktion();
					
					alter_Wert_0 = werte.get(new A_Situation_Aktion(s_0, a_0)) + (alpha * delta * ewerte.get(new A_Situation_Aktion(s_0, a_0)));
						//System.out.println("Alter Wert: " +  alter_Wert_0);
					werte.put(new A_Situation_Aktion(s_0, a_0), alter_Wert_0);
					ewerte.put(new A_Situation_Aktion(s_0, a_0), ewerte.get(new A_Situation_Aktion(s_0, a_0))*gamma*lambda);
					i--;
				}
				
		
		
			}}
			leistungselement.update_Episode();
			leistungselement.berechne_Neue_Aktion(situation_1);
			leistungselement.setExplorierend(problemgenerator.start_Generator(leistungselement));
		}

	
}
	


