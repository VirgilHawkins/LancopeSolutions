package cm.stealthwatch.problems.Solutions.gnode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.logging.Logger;


public class GraphNode implements GNode {
	private static final Logger LOGGER = Logger.getLogger(GraphNode.class.getName());

	private final GNode[] NO_CHILDREN = {};
	String name;
	GNode[] children;

	public GraphNode(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	/**
	 * Easily adds child nodes to a parent node without manually intervening the the GNode array
	 * The code was much cleaner this way
	 * @param gnode
	 */
	public void addChild(GNode gnode) {
		if (null == this.children)
			this.children = new GNode[0];
		List<GNode> nodes = Arrays.asList(this.children);
		nodes = new ArrayList<GNode>(nodes);
		nodes.add(gnode);
		GNode[] children = new GNode[nodes.size()];
		this.children = nodes.toArray(children);
	}

	/**
	 * Implemented getChildren to retrieve child nodes 
	 * and returns an empty array if no children are present.
	 */
	@Override
	public GNode[] getChildren() {
		if (null == children || children.length == 0)
			return NO_CHILDREN;
		return children;
	}

	/**
	 * Returns all the nodes in the graph without duplicates
	 * Breadth first search to traverse the whole graph entirely
	 * @param node
	 * @return
	 */
	public static ArrayList<String> walkGraph(GNode node) {
		Queue<GNode> queue = new LinkedList<>();
		Set<String> nodes = new LinkedHashSet<>();

		queue.add(node);
		while (!queue.isEmpty()) {
			nodes.add(queue.peek().getName());
			queue.addAll(Arrays.asList(queue.remove().getChildren()));
		}

		return new ArrayList<String>(nodes);
	}

	/**
	 * Prints all possible paths from a given node
	 * returns a single node if no other nodes present
	 * @param node
	 * @return
	 */
	public static ArrayList<ArrayList<String>> printPaths(GNode node) {
		List<ArrayList<String>> paths = new ArrayList<ArrayList<String>>();
		ArrayList<String> currentPath = new ArrayList<String>();
		Deque<GNode> queue = new LinkedList<>();
		Set<GNode> visited = new HashSet<>();
		GNode lastHadChildren = node;
		
		//for returning a single node
		//originally returned an empty list for single points since
		//their were no paths
		/*
		 * I was thinking in terms of a network since they can have a single point...
		 */
		if(node.getChildren().length == 0) {
			currentPath.add(node.getName());
			paths.add(currentPath);
		} else {
			queue.addAll(Arrays.asList(node.getChildren()));
			currentPath.add(node.getName());
		}
		
		while(!queue.isEmpty()) {
			if(visited.contains(queue.getLast())) {
				queue.removeLast();
				currentPath = new ArrayList<String>();
				currentPath.add(node.getName());
			} else {

				
				if(queue.peekLast().getChildren().length > 0) {
					lastHadChildren = queue.peekLast();
					currentPath.add(lastHadChildren.getName());	
					queue.addAll(Arrays.asList(lastHadChildren.getChildren()));
					continue;
				} else {
					if(!currentPath.contains(lastHadChildren.getName()))
						currentPath.add(lastHadChildren.getName());
					currentPath.add(queue.peekLast().getName());
					visited.add(queue.peekLast());
					paths.add(currentPath);
				}
				
				if(visited.containsAll(Arrays.asList(lastHadChildren.getChildren()))) {
					queue.removeAll(Arrays.asList(lastHadChildren.getChildren()));
					visited.add(lastHadChildren);
				}

			}
			
		}
		
		return (ArrayList<ArrayList<String>>) paths;
	}

	public static void main(String args[]) {
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

		
		LOGGER.info("Walking a whole graph: "+walkGraph(gNodeA));
		LOGGER.info("Walking a partial graph: "+walkGraph(gNodeB));
		LOGGER.info("Single node: "+walkGraph(new GraphNode("Z")));
		LOGGER.info("Paths of a whole graph: " + printPaths(gNodeA));
		LOGGER.info("Paths of a part graph: " + printPaths(gNodeC));
		LOGGER.info("Testing of a single node: "+printPaths(new GraphNode("Z")));

	}

}
