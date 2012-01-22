package Agent;

/**
 * Eine Konkrete Spielsituation
 * definiere_ID() muss von der Anwendung konkret implementiert werden.
 *
 */
public abstract class A_Situation implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	int episode;
	
	
	
	public int getEpisode() {return episode;}
	public void setEpisode(int episode) {this.episode = episode;}
	
	private long getId() {return definiere_ID();}	
	
	/**
	 * eindeutiger Identifier für die Situation
	 */
	public abstract long definiere_ID();
	
	
	
	
	
	@Override
	public String toString() {
		return String.valueOf(getId());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (getId() ^ (getId() >>> 32));
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
		if (!(obj instanceof A_Situation)) {
			return false;
		}
		A_Situation other = (A_Situation) obj;
		if (getId() != other.getId()) {
			return false;
		}
		return true;
	}

	
	
}