package com.accolite.comviva.test;

import java.util.ArrayList;
import java.util.List;

import com.accolite.comviva.model.WordFrequency;
import com.accolite.comviva.trie.Trie;

public class TypeaheadTest {

	public static void main(String[] args) {
		try {
			// test data
			List<String> words = getTestData();

			// insert test data into the trie
			Trie trie = new Trie(words);

			// search suggestions based on prefix
			String searchPrefix = "sp";
			List<WordFrequency> suggetionsList = trie.search(searchPrefix.toLowerCase());
			if (suggetionsList.isEmpty()) {
				System.out.println("suggetions not found for prefix: " + searchPrefix);
				System.exit(0);
			}

			// get top search words from suggetionsList
			List<String> topSuggetions = trie.topSuggetions(suggetionsList);

			System.out.println("top suggetions");
			for (String word : topSuggetions) {
				System.out.println(word);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static List<String> getTestData() {
		WordFrequency[] testData = new WordFrequency[] { new WordFrequency("hello", 300),
				new WordFrequency("hell", 200), new WordFrequency("help", 300), new WordFrequency("Human bieng", 250),
				new WordFrequency("Spider", 150), new WordFrequency("Spider man", 200) };
		List<String> words = new ArrayList<>();
		for (WordFrequency data : testData) {
			for (int i = 0; i < data.getFrequency(); i++) {
				words.add(data.getWord());
			}
		}
		return words;
	}

}
