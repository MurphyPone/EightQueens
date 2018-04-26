import java.awt.Color;

import javax.swing.ImageIcon;

public class ChessSquarePanel {
	public static Image img = new Image("Queen.png"); //The image to be displayed
	private int numSquare; //To track the color of the Square, may not be needed bc constructor
	private boolean isEmpty; 
	
	//Constructor which accepts the color and the
	ChessSquarePanel(Color bg, boolean isEmpty) {
		this.isEmpty = isEmpty;
		if(!isEmpty) { graphics.drawImage(img); } //TODO FIX?
	}
	
	//Another cosntructor to accept in numSquare and color accordingly?
	
	public void paintComponent(Graphics g, int numSquare)
	

	public static void main(String[] args) {
		

	}

}
