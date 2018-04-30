import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

//A single panel of a ChessBoard
public class ChessSquarePanel extends JPanel {
	public static Image Queen;
	private boolean isQueen;
	
	public ChessSquarePanel(boolean flag, Color bg) { //don't think it should take the bg color 
		
	}
	
	
	
	//Getters
	public boolean isQ() {
		return isQueen;
	}
}
