import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoardFrame {
	private Board b; //has a Board
	//Panel/Frame stuff
	private JFrame window; //Outermost grpahical component
	private JPanel header, grid, footer; //Panels 
	ChessSquarePanel[][] spaces = new ChessSquarePanel[ROWS][COLS]; // not sure how this is different than Grids?
	
	public static final int NUM_QUEENS = 8;
	private static final int ROWS = NUM_QUEENS;
	private static final int COLS = NUM_QUEENS; 
	
	private static final int HEIGHT = 100 * ROWS;
	private static final int WIDTH = 100 * COLS;
	private static final Color LIGHT_COLOR = Color.LIGHT_GRAY;
	private static final Color DARK_COLOR = Color.DARK_GRAY;
	private static final Color FOOTER_COLOR = Color.MAGENTA;
	private static final Color HEADER_COLOR = Color.CYAN;
	
	
	//Constructor
	public BoardFrame() { 
		b = new Board(); //Create the Board
		b.addQueens();
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
		//Construct the panels
		header = buildHeaderPanel();
		grid = buildGridPanel();
		footer = buildFooterPanel();
		//Add the panels to the Window
		window.add(header);
		window.add(grid);
		window.add(footer);
		window.repaint(); //repaint after configuring just in case?
		window.setVisible(true);	//show the window
	}
	
	//CONSTRUCTOR HELPERS//
	private JPanel buildHeaderPanel() {
		JPanel result = new JPanel();
		result.setMinimumSize(new Dimension(WIDTH, 10));
		result.setMaximumSize(new Dimension(WIDTH, 50));
		result.setPreferredSize(new Dimension(WIDTH, 40));
		result.setBackground(HEADER_COLOR);
		result.add(new JLabel("eight Queens"));
	    return result;
	}
	
	private JPanel buildFooterPanel() {
		JPanel result = new JPanel();
		result.setMinimumSize(new Dimension(WIDTH, 10));
		result.setMaximumSize(new Dimension(WIDTH, 50));
		result.setPreferredSize(new Dimension(WIDTH, 40));
		result.setBackground(FOOTER_COLOR);
		result.add(new JLabel("eight Queens"));
	    return result;
	}
	
	private JPanel buildGridPanel() {
		JPanel p = new JPanel();
	    p.setLayout(new GridLayout(ROWS,COLS));
		p.setMinimumSize(new Dimension(WIDTH/2, HEIGHT/2));
	    p.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	    Color bg;
	    for (int r = 0; r < ROWS; r++) {
	    		for (int c = 0; c < COLS; c++) {
	    			bg = setPanelColor(r,c); //get the bg color based on index of array
	    			boolean q = b.isQueen(r, c);
	    			ChessSquarePanel m = new ChessSquarePanel(q, bg); 
	            spaces[r][c] = m;  // keep a reference to the panel, so we can change it
	            p.add(m);
	         }
	      }
	      return p;
	}
	
	//GRAPHICAL HELPERS//
	private void updatePanel(int r, int c, boolean q) {
		ChessSquarePanel p = spaces[r][c];
		p.setQueen(q);
		window.repaint(); //NEed this bc I don't update in repaint
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
	
	public static void main(String[] args) {
		BoardFrame x = new BoardFrame();
		//x.updatePanel(2, 2, true); //test to change 3,3 to true
	}

}
