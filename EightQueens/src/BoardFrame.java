/**
 * Class Description: This class represents an organized display of ChessSquarePanels.  
 * 				It includes a button to cycle through all the found solutions and a header bar just to prove that I can do that.
 * @author: MurphyP1
 * @date: 5/10/18
 */

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
	
	/**
	 * The constructor which instantiates an instance of SolvedBoards which stores all the found solutions and creates all the Panels
	 */
	public BoardFrame() { 
		sb = new SolvedBoards(); //Create the Board
		solutionNum = 0;
		buildFrame(); //Build the Frame 
		createPanels(); //Create the ChessSquarePanels  
	}
	
	//CONSTRUCTOR HELPERS//

	/**
	 * A helper method which creates the JFrame for the graphics
	 */
	private void buildFrame() {
		window = new JFrame("EightQueens");
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setSize(new Dimension(WIDTH, HEIGHT));
	    window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS)); 
	}
	
	/**
	 * A helper method which creates all the panels and buttons and adds them to the Frame
	 */
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
	
	/**
	 * A helper method which builds the header JPanel 
	 * @return the header JPanel 
	 */
	private JPanel buildHeaderPanel() {
		JPanel result = new JPanel();
		result.setMinimumSize(new Dimension(WIDTH, 10));
		result.setMaximumSize(new Dimension(WIDTH, 50));
		result.setPreferredSize(new Dimension(WIDTH, 40));
		result.setBackground(HEADER_COLOR);
		result.add(new JLabel("eight Queens, more like 8 solutions :("));
	    return result;
	}
	
	/**
	 * A helper method which builds the JButton and binds it to the nextSol method.
	 * @return the JButton
	 */
	private JButton buildFooterButton() { //Help w/buttons from Sean
		JButton result = new JButton("Solution " + (solutionNum + 1) + " / "+  sb.size() );
		result.setHorizontalAlignment(SwingConstants.CENTER - result.getWidth()/2); //This doesn't work, dunno why
		result.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { nextSol(); }
		});
		
	    return result;
	}
	
	/**
	 * A helper method which builds the grid panel where all the ChessSquarePanels are stored; It creates all the CSPanels and adds them to the grid which acts as a parent container.
	 * @return the grid JPanel
	 */
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

	
	/**
	 * A helper method which determines the color to be sent into the ChessSquarePanel constructor for the bg Color
	 * @param x the 1D index of the position on the Board
	 * @return true if the index is even, false if odd
	 */
	private boolean isEven(int x) { return x % 2 == 0; }

	/**
	 * A helper method which accepts a row and column and returns the Color to be sent into the CSP constructor as the bg Color
	 * @param row the row 
	 * @param col the column 
	 * @return a Color depending on whether or not the CSP at row, col is even or odd
	 */
	private Color setPanelColor(int row, int col) {
		if (isEven(row + col) ) 
			return LIGHT_COLOR;
	    else
	        return DARK_COLOR;
	}
	
	/**
	 * A method which cycles through solutions of the Board and updates the spaces[][] and CSPanels according to the current Board
	 */
	private void nextSol() {	
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
		
		footer.setText("Solution " + (solutionNum + 1) + " / "+  sb.size() ); //update text
		
		window.repaint();	//update the window
		System.out.println("solutionNum : " + solutionNum );
		System.out.println(sb.get(solutionNum));
	}

	/**
	 * A wrapper for the sb>ArrayList.add method which adds a board to the SolvedBoards field
	 * @param b the Board to be added
	 * @return
	 */
	public void addBoard(Board b) { sb.add(b); }
	
	/**
	 * The main testing method from which this program should be executed.  It creates an instance of a BoardFrame.
	 * @param args a String array which contains arguments passed in by the command line
	 */
	public static void main(String[] args) {
		BoardFrame x = new BoardFrame();
	}

}
