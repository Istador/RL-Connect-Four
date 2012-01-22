package Agent;

import java.awt.Graphics;
import java.awt.Point;
import java.text.DecimalFormat;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

/**
 * GUI zur Darstellung und Verfolgung der gelernten Q-Werte des Agenten
 * jeder Agent hat sein eigenes JFrame
 */
public class GUI_Agent extends JFrame {
	private static final long serialVersionUID = -3738773237111308027L;
	
	//TODO: Bug, das ab und an das JFrame leer (ohne Table) bleibt
	
	//Set<A_Situation_Aktion> keys = new java.util.TreeSet<A_Situation_Aktion>();
	//Map<A_Situation_Aktion, Double> werte = new java.util.TreeMap<A_Situation_Aktion, Double>();
	
	//Map, in welcher Zeile des JTable welche A_Situation_Aktion ist
	Map<A_Situation_Aktion, Integer> zeilen = new java.util.HashMap<A_Situation_Aktion, Integer>();
	
	//Anzahl Einträge
	int n = 0;
	
	JTable table;// = new JTable();
	DefaultTableModel model;
	JScrollPane scrollPane;// = new JScrollPane(list);

	DecimalFormat df_w = new DecimalFormat("0.0000000000000000000"); // Bewertung
	
	@SuppressWarnings("serial")
	public GUI_Agent(String name) {
		this.setSize(400, 500);
		this.setTitle("GUI Agent: "+name);
		this.setVisible(true);
		//this.setAlwaysOnTop(true);
		this.setLocation(new Point(500,0));
		
		model = new DefaultTableModel(){
				@Override
				/**
				 * Verhindern, dass der Benutzer, die Tabelle bearbeiten kann
				 */
				public boolean isCellEditable(int rowIndex, int columnIndex){return false;}
			};
		
		//Tabelle erstellen
		table = new JTable(model);
		
		//Überschriften
		model.addColumn("Situation");
		model.addColumn("Aktion");
		model.addColumn("Wert");
		
		//table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
		
		//Breite der Aktions Spalte verkleinern
		table.getColumn("Aktion").setPreferredWidth(10);
		
		
		//Scrollbalken
		scrollPane = new JScrollPane( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS );
		//this.setLayout(null);
		scrollPane.add(table);
		scrollPane.getViewport().setView(table);
		scrollPane.setLocation(0, 40);
		//scrollPane.setBounds(0, 40, 500, 600);
		this.getContentPane().add(scrollPane);
		repaint();
	}
	
	/**
	 * erneuern der GUI mit den neuen Werten
	 */
	public void update_Werte(Map<A_Situation_Aktion, Double> eingabe){
		
		//für alle Werte die übergeben werden
		for(Map.Entry<A_Situation_Aktion, Double> entry : eingabe.entrySet()){
			A_Situation_Aktion key = entry.getKey();
			Double value = entry.getValue();
			int zeile = -1;
			// Zustand bereits vorhanden -> aktualisieren
			if( zeilen.containsKey(key) ){
				//Double old = werte.get(key);
				
				//Wert hat sich geändert
				//if(Double.compare(value, old)!=0){
					//werte.put(key, value);
					zeile = zeilen.get(key);
					model.setValueAt(df_w.format(value), zeile, 2);
				//}
			}
			//neuer Zustand -> neue Zeile hinzufügen
			else{
				//werte.put(key, value);
				zeile = n;
				zeilen.put(key, n++);
				model.addRow(new Object[]{key.getSituationString(), key.getAktionString(), df_w.format(value)});
			}
		}
		
		repaint();
	}		

	/**
	 * Zeichnen der GUI
	 */
	public void paint(Graphics g) { 
		super.paint(g);
		/*
		Font myFont=new Font("Arial", Font.ITALIC|Font.PLAIN, 14);
			
		g.setFont(myFont);
		g.drawString("Situation", 20, 10);
		g.drawString("Werte", 350, 10);
		//g.drawLine(0,0,200,200);
		String array[] = new String[99];
		array[2] ="WUFF";
		int i=0;
		for(A_Situation_Aktion tmp : keys){
			i++;
			System.out.println("Hinzufügen von: " +  tmp.toString() +  werte.get(tmp).toString() );
			array[i] =  tmp.toString() +  werte.get(tmp).toString();
		}
		JList list = new JList(array);
			
			 
			
		this.getContentPane().add(new JScrollPane(list));
	/*		
		int x=1;
		for(A_Situation_Aktion tmp : keys){
			x++;
			g.drawString(tmp.toString(), 10,40+ 20*x);
			g.drawString("Werte: " + werte.get(tmp).toString(), 500,40+ 20*x);
		
		}*/
	}


		
	
}
