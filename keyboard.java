import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
	
	class keyboard extends JFrame{
	  ImageIcon image = new ImageIcon("keyboardImage.jpg"); //the image
	  public keyboard(LinkedList wordList) {
		setTitle("Keyboard");
		setSize(840,335); // default size is 0,0
		setResizable(false); //so the user does not resize the window
		setLocation(10,200); // default is 0,0 (top left corner)
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Container mainPane = getContentPane();
		mainPane.setLayout(new BorderLayout());

		Container pane = new JPanel(); //sets up container
		pane.setLayout(new GridBagLayout()); //sets up layout
		GridBagConstraints c = new GridBagConstraints();

		//Sets up output boxes
		for(int i = 0; i <= 4; i++){
		Words rec = new Words((String)(wordList.get(i)));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = i;
		c.gridy = 0;
		c.ipady = 20;
		c.weightx = .5;
		c.ipadx = 10;
		pane.add(rec, c);
		rec.repaint();
		}
		//sets up image 
		/*KeyboardApplet label = new KeyboardApplet();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 5;
		c.ipady = 300;
		pane.add(label, c);*/

		mainPane.add("North",pane);
		KeyboardApplet keyboard = new KeyboardApplet();
		keyboard.repaint();
		mainPane.add("Center",keyboard);

	}
}

