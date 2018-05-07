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

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}
	
	public void setRC(int row, int col) {
		r = row;
		c = col;
	}
}
