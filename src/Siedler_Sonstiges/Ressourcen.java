package Siedler_Sonstiges;

import java.util.ArrayList;

public class Ressourcen {
	static ArrayList<Ressource> ressourcen = new ArrayList<Ressource>();
	public Ressourcen() {
		ressourcen.add(new Ressource("Holz"));
		ressourcen.add(new Ressource("Baumstamm"));
		ressourcen.add(new Ressource("Gras"));
		ressourcen.add(new Ressource("Mehl"));
		ressourcen.add(new Ressource("Brot"));
		ressourcen.add(new Ressource("Eisenerz"));
		ressourcen.add(new Ressource("Erz"));
		ressourcen.add(new Ressource("Gold"));
		ressourcen.add(new Ressource("Eisen"));
		ressourcen.add(new Ressource("Stein"));
	}
	public static Ressource getRessource(String ressource){
		for (int i=0;i<ressourcen.size();i++){
			if(ressourcen.get(i).getName().equals(ressource))
				return ressourcen.get(i);
		}
			return null;
	}
	public static int get_ID(String ressource){
		for (int i=0;i<ressourcen.size();i++){
			if(ressourcen.get(i).getName().equals(ressource))
				return i;
		}
			return -1;
	}

}
