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
		GraphNode gNodeA = new GraphNode("A");
		GraphNode gNodeB = new GraphNode("B");
		GraphNode gNodeC = new GraphNode("C");
		GraphNode gNodeD = new GraphNode("D");
		GraphNode gNodeE = new GraphNode("E");
		GraphNode gNodeF = new GraphNode("F");
		GraphNode gNodeG = new GraphNode("G");
		GraphNode gNodeH = new GraphNode("H");
		GraphNode gNodeI = new GraphNode("I");
		GraphNode gNodeJ = new GraphNode("J");
		gNodeA.addChild(gNodeB);
		gNodeA.addChild(gNodeC);
		gNodeA.addChild(gNodeD);
		gNodeB.addChild(gNodeE);
		gNodeB.addChild(gNodeF);
		gNodeC.addChild(gNodeG);
		gNodeC.addChild(gNodeH);
		gNodeC.addChild(gNodeI);
		gNodeD.addChild(gNodeJ);
		
		List<ArrayList<String>> paths = GraphNode.printPaths(gNodeA);
		System.out.println(paths);
		assertTrue(paths.contains(Arrays.asList(new String[] {"A","D","J"})));
		assertTrue(paths.contains(Arrays.asList(new String[] {"A","C","I"})));
		assertTrue(paths.contains(Arrays.asList(new String[] {"A","C","H"})));
		assertTrue(paths.contains(Arrays.asList(new String[] {"A","C","G"})));
		assertTrue(paths.contains(Arrays.asList(new String[] {"A","B","F",})));
		assertTrue(paths.contains(Arrays.asList(new String[] {"A","B","E",})));

	}
}
