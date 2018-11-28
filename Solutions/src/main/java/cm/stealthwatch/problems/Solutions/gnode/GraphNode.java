package cm.stealthwatch.problems.Solutions.gnode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;


public class GraphNode implements GNode {

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
	 * Easily adds child nodes to a parent node without manually 
	 * intervening with the GNode array
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
	 * Prints all possible paths from a given node within a list of string points within an arrayList
	 * returns a single node if no other nodes present
	 * for returning a single node originally I returned an
	 * empty list since I thought that a path was displacement between two or more points
	 * After reading some definitions on wikipedia I see a path with one point can have an
	 * infinite distance, so I decided to print the single point
	 * @param node
	 * @return ArrayList<ArrayList<String>> 
	 */
	public static ArrayList<ArrayList<String>> printPaths(GNode node) {
		List<ArrayList<String>> paths = new ArrayList<ArrayList<String>>();
		ArrayList<String> currentPath = new ArrayList<String>();
		Deque<GNode> childQueue = new LinkedList<>();
		Deque<GNode> parentQueue = new LinkedList<>();
		Set<GNode> visited = new LinkedHashSet<>();
		Set<GNode> extracted = new LinkedHashSet<>();
		
		parentQueue.add(node);
		while(queueHasContent(parentQueue)) {
			removeParentNodesFromPath(currentPath, parentQueue, visited); 
			
			if(queueIsEmpty(parentQueue))
				continue;
			
			if(isNodeAParent(parentQueue.getLast()) && nodesHaveNotBeenExtracted(parentQueue.getLast(), extracted)) {
				childQueue.addAll(Arrays.asList(parentQueue.getLast().getChildren()));
				currentPath.add(parentQueue.getLast().getName());
				extracted.add(parentQueue.getLast());
			} else if (nodeIsSingle(parentQueue.getLast())) {
				currentPath.add(parentQueue.getLast().getName());
				paths.add(currentPath);
				parentQueue.removeLast();
				continue;
			}

			if(isNodeAParent(childQueue.getLast())) {
				parentQueue.addLast(childQueue.removeLast());
				continue;
			} else {
				while(nodeIsSingle(childQueue.getLast())) {
					currentPath.add(childQueue.getLast().getName());
					visited.add(childQueue.getLast());
					paths.add(currentPath);
					currentPath = new ArrayList<>(currentPath);
					currentPath.remove(childQueue.getLast().getName());
					childQueue.removeLast();
					
					if(queueIsEmpty(childQueue))
						break;
					
					if(isNodeAParent(childQueue.getLast())) {
						removeParentNodesFromPath(currentPath, parentQueue, visited); 
						parentQueue.addLast(childQueue.removeLast());
						break;
					} 
					
					if(nodesHaveBeenVisited(parentQueue.getLast(), visited)) {
						currentPath.remove(parentQueue.getLast().getName());
						visited.add(parentQueue.removeLast());
						break;
					}
				}
			}
			
		}
		
		return (ArrayList<ArrayList<String>>) paths;
	}

	private static void removeParentNodesFromPath(ArrayList<String> currentPath, Deque<GNode> parentQueue,
			Set<GNode> visited) {
		while(nodesHaveBeenVisited(parentQueue.getLast(), visited)) {
			visited.add(parentQueue.getLast());
			currentPath.remove(parentQueue.getLast().getName());
			parentQueue.removeLast();
			if(queueHasContent(parentQueue)) {
				continue;
			} else { 
				break;
			}
		}
	}
	
	private static boolean nodesHaveBeenVisited(GNode node, Set<GNode> visited) {
		if(nodeIsSingle(node)) {
			return false;
		} else {
			return visited.containsAll(Arrays.asList(node.getChildren()));
		}
	}
	
	private static boolean nodeIsSingle(GNode node) {
		return node.getChildren().length == 0;
	}
	
	private static boolean isNodeAParent(GNode node) {
		return node.getChildren().length > 0;
	}
	
	
	private static boolean queueHasContent(Deque<GNode> nodes) {
		if(nodes.isEmpty())
			return false;
		else
			return true;
	}
	
	private static boolean queueIsEmpty(Deque<GNode> nodes) {
		return nodes.isEmpty();
	}
	
	private static boolean nodesHaveNotBeenExtracted(GNode node, Set<GNode> extracted) {
		if(extracted.contains(node)) {
			return false;
		} else {
			return true;
		}
	}

	public static void main(String args[]) {
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
		

		System.out.println(walkGraph(gNodeA));
		System.out.println(printPaths(gNodeA));
		System.out.println(printPaths(new GraphNode("M")));

	}

}
