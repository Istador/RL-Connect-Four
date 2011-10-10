package Grid;
import java.util.HashMap;

import Agent.*;
public class  Runter extends A_Aktion{

	public Runter(int id) {
		super(id);
		
	}
	//@Override
	public A_Situation fuehre_Aus(A_Situation situation) {
			Situation tmp = (Situation) situation;
			int y = tmp.getY();
			if(y > 0)
				y = y-1;
			else
				y = 3;
			tmp.setY(y);
			return situation;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "RUNTER";
	}
	@Override
	public int defeniere_ID() {
		// TODO Auto-generated method stub
		return 2;
	}
	@Override
	public HashMap<String, A_Situation> fuehre_Aus(
			HashMap<String, A_Situation> situation, String agent) {
		Situation tmp = (Situation)situation.get(agent);
		int y = tmp.getY();
		if(y > 0)
			y = y-1;
		else
			y = 3;
		tmp.setY(y);
		return situation;
	}

	
}