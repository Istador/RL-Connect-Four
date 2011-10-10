package Agent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Leistungselement {
	public void update_Episode(){
		vorrige_Episode = aktuelle_Episode;
	}
	boolean explorierend = false;
	public boolean isExplorierend() {
		return explorierend;
	}
	public void setExplorierend(boolean explorierend) {
		this.explorierend = explorierend;
	}
	int episode=0;
	int aktuelle_Episode=0;
	int vorrige_Episode=0;
	public int getAktuelle_Episode() {
		return aktuelle_Episode;
	}
	public void setAktuelle_Episode(int aktuelle_Episode) {
		
		this.aktuelle_Episode = aktuelle_Episode;
	
	}
	public int getVorrige_Episode() {
		return vorrige_Episode;
	}
	public void setVorrige_Episode(int vorrige_Episode) {
		this.vorrige_Episode = vorrige_Episode;
	}
	public int getEpisode() {
		return episode;
	}
	public void setEpisode(int episode) {
		this.episode = episode;
	}
	HashMap<A_Situation_Aktion, Double> e_Werte= new HashMap<A_Situation_Aktion, Double>();
	public HashMap<A_Situation_Aktion, Double> getE_Werte() {
		return e_Werte;
	}
	public void aktualisiere_Histroy(){
			histroy.add(new A_Situation_Aktion_Mit_Episode(neuste_Situation, aktuelle_Aktion, aktuelle_Episode));
	}
	public void setE_Werte(HashMap<A_Situation_Aktion, Double> e_Werte) {
		this.e_Werte = e_Werte;
	}
	public HashMap<A_Situation_Aktion, Boolean> getVerbotene_Aktionen() {
		return verbotene_Aktionen;
	}
	public void setVerbotene_Aktionen(
			HashMap<A_Situation_Aktion, Boolean> verbotene_Aktionen) {
		this.verbotene_Aktionen = verbotene_Aktionen;
	}
	A_Aktion aktuelle_Aktion = null;
	A_Situation neuste_Situation = null;

	A_Situation_Aktion letzte_A_S = null;

	ArrayList<A_Situation> bekannte_Situationen = new ArrayList<A_Situation>();
	ArrayList<A_Aktion> aktionen;
	HashMap<A_Situation_Aktion, Double> werte = new HashMap<A_Situation_Aktion, Double>();
	HashMap<A_Situation_Aktion, Boolean> verbotene_Aktionen = new HashMap<A_Situation_Aktion, Boolean>();
	ArrayList<A_Situation_Aktion_Mit_Episode> histroy = new ArrayList<A_Situation_Aktion_Mit_Episode>();
	public void verbotene_Aktion(A_Situation situation, A_Aktion aktion ){
		verbotene_Aktionen.put(new A_Situation_Aktion(situation, aktion), true);
	}
	public void verbotene_Aktionen_Reset(){
		verbotene_Aktionen = new HashMap<A_Situation_Aktion, Boolean>();
	}
	public void verbotene_Reset(){
		
		for(A_Situation_Aktion tmp : verbotene_Aktionen.keySet()){
			//System.err.println("RESETE VERBOTENE");
			verbotene_Aktionen.put(tmp, false);}
	}
	public ArrayList<A_Situation_Aktion_Mit_Episode> getHistroy() {
		return histroy;
	}
	public void setHistroy(ArrayList<A_Situation_Aktion_Mit_Episode> histroy) {
		this.histroy = histroy;
	}
	public A_Situation getNeuste_Situation() {
		return neuste_Situation;
	}
	public void setNeuste_Situation(A_Situation neuste_Situation) {
		this.neuste_Situation = neuste_Situation;
	}
	public Leistungselement(ArrayList<A_Aktion> aktionen) {
			this.aktionen = aktionen;
	}
	public A_Aktion getAktuelle_Aktion() {
		return aktuelle_Aktion;
	}
	public void setAktuelle_Aktion(int neue_Aktion) {
		this.aktuelle_Aktion = aktionen.get(neue_Aktion);
		//System.err.println("Setze in Methode " + neue_Aktion);
	}
	public ArrayList<A_Situation> getbekannte_Situationen() {
		return bekannte_Situationen;
	}
	public void setbekannte_Situationen(ArrayList<A_Situation> bekannte_Situationen) {
		this.bekannte_Situationen = bekannte_Situationen;
	}

	public HashMap<A_Situation_Aktion, Double> getWerte() {
		return werte;
	}
	public void setWerte(HashMap<A_Situation_Aktion, Double> werte) {
		this.werte = werte;
	}

	public void berechne_Neue_Aktion(A_Situation situation){
		aktuelle_Aktion =this.gib_Beste_Aktion(situation);
	}
	public A_Aktion gib_Beste_Aktion(A_Situation situation){
		A_Aktion tmp_A = null;
		try {
			
	
		System.out.println("Berechnet neue Aktion");
		neuste_Situation = situation;
		Double tmp_reward=0.0;
		
	//	aktuelle_Aktion = aktionen.get(0);
		boolean durch = false;
		ArrayList<Boolean> liste = new ArrayList<Boolean>();
		for(int i=0;i<aktionen.size();i++){
			liste.add(new Boolean(false));
			
		}
		Random rnd = new Random();;
		
		int i;
			while(!durch){
				i=rnd.nextInt() % (aktionen.size());
				if(i<0)
					i=i*-1;
				
				while(liste.get(i)){
					i=rnd.nextInt() % (aktionen.size());
					if(i<0)
						i=i*-1;
					
				}
			
				liste.set(i, true);
				A_Situation_Aktion tmp = new A_Situation_Aktion(situation, aktionen.get(i));
				if(!verbotene_Aktionen.get(new A_Situation_Aktion(situation, aktionen.get(i))))	{
				
				if(werte.get(tmp) > tmp_reward){
					tmp_reward = werte.get(new A_Situation_Aktion(situation, aktuelle_Aktion));
					aktuelle_Aktion = aktionen.get(i);
					bekannte_Situationen.add(situation);
					}
	else if(werte.get(tmp).equals(tmp_reward) || tmp_A == null ){
								rnd = new Random();
					int zahl = rnd.nextInt() % 100;
					if(zahl < 0)
						zahl = zahl *-1;
					if(zahl >50 || tmp_A == null){
						tmp_reward = werte.get(new A_Situation_Aktion(situation,  aktionen.get(i)));
						tmp_A = aktionen.get(i);
						bekannte_Situationen.add(situation);
					}
				}
				}
				for(int j=0;j<=liste.size()-1;j++){
					if(!liste.get(j))
						break;
					else if(j == aktionen.size()-1)
						durch = true;
				}
			
		}
			
		//histroy.add(new A_Situation_Aktion(situation, aktuelle_Aktion));
		} catch (Exception e) {
			System.err.println("GIBTER ER AUS!!! " );
			
			System.err.println(e);
		}if(tmp_A == null)
			System.err.println("DAS IST NULL");
		return tmp_A;
	}
	 
	public ArrayList<A_Aktion> getAktionen() {
		return aktionen;
	}
	public void setAktionen(ArrayList<A_Aktion> aktionen) {
		this.aktionen = aktionen;
	}
	public void verandere_Aktion(A_Aktion aktion){
		aktuelle_Aktion = aktion;
	}
	public void neue_Situation(A_Situation situation){
		neuste_Situation = situation;
		if(!bekannte_Situationen.contains(situation)){
			bekannte_Situationen.add(situation);
			for(int i=0; i< aktionen.size();i++){
				werte.put(new A_Situation_Aktion(situation, aktionen.get(i)), 0.0);
				e_Werte.put(new A_Situation_Aktion(situation, aktionen.get(i)), 0.0);
				verbotene_Aktionen.put(new A_Situation_Aktion(situation, aktionen.get(i)), false);
			}
		}
	}
	public void erste_Aktion(A_Situation situation){
		System.out.println("ERSTE");
		int i = (int) (Math.random()* aktionen.size())-1;
		while(verbotene_Aktionen.get(new A_Situation_Aktion(situation, aktionen.get(i)))){
			i = (int) (Math.random()* aktionen.size()-1);
			System.out.println("i: " + i +" geht nicht");
		}
	//	System.err.println("Setze " + i);
			aktuelle_Aktion =aktionen.get(i);
	//	histroy.add(new A_Situation_Aktion(situation, aktuelle_Aktion));

	//	histroy.get(histroy.size()-1).setEpisode(episode);
	}
}
