package Agent;

import java.util.HashMap;



public abstract  class  A_Aktion implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	public abstract int defeniere_ID();
	public abstract HashMap<String, A_Situation> fuehre_Aus(HashMap<String, A_Situation> situation, String agent);
		int id;
		public int getId() {
			return id;
		}
		
		//public abstract A_Situation fuehre_Aus(A_Situation situation);
		public void setId(int id) {
			this.id = id;
		}
		public A_Aktion(int id) {
				this.id = id;
		}
		public boolean equals(Object arg0) {
			if (arg0 instanceof A_Aktion) {
				A_Aktion tmp = (A_Aktion) arg0;
				if (tmp.getId() == this.getId())
					return true;
				else
					return false;
			} else
				return false;
		}
		@Override
		public String toString() {
			return "Gewählte Aktion: " + getId();
		
		}
		public int definiere_ID() {
			// TODO Auto-generated method stub
			return 0;
		}

	
}
