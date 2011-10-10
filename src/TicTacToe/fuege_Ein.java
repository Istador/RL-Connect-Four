package TicTacToe;

import jade.util.leap.Set;

import java.util.ArrayList;
import java.util.HashMap;

import Agent.A_Aktion;
import Agent.A_Situation;

public class fuege_Ein extends A_Aktion{
	int vertikal;
	int horizontal;
	public fuege_Ein(int id, int vertikal, int horizontal) {
		super(id);
		this.vertikal = vertikal;
		this.horizontal = horizontal;
	}

	public int getVertikal() {
		return vertikal;
	}

	public void setVertikal(int vertikal) {
		this.vertikal = vertikal;
	}

	public int getHorizontal() {
		return horizontal;
	}

	public void setHorizontal(int horizontal) {
		this.horizontal = horizontal;
	}

	@Override
	public HashMap<String, A_Situation> fuehre_Aus(HashMap<String, A_Situation> situationen, String agent) {
		java.util.Set<String> keys= situationen.keySet();
		for(String agent_Key : keys){
				Situation sit = (Situation) situationen.get(agent_Key);
			//	System.out.println("VErsu cht zu ändern bei einem Besitzer " + sit.besitzer + " mit " + horizontal + " vert " +vertikal + " bei  " + (vertikal*3+horizontal)+ " und ist : " + sit.toString());
				if(agent.equals("a1"))
					sit.veraendere("a1", (vertikal*3+horizontal));
				else
					sit.veraendere("a2", (vertikal*3+horizontal));
				//	System.out.println("GERÄNDERT auf " + sit.toString());
			
				
		}
		return situationen;
	}
@Override
public String toString() {
	// TODO Auto-generated method stub
	return "auf: " + vertikal + " :" +horizontal;
}

@Override
	public int defeniere_ID() {
	// TODO Auto-generated method stub
	return vertikal + (100*horizontal);
}


}
