import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.Queue;


/**
 * A data structure to store a graph consisting of a set of unlabeled nodes and
 * a set of directed, weighted edges connecting the nodes.
 * 
 * The interface provides methods to add nodes and edges. Both methods can be
 * called at all time and the data structure should not assume a limitation on
 * the number of nodes or edges.
 * 
 * Furthermore, there are methods to access a single edge or to check whether
 * two nodes are connected as well as methods to access all nodes connected with
 * a specific node and thus all outgoing edges.
 * 
 * @author Joerg Schneider, modified by MPGI2-Tutoren
 */
public class DiGraph implements Graph {

	public HashMap<Integer, Node> nodes;

	// needed for testing, where the execution of methods should not stop
	private boolean showSteps = true;

	public DiGraph() {
		nodes = new HashMap<Integer, Node>();
	}

	/**
	 * Adds an edge to the graph.
	 * 
	 * The edge is specified by a start node and a target node. If there is
	 * already an edge between these two nodes the behavior of the method is
	 * unspecified. The edge is labeled with a positive weight value.
	 * 
	 * @param startNode
	 *            the start node
	 * @param targetNode
	 *            the target node
	 * @param weight
	 *            a positive, non-zero value assigned to this edge
	 */
	@Override
	public void addEdge(Node startnode, Node endnode, int weight) {
		if (startnode != null && endnode != null) {
			
			// only add edges between nodes which are already part of this graph 
			if(nodes.values().contains(startnode) && nodes.values().contains(endnode)){
				startnode.addEdge(endnode, weight);
			}

		}
	}


	@Override
	public void addEdge(int startnode, int endnode, int weight) {
		addEdge(nodes.get(startnode), nodes.get(endnode), weight);
	}


	/**
	 * Creates a new node and adds it to the graph.
	 * 
	 * As the nodes are unlabeled this method has no parameter. An identifier is
	 * returned which can be used to access this specific node when calling the
	 * other operations.
	 * 
	 * @return the added Node.
	 */
	@Override
	public Node addNode() {
		Node newOne = null;
		
		int newId = nodes.size();
		newOne = new Node(newId);
		nodes.put(newId, newOne);
		return newOne;
	}


	/**
	 * Returns the weight of the directed edge that points from startnode to endnode.
	 * 
	 * @param startNode
	 *            the the start node as provided by addNode()
	 * @param targetNode
	 *            the target node as provided by
	 *            addNode()
	 * @return WEIGHT_NO_EDGE, if there is no directed edge between the start
	 *         node and the target node or the positive, non-zero weight stored
	 *         by addEdge().
	 */
	@Override
	public int getWeight(Node startnode, Node endnode) {
		if (startnode != null && endnode != null) {
			if(nodes.values().contains(startnode) && nodes.values().contains(endnode)){
				return startnode.getWeight(endnode);
			}
		}
		return Graph.WEIGHT_NO_EDGE;
	}

	@Override
	public int getWeight(int startnode, int endnode) {
		return getWeight(nodes.get(startnode), nodes.get(endnode));
	}

	/**
	 * Returns all nodes of the graph.
	 * 
	 * @return list containing all valid node references.
	 */
	@Override
	public List<Node> getNodes() {
		return new ArrayList<Node>(nodes.values());
	}

	/**
	 * For a given node the method returns all nodes the outgoing edges of the node directly lead to.
	 * 
	 * @return list containing all directly connected nodes.
	 */
	@Override
	public List<Node> getAdjacentNodes(Node startnode) {
		List<Node> nodes = null;
		if (startnode != null && this.nodes.values().contains(startnode)) {
			return startnode.getAdjacentNodes();
		}
		nodes = new LinkedList<Node>();
	    return nodes;
	}

	@Override
	public List<Node> getAdjacentNodes(int startnode) {
		return getAdjacentNodes(nodes.get(startnode));
	}

	/**
	 * Determines if the given nodes are directly connected, that is whether
	 * there is some directed edge that links startNode to targetNode.
	 * For all existing nodes: isConnected(n1, n2) <=> getAdjacentNodes(n1).contains(n2)
	 * 
	 * @param startNode
	 *            the start node as provided by addNode()
	 * @param targetNode
	 *            the target node as provided by
	 *            addNode()
	 * @return true iff there is a directed edge between the given start node
	 *         and target node.
	 */
	@Override
	public boolean isConnected(Node startnode, Node endnode) {
		if (startnode != null && endnode != null) {
			if(nodes.values().contains(startnode) && nodes.values().contains(endnode)){
				return startnode.hasEdgeTo(endnode);
			}
		}
		return false;
	}

	
	@Override
	public boolean isConnected(int startNodeID, int endNodeID) {
		return isConnected(nodes.get(startNodeID), nodes.get(endNodeID));
	}

	
	/**
	 *  YOUR IMPLEMENTATION SHOULD START HERE. 
	 */
	
	/**
	 * Traverses the Graph using breadth-first search
	 * @param startNode the node to start the search with
	 * @return a list containing the reachable nodes, ordered as visited during the search
	 * 
	 */
	@Override
	public List<Node> breadthFirstSearch(Node startNode){
		LinkedList<Node> nodeList = new LinkedList<Node>();
		resetState();	// all of the nodes in the graph will be white.But i don`t use it.
		List<Node> Graph = this.getNodes();
		// TODO
		// FIRSTOFALL
		if (startNode == null || !Graph.contains(startNode)) {
			return null;
		}
		// (1)
		LinkedList <Node> Queue = new LinkedList<Node>();
		nodeList.add(startNode);
		// adjList is a list of all neighbor nodes. I start with startNode.
		List<Node> adjList = startNode.getAdjacentNodes();
		// i add first children of startNode to Queue and it is not empty anymore!
		for (Node e: adjList) {
			if (adjList != null) {
				Queue.add(e);
			}
			else if (adjList == null) {
				return nodeList;
			}
		}
		// when Queue will be empty, that will mean, that all reachable elements were reached
		while (!Queue.isEmpty()) {
			// i need a copy cause other case i get Concurrent stuff exception
			// reason: i tried to change Queue, but Queue was a part of my loop-condition
			LinkedList <Node> Copy = new LinkedList<Node>();
			Copy.addAll(Queue);
				for (Node n: Copy) {
					if (!nodeList.contains(n)) {
					nodeList.add(n);
					adjList = n.getAdjacentNodes(); 
					for (Node e: adjList) {
						if (!Queue.contains(e) && !nodeList.contains(e)) {
							if (adjList != null) {
								Queue.add(e);
							}
						}
					}
					// sometime allNodes will be empty and it will stop
					Queue.remove(n);
				}	
				}
		}
		return nodeList;
	}
	
	/**
	 * Traverses the Graph using depth-first search
	 * @param startNode the node to start the search with
	 * @return a list containing the reachable nodes, ordered as visited during the search
	 */
	@Override
	public List<Node> depthFirstSearch(Node startNode){
		LinkedList<Node> nodeList = new LinkedList<Node>();
		resetState();
		List<Node> Graph = this.getNodes();
		// TODO
		//LinkedList<Node> Stack = new LinkedList<Node>(); hab ich irgendwie nicht gebraucht
		// Again: first of all:
		if (startNode == null || !Graph.contains(startNode)) {
			return null;
		}
		nodeList.add(startNode);
		List<Node> adjList = startNode.getAdjacentNodes();
		for (Node e: adjList) {
			if (adjList != null) {
				nodeList.add(e);
				recursiveFunction(e, nodeList);
			}
			else if (adjList == null) {
				return nodeList;
			}
		}
		return nodeList;
	}
	
	public void recursiveFunction (Node e, LinkedList<Node> nodeList) {
		List<Node> adjList = e.getAdjacentNodes();
		if (!adjList.isEmpty()) {
			for (Node r: adjList) {
				if (!nodeList.contains(r)) {
					nodeList.add(r);
					recursiveFunction(r, nodeList);
				}
			}
		}
	}
	/**
	 *  YOUR IMPLEMENTATION SHOULD END HERE
	 */

	@Override
	public List<Node> breadthFirstSearch(int nodeID) {
		return breadthFirstSearch(nodes.get(nodeID));
	}
	

	@Override
	public List<Node> depthFirstSearch(int nodeID) {
		return depthFirstSearch(nodes.get(nodeID));
	}
	


	/**
	 * resets all colors in visual representation of the graph to white
	 */
	@Override
	public void clearMarks() {
		for (Node nextNode : nodes.values()) {
			nextNode.status = Node.WHITE;
			for (Edge nextEdge : nextNode.getIncidentEdges()) {
				nextEdge.status = Edge.WHITE;
			}
		}
	}

	/**
	 * help function for reset the state of all nodes to white
	 */
	private void resetState() {
		for (Node n : nodes.values()) {
			n.status = Node.WHITE;
		}
	}
	
	
	/**
	 * travels randomly in the graph.
	 * used to demonstrate visualization class
	 * @param index of startnode
	 */
	@Override
	public void showGraph(Node startNode){
		
		resetState();
		if(startNode == null || !nodes.values().contains(startNode)){
			System.out.println("Error! this node does not exist!");
			return;
		}
		startNode.status = Node.GRAY;
		
		this.stopExecutionUntilSignal();
		startNode.status = Node.BLACK;
		
		List<Edge> edges = startNode.getIncidentEdges();
		Random ran = new Random();
		
		while(edges.size() != 0){
			List<Node> nodes = new ArrayList<Node>();
			//filter black neighbor node
			//white and gray neighbor nodes will add to nodes-list
			for(Edge e: edges){
				if(e.endnode.status != Node.BLACK){
					e.endnode.status = Node.GRAY;
					nodes.add(e.endnode);
				}
			}
			this.stopExecutionUntilSignal();
			if(nodes.size() == 0) return;
			
			startNode= nodes.get(ran.nextInt(nodes.size()));
			edges = startNode.getIncidentEdges();
			startNode.status = Node.BLACK;
		}
	}
	
	public void showGraph(int nodeID) {
		showGraph(nodes.get(nodeID));
	}
	
	

	/**
	 * function to find Cycles in a graph by using depth-first search
	 * 
	 * @return true if an cycle was found else false
	 */
	public boolean hasCycle() {
		// Ignore this function. You do not have to implement it
		return false;
	}

	// checks whether the given node has a white neighbor or not
	private boolean hasWhiteNeighbor(Node node) {

		for (Node neighbour : node.getAdjacentNodes()) {
			if (neighbour.status == Node.WHITE) {
				return true;
			}
		}
		return false;
	}

	// checks whether the given node has a gray neighbor or not
	private boolean hasGrayNeighbor(Node node) {

		for (Node neighbour : node.getAdjacentNodes()) {

			if (neighbour.status == Node.GRAY) {
				return true;
			}
		}

		return false;
	}


	/**
	 * function for sorting a graph by topological order. the graph has to be
	 * free of cycles
	 * 
	 * @return a list of nodes, ordered by depenendcy: ABC if C depends on B depends on A
	 */
	@Override
	public List<Node> topSort() {
		// Ignore this function. You do not have to implement it.
		return null;
		
	}

	// ---- Methods to visualize the operations of a graph algorithm -----

	/**
	 * Returns an index, representing the color of the given node.
	 * 0:black, 1:blue, 2:red, 3:green, 4:orange, 5:gray
	 * You may want to paint nodes that don't exist in gray.
	 * 
	 * @param nodeNumber the node for which the color
	 * 		is requested, as returned by addNode()
	 * 
	 * @return an index between 0 and 5, representing the color of the given node
	 */
	@Override
	public int getColorOfNode(Node node) {
		if (null == node) {
			return 5;
		} else {
			return node.status;
		}
	}
	
	@Override
	public int getColorOfNode(int nodeID){
		return getColorOfNode(nodes.get(nodeID));
	}

	/**
	 * Returns an index, representing the color of the given edge.
	 * 0:black, 1:blue, 2:red, 3:green, 4:orange, 5:gray
	 * You may want to paint edges that don't exist in gray.
	 * 
	 * @param sourceNodeNumber the index of the node where the edge starts
	 * 	, as returned by addNode()
	 * @param targetNodeNumber the index of the node where the edge ends
	 * 	, as returned by addNode()
	 * 
	 * @return an index between 0 and 5, representing the color of the given edge
	 */
	@Override
	public int getColorOfEdge(Node startNode, Node targetNode) {
		if (null == startNode || null == targetNode) {
			return 5;
		}
		LinkedList<Edge> incidentEdges = nodes.get(startNode.id)
				.getIncidentEdges();
		// search edge
		Edge searchedEdge = null;
		for (Edge nextEdge : incidentEdges) {
			if (nextEdge.endnode.id == targetNode.id) {
				searchedEdge = nextEdge;
			}
		}
		if (null == searchedEdge) {
			return 5;
		} else {
			return searchedEdge.status;
		}
	}

	// ---- Methods to stop and resume the execution of a graph algorithm -----

	// synchronization variable used to stop and resume processing
	private boolean isStopped = false;

	/**
	 * Tests if the current execution of a graph-algorithm was stopped
	 * @return true if the processing is stopped, false otherwise. 
	 */
	@Override
	synchronized public boolean isStopped() {
		return this.isStopped;
	}

	/**
	 * Stops or resumes the current executed graph algorithm
	 * @param status true to stop, false to resume execution
	 */
	@Override
	synchronized public void setStopped(boolean status) {
		this.isStopped = status;
	}

	/**
	 * Stops the execution of an algorithm until the visualization signals that
	 * he can be resumed. This feature can be used to visualize the work of the
	 * algorithms.
	 */
	private void stopExecutionUntilSignal() {
		if (showSteps) {
			System.out.print(".");
			this.setStopped(true);
			while (this.isStopped()) {
				this.sleep(100);
			}
		}
	}

	/**
	 * Stops the execution for a given time
	 * 
	 * @param milliseconds
	 *            time the execution will stop in milliseconds
	 */
	private void sleep(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			// the VM doesn't want us to sleep anymore,
			// so get back to work
		}
	}

	/**
	 * Sets the showSteps flag.
	 * @param show the flag
	 */
	@Override
	public void setShowSteps(boolean show) {
		this.showSteps = show;
	}
}

