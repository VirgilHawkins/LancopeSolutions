package cm.stealthwatch.problems.Solutions;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.logging.Logger;

import org.junit.Test;

import cm.stealthwatch.problems.Solutions.gnode.GNode;
import cm.stealthwatch.problems.Solutions.gnode.GNodeImpl;



public class GNodeTest {
	private static final Logger LOGGER = Logger.getLogger( GNodeTest.class.getName());

	GNodeImpl gNodeImpl = new GNodeImpl("A", null);
	
	@Test
	public void singleNodeTest() {
		assertEquals("A", gNodeImpl.getName());
		assertNotEquals("a", gNodeImpl.getName());
	}
	
	@Test
	public void childrenNodeTest() {
		GNodeImpl gNodeE = new GNodeImpl("E", null);
		GNodeImpl gNodeD = new GNodeImpl("D", null);
		GNodeImpl gNodeC = new GNodeImpl("C", new GNode[] {gNodeE});
		GNodeImpl gNodeB = new GNodeImpl("B", new GNode[] {gNodeE});
		GNodeImpl gNodeA = new GNodeImpl("A", new GNode[] {gNodeC});
		
		LOGGER.info(GNodeImpl.walkGraph(gNodeA).toString());
		
		
	}
}
