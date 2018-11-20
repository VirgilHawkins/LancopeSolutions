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

	public void addChild(GNode gnode) {
		if (null == this.children)
			this.children = new GNode[0];
		List<GNode> nodes = Arrays.asList(this.children);
		nodes = new ArrayList<GNode>(nodes);
		nodes.add(gnode);
		GNode[] children = new GNode[nodes.size()];
		this.children = nodes.toArray(children);
	}

	@Override
	public GNode[] getChildren() {
		if (null == children || children.length == 0)
			return NO_CHILDREN;
		return children;
	}

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

	public static ArrayList<ArrayList<String>> printPaths(GNode node) {
		ArrayList<ArrayList<String>> paths = new ArrayList<ArrayList<String>>();
		ArrayList<String> currentPath = new ArrayList<String>();
		Deque<GNode> queue = new LinkedList<>();
		Set<GNode> visited = new HashSet<>();
		GNode lastHadChildren = node;
		
		queue.addAll(Arrays.asList(node.getChildren()));

		while(!queue.isEmpty()) {
			if(visited.contains(queue.getLast())) {
				queue.removeAll(Arrays.asList(lastHadChildren.getChildren()));
				queue.removeLast();

				if(!currentPath.isEmpty())
					paths.add(currentPath);
				currentPath = new ArrayList<String>();
			} else {
				if(currentPath.isEmpty()) {
					currentPath.add(node.getName());
				}
				currentPath.add(queue.peekLast().getName());
				visited.add(queue.peekLast());
				if(queue.peekLast().getChildren().length != 0) {
					lastHadChildren = queue.peekLast();
					queue.addAll(Arrays.asList(lastHadChildren.getChildren()));
				}
			}
			
			//List<GNode> childrenList = Arrays.asList(queue.peek().getChildren());
			
		}
		
		//from the starting node add to the current path list and get the children
		//go down to the first child add to the current path list and get the children 
		//go further until no more children obtained and add this to a visited list
		//add current path to the paths list
		//
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

		System.out.println(walkGraph(gNodeA));
		System.out.println(printPaths(gNodeA));

	}

}
