package JunitTestingCh7;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class NotInRaven {

	@Test
	void test() {
		TextAnalyzer test = new TextAnalyzer(); //Creates a new TextAnalyzer
		List<String> stringTest = test.ReadFile("TheRavenPoem.txt"); //Creates a list of Strings called stringTest by reading the file ("TheRavenPoem.txt")
		
		//Creating the WordList by checking each word to eliminate redundancies and calculate frequency 
		for (String input: stringTest)
		{
		test.CheckWord(input);
		}
		
		String fail = "Pass";
		
		//Tries to get the word "silent" from the Raven Poem
		try {
		int output = test.WordList.stream().filter(x -> x.word.equalsIgnoreCase("silent")).findFirst().get().getFrequency();
		
		} catch (java.util.NoSuchElementException e)
		{
			fail = "fail"; //sets fail = "fail" if there is a no such element exception
		}

		assertEquals ("fail", fail); //Checks to see if fail == "fail" which it should be because it should be a no such element exception
		
	}

}
