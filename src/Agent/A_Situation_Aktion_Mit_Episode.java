package Agent;

/**
 * A_Situation_Aktion mit Episode (z.B. das wievielte Spiel das ist)
 */
public class A_Situation_Aktion_Mit_Episode {
	
	int episode;
	A_Situation_Aktion sa;
	
	
	
	public A_Situation_Aktion_Mit_Episode(A_Situation_Aktion sa, int episode) {
		this.sa = sa;
		this.episode = episode;	
	}
	
	
	
	public int getEpisode() {return episode;}
	//public void setEpisode(int episode) {this.episode = episode;}

	public A_Situation getSituation() {return sa.getSituation();}
	//public void setSituation(A_Situation situation) {sa.setSituation(situation);}

	public A_Aktion getAktion() {return sa.getAktion();}
	//public void setAktion(A_Aktion aktion) {sa.setAktion(aktion);}

	public A_Situation_Aktion getSituationsAktion() {return sa;}
	
	
	@Override
	public String toString() {
		return "Situation: " + getSituation().toString() + " Aktion: " + getAktion().toString();
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + episode;
		result = prime * result + ((sa == null) ? 0 : sa.hashCode());
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
		if (!(obj instanceof A_Situation_Aktion_Mit_Episode)) {
			return false;
		}
		A_Situation_Aktion_Mit_Episode other = (A_Situation_Aktion_Mit_Episode) obj;
		if (episode != other.episode) {
			return false;
		}
		if (sa == null) {
			if (other.sa != null) {
				return false;
			}
		} else if (!sa.equals(other.sa)) {
			return false;
		}
		return true;
	}

	
	
}
