package Siedler_Sonstiges;

import java.util.ArrayList;
import java.util.HashMap;

import Sieder_Gebäude_P.Sägemühle;
import Siedler_Aktionen.Baue;

import Agent.A_Aktion;
import Agent.A_Situation;
import Agent.A_Umwelt;
import Agent.A_Umwelt.Kommunikation;
import Agent.A_Umwelt.Sende_Episode;
import Agent.A_Umwelt.Sende_Reward;
import Agent.A_Umwelt.Sende_Situation;

import jade.core.Agent;
import jade.core.behaviours.SequentialBehaviour;

public class K_Umwelt extends A_Umwelt{

	/**
	 * 
	 */
	@Override
	protected void setup() {
		// TODO Auto-generated method stub
		super.setup();
		System.out.println("STarte");

			SequentialBehaviour seq = new SequentialBehaviour();
			//ParallelBehaviour par = new ParallelBehaviour();
			//par.addSubBehaviour(new Kommunikation(null));
		//	par.addSubBehaviour(seq);
			for(int i=0;i<400;i++){
				seq.addSubBehaviour(new Sende_Situation("a1"));
				seq.addSubBehaviour(new Sende_Reward("a1"));
				seq.addSubBehaviour(new Sende_Episode("a1"));;
				seq.addSubBehaviour(new Kommunikation(null));
			/*	seq.addSubBehaviour(new Sende_Situation("a2"));
				seq.addSubBehaviour(new Sende_Reward("a2"));
				seq.addSubBehaviour(new Sende_Episode("a2"));;
				seq.addSubBehaviour(new Kommunikation(null));*/
			
			}
	}
	public K_Umwelt() {
		super(new Uebergabe());
		System.out.println("Konstruktor");
	}
	

	@Override
	public void aktualisiere_GUI() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean aktion_Moeglich(A_Aktion a_Aktion, String agent) {
		
		Situation situation = (Situation) super.getSituationen().get(agent);

		Baue tmp = (Baue) a_Aktion;
		if(tmp.getGebäude() == null)
			return true;
		HashMap<Ressource, Integer> benötigte_Ressourcen = tmp.getBenötigte_Ressourcen();
		for(Ressource res :benötigte_Ressourcen.keySet()){
			if(benötigte_Ressourcen.get(res)>situation.getResourcen().get(res))
				return false;
		}
		return true;
	}

	@Override
	public A_Situation gib_Situation_Agent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double berechne_Reward(String agent) {
	//	if(true)
		//	return 100.0;
		HashMap<String, A_Situation> situationen =  super.getSituationen();
		Situation situation = (Situation) situationen.get(agent);
		if(situation.getResourcen().get(new Ressource("Eisenerz")) >= 1 || situation.getResourcen().get(new Ressource("Gold")) >= 1 ){
			super.neue_Episode();
			return 100.0;
		}
			
		else
			return 0.0;
	}
	public void set_Inital(){
		ArrayList<String> agenten = super.getAgenten();
		System.err.println("GUI");
		for(int i=0; i< agenten.size();i++)
			super.getSituationen().put(agenten.get(i), new Situation(agenten.get(i) ));
		//for(String agent_S: agenten)
			//aktualisiere_GUI(agent_S);
		System.err.println("RESET");
	}

}
