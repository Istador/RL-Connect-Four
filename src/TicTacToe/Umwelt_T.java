package TicTacToe;

import java.util.ArrayList;
import java.util.HashMap;

import TicTacToe.Situation;

import jade.core.AgentState;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.MessageTemplate;
import Agent.A_Aktion;
import Agent.A_Situation;
import Agent.A_Umwelt;

import Agent.A_Umwelt.Sende_Situation;

import Agent.A_Umwelt;
public class Umwelt_T extends A_Umwelt {

	TicTacToe.GUI gui;

	public Umwelt_T() {
		super(new Uebergabe());
		gui = new TicTacToe.GUI(new Uebergabe().getAktionen_ID());
		System.out.println("Konstruktor");

		
	//	addBehaviour(new Kommunikation(this));
		
	}
	@Override
	protected void setup() {
		// TODO Auto-generated method stub
		super.setup();
		
		SequentialBehaviour seq = new SequentialBehaviour();
		//ParallelBehaviour par = new ParallelBehaviour();
		//par.addSubBehaviour(new Kommunikation(null));
	//	par.addSubBehaviour(seq);
		for(int i=0;;i++){
			seq.addSubBehaviour(new Sende_Reward("a1"));
			seq.addSubBehaviour(new Sende_Situation("a1"));
		
			seq.addSubBehaviour(new Sende_Episode("a1"));;
			seq.addSubBehaviour(new Kommunikation(null));
		aktualisiere_GUI();
				seq.addSubBehaviour(new Sende_Situation("a2"));
		seq.addSubBehaviour(new Sende_Reward("a2"));
			seq.addSubBehaviour(new Sende_Episode("a2"));;
			seq.addSubBehaviour(new Kommunikation(null));
			aktualisiere_GUI();
		}

	/*
	
	      addBehaviour( seq );

		ParallelBehaviour behaviour = new ParallelBehaviour(this,ParallelBehaviour.WHEN_ALL);
	
		SequentialBehaviour par = new SequentialBehaviour();
		par.addSubBehaviour(new Sende_Situation("a1"));
		par.addSubBehaviour(new Sende_Reward("a1"));
		par.addSubBehaviour(new Sende_Episode("a1"));
		par.addSubBehaviour(new ausfuehren("a1"));
		behaviour.addSubBehaviour(par);
		behaviour.addSubBehaviour(new Kommunikation(this, 2));
		/*ParallelBehaviour p = new ParallelBehaviour();
		p.addSubBehaviour(new Kommunikation(this,1));


		SequentialBehaviour par = new SequentialBehaviour();
		par.addSubBehaviour(new Sende_Situation("a1"));
		par.addSubBehaviour(new Sende_Reward("a1"));
		par.addSubBehaviour(new Sende_Episode("a1"));
		par.addSubBehaviour(new ausfuehren("a1"));
		p.addSubBehaviour(par);
		this.addBehaviour(p);
	

*/
	}
	

	class ausfuerhung_a1 extends Behaviour{
		
		boolean done = false;
		A_Umwelt umwelt;
		public ausfuerhung_a1(Umwelt_T umwelt) {
				this.umwelt = umwelt;
			//	action();
		}
		@Override
		public void action() {
	
			block();
			System.out.println("Aktion von a1");
			int f;

			umwelt.sende_Episode("a1");			

			umwelt.sende_Situation("a1");
			umwelt.sende_Reward("a1");

	

						
			
			done =true;
		
	

		}
		
		@Override
		public boolean done() {
			// TODO Auto-generated method stub
			return done;
		}
		
	




}
class ausfuerhung_a2 extends Behaviour{
		
		boolean done = false;
		A_Umwelt umwelt;
		public ausfuerhung_a2(Umwelt_T umwelt) {
				this.umwelt = umwelt;
				action();
		}
		@Override
		public void action() {

			System.out.println("Warte");
		
			
				System.out.println("Aktion");
				int f;
		
				umwelt.sende_Episode("a2");
			
				umwelt.sende_Situation("a2");
			
		
				umwelt.sende_Reward("a2");
	
		
	
	
		
		
			done =true;
		}

		@Override
		public boolean done() {
			// TODO Auto-generated method stub
			return done;
		}
		
	




}

	@Override
	public void aktualisiere_GUI() {
		System.err.println("GUI");
		Situation sit1 = (Situation) super.getSituationen().get("a1");
		Situation sit2 = (Situation) super.getSituationen().get("a2");
		gui.aktualisiere(sit1.feld, sit2.feld);
		
	}

	@Override
	public boolean aktion_Moeglich(A_Aktion a_Aktion, String agent) {
		Situation sit = (Situation) super.getSituationen().get(agent);
		fuege_Ein aktion = (fuege_Ein) a_Aktion;
		if(sit.feld[aktion.getVertikal() * 3 + aktion.getHorizontal()].isGeaendert())
			return false;
		else
			return true;
	}

	@Override
	public A_Situation gib_Situation_Agent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double berechne_Reward(String agent) {
		System.err.println("BErechne reward");
		Situation tmp = (Situation) super.getSituationen().get(agent);
		double rew =0.0;
		boolean pr = false;
		
	/*	for(int i=0;i<8;i=i++){
			if(!tmp.feld[i].isGeaendert())
				if(i==8)
					super.neue_Episode();
			return 0.0;
		}*/
		if(agent.equals("a1")){
			for(int i=0;i<8;i=i+3){
				if(tmp.feld[i].isP1()){
					if(tmp.feld[i+1].isP1()){
						if(tmp.feld[i+2].isP1()){
							super.neue_Episode();
				
							System.err.println("Reward beim ersten für " + agent);
							return 100.0;
						}
					}
				}
			}
			for(int i=0;i<3;i++){
				if(tmp.feld[i].isP1()){
					int f=i;
					if(tmp.feld[f+3].isP1()){
						if(tmp.feld[f+6].isP1()){
							super.neue_Episode();
							
							System.err.println("Reward beim zweiten für " + agent + " mit i:" + i);
							return 100.0;
						}
					}
				}
			}
			if(tmp.feld[0].isP1() && tmp.feld[4].isP1() && tmp.feld[8].isP1()){
				super.neue_Episode();
				System.err.println("Reward beim dritten für " + agent);
				
				return 100.0;
			}
				
			if(tmp.feld[2].isP1() && tmp.feld[4].isP1() && tmp.feld[6].isP1()){
				super.neue_Episode();
				System.err.println("Reward beim vierten für " + agent);
			
				return 100.0;
			}
			if((tmp.feld[0].isP1() ||tmp.feld[0].isP2()) && (tmp.feld[1].isP1() ||tmp.feld[1].isP2()) &&(tmp.feld[2].isP1() ||tmp.feld[2].isP2()) &&(tmp.feld[3].isP1() ||tmp.feld[3].isP2()) &&(tmp.feld[4].isP1() ||tmp.feld[4].isP2()) &&(tmp.feld[5].isP1() ||tmp.feld[5].isP2()) && (tmp.feld[6].isP1() ||tmp.feld[6].isP2()) && (tmp.feld[7].isP1() ||tmp.feld[7].isP2())&& (tmp.feld[8].isP1() ||tmp.feld[8].isP2())){
				System.err.println("Reward beim fünften für " + agent);
				super.neue_Episode();
				return 0.0;
			}
				
				
		}
		if(agent.equals("a2")){
			for(int i=0;i<8;i=i+3){
				if(tmp.feld[i].isP2()){
					if(tmp.feld[i+1].isP2()){
						if(tmp.feld[i+2].isP2()){
							super.neue_Episode();
				
							System.err.println("Reward beim ersten  für " + agent);
							return 100.0;
						}
					}
				}
			}
			//vertikal
			for(int i=0;i<3;i++){
				if(tmp.feld[i].isP2()){
					int f=i;
					if(tmp.feld[f+3].isP2()){
						if(tmp.feld[f+6].isP2()){
							super.neue_Episode();
							
							System.err.println("Reward beim zweiten für " + agent);
							return 100.0;
						}
					}
				}
			}
			if(tmp.feld[0].isP2() && tmp.feld[4].isP2() && tmp.feld[8].isP2()){
				super.neue_Episode();
				System.err.println("Reward beim dritten für " + agent);

				return 100.0;
			}
				
			if(tmp.feld[2].isP2() && tmp.feld[4].isP2() && tmp.feld[6].isP2()){
				super.neue_Episode();
				System.err.println("Reward beim vierten für " + agent);
			
				return 100.0;
			}
			if((tmp.feld[0].isP1() ||tmp.feld[0].isP2()) && (tmp.feld[1].isP1() ||tmp.feld[1].isP2()) &&(tmp.feld[2].isP1() ||tmp.feld[2].isP2()) &&(tmp.feld[3].isP1() ||tmp.feld[3].isP2()) &&(tmp.feld[4].isP1() ||tmp.feld[4].isP2()) &&(tmp.feld[5].isP1() ||tmp.feld[5].isP2()) && (tmp.feld[6].isP1() ||tmp.feld[6].isP2()) && (tmp.feld[7].isP1() ||tmp.feld[7].isP2())&& (tmp.feld[8].isP1() ||tmp.feld[8].isP2())){
				System.err.println("Reward beim fünften für " + agent);
				super.neue_Episode();
			}
			
			
				
		}
	
	
		return 0.0;
	}
	@Override
	public void set_Inital(){
		ArrayList<String> agenten = super.getAgenten();
		
		for(int i=0; i< agenten.size();i++)
			super.getSituationen().put(agenten.get(i), new Situation(agenten.get(i) ));
		//for(String agent_S: agenten)
			//aktualisiere_GUI(agent_S);
		System.err.println("RESET");
	}
}
