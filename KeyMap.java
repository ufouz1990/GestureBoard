import java.util.HashMap;

public class KeyMap {
	public static final HashMap<Point,Character> POINT_TO_CHAR = new HashMap<Point,Character>();
	public static final HashMap<Character,Point> CHAR_TO_POINT = new HashMap<Character,Point>();
	
	private boolean inited = false;
	
	public static void init() {
		if (inited)
			return;
		inited = true;
		
		POINT_TO_CHAR.put(0,'`');
		CHAR_TO_POINT.put('`',);
