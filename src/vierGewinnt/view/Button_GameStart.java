package vierGewinnt.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import vierGewinnt.Controller;
import vierGewinnt.enums.GameModus;

/**
 * Buttons auf dem MainPanel zum starten
 * des gewünschten Modi
 */

public class Button_GameStart extends JButton implements ActionListener {
	private static final long serialVersionUID = 2006396356986604673L;
	
	
	private GameModus gameModus = null;
	
	public Button_GameStart(String caption, GameModus gameModus) {
		super(caption);
		this.gameModus = gameModus;
		addActionListener(this);
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Controller.getInstance().startGame(gameModus);
	}

}
