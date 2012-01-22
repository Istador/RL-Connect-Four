package vierGewinnt.KI;

import java.util.Set;

import Agent.A_Aktion;
import Agent.A_Factory;
import Agent.A_Uebergabe;
import Agent.Agent_Frame;
import Agent.Lernelement;
import Agent.Q_Learning;
import Agent.Q_Learning_Lambda;
import Agent.SARSA2;
import Agent.SARSA2_Lambda;
/**
 * Implementierung der Agent_Frame Klasse für Vier Gewinnt.
 *  Auswahl des Lernalgorithmus, Algorithmen-Parameter, und die A_Ubergabe-Klasse
 *  public static instanzvariablen werden von der GUI verändert, vor dem
 *  Starten des RL-Trainings
 */
public class VG_Agent_Frame extends Agent_Frame {
	private static final long serialVersionUID = -4692175259280395113L;

	/**
	 * Algorithmusauswahl
	 * 1 = Sarsa
	 * 2 = Sarsa Lambda
	 * 3 = Q Learning
	 * 4 = Q Learning Lambda
	 */
	public static byte algorithmus = 0; //default

	/**
	 * Epsilon
	 * Wahrscheinlichkeit, das exploriert wird (zufällig statt nach Erfahrung handeln)
	 * Wertebereich: 0.0 ... 1.0
	 * 0: keine exploration (zufall nur beim ersten auftreten einer neuen situation)
	 * 1: nur explorieren (gelerntes nicht verwenden)
	 */
	public static double epsilon = 0.2;
	
	
	/**
	 * Alpha
	 * Learning Rate
	 * Zu welchem Anteil neue Informationen (Belohnung) alte überschreiben
	 * Wertebereich: 0.0 ... 1.0
	 * 0: nicht dazulernen (alte Werte behalten)
	 * 1: alte Werte nicht in den aktuellen Wert mit reinnehmen (nur neue Werte) 
	 */
	public static double alpha = 0.2;
	
	
	/**
	 * Gamma
	 * Discount Factor
	 * Wichtigkeit neuer Belohnungen gegenüber alten Belohnungen
	 * Wertebereich: 0.0 ... 1.0
	 * 0: neue Belohnungen wichtig
	 * 1: alte Belohnungen wichtig
	 */
	public static double gamma = 0.2;
	
	
	/**
	 * Lambda
	 * Trace Decay
	 * Beeinflusst wie stark weiter zurückliegende Zustände mit dem Ergebnis
	 * (Reward) zusammenhängen.
	 * Wertebereich: 0.0 ... 1.0
	 * 0: nur der letzte/aktuelle Zustand wichtig
	 * 1: Monte Carlo (frühere Zustände so wichtig wie die letzten)
	 */
	public static double lambda = 0.2;

	
	@Override
	public A_Uebergabe getUebergabe() {
		return new vierGewinnt.KI.VG_Uebergabe();
	}

	@Override
	public Lernelement getLernelement(A_Factory factory, Set<A_Aktion> aktionen) {
		switch(algorithmus){
			case 1: return new SARSA2(factory, alpha, gamma, aktionen);
			case 2: return new SARSA2_Lambda(factory, alpha, gamma, lambda, aktionen);
			case 3: return new Q_Learning(factory, alpha, gamma, aktionen);
			case 4: return new Q_Learning_Lambda(factory, alpha, gamma, lambda, aktionen);
			default: return new SARSA2(factory, alpha, gamma, aktionen);
		}
	}

	@Override
	public double getEpsilon() {
		return epsilon;
	}

}
