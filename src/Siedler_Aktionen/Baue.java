package Siedler_Aktionen;

import jade.util.leap.Serializable;

import java.util.ArrayList;
import java.util.HashMap;

import Sieder_Gebäude_P.Bäckerrei;
import Sieder_Gebäude_P.EisenMine;
import Sieder_Gebäude_P.Eisenschmiede;
import Sieder_Gebäude_P.Fabrik;
import Sieder_Gebäude_P.Farm;
import Sieder_Gebäude_P.GoldMine;
import Sieder_Gebäude_P.Holzfäller;
import Sieder_Gebäude_P.Mühle;
import Sieder_Gebäude_P.Steinmetz;
import Sieder_Gebäude_P.Sägemühle;
import Siedler_Sonstiges.Gebäude;
import Siedler_Sonstiges.Ressource;
import Siedler_Sonstiges.Situation;

import Agent.A_Aktion;
import Agent.A_Situation;

public class Baue extends A_Aktion implements Serializable{
	public Gebäude getGebäude() {
		return gebäude;
	}




	public void setGebäude(Gebäude gebäude) {
		this.gebäude = gebäude;
	}




	Gebäude gebäude;
	
	HashMap<Ressource, Integer> benötigte_Ressourcen = new HashMap<Ressource, Integer>();
	public Baue(int i, Gebäude gebäude) {
		super(i);
		this.gebäude = gebäude;
		if(gebäude!=null)
		this.benötigte_Ressourcen = gebäude.getBenötigte_Ressourcen();
	
	//	System.out.println("Baut ein " + gebäude);
	}



	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		if(gebäude == null)
			return "Baue nichts";
		return "BAUE " + gebäude;
	}
	public Situation produziere(Situation situation){

		ArrayList<Gebäude> gebauede = (ArrayList<Gebäude>) situation.getBuilding();
		for(int i=0;i<gebauede.size();i++){
			
			HashMap<Ressource, Integer> erhaltene_Ressourcen = gebauede.get(i).getProduzierte_Ressourcen();
			HashMap<Ressource, Integer> benötigt = gebauede.get(i).getBenötigt_zumproduzieren_Ressourcen();
			//Produziere wenn man nix braucht
			if(benötigt.keySet().isEmpty()){
				for(Ressource res : erhaltene_Ressourcen.keySet())
				try {
					situation.getResourcen().put(res, situation.getResourcen().get(res) + erhaltene_Ressourcen.get(res));
				} catch (Exception e) {
					System.err.println(e);
				}
					
			}else{
				boolean erhalte = true;
				for(Ressource res : benötigt.keySet()){
					if(situation.getResourcen().get(res) < benötigt.get(res))
						erhalte = false;
				}
				if(erhalte){
					for(Ressource res : erhaltene_Ressourcen.keySet()){
					//	System.err.println("Erhalte " + erhaltene_Ressourcen.get(res) + " an " + res + " von " + gebauede.get(i).toString()) ;
						situation.getResourcen().put(res, situation.getResourcen().get(res) + erhaltene_Ressourcen.get(res));
						
					}
					for(Ressource res : benötigt.keySet()){
						//System.err.println("Verliere dafür " + benötigt.get(res) + " an " + res + " von " + gebauede.get(i).toString()) ;
						situation.getResourcen().put(res, situation.getResourcen().get(res) - benötigt.get(res));
						
					}
						
				}
			}
			
			
			
		}
		return situation;
	}
	@Override
	public HashMap<String, A_Situation> fuehre_Aus(HashMap<String, A_Situation> situation, String agent) {
		Situation sit = (Situation) situation.get(agent);
		this.produziere(sit);
		if(gebäude == null)
			return situation;
		System.out.println("Ausführen vom bau von " + gebäude);
	
		sit.getBuilding().add(gebäude);
		HashMap<Ressource, Integer> tmp_Map = sit.getResourcen();
		for(Ressource res : benötigte_Ressourcen.keySet()){
			
			sit.getResourcen().put(res, tmp_Map.get(res) - benötigte_Ressourcen.get(res));
		}
		situation.put(agent, sit);
		return situation;
	}



	@Override
	public boolean equals(Object arg0) {
		if(arg0 instanceof Baue){
			Baue baue = (Baue) arg0;
			if((this.gebäude == null && baue.getGebäude()==null) ||!(this.gebäude ==  null || baue.getGebäude() == null)&& this.gebäude.equals(baue.getGebäude()) )
				return true;
			else
				return false;
		}else
			return false;
	}
	public HashMap<Ressource, Integer> getBenötigte_Ressourcen() {
		return benötigte_Ressourcen;
	}




	public void setBenötigte_Ressourcen(
			HashMap<Ressource, Integer> benötigte_Ressourcen) {
		this.benötigte_Ressourcen = benötigte_Ressourcen;
	}




	@Override
	public int definiere_ID() {
		if(gebäude instanceof Bäckerrei)
			return 5001;
		else if(gebäude instanceof EisenMine)
			return 5002;
		else if(gebäude instanceof Eisenschmiede)
			return 5003;
		else if(gebäude instanceof Fabrik)
			return 5004;
		else if(gebäude instanceof Farm)
			return 5005;
		else if(gebäude instanceof GoldMine)
			return 5006;
		else if(gebäude instanceof Holzfäller)
			return 5007;
		else if(gebäude instanceof Mühle)
			return 5008;
		else if(gebäude instanceof Sägemühle)
			return 5009;
		else if(gebäude instanceof Steinmetz)
			return 5010;
		return 5011;
	}




	@Override
	public int defeniere_ID() {
		// TODO Auto-generated method stub
		return 0;
	}


}
