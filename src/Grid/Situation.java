package Grid;

import Agent.A_Aktion;
import Agent.A_Situation;

public class Situation extends A_Situation{
	
	int x = 0;
	int y = 0;
	@Override
	public int hashCode() {
		return x + (100 * y);
	}
	@Override
	public boolean equals(Object arg0) {
		if(arg0 instanceof Situation){
			Situation tmp = (Situation) arg0;
			if(tmp.getX() ==  this.getX() && tmp.getY() ==  this.getY())
				return true;
			else
				return false;
		}else
			return false;
		
	}
	public Situation() {
		int x = 0;
		int y = 0;
	}
	public Situation(int x, int y) {
			this.x=x;
			this.y=y;
	}
	public static Situation copy(Situation situation){
		Situation tmp = new Situation();
		tmp.setX(situation.getX());
		tmp.setY(situation.getY());
		return tmp;
	}
		public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
/*	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof A_Situation) {
			Situation tmp = (Situation) arg0;
			if (tmp.getX() == this.getX() && tmp.getY() == this.getY())
				return true;
			else
				return false;
		} else
			return false;
	}*/
		
	
	@Override
	public String toString() {
		return  " x=" + this.x + " und y: " + this.y;
		
	}
	@Override
	public long definiere_ID() {
		return x + (100 * y);
	}
}
