package vierGewinntGui.view;

import java.awt.event.ActionEvent;

/**
 * Buttons für die Spalten 1-7
 * zum Stein einwerfen
 */

import java.awt.event.ActionListener;

import javax.swing.JButton;

import vierGewinntGui.Controller;

public class Button_Cul extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private int culNr=0;
	private Controller ctrl= Controller.getInstance();
	
	public Button_Cul(int culNr) {
		this.culNr=culNr;
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ctrl.steinEinwerfenIn(culNr);
	}

}
