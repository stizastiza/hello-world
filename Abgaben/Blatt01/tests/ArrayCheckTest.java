import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;


public class ArrayCheckTest {
	// NOTTODO DIESE ZEILE BITTE NICHT VERAENDERN!!
	// TODO Fuehrt eure Tests auf diesem ArrayCheck-Objekt aus!
	// Ansonsten werden unsere Tests fehlschlagen
	public ArrayCheck arrayCheckTestObject = new ArrayCheck();
	ArrayList<Integer> emptyArray = new ArrayList<Integer>(); //will stay empty!
	ArrayList<Integer> testArray = new ArrayList<Integer>();
	ArrayList<Character> charList = new ArrayList<Character>();
	ArrayList<Character> charList2 = new ArrayList<Character>();
	ArrayList<Character> emptyList = new ArrayList<Character>();
	ArrayList<Character> randomWord = new ArrayList<Character>();
	
	@Before
	public void setUp(){
		// for the first test:
		testArray.add(5);
		testArray.add(10);
		testArray.add(25);
		// for the second test:
		charList.add('I');
		charList.add(' ');
		charList.add('a');
		charList.add('m');
		charList.add(' ');
		charList.add('L');
		charList.add('o');
		charList.add('r');
		charList.add('d');
		charList.add(' ');
		charList.add('V');
		charList.add('o');
		charList.add('l');
		charList.add('d');
		charList.add('e');
		charList.add('m');
		charList.add('o');
		charList.add('r');
		charList.add('t');
		
		charList2.add('T');
		charList2.add('o');
		charList2.add('m');
		charList2.add(' ');
		charList2.add('M');
		charList2.add('a');
		charList2.add('r');
		charList2.add('v');
		charList2.add('o');
		charList2.add('l');
		charList2.add('o');
		charList2.add(' ');
		charList2.add('R');
		charList2.add('i');
		charList2.add('d');
		charList2.add('d');
		charList2.add('l');
		charList2.add('e');
		
		randomWord.add('H');
		randomWord.add('e');
		randomWord.add('l');
		randomWord.add('l');
		randomWord.add('o');
	}

	@Test(timeout = 1000)
	public void testAllDivisibleBy() {
		
		// TODO Schreibt verschiedene Testfaelle, die unterschiedliche Arrays
		// anlegen und an arrayCheck.testAllDivisibleBy uebergeben.
		// Benutzt nur diese Methode.
		// Testet auch randfaelle wie z.B. leere arrays.
		// Bsp: assertTrue("mein test 1", arrayCheckTestObject.allDivisibleBy(testArray, 7));
		assertFalse("Testen mit Randfaelle: teste mit divisor = 0", arrayCheckTestObject.allDivisibleBy(testArray, 0));
		assertTrue("Testen mit einem ganz normallen Fall: teste mit divisor = 5", arrayCheckTestObject.allDivisibleBy(testArray,  5));
		assertFalse("Teste mit 7", arrayCheckTestObject.allDivisibleBy(testArray, 7));
		assertFalse("Teste mein leeres Array", arrayCheckTestObject.allDivisibleBy(emptyArray, 7));
		// Ich wuerde es noch voll gerne mit einem nicht existierenden Array testen, klappt aber nicht - es meckert. 
	}

	@Test(timeout = 1000)
	public void testIsAnagram() {
		// TODO Schreibt verschiedene Testfaelle fuer isAnagram in diese Methode
		// Bsp: assertTrue("mein test 2", arrayCheckTestObject.isAnagram(testArray1, testArray2));
		
		// TODO Schreibt verschiedene Testfaelle fuer isAnagram
		assertFalse("Testen mit den leeren Arrays. Versuch 1. Beide sind leer.", arrayCheckTestObject.isAnagram(emptyList, emptyList));
		assertFalse("Testen mit den leeren Arrays. Versuch 2. Eins ist leer.", arrayCheckTestObject.isAnagram(charList, emptyList));
		assertTrue("Voldemort Test", arrayCheckTestObject.isAnagram(charList, charList2));
		assertFalse("Test mit nicht Anagrams", arrayCheckTestObject.isAnagram(charList, randomWord));
		
	}


}

