package Agent;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.io.IOException;
import java.util.ArrayList;


public class Agent_Frame extends Agent{

	private static final long serialVersionUID = 1L;
	Problemgenerator problemgenerator = null;
	Leistungselement leistungselement = null;
	Lernelement lernelement = null;
	
	boolean get_Episode = false;
	boolean get_Reward = false;
	boolean get_Situation = false;
	Double tmp_Reward = 0.0;
	A_Situation tmp_Situation = null;
	A_Aktion aktion;
	ArrayList<A_Aktion> aktionen = null;
	GUI_Agent gui;
	@Override
	protected void setup() {
		System.out.println("Starten des Agenten");
	//A_Uebergabe uebergabe = new TicTacToe.Uebergabe();
	//A_Uebergabe uebergabe = new Grid.Uebergabe();
		//A_Uebergabe uebergabe = new Siedler_Sonstiges.Uebergabe();
		//	A_Uebergabe uebergabe = new Uebergabe();
		
		A_Uebergabe uebergabe = new vierGewinntKI.VG_Uebergabe();
		
		aktionen = uebergabe.getAktionen_ID();
		 gui = new GUI_Agent(uebergabe.getAktionen_ID());
		leistungselement = new Leistungselement(aktionen);
		//lernelement = new Sarsa(0.2,0.2,aktionen);
		lernelement = new Sarsa_Lamda(0.2,0.2,0.2,0.2, aktionen);
		//lernelement = new Q_Learning_Off_Policy(0.2,0.2,0.2,0.2, aktionen);
		//lernelement = new Q_Learning(0.2,0.2, aktionen);
		problemgenerator = new Problemgenerator(aktionen, 20);
		//gui =new GUI_Agent(aktionen); 

		addBehaviour(new Kommunikationselement(this));
		super.setup();
	}
	
	class Kommunikationselement extends CyclicBehaviour  {
		private static final long serialVersionUID = 1L;
		  

		Kommunikationselement(Agent a) {

			super(a);
		}
		Object content;

		@Override
		  public void action() {
		
			//boolean first = true;
			block(500);
	         ACLMessage msg = receive();
	         if (msg == null) { block(); return; }
	         try {
	            Object content = msg.getContentObject();

	       
				content = msg.getContentObject();
				if (content instanceof A_Situation) {
					
					tmp_Situation= (A_Situation) content;
					System.out.println("Agent "+ getLocalName() + " hat Situation " + tmp_Situation.toString() + " mit der ID " + tmp_Situation.hashCode() + " erhalten " );
					get_Situation = true;
					leistungselement.setNeuste_Situation(tmp_Situation);
					leistungselement.neue_Situation(tmp_Situation);
					// System.out.println("Sit Beides empfangen bereit zum starten mit r:" + get_Reward + " Sit " + get_Situation);
					
					if(get_Reward && get_Episode){
						
						get_Reward = false;
						get_Situation = false;
				
					
						lernelement.aufruf(tmp_Situation, tmp_Reward, leistungselement, problemgenerator);
						
						sende_Aktion();
					}
				
	
				}
			
				else if (content instanceof Double) {
					tmp_Reward = (Double) content;
					System.out.println("Agent "+ getLocalName() + " hat Reward " + tmp_Reward  + " erhalten");
					get_Reward = true;
					
					// System.out.println("Dou Beides empfangen bereit zum starten mit r:" + get_Reward + " Sit " + get_Situation);
					if(get_Situation && get_Episode){
						//System.out.println("Beides empfangen bereit zum starten");
						get_Reward = false;
						get_Situation = false;
						lernelement.aufruf(tmp_Situation, tmp_Reward , leistungselement, problemgenerator);
						sende_Aktion();
					}
					
				}
				else if (content instanceof Integer) {
					Integer episode = (Integer) content;
					leistungselement.setAktuelle_Episode(episode);
					System.out.println("Agent "+ getLocalName() + " hat Episode " + episode + " erhalten");
					 get_Episode = true;
					// System.out.println("Int Beides empfangen bereit zum starten mit r:" + get_Reward + " Sit " + get_Situation);
					 if(get_Situation && get_Reward){
						
						get_Reward = false;
						get_Situation = false;
						get_Episode = false;
						lernelement.aufruf(tmp_Situation, tmp_Reward , leistungselement, problemgenerator);
					//	problemgenerator.start_Generator(leistungselement);
						sende_Aktion();
					}
					
				}
				else if(content instanceof Boolean){
					System.out.println("BOOLEAN");
					Boolean tmp = (Boolean) content;
					System.out.println("Bestätgiugn erhalten mit " + tmp);
					if(tmp){
						leistungselement.aktualisiere_Histroy();
						//lernelement.aufruf(tmp_Situation, tmp_Reward, leistungselement, problemgenerator);
						System.err.println("TRUE BEKOMMEN");
						lernelement.erste_Aktion = false;
						leistungselement.verbotene_Reset();
					
						sende_Flag();
					}
					else{
						System.err.println("Verbotene Aktion! Neue wählen" + leistungselement.getAktuelle_Aktion());
					
						lernelement.verbotene_Aktion(leistungselement);
						if(leistungselement.explorierend)
							problemgenerator.explorieren(leistungselement);
						else
							leistungselement.berechne_Neue_Aktion(tmp_Situation);
						sende_Aktion();
					}
				}
				gui.update_Werte(leistungselement.getWerte());
			
			} catch (NullPointerException e) {
				System.out.println("HIEr");
				System.out.println("Excpetion!");
				System.err.println(e);
			}catch (Exception e) {
				System.out.println("Excpetion!");
				System.err.println(e);
			}

		}
		public void sende_Aktion(){
		
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			//System.out.println("Sende Aktion "+ leistungselement.getAktuelle_Aktion().toString());
			A_Aktion aktion = aktionen.get(leistungselement.getAktuelle_Aktion().getId());
			try {
				msg.setContentObject((java.io.Serializable) aktion);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			msg.addReceiver(new AID("umwelt",   AID.ISLOCALNAME));
			send(msg);
		}
		public void sende_Flag(){
			System.out.println("Sende true");
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			//System.out.println("Sende Aktion "+ leistungselement.getAktuelle_Aktion().toString());
			Boolean flag = new Boolean(true);
			try {
				msg.setContentObject((java.io.Serializable) flag);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			msg.addReceiver(new AID("umwelt",   AID.ISLOCALNAME));
			send(msg);
		}
		
			


		
	};
}

