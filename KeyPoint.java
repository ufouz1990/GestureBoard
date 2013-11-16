public class KeyPoint {
	public double row;
	public double col;
	
	public KeyPoint() {
		row = col = 0;
	}
	
	public KeyPoint(double row,double col) {
		this.row = row;
		this.col = col;
	}
	
	public boolean equals(KeyPoint point) {
		return (row == point.row && col == point.col);
	}
	
	public String toString() {
		return "("+(Math.round(row*10)/10d)+", "+(Math.round(col*10)/10d)+")";
	}
}
