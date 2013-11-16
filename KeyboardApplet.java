import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;

public class KeyboardApplet extends Applet {
	private Image keyboardImg;
	private Image bufferedImage;
	private Graphics bufferedGraphics;

	//TEMP
	private int x;
	private int y;

	public KeyboardApplet() {
		keyboardImg = new ImageIcon("keyboardImage.jpg").getImage();
	
		setSize(keyboardImg.getWidth(this),keyboardImg.getHeight(this));

		//TEMP
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				if (ke.getKeyCode() == KeyEvent.VK_UP)
					y--;
				if (ke.getKeyCode() == KeyEvent.VK_DOWN)
					y++;
				if (ke.getKeyCode() == KeyEvent.VK_LEFT)
					x--;
				if (ke.getKeyCode() == KeyEvent.VK_RIGHT)
					x++;
				System.out.println("("+x+", "+y+")");
				repaint();
			}
		});
	}

	public void paint(Graphics g) {
		g.drawImage(keyboardImg,0,0,keyboardImg.getWidth(this),keyboardImg.getHeight(this),this);
	
		//TEMP
		g.setColor(Color.RED);
		g.fillOval(x-1,y+1,3,3);
	}

	public void update(Graphics g) {
		if (bufferedGraphics == null) {
			bufferedImage = createImage(getSize().width,getSize().height);
			bufferedGraphics = bufferedImage.getGraphics();
		}

		bufferedGraphics.setColor(Color.BLACK);
		bufferedGraphics.fillRect(0,0,getSize().width,getSize().height);

		paint(bufferedGraphics);

		g.drawImage(bufferedImage,0,0,getSize().width,getSize().height,this);
	}
}