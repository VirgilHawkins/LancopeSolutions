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
		GraphNode gNodeK = new GraphNode("K");
		GraphNode gNodeL = new GraphNode("L");
		GraphNode gNodeM = new GraphNode("M");
		gNodeA.addChild(gNodeB);
		gNodeA.addChild(gNodeC);
		gNodeA.addChild(gNodeD);
		gNodeB.addChild(gNodeE);
		gNodeB.addChild(gNodeF);
		gNodeB.addChild(gNodeG);
		gNodeE.addChild(gNodeH);
		gNodeE.addChild(gNodeI);
		gNodeE.addChild(gNodeJ);
		gNodeJ.addChild(gNodeK);
		gNodeK.addChild(gNodeL);
		gNodeK.addChild(gNodeM);
		
		List<ArrayList<String>> paths = GraphNode.printPaths(gNodeA);
		assertTrue(paths.contains(Arrays.asList(new String[] {"A","D"})));
		assertTrue(paths.contains(Arrays.asList(new String[] {"A","C"})));
		assertTrue(paths.contains(Arrays.asList(new String[] {"A","B","F",})));
	}
}
