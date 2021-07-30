package com.accolite.comviva.trie;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.accolite.comviva.model.WordFrequency;

public class Trie {
	TrieNode root;

	public class TrieNode {

		Map<Character, TrieNode> children;
		char c;
		boolean isEndOfWord;
		int noOfHits;

		public TrieNode(char c) {
			this.c = c;
			children = new HashMap<>();
		}

		public TrieNode() {
			children = new HashMap<>();
		}

		public void insert(String word) {
			if (word == null || word.isEmpty()) {
				return;
			}
			char firstCharacter = word.charAt(0);
			TrieNode child = children.get(firstCharacter);
			if (child == null) {
				child = new TrieNode(firstCharacter);
				children.put(firstCharacter, child);
			}
			if (word.length() > 1)
				child.insert(word.substring(1));
			else {
				child.isEndOfWord = true;
				child.noOfHits = child.noOfHits == 0 ? 1 : child.noOfHits + 1;
			}

		}

	}

	public void searchChildrenPath(TrieNode root, List<WordFrequency> list, StringBuffer curr) {
		if (root.isEndOfWord) {
			list.add(new WordFrequency(curr.toString(), root.noOfHits));
		}

		if (root.children == null || root.children.isEmpty())
			return;

		for (TrieNode child : root.children.values()) {
			searchChildrenPath(child, list, curr.append(child.c));
			curr.setLength(curr.length() - 1);
		}
	}

	public List<WordFrequency> search(String prefix) {
		List<WordFrequency> list = new ArrayList<>();
		TrieNode lastNode = root;
		StringBuffer curr = new StringBuffer();
		for (char c : prefix.toCharArray()) {
			lastNode = lastNode.children.get(c);
			if (lastNode == null)
				return list;
			curr.append(c);
		}
		searchChildrenPath(lastNode, list, curr);
		return list;
	}

	// insert words in trie
	public Trie(List<String> words) {
		root = new TrieNode();
		for (String word : words)
			root.insert(word.toLowerCase());

	}

	public List<String> topSuggetions(List<WordFrequency> suggetionsList) {
		// sort words based on frequency
		List<WordFrequency> sortSuggetionsByFrequency = suggetionsList.stream()
				.sorted(Comparator.comparingInt(WordFrequency::getFrequency).reversed()).collect(Collectors.toList());

		List<String> topSuggetions = sortSuggetionsByFrequency.stream().map(wordFrequency -> wordFrequency.getWord())
				.collect(Collectors.toList());
		return topSuggetions;

	}

}
