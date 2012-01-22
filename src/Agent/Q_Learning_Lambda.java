package Agent;

import java.util.List;
import java.util.Map;
import java.util.Set;
import static Agent.A_Utility.*;

/**
 * Q-Learing Lambda (Watkins)
 * Sutton 7.6 http://webdocs.cs.ualberta.ca/~sutton/book/ebook/node78.html
 * 
 * Bewerte weiter zurückliegende A_Situation_Aktion geringer,
 * als kürzlich erfolgte.
 */
public class Q_Learning_Lambda extends Lernelement {
	
	
	/**
	 * Lambda
	 * Trace Decay
	 * Beeinflusst wie stark weiter zurückliegende Zustände mit dem Ergebnis
	 * (Reward) zusammenhängen.
	 * Wertebereich: 0.0 ... 1.0
	 * 0: nur der letzte/aktuelle Zustand wichtig
	 * 1: Monte Carlo (frühere Zustände so wichtig wie die letzten)
	 */
	double lambda;
	
	
	public Q_Learning_Lambda(A_Factory factory, double alpha, double gamma, double lambda,  Set<A_Aktion> aktionen) {
		super(factory, alpha, gamma, aktionen);
		this.lambda = lambda;
	}
	
	

	@Override
	public void aufruf(A_Situation situation_1, double reward, Leistungselement le) {
		List<A_Situation_Aktion_Mit_Episode> history = le.getHistory();
		Map<A_Situation_Aktion, Double> qwerte = le.getQWerte();
		Map<A_Situation_Aktion, Double> ewerte = le.getEWerte();
		
		if (history.size() >= 2) {
			A_Situation_Aktion sa_t = history.get(history.size()-2).getSituationsAktion();
			
			//Q(s,a)
			double q_t = getDouble(qwerte, sa_t);
			int episode = sa_t.getEpisode();
					
			//Q(s', a')
			A_Situation_Aktion sa_tmp = history.get(history.size()-1 ).getSituationsAktion();
			A_Situation s_inct = sa_tmp.getSituation(); // s'
			A_Aktion a_inct = sa_tmp.getAktion(); // a'
			
			//a* <- arg max_b(Q(s',b))
			A_Aktion a_star = le.gib_Beste_Aktion(s_inct); //maximum (beste Aktion) ermitteln

			//Q(s', a*) 
			double q_star = getDouble(qwerte, factory.newSituationsAktion(s_inct, a_star, episode));
			
			//Delta <- reward + gamma * Q(s',a*) - Q(s,a)
			double delta = reward + (gamma * q_star - q_t);
			
			//e(s,a) <- e(s,a) +1
			double e_t = getDouble(ewerte, sa_t);
			putDouble(ewerte, sa_t, e_t + 1);
			
			//für alle Situations-Aktionen in dieser Episode 
			for(
				int i = history.size() - 1
				;
				i >= 0   &&   episode == history.get(i).getEpisode()
				;
				i--
			)
			{
				sa_t = history.get(i).getSituationsAktion(); // (s,a)
				q_t = getDouble(qwerte, sa_t); // Q(s,a)
				e_t = getDouble(ewerte, sa_t); // e(s,a)
				
				//Q(s,a) <- Q(s,a) + alpha * delta * e(s,a)
				q_t = q_t + alpha * delta * e_t;
				putDouble(qwerte, sa_t, q_t);
				
				//if( a' == a* )
				if(a_inct.equals(a_star)){
					//e(s,a) <- gamma * lambda * e(s,a)
					e_t = gamma * lambda * e_t;
					putDouble(ewerte, sa_t, e_t);
				}
				else{
					//e(s,a) <- 0
					putDouble(ewerte, sa_t, 0.0);
				}
			}
		}
	}

		
}
