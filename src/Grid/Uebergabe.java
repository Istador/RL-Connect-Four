package Grid;

import java.util.HashMap;
import java.util.Set;

import Agent.A_Aktion;
import Agent.A_Situation;
import Agent.A_Uebergabe;


public class Uebergabe extends A_Uebergabe{
	//HashMap<String, A_Situation> situationen;
	Set<A_Aktion> aktionen_ID;
	HashMap<String, A_Aktion> aktionen = new HashMap<String, A_Aktion>();
	Set<String> agenten;
	public HashMap<String, A_Aktion> getAktionen() {
		return aktionen;
	}
	public void setAktionen(HashMap<String, A_Aktion> aktionen) {
		this.aktionen = aktionen;
	}
	public Set<A_Aktion> getAktionen_ID() {
		return aktionen_ID;
	}
	public void setAktionen_ID(Set<A_Aktion> aktionen_ID) {
		this.aktionen_ID = aktionen_ID;
	}
	/*
	public HashMap<String, A_Situation> getSituationen() {
		return  situationen;
	}
	public void setSituationen(HashMap<String, A_Situation> situationen) {
		this.situationen = situationen;
	}
	*/
	public Set<String> getAgenten() {
		return agenten;
	}
	public void setAgenten(Set<String> agenten) {
		this.agenten = agenten;
	}

	public Uebergabe() {
			this.aktionen_ID = new java.util.HashSet<A_Aktion>();
			//this.situationen = new HashMap<String, A_Situation>();
			this.agenten = new java.util.HashSet<String>();
			this.agenten.add("a1");
			this.situation = new Situation();
			this.aktionen_ID.add(new Hoch());
			this.aktionen_ID.add(new Runter());
			this.aktionen_ID.add(new Rechts());
			this.aktionen_ID.add(new Links());
	}
}
