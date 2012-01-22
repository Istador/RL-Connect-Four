package vierGewinnt.KI;

import java.util.*;

/**
 * Vier Gewinnt Utility
 *  Methoden für die VierGewinnt Spiellogik
 */
public class VG_Utility {

	
	
	
	/**
	 * 2^n rekursiv mit gecachtem Ergebnis
	 */
	private static Map<Long, Long> pow2 = new HashMap<Long,Long>();
	public static final long pow2(long n){
		assert(n>=0);
		Long z = pow2.get(n);
		if(z == null){
			z = sub_pow2(n);
			pow2.put(n, z);
		}
		return z;
	}
	private static final long sub_pow2(long n){
		if(n==0)
			return 1;
		if(n==1)
			return 2;
		if(n%2==0) // gerade
			return pow2(n/2) * pow2(n/2);
		else //ungerade
			return 2 * pow2(n-1);
	}
	
	/**
	 * mitgetragene Summe
	 */
	@SuppressWarnings("unused")
	private static final long sub_pow2(long n, long sum){
		if(n==0)
			return sum;
		if(n==1)
			return 2*sum;
		if(n%2==0) // gerade
			if(sum%2==0)
				//return pow2(n/2, sum/2) * pow2(n/2, sum/2);
				return sub_pow2(n/2, sub_pow2(n/2, sum));
			else
				return sum * pow2(n/2) * pow2(n/2);
		else //ungerade
			return sub_pow2(n-1, 2*sum);
	}
	
	/* Mit BigInteger
	private static BigInteger ZERO = BigInteger.ZERO;
	private static BigInteger ONE = BigInteger.ONE;
	private static BigInteger TWO = ONE.add(ONE);
	private static Map<BigInteger, BigInteger> bipow2 = new HashMap<BigInteger,BigInteger>();
	
	public static final BigInteger pow2(BigInteger n){
		assert(n.compareTo(BigInteger.ZERO)>=0);
		BigInteger z = bipow2.get(n);
		if(z == null){
			z = sub_pow2(n);
			bipow2.put(n, z);
		}
		return z;
	}
	
	private static final BigInteger sub_pow2(BigInteger n){
		if(n.equals(ZERO))
			return ONE;
		if(n.equals(ONE))
			return TWO;
		if(n.remainder(TWO).equals(ZERO)) // gerade
			return pow2(n.divide(TWO)).multiply(pow2(n.divide(TWO)));
		else //ungerade
			return TWO.multiply(pow2(n.subtract(ONE)));
	}
	*/
	
	
	
	
	
	
	
	/**
	 * Liefert eine eindeutige ID des Spielzustandes eines Spielfeldes.
	 * 
	 * @param spielfeld 7x6-byte-Array mit 0=Leer, 1=Gelb, 2=Rot
	 * @return Eindeutige Long-Zahl, deren binäre kodierung den Spielzustand darstellt.
	 * 
	 * Das 1. bit ist das Vorzeichen von long (für uns unbedeutend).
	 * Danach folgen 7 [(6+3)=9]-Bit Abschnitte für die einzelnen Reihen (7*9=63bit).
	 * Für jede Reihe sind die ersten 3bits die Anzahl Spielsteine in der Reihe,
	 * und die folgenden 6bits geben für jedes Feld an, ob ein gelber(==1) oder 
	 * ein roter(==0) Stein drin ist. Leerfelder und Rote Steine sind in den 6 
	 * bits beide binär eine 0, und sind anhand der Anzahl Spielsteine in den
	 * ersten 3 bits zu unterscheiden. 
	 * Bei den 6 bits für die Felderbelegung ist das unterste Feld 
	 * Binär das LSB und das oberste Feld binär das MSB. 
	 */
	public static long getID(byte[][] spielfeld){
		assert(spielfeld.length == 7);
		assert(spielfeld[0].length == 6);
		
		long ergebnis=0;								// variable (1bit Vorzeichen + 63bit) in der dann das ergebnis steht (die eindeutige ID)
		byte n;											// variable (3 bit Verwendung), um für jede Reihe zu zählen wieviele Steine eingeworfen sind.
		byte zahl; 										// variable (6 bit Verwendung) jedes bit ein Feld in der Reihe, 0 = rot oder leer, 1 = gelb
		for (byte i = 0; i < 7; i++) {					// von links nach rechts alle Reihen.
			ergebnis *= 512;							// 2^9=512, ergebnis 9 bits nach links shiften
			n = 0; 										// für nächste Reihe wieder auf 0 setzen
			zahl = 0; 									// für nächste Reihe wieder auf 0 setzen
			for (byte j = 5; j >= 0; j--) {				// von unten nach oben in einer Reihe alle Felder
				if(spielfeld[i][j]==1) 					// ist das Feld Gelb?
					zahl += pow2(5-j);					// unterstes Feld binär rechts, oberstes feld binär links
				else if (spielfeld[i][j]==0)			// das feld ist leer
					break;								// die Reihe nicht weiter durchlaufen
														// else ist Feld ist rot, wir lassen es auf 0
				n++; 									// anzahl spielsteine hochzählen (wenn leer, dann kommt er wegen dem break nicht hier hin)
			}
			ergebnis += n * 64 + zahl;					// 2^6=64, die Anzahl spielsteine 6 bits nach links shiften, und in die 6 freien bits die 6bits die die 6 Felder repräsentieren
		}
		return ergebnis;
	}
	
	
	/**
	 * Liefert ein Spielfeld zu einer eindeutigen ID eines Spielzustandes
	 * @param id Eindeutige-ID die einen Spielstand darstellt. Rückgabewert von getID().
	 * @return Spielfeld: 7x6-byte-Array mit 0=Leer, 1=Gelb, 2=Rot
	 */
	public static byte[][] getSpielfeld(long id){
		assert(0 <= id);
		long zahl_binarymuster =  32 + 16 + 8 + 4 + 2 + 1;	// Muster für die bits 0-5
		long anzahl_binarymuster = 256 + 128 + 64;			// Muster für die bits 6-8
		
		byte[][] spielfeld = new byte[7][6];				// leeres Spielfeld, alles mit 0 initialisiert
		byte n;
		byte zahl;
		for (byte i = 6; i >= 0; i--) { 					// von rechts nach links alle Reihen (umgekehrt wie bei getId). 
			n = (byte) ((id & anzahl_binarymuster)/64); // 2^6=64, mit Muster verundung nur die 3 bits der Anzahl der aktuellen Reihe zu holen, und dann 6bits nach rechts shiften
			assert(0<=n && n<=6);
			zahl = (byte) (id & zahl_binarymuster);		// verundung, um nur die 6bits der Belegung der aktuellen Reihe zu erhalten
			for (byte j = 5; j >= 0; j--) { 				// von unten nach oben in einer Reihe alle Felder
				byte bit = (byte)(zahl & 1);			// bit 0 lesen
				zahl /= 2;								// bit 0 rausschiften
				if(n>0 && bit==1 )						// gelb
					spielfeld[i][j]= 1;
				else if(n>0 && bit==0 )					// rot
					spielfeld[i][j]= 2;
				else									// leer
					break;
				n--;									//anzahl verbleibender spielsteine in der Reihe verringern
			}
			assert(n==0 && zahl==0);
			id/=512; 									//2^9=512 die ersten (rechts) 9bits rausnehmen -> nächste Reihe in id
		}
		assert(id==0);
		return spielfeld;
	}
	

	
	
	
	
	
	
	/**
	 * Kopiert ein byte[][]-Objekt
	 */
	public static byte[][] spielfeldCopy(byte[][] spielfeld){
		byte[][] result = new byte[spielfeld.length][];
		for(byte i = 0; i < spielfeld.length; i++)
			result[i] = Arrays.copyOf(spielfeld[i], spielfeld[i].length);
		return result;
	}
	
	
	
	
	
	
	
	/**
	 * Spielfeld voll?
	 */
	public static boolean istSpielfeldVoll(long id){
		return istSpielfeldVoll(getSpielfeld(id));
	}
	/**
	 * Spielfeld voll?
	 */
	public static boolean istSpielfeldVoll(byte[][] spielfeld){
		assert(spielfeld.length==7);
		for (byte i = 0; i < 7 ; i++) //von links nach rechts
			if( !istReiheVoll(spielfeld, i) )
				return false;
		return true;
	}
	
	
	
	
	
	/**
	 * Reihe x voll?
	 */
	public static boolean istReiheVoll(long id, byte reihe){
		return istReiheVoll(getSpielfeld(id),reihe);
	}
	/**
	 * Reihe x voll?
	 */
	public static boolean istReiheVoll(byte[][] spielfeld, byte reihe){
		assert(spielfeld[reihe].length==6);
		assert(0 <= reihe && reihe <= 6);
		return spielfeld[reihe][0] != 0;
	}
	
	
	
	
	
	/**
	 * Reihe x leer?
	 */
	public static boolean istReiheLeer(long id, byte reihe){
		return istReiheLeer(getSpielfeld(id),reihe);
	}
	/**
	 * Reihe x leer?
	 */
	public static boolean istReiheLeer(byte[][] spielfeld, byte reihe){
		assert(spielfeld.length==7);
		assert(spielfeld[0].length==6);
		assert(0 <= reihe && reihe <= 6);
		return spielfeld[reihe][5] == 0;
	}
	
	
	
	
	
	
	
	
	/**
	 * Folgezustand, wenn in reihe x eingeworfen wird (Spiel begonnen durch Spieler: Gelb=1 oder Rot=2)
	 */
	public static long getFolgeID(long id, byte reihe, byte begonnen){
		return getFolgeID(getSpielfeld(id), reihe, begonnen);
	}
	/**
	 * Folgezustand, wenn in reihe x eingeworfen wird (Spiel begonnen durch Spieler: Gelb=1 oder Rot=2)
	 */
	public static long getFolgeID(byte[][] spielfeld, byte reihe, byte begonnen){
		return getID(getFolgeSpielfeld(spielfeld, reihe, begonnen));
	}
	/**
	 * Folgezustand, wenn in reihe x eingeworfen wird (Spiel begonnen durch Spieler: Gelb=1 oder Rot=2)
	 */
	public static byte[][] getFolgeSpielfeld(long id, byte reihe, byte begonnen){
		return getFolgeSpielfeld(getSpielfeld(id), reihe, begonnen);
	}
	/**
	 * Folgezustand, wenn in reihe x eingeworfen wird (Spiel begonnen durch Spieler: Gelb=1 oder Rot=2)
	 */
	public static byte[][] getFolgeSpielfeld(byte[][] spielfeld, byte reihe, byte begonnen){
		byte farbe = werIstDran(spielfeld, begonnen); 
		return getFolgeSpielfeldFarbe(spielfeld, reihe, farbe);
	}
	
	
	
	
	/**
	 * Folgezustand, wenn in reihe x ein Stein in Farbe y (Gelb=1, Rot=2) eingeworfen wird. Illegaler Spielzustand möglich.
	 */
	public static long getFolgeIDFarbe(long id, byte reihe, byte farbe){
		return getFolgeIDFarbe(getSpielfeld(id), reihe, farbe);
	}
	/**
	 * Folgezustand, wenn in reihe x ein Stein in Farbe y (Gelb=1, Rot=2) eingeworfen wird. Illegaler Spielzustand möglich.
	 */
	public static long getFolgeIDFarbe(byte[][] spielfeld, byte reihe, byte farbe){
		return getID(getFolgeSpielfeldFarbe(spielfeld, reihe, farbe));
	}
	/**
	 * Folgezustand, wenn in reihe x ein Stein in Farbe y (Gelb=1, Rot=2) eingeworfen wird. Illegaler Spielzustand möglich.
	 */
	public static byte[][] getFolgeSpielfeldFarbe(long id, byte reihe, byte farbe){
		return getFolgeSpielfeldFarbe(getSpielfeld(id), reihe, farbe);
	}
	/**
	 * Folgezustand, wenn in reihe x ein Stein in Farbe y (Gelb=1, Rot=2) eingeworfen wird. Illegaler Spielzustand möglich.
	 */
	public static byte[][] getFolgeSpielfeldFarbe(byte[][] spielfeld, byte reihe, byte farbe){
		assert(spielfeld.length==7);
		assert(spielfeld[0].length==6);
		assert(0 <= reihe && reihe <= 6);
		assert(farbe==1 || farbe==2);
		assert(!istReiheVoll(spielfeld, reihe));
		
		byte[][] matrix = spielfeldCopy(spielfeld);
		for (byte i = 5; i >= 0 ; i--) { //von unten nach oben
			if(matrix[reihe][i] == 0){
				matrix[reihe][i] = farbe;
				break;
			}
		}
		return matrix;
	}
	
	
	
	/**
	 * Ermittelt aufgrund des Spielfeldes und der information welcher Spieler das Spiel begonnen hat (Gelb=1, Rot=2) welcher Spieler (Gelb=1, Rot=2) dran ist.
	 */
	public static byte werIstDran(byte[][] spielfeld, byte begonnen){
		assert(spielfeld.length==7);
		assert(spielfeld[0].length==6);
		assert(begonnen==1 || begonnen==2);

		int gelb = 0; //Anzahl gelber Steine zählen
		int rot = 0; //Anzahl roter Steine zählen
		for (byte i = 0; i < 7 ; i++){ //von links nach rechts
			for (byte j = 5; j >= 0 ; j--){ //von unten nach oben
				if(spielfeld[i][j] == 0) //leer - nächste Reihe
					break;
				if(spielfeld[i][j] == 1) //gelb
					gelb++;
				else //rot
					rot++;
			}
		}
		
		//Gelb hat mehr Steine als Rot eingeworfen -> Rot ist dran
		if( (begonnen==1 && gelb>rot) || (begonnen==2 && gelb>=rot) )
			return 2; //rot ist dran
		return 1; //gelb ist dran
	}
	
	
	
	
	
	
	/**
	 * Rückgängig machen eines Spielzuges, wenn zuvor in reihe x eingeworfen wurde.
	 */
	public static long getVorigeID(int id, byte reihe){
		return getVorigeID(getSpielfeld(id), reihe);
	}
	/**
	 * Rückgängig machen eines Spielzuges, wenn zuvor in reihe x eingeworfen wurde.
	 */
	public static long getVorigeID(byte[][] spielfeld, byte reihe){
		return getID(getVorigesSpielfeld(spielfeld, reihe));
	}
	/**
	 * Rückgängig machen eines Spielzuges, wenn zuvor in reihe x eingeworfen wurde.
	 */
	public static byte[][] getVorigesSpielfeld(int id, byte reihe){
		return getVorigesSpielfeld(getSpielfeld(id), reihe);
	}
	/**
	 * Rückgängig machen eines Spielzuges, wenn zuvor in reihe x eingeworfen wurde.
	 */
	public static byte[][] getVorigesSpielfeld(byte[][] spielfeld, byte reihe){
		assert(spielfeld.length==7);
		assert(spielfeld[0].length==6);
		assert(0 <= reihe && reihe <= 6);
		assert(!istReiheLeer(spielfeld, reihe));
		
		byte[][] matrix = spielfeldCopy(spielfeld);
		for (int i = 0; i < 6 ; i++){ //von oben nach unten
			if(matrix[reihe][i] != 0){
				matrix[reihe][i] = 0;
				break;
			}
		}
		return matrix;
	}
	
	
	
}
