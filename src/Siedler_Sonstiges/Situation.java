package Siedler_Sonstiges;

import jade.util.leap.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

import Agent.A_Situation;



public class Situation extends A_Situation implements java.io.Serializable {
	HashMap<Ressource, Integer> resourcen = new HashMap<Ressource, Integer>();
	
	public HashMap<Ressource, Integer> getResourcen() {
		return resourcen;
	}
	
	public void setResourcen(HashMap<Ressource, Integer> resourcen) {
		this.resourcen = resourcen;
	}
	
	protected final List<Geb�ude> geb�ude = new ArrayList<Geb�ude>();
	
	public List<Geb�ude> getBuilding() {
		return geb�ude;
	}
	public Situation() {
		resourcen.put(Ressourcen.getRessource("Holz") ,0);
		resourcen.put(Ressourcen.getRessource("Baumstamm") , 20);
		resourcen.put(Ressourcen.getRessource("Gras") , 0);
		resourcen.put(Ressourcen.getRessource("Mehl"), 0);
		resourcen.put(Ressourcen.getRessource("Brot"), 0);
		resourcen.put(Ressourcen.getRessource("Eisenerz"), 0);
		resourcen.put(Ressourcen.getRessource("Erz"), 0);
		resourcen.put(Ressourcen.getRessource("Gold"), 0);
		resourcen.put(Ressourcen.getRessource("Eisen"), 0);
		resourcen.put(Ressourcen.getRessource("Stein"), 10);
	}
	
	public static Situation copy(Situation other){
		Situation neu = new Situation();
		HashMap<Ressource, Integer> ressis = new HashMap<Ressource, Integer>(other.getResourcen());
		neu.setResourcen(ressis);
		return neu;
	}
	
	@Override
	public boolean equals(Object arg0) {
		if(arg0 instanceof Situation){
			Situation sit = (Situation) arg0;
			HashMap<Ressource, Integer> res_tmp = sit.getResourcen();
			for(Ressource res : res_tmp.keySet()){
				if(!(this.resourcen.get(res).equals(res_tmp.get(res))))
					return false;
			}
			ArrayList<Geb�ude> geb�ude_tmp = (ArrayList<Geb�ude>) sit.getBuilding();
			for(int i=0;i<geb�ude.size();i++){
				if(!this.geb�ude.get(i).equals(geb�ude_tmp.get(i)))
					return false;
			}
			return true;
		}else
			return false;
	}
	@Override
	public int hashCode() {
		int id=0;
		int b�ckerrei=0;
		int eisenMine=0;
		int eisenSchmiede=0;
		int fabrik =0;
		int farm =0;
		int goldMine=0;
		int holzf�ller=0;
		int m�hle=0;
		int s�gem�hle=0;
		int steinmetz=0;
		for(int i=0;i<geb�ude.size();i++){
			if(geb�ude.get(i) instanceof B�ckerrei)
				b�ckerrei++;
			else if(geb�ude.get(i) instanceof EisenMine)
				eisenMine++;
			else if(geb�ude.get(i) instanceof Eisenschmiede)
				eisenSchmiede++;
			else if(geb�ude.get(i) instanceof Fabrik)
				fabrik++;
			else if(geb�ude.get(i) instanceof Farm)
				farm++;
			else if(geb�ude.get(i) instanceof GoldMine)
				goldMine++;
			else if(geb�ude.get(i) instanceof Holzf�ller)
				holzf�ller++;
			else if(geb�ude.get(i) instanceof M�hle)
				m�hle++;
			else if(geb�ude.get(i) instanceof S�gem�hle)
				s�gem�hle++;
			else if(geb�ude.get(i) instanceof Steinmetz)
				steinmetz++;
		}
		id = b�ckerrei + (20 * eisenMine) + (40 * eisenSchmiede) + (60* fabrik) + (80 * farm) + (100 * goldMine) + (120 * holzf�ller) + (140 * m�hle) + (160 * s�gem�hle) + (180 *steinmetz);
		int holz=resourcen.get(new Ressource("Holz"));
		int baumstamm =resourcen.get(new Ressource("Baumstamm"));
		int gras =resourcen.get(new Ressource("Gras"));
		int mehl =resourcen.get(new Ressource("Mehl"));
		int brot =resourcen.get(new Ressource("Brot"));
		int eisenerz =resourcen.get(new Ressource("Eisenerz"));
		int erz=resourcen.get(new Ressource("Erz"));
		int gold=resourcen.get(new Ressource("Gold"));
		
		int eisen=resourcen.get(new Ressource("Eisen"));
		int stein=resourcen.get(new Ressource("Stein"));
		return id;
	}
	@Override
	public long definiere_ID() {
		int id=0;
		int b�ckerrei=0;
		int eisenMine=0;
		int eisenSchmiede=0;
		int fabrik =0;
		int farm =0;
		int goldMine=0;
		int holzf�ller=0;
		int m�hle=0;
		int s�gem�hle=0;
		int steinmetz=0;
		for(int i=0;i<geb�ude.size();i++){
			if(geb�ude.get(i) instanceof B�ckerrei)
				b�ckerrei++;
			else if(geb�ude.get(i) instanceof EisenMine)
				eisenMine++;
			else if(geb�ude.get(i) instanceof Eisenschmiede)
				eisenSchmiede++;
			else if(geb�ude.get(i) instanceof Fabrik)
				fabrik++;
			else if(geb�ude.get(i) instanceof Farm)
				farm++;
			else if(geb�ude.get(i) instanceof GoldMine)
				goldMine++;
			else if(geb�ude.get(i) instanceof Holzf�ller)
				holzf�ller++;
			else if(geb�ude.get(i) instanceof M�hle)
				m�hle++;
			else if(geb�ude.get(i) instanceof S�gem�hle)
				s�gem�hle++;
			else if(geb�ude.get(i) instanceof Steinmetz)
				steinmetz++;
		}
		id = b�ckerrei + (20 + eisenMine) + (20 + eisenSchmiede) + (20 + fabrik) + (20 + farm) + (20 + goldMine) + (20 + holzf�ller) + (20 + m�hle) + (20 + s�gem�hle) + (20 +steinmetz);
		int holz=resourcen.get(new Ressource("Holz"));
		int baumstamm =resourcen.get(new Ressource("Baumstamm"));
		int gras =resourcen.get(new Ressource("Gras"));
		int mehl =resourcen.get(new Ressource("Mehl"));
		int brot =resourcen.get(new Ressource("Brot"));
		int eisenerz =resourcen.get(new Ressource("Eisenerz"));
		int erz=resourcen.get(new Ressource("Erz"));
		int gold=resourcen.get(new Ressource("Gold"));
		
		int eisen=resourcen.get(new Ressource("Eisen"));
		int stein=resourcen.get(new Ressource("Stein"));
		return id;
		
	}
	@Override
	public String toString() {
		String tmp = "\nvorhandende Geb�ude: \n";
			for(int i=0;i<geb�ude.size();i++){
				tmp += " " + geb�ude.get(i).toString() + " \n";
			}
			tmp += " \nMit den Ressourcen: \n ";
			for(Ressource res : resourcen.keySet()){
				tmp += res.toString()+ ": " + resourcen.get(res) + " \n ";
			}
		return tmp;
	}
	
}
