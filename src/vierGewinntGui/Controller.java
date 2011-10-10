package vierGewinntGui;


import java.lang.reflect.Method;

import javax.swing.SwingUtilities;

import vierGewinntGui.enums.Feld;
import vierGewinntGui.enums.GameModus;
import vierGewinntGui.view.View;


/**
 * Steuerung der GUI und des Spielablaufs,
 * Singleton-Klasse
 * @author Daniel
 *
 */


public class Controller {
	
	
	private Model model=null;
	private View view = null;
	
	private static Controller cInstance=null;
	private Feld spieler = null;

	private ComputerPlayerRandom cp1 = null;
	private ComputerPlayerRandom cp2 = null;
	
	
	private Controller() {
		view = new View();
		model=new Model();
	}
	
	public static synchronized Controller getInstance(){
		if(cInstance==null){
			cInstance= new Controller();
		}
		return cInstance;
	}
	
	private void switchPlayers() {
		
		if(spieler==Feld.spieler1){
			spieler=Feld.spieler2;
			view.displayPlayer(2);
			if(cp2!=null){
				int cul=cp2.makeMove(model.getIntMatrix());
				steinEinwerfenIn(cul);
			}
		}
		else{
			spieler=Feld.spieler1;
			view.displayPlayer(1);
			if(cp1!=null){
				int cul=cp1.makeMove(model.getIntMatrix());
				steinEinwerfenIn(cul);
				
			}
		}
		view.refresh();

	}
	
	public void steinEinwerfenIn(int reihe){
		int rest=-1;
		try {
			rest = model.steinEinwerfenIn(reihe, spieler);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(rest>0 && model.spielZuende()==-1){
			switchPlayers();
		}
		else if(cp1!= null && cp2!= null){
			if(model.spielZuende()>=0){
				view.refresh();
				view.showMessage("Computer-Game beendet!");
				view.showMainPanel();
			}
			
		}
		else if(model.spielZuende()>0){
			view.refresh();
			view.showMessage("gewonnen");
			reset();
		}
		else if(model.spielZuende()==0){
			view.refresh();
			view.showMessage("unentschieden!");
			reset();
		}
		view.refresh();
	}

	private void reset() {
		model.reset();
		spieler=Feld.spieler1;
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

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public void startGame(GameModus gameModus) {
		
		reset();
		cp1=null;
		cp2=null;
		
		switch (gameModus) {
		
		case playerVsPlayer:
			view.showGamePanel();
			break;
		
		case playerVsMachine:
			view.showGamePanel();
			cp2= new ComputerPlayerRandom();
			break;
			
		case machineVsMachine:
			view.showGamePanel();
			view.refresh();
			cp1= new ComputerPlayerRandom();
			cp2= new ComputerPlayerRandom();
			int cul=cp1.makeMove(getModel().getIntMatrix());
			steinEinwerfenIn(cul);
			break;
			
		case rlTraining:
			invokeRLTraining();
			
			break;

		default:
			break;
		}
		
	}
	
	private void invokeRLTraining() {

		Class<?> c = null;
		try {
			c = Class.forName("jade.Boot");
			Class<?>[] argTypes = new Class[] { String[].class };
			Method main = c.getDeclaredMethod("main", argTypes);
			String[] mainArgs = { 	"-agents",
				"a1:Agent.Agent_Frame;a2:Agent.Agent_Frame;umwelt:vierGewinntKI.VG_Umwelt"	};
			main.invoke(null, (Object) mainArgs);

		} catch (Exception e) {e.printStackTrace();}
	}

}
