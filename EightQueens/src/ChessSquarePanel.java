/**
 * Class Description: This class represents a single graphical panel of a chessboard by keeping track of its color and the status of its queen placement.
 * @author: MurphyP1
 * @date: 5/10/18
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

//A single panel of a ChessBoard
public class ChessSquarePanel extends JPanel {
	private static BufferedImage img; //The Queen image
	private boolean isQueen; //Whether or not is is a Queen
	private Color bgc; //Background color
	
	/**
	 * The constructor which accepts a flag denoting the Queen status of this panel and the background color for the panel so that the BoardFrame looks pretty
	 * @param flag a boolean value which defines the panels Queen status
	 * @param bg a Color which makes the BoardFrame have checker board pattern
	 */
	public ChessSquarePanel(boolean flag, Color bg) { 
		isQueen = flag;
		bgc = bg;
		img = loadImg();
	}
	
	/**
	 * A helper method which attempts to load the Queen image in.
	 * @return the Image
	 * @throws IOException e if failed to load the image
	 */
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
	
	/**
	 * The graphical paint method which configures and executes the graphical aspects of the panel
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(bgc);
		drawQueen(g);
	}
	
	/**
	 * A helper method which draws the queen image on the panel according to its Queen status
	 * @param g the Graphics
	 */
	private void drawQueen(Graphics g) {
		if(isQueen()) { //Only draw if it is a Queen
			int x = img.getWidth() / 4; //center the img
			int y = 5; //img.getHeight() / 2;
			g.drawImage(img, x, y, null); //don't need an observer
		}
	}
	
	/**
	 * A helper method which sets the Queen status of the panel
	 * @param q a boolean value representing the desired Queen status of the panel
	 */
	public void setQueen(boolean q) { isQueen = q; }
	
	/**
	 * A helper method which returns the Queen status of the panel
	 * @return true if Queen, false if not a Queen
	 */
	public boolean isQueen() { return isQueen; }
}
