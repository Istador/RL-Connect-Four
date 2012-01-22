package Agent;

/**
 * 
 * Interface für Aktionen (Spielzüge)
 * fuehre_Aus(Map, agent) und definiere_ID() müssen von der Anwendung implementiert werden
 *
 */
public abstract class  A_Aktion implements java.io.Serializable{
	private static final long serialVersionUID = 2449526577536844219L;

	/**
	 * Führe die Aktion auf der übergebenen Aktion aus
	 */
	public abstract A_Situation fuehre_Aus(A_Situation situation, String agent);

	
	private int getId() {return definiere_ID();}
	
	/**
	 * eindeutiger Identifier für die Aktion
	 */
	public abstract int definiere_ID();
	
	
	
	@Override
	public String toString() {
		return String.valueOf(getId());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + getId();
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
		if (!(obj instanceof A_Aktion)) {
			return false;
		}
		A_Aktion other = (A_Aktion) obj;
		if (getId() != other.getId()) {
			return false;
		}
		return true;
	}
	
	
}
