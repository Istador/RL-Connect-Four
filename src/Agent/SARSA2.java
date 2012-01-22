package Agent;

import java.util.List;
import java.util.Map;
import java.util.Set;
import static Agent.A_Utility.*;

/**
 * SARSA
 * Sutton 6.4 http://webdocs.cs.ualberta.ca/~sutton/book/ebook/node64.html
 */
public class SARSA2 extends Lernelement{	
	
	public SARSA2(A_Factory factory, double alpha, double gamma, Set<A_Aktion> aktionen) {
		super(factory, alpha, gamma, aktionen);
	}
	
	
	
	@Override
	public void aufruf(A_Situation situation_1, double reward, Leistungselement le){
		le.neue_Situation(situation_1);
		
		le.berechne_Neue_Aktion(situation_1);
		Map<A_Situation_Aktion, Double> qwerte = le.getQWerte();
		List<A_Situation_Aktion_Mit_Episode> history = le.getHistory();

		if(history.size() >= 2){
			A_Situation_Aktion sa_t = history.get(history.size()-2).getSituationsAktion();
			A_Situation_Aktion sa_inct = history.get(history.size()-1).getSituationsAktion();

			double q_t = getDouble(qwerte, sa_t); // Q_{t}
			double q_inct = getDouble(qwerte, sa_inct) ; // Q_{t+1}
			
			/**
			 * SARSA-Algorithmus: neuen Wert berechnen
			 * Q(s_t,a_t) <- Q(s_t,a_t) + \alpha [r_{t} + \gamma Q(s_{t+1}, a_{t+1})-Q(s_t,a_t)]
			 */
			q_t = q_t + alpha * (reward + gamma * q_inct  - q_t);
			
			//Wert aktualisieren
			putDouble(qwerte, sa_t, q_t);
		}
	}
		
}
