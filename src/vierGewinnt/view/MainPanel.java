package vierGewinnt.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import static vierGewinnt.enums.GameModus.*;
import vierGewinnt.KI.VG_Agent_Frame;
import vierGewinnt.KI.VG_Umwelt;

/**
 * HauptmenÃ¼
 * 
 * Das Hauptfenster, das direkt beim Start des Programms angezeigt wird
 * zum AuswÃ¤hlen der Spielmodi und des RL-Algorithmus
 */
public class MainPanel extends JPanel implements ActionListener, DocumentListener {
	private static final long serialVersionUID = 4870309992447084475L;
	
	/**
	 * Spiel starten Buttons
	 */
	private Button_GameStart butt_PlayerVsPlayer = new Button_GameStart("Player Vs Player", playerVsPlayer);
	private Button_GameStart butt_PlayerVsMachine = new Button_GameStart("Player Vs Machine", playerVsMachine);
	private Button_GameStart butt_MachineVsMachine = new Button_GameStart("Machine Vs Machine", machineVsMachine);
	private Button_GameStart butt_RLTraining = new Button_GameStart("RL Training", rlTraining);
	
	/**
	 * Algorithmus Auswahl
	 */
	private ButtonGroup group = new ButtonGroup();
	private JPanel radioPanel = new JPanel(new GridLayout(0, 1));
	private JRadioButton sarsaRB = new JRadioButton("SARSA");
	private JRadioButton sarsalRB = new JRadioButton("SARSA(λ)");
	private JRadioButton qlRB = new JRadioButton("Q-Learning");
	private JRadioButton qllRB = new JRadioButton("Q-Learning(λ)");
	
	/**
	 * Algorithmus Parameter
	 */
	private JPanel varPanel = new JPanel(new GridLayout(0, 1));
	private JTextField var_e = new JTextField("0.2");
	private JTextField var_a = new JTextField("0.2");
	private JTextField var_g = new JTextField("0.2");
	private JTextField var_l = new JTextField("0.2");
	private JPanel labPanel = new JPanel(new GridLayout(0, 1));
	private JLabel l_e = new JLabel("epsilon (exploitation rate)");
	private JLabel l_a = new JLabel("alpha (learning rate)");
	private JLabel l_g = new JLabel("gamma (discount factor)");
	private JLabel l_l = new JLabel("lambda (trace decay)");
	
	private JTextField var_wait = new JTextField("100");
	private JLabel l_wait = new JLabel("wait time (ms)");
	
	public MainPanel(int width, int height, final GamePanel gp,final View v) {
		setSize(width, height);
		setLayout(null);
		
		int buttAbstand = 50;
		int buttWidth = 150;
		int buttHeight = 30;
		int buttLeft = (width/2)-(buttWidth/2);
		int buttTop = (height-25)/4-(buttAbstand+buttHeight)/2+25;
		
		/**
		 * Spiel starten Buttons
		 */
		
		// btt_PlayerVsPlayer
		butt_PlayerVsPlayer.setBounds(buttLeft, buttTop, buttWidth, buttHeight);
		add(butt_PlayerVsPlayer);
		buttTop += buttAbstand;
		
		// btt_PlayerVsMachine
		butt_PlayerVsMachine.setBounds(buttLeft, buttTop, buttWidth, buttHeight);
		add(butt_PlayerVsMachine);
		buttTop += buttAbstand;
		
		// btt_MachineVsMachine
		butt_MachineVsMachine.setBounds(buttLeft, buttTop, buttWidth, buttHeight);
		add(butt_MachineVsMachine);
		buttTop += buttAbstand;
		
		//btt_RLTraining
		butt_RLTraining.setBounds(buttLeft, buttTop, buttWidth, buttHeight);
		add(butt_RLTraining);
		buttTop += buttAbstand;
	
		
		
		
		
        /**
         * Algorithmus Auswahl - Radio Buttons
         */
		
		//ActionCommand
        sarsaRB.setActionCommand("1");
        sarsalRB.setActionCommand("2");
        qlRB.setActionCommand("3");
        qllRB.setActionCommand("4");
        sarsaRB.setSelected(true);
        
        //gruppiere Radio Buttons
        group.add(sarsaRB);
        group.add(sarsalRB);
        group.add(qlRB);
        group.add(qllRB);

        //Action Listener
        sarsaRB.addActionListener(this);
        sarsalRB.addActionListener(this);
        qlRB.addActionListener(this);
        qllRB.addActionListener(this);

        //Panel fÃ¼r die Radio Buttons
        radioPanel.add(sarsaRB);
        radioPanel.add(sarsalRB);
        radioPanel.add(qlRB);
        radioPanel.add(qllRB);
        radioPanel.setBounds(0,0,200,100);
        add(radioPanel, BorderLayout.LINE_START);
		
        
        
        
        
        
        
        
        /**
         * Algorithmen Parameter
         */
        //Listener
        var_e.getDocument().addDocumentListener(this);
        var_a.getDocument().addDocumentListener(this);
        var_g.getDocument().addDocumentListener(this);
        var_l.getDocument().addDocumentListener(this);
        var_wait.getDocument().addDocumentListener(this);
        
        //Variablen - aktivieren / zu Panel hinzufÃ¼gen
        var_a.setEnabled(true);
        var_e.setEnabled(true);
        var_g.setEnabled(true);
        var_l.setEnabled(false);
        var_wait.setEnabled(true);
        varPanel.add(var_e);	labPanel.add(l_e);
        varPanel.add(var_a);	labPanel.add(l_a);
	    varPanel.add(var_g);	labPanel.add(l_g);
        varPanel.add(var_l);	labPanel.add(l_l);
        varPanel.add(var_wait);	labPanel.add(l_wait);
        varPanel.setBounds(0,150,100,100);
        labPanel.setBounds(110,150,200,100);
        add(varPanel, BorderLayout.LINE_START);
        add(labPanel, BorderLayout.LINE_START);
		setSize(width,height);
	}

	

	/**
	 * Button Klick
	 */
	@Override public void actionPerformed(ActionEvent e) {
		switch(new Integer(e.getActionCommand())){
			case 1: //SARSA
				VG_Agent_Frame.algorithmus = 1;
				var_e.setEnabled(true);
				var_a.setEnabled(true);
				var_g.setEnabled(true);
				var_l.setEnabled(false);
				break;
			case 2: //SARSA Lambda
				VG_Agent_Frame.algorithmus = 2;
				var_e.setEnabled(true);
				var_a.setEnabled(true);
				var_g.setEnabled(true);
				var_l.setEnabled(true);
				break;
			case 3: //Q-Learning
				VG_Agent_Frame.algorithmus = 3;
				var_e.setEnabled(true);
				var_a.setEnabled(true);
				var_g.setEnabled(true);
				var_l.setEnabled(false);
				break;
			case 4: //Q-Learning Lambda
				VG_Agent_Frame.algorithmus = 4;
				var_e.setEnabled(true);
				var_a.setEnabled(true);
				var_g.setEnabled(true);
				var_l.setEnabled(true);
				break;
		}
	}
	
	
	/**
	 * Variablen Ã„nderungen
	 */
	public void changedUpdate(DocumentEvent e) {update();}
	public void removeUpdate(DocumentEvent e) {update();}
	public void insertUpdate(DocumentEvent e) {update();}
	public void update() {
 		try{ VG_Agent_Frame.epsilon = new Double(var_e.getText()); } catch(Exception e){}
 		try{ VG_Agent_Frame.alpha = new Double(var_a.getText()); } catch(Exception e){}
 		try{ VG_Agent_Frame.gamma = new Double(var_g.getText()); } catch(Exception e){}
 		try{ VG_Agent_Frame.lambda = new Double(var_l.getText()); } catch(Exception e){}
 		try{ VG_Umwelt.blocktime = new Long(var_wait.getText()); } catch(Exception e){}
	}

}
