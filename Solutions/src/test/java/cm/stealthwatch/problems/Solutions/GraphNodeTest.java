package cm.stealthwatch.problems.Solutions;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.junit.Test;

import cm.stealthwatch.problems.Solutions.gnode.GraphNode;



public class GraphNodeTest {
	private static final Logger LOGGER = Logger.getLogger(GraphNodeTest.class.getName());

	GraphNode gNodeImpl = new GraphNode("A");
	
	@Test
	public void singleNodeTest() {
		assertEquals("A", gNodeImpl.getName());
		assertNotEquals("a", gNodeImpl.getName());
	}
	
	@Test
	public void walkGraphTest() {
		GraphNode gNodeE = new GraphNode("E");
		GraphNode gNodeD = new GraphNode("D");
		GraphNode gNodeC = new GraphNode("C");
		GraphNode gNodeB = new GraphNode("B");
		GraphNode gNodeA = new GraphNode("A");
		
		gNodeD.addChild(gNodeE);
		gNodeA.addChild(gNodeD);
		gNodeD.addChild(new GraphNode("Z"));
		gNodeD.addChild(gNodeC);
		gNodeA.addChild(gNodeB);
		
		List<String> list = GraphNode.walkGraph(gNodeA);
		LOGGER.info(list.toString());	
		
		assertTrue(list.containsAll(Arrays.asList(new String[]{"A","B","C","D","E","Z"})));
	}
	
	@Test
	public void printPathTest() {
		GraphNode gNodeD = new GraphNode("D");
		GraphNode gNodeC = new GraphNode("C");
		GraphNode gNodeB = new GraphNode("B");
		GraphNode gNodeA = new GraphNode("A");

		gNodeA.addChild(gNodeB);
		gNodeB.addChild(new GraphNode("E"));
		gNodeB.addChild(new GraphNode("F"));
		gNodeC.addChild(new GraphNode("G"));
		gNodeC.addChild(new GraphNode("H"));
		gNodeC.addChild(new GraphNode("I"));
		gNodeD.addChild(new GraphNode("J"));
		gNodeA.addChild(gNodeC);
		gNodeA.addChild(gNodeD);
		
		List<ArrayList<String>> paths = GraphNode.printPaths(gNodeA);
		assertTrue(paths.contains(Arrays.asList(new String[] {"A","D","J"})));
		assertTrue(paths.contains(Arrays.asList(new String[] {"A","C","I"})));
		assertTrue(paths.contains(Arrays.asList(new String[] {"A","B","E"})));

	}
	
}
