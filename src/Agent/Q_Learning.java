package Agent;

import java.util.List;
import java.util.Map;
import java.util.Set;
import static Agent.A_Utility.*;

/**
 * Q-Learning
 * Sutton 6.5 http://webdocs.cs.ualberta.ca/~sutton/book/6/node6.html
 */
public class Q_Learning extends Lernelement{

	boolean erste_Aktion = true;
	
	
	public Q_Learning(A_Factory factory, double alpha, double gamma, Set<A_Aktion> aktionen) {
		super(factory, alpha, gamma, aktionen);
	}
	
	
	
	
	
	@Override
	public void aufruf(A_Situation situation_1, double reward, Leistungselement le){
		le.neue_Situation(situation_1);
		
		le.berechne_Neue_Aktion(situation_1);
		Map<A_Situation_Aktion, Double> qwerte = le.getQWerte();
		List<A_Situation_Aktion_Mit_Episode> history = le.getHistory();
		
		if(history.size() >= 2){
			A_Situation_Aktion sa_t = history.get(history.size()-2 ).getSituationsAktion();
			double q_t = getDouble(qwerte, sa_t) ;
			int episode = sa_t.getEpisode();
			
			//s'
			A_Situation s_inct = history.get(history.size()-1 ).getSituation();
			
			//max_a'
			A_Aktion a_max = le.gib_Beste_Aktion(s_inct);
			
			//max_a'(Q(s',a'))
			double q_max = getDouble(qwerte, factory.newSituationsAktion(s_inct, a_max, episode));
			
			/**
		 	* Q-Learning-Algorithmus: neuen Wert berechnen
		 	* Q(s,a) <- Q(s,a) + alpha * [reward + gamma * max_a'(Q(s',a')) - Q(s,a) ]
		 	*/
			q_t = q_t + alpha * (reward + gamma * q_max  - q_t);
			
			//Wert aktualisieren
			putDouble(qwerte, sa_t, q_t);
		}
	}
		
}
