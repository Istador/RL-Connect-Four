package Grid;
import java.util.HashMap;

import Agent.*;
public class  Links extends A_Aktion{

	public Links(int id) {
		super(id);
		
	}
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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "LINKS";
	}
	@Override
	public int defeniere_ID() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public HashMap<String, A_Situation> fuehre_Aus(
			HashMap<String, A_Situation> situation, String agent) {
		Situation tmp = (Situation)situation.get(agent);
		int x = tmp.getX();
		if(x > 0)
			x = x-1;
		else
			x = 3;
		tmp.setX(x);
		return situation;
	
	}
	
}