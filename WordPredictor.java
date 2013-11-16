import java.awt.Point;
import java.io.*;
import java.util.*;

public class WordPredictor {
	private LinkedList<String> dictionary;
	
	public static final LinkedList<Character> FIRST_LINE = new LinkedList<Character>();
	public static final LinkedList<Character> SECOND_LINE = new LinkedList<Character>();
	public static final LinkedList<Character> THIRD_LINE = new LinkedList<Character>();
	public static final LinkedList<Character> FOURTH_LINE = new LinkedList<Character>();
	public static final LinkedList<Character> FIFTH_LINE = new LinkedList<Character>();
	
	public static final double SAME_LINE_ERROR = 0.1;
	public static final double DIFF_LINE_ERROR = 0.2;
	public static final double SUB_ERROR = 1;
	
	public static void main(String[] args) {
		System.out.println(new WordPredictor().dictionary.size());
	}
	
	public WordPredictor() {
		loadDictionary();
		loadLines();
	}
	
	private void loadDictionary() {
		dictionary = new LinkedList<String>();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader("dict.txt"));
			while (true) {
				String line = reader.readLine();
				if (line == null)
					break;
				dictionary.add(line);
			}
			reader.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void loadLines() {
		FIRST_LINE.clear();
		SECOND_LINE.clear();
		THIRD_LINE.clear();
		
		FIRST_LINE.add('`');
		FIRST_LINE.add('1');
		FIRST_LINE.add('2');
		FIRST_LINE.add('3');
		FIRST_LINE.add('4');
		FIRST_LINE.add('5');
		FIRST_LINE.add('6');
		FIRST_LINE.add('7');
		FIRST_LINE.add('8');
		FIRST_LINE.add('9');
		FIRST_LINE.add('0');
		FIRST_LINE.add('-');
		FIRST_LINE.add('=');
		FIRST_LINE.add('\b');
		
		SECOND_LINE.add('\t');
		SECOND_LINE.add('Q');
		SECOND_LINE.add('W');
		SECOND_LINE.add('E');
		SECOND_LINE.add('R');
		SECOND_LINE.add('T');
		SECOND_LINE.add('Y');
		SECOND_LINE.add('U');
		SECOND_LINE.add('I');
		SECOND_LINE.add('O');
		SECOND_LINE.add('P');
		SECOND_LINE.add('[');
		SECOND_LINE.add(']');
		SECOND_LINE.add('\\');
		
		THIRD_LINE.add('\f');
		THIRD_LINE.add('A');
		THIRD_LINE.add('S');
		THIRD_LINE.add('D');
		THIRD_LINE.add('F');
		THIRD_LINE.add('G');
		THIRD_LINE.add('H');
		THIRD_LINE.add('J');
		THIRD_LINE.add('K');
		THIRD_LINE.add('L');
		THIRD_LINE.add(';');
		THIRD_LINE.add('\'');
		THIRD_LINE.add('\n');
		
		FOURTH_LINE.add('\r');
		FOURTH_LINE.add('Z');
		FOURTH_LINE.add('X');
		FOURTH_LINE.add('C');
		FOURTH_LINE.add('V');
		FOURTH_LINE.add('B');
		FOURTH_LINE.add('N');
		FOURTH_LINE.add('N');
		FOURTH_LINE.add('M');
		FOURTH_LINE.add(',');
		FOURTH_LINE.add('.');
		FOURTH_LINE.add('/');
		FOURTH_LINE.add('\r');
		
		FIFTH_LINE.add(' ');
		FIFTH_LINE.add('\0');
	}
	
	public LinkedList<String> predict(LinkedList<Point> swipePoints) {
		HashMap<String,Integer> words = new HashMap<String,Integer>();
		
		StringBuilder base_word = new StringBuilder();
		for (int i = 0;i < swipePoints.size();i++) {
			base_word.append(KeyMap.POINT_TO_CHAR.get(swipePoints.get(i)));
		}
		
		for (String dict_word : dictionary) {
			
		}
		
		return mostCommonWords(words);
	}
	
	private double mistypes(String word1,String word2) {
		word1 = word1.toLowerCase().replaceAll("(.)\\1","$1");
		word2 = word2.toLowerCase().replaceAll("(.)\\1","$1");
		
		
		
		return 0;
	}
	
	private boolean sameLine(char c1,char c2) {
		if (FIRST_LINE.contains(c1) && FIRST_LINE.contains(c2))
			return true;
		if (SECOND_LINE.contains(c1) && SECOND_LINE.contains(c2))
			return true;
		if (THIRD_LINE.contains(c1) && THIRD_LINE.contains(c2))
			return true;
		return false;
	}
	
	private LinkedList<String> mostCommonWords(HashMap<String,Integer> words) {
		LinkedList<String> wordsList = new LinkedList<String>();
		
		
		
		return wordsList;
	}
}
