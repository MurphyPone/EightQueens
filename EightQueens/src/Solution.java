import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class Solution extends JFrame{ //JPanel?
	//Logic Fields
	final static int NUM_QUEENS = 8;
	ChessSquarePanel[][] board;	//The board which stores all the placed queens
	int placedQueens; //number of placed queens
	
	//Graphical Fields
	private Container c;
	//Also needs to draw stuff, so TODO add all that component stuff 
	
	//CONSTRUCTOR
	Solution() {
		super("Eight Queens");
		//Configuring Logic
		board = new ChessSquarePanel[NUM_QUEENS][NUM_QUEENS];
		placedQueens = 0;
		
		//Configuring Graphics
		c = getContentPane();
		c.setLayout(new GridLayout(8, 8) ); //(r, c, hgap, vgap)
		//Create NxN ChessSquarePanels
	}
	
	
	//Logical Methods //Should hash every position 
	private boolean isRowClear(int r, int c) {
		if(board[r][c] == null ) { return true; } //Reached end of board
		if(board[r][c].equals("Q") ) { return false; } //conflict
		//else if(board[r][c].equals(" ") ) 
		return isRowClear(r-1, c) && isRowClear(r+1, c); //If empty, 
	}
	
	private boolean isColClear(int r, int c) {
		if(board[r][c] == null ) { return true; } //Reached end of board
		if(board[r][c].equals("Q") ) { return false; } //conflict
		//else if(board[r][c].equals(" ") ) 
		return isColClear(r, c-1) && isRowClear(r, c+1); //If empty, 
	}
	
	private boolean isDiagonalClear(int r, int c) {
		if(board[r][c] == null ) { return true; } //Reached end of board
		if(board[r][c].equals("Q") ) { return false; } //conflict
		//else if(board[r][c].equals(" ") ) 
		return isDiagonalClear(r+1, c+1) && isDiagonalClear(r-1, c-1) //Down right and up left
				&& isDiagonalClear(r+1, c-1) && isDiagonalClear(r-1, c+1); //up Right and down left 
	}
	
	public boolean isClear(int r, int c) {
		return isRowClear(r, c) && isColClear(r, c) && isDiagonalClear(r, c);
	}
	
	public boolean isSolution() {
		return (placedQueens == 8);
	}
	
	public void addQueens() {
		if(NUM_QUEENS < 8) {
			for(int r = 0; r < NUM_QUEENS; r++) {
				for(int c = 0; c < NUM_QUEENS; c++) {
					if( isClear(r, c) )
						board[r][c].setValue("Q");
				}
			}
		}
	}
	
	//Graphical Methods
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
