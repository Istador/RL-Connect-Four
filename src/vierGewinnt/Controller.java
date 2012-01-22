package vierGewinnt;

import javax.swing.SwingUtilities;

import vierGewinnt.enums.GameModus;
import vierGewinnt.view.View;


/**
 * Steuerung der GUI und des Spielablaufs,
 * Singleton-Klasse
 */


public class Controller {
	
	
	private Model model = null;
	private View view = null;
	
	private byte spieler = 0;

	private boolean cp1 = false; //Spieler 1 von KI gesteuert
	private boolean cp2 = false; //Spieler 2 von KI gesteuert
	
	
	
	
	/**
	 * Singleton
	 */
	private static Controller instance=null;
	
	public static synchronized Controller getInstance(){
		if(instance==null){
			instance= new Controller();
		}
		return instance;
	}
	
	private Controller() {
		view = new View();
		model = new Model();
	}
	
	
	
	/**
	 * Spielertausch
	 * KI-Aktion, wenn nötig
	 * TODO: Stack Overflow!
	 */
	private void switchPlayers() {
		//wenn spieler 1 dann wechsel zu spieler 2
		if(spieler==1){
			spieler = 2;
			view.displayPlayer(2);
			if(cp2){
				steinEinwerfenIn(ComputerPlayerRandom.makeMove(getModel().getMatrix()));
			}
		}
		//wenn spieler 2 dann wechsel zu spieler 1
		else{
			spieler = 1;
			view.displayPlayer(1);
			if(cp1){
				steinEinwerfenIn(ComputerPlayerRandom.makeMove(getModel().getMatrix()));
			}
		}
		view.refresh();
	}
	
	
	/**
	 * Stein einwerfen in eine Reihe, und gucken ob das Spiel nun beendet
	 */
	public void steinEinwerfenIn(byte reihe){		
		//Stein einwerfen
		model.steinEinwerfenIn(reihe, spieler);
		
		//GUI aktualisieren
		view.refresh();
		
		//herausfinden, ob Spiel zuende
		int istzuende = Model.spielZuende(model.getMatrix());
		
		//spiel noch nicht zuende
		if(istzuende == -1){
			switchPlayers();
		}
		// einer der Computerspieler hat gewonnen
		else if(cp1 && cp2 && istzuende>=0){
			view.showMessage("Computer-Game beendet! "+istzuende+" hat gewonnen.");
			view.showMainPanel();
			reset();
			//Computer 1 auslösen
			//steinEinwerfenIn(ComputerPlayerRandom.makeMove(getModel().getIntMatrix()));
		}
		//Spieler 1 hat gewonnen
		else if(istzuende==1){
			view.showMessage("Spieler 1 hat gewonnen");
			reset();
		}
		//Spieler 2 hat gewonnen
		else if(istzuende==2){
			if(cp2)
				view.showMessage("Computer hat gewonnen");
			else
				view.showMessage("Spieler 2 hat gewonnen");
			reset();
		}
		//Unentschieden
		else if(istzuende==0){
			view.showMessage("unentschieden!");
			reset();
		}
		view.refresh();
	}

	private void reset() {
		model.reset(); //spielfeld zurücksetzen
		spieler = 1; //immer spieler 1 (gelb) dran
		view.displayPlayer(1);
	}

	public void showView() {
		try {
			//SwingUtilities.invokeAndWait(view);
			SwingUtilities.invokeLater(view);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Model getModel() {return model;}
	//public void setModel(Model model) {this.model = model;}

	
	/**
	 * Starte Spiel (Button-Klick des Hauptfensters)
	 */
	public void startGame(GameModus gameModus) {
		reset(); //spielfeld zurücksetzen/initialisieren
		switch (gameModus) {
			//Spieler gegen Spieler
			case playerVsPlayer:
				view.showGamePanel(); //GUI zeigen
				cp1 = false;
				cp2 = false;
				break;
			//Spieler gegen KI
			case playerVsMachine:
				view.showGamePanel(); //GUI zeigen
				cp1 = false;
				cp2 = true; // Spieler 2 vom Computer steuern
				break;
			//KI gegen KI
			case machineVsMachine:
				view.showGamePanel(); //GUI zeigen
				cp1= true; // Spieler 1 vom Computer steuern
				cp2= true; // Spieler 2 vom Computer steuern
				steinEinwerfenIn(ComputerPlayerRandom.makeMove(getModel().getMatrix()));
				break;
			//Reinforcment Learning
			case rlTraining:
				invokeRLTraining();
				break;
			//ungültig
			default:
				break;
		}
	}
	
	/**
	 * Reinforcment Learning starten
	 * JADE main-Methode starten, mit den VierGewinnt 
	 * spezifischen Klassen als Agenten
	 * 
	 * Agenten: a1, a2, umwelt
	 */
	private void invokeRLTraining() {
		jade.Boot.main(new String[]{
				"-agents",
				"a1:vierGewinnt.KI.VG_Agent_Frame;"
				+"a2:vierGewinnt.KI.VG_Agent_Frame;"
				+"umwelt:vierGewinnt.KI.VG_Umwelt"
		});
	}

}
