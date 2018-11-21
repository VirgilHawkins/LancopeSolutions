package cm.stealthwatch.problems.Solutions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class WordCounter {
	private static final Logger LOGGER = Logger.getLogger(WordCounter.class.getName());

	public static void main(String args[]) throws IOException {
		Map<String, Integer> wordMap = getWordsByCountInAFile(args[0]);

		
		wordMap.entrySet().stream()
		.sorted(Map.Entry.<String, Integer>comparingByValue()
		.reversed())
		.forEach(e -> System.out.println(e.getValue() +" "+ e.getKey()));

	}
	
	
	/**
	 * For the last problem I decided to go with a bufferedReader approach instead of
	 * reading the entire file as a string as a safety measure of running out of memory
	 * 
	 * @param fileLocation
	 * @return Map<String, Integer>
	 */
	public static Map<String, Integer> getWordsByCountInAFile(String fileLocation){
		Map<String, Integer> wordMapByCount = new HashMap<String, Integer>();
		BufferedReader bufferedReader = null;
		FileReader fileReader = null;
		try {
			File file = new File(fileLocation);
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line=bufferedReader.readLine()) != null) {
				line = stringFormatter(line);
				String[] words = line.split(" ");
				
				for(int i = 0; i < words.length; i++) {
					
					int wordCount = 0;
					
					String word = words[i];
					
					if(word.isEmpty() || null == word)
						continue;
					
					if(wordMapByCount.containsKey(word)) {
						
						wordCount = wordMapByCount.get(word);
						wordMapByCount.put(word, wordCount+1);
						
					} else {
						
						wordMapByCount.put(word, wordCount+1);
						
					}
				}
			}
			bufferedReader.close();
			fileReader.close();
		} catch (IOException e) {
			System.out.println("An error has occured in reading the file: " + e.getMessage());
			e.printStackTrace();
		}
		return wordMapByCount;
	}

	private static String stringFormatter(String text) {
		return text.toLowerCase().replaceAll("[^a-zA-Z-]", " ").replaceAll("\\s+", " ").trim();
	}

}
