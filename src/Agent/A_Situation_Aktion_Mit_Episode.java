package Agent;

public class A_Situation_Aktion_Mit_Episode {
	int episode;
	public int getEpisode() {
		return episode;
	}

	public void setEpisode(int episode) {
		this.episode = episode;
	}

	public A_Situation getSituation() {
		return situation;
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof A_Situation_Aktion) {
			A_Situation_Aktion tmp = (A_Situation_Aktion) arg0;
			if (tmp.getAktion().equals(this.getAktion())
					&& tmp.getSituation().equals(this.getSituation()))
				return true;
			else
				return false;
		} else
			return false;
	}
	
	public void setSituation(A_Situation situation) {
		this.situation = situation;
	}

	public A_Aktion getAktion() {
		return aktion;
	}

	public void setAktion(A_Aktion aktion) {
		this.aktion = aktion;
	}

	A_Situation situation;
	A_Aktion aktion;

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return (int) (aktion.getId() + situation.getId());
	}
	 public A_Situation_Aktion_Mit_Episode(A_Situation situation, A_Aktion aktion, int episode) {
		this.aktion = aktion;
		this.situation = situation;
		this.episode = episode;
		
	}
	@Override
	public String toString() {
		return "Situation: " + situation.toString() + " Aktion: " + aktion.toString();
	}

}
