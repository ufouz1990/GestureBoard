import java.awt.*;

public class ScreenPoint{
	public double x;
	public double y;
	public double width;
	public double height;

	public ScreenPoint(Rectangle rec){
		x = this.x;
		y = this.y;
		width = this.width;
		height = this.height;
	}

	public boolean equals(ScreenPoint point){
		return(x == point.x && y == point.y && width == point.width && height == point.height);
	}
}