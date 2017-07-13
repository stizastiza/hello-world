import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author Uwe + Damien + Arne
 * 
 */
public class Network extends DiGraph {

	ResidualGraph residualGraph;
	
	// -- constructor --
	public Network() {
	}

	/**
	 * Searches for sources in the graph
	 * 
	 * @return All sources found in the graph
	 */
	public Node findSource() {
		LinkedList<Node> sources = new LinkedList<Node>();
		boolean isSource = true;
		// source <-> no incoming edges
		for (Node n : nodes.values()) {
			isSource = true;
			for (Node m : nodes.values()) {
				if (!m.equals(n) && isConnected(m, n)) {
					isSource = false;
					break;
				}
			}
			if (isSource)
				sources.add(n);
		}
		// error handling
		if (sources.size() == 0)
			System.out.println("Found no source in network");
		else if(sources.size() > 1)
			System.out.println("Found more than one source in network");
		
		return sources.getFirst();
	}

	/**
	 * Searches the graph for sinks.
	 * 
	 * @return All sinks found in the graph
	 */
	public Node findSink() {
		LinkedList<Node> sinks = new LinkedList<Node>();
		// sink <-> no outgoing edges
		for (Node n : nodes.values()) {
			if (n.getOutgoingEdges().isEmpty())
				sinks.add(n);
		}
		// error handling
		if (sinks.size() == 0)
			System.out.println("Found no sink in network");
		else if(sinks.size() > 1)
			System.out.println("Found more than one sink in network");
		return sinks.getFirst();
	}

	/**
	 * Computes the maximum flow over the network with the Ford-Fulkerson
	 * Algorithm
	 * 
	 * @returns Value of maximal flow
	 */
	public double fordFulkerson() {

		// These methods find the source and sink in the network
		Node source = findSource();
		Node sink = findSink();

		// You can use this method to create a residual network
		residualGraph = initializeResidualGraph();
		
		double max = 0;
		LinkedList<Node> tempPath = residualGraph.findAugmentingPath(source.getID(), sink.getID());
		double min = residualGraph.findMinCapacity(tempPath);
		max = max + min;
		while (min > 0) {
			residualGraph.updateResidualCapacity(min, tempPath);
			tempPath = residualGraph.findAugmentingPath(source.getID(), sink.getID()); 
			min = residualGraph.findMinCapacity(tempPath);
			max = max + min;
		}

		// TODO: Your implementation here
		
		return max;
	}


	/**
	 * Builds the residual graph to a flow graph
	 * 
	 * @return the residual graph to this flow graph
	 */
	public ResidualGraph initializeResidualGraph() {

		ResidualGraph residualGraph = new ResidualGraph();

		// adding nodes
		for (int i = 0; i < nodes.values().size(); i++)
			residualGraph.addNode();
		// adding edges
		for (Node n : nodes.values()) {
			for (Edge e : n.getOutgoingEdges()) {
				// Add forward edges with same capacity
				residualGraph.addEdge(n.id, e.endNode.id, e.weight);
				// Add backwards edge
				residualGraph.addEdge(e.endNode.id, n.id, 0);
			}
		}

		return residualGraph;
	}

}

