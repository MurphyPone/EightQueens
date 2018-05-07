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
	
	//Logical Methods //Should hash every position 
	private boolean isRowClear(int r, int c, int dirFrom) {
		//Base Cases
		if( isOutOfBounds(r, c) ) { return true; } //Reached end of board
		if( isQueen(r, c) ) { return false; } //conflicting Queen
		//Recurse
		if(dirFrom == -1 ) { return isRowClear(r-1, c, -1 ); } //search left
		if(dirFrom == 1) { return isRowClear(r+1, c, 1); } //search right 
		
		return isRowClear(r-1, c, -1) && isRowClear(r+1, c, 1 ); //one call to the left and one call to the right
	}
	
	private boolean isColClear(int r, int c, int dirFrom) {
		//Base Cases
		if( isOutOfBounds(r, c) ) { return true; } //Reached end of board
		if( isQueen(r, c) ) { return false; } //conflicting Queen
		//Recurse
		if(dirFrom == -1 ) { return isColClear(r, c-1, -1 ); } //search left
		if(dirFrom == 1) { return isColClear(r, c+1, 1); } //search right 
		//If not left or right, call both
		return isColClear(r, c-1, -1) && isColClear(r, c+1, 1 ); //one call to the left and one call to the right
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
		return isRowClear(r, c, 0) && isColClear(r, c, 0) && isDiagonalClear(r, c, 0); 
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
	
	//GETTERS AND SETTERS//
	public boolean isQueen(int r, int c) {
		return b[r][c].equals("Q");
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
		x.addQueens();
		System.out.println( x.isSolution() );
		System.out.println(x);
		System.out.println();

	}
}
