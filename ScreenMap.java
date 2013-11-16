import java.util.HashMap;
import java.awt.*;

public class ScreenMap{
	public static final HashMap<Character, Rectangle> CHAR_TO_POINT = new HashMap<Character, ScreenPoint>();

	static boolean inited = false; 

	public static void init(){
		if(inited)
			return;
		nited = true;

	CHAR_TO_POINT.put('`', new Rectangle(20,22,45,41));
	CHAR_TO_POINT.put('1', new Rectangle(75,22,45,41)); 
	CHAR_TO_POINT.put('2', new Rectangle(130,22,45,41)); 
	CHAR_TO_POINT.put('3', new Rectangle(184,22,45,41));
	CHAR_TO_POINT.put('4', new Rectangle(238,22,45,41));
	CHAR_TO_POINT.put('5', new Rectangle(295,22,45,41));
	CHAR_TO_POINT.put('6', new Rectangle(350,22,45,41));
	CHAR_TO_POINT.put('7', new Rectangle(405,22,45,41));
	CHAR_TO_POINT.put('8', new Rectangle(460,22,45,41));
	CHAR_TO_POINT.put('9', new Rectangle(515,22,45,41));
	CHAR_TO_POINT.put('0', new Rectangle(570,22,45,41));
	CHAR_TO_POINT.put('-', new Rectangle(625,22,45,41));
	CHAR_TO_POINT.put('=', new Rectangle(680,22,45,41));
	CHAR_TO_POINT.put('\b', new Rectangle(735,22,71,41));
	CHAR_TO_POINT.put('\t', new Rectangle(20,73,71,41));
	CHAR_TO_POINT.put('Q', new Rectangle(101,73,45,41));
	CHAR_TO_POINT.put('W', new Rectangle(156,73,45,41));
	CHAR_TO_POINT.put('E', new Rectangle(211,73,45,41));
	CHAR_TO_POINT.put('R', new Rectangle(266,73,45,41));
	CHAR_TO_POINT.put('T', new Rectangle(321,73,45,41));
	CHAR_TO_POINT.put('Y', new Rectangle(376,73,45,41));
	CHAR_TO_POINT.put('U', new Rectangle(431,73,45,41));
	CHAR_TO_POINT.put('I', new Rectangle(486,73,45,41));
	CHAR_TO_POINT.put('O', new Rectangle(541,73,45,41));
	CHAR_TO_POINT.put('P', new Rectangle(596,73,45,41));
	CHAR_TO_POINT.put('[', new Rectangle(651,73,45,41));
	CHAR_TO_POINT.put(']', new Rectangle(706,73,45,41));
	CHAR_TO_POINT.put('\\', new Rectangle(761,73,45,41));
	CHAR_TO_POINT.put('\f', new Rectangle(20,125,85,41));
	CHAR_TO_POINT.put('A', new Rectangle(115,125,45,41));
	CHAR_TO_POINT.put('S', new Rectangle(170,125,45,41));
	CHAR_TO_POINT.put('D', new Rectangle(225,125,45,41));
	CHAR_TO_POINT.put('F', new Rectangle(280,125,45,41));
	CHAR_TO_POINT.put('G', new Rectangle(335,125,45,41));
	CHAR_TO_POINT.put('H', new Rectangle(390,125,45,41));
	CHAR_TO_POINT.put('J', new Rectangle(445,125,45,41));
	CHAR_TO_POINT.put('K', new Rectangle(500,125,45,41));
	CHAR_TO_POINT.put('L', new Rectangle(555,125,45,41));
	CHAR_TO_POINT.put(';', new Rectangle(610,125,45,41));
	CHAR_TO_POINT.put('\'', new Rectangle(665,125,45,41));
	CHAR_TO_POINT.put('\n', new Rectangle(721,125,84,40));
	CHAR_TO_POINT.put('\r', new Rectangle(20,175,100,41));
	CHAR_TO_POINT.put('Z', new Rectangle(130,175,45,41));
	CHAR_TO_POINT.put('X', new Rectangle(185,175,45,41));
	CHAR_TO_POINT.put('C', new Rectangle(240,175,45,41));
	CHAR_TO_POINT.put('V', new Rectangle(295,175,45,41));
	CHAR_TO_POINT.put('B', new Rectangle(350,175,45,41));
	CHAR_TO_POINT.put('N', new Rectangle(405,175,45,41));
	CHAR_TO_POINT.put('M', new Rectangle(460,175,45,41));
	CHAR_TO_POINT.put(',', new Rectangle(515,175,45,41));
	CHAR_TO_POINT.put('.', new Rectangle(570,175,45,41));
	CHAR_TO_POINT.put('/', new Rectangle(625,175,45,41));
	CHAR_TO_POINT.put('\r', new Rectangle(680,175,126,41));
	CHAR_TO_POINT.put(' ', new Rectangle(129,226,320,41));
	CHAR_TO_POINT.put('\0', new Rectangle(462,226,209,41));
	}