package Sieder_Gebäude_P;

import java.util.HashMap;

import Siedler_Sonstiges.Gebäude;
import Siedler_Sonstiges.Ressource;

public class EisenMine extends Gebäude{

	public EisenMine(HashMap<Ressource, Integer> benötigte_Ressourcen,
			HashMap<Ressource, Integer> produzierte_Ressourcen,
			HashMap<Ressource, Integer> benötigt_zumproduzieren_Ressourcen) {
		super(benötigte_Ressourcen, produzierte_Ressourcen,
				benötigt_zumproduzieren_Ressourcen);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Eisenmine";
	}

}
