public class Point2D{
	double x;
	double y;
	public Point2D(double x, double y){
		this.x = x;
		this.y = y;
	}
	public String toString(Point2D point){
		return ("("+point.x+", "+point.y+")");
	}
}