import java.io.IOException;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class FastaParser {

	ArrayList<String> FastaList = new ArrayList<String>();
	/**
	 * checks non-identifier line
	 * @param line 
	 * @return false -> goes to an exception
	 * @return true -> line will be added to the current ListElement
	 */
	public boolean onlyLetterChecking(String line) {
		for (int i = 0; i<line.length(); i++) {
			if (Character.isLetter(line.charAt(i)) == false) {
				return false;
			}
		}
		if (line.length()==0) {
			return false;
		}
		else {
		return true;
		}
	}
	/**
	 * Parse Fasta File and return list of sequences
	 * @param file to read
	 * @return ArrayList of String Objects containing the sequences
	 * IDEA:
	 * FastaList := ArrayList to return. My task is to fill it with sequences.
	 * Sequence contains only Letters. Starts with ">" and Letters following it after \n
	 * An element of FastaList starts with a title ">" and ends when next line starts with another ">".
	 * member switcher switches allowing to add line to the element of the list or not. 
	 */
	
	public ArrayList<String> readSequences(File file) throws FastaException, IOException {
		
		String LastLine = "";
	    RandomAccessFile input = null;
	    String FastaListElement = "";
	    input = new RandomAccessFile(file, "r");
	    String line;
	    boolean switcher = true;
	    int i = 1; //start with a line 1
	    while ((line = input.readLine()) != null) { // read file line by line, if line == null, end is reached
	    	// Process possible exceptions
	    	// Process very first line. It has to be an identifier:
	    	if (i==1 && line.startsWith(">")==false) {
	    		input.close();
	    		throw new FastaException("Expected identifier");
	    	} 
	    	else {
		    	// process sequence lines:
	    		if (!line.startsWith(">")) {
		    		if (onlyLetterChecking(line)) {
			    		FastaListElement = FastaListElement + line;
			    		//switchOff(switcher); 
			    		switcher = false;
			    		}
			    	else {
			    		//WORKS ALSO FOR EMPTY SEQUENCES:
			    		input.close(); 
			    		throw new FastaException("Expected sequence on line " + i);
			    		}
		    		} 
		    	else {
		    	// sequence ID comes twice
		    	if (LastLine.startsWith(">") && line.startsWith(">")) {
		    		input.close();
		    		throw new FastaException("Expected sequence on line " + i);
		    	}
		    	// sequence ID is missing
		    	else if (line.startsWith(">") && line.length()<2) {
		    		input.close();
		    		throw new FastaException("Expected identifier");
		    	}
		    	//sequence ended here. This line is ID, switcher must be off 
		    	else if (line.startsWith(">") && switcher == false) {
		    		FastaList.add(FastaListElement);
		    		FastaListElement = "";
		    		switcher = true;
		    	}
		    		} 
		    LastLine = line;
		    i++;
	    }
	    }
	    //Everything what is happening after stream reaches last line:
	    FastaList.add(FastaListElement);
	    //last line was an ID, sequence is expected:
	    if (LastLine.startsWith(">")) {
	    	input.close();
	    	throw new FastaException("Expected sequence on line " + i+1);
	    }
	    //file is empty:
	    if (i==1) {
	    	input.close();
	    	throw new FastaException("Expected identifier");
	    }
	    input.close();
	    return FastaList;
	}

	/**
	 * Runs readSequence on the input and returns either a string presentation of the resulting ArrayList or, 
         * in case of an Exception, the corresponding message (hint: Exceptions are normal objects with fields/ methods) 
	 * @param file to read
	 * @return String representation of result list or exception message
	 */
	public String handleException(File f) throws FastaException, IOException {
		String result;
		try {
			result = readSequences(f).toString();
		} catch(FastaException k) {
			return k.getMessage();
		} catch(IOException e) {
			return e.getMessage();
		}
		
	    return result;
	}
}

