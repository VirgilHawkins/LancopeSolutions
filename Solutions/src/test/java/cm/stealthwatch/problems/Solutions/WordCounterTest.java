package cm.stealthwatch.problems.Solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.Test;




public class WordCounterTest {
	
	@Test
	public void testGetWordsByCountInAFile() {
		Map<String, Integer> map = WordCounter.getWordsByCountInAFile("src\\\\main\\\\resources\\\\example.txt");
		
		assertEquals(450, map.size());
	}
	
	@Test
	public void test75KBFile() {
		Map<String, Integer> map = WordCounter.getWordsByCountInAFile("src\\\\test\\\\resources\\\\gutenburg.txt");
		
		map.entrySet().stream()
		.sorted(Map.Entry.<String, Integer>comparingByValue()
		.reversed())
		.forEach(e -> System.out.println(e.getValue() +" "+ e.getKey()));

	}
	
	@Test
	public void test900KbSizeFile() {
		Map<String, Integer> map = WordCounter.getWordsByCountInAFile("src\\\\test\\\\resources\\\\dracula.txt");
		
		map.entrySet().stream()
		.sorted(Map.Entry.<String, Integer>comparingByValue()
		.reversed())
		.forEach(e -> System.out.println(e.getValue() +" "+ e.getKey()));

	}
}
