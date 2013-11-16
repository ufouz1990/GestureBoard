import com.leapmotion.leap.Vector;

public class Vector3D {
	private Vector location;
	private Vector velocity;
	
	public Vector3D(Vector location,Vector velocity) {
		this.location = location;
		this.velocity = velocity;
	}
	
	public Vector getLocation() {
		return location;
	}
	
	public Vector getVelocity() {
		return velocity;
	}
}
