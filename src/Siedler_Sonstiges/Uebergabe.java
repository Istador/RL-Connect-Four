package Siedler_Sonstiges;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import Sieder_Gebäude_P.*;
import Siedler_Aktionen.Baue;

import Agent.A_Aktion;
import Agent.A_Situation;
import Agent.A_Uebergabe;


public class Uebergabe extends A_Uebergabe{
	//HashMap<String, A_Situation> situationen = new HashMap<String, A_Situation>();
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
	/*public HashMap<String, A_Situation> getSituationen() {
		return  situationen;
	}
	public void setSituationen(HashMap<String, A_Situation> situationen) {
		this.situationen = situationen;
	}*/
	public Set<String> getAgenten() {
		return agenten;
	}
	public void setAgenten(Set<String> agenten) {
		this.agenten = agenten;
	}
	
	public Uebergabe() {
			System.out.println("UEBERGABE SIEDER");
			Ressourcen ressourcen = new Ressourcen();
			
			ArrayList<Gebäude> gebauede = new ArrayList<Gebäude>();
			//Ressis für Hozfäller
			HashMap<Ressource, Integer> res_H_Benötigt = new HashMap<Ressource, Integer>();
			res_H_Benötigt.put(ressourcen.getRessource("Holz"), 3);
			HashMap<Ressource, Integer> res_H_Produziert = new HashMap<Ressource, Integer>();
			res_H_Produziert.put(ressourcen.getRessource("Baumstamm"), 1);		
			HashMap<Ressource, Integer> res_H_zur_Prouktion = new HashMap<Ressource, Integer>();
			gebauede.add(new Holzfäller(res_H_Benötigt, res_H_Produziert, res_H_zur_Prouktion));
			
			//Ressis für Sägemühle
			HashMap<Ressource, Integer> res_Sägemühle_Benötigt = new HashMap<Ressource, Integer>();
			res_Sägemühle_Benötigt.put(ressourcen.getRessource("Holz"), 5);
			HashMap<Ressource, Integer> res_Sägemühle_Produziert = new HashMap<Ressource, Integer>();
			res_Sägemühle_Produziert.put(ressourcen.getRessource("Holz"), 1);		
			HashMap<Ressource, Integer> res_Sägemühle_zur_Prouktion = new HashMap<Ressource, Integer>();
			res_Sägemühle_zur_Prouktion.put(ressourcen.getRessource("Baumstamm"), 1);
			gebauede.add(new Sägemühle(res_Sägemühle_Benötigt, res_Sägemühle_Produziert, res_Sägemühle_zur_Prouktion));
			
			
			//Ressis für Farm
			HashMap<Ressource, Integer> res_Farm_Benötigt = new HashMap<Ressource, Integer>();
			res_Farm_Benötigt.put(ressourcen.getRessource("Holz"), 5);
			HashMap<Ressource, Integer> res_Farm_Produziert = new HashMap<Ressource, Integer>();
			res_Farm_Produziert.put(ressourcen.getRessource("Gras"), 4);		
			HashMap<Ressource, Integer> res_Farm_zur_Prouktion = new HashMap<Ressource, Integer>();
		
			gebauede.add(new Farm(res_Farm_Benötigt, res_Farm_Produziert, res_Farm_zur_Prouktion));
			
				//Ressis für Mühle
					HashMap<Ressource, Integer> res_Mühle_Benötigt = new HashMap<Ressource, Integer>();
			res_Mühle_Benötigt.put(ressourcen.getRessource("Holz"), 6);
			res_Mühle_Benötigt.put(ressourcen.getRessource("Stein"), 2);
			HashMap<Ressource, Integer> res_Mühle_Produziert = new HashMap<Ressource, Integer>();
			res_Mühle_Produziert.put(ressourcen.getRessource("Mehl"), 1);		
			HashMap<Ressource, Integer> res_Mühle_zur_Prouktion = new HashMap<Ressource, Integer>();
			res_Mühle_zur_Prouktion.put(ressourcen.getRessource("Gras"), 1);
			
			gebauede.add(new Mühle(res_Mühle_Benötigt, res_Mühle_Produziert, res_Mühle_zur_Prouktion));
				//Ressis für Bäckerrei
					HashMap<Ressource, Integer> res_Bäckerrei_Benötigt = new HashMap<Ressource, Integer>();
			res_Bäckerrei_Benötigt.put(ressourcen.getRessource("Holz"), 3);
			res_Bäckerrei_Benötigt.put(ressourcen.getRessource("Stein"), 4);
			HashMap<Ressource, Integer> res_Bäckerrei_Produziert = new HashMap<Ressource, Integer>();
			res_Bäckerrei_Produziert.put(ressourcen.getRessource("Brot"), 1);		
			HashMap<Ressource, Integer> res_Bäckerrei_zur_Prouktion = new HashMap<Ressource, Integer>();
			res_Farm_zur_Prouktion.put(ressourcen.getRessource("Mehl"), 1);
			
			gebauede.add(new Bäckerrei(res_Bäckerrei_Benötigt, res_Bäckerrei_Produziert, res_Bäckerrei_zur_Prouktion));
			
			//Ressis für Steinmetz
			HashMap<Ressource, Integer> res_Steinmetz_Benötigt = new HashMap<Ressource, Integer>();
			res_Steinmetz_Benötigt.put(ressourcen.getRessource("Holz"), 4);
			res_Steinmetz_Benötigt.put(ressourcen.getRessource("Stein"), 1);
			HashMap<Ressource, Integer> res_Steinmetz_Produziert = new HashMap<Ressource, Integer>();
			res_Steinmetz_Produziert.put(ressourcen.getRessource("Stein"), 1);		
			HashMap<Ressource, Integer> res_Steinmetz_zur_Prouktion = new HashMap<Ressource, Integer>();
			res_Farm_zur_Prouktion.put(ressourcen.getRessource("Brot"), 1);
			
			gebauede.add(new Steinmetz(res_Steinmetz_Benötigt, res_Steinmetz_Produziert, res_Steinmetz_zur_Prouktion));
		
			
			//Ressis für GoldMine
			HashMap<Ressource, Integer> res_GoldMine_Benötigt = new HashMap<Ressource, Integer>();
			res_GoldMine_Benötigt.put(ressourcen.getRessource("Holz"), 5);

			HashMap<Ressource, Integer> res_GoldMine_Produziert = new HashMap<Ressource, Integer>();
			res_GoldMine_Produziert.put(ressourcen.getRessource("Gold"), 1);		
			HashMap<Ressource, Integer> res_GoldMine_zur_Prouktion = new HashMap<Ressource, Integer>();
			res_Farm_zur_Prouktion.put(ressourcen.getRessource("Brot"), 1);
			
			gebauede.add(new GoldMine(res_GoldMine_Benötigt, res_GoldMine_Produziert, res_GoldMine_zur_Prouktion));
			
			//Ressis für EisenMine
			HashMap<Ressource, Integer> res_EisenMine_Benötigt = new HashMap<Ressource, Integer>();
			res_EisenMine_Benötigt.put(ressourcen.getRessource("Holz"), 6);

			HashMap<Ressource, Integer> res_EisenMine_Produziert = new HashMap<Ressource, Integer>();
			res_EisenMine_Produziert.put(ressourcen.getRessource("Eisenerz"), 1);		
			HashMap<Ressource, Integer> res_EisenMine_zur_Prouktion = new HashMap<Ressource, Integer>();
			res_Farm_zur_Prouktion.put(ressourcen.getRessource("Brot"), 1);
			
			gebauede.add(new EisenMine(res_EisenMine_Benötigt, res_EisenMine_Produziert, res_EisenMine_zur_Prouktion));
			
			//Ressis für Eisenschmiede
			HashMap<Ressource, Integer> res_Eisenschmiede_Benötigt = new HashMap<Ressource, Integer>();
			res_Eisenschmiede_Benötigt.put(ressourcen.getRessource("Holz"), 6);
			res_Eisenschmiede_Benötigt.put(ressourcen.getRessource("Stein"), 2);

			HashMap<Ressource, Integer> res_Eisenschmiede_Produziert = new HashMap<Ressource, Integer>();
			res_Eisenschmiede_Produziert.put(ressourcen.getRessource("Eisen"), 1);		
			HashMap<Ressource, Integer> res_Eisenschmiede_zur_Prouktion = new HashMap<Ressource, Integer>();
			res_Farm_zur_Prouktion.put(ressourcen.getRessource("Eisenerz"), 1);
			
			gebauede.add(new Eisenschmiede(res_Eisenschmiede_Benötigt, res_Eisenschmiede_Produziert, res_Eisenschmiede_zur_Prouktion));
			this.aktionen_ID = new java.util.HashSet<A_Aktion>();
			
			for(int i=0;i<gebauede.size();i++)
				aktionen_ID.add(new Baue(gebauede.get(i)));
			
			aktionen_ID.add(new Baue(null));
			
			
			try
			 {
				this.agenten = new java.util.HashSet<String>();
				this.agenten.add("a1");
				this.agenten.add("a2");
				this.situation = new Situation();
			 }
			catch (Exception e)
			 {
			   System.out.println(e);
			 }
		
	

	}
	
}
