package Siedler_Sonstiges;

import jade.util.leap.Serializable;

import java.util.HashMap;

public class Geb�ude  implements Serializable{
	HashMap<Ressource, Integer> ben�tigte_Ressourcen;
	HashMap<Ressource, Integer> produzierte_Ressourcen;
	HashMap<Ressource, Integer> ben�tigt_zumproduzieren_Ressourcen;
	
	public Geb�ude(	HashMap<Ressource, Integer> ben�tigte_Ressourcen,HashMap<Ressource, Integer> produzierte_Ressourcen, HashMap<Ressource, Integer> ben�tigt_zumproduzieren_Ressourcen) {
		this.ben�tigte_Ressourcen = ben�tigte_Ressourcen;
		this.produzierte_Ressourcen = produzierte_Ressourcen;
		this.ben�tigt_zumproduzieren_Ressourcen = ben�tigt_zumproduzieren_Ressourcen;
	}
	
	public HashMap<Ressource, Integer> getBen�tigte_Ressourcen() {
		return ben�tigte_Ressourcen;
	}

	public void setBen�tigte_Ressourcen(
			HashMap<Ressource, Integer> ben�tigte_Ressourcen) {
		this.ben�tigte_Ressourcen = ben�tigte_Ressourcen;
	}

	public HashMap<Ressource, Integer> getProduzierte_Ressourcen() {
		return produzierte_Ressourcen;
	}

	public void setProduzierte_Ressourcen(
			HashMap<Ressource, Integer> produzierte_Ressourcen) {
		this.produzierte_Ressourcen = produzierte_Ressourcen;
	}

	public HashMap<Ressource, Integer> getBen�tigt_zumproduzieren_Ressourcen() {
		return ben�tigt_zumproduzieren_Ressourcen;
	}

	public void setBen�tigt_zumproduzieren_Ressourcen(
			HashMap<Ressource, Integer> ben�tigt_zumproduzieren_Ressourcen) {
		this.ben�tigt_zumproduzieren_Ressourcen = ben�tigt_zumproduzieren_Ressourcen;
	}
	@Override
	public boolean equals(Object arg0) {
		if(arg0 instanceof Geb�ude){
			Geb�ude tmp = (Geb�ude) arg0;
			if(tmp.toString().equals(this.toString()))
				return true;
			else 
				return false;
		}else
			return false;
	}
}
