import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * @author Arne
 * 
 */
public class ResidualGraph extends DiGraph {

	// -- constructor --
	public ResidualGraph() {
	}
	
	/**
	 * Finds an augmenting path from start to end in the graph A path is
	 * augmenting if all it's edges have residual capacity > 0 (You can choose
	 * from several algorithms to find a path)
	 * 
	 * Do it very similar to bfs:
	 * 
	 * 
	 * @param startNodeId
	 *            the id of the start node from where we should start the search
	 * @param endNodeId
	 *            the id of the end node which we want to reach
	 * 
	 * @return the path from start to end or an empty list if there is none
	 */
	public LinkedList<Node> findAugmentingPath(int startNodeId, int endNodeId){
		// TODO: Your implementation here
		
		if(nodes.get(startNodeId)== null ||nodes.get(endNodeId) == null){
			throw new RuntimeException("Wrong IDs!");
		}
		//BFS: 
		resetState();	
		
		Queue<Node> reqQueue = new LinkedList<Node>();	
		LinkedList<Node> Path = new LinkedList<Node>();      		
		List<Node> adjList = new LinkedList<Node>();		
	
		Node temp = nodes.get(startNodeId);


		reqQueue.add(temp);
		temp.status = 2;
		

		while(!(reqQueue.isEmpty())) {	
			// fill adjacent list:
			adjList = getAdjacentNodes(reqQueue.peek());
			temp = reqQueue.peek();
			Path.add(temp);
		
			//work adjacent elements through: 
			for (Node n: adjList) {
				//if there is a path:
					if(n.status!=2 && temp.getWeight(n) > 0) {
						n.predecessor = temp;
						//add it to the queue
						reqQueue.add(n);
						// n is done:
						n.status=2;
					}	
				}
			reqQueue.remove();
		}
		
		if(!Path.contains(nodes.get(endNodeId))){
			return null;
		}
		LinkedList<Node> reList = new LinkedList<Node>();
		
		Node runner = nodes.get(endNodeId);
		
		while(runner!=nodes.get(startNodeId)){
			reList.addFirst(runner);
			runner = runner.predecessor;		
		}
		//after while first is still not in the list:
		reList.addFirst(nodes.get(startNodeId));
		
		return reList;	
	}

	/**
	 * Finds the minimal residual capacity over the given path
	 * 
	 * @return the minimal capacity
	 */
	public double findMinCapacity(LinkedList<Node> path) {
		if (path == null || path.isEmpty() || path.size() < 2) {
			return 0;
		}
		// from first to the next: 
		double min = path.getFirst().getWeight(path.get(1));
		// just like in update, go through and change min if
		// some values are smaller:
		for (int i = 0; i<path.size() - 1; i++) {
			if (path.get(i).getWeight(path.get(i+1)) < min) {
				min = path.get(i).getWeight(path.get(i+1));
			}
		}
		
		return min;
	}

	/**
	 * Update capacity on given path, to be executed on residual graph
	 */
	public void updateResidualCapacity(double minCapacity, LinkedList<Node> path) {
		// TODO: Your implementation here
		// residualCapacity = edge.capacity - edge.flow
		// to path.size() -1 cause i need to get the second node, which is i+1
		for (int i = 0; i<path.size()-1; i++) {
			// go through the whole path with two neighbor nodes and simply change value of weight
			Node first = path.get(i);
			Node second = path.get(i+1);
			Edge first2second = first.getEdgeTo(second);
			Edge second2first = second.getEdgeTo(first);
			//it is - minCapacity for residual capacity:
			first2second.weight = first2second.weight - minCapacity;
			// and +minCapacity for the way back:
			second2first.weight = second2first.weight + minCapacity;
		}
	}

}

