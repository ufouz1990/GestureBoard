import java.util.HashMap;

public class KeyMap {
	public static final HashMap<KeyPoint,Character> POINT_TO_CHAR = new HashMap<KeyPoint,Character>();
	public static final HashMap<Character,KeyPoint> CHAR_TO_POINT = new HashMap<Character,KeyPoint>();
	
	static boolean inited = false;
	
	public static void init() {
		if (inited)
			return;
		inited = true;
		
		POINT_TO_CHAR.put(new KeyPoint(0,0.5),'`');
		CHAR_TO_POINT.put('`', new KeyPoint(0, 0.5));

		POINT_TO_CHAR.put(new KeyPoint(0, 1.5),'1');
		CHAR_TO_POINT.put('1', new KeyPoint(0, 1.5));

		POINT_TO_CHAR.put(new KeyPoint(0, 2.5),'2');
		CHAR_TO_POINT.put('2', new KeyPoint(0, 2.5));

		POINT_TO_CHAR.put(new KeyPoint(0, 3.5),'3');
		CHAR_TO_POINT.put('3', new KeyPoint(0, 3.5));

		POINT_TO_CHAR.put(new KeyPoint(0, 4.5),'4');
		CHAR_TO_POINT.put('4', new KeyPoint(0, 4.5));

		POINT_TO_CHAR.put(new KeyPoint(0, 5.5),'5');
		CHAR_TO_POINT.put('5', new KeyPoint(0, 5.5));

		POINT_TO_CHAR.put(new KeyPoint(0, 6.5),'6');
		CHAR_TO_POINT.put('6', new KeyPoint(0, 6.5));

		POINT_TO_CHAR.put(new KeyPoint(0, 7.5),'7');
		CHAR_TO_POINT.put('7', new KeyPoint(0, 7.5));

		POINT_TO_CHAR.put(new KeyPoint(0, 8.5),'8');
		CHAR_TO_POINT.put('8', new KeyPoint(0, 8.5));

		POINT_TO_CHAR.put(new KeyPoint(0, 9.5),'9');
		CHAR_TO_POINT.put('9', new KeyPoint(0, 9.5));

		POINT_TO_CHAR.put(new KeyPoint(0, 10.5),'0');
		CHAR_TO_POINT.put('0', new KeyPoint(0, 10.5));

		POINT_TO_CHAR.put(new KeyPoint(0, 11.5),'-');
		CHAR_TO_POINT.put('-', new KeyPoint(0, 11.5));

		POINT_TO_CHAR.put(new KeyPoint(0, 12.5),'=');
		CHAR_TO_POINT.put('=', new KeyPoint(0, 12.5));

		POINT_TO_CHAR.put(new KeyPoint(0, 13.25),'\b');
		CHAR_TO_POINT.put('\b', new KeyPoint(0, 13.25));

		POINT_TO_CHAR.put(new KeyPoint(1, 0.75),'\t');
		CHAR_TO_POINT.put('\t', new KeyPoint(1, .75));

		POINT_TO_CHAR.put(new KeyPoint(1, 2.15),'Q');
		CHAR_TO_POINT.put('Q', new KeyPoint(1, 2.15));

		POINT_TO_CHAR.put(new KeyPoint(1, 3.15),'W');
		CHAR_TO_POINT.put('W', new KeyPoint(1, 3.15));

		POINT_TO_CHAR.put(new KeyPoint(1, 4.15),'E');
		CHAR_TO_POINT.put('E', new KeyPoint(1, 4.15));

		POINT_TO_CHAR.put(new KeyPoint(1, 5.15),'R');
		CHAR_TO_POINT.put('R', new KeyPoint(1, 5.15));

		POINT_TO_CHAR.put(new KeyPoint(1, 6.15),'T');
		CHAR_TO_POINT.put('T', new KeyPoint(1, 6.15));

		POINT_TO_CHAR.put(new KeyPoint(1, 7.15),'Y');
		CHAR_TO_POINT.put('Y', new KeyPoint(1, 7.15));

		POINT_TO_CHAR.put(new KeyPoint(1, 8.15),'U');
		CHAR_TO_POINT.put('U', new KeyPoint(1, 8.15));

		POINT_TO_CHAR.put(new KeyPoint(1, 9.15),'I');
		CHAR_TO_POINT.put('I', new KeyPoint(1, 9.15));

		POINT_TO_CHAR.put(new KeyPoint(1, 10.15),'O');
		CHAR_TO_POINT.put('O', new KeyPoint(1, 10.15));

		POINT_TO_CHAR.put(new KeyPoint(1, 11.15),'P');
		CHAR_TO_POINT.put('P', new KeyPoint(1, 11.15));

		POINT_TO_CHAR.put(new KeyPoint(1, 12.15),'[');
		CHAR_TO_POINT.put('[', new KeyPoint(1, 12.15));

		POINT_TO_CHAR.put(new KeyPoint(1, 13.15),']');
		CHAR_TO_POINT.put(']', new KeyPoint(1, 13.15));

		POINT_TO_CHAR.put(new KeyPoint(1, 14.15),'\\');
		CHAR_TO_POINT.put('\\', new KeyPoint(1, 14.15));

		POINT_TO_CHAR.put(new KeyPoint(2, 0.85),'\f');
		CHAR_TO_POINT.put('\f', new KeyPoint(2, 0.85));

		POINT_TO_CHAR.put(new KeyPoint(2, 2.3),'A');
		CHAR_TO_POINT.put('A', new KeyPoint(2, 2.3));

		POINT_TO_CHAR.put(new KeyPoint(2, 3.3),'S');
		CHAR_TO_POINT.put('S', new KeyPoint(2, 3.3));

		POINT_TO_CHAR.put(new KeyPoint(2, 4.3),'D');
		CHAR_TO_POINT.put('D', new KeyPoint(2, 4.3));

		POINT_TO_CHAR.put(new KeyPoint(2, 5.3),'F');
		CHAR_TO_POINT.put('F', new KeyPoint(2, 5.3));

		POINT_TO_CHAR.put(new KeyPoint(2, 6.3),'G');
		CHAR_TO_POINT.put('G', new KeyPoint(2, 6.3));

		POINT_TO_CHAR.put(new KeyPoint(2, 7.3),'H');
		CHAR_TO_POINT.put('H', new KeyPoint(2, 7.3));

		POINT_TO_CHAR.put(new KeyPoint(2, 8.3),'J');
		CHAR_TO_POINT.put('J', new KeyPoint(2, 8.3));

		POINT_TO_CHAR.put(new KeyPoint(2, 9.3),'K');
		CHAR_TO_POINT.put('K', new KeyPoint(2, 9.3));

		POINT_TO_CHAR.put(new KeyPoint(2, 10.3),'L');
		CHAR_TO_POINT.put('L', new KeyPoint(2, 10.3));

		POINT_TO_CHAR.put(new KeyPoint(2, 11.3),';');
		CHAR_TO_POINT.put(';', new KeyPoint(2, 11.3));

		POINT_TO_CHAR.put(new KeyPoint(2, 12.3),'\'');
		CHAR_TO_POINT.put('\'', new KeyPoint(2, 12.3));

		POINT_TO_CHAR.put(new KeyPoint(2, 13.65),'\n');
		CHAR_TO_POINT.put('\n', new KeyPoint(2, 13.65));

		POINT_TO_CHAR.put(new KeyPoint(3, 1),'\r');
		CHAR_TO_POINT.put('\r', new KeyPoint(3, 1));

		POINT_TO_CHAR.put(new KeyPoint(3, 2.5),'Z');
		CHAR_TO_POINT.put('Z', new KeyPoint(3, 2.5));

		POINT_TO_CHAR.put(new KeyPoint(3, 3.5),'X');
		CHAR_TO_POINT.put('X', new KeyPoint(3, 3.5));

		POINT_TO_CHAR.put(new KeyPoint(3, 4.5),'C');
		CHAR_TO_POINT.put('C', new KeyPoint(3, 4.5));

		POINT_TO_CHAR.put(new KeyPoint(3, 5.5),'V');
		CHAR_TO_POINT.put('V', new KeyPoint(3, 5.5));

		POINT_TO_CHAR.put(new KeyPoint(3, 6.5),'B');
		CHAR_TO_POINT.put('B', new KeyPoint(3, 6.5));

		POINT_TO_CHAR.put(new KeyPoint(3, 7.5),'N');
		CHAR_TO_POINT.put('N', new KeyPoint(3, 7.5));

		POINT_TO_CHAR.put(new KeyPoint(3, 8.5),'M');
		CHAR_TO_POINT.put('X', new KeyPoint(3, 8.5));

		POINT_TO_CHAR.put(new KeyPoint(3, 9.5),',');
		CHAR_TO_POINT.put(',', new KeyPoint(3, 9.5));

		POINT_TO_CHAR.put(new KeyPoint(3, 10.5),'.');
		CHAR_TO_POINT.put('.', new KeyPoint(3, 10.5));

		POINT_TO_CHAR.put(new KeyPoint(3, 11.5),'/');
		CHAR_TO_POINT.put('/', new KeyPoint(3, 11.5));

		POINT_TO_CHAR.put(new KeyPoint(3, 13.25),'\r');
		CHAR_TO_POINT.put('\r', new KeyPoint(3, 13.25));

		POINT_TO_CHAR.put(new KeyPoint(4, 6.8),' ');
		CHAR_TO_POINT.put(' ', new KeyPoint(4, 6.8));
	}
}
