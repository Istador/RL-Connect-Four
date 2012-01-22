package Grid;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.List;
import java.util.Map;
import java.util.Set;

import Grid.Situation;

import Agent.A_Aktion;
import Agent.A_Situation;
import Agent.A_Uebergabe;
import Agent.A_Umwelt;

import Agent.A_Umwelt.Kommunikation;
import Agent.A_Umwelt.Sende_Episode;
import Agent.A_Umwelt.Sende_Reward;
import Agent.A_Umwelt.Sende_Situation;

public class Umwelt extends A_Umwelt {
	GUI gui;
	public int getMax_x() {
		return max_x;
	}
	public void setMax_x(int max_x) {
		this.max_x = max_x;
	}
	public int getMax_y() {
		return max_y;
	}
	public void setMax_y(int max_y) {
		this.max_y = max_y;
	}

	int max_x = 3;
	int max_y = 3;
	class ausfuerhung extends SimpleBehaviour{
	
		boolean done = false;
		A_Umwelt umwelt;
		public ausfuerhung(Umwelt umwelt) {
				this.umwelt = umwelt;
		}
		@Override
		public void action() {
			int f;
			SequentialBehaviour seq = new SequentialBehaviour();
			//ParallelBehaviour par = new ParallelBehaviour();
			//par.addSubBehaviour(new Kommunikation(null));
		//	par.addSubBehaviour(seq);
			for(int i=0;i<50;i++){
				System.out.println("Durchlauf mit " + i);
				seq.addSubBehaviour(new Sende_Reward("a1"));
				aktualisiere_GUI();
				seq.addSubBehaviour(new Sende_Situation("a1"));
				
				seq.addSubBehaviour(new Sende_Episode("a1"));;
				seq.addSubBehaviour(new Kommunikation(null));
				aktualisiere_GUI();
			/*	seq.addSubBehaviour(new Sende_Situation("a2"));
				seq.addSubBehaviour(new Sende_Reward("a2"));
				seq.addSubBehaviour(new Sende_Episode("a2"));;
				seq.addSubBehaviour(new Kommunikation(null));*/
			
			}
			done = true;
		}

		@Override
		public boolean done() {
			// TODO Auto-generated method stub
			return done;
		}
		
	}
	@Override
	protected void setup() {
		// TODO Auto-generated method stub
		super.setup();
		addBehaviour(new ausfuerhung(this));
	

	System.out.println("Umwelt gestartet");
	
	



	
		
		/*
		
		System.out.println("Sende Nachricht 1");
		A_Situation sit0 = new A_Situation();
		sit0.setId(0);
		msg1.setContentObject((java.io.Serializable) sit0);
		msg1.addReceiver(new AID("a1", AID.ISLOCALNAME));
		send(msg1);
		
		System.out.println("Sende Nachricht 2");
		Double rew0 = new Double(0.0);
		msg2.setContentObject((java.io.Serializable) rew0);
		msg2.addReceiver(new AID("a1", AID.ISLOCALNAME));
		send(msg2);
		
		System.out.println("Sende Nachricht 1");
		A_Situation sit1 = new A_Situation();
		sit1.setId(1);
		msg3.setContentObject((java.io.Serializable) sit1);
		msg3.addReceiver(new AID("a1", AID.ISLOCALNAME));
		send(msg3);
		
		System.out.println("Sende Nachricht 2");
		Double rew1 = new Double(0.0);
		msg4.setContentObject((java.io.Serializable) rew1);
		msg4.addReceiver(new AID("a1", AID.ISLOCALNAME));
		send(msg4);
		
		System.out.println("Sende Nachricht 1");
		A_Situation sit2 = new A_Situation();
		sit2.setId(2);
		msg5.setContentObject((java.io.Serializable) sit2);
		msg5.addReceiver(new AID("a1", AID.ISLOCALNAME));
		send(msg5);
		
		
		
		System.out.println("Sende Nachricht 2");
		Double rew2 = new Double(100.0);
		msg6.setContentObject((java.io.Serializable) rew2);
		msg6.addReceiver(new AID("a1", AID.ISLOCALNAME));
		send(msg6);*/
		
		

	
}
	public Umwelt() {
		super(new Uebergabe());
		gui = new GUI(max_x, max_y);
		System.out.println("Konstruktor");
	}



//	@Override
	class berechne_Reward extends SimpleBehaviour{

	@Override
	public void action() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return false;
	}
		
	}
	
	boolean episodevorbei = false;
	public Double berechne_Reward(String agent) {
		Situation sit = (Situation) super.getSituation();
		System.out.println("Vergleich mit : " + sit);
		if(sit.getX() == 2 && sit.getY() == 2 || sit.getX() == 1 && sit.getY() == 2 || sit.getX() == 3 && sit.getY() == 2){
			System.err.println("REWARD");
			set_Inital();
			episodevorbei = true;
			super.neue_Episode();
			return 100.0;
			
			
		}
		else{
			episodevorbei = false;
			return 0.0;
		}
	}
	@Override
	public A_Situation gib_Situation_Agent() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean aktion_Moeglich(A_Aktion aktion_tmp, String agent) {
		
		Situation sit = (Situation) aktion_tmp.fuehre_Aus(super.getSituation(), agent);
		if(sit.equals(new Situation(1,1))){
			System.err.println("GEBE EIN FALSE ZURÜCK");
			return false;
		}
		return true;
		
		/*
		Situation alt = (Situation) super.getSituationen().get(agent);
		super.getSituationen().get(agent);
		Situation situation = Situation.copy(alt);
		Map<String, A_Situation> map = new java.util.HashMap<String, A_Situation>();
		map.put(agent, situation);
		map = aktion_tmp.fuehre_Aus(map, agent);
		Situation tmp2 = (Situation) map.get("a1");
		
			if(tmp2.equals(new Situation(1,1) )){
				System.err.println("GEBE EIN FALSE ZURÜCK");
			return false;
		}
		*/
		//	if(super.getSituationen().get(agent).equals(new Situation(0,0)))
			//	return false;
		
	/*	else if(aktion_tmp instanceof Hoch){
			//System.out.println("l");
	
			if(super.getSituationen().get(agent).equals(new Situation(0,0)))
				return false;
		}*/
		
	
		
		//return true;
	}

	/*
	public void aktualisiere_GUI(String agent) {
		Situation sit = (Situation) super.getSituation();
		gui.aktualisiere(sit.getX(), sit.getY());
		
	}
	*/
	
	@Override
	public void aktualisiere_GUI() {
		Situation sit = (Situation) super.getSituation();
		gui.aktualisiere(sit.getX(), sit.getY());
		
	}
	public void set_Inital(){
		//Set<String> agenten = super.getAgenten();
		System.err.println("GUI");
		setSituation(new Situation());	
			//super.getSituationen().put(a, new Situation(a));
		//for(String agent_S: agenten)
			//aktualisiere_GUI(agent_S);
		System.err.println("RESET");
		//aktualisiere_GUI("a1");
		aktualisiere_GUI();
	}
	
	@Override
	public boolean istEpisodeVorbei() {
		return episodevorbei;
	}

}


