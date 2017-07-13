
public class Pair<T> {
	/**
	 * members:
	 * first, second - entities of type T
	 */
	T first, second;
	Pair(T first, T second) {
		this.first = first;
		this.second = second;
	}
	/**
	 * 
	 * @return FIRST OBJECT
	 */
	public T getFirst() {
		return this.first;
	}
	/**
	 * 
	 * @return SECOND OBJECT
	 */
	public T getSecond() {
		return this.second;
	}
	/**
	 * 
	 * @param dt is a new value for first object
	 */
	public void setFirst(T dt) {
		this.first = dt;
	}
	/**
	 * 
	 * @param dt is a new value for second object
	 */
	public void setSecond(T dt) {
		this.second = dt;
	}
	
	public void swap() {
		T swap;
		swap = this.first;
		this.first = this.second;
		this.second = swap;
	}
	/**
	 * it is a pair object so i return both of them as a string
	 */
	@Override
	public String toString() {
		return this.first + ", " + this.second;
	}
}

