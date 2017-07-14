import java.util.ArrayList;
import java.util.Collections;

/**
 * This class implements two methods. These check an array on a few
 * characteristics.
 * 
 * @author AlgoDat-Tutoren
 * 
 */
public class ArrayCheck {

	/**
	 * Tests all elements of the given array, if they are divisible by the given
	 * divisor.
	 * 
	 * @param arr
	 *            array to be tested
	 * @param divisor
	 *            number by which all elements of the given array should be
	 *            divisible
	 * @return true if all elements are divisible by divisor
	 */
	public boolean allDivisibleBy(ArrayList<Integer> arr, int divisor) {
        if (arr == null || divisor == 0 || arr.size()==0) {
        	return false;
        }
        for (int toCheck: arr) {
        	if (toCheck%divisor != 0) {
        		return false;
        	}
        }
        return true;
    }

	/**
	 * Tests if the given two arrays are anagrams of each other
	 * My plan:
	 * Build two new dynamic arrays, which are copies of those two invoked BUT
	 * without spaces and special characters. With every single letter as a lower case (Character.toLowerCase())
	 * sort them out with Collections.sort(), compare their size(), compare their letters. Profit.
	 * @param arr1
	 *            first array to be compared
	 * @param arr2
	 *            second array to be compared
	 * @return true if the two arrays are an anagram
	 */
    public boolean isAnagram(ArrayList<Character> arr1, ArrayList<Character> arr2) {
        // check, if one of invoked arrays NULL is. If it`s the case -> return false:
    	if (arr1 == null || arr2 == null) {
        	return false;
        }
    	if (arr1.size() == 0 || arr2.size() == 0){
    		return false;
    	}
    	// here are two dynamic arrays i was talking about:
        ArrayList<Character> copy1 = new ArrayList<Character>();
        ArrayList<Character> copy2 = new ArrayList<Character>(); 
        // fill them without special symbols:
        for (int i=0; i<arr1.size(); i++){
        	if (Character.isLetter(arr1.get(i))) {
        		copy1.add(Character.toLowerCase(arr1.get(i)));
        	}
        }
        for (int i=0; i<arr2.size(); i++){
        	if (Character.isLetter(arr2.get(i))) {
        		copy2.add(Character.toLowerCase(arr2.get(i)));
        	}
        }
        // compare their sizes:
        if (copy1.size() != copy2.size()) {
        	return false;
        }
        // sort them:
        Collections.sort(copy1);
        Collections.sort(copy2);
        // compare them:
        for (int i=0; i<copy1.size(); i++){
        	if (copy1.get(i) != copy2.get(i)) {
        		return false;
        	}
        }
        // at this point we`ve nothing more to check, we can say, 
        // that they are Anagrams.
        return true;
    }

}

