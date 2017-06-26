import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class implementing a Union-Find-data structure with representatives.
 * 
 * @author AlgoDat-Team
 */
public class UnionFindSet<T>{

	//You can use this map to store the representant for each element of the Union find structure
    private HashMap<T,T> element2representative;

	public UnionFindSet() {
		element2representative = new HashMap<>();
	}

	/**
	 * Takes a collection of n elements and adds 
	 * n disjoint partitions each with one element in it.
	 * 
	 * @param set
	 *            The set to be partitioned.
	 */
	public void add(List<T> elements) {
		// TODO Homework 2.1
		for (T element: elements) {
			element2representative.put(element, element);
		}
	}

	/**
	 * Creates one disjoint partition with the element in it 
	 * and adds it to the Union-Find data structure
	 * 
	 * @param element
	 *            The element to put in the partition.
	 */
	public void add(T element) {
		// TODO Homework 2.1
		element2representative.put(element, element);
	}

	/**
	 * Retrieves the partition which contains the wanted element.
	 * 
	 * A partition is identified by a single, representative element.
	 * This function returns the representative of the partition
	 * that contains x. 
	 * 
	 * @param x
	 *            The element whose partition we want to know
	 * @return 
	 *            The representative element of the partition
	 */
	public T getRepresentative(T x) {
		// TODO Homework 2.1
		return element2representative.get(x);
	}

	/**
	 * Joins two partitions. First it retrieves the partitions containing the
	 * given elements. Removes one of the joined partitions from
	 * <code>partitions</code>.
	 * element x wird aus seiner Patition geloescht und zu der Partition von y hinzugefuegt.
	 * Ohne WhereToPut & WhereToRemove bekomme ich Concurrent Modification Exception 
	 * @param x
	 *            One element whose partition is to be joined.
	 * @param y
	 *            The other element whose partition is to be joined.
	 */
	public void union(T x, T y) {
		// TODO Homework 2.1
		//
		T WhereToPut = getRepresentative(y);
		T WhereToRemove = getRepresentative(x);
		for (T fleissig: element2representative.keySet()) {
			if (getRepresentative(fleissig) == WhereToRemove) {
				element2representative.put(fleissig, WhereToPut);
				element2representative.remove(fleissig, WhereToRemove);
			}
		}
	}
}

