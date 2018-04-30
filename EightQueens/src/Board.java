//Handles the logic 
public class Board {
	private final static int NQ = BoardPanel.NUM_QUEENS;
	private int placedQueens;
	private String[][] b;
	
	public Board() {
		b = new String[NQ][NQ];
		fillBoard(); //config
		placedQueens = 0;
	}
	
	//Logical Methods //Should hash every position 
	private boolean isRowClear(int r, int c) {
		if(b[r][c] == null ) { return true; } //Reached end of board
		if(b[r][c].equals("Q") ) { return false; } //conflict
		//else if(board[r][c].equals(" ") ) 
		return isRowClear(r-1, c) && isRowClear(r+1, c); //If empty, 
	}
	
	private boolean isColClear(int r, int c) {
		if(b[r][c] == null ) { return true; } //Reached end of board
		if(b[r][c].equals("Q") ) { return false; } //conflict
		//else if(board[r][c].equals(" ") ) 
		return isColClear(r, c-1) && isRowClear(r, c+1); //If empty, 
	}
	
	private boolean isDiagonalClear(int r, int c) {
		if(b[r][c] == null ) { return true; } //Reached end of board
		if(b[r][c].equals("Q") ) { return false; } //conflict
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
	
	//End Logic//
	
	//Helper setup 
	private void fillBoard() {
		for(int r = 0; r < NQ; r++) {
			for(int c = 0; c < NQ; c++) {
				b[r][c] = " "; 
			}
		}
	}
	
	
	public void addQueens() {
		while(placedQueens < NQ) {
			for(int r = 0; r < NQ; r++) {
				for(int c = 0; c < NQ; c++) {
					if( isClear(r, c) ) {
						b[r][c] = "Q";
						placedQueens++;
					}
				}
			}
		}
	}
	
	public String toString() {
		String result = "";
		for(int r = 0; r < NQ; r++) {
			for(int c = 0; c < NQ; c++) {
				if(b[r][c].equals("Q") )
					result += b[r][c];
				else 
					result += "X ";
			}
			result += "\n";
		}
		return result;
	}
	
	public static void main(String[] args) {
		Board x = new Board();
		//x.addQueens();
		System.out.println( x.isSolution() );
		System.out.println(x);

	}
}
