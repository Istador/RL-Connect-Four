package Grid;
import java.util.HashMap;

import Agent.*;
public class  Rechts extends A_Aktion{

	public Rechts(int id) {
		super(id);
		
	}
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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "RECHTS";
	}
	@Override
	public int defeniere_ID() {
		// TODO Auto-generated method stub
		return 1;
	}
	@Override
	public HashMap<String, A_Situation> fuehre_Aus(
			HashMap<String, A_Situation> situation, String agent) {
		Situation tmp = (Situation)situation.get(agent);
		int x = tmp.getX();
		if(x < 3)
			x = x+1;
		else
			x = 0;
		tmp.setX(x);
		return situation;
	}
	
}