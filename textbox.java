import java.awt.*;
import javax.swing.*;


public class textbox extends JFrame{
	public textbox(){
		setTitle("Textbox");
		setSize(300,50); // default size is 0,0
		setResizable(false); //so the user does not resize the window
		setLocation(10,200); // default is 0,0 (top left corner)
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Container mainPane = getContentPane();
		mainPane.setLayout(new BorderLayout());

		JTextField txt = new JTextField(20);
		mainPane.add(txt);
	}
}