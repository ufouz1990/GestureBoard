import java.awt.*;
import java.applet.Applet;
import java.util.*;

public class Words extends Applet{
	String output;
	public Words(String word){
		output = word; 
	}
	public void paint(Graphics g){
		g.drawRect(10, 0, getSize().width-11, getSize().height-1);
		g.setColor(new Color(000000));
		g.drawString(output, 20, 20);
	}
		
}