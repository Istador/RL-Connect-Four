package vierGewinntGui;

import java.util.Random;
/**
 * Computergegner der einen zufälligen Zug macht
 * @author Daniel
 *
 */

public class ComputerPlayerRandom {
	
	private static final int PLAYER_DELAY = 20; // ms
	
	// return = reihe (0-6)
	public int makeMove(int[][] spielfeld) {
		Random rand = new Random();
		int cul = rand.nextInt(7);
		int count=0;
		while(spielfeld[cul][5]!=0){
			cul = rand.nextInt(7);
			if(count==1000){
				//System.err.println("Spiel muss schon zuende sein");
				break;
			}
			count++;
		}
		try {Thread.sleep(PLAYER_DELAY);} catch (InterruptedException e) {e.printStackTrace();}

		return cul;
		

	}

}
