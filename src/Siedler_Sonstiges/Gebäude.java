package Siedler_Sonstiges;

import jade.util.leap.Serializable;

import java.util.HashMap;

public class Gebäude  implements Serializable{
	HashMap<Ressource, Integer> benötigte_Ressourcen;
	HashMap<Ressource, Integer> produzierte_Ressourcen;
	HashMap<Ressource, Integer> benötigt_zumproduzieren_Ressourcen;
	
	public Gebäude(	HashMap<Ressource, Integer> benötigte_Ressourcen,HashMap<Ressource, Integer> produzierte_Ressourcen, HashMap<Ressource, Integer> benötigt_zumproduzieren_Ressourcen) {
		this.benötigte_Ressourcen = benötigte_Ressourcen;
		this.produzierte_Ressourcen = produzierte_Ressourcen;
		this.benötigt_zumproduzieren_Ressourcen = benötigt_zumproduzieren_Ressourcen;
	}
	
	public HashMap<Ressource, Integer> getBenötigte_Ressourcen() {
		return benötigte_Ressourcen;
	}

	public void setBenötigte_Ressourcen(
			HashMap<Ressource, Integer> benötigte_Ressourcen) {
		this.benötigte_Ressourcen = benötigte_Ressourcen;
	}

	public HashMap<Ressource, Integer> getProduzierte_Ressourcen() {
		return produzierte_Ressourcen;
	}

	public void setProduzierte_Ressourcen(
			HashMap<Ressource, Integer> produzierte_Ressourcen) {
		this.produzierte_Ressourcen = produzierte_Ressourcen;
	}

	public HashMap<Ressource, Integer> getBenötigt_zumproduzieren_Ressourcen() {
		return benötigt_zumproduzieren_Ressourcen;
	}

	public void setBenötigt_zumproduzieren_Ressourcen(
			HashMap<Ressource, Integer> benötigt_zumproduzieren_Ressourcen) {
		this.benötigt_zumproduzieren_Ressourcen = benötigt_zumproduzieren_Ressourcen;
	}
	@Override
	public boolean equals(Object arg0) {
		if(arg0 instanceof Gebäude){
			Gebäude tmp = (Gebäude) arg0;
			if(tmp.toString().equals(this.toString()))
				return true;
			else 
				return false;
		}else
			return false;
	}
}
