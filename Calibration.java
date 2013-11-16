import java.awt.*;

public class Calibration
{
	Point3D q, m;
	double initialz = 0;
	double rx,ry;

	private static final double DISTANCE_X = 359;
	private static final double DISTANCE_Y = 102;

	public void calPoints(Point3D[] points){
		q = points[0];
		m = points[1];
		initialz = (q.z + m.z)/2.0;
		double distX = m.x-q.x;
		double distY = q.y-m.y;
		rx = (DISTANCE_X/distX);
		ry = (DISTANCE_Y/distY);
	}

	public double getInitialZ(){
		return initialz; 
	}

	public boolean isPressing(Point3D p)
	{
		return p.z >= initialz + initialz*0.2;
	}

	public Point getScreenCoords(Point3D point){
		Rectangle qRect = ScreenMap.CHAR_TO_POINT.get('Q');
		Rectangle mRect = ScreenMap.CHAR_TO_POINT.get('M');
		double littlex = qRect.x+(qRect.width/2)+rx*(point.x-q.x);
		double littley = qRect.y+(qRect.width/2)-ry*(point.y-q.y);
		//System.out.println("2D points: ("+littlex+", "+littley+")" );
		return new Point(littlex, littley);

	}

	
}
