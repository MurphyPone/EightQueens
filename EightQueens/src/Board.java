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
			if(q.getR() == r || q.getC() == c )
				return false;
			if(isDiagonalClear(r, c, 0))
				return false;	
		}
		QP.add(new Queen(r, c));
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
	
	public boolean isClear(int r, int c) {
		LinkedList<Queen> Q_P = new LinkedList<Queen>();
		return isValid(r, c, Q_P); 
	}
	
	public boolean isSolution() {
		return (placedQueens == 8);
	}
	
	//End Logic//
	
	public void addQueens() {
		//Pretty sure this recurses infinitely
		int count = 0;
		//while( !isSolution() ) {
			for(int c = 0; c < NQ; c++) {
				for(int r = 0; r < NQ; r++) {

					if( isClear(r, c) ) {
						b[r][c] = "Q";
						placedQueens++;
					}
				}
			}
		//}
	}
	
	
	public boolean addQueens(LinkedList<Queen> QP) {
		if(QP.size() == NQ) {
			for( Queen q : QP ) {
				b[q.r][q.c] = "Q";
				return true; //copy over the realy Queens
			}
		} else {
			for(int r = 0; r < NQ; r++ ) {
				for(int c = 0; c < NQ c++; ) {
					if ( isValid(r, c) )
						return true;
					else 
						return false/break?
				}
			}
		}
	}
	
	//GETTERS AND SETTERS//
	public boolean isQueen(int r, int c) {
		return b[r][c].equals("Q");
	}
	
	public void setQueen(int r, int c) {
		b[r][c] = "Q";
	}
	
	public String toString() {
		String result = "";
		for(int c = 0; c < NQ; c++) {
			for(int r = 0; r < NQ; r++) {

				if(b[r][c].equals("Q") )
					result += b[r][c] + " ";
				else 
					result += "X ";
			}
			result += "\n";
		}
		return result + "\nQueens Placed: " + placedQueens;
	}
	
	public static void main(String[] args) {
		Board x = new Board();
		x.setQueen(2, 0);
		x.addQueens();
		System.out.println( x.isSolution() );
		System.out.println(x);
		System.out.println();

	}
}
