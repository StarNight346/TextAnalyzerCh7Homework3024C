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

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SizeOfArray {

	@Test
	void test() {
		//before looking for duplicates
		TextAnalyzer test = new TextAnalyzer(); //Creates a new TextAnalyzer
		List<String> stringTest = test.ReadFile("TheRavenPoem.txt"); //Creates a list of Strings called stringTest by reading the file ("TheRavenPoem.txt")
		int output = stringTest.size(); //Gets the size of the initial array from reading the file ("TheRavenPoem.txt")
		assertEquals(1075, output); //Checks to make sure the size of the initial array is equal to 1075
	}

}
