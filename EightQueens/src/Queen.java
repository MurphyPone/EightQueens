public class Queen {
	private int r;
	private int c;
	
	public Queen() {
		r = 0;
		c = 0;
	}
	
	public Queen(int row, int col) {
		r = row;
		c = col;
	}

	public int getR() { return r; }
	public int getC() { return c; }
	
	public String toString() {
		return "(" + r + ", " + c + ")";
	}
}
