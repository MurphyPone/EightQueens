/**
 * Class Description: This class represents a single chess board, storing NxN spaces which can either be empty or occupied by a Queen.
 * 				1. This class has a recursive boolean method addQueens() which attempts to add N queens to the Board in such a way that no Queen can attack another.
 * @author: MurphyP1
 * @date: 5/10/18
 */

import java.util.LinkedList;

public class Board {
	private final static int NQ = BoardFrame.NUM_QUEENS; //N amount of Queens to be stored
	private String[][] b; //String array to keep track of the pieces
	
	/**
	 * The default constructor for a Board which instantiates an NQxNQ String[][] and fills the board with empty spaces.
	 */
	public Board() {
		b = new String[NQ][NQ];
		fillBoard(); //config
	}
	
	/**
	 * A helper method for the constructor which iterates over b and adds blank spaces. 
	 */
	private void fillBoard() {
		for(int c = 0; c < NQ; c++) {
			for(int r = 0; r < NQ; r++) {
				b[r][c] = " "; 
			}
		}
	}
	
	/**
	 * A logical helper method which determines if a given spaces within b is a valid placement according to the placement of other Queens on the Board.
	 * @param r the row to check
	 * @param c the column to check
	 * @param QP a list of Queens that have been place so far
	 * @return true if the b[r][c] is a "safe" space to place a new Queen
	 */
	private boolean isValid(int r, int c, LinkedList<Queen> QP) {
		for(Queen q : QP) {
			if(q.getR() == r || q.getC() == c ) //Check for horizontal and vertical Queens
				return false;
			if(!isDiagonalClear(r, c, QP))
				return false;	
		}
		return !isOutOfBounds(r, c); //lastly, ensure that the coords are inbounds
	}
	
	/**
	 * A logical helper method which determines if a given spaces within b is a valid placement according to the diagonal placement of other Queens on the Board.
	 *  @param r the row to check
	 * @param c the column to check
	 * @param QP a list of Queens that have been place so far
	 * @return true if the b[r][c] is a "safe" space to place a new Queen
	 */
	private boolean isDiagonalClear(int r, int c, LinkedList<Queen> QP) {
		//Major diagonal = top left --> bottom right
		//A shorter recursive version of this method can be found in the heap of old code that now occupies my PseudoCode file
		
		int row = r; //Pointers which walk the Board
		int col = c;
		
		//check towards 0,0
		while(row >= 0 && col >= 0) { 
			if(hasQueen(row, col, QP) )
				return false;
			row--; //decrement
			col--;
		}
		
		//Reset pointers
		row = r;
		col = c;
		
		//check towards n,n
		while(row < NQ && col < NQ ) { 
			if(hasQueen(row, col, QP) )
				return false;
			row++; //Increment 
			col++;
		}
		
		//Minor diagonal = top right--> bottom left //
		
		row = r;
		col = c;
		
		//Towards bottom left
		while(row >=  0 && col < NQ ) { 
			if(hasQueen(row, col, QP) )
				return false;
			row--; //decrement to the left
			col++;//Increment to go towards 0, n
		}
		
		//Reset counters
		row = r;
		col = c;
		
		//Towards top right
		while(row <  NQ && col >= 0) { 
			if(hasQueen(row, col, QP) )
				return false;
			row++; //Increment to the right
			col--;//Decrement towards n, 0
		}

		return true;	//If no queens found return true		
	}
	
	/**
	 * A helper method which ensures that a pair of indices for the Board are inbounds
	 * @param r the row to check
	 * @param c the column to check
	 * @return true if the b[r][c] is in bounds
	 */
	private boolean isOutOfBounds(int r, int c) {
		return r < 0 || r >= NQ || c < 0 || c >= NQ;
	}
	
	/**
	 * The wrapper method for recursive calls to addQueens() which instantiates an empty list of placed Queens.
	 * @param startR the row to begin searching from
	 * @param startC the column to being searching from
	 * @return true if a solution was found
	 */
	public boolean solve(int startR, int startC) {
		LinkedList<Queen> list = new LinkedList<Queen>(); 
		return addQueens(startR, startC, list); //Start with empty list
	}
	
	/**
	 * The recursive algorithm which iterates over the Board, searching for safe places to addQueens
	 * @param r the row to start from
	 * @param c the column to begin searching from
	 * @param QP a LinkedList of Queens placed so far
	 * @return true if NQ queens were placed, false if fewer Queens were able to be placed
	 */
	public boolean addQueens(int r, int c, LinkedList<Queen> QP) {
		//Base Case: check if finished
		if(QP.size() == NQ) {
			for(Queen q : QP) 
				setQueen(q); //Copy the list to the Board
			return true;
		} else {
			for(int j = c; j < NQ + c; j++) { 
				if( isValid(r, j, QP) ) {
					LinkedList<Queen> temp = (LinkedList<Queen>) QP.clone();
					temp.add( new Queen(r, j) );
					if( addQueens(r+1, 0, temp) == true)	//If can place queens
						return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * A helper method which verifies if a Queen is placed at the given coordinates of the Board.
	 * @param r the row to check
	 * @param c the column to check
	 * @return true if a Queen is placed at b[r][c], false if not
	 */
	public boolean isQueen(int r, int c) {
		return b[r][c].equals("Q");
	}
	
	/**
	 * A getter method which returns the piece at a location
	 * @param r the row to fetch
	 * @param c the column to fetch
	 * @return the String value of the piece at that location: "Q" if Queen, " " if not 
	 */
	public String getPiece(int r, int c) {
		return b[r][c];
	}
	
	/**
	 * A helper method which compares a list of Queens to a given point on a board
	 * @param r the row to check
	 * @param c the column to check
	 * @param QP the list of Queens to compare to the given place on the Board
	 * @return true if a Queen on the list exists at the given coordinates
	 */
	private boolean hasQueen(int r, int c, LinkedList<Queen> QP) {
		for(Queen q : QP) {
			if(q.getR() == r && q.getC() == c)
				return true;
		}
		return false;
	}
	
	/**
	 * A setter method which changes the value of a position on the Board according to the Queen 
	 * @param q the Queen to be "copied" to the Board
	 */
	public void setQueen(Queen q) {
		b[q.getR()][q.getC()] = "Q";
	}
	
	/**
	 * A helper method which verifies if one Board is the same as another
	 * @param other the Board to be compared to 
	 * @return true if all the pieces on the Board on the same
	 */
	public boolean equals(Board other) {
		for(int c = 0; c < NQ; c++) {
			for(int r = 0; r < NQ; r++) {
				String piece = other.getPiece(r, c);
				if(!b[r][c].equals(piece) )
					return false;
			}
		}
		return true;
	}
	
	/**
	 * The toString method which displays a Board in the console as "X"s and "Q"s
	 * @return the String representation of the Board
	 */
	public String toString() {
		String result = "";
		for(int c = 0; c < NQ; c++) {
			for(int r = 0; r < NQ; r++) {

				if(b[r][c].equals("Q") )
					result += b[r][c] + "  ";
				else 
					result += "X  ";
			}
			result += "\n";
		}
		return result;
	}
	
	public static void main(String[] args) {
		//TESTS FOR A SINGLE BOARD
		Board x = new Board();
		System.out.println( x.solve(0, 0) );
		System.out.println(x);
		System.out.println();

	}
}
