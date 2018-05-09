import java.util.ArrayList;
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
			if(!isDiagonalClear(r, c, QP))
				return false;	
		}
		return !isOutOfBounds(r, c); //lastly, ensure that the coords are inbounds
	}
	
	private boolean isDiagonalClear(int r, int c, LinkedList<Queen> QP) {
		//Major diagonal = top left --> bottom right
		
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
	
	private boolean isOutOfBounds(int r, int c) {
		return r < 0 || r >= NQ || c < 0 || c >= NQ;
	}
	
	public boolean solve() {
		LinkedList<Queen> list = new LinkedList<Queen>();
		/*ArrayList<LinkedList<Queen>> solutions = new ArrayList<LinkedList<Queen>>();  
		for(int i = 0; i < NQ; i++) {
			solutions.add
		}
		*/
		return addQueens(0, 0, list); //Start with empty list
	}
	
	//End Logic//
	
	public boolean addQueens(int r, int c, LinkedList<Queen> QP) {
		//Base Case: check if finished
		if(QP.size() == NQ) {
			for(Queen q : QP) 
				setQueen(q); //update the board to match the List
			return true;
		// } else if( !isValid(r, c, QP) ) { //Breaks out too early
		//	return false; 
		} else {
			for(int j = 0; j < NQ; j++) { //Unnecessary loops, but O(n) even if I keep track of current col as a param
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
	
	//GETTERS AND SETTERS//
	public boolean isQueen(int r, int c) {
		return b[r][c].equals("Q");
	}
	
	//Compares a list of Queens to a given point on a board
	private boolean hasQueen(int r, int c, LinkedList<Queen> QP) {
		for(Queen q : QP) {
			if(q.getR() == r && q.getC() == c)
				return true;
		}
		return false;
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
		return result;
	}
	
	public static void main(String[] args) {
		Board x = new Board();
		System.out.println( x.solve() );
		System.out.println(x);
		System.out.println();

	}
}
