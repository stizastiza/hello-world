import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SearchUnitTest {
	private DiGraph g1;

	@Before
	public void setUp() throws Exception {
		// read graph from file
		try {
			g1 = GraphIO.loadGraph("tests/testgraphen/graphBFS_VS_DFS.txt");

			// If you set this to true, each step of your algorithm will be
			// shown
			g1.setShowSteps(true);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGraphVisualization() {
		// Create a graph visualization
		VisualGraph v = new VisualGraph(g1);

		// randomly move through the graph
		g1.showGraph(g1.nodes.get(0));
	}

	@Test
	public void testDepthFirstSearch() {
		fail("Not yet implemented");
	}

	@Test
	public void testBreadthFirstSearch() {
		fail("Not yet implemented");
	}

}

