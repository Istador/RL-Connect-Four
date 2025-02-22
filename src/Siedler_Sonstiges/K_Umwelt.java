package Siedler_Sonstiges;

import java.util.List;
import java.util.Map;
import java.util.Set;

import Sieder_Geb�ude_P.S�gem�hle;
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
		
		Situation situation = (Situation) super.getSituation();

		Baue tmp = (Baue) a_Aktion;
		if(tmp.getGeb�ude() == null)
			return true;
		Map<Ressource, Integer> ben�tigte_Ressourcen = tmp.getBen�tigte_Ressourcen();
		for(Ressource res :ben�tigte_Ressourcen.keySet()){
			if(ben�tigte_Ressourcen.get(res)>situation.getResourcen().get(res))
				return false;
		}
		return true;
	}

	@Override
	public A_Situation gib_Situation_Agent() {
		// TODO Auto-generated method stub
		return null;
	}

	boolean episodevorbei = false;
	
	@Override
	public Double berechne_Reward(String agent) {
	//	if(true)
		//	return 100.0;
		Situation situation = (Situation) super.getSituation();
		if(situation.getResourcen().get(new Ressource("Eisenerz")) >= 1 || situation.getResourcen().get(new Ressource("Gold")) >= 1 ){
			//super.neue_Episode();
			episodevorbei = true;
			return 100.0;
		}
			
		else{
			episodevorbei = false;
			return 0.0;
		}
	}
	public void set_Inital(){
		//Set<String> agenten = super.getAgenten();
		System.err.println("GUI");
		super.setSituation(new Situation());
		//for(String agent_S: agenten)
			//aktualisiere_GUI(agent_S);
		System.err.println("RESET");
	}
	@Override
	public boolean istEpisodeVorbei() {
		return episodevorbei;
	}

}
