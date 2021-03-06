/**
 * 
 * @author Algorithm and Datastructures Team SS2016
 * @version 1.0  
 * 
 */
import java.lang.RuntimeException;
public class MyHashMap {
	
	/**
	 * Use this array to store the values
	 * DO NOT MODIFY OR REMOVE THIS Attribute. Our tests rely on it.
	 */
	Student[] array;
	/**
	 * Tries inserting a Student into the hash map.
	 * Throws RunTimeException, if the student is already in the table or the table is full.
	 */
	public void add(Student s){
		//Variables
		int HashValue = hashFunction(s);
		int HashCopy = HashValue;
		//Throw an Exception, if the student is already in the table
		if (contains(s)) {
			throw new RuntimeException();
		}
		//No collision:
		if (this.array[HashValue] == null) {
			this.array[HashValue] = s;
		}
		// collision. Use technology:
		else {
			boolean Switcher = true;
			//Switcher switches to false, when a free place is found
			while(Switcher) {
				//it goes up to the length and checks every single place
				if(HashValue+1<this.array.length) {
					HashValue++;
					if (this.array[HashValue] == null) {
						this.array[HashValue] = s;
						Switcher = false;
					}
				}
				//when length is reached, we start from 0 and go again. but this time only up to old HashValue:=HashCopy
				else {
					for (int i = 0; i<HashCopy; i++) {
						if (this.array[i] == null) {
							this.array[i] = s;
							Switcher = false;
							break;
						}
					}
					if (Switcher) {
					//we did it through the whole array and while is still on
					//it means, the table is full:
						Switcher = false;
						throw new RuntimeException();
					}
				}
			}	
		}	
	}
	
	/**
	 * Try removing a Student from the hash table.
	 * You should use the same implementation for remove discussed in the tutorial.
	 * You should NOT use the lazy deletion strategy (adding a special flag key indicating a deleted key)
	 * See https://en.wikipedia.org/wiki/Linear_probing#Deletion for more details about the algorithm!
	 * Throw RunTimeException if the hash table contained the Student
	 * @param Student s to delete from the hash table
	 * (1) Set the position 'i' of the student to 'null'
	 * (2) go up from new empty position 'i' till i find another empty position. In between, if i see
	 * (2.1) Students, which hashValue is <= 'i' i put them in i. WHICH makes their current space empty too.
	 * (2.2) so it works recursive till i find an empty cell.
	 */
	public void remove(Student s){
		//TODO: Your Code here.
		// (1) OR Exception
		if (!contains(s)) {
			throw new RuntimeException();
		}
		int i = findPosition(s);
		// delete my element. Create a null-space!
		this.array[i] = null;
		// (2) run up to next null element
		while (array[(i+1)%this.array.length] != null) {
			//take next student and save it as s1 
			Student s1 = array[(i+1)%this.array.length];
			// no matter of what, delete it.
			array[(i+1)%this.array.length] = null;
			// correct working function 'add' will find an appropriate place: it will be the same or one higher
			this.add(s1);
			i++;
		}
	}
	public int findPosition(Student s) {
		int i = this.hashFunction(s);
		while (i>=0) {
			if (this.array[i%this.array.length] != null) {
			if (s.equals(this.array[i%this.array.length])) {
				return i%this.array.length;
			}
			}
			i++;
		}
		return -1;
	}
	
	/**
	 * Returns true, if the hash table contains the given Student
	 * IT SHOULD ALSO NOT BE ABLE TO FIND STUDENTS,
	 * WHICH ARE PLACED WRONG
	 */
	public boolean contains(Student s){
		//TODO: Your Code here.
		int i = this.hashFunction(s);
		while(i<=this.hashFunction(s)*2) {
			if (this.array[i%this.array.length] != null) {
				if (s.equals(array[i%this.array.length])) {
					// check, if its an appropriate place for it:
					if (isAppropriate(s)) {
						return true;
					}
					else {
						return false;
					}
				}
			}
			i++;
		}
		return false;
	}
	public boolean isAppropriate(Student s) {
		int Position = findPosition(s);
		this.array[Position] = null;
		this.add(s);
		if (findPosition(s) == Position) {
			return true;
		}
		else {
			return false; 
		}
	}
	
	/**
	 * @param h Hashvalue to search for
	 * @return Number of Student in the hash table that have the hashvalue h
	 */
	
	public int getNumberStudentsWithHashvalue(int h){
		int n = 0;
		int i = h;
		while (i<=h*2) {
			if (this.array[i%this.array.length]!=null) {
				if (this.hashFunction(this.array[i%this.array.length]) == h) {
					n++;
				}
			}
			i++;
		}
		
		return n;
	}
	
	/**
	 * Doubles the size of the hash table. Recomputes the position of all elements using the
	 * new function.
	 */
	public void resize(){
		//TODO: Your Code here.
		//copy my previous-sized array:
		Student[] newArray = this.array;
		//create an integer value for the new length to work with it
		int DoubledLength = newArray.length*2;
		// create another array with new size:
		Student workArray[] = new Student[DoubledLength];
		// set this.array with a newLength:
		this.setArray(workArray);
		for (int i = 0; i<newArray.length; i++) {
			if (newArray[i] != null) {
				this.add(newArray[i]);
			}
		}
	}

	/**
	 * This method return the array in which the strings are stored.
	 * DO NOT MODIFY OR REMOVE THIS METHOD. Our tests rely on it.
	 */
	public Student[] getArray(){
		return array;
	}
	
	/**
	 * DO NOT MODIFY OR REMOVE THIS METHOD. Our tests rely on it.
	 */
	public void setArray(Student[] array){
		this.array = array;
	}

	/**
	 * Runs the hash function for Student s (dependent on the size of the hash table)
	 * DO NOT MODIFY OR REMOVE THIS METHOD. Our tests rely on it.
	 * @param s Student
	 * @return the hash value for a student. (The position where it would be saved given no collisions)
	 */
	public int hashFunction(Student s){
		int hashvalue = Math.abs(s.hashCode()) % array.length;
		return hashvalue;
	}
	
	/**
	 * Constructor to initialize the hash with a given capacity
	 * DO NOT MODIFY OR REMOVE THIS METHOD. Our tests rely on it.
	 */
	public MyHashMap(int capacity){
		array = new Student[capacity];
	}

}

