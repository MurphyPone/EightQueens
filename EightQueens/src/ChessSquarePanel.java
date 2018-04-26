import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChessSquarePanel extends JPanel {
	public static Image img = new Image("Queen.png"); //The image to be displayed
	private int numSquare; //To track the color of the Square, may not be needed bc constructor
	private boolean isEmpty; 
	private boolean isQueen; //TODO PICK UP HERE WITH GETTER AND SETTER
	
	//Constructor which accepts the color and the
	ChessSquarePanel(Color bg, boolean isEmpty) {
		this.isEmpty = isEmpty;
		if(!isEmpty) { graphics.drawImage(img); } //TODO FIX?
	}
	
	//Another cosntructor to accept in numSquare and color accordingly?
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int w = getWidth();
		int h = getHeight();
		//move to helper 
		if(numSquare % 2 == 0) //TODO Create some Brown colors 
			g.setColor(Color.WHITE);
		else 
			g.setColor(Color.DARK_GRAY);
		
		g.drawRect(getX(), getY(), getWidth(), getHeight());
		
	}
	

	public static void main(String[] args) {
		JFrame window = new JFrame("Drawings");
		//window.setBounds(800, 600)
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
		JPanel canvas = new ChessSquarePanel();
		canvas.setBackground(Color.white)
		window.getContentPanel().add(canvas)
		window.setVisible(true)

	}

}
