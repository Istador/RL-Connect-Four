package Siedler_Aktionen;

import jade.util.leap.Serializable;

import java.util.ArrayList;
import java.util.HashMap;

import Sieder_Geb�ude_P.B�ckerrei;
import Sieder_Geb�ude_P.EisenMine;
import Sieder_Geb�ude_P.Eisenschmiede;
import Sieder_Geb�ude_P.Fabrik;
import Sieder_Geb�ude_P.Farm;
import Sieder_Geb�ude_P.GoldMine;
import Sieder_Geb�ude_P.Holzf�ller;
import Sieder_Geb�ude_P.M�hle;
import Sieder_Geb�ude_P.Steinmetz;
import Sieder_Geb�ude_P.S�gem�hle;
import Siedler_Sonstiges.Geb�ude;
import Siedler_Sonstiges.Ressource;
import Siedler_Sonstiges.Situation;

import Agent.A_Aktion;
import Agent.A_Situation;

public class Baue extends A_Aktion implements Serializable{
	public Geb�ude getGeb�ude() {
		return geb�ude;
	}




	public void setGeb�ude(Geb�ude geb�ude) {
		this.geb�ude = geb�ude;
	}




	Geb�ude geb�ude;
	
	HashMap<Ressource, Integer> ben�tigte_Ressourcen = new HashMap<Ressource, Integer>();
	public Baue(int i, Geb�ude geb�ude) {
		super(i);
		this.geb�ude = geb�ude;
		if(geb�ude!=null)
		this.ben�tigte_Ressourcen = geb�ude.getBen�tigte_Ressourcen();
	
	//	System.out.println("Baut ein " + geb�ude);
	}



	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		if(geb�ude == null)
			return "Baue nichts";
		return "BAUE " + geb�ude;
	}
	public Situation produziere(Situation situation){

		ArrayList<Geb�ude> gebauede = (ArrayList<Geb�ude>) situation.getBuilding();
		for(int i=0;i<gebauede.size();i++){
			
			HashMap<Ressource, Integer> erhaltene_Ressourcen = gebauede.get(i).getProduzierte_Ressourcen();
			HashMap<Ressource, Integer> ben�tigt = gebauede.get(i).getBen�tigt_zumproduzieren_Ressourcen();
			//Produziere wenn man nix braucht
			if(ben�tigt.keySet().isEmpty()){
				for(Ressource res : erhaltene_Ressourcen.keySet())
				try {
					situation.getResourcen().put(res, situation.getResourcen().get(res) + erhaltene_Ressourcen.get(res));
				} catch (Exception e) {
					System.err.println(e);
				}
					
			}else{
				boolean erhalte = true;
				for(Ressource res : ben�tigt.keySet()){
					if(situation.getResourcen().get(res) < ben�tigt.get(res))
						erhalte = false;
				}
				if(erhalte){
					for(Ressource res : erhaltene_Ressourcen.keySet()){
					//	System.err.println("Erhalte " + erhaltene_Ressourcen.get(res) + " an " + res + " von " + gebauede.get(i).toString()) ;
						situation.getResourcen().put(res, situation.getResourcen().get(res) + erhaltene_Ressourcen.get(res));
						
					}
					for(Ressource res : ben�tigt.keySet()){
						//System.err.println("Verliere daf�r " + ben�tigt.get(res) + " an " + res + " von " + gebauede.get(i).toString()) ;
						situation.getResourcen().put(res, situation.getResourcen().get(res) - ben�tigt.get(res));
						
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
		if(geb�ude == null)
			return situation;
		System.out.println("Ausf�hren vom bau von " + geb�ude);
	
		sit.getBuilding().add(geb�ude);
		HashMap<Ressource, Integer> tmp_Map = sit.getResourcen();
		for(Ressource res : ben�tigte_Ressourcen.keySet()){
			
			sit.getResourcen().put(res, tmp_Map.get(res) - ben�tigte_Ressourcen.get(res));
		}
		situation.put(agent, sit);
		return situation;
	}



	@Override
	public boolean equals(Object arg0) {
		if(arg0 instanceof Baue){
			Baue baue = (Baue) arg0;
			if((this.geb�ude == null && baue.getGeb�ude()==null) ||!(this.geb�ude ==  null || baue.getGeb�ude() == null)&& this.geb�ude.equals(baue.getGeb�ude()) )
				return true;
			else
				return false;
		}else
			return false;
	}
	public HashMap<Ressource, Integer> getBen�tigte_Ressourcen() {
		return ben�tigte_Ressourcen;
	}




	public void setBen�tigte_Ressourcen(
			HashMap<Ressource, Integer> ben�tigte_Ressourcen) {
		this.ben�tigte_Ressourcen = ben�tigte_Ressourcen;
	}




	@Override
	public int definiere_ID() {
		if(geb�ude instanceof B�ckerrei)
			return 5001;
		else if(geb�ude instanceof EisenMine)
			return 5002;
		else if(geb�ude instanceof Eisenschmiede)
			return 5003;
		else if(geb�ude instanceof Fabrik)
			return 5004;
		else if(geb�ude instanceof Farm)
			return 5005;
		else if(geb�ude instanceof GoldMine)
			return 5006;
		else if(geb�ude instanceof Holzf�ller)
			return 5007;
		else if(geb�ude instanceof M�hle)
			return 5008;
		else if(geb�ude instanceof S�gem�hle)
			return 5009;
		else if(geb�ude instanceof Steinmetz)
			return 5010;
		return 5011;
	}




	@Override
	public int defeniere_ID() {
		// TODO Auto-generated method stub
		return 0;
	}


}
