import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import com.leapmotion.leap.Vector;
	
	class keyboard extends JFrame{
	  ImageIcon image = new ImageIcon("keyboardImage.jpg"); //the image
	  Words[] boxes = new Words[5];
	  KeyboardApplet keyboard;

	  public keyboard() {
		setTitle("Keyboard");
		setSize(840,335); // default size is 0,0
		setResizable(false); //so the user does not resize the window
		setLocationRelativeTo(null); // default is 0,0 (top left corner)
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Container mainPane = getContentPane();
		mainPane.setLayout(new BorderLayout());

		Container pane = new JPanel(); //sets up container
		pane.setLayout(new GridBagLayout()); //sets up layout
		GridBagConstraints c = new GridBagConstraints();
		
		//Sets up output boxes
		for(int i = 0; i <= 4; i++) {
		Words rec = new Words();
		boxes[i] = rec;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = i;
		c.gridy = 0;
		c.ipady = 20;
		c.weightx = .5;
		c.ipadx = 10;
		pane.add(rec, c);
		rec.repaint();
		}

		mainPane.add("North",pane);
		keyboard = new KeyboardApplet();
		keyboard.repaint();
		mainPane.add("Center",keyboard);

		new Thread() {
			public void run() {
				loop();
			}
		}.start();
	}

	private void loop() {
		Calibration calibration = new Calibration();
		boolean pressed = false;
		LinkedList<Point3D> points = new LinkedList<Point3D>();
		Vector lastDirection = new Vector();
		
		LinkedList<Character> letters = new LinkedList<Character>();
		
		// Z threashold
		double zThresh = -1;
		
		// Calibration loop
		Point3D[] cal_points = new Point3D[2];
		cal_points[0] = cal_points[1] = null;
		while (true) {
			VectorPacket vector = LeapDataPipeline.pipeline.getVectorPkt();
			
			if (vector != null) {
			
			if (!pressed && vector.getLocation().z < zThresh)
				if (cal_points[0] == null) {
					cal_points[0] = vector.getLocation();
					System.out.println(cal_points[0]);
				}
				else {
					cal_points[1] = vector.getLocation();
					System.out.println(cal_points[1]);
				}
			if (vector.getLocation().z < zThresh)
				pressed = true;
			else
				pressed = false;
			
			}
			
			if (cal_points[1] != null)
				break;
			
			try {
				Thread.currentThread().sleep(50);
			}
			catch(InterruptedException ie) {}
		}

		while (true) {
			VectorPacket vector = LeapDataPipeline.pipeline.getVectorPkt();
			
			if (vector != null) {
			
			if (!pressed && vector.getLocation().z < zThresh) {
				pressed = true;
				points.add(vector.getLocation());
			} else
			if (pressed && vector.getLocation().z >= zThresh) {
				pressed = false;
				points.add(vector.getLocation());
			}

			if (pressed) {
				Vector direction = vector.getVelocity().normalized();
				double xChange = Math.abs(direction.getX()-lastDirection.getX());
				double yChange = Math.abs(direction.getY()-lastDirection.getY());
				double zChange = Math.abs(direction.getZ()-lastDirection.getZ());
				lastDirection = direction;

				double directionThreshold = 0.2;
				double velocityThreshold = 0.1;
				if (xChange > directionThreshold || yChange > directionThreshold || zChange > directionThreshold) {
					points.add(vector.getLocation());
				} else
				if (vector.getVelocity().magnitude() < velocityThreshold) {
					points.add(vector.getLocation());
				}
			} else
			if (points.size() > 0) {
				LinkedList<Point> screenPoints = new LinkedList<Point>();
				for (int i = 0;i < points.size();i++) {
					Point screenPnt = calibration.getScreenCoords(points.get(i));
					if (i == 0 || !screenPnt.equals(points.get(i-1)))
						screenPoints.add(screenPnt);
				}
				if (points.size() == 1) {
					letters.add(ScreenMap.selectKey(new Point2D(points.get(0).x,points.get(0).y)));
					String cur = boxes[0].getWord()+letters.getLast();
					boxes[0].setWord(cur);
					boxes[0].repaint();
				}
				else {

				}
				points.clear();
			}
			
			}

			try {
				Thread.currentThread().sleep(50);
			}
			catch(InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}

	private void updateBoxes(String[] words) {
		for (int i = 0;i < words.length;i++) {
			boxes[i].setWord(words[i]);
			boxes[i].repaint();
		}
	}
}

