public class Calibration
{
	Point3D q, p, z, m;
	double initialz = 0;

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

	
}
