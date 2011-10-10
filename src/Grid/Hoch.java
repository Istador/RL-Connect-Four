package Grid;
import java.util.HashMap;

import Agent.*;
public class  Hoch extends A_Aktion{

	public Hoch(int id) {
		super(id);
		
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
		// TODO Auto-generated method stub
		return "HOCH";
	}
	@Override
	public int defeniere_ID() {
		// TODO Auto-generated method stub
		return 3;
	}
	@Override
	public HashMap<String, A_Situation> fuehre_Aus(
			HashMap<String, A_Situation> situation, String agent) {
		Situation tmp = (Situation)situation.get(agent);
		int y = tmp.getY();
		if(y < 3)
			y = y+1;
		else
			y = 0;
		tmp.setY(y);
		return situation;
	}
	
}