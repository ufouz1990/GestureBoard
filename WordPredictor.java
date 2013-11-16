import java.awt.Point;
import java.io.*;
import java.util.*;

public class WordPredictor {
	private ArrayList<String> dictionary;
	private ArrayList<String> mostFrequentWords;
	
	public static final double MAX_ERROR = 2;
	public static final double SAME_LINE_ERROR = 0.1;
	public static final double DIFF_LINE_ERROR = 0.2;
	public static final double DELETION_NEAR_ERROR = 0.15;
	public static final double DELETION_FAR_ERROR = 0.7;
	public static final double CLOSE_MISS_ERROR = 0.6;
	public static final double MISS_ERROR = 1;
	public static final double EXTRA_LETTER_ERROR = 0.3;
	
	public static void main(String[] args) {
		KeyMap.init();
		LinkedList<Character> p = new LinkedList<Character>();
		p.add('W');
		p.add('O');
		p.add('E');
		System.out.println(new WordPredictor().predict(p));
	}
	
	public WordPredictor() {
		loadDictionary();
		loadMostFrequentWords();
	}
	
	private void loadDictionary() {
		dictionary = new ArrayList<String>();
		
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
	
	private void loadMostFrequentWords() {
		mostFrequentWords = new ArrayList<String>();
		 
		try {   
			BufferedReader reader = new BufferedReader(new FileReader("mostFreqWords.txt"));
			while (true) { 
				String line = reader.readLine();
				if (line == null)
					break;
				mostFrequentWords.add(line);
			}
			reader.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public LinkedList<String> predict(LinkedList<Character> swipePoints) {
		double[] costs = new double[dictionary.size()];
		
		StringBuilder base_word = new StringBuilder();
		for (int i = 0;i < swipePoints.size();i++) {
			base_word.append(swipePoints.get(i));
		}
		String word = base_word.toString();
		
		int i = 0;
		for (String dict_word : dictionary) {
			costs[i] = mistypes(word,dict_word);
			i++;
		}
		
		return mostLikelyWords(costs);
	}
	
	/**
	 * Returns the mistype cost for word1 representing the dictionary word word2.
	 *
	 * @param word1		Word swiped by user on keyboard
	 * @param word2 	Dictionary word to compare to word1
	 * @return		The cost of the mistype
	 */
	private double mistypes(String word1,String word2) {
		double cost = 0;
		
		// Set all to upper case and remove duplicate letters (no cost)
		word1 = word1.toUpperCase().replaceAll("(.)\\1","$1");
		word2 = word2.toUpperCase().replaceAll("(.)\\1","$1").replaceAll("[^A-Z0-9]", "");
		
		// Check for single deletion
		double singleDeletion = singleDeletion(word1,word2);
		
		int i = 0;
		int j = 0;
		int extraLetters = 0;
		for (;i < word1.length();i++) {
			char c1 = word1.charAt(i);
			if (j == word2.length()) {
				cost = Math.min(cost+1,MAX_ERROR);
				break;
			}
			char c2 = word2.charAt(j);
			
			boolean prevExists = (i > 0);
			char prevChar = 'q';	// Arbitrary
			if (prevExists)
				prevChar = word1.charAt(i-1);
			
			// If mistype >= MAX_ERROR, not the word, so exit!
			if (cost >= MAX_ERROR)
				//return MAX_ERROR;
				break;
			
			// Characters are same, move along!
			if (c1 == c2) {
				j++;
				continue;
			}
			
			// Characters on same line, so move to next dict word letter
			if (prevExists && between(prevChar,c2,c1)) {
				cost += SAME_LINE_ERROR;
				extraLetters++;
				i--;
				j++;
				continue;
			}
			
			// Character on path to next character, risky business
			if (prevExists && onPath(prevChar,c2,c1)) {
				cost += DIFF_LINE_ERROR;
				extraLetters++;
				i--;
				j++;
				continue;
			}
			
			cost += (nextToEachOther(c1,c2))? CLOSE_MISS_ERROR : MISS_ERROR;
			j++;
		}
		
		if (word2.length()-word1.length()-extraLetters > 0)
			cost = Math.min(cost+(word2.length()-word1.length()-extraLetters)*EXTRA_LETTER_ERROR,MAX_ERROR);
		
		if (word1.length()-word2.length() > 3)
			cost = MAX_ERROR;
		
		if (singleDeletion > 0)
			cost = Math.min(singleDeletion,cost);
		
		return cost;
	}
	
	private boolean sameLine(char c1,char c2) {
		return (KeyMap.CHAR_TO_POINT.get(c1).row == KeyMap.CHAR_TO_POINT.get(c2).row);
	}
	
	// Swiped letter (middle) is on same line as start and end
	private boolean between(char start,char middle,char end) {
		if (!sameLine(start,middle) || !sameLine(start,end))
			return false;
		
		double s_col = KeyMap.CHAR_TO_POINT.get(start).col;
		double m_col = KeyMap.CHAR_TO_POINT.get(middle).col;
		double e_col = KeyMap.CHAR_TO_POINT.get(end).col;
		
		return ((s_col > m_col && m_col > e_col) ||
			(s_col < m_col && m_col < e_col));
	}
	
	// Start and end on different lines
	private boolean onPath(char start,char middle,char end) {
		double s_row = KeyMap.CHAR_TO_POINT.get(start).row;
                double m_row = KeyMap.CHAR_TO_POINT.get(middle).row;
                double e_row = KeyMap.CHAR_TO_POINT.get(end).row;
		
		double s_col = KeyMap.CHAR_TO_POINT.get(start).col;
                double m_col = KeyMap.CHAR_TO_POINT.get(middle).col;
                double e_col = KeyMap.CHAR_TO_POINT.get(end).col;
		
		boolean between_row = ((s_row < m_row && m_row < e_row) ||
					(s_row > m_row && m_row > e_row));
		boolean between_col = ((s_col < m_col && m_col < e_col) ||
					(s_col > m_col && m_col > e_col));
		
		return (between_row && between_col);
	}
	
	private boolean nextToEachOther(char c1,char c2) {
		double c1_row = KeyMap.CHAR_TO_POINT.get(c1).row;
		double c2_row = KeyMap.CHAR_TO_POINT.get(c2).row;
		
		double c1_col = KeyMap.CHAR_TO_POINT.get(c1).col;
		double c2_col = KeyMap.CHAR_TO_POINT.get(c2).col;
		
		switch(c1) {
			case '`':
				return (c2 == '1' || c2 == '\t');
			case '1':
				return (c2 == '`' || c2 == '2' || c2 == '\t' || c2 == 'Q');
			case '=':
				return (c2 == '-' || c2 == '\b' || c2 == '[' || c2 == ']');
			case '\b':
				return (c2 == '=' || c2 == ']' || c2 == '\\');
			case '\t':
				return (c2 == '`' || c2 == '1' || c2 == 'Q' || c2 == '\f');
			case ']':
				return (c2 == '[' || c2 == '=' || c2 == '\b' || c2 == '\\' || c2 == '\'' || c2 == '\n');
			case '\\':
				return (c2 == '\b' || c2 == ']' || c2 == '\n');
			case '\f':
				return (c2 == '\t' || c2 == 'Q' || c2 == 'A' || c2 == '\r');
			case '\r':
				return (c2 == '\f' || c2 == 'A' || c2 == 'Z' || c2 == '/' || c2 == '\'' || c2 == '\n');
			case '\n':
				return (c2 == ']' || c2 == '\\' || c2 == '\'' || c2 == '\r');
			case 'Q':
				return (c2 == '\t' || c2 == '1' || c2 == '2' || c2 == 'W' || c2 == '\f' || c2 == 'A');
			case 'A':
				return (c2 == 'Q' || c2 == 'W' || c2 == '\f' || c2 == '\r' || c2 == 'Z' || c2 == 'S');
			case 'Z':
				return (c2 == '\r' || c2 == 'A' || c2 == 'S' || c2 == 'X' || c2 == ' ');
			case 'X':
				return (c2 == 'Z' || c2 == 'S' || c2 == 'D' || c2 == 'C' || c2 == ' ');
			case 'B':
				return (c2 == 'V' || c2 == 'G' || c2 == 'H' || c2 == 'N' || c2 == ' ');
			case 'N':
				return (c2 == 'B' || c2 == 'H' || c2 == 'J' || c2 == 'M' || c2 == ' ' || c2 == '\0');
			case 'M':
				return (c2 == 'N' || c2 == 'J' || c2 == 'K' || c2 == ',' || c2 == ' ' || c2 == '\0');
			case '/':
				return (c2 == '.' || c2 == ';' || c2 == '\'' || c2 == '\r' || c2 == '\0');
			case '\'':
				return (c2 == ';' || c2 == '/' || c2 == '\r' || c2 == '\n' || c2 == '[' || c2 == ']');
			default:
				return (Math.sqrt(Math.pow(c1_row-c2_row,2)+Math.pow(c1_col-c2_col,2)) < 1.7);
		}
	}
	
	private double singleDeletion(String word1,String word2) {
		if (word2.length()-word1.length() != 1)
			return 0;
		
		boolean foundDeletion = false;
		char deletion = 'Q';
		char opt1 = 'Q';
		char opt2 = 'Q';
		int j = 0;
		for (int i = 0;i < word1.length();i++) {
			if (word1.charAt(i) == word2.charAt(j)) {
				j++;
				continue;
			}
			if (foundDeletion) {
				return 0;
			}
			else {
				foundDeletion = true;
				deletion = word2.charAt(j);
				opt1 = word1.charAt(i);
				opt2 = (i > 0)? word1.charAt(i-1) : opt1;
				i--;
			}
			j++;
		}
		
		return (nextToEachOther(deletion,opt1) || nextToEachOther(deletion,opt2))? DELETION_NEAR_ERROR : DELETION_FAR_ERROR;
	}
	
	private LinkedList<String> mostLikelyWords(double[] costs) {
		LinkedList<String> words = new LinkedList<String>();
		LinkedList<Double> minCosts = new LinkedList<Double>();
		
		for (int i = 0;i < 20;i++) {
			words.add(null);
			minCosts.add(MAX_ERROR);
		}
		
		for (int i = 0;i < costs.length;i++) {
			if (costs[i] >= MAX_ERROR)
				continue;
			
			for (int j = 0;j < minCosts.size();j++) {
				if (costs[i] < minCosts.get(j)) {
					words.add(j,dictionary.get(i));
					words.removeLast();
					minCosts.add(j,costs[i]);
					minCosts.removeLast();
					break;
				}
			}
		}
		
		String[] oldTop5 = new String[5];
		int topInTop = 0;
		for (int i = 0;i < oldTop5.length;i++)
			oldTop5[i] = words.get(i);
		
		String[] top5 = new String[oldTop5.length];
		int count = 1;
		for (count = 1;count < top5.length;count++)
			if (costs[count] != costs[0])
				break;
		
		int top5Index = 0;
		for (int i = 0;i < mostFrequentWords.size();i++) {
			for (int j = 0;j < count;j++) {
				if (mostFrequentWords.get(i).equalsIgnoreCase(words.get(j))) {
					top5[top5Index] = words.get(j);
					top5Index++;
					if (j <= i)
						topInTop++;
				}
			}
		}
		for (int i = top5Index;i < count;i++) {
			top5[i] = oldTop5[i-top5Index+topInTop];
		}
		for (int i = count;i < 5;i++)
			top5[i] = words.get(i);
		
		LinkedList<String> wordsList = new LinkedList<String>();
		
		for (int i = 0;i < top5.length;i++) {
			if (top5[i] == null)
				break;
			wordsList.add(top5[i]);
		}
		
		for (int i = 5;i < minCosts.size();i++) {
			if (minCosts.get(i) >= MAX_ERROR)
				break;
			wordsList.add(words.get(i));
		}
		
		return wordsList;
	}
}
