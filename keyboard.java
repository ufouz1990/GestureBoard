import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
	
	class keyboard extends JFrame{
	  ImageIcon image = new ImageIcon("keyboardImage.jpeg"); //the image
	  public keyboard(LinkedList wordList) {
		setTitle("Keyboard");
		setSize(850,400); // default size is 0,0
		setResizable(false); //so the user does not resize the window
		setLocation(10,200); // default is 0,0 (top left corner)
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Container pane = getContentPane(); //sets up container
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
		JLabel label = new JLabel(image);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 5;
		pane.add(label, c);

	}
}

