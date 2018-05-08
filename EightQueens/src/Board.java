import java.util.LinkedList;

//Handles the logic 
public class Board {
	private final static int NQ = BoardFrame.NUM_QUEENS;
	private int placedQueens;
	private String[][] b; //Could be boolean? --> cannot check null for bool
	
	public Board() {
		b = new String[NQ][NQ];
		fillBoard(); //config
		placedQueens = 0;
	}
	
	//CONSTRUCTOR HELPER// 
	private void fillBoard() {
		for(int c = 0; c < NQ; c++) {
			for(int r = 0; r < NQ; r++) {
				b[r][c] = " "; 
			}
		}
	}
	
	//Logic
	private boolean isValid(int r, int c, LinkedList<Queen> QP) {
		for(Queen q : QP) {
			if(q.getR() == r || q.getC() == c ) //obfuscate
				return false;
			if(isDiagonalClear(r, c, 0))
				return false;	
		}
		return true;
	}
	
	private boolean isDiagonalClear(int r, int c, int dirFrom) { //top-left, top-right, bottom-left, bottom-right : 1 2 3 4 -- 0 = all
		//Base cases
		if( isOutOfBounds(r, c) ) { return true; } //Reached end of board
		if( isQueen(r, c) ) { return false; } //conflict
		//Recurse
		if(dirFrom == 1) { return isDiagonalClear(r-1, c-1, 1); }	//top left
		if(dirFrom == 2) { return isDiagonalClear(r+1, c-1, 2); }	//top right
		if(dirFrom == 3) { return isDiagonalClear(r-1, c+1, 3); }	//bottom left
		if(dirFrom == 4) { return isDiagonalClear(r+1, c+1, 4); }	//bottom right
		//if 0, then call all with respective dir
		return  isDiagonalClear(r-1, c-1, 1) && isDiagonalClear(r+1, c-1, 2) 
				&& isDiagonalClear(r-1, c+1, 3) &&isDiagonalClear(r+1, c+1, 4);
	}
	
	private boolean isOutOfBounds(int r, int c) {
		return r < 0 || r >= NQ || c < 0 || c >= NQ;
	}
	
	public boolean solve() {
		LinkedList<Queen> list = new LinkedList<Queen>();
		return addQueens(0, 0, list); //Start with empty list
	}
	
	public boolean isSolution() {
		return (placedQueens == 8);
	}
	
	//End Logic//
	
	public boolean addQueens(int r, int c, LinkedList<Queen> QP) {
		//Base Case: check if finished
		if(QP.size() == NQ) {
			for(Queen q : QP) 
				setQueen(q); //update the board to match the List
			return true;
		} else if( !isValid(r, c, QP) ) { 
			return false; 
		} else {
			for(int j = 0; j < NQ; j++) { //Unnecessary loops, but O(n) even if I keep track of current col as a param
				if( isValid(r, j, QP) ) {
					LinkedList<Queen> temp = (LinkedList<Queen>) QP.clone();
					temp.add( new Queen(r, j) );
					return addQueens(r+1, 0, temp);
				}
			}
		}
		return false;
	}
	
	//GETTERS AND SETTERS//
	public boolean isQueen(int r, int c) {
		return b[r][c].equals("Q");
	}
	
	public void setQueen(Queen q) {
		b[q.getR()][q.getC()] = "Q";
	}
	
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
		return result + "\nQueens Placed: " + placedQueens;
	}
	
	public static void main(String[] args) {
		Board x = new Board();
		System.out.println( x.solve() );
		System.out.println(x);
		System.out.println();

	}
}
