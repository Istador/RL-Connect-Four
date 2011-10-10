package TicTacToe;

import java.util.ArrayList;

import jade.util.leap.Serializable;
import Agent.A_Situation;

public class Situation extends A_Situation implements Serializable{
	String besitzer;
	Status_Feld[] feld = new Status_Feld[9];
	public Status_Feld[] getFeld() {
		return feld;
	}
	public void setFeld(Status_Feld[] feld) {
		this.feld = feld;
	}
	public Situation() {
		for(int i=0;i<9;i++)
			feld[i] = new Status_Feld();
	}
	public String getBesitzer() {
		return besitzer;
	}
	public void setBesitzer(String besitzer) {
		this.besitzer = besitzer;
	}

	

	
	public Situation(String besitzer) {
		this.besitzer = besitzer;
		for(int i=0;i<9;i++)
			feld[i] = new Status_Feld();
		
	}
	public void veraendere(String agent, int feld){
		//System.err.println("Bekommt " + toString() + " wobei feld " + feld + " ist");
		if(agent.equals("a1")){
			this.feld[feld].p1();
		}
		else{
			this.feld[feld].p2();
		}
		//System.err.println("Daraus wird " + toString());
		
		
	}
		
	@Override
	public long defeniere_ID() {
		int id= 0;
		for(int i =0;i<9;i++){
			if(this.feld[i].isGeaendert())
				id+=i*4 + 1;
			else if(feld[i].isP1())
				id+=i*4 +2;
			else
				id+=i*4 + 3;
		}
		return id;
		
	}
	public String toString(){
		String tmp = "\n";
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(!feld[i+j*3].isGeaendert())
					tmp = tmp + " "  + " 0";
				else if(this.feld[i+j*3].isP1())
					tmp = tmp + " "  + " 1";
				else if(this.feld[i+j*3].isP2())
					tmp = tmp + " "  + " 2";
			}
			tmp = tmp +"\n";
		}
	/*	for(int i=0;i<9;i++){
			if(!feld[i].isGeaendert())
				tmp = tmp + " "  + " 0";
			else if(feld[i].isP1())
				tmp = tmp + " "  + " 1";
			else if(feld[i].isP2())
				tmp = tmp + " "  + " 2";
		}*/
			
		return "Situation :"+  tmp;
	}

}
