package TicTacToe;

import jade.util.leap.Serializable;

public class Status_Feld implements Serializable{
	public Status_Feld(String a1){
		p1 = true;
	}
	public Status_Feld() {
		// TODO Auto-generated constructor stub
	}
	public boolean isP1() {
		return p1;
	}
	public boolean isP2() {
		return p2;
	}
	public static Status_Feld status_Feld_COP (Status_Feld tmp){
		Status_Feld neu = new Status_Feld();
		neu.setGeaendert(tmp.isGeaendert());
		neu.setP1(tmp.isP1());
		neu.setP2(tmp.isP2());
		return neu;
	}
		public void setP1(boolean p1) {
		this.geaendert = true;
		this.p1 = p1;
	}
	public void setP2(boolean p2) {
		this.geaendert = true;
		this.p2 = p2;
	}
		boolean p1 = false;
		boolean p2 = false;
		boolean geaendert = false;

		public boolean isGeaendert() {
			return geaendert;
		}
		public void setGeaendert(boolean geaendert) {
			this.geaendert = geaendert;
		}
		public void machP1(){
			p1=true;
		}
		public boolean p1(){
			if(!geaendert){
				p1 = true;
				geaendert = true;
				return true;
			}else
				return false;
		}
		public boolean p2(){
			if(!geaendert){
				p2 = true;
				geaendert = true;
				return true;
			}else
				return false;
		}
		@Override
		public String toString() {
		if(!geaendert)
			return "leer";
		else if(p1)
			return "p1";
		else
			return "p2";
		}
}
