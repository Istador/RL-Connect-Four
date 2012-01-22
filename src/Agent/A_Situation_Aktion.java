package Agent;

import java.io.Serializable;

/**
 * Die Kombination aus Situation und Aktion die bewertet wird (Q-Werte).
 */
public class A_Situation_Aktion implements Serializable {
	private static final long serialVersionUID = 1L;
		
	protected int episode;
	protected A_Situation situation;
	protected A_Aktion aktion;
	
	
	
	public A_Situation_Aktion(A_Situation situation, A_Aktion aktion, int episode) {
		this.aktion = aktion;
		this.situation = situation;
		this.episode = episode;
	}
	
	
	
	public int getEpisode() {return episode;}
	//public void setEpisode(int episode) {this.episode = episode;}
	
	public A_Situation getSituation() {return situation;}
	//public void setSituation(A_Situation situation) {this.situation = situation;}
	
	public A_Aktion getAktion() {return aktion;}
	//public void setAktion(A_Aktion aktion) {this.aktion = aktion;}
	
	
	
	
	
	/**
	 * String repräsentation für GUI_Agent 
	 */
	public String getEpisodeString(){return String.valueOf(episode);}
	
	/**
	 * String repräsentation für GUI_Agent 
	 */
	public String getSituationString(){return situation.toString();}
	
	/**
	 * String repräsentation für GUI_Agent 
	 */
	public String getAktionString(){return aktion.toString();}
	
	
	
	
	
	@Override
	public String toString() {
		//return "Situation: " + situation.toString() + " Aktion: " + aktion.toString();
		return "Situation: " + situation.definiere_ID() + " Aktion: " + aktion.toString();
	}

	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aktion == null) ? 0 : aktion.hashCode());
		result = prime * result + ((situation == null) ? 0 : situation.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof A_Situation_Aktion)) {
			return false;
		}
		A_Situation_Aktion other = (A_Situation_Aktion) obj;
		if (aktion == null) {
			if (other.aktion != null) {
				return false;
			}
		} else if (!aktion.equals(other.aktion)) {
			return false;
		}
		if (situation == null) {
			if (other.situation != null) {
				return false;
			}
		} else if (!situation.equals(other.situation)) {
			return false;
		}
		return true;
	}
	
	

	
}
