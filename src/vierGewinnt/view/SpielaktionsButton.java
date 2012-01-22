package vierGewinnt.view;

import java.awt.event.ActionEvent;

/**
 * Buttons für die Spalten 1-7
 * zum Stein einwerfen
 */

import java.awt.event.ActionListener;

import javax.swing.JButton;

import vierGewinnt.Controller;

/**
 * Buttons die auf dem Spielfd unter jeder Reihe sind, um einen Stein
 * in die jeweilige Reihe einzuwerfen.
 */
public class SpielaktionsButton extends JButton implements ActionListener {
	private static final long serialVersionUID = 8662185202242907889L;
	
	
	private byte culNr;
	
	public SpielaktionsButton(byte culNr) {
		super("einwerfen");
		this.culNr=culNr;
		this.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Controller.getInstance().steinEinwerfenIn(culNr);
	}

}
