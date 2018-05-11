import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class BoardFrame {
	private SolvedBoards sb; //has a SolvedBoards
	int solutionNum; //Keeps track of which solution is being displayed
	//Panel/Frame stuff
	private JFrame window; //Outermost graphical component
	private JPanel header, grid; //Panels 
	private JButton footer; //Button to iterate over found solutions
	ChessSquarePanel[][] spaces = new ChessSquarePanel[ROWS][COLS]; 
	
	public static final int NUM_QUEENS = 8;
	private static final int ROWS = NUM_QUEENS;
	private static final int COLS = NUM_QUEENS; 
	
	private static final int HEIGHT = 100 * ROWS;
	private static final int WIDTH = 100 * COLS;
	private static final Color LIGHT_COLOR = Color.LIGHT_GRAY;
	private static final Color DARK_COLOR = Color.DARK_GRAY;
	private static final Color HEADER_COLOR = Color.ORANGE;
	
	//Constructor
	public BoardFrame() { 
		sb = new SolvedBoards(); //Create the Board
		solutionNum = 0;
		buildFrame(); //Build the Frame 
		createPanels(); //Create the ChessSquarePanels  
	}
	
	//graphics helpers
	private void buildFrame() {
		window = new JFrame("EightQueens");
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setSize(new Dimension(WIDTH, HEIGHT));
	    window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS)); 
	}
	
	private void createPanels() {
		//Construct the panels
		header = buildHeaderPanel();
		grid = buildGridPanel();
		footer = buildFooterButton();
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
	
	private JButton buildFooterButton() { //Help w/buttons from Sean
		JButton result = new JButton("Next Solution");
		result.setHorizontalAlignment(SwingConstants.CENTER);
		result.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { nextSol(); }
		});
		
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
	    			bg = setPanelColor(r, c); //get the bg color based on index of array
	    			boolean q = sb.get(solutionNum).isQueen(r, c); //Determine if space on given Board is a Queen
	    			ChessSquarePanel m = new ChessSquarePanel(q, bg); 	//Create the Panel
	            spaces[r][c] = m;  // keep a reference to the panel, so we can change it
	            p.add(m); //Add the sub-panel to the grid panel
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
	
	private boolean nextSol() {	
		int i = solutionNum + 1;
		
		//Cycle logic
		try {
			sb.get(i); //Test for IOOB
			solutionNum = i; //If doable, then update the solutionNumber
		} catch(IndexOutOfBoundsException e) {
            solutionNum = 0; //cycle
		}
		
		//Update panel according to new Board 
		for(int r = 0; r < NUM_QUEENS; r++) {
			for(int c = 0; c < NUM_QUEENS; c++) {
    				boolean q = sb.get(solutionNum).isQueen(r, c); //Determine if space on given Board is a Queen
    				spaces[r][c].setQueen(q); //update panel
			}
		}
		
		window.repaint();	//update the window
		System.out.println("solutionNum : " + solutionNum );
		System.out.println(sb.get(solutionNum));
		
		return true;
	}
	
	public boolean addBoard(Board b) {
		sb.add(b);
		return true;
	}
	
	public static void main(String[] args) {
		BoardFrame x = new BoardFrame();
	}

}
