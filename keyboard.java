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
		boolean pressing = false;
		LinkedList<Point3D> points = new LinkedList<Point3D>();
		Vector lastDirection = new Vector();

		while (true) {
			VectorPackage vector = LeapDataPipeline.pipeline.getVectorPkt();

			if (!pressing && vector.getLocation().z < -1) {
				pressing = true;
				points.add(vector.getLocation());
			} else
			if (pressing && vector.getLocation().z >= -1) {
				pressing = false;
				points.add(vector.getLocation());
			}

			if (pressed) {
				Vector direction = vector.normalized();
				boolean xChange = Math.abs(direction.getX()-lastDirection.getX());
				boolean yChange = Math.abs(direction.getY()-lastDirection.getY());
				boolean zChange = Math.abs(direction.getZ()-lastDirection.getZ());
				lastDirection = direction;

				double directionThreshold = 0.2;
				double velocityThreshold = 0.1;
				if (xChange > directionThreshold || yChange > directionThreshold || zChange > directionThreshold) {
					points.add(vector.getLocation());
				} else
				if (vector.magnitude() < velocityThreshold) {
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
					
				}
				else {

				}
				points.clear();
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

