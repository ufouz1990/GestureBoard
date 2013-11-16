import com.leapmotion.leap.Vector;

public class VectorPacket {
	private Vector location;
	private Vector velocity;
	
	public VectorPacket(Vector location,Vector velocity) {
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
