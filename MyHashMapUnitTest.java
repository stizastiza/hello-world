import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class MyHashMapUnitTest {
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	
	private MyHashMap hashmap;
	private Student s1, s2, s3, s4, s5, s6, s7, s8, s9;
	
	@Before
	public void setUp() throws Exception {
		hashmap = new MyHashMap(7);
		s1 = new Student(1, "Max", "Mustermann", "Informatik");
		s2 = new Student(2, "Annika", "Mustermann", "Mathematik");
		s3 = new Student(3, "Alex", "Mustermann", "Biologie");
		s4 = new Student(4, "Julie", "Mustermann", "TI");
		s5 = new Student(5, "Leila", "Mustermann", "Informatik");
		s6 = new Student(6, "Arne", "Mustermann", "Jura");
		s7 = new Student(7, "Bella", "Mustermann", "Physik");
		s8 = new Student(8, "Linus", "Mustermann", "Psychologie");
		s9 = new Student(9, "Albert", "Mustermann", "Informatik");
	}

	/**
	 * Adds an element to the hash table. Checks if it is in the array
	 */
	@Test(timeout=1000)
	public void testSimpleAdd() {
		hashmap.add(s1);
		Student[] array = hashmap.getArray();
		boolean success = false;
		for (int i = 0; i < array.length; i++) {
			Student s = array[i];
			if (s1.equals(s)){
				success = true;
				break;
			}
		}
		assertTrue("add() failed. Element not found in array", success);
	}

	/**
	 * Remove an element from a 
	 */
	@Test(timeout=1000)
	public void testRemove() {
		Student[] array = new Student[7];
		array[5] = s2;  // This is the correct hash position of this student
		hashmap.setArray(array);
		hashmap.remove(s2);
		// Check if the element has been removed
		boolean success = true;
		array = hashmap.getArray();
		for (int i = 0; i < array.length; i++) {
			Student s = array[i];
			if (s2.equals(s)){
				success = false;
				break;
			}
		}
		assertTrue("remove() failed. Element was not removed from hashtable", success);
	}

	@Test(timeout=1000)
	public void testContains() {
		Student[] array = new Student[7];
		array[5] = s2;  // This is the correct hash position of this student
		hashmap.setArray(array);
		assertTrue("contains() failed. Element was not found in the hashtable", hashmap.contains(s2));
	}
	
	/**
	 * Test getNumberStudentsWithHashvalue
	 */
	@Test(timeout=1000)
	public void testGetNumberStudentsWithHashvalue() {
		Student[] array = new Student[7];
		array[5] = s2;  // This is the correct hash position of this student
		array[6] = s4;
		array[0] = s5;
		array[3] = s1;
		array[4] = s3;
		hashmap.setArray(array);
		assertEquals("getNumberStudentsWithHashvalue() failed. Wrong number", 3, hashmap.getNumberStudentsWithHashvalue(5));
	}
	
	
	@Test(timeout=1000)
	public void testTrivialResize() {
		Student[] array = new Student[7];
		array[5] = s2;  // This is the correct hash position of this student
		array[6] = s4;
		array[0] = s5;
		array[3] = s1;
		array[4] = s3;
		hashmap.setArray(array);
		hashmap.resize();
		int newsize = hashmap.getArray().length;
		assertEquals("resize() failed; length of new hash table is not the double of the old one", 14, newsize);
	}
	
	
	
	/* The rest of these tests are not implemented. We include them 
	 * to give you an idea on the parts of your code that we will test for the correction.
	 * Note, that these is not the final list of tests that we will use
	 * We do recommend that you implement these tests. 
	 * But, YOU DO NOT HAVE TO IMPLEMENT THEM!
	 */
	
	public int getIndexOfStudent(MyHashMap a, Student s){
		for(int i = 0; i < a.getArray().length; i++){
			if(a.getArray()[i] == s){
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Tests resize: Checks that each element is saved in the correct position after a resize operation
	 */
	@Test(timeout=1000)
	public void testResize(){
		Student[] array = new Student[7];
		array[5] = s2;  // This is the correct hash position of this student
		array[6] = s4;
		array[0] = s5;
		array[3] = s1;
		array[4] = s3;
		hashmap.setArray(array);
		hashmap.resize();
		int newsize = hashmap.getArray().length;
		assertEquals("resize() failed; length of new hash table is not the double of the old one", 14, newsize);
		assertEquals("resize() failed; Student 2 is not at the correct index", 12, getIndexOfStudent(hashmap, s2));
		assertEquals("resize() failed; Student 4 is not at the correct index", 6, getIndexOfStudent(hashmap, s4));
		assertEquals("resize() failed; Student 5 is not at the correct index", 5, getIndexOfStudent(hashmap, s5));
		assertEquals("resize() failed; Student 1 is not at the correct index", 10, getIndexOfStudent(hashmap, s1));
		assertEquals("resize() failed; Student 3 is not at the correct index", 11, getIndexOfStudent(hashmap, s3));
	}
	
	/**
	 * Test remove an element saved in the wrong position of the hashtable.
	 * This test will set the array of MyHashTable with an array containing one element on the wrong index
	 * We then try removing these elements. This should not be possible.
	 */
	@Test(timeout=1000)
	public void testWrongRemove(){
		exception.expect(RuntimeException.class);
		exception.reportMissingExceptionWithMessage("Student 7 shouldn`t be findable and therefore a RunTimeException should be thrown");
		Student[] array = new Student[7];
		array[5] = s2;  // This is the correct hash position of this student
		array[6] = s4;
		array[0] = s5;
		array[3] = s1;
		array[4] = s3;
		array[2] = s7;
		hashmap.setArray(array);
		hashmap.remove(s7);
	}
	

	/**
	 * Add element to the hash table twice. Checks if an exception is thrown.
	 */
	@Test(timeout=1000)
	public void testAddElementTwice() {
		exception.expect(RuntimeException.class);
		exception.reportMissingExceptionWithMessage("Student 2 is already in the HashMap and a RuntimeException should be thrown");
		Student[] array = new Student[7];
		array[5] = s2;  // This is the correct hash position of this student
		array[6] = s4;
		array[0] = s5;
		array[3] = s1;
		array[4] = s3;
		array[1] = s7;
		hashmap.setArray(array);
		hashmap.add(s2);
	}
	
	/**
	 * Add elements to the hashtable until it is full. Check if an exception is thrown
	 */
	@Test(timeout=1000)
	public void testAddUntilFull() {
		exception.expect(RuntimeException.class);
		exception.reportMissingExceptionWithMessage("The HashMap is already full and a RuntimeException should be thrown");
		hashmap.add(s1);
		hashmap.add(s2);
		hashmap.add(s3);
		hashmap.add(s4);
		hashmap.add(s5);
		hashmap.add(s6);
		hashmap.add(s7);
		hashmap.add(s8);
		
	}
	
	/**
	 * Add elements to the hashtable until it is full.
	 * Tests, if all elements are in the hashmap and if they are in correct positions
	 */
	@Test(timeout=1000)
	public void testAddUntilExactlyFull() {
		hashmap.add(s1);
		hashmap.add(s2);
		hashmap.add(s3);
		hashmap.add(s4);
		hashmap.add(s5);
		hashmap.add(s6);
		hashmap.add(s7);
		
		for(int i = 0; i < hashmap.getArray().length; i++){
			if(hashmap.getArray()[i] == null){
				fail("Es wurden nicht alle Studenten eingefügt");
			}
		}
		assertEquals("Student 1 was not added correctly", s1, hashmap.getArray()[3]);
		assertEquals("Student 2 was not added correctly", s2, hashmap.getArray()[5]);
		assertEquals("Student 3 was not added correctly", s3, hashmap.getArray()[4]);
		assertEquals("Student 4 was not added correctly", s4, hashmap.getArray()[6]);
		assertEquals("Student 5 was not added correctly", s5, hashmap.getArray()[0]);
		assertEquals("Student 6 was not added correctly", s6, hashmap.getArray()[1]);
		assertEquals("Student 7 was not added correctly", s7, hashmap.getArray()[2]);
		
	}
	/**
	 * Add an element, then remove it. Check what contains return.
	 */
	@Test(timeout=1000)
	public void testAddRemoveContains() {
		hashmap.add(s1);
		hashmap.add(s2);
		hashmap.remove(s1);
		assertFalse("Student on was removed from the HashMap but the contains method still returns true", hashmap.contains(s1));
	}
	
	/**
	 * Remove an element twice. Check for the exception.
	 */
	@Test(timeout=1000)
	public void testRemoveElementTwice() {
		exception.expect(RuntimeException.class);
		exception.reportMissingExceptionWithMessage("Student 1 was already remove and thus a RuntimeException should be thrown");
		hashmap.add(s1);
		hashmap.add(s9);
		hashmap.remove(s1);
		hashmap.remove(s1);
	}
	
	/*
	 * Test if a student is added correctly (continued at the start) if there is no more room at the end of the array
	 */
	@Test(timeout=1000)
	public void testAddWhenEndIsFull(){
		Student[] array = new Student[7];
		array[5] = s2;  // This is the correct hash position of this student
		array[6] = s4;
		hashmap.setArray(array);
		hashmap.add(s5);
		assertEquals("The student was not correctly added", s5, hashmap.getArray()[0]);
	}
	
	/*
	 * Tests if a student is being removed correctly (continued at the start) if there is no more room at the end of the array
	 */
	@Test(timeout=1000)
	public void testRemoveOverEnd(){
		//System.out.println(hashmap.hashFunction(s6));
		Student[] array = new Student[7];
		array[5] = s2;  // This is the correct hash position of this student
		array[6] = s4;
		array[0] = s6;
		array[1] = s5;
		array[3] = s1;
		array[4] = s3;
		hashmap.setArray(array);
		hashmap.remove(s2);
		
		assertFalse("Student 4 was not removed", hashmap.contains(s2));
		assertEquals("Linear probing does not work correctly", s5, hashmap.getArray()[6]);
		assertEquals("Linear probing does not work correctly", null, hashmap.getArray()[1]);
	}
	
	/*
	 * Tests if a student is being found correctly (continued at the start) if there is no more room at the end of the array
	 */
	@Test(timeout=1000)
	public void testContainsOverEnd(){
		Student[] array = new Student[7];
		array[5] = s2;  // This is the correct hash position of this student
		array[6] = s4;
		array[0] = s6;
		array[1] = s5;
		array[3] = s1;
		array[4] = s3;
		hashmap.setArray(array);
		
		assertTrue("Rewinding does not work in contains", hashmap.contains(s5));
	}
	
	/*
	 * Tests if the GetNumberStudentsWithHashValue was implemented efficiently 
	 * We currently don’t know how efficient it must be so values may differ
	 */
	@Test(timeout=1000)
	public void testEfficientGetNumberStudentsWithHashValue(){
		Student[] array = new Student[7];
		array[5] = s2;  // This is the correct hash position of this student
		array[6] = s4;
		array[1] = s5;
		array[3] = s1;
		array[4] = s3;
		hashmap.setArray(array);
		
		assertEquals("Not solved efficiently, probably just iterated over whole array", 2, hashmap.getNumberStudentsWithHashvalue(5));
	}
	
	/*
	 * Test if a student with a higher hash value is being moved in remove, if the move is necessary
	 * Hashvalues in the array
	 * Before:	After:
	 * 0: 0		0: 5
	 * 1: 5		1: null
	 * 2: null	2: null
	 * 3: null	3: null
	 * 4: null 	4: null
	 * 5: 5		5: 5
	 * 6: 5 	6: 5
	 */
	@Test(timeout=1000)
	public void testWeirdThingWithRemove(){
		Student[] array = new Student[7];
		array[0] = s6;
		array[1] = s5;
		array[5] = s2;
		array[6] = s4;
		hashmap.setArray(array);
		
		hashmap.remove(s6);
		assertEquals("Student 6 was not removed or Student 5 was not moved correctly", s5, hashmap.getArray()[0]);
	}
	
	/*
	 * Tests, if remove works correctly in a full hashmap 
	 */
	@Test(timeout=1000)
	public void TestComplexRemove(){
		hashmap.add(s1);
		hashmap.add(s2);
		hashmap.add(s3);
		hashmap.add(s4);
		hashmap.add(s5);
		hashmap.add(s6);
		hashmap.add(s7);
		
		hashmap.remove(s2);
		hashmap.remove(s5);

		assertEquals("An error ocurred in remove in array[0]", s6, hashmap.getArray()[0]);
		assertEquals("An error ocurred in remove in array[1]", null, hashmap.getArray()[1]);
		assertEquals("An error ocurred in remove in array[2]", null, hashmap.getArray()[2]);
		assertEquals("An error ocurred in remove in array[3]", s1, hashmap.getArray()[3]);
		assertEquals("An error ocurred in remove in array[4]", s3, hashmap.getArray()[4]);
		assertEquals("An error ocurred in remove in array[5]", s4, hashmap.getArray()[5]);
		assertEquals("An error ocurred in remove in array[6]", s7, hashmap.getArray()[6]);
		
		hashmap.remove(s7);
		hashmap.remove(s1);
		hashmap.remove(s4);
		hashmap.remove(s3);
		hashmap.remove(s6);
		
		for(int i = 0; i < hashmap.getArray().length; i++){
			if(hashmap.getArray()[i] != null){
				fail("Student at position" + i + "was not removed");
			}
		}
	}
	
	/*
	 * Tests if a Student with a lower hash value than the removed one, who is in the correct place was being moved
	 * Hashvalues in the array
	 * Before:	After:
	 * 0: 4		0: null
	 * 1: 1		1: 1
	 * 2: 1 	2: 1
	 * 3: 3		3: 3
	 * 4: 4		4: 4
	 * 5: 4		5: 4
	 * 6: 4		6: 4
	 */
	@Test(timeout=1000)
	public void testRemoveCase(){
		Student[] array = new Student[6];
		array[0] = s1;
		array[1] = s4;
		array[2] = s5;
		array[3] = s6;
		array[4] = s3;
		array[5] = s2;
		hashmap.setArray(array);		
		hashmap.remove(s1);
		assertEquals("Student 5 should not be moved", s5, hashmap.getArray()[2]);
		assertEquals("Student 1 was not removed", null, hashmap.getArray()[0]);
	}
	

}
