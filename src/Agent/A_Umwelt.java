package Agent;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class A_Umwelt extends Agent {
	private static final long serialVersionUID = 1L;
	public ArrayList<String> getAgenten() {
		return agenten;
	}
	public void setAgenten(ArrayList<String> agenten) {
		this.agenten = agenten;
	}
	boolean b = false;

	int episode =0;
	HashMap<String, Boolean> reward_Situation_Gesendet = new HashMap<String, Boolean>();

	HashMap<String , Boolean> aktion_Ausgeführt = new HashMap<String, Boolean>();
	public abstract void aktualisiere_GUI();
	public  abstract boolean aktion_Moeglich (A_Aktion a_Aktion, String agent);
	public HashMap<String, Boolean> getReward_Situation_Gesendet() {
		return reward_Situation_Gesendet;
	}

	public void setReward_Situation_Gesendet(
			HashMap<String, Boolean> reward_Situation_Gesendet) {
		this.reward_Situation_Gesendet = reward_Situation_Gesendet;
	}
	ArrayList<String> agenten;

	HashMap<String, A_Situation> situationen;
	HashMap<String, A_Aktion> aktionen_Agenten;
	ArrayList<A_Aktion> aktionen;
	public HashMap<String, A_Situation> getSituationen() {
		// TODO Auto-generated method stub
		return situationen;
	}
	abstract public void 	set_Inital();
	public void setSituationen(HashMap<String, A_Situation> situationen) {
		this.situationen = situationen;
	}
	public abstract A_Situation gib_Situation_Agent();
	public abstract Double berechne_Reward(String agent);
	public void sende(String agent, Double reward){
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		//Double rew0 = new Double(0.0);
		try {
			msg.setContentObject((java.io.Serializable) reward);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("Sende an " + agent + " mit rew " + reward);
		msg.addReceiver(new AID("a1", AID.ISLOCALNAME));
		send(msg);
	}
	public void neue_Episode(){
		set_Inital();
		System.err.println("NEUE EPISODE");
		episode++;
	}
	public void sende_Situation(String agent){
		
		addBehaviour(new Sende_Situation(agent));
		
	}
	public class Sende_Situation extends SimpleBehaviour{
		private static final long serialVersionUID = 1L;
		String agent_T;
		A_Situation situation;
		boolean done = false;
		boolean ausfuehrung =false;
		public Sende_Situation(String agent) {
			


			this.agent_T = agent;
			action();
			
		}
	
		@Override
		public void action() {
		
			situation = situationen.get(agent_T);
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		

		
			{	if(!ausfuehrung){
				
					ausfuehrung = true;
					situation.setEpisode(episode);
					try {
						msg.setContentObject((java.io.Serializable) situation);
					} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				msg.addReceiver(new AID(agent_T, AID.ISLOCALNAME));
		
				send(msg);
			
				done = true;
	
			
			
			}}
		}

		@Override
		public boolean done() {
			// TODO Auto-generated method stub
			return done;
		}
		
	}
	public void sende_Reward(String agent){
		
		addBehaviour(new Sende_Reward(agent));
		
	}
	public class Sende_Reward extends SimpleBehaviour{
		private static final long serialVersionUID = 1L;
		String agent_T;
		boolean ausfuehrung =false;
		boolean done = false;
		public Sende_Reward(String agent_T) {
			this.agent_T = agent_T;
			action();

			
		}

		
		
		@Override
		public void action() {

	
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		
			Double reward = berechne_Reward(agent_T);
			
			try {
				msg.setContentObject((java.io.Serializable) reward);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			msg.addReceiver(new AID(agent_T, AID.ISLOCALNAME));
			send(msg);

			
			done = true;
		
		}
		

		@Override
		public boolean done() {
			// TODO Auto-generated method stub
			return done;
		}
		
	}
	public void sende_Episode(String agent){
		
		addBehaviour(new Sende_Episode(agent));
		
	}
	public class Sende_Episode extends SimpleBehaviour{
		private static final long serialVersionUID = 1L;
		String agent_T;
		boolean ausfuehrung =false;
		boolean done = false;
		public Sende_Episode(String agent_T) {
			this.agent_T = agent_T;
			
	
				action();
	
		}

		@Override
		public void action() {
		
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		//	System.out.println("Vor der Berechnung!");
			Integer ep = episode;
			try {
				msg.setContentObject((java.io.Serializable) ep);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			msg.addReceiver(new AID(agent_T, AID.ISLOCALNAME));
			send(msg);
			
			try {
				System.out.println("Sende Episode mit " + msg.getContentObject());
			} catch (UnreadableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			done = true;
	
		}
		

		@Override
		public boolean done() {
			// TODO Auto-generated method stub
			return done;
		}
		
	}
	public A_Umwelt(A_Uebergabe uebergabe) {

		this.agenten = uebergabe.getAgenten();
		this.situationen = uebergabe.getSituationen();
		this.aktionen = uebergabe.getAktionen_ID();
		this.aktionen_Agenten = uebergabe.getAktionen();
		for(int i=0; i< agenten.size();i++){

			reward_Situation_Gesendet.put(agenten.get(i), false);
	
		}
		
	}
		@Override
	protected void setup() {

		super.setup();
		
	}

	public class Kommunikation extends SimpleBehaviour{
		private static final long serialVersionUID = 1L;
		boolean done=false;
		public Kommunikation(Agent a) {
			
			super(a);
			
			while(!done)
			action();
			// TODO Auto-generated constructor stub
		}
	
		@Override
		public boolean done() {
		
			return done;
		}
		@Override
		public void action() {

			  ACLMessage msg = receive();
			  
		         if (msg == null) { block(); return; }
				    Object content;

					try {
				
					
						A_Aktion aktion;
					
						content = msg.getContentObject();
						if(content instanceof A_Aktion){
						aktion = (A_Aktion) content;
						System.out.println("ERHALTEN von " + aktion.toString());
						String agent = msg.getSender().getLocalName();
				
						for(int i =0;i < aktionen.size();i++){
							if(aktion.getId() == aktionen.get(i).getId()){
								if(!aktion_Moeglich(aktionen.get(i), agent)){
									System.err.println("AKTON NICHT MÖGLICH");
									 ACLMessage reply = msg.createReply();
					                 reply.setPerformative( ACLMessage.INFORM );
					                    reply.setContentObject((java.io.Serializable) new Boolean(false));
					                    send(reply);
								}else{
								
								aktionen_Agenten.put(agent, aktionen.get(i));
					
								 ACLMessage reply = msg.createReply();
				                 reply.setPerformative( ACLMessage.INFORM );
				                    reply.setContentObject((java.io.Serializable) new Boolean(true));
				                    send(reply);
				                    b=true;
				                    while(b){
				                    	  ACLMessage msg2 = receive();
				                    
				         		         if (msg2 != null) { 
			
				         		    	Object  content2 = msg2.getContentObject();
				         		         if(content2 instanceof Boolean){
				         		        	 b=false;
				         		         }}
				          
				                    }
							
							
								
								situationen = aktion.fuehre_Aus(situationen, agent);
								done = true;
								}
							}
						}
						}
					
					
					} catch (UnreadableException e1) {
						// TODO Auto-generated catch block
					//	System.out.println("Excpetion!");
						e1.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					
		
					
				
					
					 
			
			
		};
	/*	public void action() {
			System.out.println("Kommunikationselement fragt ab!");
			  ACLMessage msg = receive();
			  
		         if (msg == null) { block(); return; }
				    Object content;
				   // System.out.println("Umelt hat Aktion");
					try {
						System.out.println("ERHALTEN");
					
						A_Aktion aktion;
						content = msg.getContentObject();
						aktion = (A_Aktion) content;
						String agent = msg.getSender().getLocalName();
						for(int i =0;i < aktionen.size();i++){
							if(aktion.getId() == aktionen.get(i).getId()){
								if(!aktion_Moeglich(aktionen.get(i), agent)){
									 ACLMessage reply = msg.createReply();
					                 reply.setPerformative( ACLMessage.INFORM );
					                    reply.setContentObject((java.io.Serializable) new Boolean(false));
					                    send(reply);
								}else{
								aktionen_Agenten.put(agent, aktionen.get(i));
								System.out.println("Empfange Aktion " + agent + " Aktion " + aktionen.get(i) + " ein");
								reward_Situation_Gesendet.put(agent, true);
								aktionen_B.put(agent, true);
								State state = agenten_State.get(agent);
								state.isAktion_Erhalten();
								agenten_State.put(agent, state);
								
								}
							}
						
						}
					
					
					} catch (UnreadableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					block();		
		
					
				
					
					 
			
		}*/
		
	}

	}