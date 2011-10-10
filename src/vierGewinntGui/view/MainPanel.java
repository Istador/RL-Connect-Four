package vierGewinntGui.view;

import javax.swing.JPanel;

/**
 * Hauptmenü
 */

import vierGewinntGui.enums.GameModus;

public class MainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Button_GameStart btt_PlayerVsPlayer = null;
	private Button_GameStart btt_PlayerVsMachine = null;
	private Button_GameStart btt_MachineVsMachine = null;
	private Button_GameStart btt_RLTraining = null;
	
	public MainPanel(int x, int y,final GamePanel gp,final View v) {
		int buttonSizeX=150;
		int buttonSizeY=30;
		setSize(x,y);
		setLayout(null);
		
		final int distance = 50;
		
		
		// btt_PlayerVsPlayer
		btt_PlayerVsPlayer = new Button_GameStart("Player Vs Player",GameModus.playerVsPlayer);
		btt_PlayerVsPlayer.setBounds((x/2)-(buttonSizeX/2), (y/3), buttonSizeX,buttonSizeY);
		add(btt_PlayerVsPlayer);
		
		// btt_PlayerVsMachine
		btt_PlayerVsMachine = new Button_GameStart("Player Vs Machine",GameModus.playerVsMachine);
		btt_PlayerVsMachine.setBounds((x/2)-(buttonSizeX/2), (y/3)+distance, buttonSizeX,buttonSizeY);
		add(btt_PlayerVsMachine);
		
		// btt_MachineVsMachine
		btt_MachineVsMachine = new Button_GameStart("Machine Vs Machine",GameModus.machineVsMachine);
		btt_MachineVsMachine.setBounds((x/2)-(buttonSizeX/2), (y/3)+(distance*2), buttonSizeX,buttonSizeY);
		add(btt_MachineVsMachine);
		
		//btt_RLTraining
		btt_RLTraining = new Button_GameStart("RL Training",GameModus.rlTraining);
		btt_RLTraining.setBounds((x/2)-(buttonSizeX/2), (y/3)+(distance*3), buttonSizeX,buttonSizeY);
		add(btt_RLTraining);
		
		
		
		setSize(x,y);
		
		
		
//		setLayout(new BorderLayout());
//		btt_PlayerVsPlayer.setPreferredSize(new Dimension(100, 30));
//		btt_PlayerVsPlayer.setMaximumSize(new Dimension(100, 30));
//		add(btt_PlayerVsPlayer,BorderLayout.CENTER);
		
		
	}
	

}
