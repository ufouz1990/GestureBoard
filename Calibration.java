public class Calibration
{
	Point3D q, p, z, m;
	double initialz = 0;
	private static final double DISTANCE = 495;

	public void calPoints(Point3D[] points){
		q = points[0];
		p = points[1];
		z = points[2];
		m = points[3];
		initialz = (q.z + p.z + z.z + m.z)/4.0;
	}

	public double getInitialZ(){
		return initialz; 
	}

	public boolean isPressing(Point3D p)
	{
		return p.z >= initialz + initialz*0.2;
	}

	public Point2D ratio(Point3D point){
		double bigD = Math.sqrt(Math.pow(p.x - q.x, 2)+Math.pow(p.y - q.y, 2));
		double r = (bigD/ DISTANCE);
		double littlex = r*point.x;
		double littley = r*point.y;
		System.out.println("2D points: ("+littlex+", "+littley+")" );
		return new Point2D(littlex, littley);

	}

	
}
