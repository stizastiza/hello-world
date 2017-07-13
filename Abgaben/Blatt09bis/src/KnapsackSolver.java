import java.util.LinkedList;
import java.util.List;
import java.math.*;

/**
 * 
 * @author AlgoDat Team
 *
 */
public class KnapsackSolver {
	/**
	 * members:
	 * KSacks : all of the possible Knapsacks
	 */
	LinkedList<Knapsack> KSacks = new LinkedList<Knapsack>();
	/**
	 * Tries all possible item combination to solve the knapsack problem. Returns the optimal solution.
	 * @param k Empty knapsack with a maximum capacity to fill
	 * @param items a list of items each with a weight and a value
	 * @return the filled knapsack
	 */
	public Knapsack solveKnapsackOptimal(Knapsack k, LinkedList<Item> items){
		//TODO: Implement this
		// POSSIBLE MISTAKES:
		if (k.maxWeight <= 0) {
			return null;
		}
		
		// brute force fills KSacks with every possible Knapsack:
		for (int i = 1; i<=items.size(); i++) {
			int checker = 0;
			int kWeight = k.getMaximumWeight();
			bruteForce(items, i, kWeight, checker);
		}
		
		
		// iterate KSacks to find the most efficient KnapSack:
		Knapsack Best =  KSacks.get(0);
		for (Knapsack KS: KSacks) {
			if (KS.currentValue > Best.currentValue) {
				Best = KS;
			}
		}
		k = Best;
		return k;
	}
	/**
	 * 
	 * @param items
	 * @param i
	 * @param k
	 * @param checker : the most important control unit. Has two purposes:
	 * (1) checks, if the right count of combinations is in KSacks
	 * (2) does not count repeatable combinations
	 */
	public void bruteForce(LinkedList<Item> items, int i, int kWeight, int checker) {
		int sPoint = 0;
		Knapsack tmp = new Knapsack(kWeight);
		for (Item item: items) {
				if (!tmp.getItemsInKnapsack().contains(item)) {
					tmp.addItem(item);
					sPoint++;
				}
				if (sPoint == i) {
					break;
				}
		}
		if (!KSacks.contains(tmp)) {
			KSacks.add(tmp);
			checker++;
		}
		if (checker < Math.pow(2, i*2)) {
			bruteForce(items, i, kWeight, checker);
		}
	}
	
	/**
	 * Uses the trivial greedy algorithm to solve the Knapsack problem. 
	 * @param k Empty knapsack with a maximum capacity to fill
	 * @param items a list of items each with a weight and a value
	 * @return the filled knapsack
	 */
	public Knapsack solveKnapsackGreedyStupid(Knapsack k, LinkedList<Item> items){
		//TODO: Implement this
		LinkedList<Item> Sorted = findMaxValue(items);
		for (int i = Sorted.size()-1; i>=0; i--) {
			k.addItem(Sorted.get(i));
		}
		return k;
	}
	public LinkedList<Item> findMaxValue(LinkedList<Item> items) {
		LinkedList<Item> Sorted = new LinkedList<Item>();
		Sorted.addAll(items);
		// insertion sort:
		int j;
		Item key;
		int i;
		for (j = 1; j<Sorted.size(); j++) {
			key = Sorted.get(j);
			for (i=j-1; (i>=0) && (Sorted.get(i).getValue() < key.getValue()); i--) {
				Sorted.add(i+1, Sorted.get(i));
			}
			Sorted.add(i+1, key);
		}
		return Sorted;
	}

	
	/**
	 * Uses a smarter greedy algorithm to solve the Knapsack problem. 
	 * @param k Empty knapsack with a maximum capacity to fill
	 * @param items a list of items each with a weight and a value
	 * @return the filled knapsack
	 */
	public Knapsack solveKnapsackGreedySmart(Knapsack k, LinkedList<Item> items){
		// TODO: Implement this
		return k;
	}
	
	//END
}



