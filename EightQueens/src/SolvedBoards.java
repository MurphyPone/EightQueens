/**
 * Class Description: This class represents a collection of chess boards which are solutions to the NQueens problem.
 * @author: MurphyP1
 * @date: 5/10/18
 */

import java.util.ArrayList;

public class SolvedBoards {
	private ArrayList<Board> boards;
	
	/**
	 * The constructor for a SolvedBoards class which iterates over N rows and N columns creating Boards and calling their solve methods, attempting to find new solutions.
	 */
	public SolvedBoards() {
		boards = new ArrayList<Board>(); 
		//boards.add(new Board()); //First board is blank by default
		
		for(int i = 0; i < 10; i++ ) { //Go bakc and find more solutions from the same starting positions --Doesn't actually find new one
			for(int r = 0; r < BoardFrame.NUM_QUEENS; r ++) {
				for(int c = 0; c < BoardFrame.NUM_QUEENS; c ++) {
					Board temp = new Board();
					if( temp.solve(r, c ) == true) { //use % for more solutions? --Doesn't!!!!
						if(!contains(temp))	//Don't add duplicates
							boards.add(temp); //If a solution is found at the given starting position, then add it to the boards
					}
				}
			}
		}
	}
	
	/**
	 * A wrapper for the ArrayList.get() method. 
	 * @param i the index to fetch
	 * @return the Board at that index within the boards ArrayList
	 */
	public Board get(int i ) {
		return boards.get(i);
	}
	
	/**
	 * A wrapper method for the ArrayList.add() method.
	 * @param b the Board to be added
	 * @return true.  Always. No matter what.
	 */
	public boolean add(Board b) {
		boards.add(b);
		return true;
	}
	
	/**
	 * A wrapper method for the ArrayList.size method.
	 * @return an integer representing the amount of elements within the boards field
	 */
	public int size() {
		return boards.size();
	}
	
	/**
	 * A wrapper method for the ArrayList.contains method.
	 * @param b the Board to check
	 * @return true if b already exists within the boards field
	 */
	public boolean contains(Board b) {
		return boards.contains(b);
		
		/*for(Board x : boards) { //NOTE TO SELF, DO NOT ATTEMPT TO RE-INVENT THE .CONTAINS WHEEL. BC THEN YOU WILL ONLY FIND 8 SOLUTIONS
			if(x.equals(b))
				return true;
		}*/
	}
	
	/**
	 * The toString method which outputs all the Boards within the boards field.
	 * @return a String representation of all of the Boards within the boards field.
	 */
	public String toString() {
		String result = "There are " + boards.size() + " solutions: \n";
		for(Board b : boards ) {
			result +=  b + "-----------------------\n";
		}
		
		return result;
	}


	public static void main(String[] args) {
		//TESTS FOR SOLVED BOARDS
		SolvedBoards x = new SolvedBoards();
		System.out.println(x);
	}
}
