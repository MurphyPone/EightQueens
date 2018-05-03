import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

//A single panel of a ChessBoard
public class ChessSquarePanel extends JPanel {
	private static BufferedImage img;
	private boolean isQueen;
	private Color bgc; //Background color...doesn't make sense for this to be stored here though...
	
	//Default Constructor
	public ChessSquarePanel() {
		isQueen = false;
		bgc = Color.ORANGE;
		img = loadImg();
	}
	
	//Constructor required by assignment
	public ChessSquarePanel(boolean flag, Color bg) { //don't think it should take the bg color 
		isQueen = flag;
		bgc = bg;
		img = loadImg();
	}
	
	private BufferedImage loadImg() {
		img = null;
		try { 
			img = ImageIO.read(new File("img_small.png") );
			return img;
		} catch (IOException e) {
			System.out.println("Image does not exist");
			return img;
		}
	}
	
	public void setQueen(boolean q) {
		isQueen = q;
	}
	
	public boolean isQueen() {
		return isQueen;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(bgc);
	   // g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		//g.setColor(Color.WHITE)
		drawQueen(g);
	}
	
	private void drawQueen(Graphics g) {
		if(isQueen()) { //Only draw if it is a Queen
			int x = img.getWidth() / 4; //center the img
			int y = 5; //img.getHeight() / 2;
			g.drawImage(img, x, y, null); //don't need an observer
		}
	}
	
	//Getters
	public boolean isQ() {
		return isQueen;
	}
	
	public String toString() {
		if(isQueen) 
			return "Q";
		else 
			return "";
	}
}
