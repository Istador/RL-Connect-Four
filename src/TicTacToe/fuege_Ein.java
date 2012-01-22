package TicTacToe;

import java.util.Map;
import java.util.Set;

import Agent.A_Aktion;
import Agent.A_Situation;

public class fuege_Ein extends A_Aktion{
	int vertikal;
	int horizontal;
	public fuege_Ein(int vertikal, int horizontal) {
		super();
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
	public A_Situation fuehre_Aus(A_Situation situation, String agent) {
	/*public Map<String, A_Situation> fuehre_Aus(Map<String, A_Situation> situationen, String agent) {
		
		Set<String> keys= situationen.keySet();
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
		*/
		Situation sit = Situation.copy((Situation) situation);
		sit.veraendere(agent, (vertikal*3+horizontal));
		return sit;
	}
@Override
public String toString() {
	// TODO Auto-generated method stub
	return "auf: " + vertikal + " :" +horizontal;
}

@Override
public int definiere_ID() {
	return vertikal + (100*horizontal);
}


}
