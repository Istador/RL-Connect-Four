package vierGewinntGui.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vierGewinntGui.Controller;
import vierGewinntGui.enums.GameModus;

/**
 * Buttons auf dem MainPanel zum starten
 * des gewünschten Modi
 * @author Daniel
 *
 */

public class Button_GameStart extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Controller ctrl= Controller.getInstance();
	
	private GameModus gameModus = null;
	
	public Button_GameStart(String caption,GameModus gameModus) {
		super(caption);
		this.gameModus=gameModus;
		addActionListener(this);
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ctrl.startGame(gameModus);
	}

}
