package Grid;
import java.util.Map;

import Agent.*;
public class  Rechts extends A_Aktion{

	public Rechts() {
		super();	
	}
	
	/*
	//@Override
	public A_Situation fuehre_Aus(A_Situation situation) {
			Situation tmp = (Situation) situation;
			int x = tmp.getX();
			if(x < 3)
				x = x+1;
			else
				x = 0;
			tmp.setX(x);
			return situation;
	}
	*/

	@Override
	public String toString() {
		return "RECHTS";
	}
	@Override
	public int definiere_ID() {
		return 1;
	}
	@Override
	public A_Situation fuehre_Aus(A_Situation situation, String agent) {
	//public Map<String, A_Situation> fuehre_Aus(Map<String, A_Situation> situation, String agent) {
		Situation tmp = Situation.copy((Situation)situation);
		int x = tmp.getX();
		if(x < 3)
			x = x+1;
		else
			x = 0;
		tmp.setX(x);
		return tmp;
	}
	
}