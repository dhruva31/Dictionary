package com.pramati.node;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dhruva Somani
 * 
 */
public class Node {

	private final List<Node> leafNodes;
	private String meaning;
	private final Character characterAtNode;

	private Node(char c) {
		leafNodes = new ArrayList<Node>(26);
		characterAtNode = c;
	}

	public Node() {
		leafNodes = new ArrayList<Node>(26);
		characterAtNode = null;
	}

	private List<Node> getLeafNodes() {
		return leafNodes;
	}

	private Node getLeafNodeWithCharacter(char c) {
		Node node = null;

		for (Node leaf : leafNodes) {
			if (leaf.getCharacterAtNode() == c) {
				node = leaf;
			}
		}

		if (node == null) {
			node = new Node(c);
			leafNodes.add(node);
		}
		return node;
	}

	private String getMeaning() {
		return meaning;
	}

	private void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	private char getCharacterAtNode() {
		return characterAtNode;
	}

	public String fetchWordMeaning(String word) {
		if (word == null) {
			throw new RuntimeException("Cant find meaning of empty words");
		}
		String meaning = null;
		char[] charactersInWord = word.toCharArray();
		Node nodeFound = this;
		Node intermediateNode = null;

		for (char c : charactersInWord) {
			intermediateNode = null;
			for (Node leaf : nodeFound.getLeafNodes()) {
				if (leaf.getCharacterAtNode() == c) {
					nodeFound = leaf;
					intermediateNode = leaf;
				}
			}
			if (intermediateNode == null) {
				break;
			}
		}

		if (intermediateNode != null) {
			meaning = intermediateNode.getMeaning();
		}
		return meaning;
	}

	public void addNewWordToDictionary(String word, String meaning) {
		if (word == null || meaning == null) {
			throw new RuntimeException(
					"Make sure that both word and meaning are not null");
		}
		char[] charactersInWord = word.toCharArray();
		Node nodeToAddCharacter = this;
		for (char c : charactersInWord) {
			nodeToAddCharacter = nodeToAddCharacter.getLeafNodeWithCharacter(c);
		}

		nodeToAddCharacter.setMeaning(meaning);
	}
}
