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
		List<String> continuePath = new ArrayList<>();
		Deque<GNode> childQueue = new LinkedList<>();
		Deque<GNode> parentQueue = new LinkedList<>();
		Set<GNode> visited = new LinkedHashSet<>();
		Set<GNode> extracted = new LinkedHashSet<>();
		
		
		//for returning a single node
		//originally returned an empty list for single points since
		//their were no paths
		/*
		 * I was thinking in terms of a network since they can have a single point...
		 */
		parentQueue.add(node);
		while(!parentQueue.isEmpty()) {
			if(nodesAreVisited(parentQueue.getLast(), visited)) {
				visited.add(parentQueue.getLast());
				currentPath.remove(parentQueue.getLast().getName());
				parentQueue.removeLast();
				continue;
			} 
			
			if(parentQueue.getLast().getChildren().length > 0 && !extracted.contains(parentQueue.getLast())) {
				childQueue.addAll(Arrays.asList(parentQueue.getLast().getChildren()));
				currentPath.add(parentQueue.getLast().getName());
			} else if (parentQueue.getLast().getChildren().length == 0){
				currentPath.add(parentQueue.getLast().getName());
				paths.add(currentPath);
				parentQueue.removeLast();
				continue;
			}
			
			if(nodesAreVisited(parentQueue.getLast(), visited)) {
				
			}
			
			if(childQueue.getLast().getChildren().length > 0) {
				parentQueue.addLast(childQueue.removeLast());
				continue;
			} else {
				while(childQueue.getLast().getChildren().length == 0) {
					currentPath.add(childQueue.getLast().getName());
					visited.add(childQueue.getLast());
					paths.add(currentPath);
					currentPath = new ArrayList<>(currentPath);
					currentPath.remove(childQueue.getLast().getName());
					childQueue.removeLast();
					if(childQueue.getLast().getChildren().length > 0) {
						parentQueue.addLast(childQueue.removeLast());
						break;
					}
					if(visited.containsAll(Arrays.asList(parentQueue.getLast().getChildren()))) {
						currentPath.remove(parentQueue.getLast().getName());
						visited.add(parentQueue.removeLast());
						break;
					}
				}
			}
			
		}
		
		return (ArrayList<ArrayList<String>>) paths;
	}
	
	private static boolean nodesAreVisited(GNode node, Set<GNode> visited) {
		return visited.containsAll(Arrays.asList(node.getChildren()));
	}

	public static void main(String args[]) {
		GraphNode A = new GraphNode("A");
		GraphNode B = new GraphNode("B");
		GraphNode C = new GraphNode("C");
		GraphNode D = new GraphNode("D");
		GraphNode E = new GraphNode("E");
		GraphNode F = new GraphNode("F");
		GraphNode G = new GraphNode("G");
		GraphNode H = new GraphNode("H");
		GraphNode I = new GraphNode("I");
		GraphNode J = new GraphNode("J");
		GraphNode K = new GraphNode("K");
		GraphNode L = new GraphNode("L");
		GraphNode M = new GraphNode("M");
		A.addChild(B);
		A.addChild(C);
		A.addChild(D);
		B.addChild(E);
		B.addChild(F);
		B.addChild(G);
		E.addChild(H);
		E.addChild(I);
		E.addChild(J);
		J.addChild(K);
		K.addChild(L);
		K.addChild(M);
		

		System.out.println(printPaths(A));
		
//		LOGGER.info("Walking a whole graph: "+walkGraph(gNodeA));
//		LOGGER.info("Walking a partial graph: "+walkGraph(gNodeB));
//		LOGGER.info("Single node: "+walkGraph(new GraphNode("Z")));

//		LOGGER.info("Paths of a part graph: " + printPaths(gNodeC));
//		LOGGER.info("Testing of a single node: "+printPaths(new GraphNode("Z")));

	}

}
