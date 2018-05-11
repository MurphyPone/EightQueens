import java.util.ArrayList;

public class SolvedBoards {
	private ArrayList<Board> boards;
	
	public SolvedBoards() {
		int numBoards = (int) Math.pow(BoardFrame.NUM_QUEENS, 2);
		boards = new ArrayList<Board>(numBoards); //#solution ~= N^2 + 2N
		
		for(int r = 0; r < BoardFrame.NUM_QUEENS; r ++) {
			for(int c = 0; c < BoardFrame.NUM_QUEENS; c ++) {
				Board temp = new Board();
				if( temp.solve(r, c ) == true) { //use % for more solutions?
					boards.add(temp); //If a solution is found at the given starting position, then add it to the boards
				}
			}
		}
	}
	
	public Board get(int i ) {
		return boards.get(i);
	}
	
	public boolean add(Board b) {
		boards.add(b);
		return true;
	}
	
	public String toString() {
		String result = "There are " + boards.size() + " solutions: \n";
		for(Board b : boards ) {
			result +=  b + "-----------------------\n";
		}
		
		return result;
	}

	public static void main(String[] args) {
		SolvedBoards x = new SolvedBoards();
		System.out.println(x);

	}

}
