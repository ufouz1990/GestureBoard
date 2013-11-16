import com.leapmotion.leap.Vector;

public class VectorPacket {
	private Point3D location;
	private Vector velocity;
	
	public VectorPacket(Point3D location,Vector velocity) {
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
