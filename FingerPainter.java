import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Finger;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.Gesture;
import com.leapmotion.leap.Listener;
import com.leapmotion.leap.Pointable;
import com.leapmotion.leap.Vector;

import java.awt.GridBagLayout;


public class FingerPainter extends JFrame {

	private JPanel contentPane;
	private Controller controller;
	private FingerListener listener;
	
	private static int width = 900;
	private static int height = 900;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FingerPainter frame = new FingerPainter();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FingerPainter() {
		
		controller = new Controller();
		listener = new FingerListener();
		
		controller.addListener(listener);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, width, height);
		contentPane = new JPanel(){
			public void paintComponent(Graphics g){
				//super.paintComponent(g);
				fingerPaint(g);
				try {Thread.sleep(50);} 
				catch (InterruptedException e) {e.printStackTrace();}
				repaint();
		}};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0};
		gbl_contentPane.rowHeights = new int[]{0};
		gbl_contentPane.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
	}
	
	
	private void fingerPaint(Graphics g){
		System.out.println(listener.x+" "+listener.y+" "+listener.velocity);
		
		
		
		int radius = Math.min((int)(2500/listener.velocity), 50);
		
		g.fillOval(width/2 + (int)listener.x - radius, height - (int)listener.y - radius,radius*2,radius*2);
		
	}

}

class FingerListener extends Listener{
	
	public double x;
	public double y;
	public double velocity;
	
	
	public void onInit(Controller controller) {
        System.out.println("Initialized");
    }

    public void onConnect(Controller controller) {
        System.out.println("Connected");
        controller.enableGesture(Gesture.Type.TYPE_SWIPE);
        controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
        controller.enableGesture(Gesture.Type.TYPE_SCREEN_TAP);
        controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
        
  
        
    }

    
    public void onDisconnect(Controller controller) {
        //Note: not dispatched when running in a debugger.
        System.out.println("Disconnected");
    }

    public void onExit(Controller controller) {
        System.out.println("Exited");
    }
    
    public void onFrame(Controller controller) {
    	
        Frame frame = controller.frame();
        System.out.println("Frame id: " + frame.id()
                         + ", timestamp: " + frame.timestamp()
                         + ", hands: " + frame.hands().count()
                         + ", fingers: " + frame.fingers().count()
                         + ", tools: " + frame.tools().count()
                         + ", gestures " + frame.gestures().count());
        
        if(!frame.hands().isEmpty()){
        	Finger finger = frame.fingers().frontmost();
        	
        	Vector position = finger.tipPosition();
        	x = position.getX();
        	y = position.getY();
        	
        	Vector veloc = finger.tipVelocity();
        	velocity = veloc.magnitude();
        	
        	
        }
    	
    }
	
}

