public class Point3DAux{
	public static void main(String[] args){
		Point3D[] points = {new Point3D(100.0, 200.0, 10.0), new Point3D(300.0, 300.0,200.0),new Point3D(100.0, 400.0, 300.0),new Point3D(100.0, 500.0, 400.0)};
		Calibration init = new Calibration();
		init.calPoints(points);
		System.out.println("initial z in calibration: "+init.getInitialZ());
		Point3D nextPoint = new Point3D(100.0, 200.0, 300.5);
		System.out.println(init.isPressing(nextPoint));
		init.ratio(nextPoint);

	}
}