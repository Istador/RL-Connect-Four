package Agent;

import java.util.Map;

/**
 * Hilfsmethoden f�r das RL-Framework, um wiederkehrende und n�tzliche Methoden
 * an einer Stelle zu sammeln.
 * @author RCL
 */

public class A_Utility {
	
	/**
	 * erhalte aus einer Map den Boolschen Value-Wert zu einem Key.
	 * Wenn Key nicht vorhanden, gib false zur�ck.
	 */
	public static boolean getBoolFalse(Map<A_Situation_Aktion, Boolean> map, A_Situation_Aktion key){
		if(map==null || key==null) return false;
		Boolean b = map.get(key);
		return b!=null && b;
	}
	
	
	/**
	 * erhalte aus einer Map den Boolschen Value-Wert zu einem Key.
	 * Wenn Key nicht vorhanden, gib true zur�ck.
	 */
	public static boolean getBoolTrue(Map<A_Situation_Aktion, Boolean> map, A_Situation_Aktion key){
		if(map==null || key==null) return true;
		Boolean b = map.get(key);
		return b==null || b;
	}
	
	
	/**
	 * erhalte aus einer Map den Double Value-Wert zu einem Key.
	 * Wenn Key nicht vorhanden, gib 0.0 zur�ck.
	 */
	public static double getDouble(Map<A_Situation_Aktion, Double> map, A_Situation_Aktion key){
		if(map==null || key==null) return 0.0;
		Double d = map.get(key);
		return (d==null ? 0.0 : d);
	}
	
	
	/**
	 * F�ge einen Double-Value in eine Map ein,
	 * nur wenn der Value-Wert nicht 0.0 ist.
	 */
	public static void putDouble(Map<A_Situation_Aktion, Double> map, A_Situation_Aktion key, Double value){
		if(Double.compare(value,0.0)==0) return;
		if(Double.compare(value,-0.0)==0) return;
		map.put(key,value);
	}
}
