/**
 * Class Description: This class represents a Queen in a game of Chess. It has fields which keep track of it's coordinates on the Board
 * @author: MurphyP1
 * @date: 5/10/18
 */

public class Queen {
	private int r; //row
	private int c; //column
	
	/**
	 * The constructor for a Queen which accepts row and column values for the Queen
	 * @param row the row
	 * @param col the column
	 */
	public Queen(int row, int col) {
		r = row;
		c = col;
	}

	/**
	 * A getter method which returns the row of the Queen.
	 * @return an integer representing the row of the Queen
	 */
	public int getR() { return r; }
	/**
	 * A getter method which returns the column of the Queen.
	 * @return an integer representing the column of the Queen
	 */
	public int getC() { return c; }
	
	/**
	 * The toString method for a Queen which displays the position of the Queen on the Board
	 * @return the coordinate pair for a Queen
	 */
	public String toString() {
		return "(" + r + ", " + c + ")";
	}
}
