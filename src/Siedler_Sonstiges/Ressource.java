package Siedler_Sonstiges;

import Agent.A_Aktion;
import jade.util.leap.Serializable;

public class Ressource implements Serializable{
	String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Ressource(String name) {
	
		this.name = name;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}
	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof Ressource) {
			Ressource tmp = (Ressource) arg0;
			if (tmp.getName().equals(this.getName()))
				return true;
			else
				return false;
		} else
			return false;
	}
	
	
	@Override
    public int hashCode() {
        //System.out.println("in hashCode der Klasse ..");
        if(name.equals("Holz")) {        
         //   System.out.println("werde gleich 8 zurückgeben");
            return 8;
        }
      //  System.out.println("werde gleich 9 zurückgeben");
        return 9;
    }
}
