package Sieder_Gebäude_P;

import jade.util.leap.Serializable;

import java.util.HashMap;

import Siedler_Sonstiges.Gebäude;
import Siedler_Sonstiges.Ressource;

public class Sägemühle extends Gebäude implements Serializable{

	public Sägemühle(HashMap<Ressource, Integer> benötigte_Ressourcen,
			HashMap<Ressource, Integer> produzierte_Ressourcen,
			HashMap<Ressource, Integer> benötigt_zumproduzieren_Ressourcen) {
		super(benötigte_Ressourcen, produzierte_Ressourcen,
				benötigt_zumproduzieren_Ressourcen);
		// TODO Auto-generated constructor stub
	}
	public String toString() {
		// TODO Auto-generated method stub
		return "Sägemühle";
	}
}
