package Grid;
import java.util.Map;

import Agent.*;
public class  Hoch extends A_Aktion{

	public Hoch() {
		super();
	}
	
	//@Override
	public A_Situation fuehre_Aus(A_Situation situation) {
			Situation tmp = (Situation) situation;
			int y = tmp.getY();
			if(y < 3)
				y = y+1;
			else
				y = 0;
			tmp.setY(y);
			return situation;
	}

	@Override
	public String toString() {
		return "HOCH";
	}
	@Override
	public int definiere_ID() {
		return 3;
	}
	@Override
	public A_Situation fuehre_Aus(A_Situation situation, String agent) {
	//public Map<String, A_Situation> fuehre_Aus(Map<String, A_Situation> situation, String agent) {
		Situation tmp = Situation.copy((Situation)situation);
		int y = tmp.getY();
		if(y < 3)
			y = y+1;
		else
			y = 0;
		tmp.setY(y);
		return tmp;
	}
	
}