package TicTacToe;



import java.util.ArrayList;
import java.util.HashMap;

import Agent.A_Aktion;
import Agent.A_Situation;
import Agent.A_Uebergabe;
import Agent.Leistungselement;

public class Uebergabe extends A_Uebergabe{
	HashMap<String, A_Situation> situationen;
	ArrayList<A_Aktion> aktionen_ID;
	HashMap<String, A_Aktion> aktionen = new HashMap<String, A_Aktion>();
	public HashMap<String, A_Aktion> getAktionen() {
		return aktionen;
	}
	public void setAktionen(HashMap<String, A_Aktion> aktionen) {
		this.aktionen = aktionen;
	}
	public ArrayList<A_Aktion> getAktionen_ID() {
		return aktionen_ID;
	}
	public void setAktionen_ID(ArrayList<A_Aktion> aktionen_ID) {
		this.aktionen_ID = aktionen_ID;
	}
	public HashMap<String, A_Situation> getSituationen() {
		return  situationen;
	}
	public void setSituationen(HashMap<String, A_Situation> situationen) {
		this.situationen = situationen;
	}
	public ArrayList<String> getAgenten() {
		return agenten;
	}
	public void setAgenten(ArrayList<String> agenten) {
		this.agenten = agenten;
	}
	ArrayList<String> agenten;
	public Uebergabe() {
			this.aktionen_ID = new ArrayList<A_Aktion>();
			this.situationen = new HashMap<String, A_Situation>();
			int id =0;
			for(int i=0;i<3;i++){
				
				for(int j=0;j<3;j++){
					
					aktionen_ID.add(new fuege_Ein(id, i, j));
				//	System.out.println("Füge ein  i:" + i + " j: " + j + " mit der id: " + id);
					id++;
				}
					
			}
			this.agenten = new ArrayList<String>();
			this.agenten.add("a1");
			this.agenten.add("a2");
			this.situationen.put("a1", new Situation("a1"));
			this.situationen.put("a2", new Situation("a2"));

	}
}
