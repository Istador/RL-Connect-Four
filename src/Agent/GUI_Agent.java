package Agent;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class GUI_Agent extends JFrame {

	private static final long serialVersionUID = 1L;
		HashMap<A_Situation_Aktion, Double> werte = new HashMap<A_Situation_Aktion, Double>();
		Set<A_Situation_Aktion> keys = new TreeSet<A_Situation_Aktion>();
		ArrayList<A_Aktion>  aktionen;
		JList list = new JList();
		JScrollPane scrollPane = new JScrollPane(list);
		public GUI_Agent(ArrayList<A_Aktion>  aktionen) {
			this.setSize(600, 600);
			this.setTitle("GUI Agent");
			this.setVisible(true);
			this.aktionen = aktionen;
			this.setAlwaysOnTop(true);
			this.setLocation(new Point(500,0));
			scrollPane = new JScrollPane( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
                    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS );
		//	this.setLayout(null);
			repaint();
	
		
			
		}
		
	
		public void update_Werte(HashMap<A_Situation_Aktion, Double> werte){
			this.werte = werte;
			this.keys = werte.keySet();
			this.aktualisieren();

			
		}
		public void aktualisieren(){
	//Font myFont=new Font("Arial", Font.ITALIC|Font.PLAIN, 14);
			
			String array[] = new String[keys.size()+1];
		
			int i=0;
			for(A_Situation_Aktion tmp : keys){
				i++;
			//	System.out.println("Hinzufügen von: " +  tmp.toString() +  werte.get(tmp).toString() );
				array[i] =  i + " " +tmp.toString() + " Wert: " +  werte.get(tmp).toString() + " Episode: " +  tmp.getEpisode();
			}
			list = new JList(array);
			scrollPane.add(list);
			scrollPane.getViewport().setView(list);
			scrollPane.setLocation(0, 40);
			//scrollPane.setBounds(0, 40, 500, 600);
			this.getContentPane().add(scrollPane);
			repaint();
	 
		}
		public void paint(Graphics g) { 
		
			super.paint(g);
			
			Font myFont=new Font("Arial", Font.ITALIC|Font.PLAIN, 14);
			
			g.setFont(myFont);
			g.drawString("Situation", 20, 10);
			g.drawString("Werte", 350, 10);/*
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
