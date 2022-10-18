package JunitTestingCh7;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class RavenWordFrequency {

	@Test
	void test() {
		TextAnalyzer test = new TextAnalyzer(); //Creates a new TextAnalyzer
		List<String> stringTest = test.ReadFile("TheRavenPoem.txt"); //Creates a list of Strings called stringTest by reading the file ("TheRavenPoem.txt")
		
		//Creating the WordList by checking each word to eliminate redundancies and calculate frequency 
		for (String input: stringTest)
		{
		test.CheckWord(input);
		}
		
		//Gets the frequency for the word "Raven"
		int output = test.WordList.stream().filter(x -> x.word.equalsIgnoreCase("raven")).findFirst().get().getFrequency();
		assertEquals (4, output); //Checks to make sure frequency is 4
	}

}
