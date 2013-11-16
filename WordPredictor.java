import java.awt.Point;
import java.io.*;
import java.util.*;

public class WordPredictor {
	private ArrayList<String> dictionary;
	
	public static final double MAX_ERROR = 2;
	public static final double SAME_LINE_ERROR = 0.1;
	public static final double DIFF_LINE_ERROR = 0.2;
	public static final double MISS_ERROR = 1;
	
	public static void main(String[] args) {
		KeyMap.init();
		LinkedList<KeyPoint> p = new LinkedList<KeyPoint>();
		p.add(new KeyPoint(2,2.3));
		p.add(new KeyPoint(1,11.15));
		p.add(new KeyPoint(2,10.3));
		p.add(new KeyPoint(1,4.15));
		System.out.println(new WordPredictor().predict(p));
	}
	
	public WordPredictor() {
		loadDictionary();
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
	
	public LinkedList<String> predict(LinkedList<KeyPoint> swipePoints) {
		double[] costs = new double[dictionary.size()];
		
		StringBuilder base_word = new StringBuilder();
		for (int i = 0;i < swipePoints.size();i++) {
			base_word.append(KeyMap.POINT_TO_CHAR.get(swipePoints.get(i)));
		}
		String word = base_word.toString();
		
		int i = 0;
		for (String dict_word : dictionary)
			costs[i] = mistypes(word,dict_word);
		
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
		word2 = word2.toUpperCase().replaceAll("(.)\\1","$1");
		
		int j = 0;
		for (int i = 0;i < word1.length();i++) {
			char c1 = word1.charAt(i);
			char c2 = word2.charAt(j);
			
			boolean prevExists = (i > 0);
			char prevChar = 'q';	// Arbitrary
			if (prevExists)
				prevChar = word1.charAt(i-1);
			
			// If mistype >= MAX_ERROR, not the word, so exit!
			if (cost >= MAX_ERROR)
				return MAX_ERROR;
			
			// Characters are same, move along!
			if (c1 == c2)
				continue;
			
			// Characters on same line, so move to next dict word letter
			if (prevExists && between(prevChar,c2,c1)) {
				cost += SAME_LINE_ERROR;
				continue;
			}
			
			// Character on path to next character, risky business
			if (prevExists && onPath(prevChar,c2,c1)) {
				cost += DIFF_LINE_ERROR;
				continue;
			}
			
			cost += MISS_ERROR;
		}
		
		return 0;
	}
	
	private boolean sameLine(char c1,char c2) {
		System.out.println(KeyMap.CHAR_TO_POINT.get(c2)+" : "+c2);
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
				}
			}
		}
		
		LinkedList<String> wordsList = new LinkedList<String>();
		
		for (int i = 0;i < minCosts.size();i++) {
			if (minCosts.get(i) >= MAX_ERROR)
				break;
			wordsList.add(words.get(i));
		}
		
		return wordsList;
	}
}
