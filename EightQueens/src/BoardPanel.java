import java.awt.Color;

public class BoardPanel {
	private Board b; //has a Board
	public static final int NUM_QUEENS = 8;
	private static final int ROWS = NUM_QUEENS;
	private static final int COLS = NUM_QUEENS; 
	
	private static final int HEIGHT = 120 * ROWS;
	private static final int WIDTH = 120 * COLS;
	private static final Color LIGHT_COLOR = Color.ORANGE;
	private static final Color DARK_COLOR = Color.DARK_GRAY;
	
	
	public BoardPanel() { 
		b = new Board();
	}
	
	public void drawboard() {
		
	}
	
	public static void main(String[] args) {
		
	}

}
