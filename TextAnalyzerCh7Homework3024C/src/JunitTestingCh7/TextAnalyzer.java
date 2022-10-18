package JunitTestingCh7;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


//Creating a class to handle which words are in the file and their frequency
class Word {
	
	String word;
	int frequency;
	
	Word(String word) {
		this.word = word;
		frequency = 1;
	}
	
	public int getFrequency() {
		return this.frequency;
	}
}

//Creating a class to store a list of words
class WordList {
	
	static List<Word> SortedWordList = new ArrayList<Word>();
	
	public void SetWordList (List<Word> SortedWordList) {
		this.SortedWordList = SortedWordList;
	}
	
	public int GetWordListSize () {
		return SortedWordList.size();
	}
}

	
public class TextAnalyzer {
	
	//constructing the GUI
	private static void constructGUI()
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		MyFrame frame = new MyFrame();
		frame.setVisible(true);
	}
	
	//Create lists
	static List<Word> WordList = new ArrayList<Word>();
	static List<String> StringList = new ArrayList<String>();

	
	//creates a list of every word in the file
	public static List<String> ReadFile(String file) {
		
		
		//Try to read all lines in the file
		try {
		    Scanner scnr = new Scanner(new File(file));
		    while (scnr.hasNextLine()) {
		    String data = scnr.next();
		    StringList.add(data);
		      }
		    scnr.close();
		}
		//Catches exceptions if found
		catch (Exception e) {
			System.out.println("You have an error.");
			e.printStackTrace();
		}
		
		return StringList;
	}
	
	//checks to see if word is in unique word array and adds frequency if it is
	public static void CheckWord(String word) {
		
		if(WordList.size() == 0) {
			WordList.add(new Word(word));
		} else {
			boolean isMatch = false;
			
			for (Word input: WordList) {
				
			if(word.equals(input.word)) {
				input.frequency += 1;
				isMatch = true;
			}				
		  }
			if (isMatch == false) {
				WordList.add(new Word(word));
			}
		}
	 }
	
	//Method to sort the word by frequency
	public static List<Word> SortWord (List<Word> wordList) {
		
		Comparator<Word> byFrequency = Comparator.comparing(Word::getFrequency);
		wordList.sort(byFrequency.reversed());
		
		return wordList;
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//reads file
		ReadFile("TheRavenPoem.txt");	
		
		//checks words
		for (String input: StringList)
			{
			CheckWord(input);
			}
		
		//sort words
		WordList = SortWord(WordList);
		
		WordList SortedWordList = new WordList();
		
		SortedWordList.SetWordList(WordList);
		
		//outputs words
		for (Word input: WordList)
		{
		System.out.print("Word: " + input.word + "\n " + "Frequency: " + input.frequency + "\n\n");;
		}
		
		System.out.print(SortedWordList.GetWordListSize() + " ");
		
		//Creates GUI on Screen
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run() {
			constructGUI();
			}
		});
	}
	
	
}


//Creating the action listener
class MyButtonListener implements ActionListener {
	MyFrame fr;
	public MyButtonListener(MyFrame frame)
	{
		fr = frame;
	}

	public void actionPerformed(ActionEvent e) 
	{
		JButton btn = (JButton) e.getSource();
		List<String> StringList = new ArrayList<String>();
		
		//Checks to see if the word matches the word being searched for. If so creates a word and displays the frequency, if not returns that it does not exist in the poem
		if (WordList.SortedWordList.stream().filter(x -> x.word.equalsIgnoreCase(fr.WordField.getText())).findFirst().isPresent()) {
			Word matchedWord = WordList.SortedWordList.stream().filter(x -> x.word.equalsIgnoreCase(fr.WordField.getText())).findFirst().get();
			fr.result.setText("Results: The word " + matchedWord.word + " appears in the poem " + matchedWord.getFrequency() + " times");
	    } else {
	    	fr.result.setText("Results: The word " + fr.WordField.getText() + " does not appear in the poem");
	    }
	}
}

//creates a class for the Jframe
class MyFrame extends JFrame {
	public JLabel result;
	public JTextField WordField;
	
	public MyFrame() {
		super();
		init();
	}
	
	//Initializing the MyFrame class
private void init() {
	this.setTitle("Text Analyzer for the Raven"); //Setting title
	JLabel wordSearchLabel = new JLabel("Word to be searched"); //Label for the first number
	JButton search = new JButton("Search"); //Calculate button
	search.addActionListener(new MyButtonListener(this)); //Actionlistener for the calc button
	WordField = new JTextField(); //Text for first number
	result = new JLabel("Please enter a word that you would like to search, and press \"search\" to receive results"); //Jlabel for results
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setLayout(new GridLayout(3, 2)); //Using the gridlayout
	//Adding everything to the GUI
	this.add(wordSearchLabel);
	this.add(WordField);
	this.add(new JLabel());
	this.add(search);
	this.add(result);
	this.pack();
	this.setVisible(true);
	}
}