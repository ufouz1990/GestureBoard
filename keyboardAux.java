import java.util.*;
import javax.swing.*;
import java.awt.*;
public class keyboardAux{
	public static void main(String[] args){
		LinkedList list = new LinkedList();
		list.add("new");
		list.add("old");
		list.add("oh hell no");
		list.add("one more after this");
		list.add("last one");
		//System.out.println(list.toString());
		JFrame f = new keyboard(list);
		f.show();
	}
}