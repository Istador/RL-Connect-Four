package Agent;

import java.util.ArrayList;

public abstract class Lernelement {

	boolean erste_Aktion = true;
	Double epsilon;
	Double alpha;
	ArrayList<Double> reward_History = new ArrayList<Double>();
	ArrayList<A_Aktion> aktionen;
	A_Situation_Aktion letzte_S_A;
		public abstract void  verbotene_Aktion(Leistungselement leistungselement);
		public Lernelement(Double epsilon, Double alpha, ArrayList<A_Aktion> aktionen) {
				this.epsilon = epsilon;
				this.alpha = alpha;
				this.aktionen = aktionen;
		}
			public abstract void aufruf(A_Situation situation_1, Double reward, Leistungselement leistungselement, Problemgenerator problemgenerator);
}