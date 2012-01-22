package Grid;
import java.util.Map;

import Agent.*;
public class  Links extends A_Aktion{

	public Links() {
		super();
	}
	
	/*
	//@Override
	public A_Situation fuehre_Aus(A_Situation situation) {
			Situation tmp = (Situation) situation;
			int x = tmp.getX();
			if(x > 0)
				x = x-1;
			else
				x = 3;
			tmp.setX(x);
			return situation;
	}
	 */
	
	@Override
	public String toString() {
		return "LINKS";
	}
	@Override
	public int definiere_ID() {
		return 0;
	}
	@Override
	public A_Situation fuehre_Aus(A_Situation situation, String agent) {
	//public Map<String, A_Situation> fuehre_Aus(Map<String, A_Situation> situation, String agent) {
		Situation tmp = Situation.copy((Situation)situation);
		int x = tmp.getX();
		if(x > 0)
			x = x-1;
		else
			x = 3;
		tmp.setX(x);
		return tmp;
	
	}
	
}