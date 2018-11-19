package cm.stealthwatch.problems.Solutions;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

public class WordCounter {
	Map<String, Integer> wordMap = new HashMap<String, Integer>();

	public static void main(String args[]) throws IOException {
		Map<String, Integer> wordMap = new HashMap<String, Integer>();

		List<String> lines = FileUtils.readLines(new File("src\\main\\resources\\example.txt"), "UTF-8");

		String text = FileUtils.readFileToString(new File("src\\main\\resources\\example.txt"), "UTF-8");
		for(String line: lines) {
			System.out.println(line);

			line = line.toLowerCase().replaceAll("[^a-zA-Z-]", " ").replaceAll("\\s+", " ").trim();
			System.out.println(line);

			String[] words = line.split(" ");
			for(int i = 0; i < words.length; i++) {
				int value = 0;
				String word = words[i];
				if(StringUtils.isEmpty(word))
					continue;
				if(wordMap.containsKey(word)) {
					value = wordMap.get(word);
					wordMap.put(word,value+1);
				} else {
					wordMap.put(word, value+1);
				}
			}
			
		}
		wordMap.entrySet().stream().forEach(e -> System.out.println(e.getValue() +" "+ e.getKey()));
	}
	
	public Map<String, Integer> getWordCountByWord(String fileLocation) throws IOException {
		Map<String, Integer> wordMap = new HashMap<String, Integer>();

		String text = formatter(FileUtils.readFileToString(new File(fileLocation), "UTF-8"));
		String[] words = text.split(" ");
		
		for(int i = 0; i < words.length; i++) {
			int wordCount = 0;
			String word = words[i];
			if(StringUtils.isEmpty(word))
				continue;
			if(wordMap.containsKey(word)) {
				wordCount = wordMap.get(word);
				wordMap.put(word, wordCount++);
			} else {
				wordMap.put(word, wordCount++);
			}
		}
		return wordMap;


	}
	
	private String formatter(String text) {
		return text.toLowerCase().replaceAll("[^a-zA-Z-]", " ").replaceAll("\\s+", " ").trim();
	}

}
