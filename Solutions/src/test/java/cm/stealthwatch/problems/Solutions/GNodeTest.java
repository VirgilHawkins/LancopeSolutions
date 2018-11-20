package cm.stealthwatch.problems.Solutions;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.logging.Logger;

import org.junit.Test;

import cm.stealthwatch.problems.Solutions.gnode.GraphNode;



public class GNodeTest {
	private static final Logger LOGGER = Logger.getLogger( GNodeTest.class.getName());

	GraphNode gNodeImpl = new GraphNode("A");
	
	@Test
	public void singleNodeTest() {
		assertEquals("A", gNodeImpl.getName());
		assertNotEquals("a", gNodeImpl.getName());
	}
	
	@Test
	public void childrenNodeTest() {
		GraphNode gNodeE = new GraphNode("E");
		GraphNode gNodeD = new GraphNode("D");
		GraphNode gNodeC = new GraphNode("C");
		GraphNode gNodeB = new GraphNode("B");
		GraphNode gNodeA = new GraphNode("A");
		

		gNodeA.addChild(gNodeD);
		gNodeD.addChild(new GraphNode("Z"));
		gNodeD.addChild(gNodeC);
		gNodeA.addChild(gNodeB);
		
		LOGGER.info(GraphNode.walkGraph(gNodeA).toString());
		
		
	}
}
