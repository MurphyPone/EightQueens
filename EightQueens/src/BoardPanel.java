import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoardPanel {
	private Board b; //has a Board
	//Pnale/Frame stuff
	private ArrayList<ChessSquarePanel> panels;
	private JFrame window;
	private JPanel header, grid, footer; //Panels 
	
	
	public static final int NUM_QUEENS = 8;
	private static final int ROWS = NUM_QUEENS;
	private static final int COLS = NUM_QUEENS; 
	
	private static final int HEIGHT = 120 * ROWS;
	private static final int WIDTH = 120 * COLS;
	private static final Color LIGHT_COLOR = Color.ORANGE;
	private static final Color DARK_COLOR = Color.DARK_GRAY;
	
	
	//Constructor
	public BoardPanel() { 
		b = new Board(); //Create the Board
		buildFrame(); //Build the Frame 
		createPanels(); //Create the ChessSquarePanels  
	}
	
	//graphics helpers
	private void buildFrame() {
		window = new JFrame("EightQueens");
		window = new JFrame("Practicing");
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setSize(new Dimension(WIDTH, HEIGHT));
	    window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS)); 
	}
	
	private void createPanels() {
		header = buildHeaderPanel();
		grid = buildGridPanel();
		footer = buildfooterPanel();
	}
	
	private JPanel buildHeaderPanel() {
		JPanel result = new JPanel();
		result.setMinimumSize(new Dimension(WIDTH, 10));
		result.setMaximumSize(new Dimension(WIDTH, 50));
		result.setPreferredSize(new Dimension(WIDTH, 40));
		result.setBackground(LIGHT_COLOR);
		result.add(new JLabel("eight Queens"));
	    return result;
	}
	
	//Helper logic method
	private boolean isEven(int x) { return x % 2 == 0; }
	////Helper Color Method
	private Color setPanelColor(int row, int col) {
		if (isEven(row + col) ) 
			return LIGHT_COLOR;
	    else
	        return DARK_COLOR;
	}
	
	private JPanel buildGridPanel() {
		JPanel p = new JPanel();
	    p.setLayout(new GridLayout(ROWS,COLS));
	    Color bg;
	    for (int r = 0; r < ROWS; r++) {
	    	for (int c = 0; c < COLS; c++) {
	    		bg = setPanelColor(r,c);           
	            MyPanel m = new MyPanel(bg, (char)((int)'a' + r * COLS + c) + ""); //TODO INSTEAD this should be a ChessSquarePanel
	            spaces[r][c] = m;  // keep a reference to the panel, so we can change it
	            p.add(m);
	         }
	      }
	      return p;
	}
	
	public static void main(String[] args) {
		
	}

}
