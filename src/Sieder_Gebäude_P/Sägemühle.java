package Sieder_Geb�ude_P;

import jade.util.leap.Serializable;

import java.util.HashMap;

import Siedler_Sonstiges.Geb�ude;
import Siedler_Sonstiges.Ressource;

public class S�gem�hle extends Geb�ude implements Serializable{

	public S�gem�hle(HashMap<Ressource, Integer> ben�tigte_Ressourcen,
			HashMap<Ressource, Integer> produzierte_Ressourcen,
			HashMap<Ressource, Integer> ben�tigt_zumproduzieren_Ressourcen) {
		super(ben�tigte_Ressourcen, produzierte_Ressourcen,
				ben�tigt_zumproduzieren_Ressourcen);
		// TODO Auto-generated constructor stub
	}
	public String toString() {
		// TODO Auto-generated method stub
		return "S�gem�hle";
	}
}
