package Agent;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.util.Set;

/**
 * Die Agenten-Klasse für die Agenten die mit der Umwelt interagieren.
 * 
 * Kommunikation mit der Umwelt, und Verwendung der Lernelemente.
 * 
 * getUebergabe() und getLernelement() muss von der konkreten Anwendung implementiert werden.
 */
public abstract class Agent_Frame extends Agent {

	//public long blocktime = 100; 
	
	private static final long serialVersionUID = 1L;
	Problemgenerator problemgenerator = null;
	Leistungselement leistungselement = null;
	Lernelement lernelement = null;

	/*
	 * Variablen, um festzustellen, ob alles drei in diesem Spielzug geliefert wurde
	 */
	boolean get_Episode = false;
	boolean get_Reward = false;
	boolean get_Situation = false;
	
	int tmp_Episode = 0;
	double tmp_Reward = 0.0;
	A_Situation tmp_Situation = null;
	
	A_Aktion aktion;
	Set<A_Aktion> aktionen = null;
	GUI_Agent gui;

	
	/**
	 * initialisierung der KI
	 */
	@Override
	protected void setup() {
		System.out.println("Starten des Agenten");
		
		// A_Uebergabe uebergabe = new TicTacToe.Uebergabe();
		// A_Uebergabe uebergabe = new Grid.Uebergabe();
		// A_Uebergabe uebergabe = new Siedler_Sonstiges.Uebergabe();
		// A_Uebergabe uebergabe = new Uebergabe();
		A_Uebergabe uebergabe = getUebergabe();
		A_Factory factory = uebergabe.getFactory();
		
		aktionen = uebergabe.getAktionen_ID();
		gui = new GUI_Agent(getLocalName());
		leistungselement = new Leistungselement(factory, aktionen);
		lernelement = getLernelement(factory, aktionen);
		problemgenerator = new Problemgenerator(factory, aktionen, getEpsilon());

		addBehaviour(new Kommunikationselement(this));
		super.setup();
	}
	
	/**
	 * Implementierung durch die Anwendung, um die verwendete
	 * A_Uebergabe Klasse zu bestimmen
	 */
	public abstract A_Uebergabe getUebergabe();
	
	/**
	 * Implementiert durch die Anwendung, um bestimmen zu können
	 * welches Lernelement (und mit welchen Parametern) es erstellt
	 * werden soll.
	 */
	public abstract Lernelement getLernelement(A_Factory factory, Set<A_Aktion> aktionen);
	
	/**
	 * Implementiert durch die Anwendung, um bestimmen zu können
	 * wie oft exploriert werden soll.
	 */
	public abstract double getEpsilon();
	
	
	
	/** 
	 * Unterklasse Kommunikutationselement
	 * für die Kommunikation mit der Umwelt
	 */
	class Kommunikationselement extends CyclicBehaviour {
		private static final long serialVersionUID = 7453521122843010691L;

		Kommunikationselement(Agent a) {
			super(a);
		}

		Object content;

		@Override
		public void action() {
			
			// boolean first = true;
			//block(blocktime);
			
			/**
			 * Empfange Nachricht
			 */
			ACLMessage msg = receive();
			if (msg == null) {
				block();
				return;
			}
			
			/**
			 * Nachricht auswerten
			 */
			try {
				//Nachrichteninhalt
				Object content = msg.getContentObject();
				//content = msg.getContentObject();
				
				/**
				 * empfange neue Situation von Umwelt
				 */
				if (content instanceof A_Situation) {
					A_Situation old = tmp_Situation;
					tmp_Situation = (A_Situation) content;
					System.out.println(getLocalName() + " | "+old+" | neue Situation " + tmp_Situation.toString() + " erhalten ");
					
					//leistungselement.setNeuste_Situation(tmp_Situation);
					leistungselement.neue_Situation(tmp_Situation);
					
					get_Situation = true;
					doAction();
				}
				
				/**
				 * empfange Belohnung von Umwelt
				 */
				else if (content instanceof Double) {
					//wenn schon Reward erhalten, addieren
					if(get_Reward)
						tmp_Reward += (Double) content;
					else
						tmp_Reward = (Double) content;
					System.out.println(getLocalName()+" | "+tmp_Situation+" | Reward " + tmp_Reward + " erhalten");
					
					get_Reward = true;
					doAction();
				}
				
				/**
				 * empfange Episode von Umwelt
				 */
				else if (content instanceof Integer) {
					tmp_Episode = (Integer) content;
					
					System.out.println(getLocalName()+" | "+tmp_Situation+" | Episode "+tmp_Episode + " erhalten");
					
					get_Episode = true;
					doAction();
				}
				
				/**
				 * Bestätigung/Verwerfung der gewählten Aktion von der mwelt
				 * - Aktion wurde erfolgreich in der Umwelt ausgeführt oder nicht
				 */
				else if (content instanceof Boolean) {
					Boolean tmp = (Boolean) content;
					
					//Aktion erfolgreich ausgeführt
					if (tmp) {
						//Situations-Aktion in die History aufnehmen
						leistungselement.aktualisiere_History();
						// lernelement.aufruf(tmp_Situation, tmp_Reward, leistungselement, problemgenerator);
					}
					//Aktion von Umwelt verworfen (Verbotene Aktion)
					else {
						// bei einer verbotenen Aktion ist die Situation intern schon wieder anders.
						System.out.println(getLocalName()+" | "+tmp_Situation+" | Verbotene Aktion "+leistungselement.getAktuelle_Aktion()+"! Neue wählen.");

						//markiere Aktion als verboten
						leistungselement.verbotene_Aktion(
								leistungselement.getNeuste_Situation(),
								leistungselement.getAktuelle_Aktion()
								);
						
						//wähle andere Aktion
						if(!problemgenerator.start_Generator(leistungselement))
							leistungselement.berechne_Neue_Aktion(tmp_Situation);
						
						//sende gewählte Aktion an Umwelt
						sende_Aktion();
					}
				}
				
				//Aktualisierung der GUI
				gui.update_Werte(leistungselement.getQWerte());
				//System.out.println(getLocalName()+" | "+leistungselement.getWerte());

			
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		
		/**
		 * führe eine Aktion aus, wenn Reward, Episode und Situation geliefert wurden.
		 * 
		 * Reward, Episode und Situation kann in beliebiger Reihenfolge kommen.
		 * Eine Aktion wird erst dann ausgewählt, wenn alles 3 da sind. 
		 */
		private void doAction(){
			if (get_Reward && get_Episode && get_Situation) {
				
				//Variablen zurücksetzen
				get_Reward = false;
				get_Episode = false;
				get_Situation = false;
				
				//Episode ändern falls geändert (Reihenfolge hier OK, oder Episode erst nach dem Lernen?)
				leistungselement.setAktuelle_Episode(tmp_Episode);
				
				//Lerne aus dem Reward
				lernelement.aufruf(tmp_Situation, tmp_Reward, leistungselement);
				
				//nächste Aktion auswählen
				if(!problemgenerator.start_Generator(leistungselement))
					leistungselement.berechne_Neue_Aktion(tmp_Situation);
				
				//gewählte Aktion an Umwelt senden
				sende_Aktion();
			}
		}
		

		//sende die berechnete/gewählte Aktion an die Umwelt
		public void sende_Aktion() {
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			System.out.println(getLocalName()+" | "+tmp_Situation+" | Sende Aktion "+ leistungselement.getAktuelle_Aktion().toString());
			A_Aktion aktion = leistungselement.getAktuelle_Aktion();
			try {
				msg.setContentObject((java.io.Serializable) aktion);
			} catch (IOException e) {
				e.printStackTrace();
			}
			msg.addReceiver(new AID("umwelt", AID.ISLOCALNAME));
			send(msg);
		}

		/*
		public void sende_Flag() {
			//System.out.println("Sende true");
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			//System.out.println(getLocalName()+" | Sende Aktion "+ leistungselement.getAktuelle_Aktion().toString());
			Boolean flag = new Boolean(true);
			try {
				msg.setContentObject((java.io.Serializable) flag);
			} catch (IOException e) {
				e.printStackTrace();
			}
			msg.addReceiver(new AID("umwelt", AID.ISLOCALNAME));
			send(msg);
		}
		 */
	};
	
	/**
	 * Ende Kommunikations Unterklasse
	 */
	
	
	
}
