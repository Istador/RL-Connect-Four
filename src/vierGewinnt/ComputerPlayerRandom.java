package vierGewinnt;

import java.util.Random;

/**
 * Computergegner der einen zufälligen Zug macht
 */
public class ComputerPlayerRandom {
	
	/**
	 * Liefert eine zufällige Aktion, die noch möglich ist (also einen Index einer noch nicht vollen Spalte)
	 * @param bs ein nicht volles Spielfeld-Array (sonst Endlosschleife!)
	 * @return einen Wert zwischen 0 und 6 der den entsprechenden Spaltenindex darstellt in den geworfen werden soll
	 */
	public static byte makeMove(byte[][] bs) {
		Random rand = new Random();
		byte cul = (byte) rand.nextInt(7);
		//solange die zufäälig gewählte Spalte voll ist (verbotene Aktion)
		while(bs[cul][0]!=0){
			cul = (byte) rand.nextInt(7);
		}
		try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
		return cul;
	}

}
