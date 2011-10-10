package Agent;


public abstract class A_Situation implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	public int id = 99;
	int episode;
	public int getEpisode() {
		return episode;
	}
	public void setEpisode(int episode) {
		this.episode = episode;
	}
	public long getId() {
		return defeniere_ID();
	}
	public abstract long defeniere_ID();
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof A_Situation) {
			A_Situation tmp = (A_Situation) arg0;
			if (tmp.getId() == this.getId())
				return true;
			else
				return false;
		} else
			return false;
	}
}