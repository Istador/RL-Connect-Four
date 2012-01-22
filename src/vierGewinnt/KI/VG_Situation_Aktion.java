package vierGewinnt.KI;

import java.text.DecimalFormat;

import Agent.A_Aktion;
import Agent.A_Situation;
import Agent.A_Situation_Aktion;

public class VG_Situation_Aktion extends A_Situation_Aktion {
	private static final long serialVersionUID = 6407916692725300178L;
	private static final DecimalFormat df_s = new DecimalFormat("0000000000000000000"); // Zustands-ID
	
	private final long id;
	
	public VG_Situation_Aktion(A_Situation situation, A_Aktion aktion, int episode) {
		super(situation, aktion, episode);
		this.id = VG_Utility.getFolgeID(((VG_Situation)situation).getMatrix(), (byte)aktion.definiere_ID(), (byte)(episode%2+1));
	}

	
	
	@Override
	public String getSituationString(){return df_s.format(id);}
	
	@Override
	public String getAktionString(){return "-";}
	
	@Override
	public String toString() {
		//return "Situation: " + situation.toString() + " Aktion: " + aktion.toString();
		return "Situations-Aktion: " + id;
	}
	
	//TODO funktioniert noch nicht wie gewollt, da er bei der Aktionsauswahl irgendwie alle als gleich betrachtet. Und dadurch immer das gleiche tut. 
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof VG_Situation_Aktion)) {
			return false;
		}
		VG_Situation_Aktion other = (VG_Situation_Aktion) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}
	
	
	
}
