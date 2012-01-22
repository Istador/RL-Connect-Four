package vierGewinnt.KI;

import java.util.*;
import junit.framework.*;
import static vierGewinnt.KI.VG_Utility.*;

public class VG_Utility_Test extends TestCase {
	
	
	
	
	/** Alles Leer
	 *  binär:		0 000000000 000000000 000000000 000000000 000000000 000000000 000000000
	 *  dezimal:	0
	 */
	final byte[][] alles_leer = new byte[7][6];
	final long alles_leer_id = 0;
	
	
	
	
	/** Alles Gelb
	 * binär:		0 110111111 110111111 110111111 110111111 110111111 110111111 110111111
	 * dezimal:		8068194325780987839
	 */
	final byte[][] alles_gelb = new byte[][] {
			{1, 1, 1, 1, 1, 1},		// 110 111111
			{1, 1, 1, 1, 1, 1},		// 110 111111
			{1, 1, 1, 1, 1, 1},		// 110 111111
			{1, 1, 1, 1, 1, 1},		// 110 111111
			{1, 1, 1, 1, 1, 1},		// 110 111111
			{1, 1, 1, 1, 1, 1},		// 110 111111
			{1, 1, 1, 1, 1, 1}		// 110 111111
	}; 
	final long alles_gelb_id = 8068194325780987839l;
	
	
	
	
	/** Alles Rot
	 * binär:		0 110000000 110000000 110000000 110000000 110000000 110000000 110000000
	 * dezimal:		6931066266442727808
	 */
	final byte[][] alles_rot = new byte[][] {
			{2, 2, 2, 2, 2, 2},		// 110 000000
			{2, 2, 2, 2, 2, 2},		// 110 000000
			{2, 2, 2, 2, 2, 2},		// 110 000000
			{2, 2, 2, 2, 2, 2},		// 110 000000
			{2, 2, 2, 2, 2, 2},		// 110 000000
			{2, 2, 2, 2, 2, 2},		// 110 000000
			{2, 2, 2, 2, 2, 2}		// 110 000000
	}; 
	final long alles_rot_id = 6931066266442727808l;
	
	
	
	
	/** Rot/Gelb Wechselnd
	 * binär:		0 110101010 110101010 110101010 110101010 110101010 110101010 110101010
	 * dezimal:		7689151639334901162
	 */
	final byte[][] rot_gelb_wechselnd = new byte[][] { //mit rot unten beginnend
			{1, 2, 1, 2, 1, 2},		// 110 101010
			{1, 2, 1, 2, 1, 2},		// 110 101010
			{1, 2, 1, 2, 1, 2},		// 110 101010
			{1, 2, 1, 2, 1, 2},		// 110 101010
			{1, 2, 1, 2, 1, 2},		// 110 101010
			{1, 2, 1, 2, 1, 2},		// 110 101010
			{1, 2, 1, 2, 1, 2}		// 110 101010
	}; 
	final long rot_gelb_wechselnd_id = 7689151639334901162l;
	
	
	
	
	/** Gelb/Rot Wechselnd
	 * binär:		0 110010101 110010101 110010101 110010101 110010101 110010101 110010101
	 * dezimal:		7310108952888814485
	 */
	final byte[][] gelb_rot_wechselnd = new byte[][] { //mit gelb unten beginnend
			{2, 1, 2, 1, 2, 1},		// 110 010101
			{2, 1, 2, 1, 2, 1},		// 110 010101
			{2, 1, 2, 1, 2, 1},		// 110 010101
			{2, 1, 2, 1, 2, 1},		// 110 010101
			{2, 1, 2, 1, 2, 1},		// 110 010101
			{2, 1, 2, 1, 2, 1},		// 110 010101
			{2, 1, 2, 1, 2, 1}		// 110 010101
	}; 
	final long gelb_rot_wechselnd_id = 7310108952888814485l;
	
	
	
	
	/** Rot/Gelb Abnehmend
	 * binär:		0 110101010 101001010 100001010 011000010 010000010 001000000 000000000
	 * dezimal:		7685762913281802240
	 */
	final byte[][] rot_gelb_abnehmend = new byte[][] { //von links nach rechts immer weniger Steine pro Reihe
			{1, 2, 1, 2, 1, 2},		// 110 101010
			{0, 2, 1, 2, 1, 2},		// 101 001010
			{0, 0, 1, 2, 1, 2},		// 100 001010
			{0, 0, 0, 2, 1, 2},		// 011 000010
			{0, 0, 0, 0, 1, 2},		// 010 000010
			{0, 0, 0, 0, 0, 2},		// 001 000000
			{0, 0, 0, 0, 0, 0}		// 000 000000
	}; 
	final long rot_gelb_abnehmend_id = 7685762913281802240l;
	
	
	
	
	/** Gelb/Rot Abnehmend
	 * binär:		0 110010101 101010101 100000101 011000101 010000001 001000001 000000000
	 * dezimal:		7307847229480665600
	 */
	final byte[][] gelb_rot_abnehmend = new byte[][] {  //von links nach rechts immer weniger Steine pro Reihe
			{2, 1, 2, 1, 2, 1},		// 110 010101
			{0, 1, 2, 1, 2, 1},		// 101 010101
			{0, 0, 2, 1, 2, 1},		// 100 000101
			{0, 0, 0, 1, 2, 1},		// 011 000101
			{0, 0, 0, 0, 2, 1},		// 010 000001
			{0, 0, 0, 0, 0, 1},		// 001 000001
			{0, 0, 0, 0, 0, 0}		// 000 000000
	}; 
	final long gelb_rot_abnehmend_id = 7307847229480665600l;
	
	
	
	
	/** Rot/Gelb Zunehmend
	 * binär:		0 000000000 001000000 010000010 011000010 100001010 101001010 110101010
	 * dezimal:		2260759453799850
	 */
	final byte[][] rot_gelb_zunehmend = new byte[][] {  //von links nach rechts immer mehr Steine pro Reihe
			{0, 0, 0, 0, 0, 0},		// 000 000000
			{0, 0, 0, 0, 0, 2},		// 001 000000
			{0, 0, 0, 0, 1, 2},		// 010 000010
			{0, 0, 0, 2, 1, 2},		// 011 000010
			{0, 0, 1, 2, 1, 2},		// 100 001010
			{0, 2, 1, 2, 1, 2},		// 101 001010
			{1, 2, 1, 2, 1, 2}		// 110 101010
	}; 
	final long rot_gelb_zunehmend_id = 2260759453799850l;
	
	
	
	
	/** Gelb/Rot Zunehmend
	 * binär:		0 000000000 001000001 010000001 011000101 100000101 101010101 110010101
	 * dezimal:		2295875507760021
	 */
	final byte[][] gelb_rot_zunehmend = new byte[][] { //von links nach rechts immer mehr Steine pro Reihe
			{0, 0, 0, 0, 0, 0},		// 000 000000
			{0, 0, 0, 0, 0, 1},		// 001 000001
			{0, 0, 0, 0, 2, 1},		// 010 000001
			{0, 0, 0, 1, 2, 1},		// 011 000101
			{0, 0, 2, 1, 2, 1},		// 100 000101
			{0, 1, 2, 1, 2, 1},		// 101 010101
			{2, 1, 2, 1, 2, 1}		// 110 010101
	}; 
	final long gelb_rot_zunehmend_id = 2295875507760021l;
		
	
	
	
	/** beispiel.png
	 * binär:		0 110101010 101010111 100000000 110010011 011000101 011000010 110011001
	 * dezimal:		7686219650993325465
	 */
	final byte[][] beispielpng = new byte[][] {
			{1, 2, 1, 2, 1, 2},		// grgrgr -> 110 101010
			{0, 1, 2, 1, 1, 1},		// fgrggg -> 101 010111
			{0, 0, 2, 2, 2, 2},		// ffrrrr -> 100 000000
			{2, 1, 2, 2, 1, 1},		// rgrrgg -> 110 010011
			{0, 0, 0, 1, 2, 1},		// fffgrg -> 011 000101
			{0, 0, 0, 2, 1, 2},		// fffrgr -> 011 000010
			{2, 1, 1, 2, 2, 1}		// rggrrg -> 110 011001
	}; 
	final long beispielpng_id = 7686219650993325465l;
	
	
	
	
	
	

	
	
	public void testGetId(){
		// das aus einem erwartetem Zustand die manuell berechneten ID herauskommt
		assert(alles_leer_id==getID(alles_leer));
		assert(alles_gelb_id==getID(alles_gelb));
		assert(alles_rot_id==getID(alles_rot));
		assert(rot_gelb_wechselnd_id==getID(rot_gelb_wechselnd));
		assert(gelb_rot_wechselnd_id==getID(gelb_rot_wechselnd));
		assert(rot_gelb_abnehmend_id==getID(rot_gelb_abnehmend));
		assert(gelb_rot_abnehmend_id==getID(gelb_rot_abnehmend));
		assert(rot_gelb_zunehmend_id==getID(rot_gelb_zunehmend));
		assert(gelb_rot_zunehmend_id==getID(gelb_rot_zunehmend));
		assert(beispielpng_id==getID(beispielpng));
	}
	
	
	public void testGetSpielfeld(){
		// das aus einer manuell berechneten ID der erwartete Zustand herauskommt
		assert(Arrays.deepEquals(alles_leer, getSpielfeld(alles_leer_id)));
		assert(Arrays.deepEquals(alles_gelb, getSpielfeld(alles_gelb_id)));
		assert(Arrays.deepEquals(alles_rot, getSpielfeld(alles_rot_id)));
		assert(Arrays.deepEquals(rot_gelb_wechselnd, getSpielfeld(rot_gelb_wechselnd_id)));
		assert(Arrays.deepEquals(gelb_rot_wechselnd, getSpielfeld(gelb_rot_wechselnd_id)));
		assert(Arrays.deepEquals(rot_gelb_abnehmend, getSpielfeld(rot_gelb_abnehmend_id)));
		assert(Arrays.deepEquals(gelb_rot_abnehmend, getSpielfeld(gelb_rot_abnehmend_id)));
		assert(Arrays.deepEquals(rot_gelb_zunehmend, getSpielfeld(rot_gelb_zunehmend_id)));
		assert(Arrays.deepEquals(gelb_rot_zunehmend, getSpielfeld(gelb_rot_zunehmend_id)));
		assert(Arrays.deepEquals(beispielpng, getSpielfeld(beispielpng_id)));
	}
	
	
	
	
	public void testGetSpielfeldGetId(){
		// das das Spielfeld durch beide Funktionen erhalten bleibt
		assert(Arrays.deepEquals(alles_leer, getSpielfeld(getID(alles_leer))));
		assert(Arrays.deepEquals(alles_gelb, getSpielfeld(getID(alles_gelb))));
		assert(Arrays.deepEquals(alles_rot, getSpielfeld(getID(alles_rot))));
		assert(Arrays.deepEquals(rot_gelb_wechselnd, getSpielfeld(getID(rot_gelb_wechselnd))));
		assert(Arrays.deepEquals(gelb_rot_wechselnd, getSpielfeld(getID(gelb_rot_wechselnd))));
		assert(Arrays.deepEquals(rot_gelb_abnehmend, getSpielfeld(getID(rot_gelb_abnehmend))));
		assert(Arrays.deepEquals(gelb_rot_abnehmend, getSpielfeld(getID(gelb_rot_abnehmend))));
		assert(Arrays.deepEquals(rot_gelb_zunehmend, getSpielfeld(getID(rot_gelb_zunehmend))));
		assert(Arrays.deepEquals(gelb_rot_zunehmend, getSpielfeld(getID(gelb_rot_zunehmend))));
		assert(Arrays.deepEquals(beispielpng, getSpielfeld(getID(beispielpng))));
	}
	
	
	
	public void testGetIdGetSpielfeld(){
		// das die ID durch beide Funktionen erhalten bleibt
		assert(alles_leer_id==getID(getSpielfeld(alles_leer_id)));
		assert(alles_gelb_id==getID(getSpielfeld(alles_gelb_id)));
		assert(alles_rot_id==getID(getSpielfeld(alles_rot_id)));
		assert(rot_gelb_wechselnd_id==getID(getSpielfeld(rot_gelb_wechselnd_id)));
		assert(gelb_rot_wechselnd_id==getID(getSpielfeld(gelb_rot_wechselnd_id)));
		assert(rot_gelb_abnehmend_id==getID(getSpielfeld(rot_gelb_abnehmend_id)));
		assert(gelb_rot_abnehmend_id==getID(getSpielfeld(gelb_rot_abnehmend_id)));
		assert(rot_gelb_zunehmend_id==getID(getSpielfeld(rot_gelb_zunehmend_id)));
		assert(gelb_rot_zunehmend_id==getID(getSpielfeld(gelb_rot_zunehmend_id)));
		assert(beispielpng_id==getID(getSpielfeld(beispielpng_id)));
	}
	
	
	
	
	public void testIstSpielfeldVoll(){
		// ist das spielfeld (alle Reihen) voll?
		assert(!istSpielfeldVoll(alles_leer));
		assert(istSpielfeldVoll(alles_gelb));
		assert(istSpielfeldVoll(alles_rot));
		assert(istSpielfeldVoll(rot_gelb_wechselnd));
		assert(istSpielfeldVoll(gelb_rot_wechselnd));
		assert(!istSpielfeldVoll(rot_gelb_abnehmend));
		assert(!istSpielfeldVoll(gelb_rot_abnehmend));
		assert(!istSpielfeldVoll(rot_gelb_zunehmend));
		assert(!istSpielfeldVoll(gelb_rot_zunehmend));
		assert(!istSpielfeldVoll(beispielpng));
		
		assert(!istSpielfeldVoll(alles_leer_id));
		assert(istSpielfeldVoll(alles_gelb_id));
		assert(istSpielfeldVoll(alles_rot_id));
		assert(istSpielfeldVoll(rot_gelb_wechselnd_id));
		assert(istSpielfeldVoll(gelb_rot_wechselnd_id));
		assert(!istSpielfeldVoll(rot_gelb_abnehmend_id));
		assert(!istSpielfeldVoll(gelb_rot_abnehmend_id));
		assert(!istSpielfeldVoll(rot_gelb_zunehmend_id));
		assert(!istSpielfeldVoll(gelb_rot_zunehmend_id));
		assert(!istSpielfeldVoll(beispielpng_id));
	}
	
	
	
	
	public void testIstReiheVoll(){
		// ist eine bestimmte Reihe voll (alle Felder mit Spielstein belegt)
		
		for(byte i=0; i<7; i++){
			assert(!istReiheVoll(alles_leer,i));
			assert(istReiheVoll(alles_gelb,i));
			assert(istReiheVoll(alles_rot,i));
			assert(istReiheVoll(rot_gelb_wechselnd,i));
			assert(istReiheVoll(gelb_rot_wechselnd,i));
			
			if(i==0) assert(istReiheVoll(rot_gelb_abnehmend,i));
			else assert(!istReiheVoll(rot_gelb_abnehmend,i));
			
			if(i==0) assert(istReiheVoll(gelb_rot_abnehmend,i));
			else assert(!istReiheVoll(gelb_rot_abnehmend,i));
			
			if(i==6) assert(istReiheVoll(rot_gelb_zunehmend,i));
			else assert(!istReiheVoll(rot_gelb_zunehmend,i));
			
			if(i==6) assert(istReiheVoll(gelb_rot_zunehmend,i));
			else assert(!istReiheVoll(gelb_rot_zunehmend,i));
			
			if(i==0 || i==3 || i==6) assert(istReiheVoll(beispielpng,i));
			else assert(!istReiheVoll(beispielpng,i));
		}
		
		for(byte i=0; i<7; i++){
			assert(!istReiheVoll(alles_leer_id,i));
			assert(istReiheVoll(alles_gelb_id,i));
			assert(istReiheVoll(alles_rot_id,i));
			assert(istReiheVoll(rot_gelb_wechselnd_id,i));
			assert(istReiheVoll(gelb_rot_wechselnd_id,i));
			
			if(i==0) assert(istReiheVoll(rot_gelb_abnehmend_id,i));
			else assert(!istReiheVoll(rot_gelb_abnehmend_id,i));
			
			if(i==0) assert(istReiheVoll(gelb_rot_abnehmend_id,i));
			else assert(!istReiheVoll(gelb_rot_abnehmend_id,i));
			
			if(i==6) assert(istReiheVoll(rot_gelb_zunehmend_id,i));
			else assert(!istReiheVoll(rot_gelb_zunehmend_id,i));
			
			if(i==6) assert(istReiheVoll(gelb_rot_zunehmend_id,i));
			else assert(!istReiheVoll(gelb_rot_zunehmend_id,i));
			
			if(i==0 || i==3 || i==6) assert(istReiheVoll(beispielpng_id,i));
			else assert(!istReiheVoll(beispielpng_id,i));
		}
	}
	
	
	
	
	public void testIstReiheLeer(){
		// ist eine bestimmte Reihe leer? (kein Stein in der Reihe)
		
		for(byte i=0; i<7; i++){
			assert(istReiheLeer(alles_leer,i));
			assert(!istReiheLeer(alles_gelb,i));
			assert(!istReiheLeer(alles_rot,i));
			assert(!istReiheLeer(rot_gelb_wechselnd,i));
			assert(!istReiheLeer(gelb_rot_wechselnd,i));
			
			if(i==6) assert(istReiheLeer(rot_gelb_abnehmend,i));
			else assert(!istReiheLeer(rot_gelb_abnehmend,i));
			
			if(i==6) assert(istReiheLeer(gelb_rot_abnehmend,i));
			else assert(!istReiheLeer(gelb_rot_abnehmend,i));
			
			if(i==0) assert(istReiheLeer(rot_gelb_zunehmend,i));
			else assert(!istReiheLeer(rot_gelb_zunehmend,i));
			
			if(i==0) assert(istReiheLeer(gelb_rot_zunehmend,i));
			else assert(!istReiheLeer(gelb_rot_zunehmend,i));
			
			assert(!istReiheLeer(beispielpng,i));
		}
		
		for(byte i=0; i<7; i++){
			assert(istReiheLeer(alles_leer_id,i));
			assert(!istReiheLeer(alles_gelb_id,i));
			assert(!istReiheLeer(alles_rot_id,i));
			assert(!istReiheLeer(rot_gelb_wechselnd_id,i));
			assert(!istReiheLeer(gelb_rot_wechselnd_id,i));
			
			if(i==6) assert(istReiheLeer(rot_gelb_abnehmend_id,i));
			else assert(!istReiheLeer(rot_gelb_abnehmend_id,i));
			
			if(i==6) assert(istReiheLeer(gelb_rot_abnehmend_id,i));
			else assert(!istReiheLeer(gelb_rot_abnehmend_id,i));
			
			if(i==0) assert(istReiheLeer(rot_gelb_zunehmend_id,i));
			else assert(!istReiheLeer(rot_gelb_zunehmend_id,i));
			
			if(i==0) assert(istReiheLeer(gelb_rot_zunehmend_id,i));
			else assert(!istReiheLeer(gelb_rot_zunehmend_id,i));
			
			assert(!istReiheLeer(beispielpng_id,i));
		}
	}
	
	
	
	/*
	 * TODO tests schreiben:
	 * pow2, spielfeldCopy, werIstDran, getVorigeID, getVorigesSpielfeld,
	 * getFolgeID, getFolgeSpielfeld, getFolgeIDFarbe, getFolgeSpielfeldFarbe
	 */
	
	
}
