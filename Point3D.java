
public class Point3D {
	public double x;
	public double y;
	public double z;
	boolean hasInitial = false;
	double initialz = 0;
	
	public Point3D() 
	{
		x = y = z = 0;
		if(hasInitial == false)
		{
			initialz = z;
		}
	}
	
	public Point3D(double x,double y,double z) 
	{
		this.x = x;
		this.y = y;
		this.z = z;
		if(hasInitial == false)
		{
			initialz = z;
		}
	}
	
	public String toString()
	{
		return "("+(Math.round(x*10)/10d)+", "+(Math.round(x*10)/10d)+", "+(Math.round(x*10)/10d)+")";
	}
}
