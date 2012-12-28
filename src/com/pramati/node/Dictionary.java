package com.pramati.node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Dictionary {

	private Node rootNode;
	private static int noOfWordsInDictionary = 0;

	public Dictionary() {
		rootNode = new Node();
	}

	public static void main(String args[]) throws IOException {
		Dictionary dictionary = new Dictionary();

		BufferedReader br = new BufferedReader(new FileReader(
				"WordsWithMeanings.txt"));
		try {
			String line = br.readLine();
			while (line != null) {
				String word = line.split(":")[0];
				String meaning = line.split(":")[1];
				dictionary.getRootNode().addNewWordToDictionary(word, meaning);
				noOfWordsInDictionary++;
				line = br.readLine();
			}

		} finally {
			br.close();
		}

		System.out.println("Total no of words in dictionary "
				+ noOfWordsInDictionary);

		System.out.println("Please enter the words to fetch meanings :: -1 to exit");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.next();

		while (!input.equals("-1")) {
			try {
				String meaning = dictionary.getRootNode().fetchWordMeaning(
						input);
				if(meaning == null){
					System.out.println("No such word found !!!");
				}else{
					System.out.println("The meaning of the word is " + meaning);
				}
			} catch (Exception exception) {
				System.out.println(exception.getMessage());
			}
			input = scanner.next();
		}
		System.out.println("Dictionary turned off");
	}

	public Node getRootNode() {
		return rootNode;
	}
}