import com.leapmotion.Vector;

public class Vector3D {
	private Point3D location;
	private Vector velocity;
	
	public Vector3D(Point3D location,Vector velocity) {
		this.location = location;
		this.velocity = velocity;
	}
	
	public Point3D getLocation() {
		return location;
	}
	
	public Vector getVelocity() {
		return velocity;
	}
}
