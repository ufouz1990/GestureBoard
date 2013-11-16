import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.leapmotion.leap.*;
public class TouchPointerExample extends JFrame {
	
	JPanel contentPane;
	
	Controller leap;
	//TouchListener listener;
	
	int width = 1500;
	int height = 800;
	Color cursorNormalColor = new Color(0, 0, 128);
	Color cursorHoverColor = new Color(0, 128, 0);
	Color cursorTouchColor = new Color(128, 0, 0);
	Color backgroundColor = new Color(250, 235, 200);

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TouchPointerExample frame = new TouchPointerExample();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public TouchPointerExample()
	{
		
	  leap = new Controller();
	  
	  
	  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, width, height);
		contentPane = new JPanel(){
			public void paintComponent(Graphics g){
				super.paintComponent(g);
				draw(g);
				try {Thread.sleep(50);} 
				catch (InterruptedException e) {e.printStackTrace();}
				repaint();
		}};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		//Graphics2D g2d = (Graphics2D)contentPane.getGraphics();
		//g2d.setBackground(backgroundColor);
		
		
	  

	}

	void draw(Graphics g)
	{
	  InteractionBox iBox = leap.frame().interactionBox();
	  PointableList pointables = leap.frame().pointables();
	  for(int p = 0; p < pointables.count(); p++)
	  {
	    Pointable pointable = pointables.get(p);
	    Vector normalizedPosition = iBox.normalizePoint(pointable.stabilizedTipPosition());
	    float pixelX = normalizedPosition.getX() * width;
	    float pixelY = height - normalizedPosition.getY() * height;
	    int cx = (int) pixelX - 20;
	    int cy = (int) pixelY - 20;

	    Pointable.Zone fingerzone = pointable.touchZone();

	    if(pointable.touchDistance() > 0 && fingerzone != Pointable.Zone.ZONE_NONE)
	    {
	    	g.setColor(colorWithAlpha(cursorHoverColor, (int)(64 - pointable.touchDistance() * 48)));
	        g.fillOval(cx, cy, 40, 40);
	        g.setColor(cursorHoverColor);
	        g.drawOval(cx, cy, 40, 40);
	        
	    }
	    else if(pointable.touchDistance() <= 0)
	    {
	    	g.setColor(colorWithAlpha(cursorTouchColor, (int)(64 - pointable.touchDistance() * 126)));
	    	g.fillOval(cx, cy, 40, 40);
	    	g.setColor(cursorTouchColor);
	        g.drawOval(cx,cy,40,40);
	    	
	    }
	    else
	    {
	    	g.setColor(colorWithAlpha(cursorNormalColor, 16));
	    	g.fillOval(cx, cy, 40, 40);
	    	g.setColor(cursorNormalColor);
	    	g.drawOval(cx, cy, 40, 40);
	       
	    }
	    
	  }
	}

	private Color colorWithAlpha(Color rgb, int alpha)
	{
	   return new Color(rgb.getRed(),rgb.getGreen(), rgb.getBlue(), alpha);
	}
}
