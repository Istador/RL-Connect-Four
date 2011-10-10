package TicTacToe;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import Agent.A_Aktion;

public class GUI extends JFrame{
		boolean end =true;
		ArrayList<A_Aktion> aktionen = new ArrayList<A_Aktion>();
		Status_Feld[] p1 =new Status_Feld[9];
		Status_Feld[] p2 =new Status_Feld[9];
		GUI(ArrayList<A_Aktion> arrayList){
			this.setName("Tic Tac Toe");
			this.setSize(600, 600);
			this.setAlwaysOnTop(true);
			this.setVisible(true);
			this.aktionen = arrayList;
			repaint();
		}
		public void paint(Graphics g){
			this.end = false;
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(getBackground());
			g2.fillRect(0, 0, 600, 600);
			g2.setColor(Color.black);
			for(int i=0;i<3;i++){
				g2.drawLine(i*200,0 ,i*200 , 600);
				g2.drawLine(0, i*200, 600, i*200);
				
			}
			for(int f=0; f<9;f++){
				if(p1[f] != null){
				if(p1[f].isP1()){
				//	System.out.println("Durchlauf mit " + f);
					fuege_Ein tmp = (fuege_Ein) aktionen.get(f);
					int v = tmp.getVertikal();
					int h = tmp.getHorizontal();
					//g.drawLine(20, 40, 60, 80);
					g2.drawArc(200*v + 60, 200*h +60, 80, 80,360,360);
					//System.out.println("ZEICHNEN bei " + (200*v + 60) + " und " + h*200 +60 );
				}
			}
			}
			for(int f=0; f<9;f++){
				if(p2[f] != null){
				if(p2[f].isP2()){
				//	System.out.println("Durchlauf mit " + f);
					fuege_Ein tmp = (fuege_Ein) aktionen.get(f);
					int v = tmp.getVertikal();
					int h = tmp.getHorizontal();
					g2.drawRoundRect(200*v + 60, 200*h +60, 80, 80,360,360);
					BufferedImage img = null;
					try {
						img = ImageIO.read(new File("C:/EclipseWorkspace/Copy of Jade_Test/src/TicTacToe/Kreuz.gif"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					 g.drawImage(img,  200*v + 60, 200*h +60, 80, 80, null);
					//System.out.println("ZEICHNEN bei " + (200*v + 60) + " und " + h*200 +60 );
				}
			}
			}
			this.end= true;
		}
		public boolean isEnd() {
			return end;
		}
		public void setEnd(boolean end) {
			this.end = end;
		}
		public void aktualisiere(Status_Feld[] p1, Status_Feld[] p2){
			for(int i=0;i<9;i++)
				this.p1[i] = Status_Feld.status_Feld_COP(p1[i]);
			
			for(int i=0;i<9;i++)
				this.p2[i] = Status_Feld.status_Feld_COP(p2[i]);
			super.paint(getGraphics());
			repaint();
		}

}
