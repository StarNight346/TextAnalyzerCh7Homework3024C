package JunitTestingCh7;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class SizeOfSortedArray {

	@Test
	void test() {
		//After looking for duplicates
		TextAnalyzer test = new TextAnalyzer(); //Creates a new TextAnalyzer
		List<String> stringTest = test.ReadFile("TheRavenPoem.txt"); //Creates a list of Strings called stringTest by reading the file ("TheRavenPoem.txt")
		
		//Creating the WordList by checking each word to eliminate redundancies and calculate frequency 
		for (String input: stringTest)
		{
		test.CheckWord(input);
		}
		
		int output2 = test.WordList.size(); //Checks the size of the WordList that is has been sorted to eliminate redundancies 
		assertEquals(553, output2); //Checks to make sure the size of the WordList array is equal to 553
	}

}
