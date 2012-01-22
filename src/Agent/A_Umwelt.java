package Agent;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * Die Umwelt / Spielumgebung, ist ein eigener Agent, 
 * der mit den normalen Agenten mittels JADE Message-Passing kommuniziert.
 *
 * Die Umwelt teilt den Agenten mit wie ihre Aktionen die Umwelt beeinflusst
 * haben (welche neue Situation es gibt) und ob sie eine Belohnung erhalten
 * 
 * einige Methoden müssen von der Anwendung konkret implementiert werden,
 * um dem speziellem Spiel zu entsprechen
 */
public abstract class A_Umwelt extends Agent {
	private static final long serialVersionUID = 1L;

	Set<String> agenten;
	A_Situation situation;
	Set<A_Aktion> aktionen;
	
	int episode = 0; //welche Spielrunde
	
	Map<String, Boolean> reward_Situation_Gesendet = new java.util.HashMap<String, Boolean>();
	Map<String, Boolean> aktion_Ausgeführt = new java.util.HashMap<String, Boolean>();
	
	
	
	
	
	public Set<String> getAgenten() {return agenten;}
	//public void setAgenten(Set<String> agenten) {this.agenten = agenten;}
	
	public Map<String, Boolean> getReward_Situation_Gesendet() {return reward_Situation_Gesendet;}
	//public void setReward_Situation_Gesendet(Map<String, Boolean> reward_Situation_Gesendet) {this.reward_Situation_Gesendet = reward_Situation_Gesendet;}
	
	public A_Situation getSituation() {return situation;}
	public void setSituation(A_Situation situation) {this.situation = situation;}
	
	
	
	
	
	
	public A_Umwelt(A_Uebergabe uebergabe) {
		//initialisiere
		this.agenten = uebergabe.getAgenten();
		this.situation = uebergabe.getSituation();
		this.aktionen = uebergabe.getAktionen_ID();
		for (String agent : agenten) {
			reward_Situation_Gesendet.put(agent, false);
		}

	}

	public void sende_Situation(String agent) {
		addBehaviour(new Sende_Situation(agent));
	}

	public void sende_Reward(String agent) {
		addBehaviour(new Sende_Reward(agent));
	}
	
	public void sende_Episode(String agent) {
		addBehaviour(new Sende_Episode(agent));
	}
	
	/*
	@Override
	protected void setup() {
		super.setup();
	}
	*/
	
	
	/**
	 * Methode wird aufgerufen wenn sich an der Situation etwas ändert,
	 * um das Spielfeld neu zu zeichnen. Muss von der Anwendung implementiert
	 * werden, um dem Spiel zu entsprechen. 
	 */
	public abstract void aktualisiere_GUI();

	/**
	 * Methode, damit die Umwelt feststellen kann ob eine bestimmte Aktion
	 * erlaubt ist oder nicht, um einem Agenten verbote Aktionen zurückzuweisen
	 */
	public abstract boolean aktion_Moeglich(A_Aktion a_Aktion, String agent);

	/**
	 * Initialisieren des Spielfeldes / der Spielsituation
	 */
	public abstract void set_Inital();

	/**
	 * wird AFAIK nirgendwo verwendet -> raus, kann entfernt werden.
	 */
	public abstract A_Situation gib_Situation_Agent();

	/**
	 * Berechne die Belohnung für den Agenten für die aktuelle Spielsituation
	 */
	public abstract Double berechne_Reward(String agent);

	/**
	 * true wenn eine Episode vorbei ist (ein Spiel zuende ist)
	 */
	public abstract boolean istEpisodeVorbei();
	
	
	
	/**
	 * sende eine Nachricht an einen Agenten über JADE
	 */
	public void sende(String agent, java.io.Serializable inhalt) {
		try {
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			msg.setContentObject(inhalt);
			msg.addReceiver(new AID(agent, AID.ISLOCALNAME));
			send(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * sende eine Antwort zu einer erhaltenen Nachricht
	 */
	public void reply(ACLMessage msg, java.io.Serializable inhalt){
		try {
			ACLMessage reply = msg.createReply();
			reply.setPerformative(ACLMessage.INFORM);
			reply.setContentObject(inhalt);
			send(reply);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * nächste Spielrunde
	 */
	public void neue_Episode() {
		set_Inital();
		System.out.println(getLocalName()+" | NEUE EPISODE");
		episode++;
	}
	
	
	/**
	 * Unterklasse um einem Agenten eine neue Situation zu schicken.
	 */
	public class Sende_Situation extends SimpleBehaviour {
		private static final long serialVersionUID = -4581413296788437853L;
		String agent_T;
		A_Situation situation;
		boolean done = false;
		boolean ausfuehrung = false;

		public Sende_Situation(String agent) {
			this.agent_T = agent;
			action();
		}

		@Override
		public boolean done(){return done;}
		
		@Override
		public void action() {
			if (!ausfuehrung) {
				ausfuehrung = true;
				situation = getSituation();
				situation.setEpisode(episode);
				sende(agent_T, situation);
				done = true;
			}
		}
		
	}



	/**
	 * Unterklasse, um einen Agenten eine Belohnung zu schicken
	 */
	public class Sende_Reward extends SimpleBehaviour {
		private static final long serialVersionUID = -1809193998470732489L;
		
		String agent;
		boolean ausfuehrung = false;
		boolean done = false;

		public Sende_Reward(String agent) {
			this.agent = agent;
			action();
		}

		@Override
		public boolean done() {return done;}
		
		@Override
		public void action() {
			Double reward;// = berechne_Reward(agent);
			//sende(agent, reward);
			
			//reward für alle
			for(String a: agenten){
				reward = berechne_Reward(a);
				sende(a, reward);
			}
			
			//Spiel nun vorbei
			if(istEpisodeVorbei()){
				neue_Episode();
			}
			done = true;
		}
		
	}


	/**
	 * Unterklasse um einem Agenten die aktuelle Episode zu schicken
	 */
	public class Sende_Episode extends SimpleBehaviour {
		private static final long serialVersionUID = 1681223474597804222L;
		
		String agent_T;
		boolean ausfuehrung = false;
		boolean done = false;
		
		public Sende_Episode(String agent_T) {
			this.agent_T = agent_T;
			action();
		}

		@Override
		public boolean done() {return done;}
		
		@Override
		public void action() {
			Integer ep = episode;
			sende(agent_T, ep);
			//System.out.println(getLocalName()+" | Sende Episode mit " + ep);
			done = true;
		}


	}


	/**
	 * Unterklasse, die die Kommunikation mit dem Agenten bezüglich
	 * der Spiellogik handelt.
	 * 
	 * Empfangen von Aktionen des Agenten, und der Ausführung auf die Spielwelt
	 */
	public class Kommunikation extends SimpleBehaviour {
		private static final long serialVersionUID = 1L;
		boolean done = false;

		public Kommunikation(Agent a) {
			super(a);
			while (!done)
				action();
		}

		@Override
		public boolean done() {return done;}

		@Override
		public void action() {
			ACLMessage msg = receive();
			if (msg == null) {
				block();
				return;
			}
			
			/**
			 * Empfangene Nachricht vom Agenten auswerten
			 */
			try {
				Object content = msg.getContentObject();
				
				/**
				 * Aktion vom Agenten erhalten
				 */
				if (content instanceof A_Aktion) {
					A_Aktion a = (A_Aktion) content;
					String agent = msg.getSender().getLocalName();
					
					//existiert die Aktion?
					if(aktionen.contains(a)){
						//Verbotene Aktion
						if (!aktion_Moeglich(a, agent)) {
							System.out.println(getLocalName()+" | "+situation+" | Aktion "+a+" von Agent "+agent+" nicht möglich!");
							//beim Agenten Aktion verwerfen
							reply(msg, new Boolean(false));
						} 
						//Aktion möglich
						else {
							
							//bestätige dem Agenten die Aktion
							reply(msg, new Boolean(true));
							
							A_Situation old = situation;
							
							//aktion ausführen, und Situation verändern
							situation = a.fuehre_Aus(situation, agent);
							
							System.out.println(getLocalName()+" | "+old+" | legale Aktion " + a.toString() +" von Agent "+agent+" erhalten, neue Situation "+getSituation()+".");
							done = true;
						}
					}
					//Aktion existiert nicht
					else{
						System.out.println(getLocalName()+" | "+situation+" | Fehler: Nicht existierende Aktion "+a+" von Agent "+agent);
					}
				}
			} catch (Exception e) {
				//e.printStackTrace();
			}

		};
	}

}