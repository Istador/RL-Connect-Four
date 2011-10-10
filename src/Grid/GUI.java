package Grid;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class GUI extends JFrame{
	  
	int size_x = 600;
	int size_y = 600;
	int size_X_Umwelt;
	int size_Y_Umwelt;
	public int getKor_X_A() {
		return kor_X_A;
	}
	public void setKor_X_A(int kor_X_A) {
		this.kor_X_A = kor_X_A;
	}
	public int getKor_Y_A() {
		return kor_Y_A;
	}
	public void setKor_Y_A(int kor_Y_A) {
		this.kor_Y_A = kor_Y_A;
	}
	int kor_X_A=0;
	int kor_Y_A=0;
	BufferedImage bild_Agent;
	BufferedImage bild_Schatz;
	BufferedImage bild_Berg;
	
		public GUI(int x, int y) {
			this.setAlwaysOnTop(true);
			 try {
				bild_Agent = ImageIO.read(new File("C:/EclipseWorkspace/Copy of Jade_Test/src/fw/Agent.gif"));
				bild_Schatz = ImageIO.read(new File("C:/EclipseWorkspace/Copy of Jade_Test/src/fw/Schatz.gif"));
				bild_Berg = ImageIO.read(new File("C:/EclipseWorkspace/Copy of Jade_Test/src/fw/Berg.gif"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.size_X_Umwelt = x;
			this.size_Y_Umwelt = y;
			this.setSize(size_x+200, size_y+200);
			this.setVisible(true);
			this.repaint();
	
		}
	@Override
	public void paint(Graphics g) { 
		super.paint(g); 
		g.setColor(Color.black); 
		setBackground(Color.green); 
		int zeilen = size_x*2 / this.size_X_Umwelt  ;

		for(int i=0; i< zeilen;i++){ 
			g.drawLine(0,i*(size_x/ size_X_Umwelt),size_x+(200),i*(size_y / size_X_Umwelt)); 
		}
		int spalten = size_y / this.size_Y_Umwelt +1;
		for(int i=0; i< spalten;i++){
			g.drawLine(i*(size_y / size_Y_Umwelt),size_y+200,i*(size_y / size_Y_Umwelt),0); 
		}
	
		   Graphics2D g2 = (Graphics2D) g;
		
		   g.drawImage(bild_Schatz,  2*(size_x/ size_X_Umwelt)+60,1*(size_y / size_Y_Umwelt)+60 , null);
		   g.drawImage(bild_Berg,  1*(size_x/ size_X_Umwelt)+60,2*(size_y / size_Y_Umwelt)+60 , null);
		  // Image img1 = Toolkit.getDefaultToolkit().getImage("Agent.gif");
		 //   g2.drawImage(img1, 10, 10, this);
		//    g2.finalize();
		    g.drawImage(bild_Agent, (size_x / size_X_Umwelt) * kor_X_A+60,size_y - (size_y / size_Y_Umwelt) * kor_Y_A +55, null);   
		//g.drawRoundRect((size_x / size_X_Umwelt) * kor_X_A+30,size_y - (size_y / size_Y_Umwelt) * kor_Y_A +30, 50, 50, 50, 60);
		//g.drawLine(0,0,200,200); 
	
		
		}
	public void aktualisiere(int x, int y){
		this.kor_X_A=x;
		this.kor_Y_A =y;
		repaint();
	}
}
