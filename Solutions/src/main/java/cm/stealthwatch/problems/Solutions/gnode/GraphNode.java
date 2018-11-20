package cm.stealthwatch.problems.Solutions.gnode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class GraphNode implements GNode{

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
		if(null == this.children)
			this.children = new GNode[0];
		List<GNode> nodes = Arrays.asList(this.children);
		nodes = new ArrayList<GNode>(nodes);
		nodes.add(gnode);
		GNode[] children = new GNode[nodes.size()];
		this.children = nodes.toArray(children);
	}

	@Override
	public GNode[] getChildren() {
		if(null == children || children.length == 0)
			return NO_CHILDREN;
		return children;
	}
	
	public static ArrayList<String> walkGraph(GNode node) {
		Queue<GNode> queue = new LinkedList<>();
		Set<String> nodes = new LinkedHashSet<>();
		
		queue.add(node);
		while(!queue.isEmpty()) {
			nodes.add(queue.peek().getName());
			queue.addAll(Arrays.asList(queue.remove().getChildren()));
		}
		
		return new ArrayList<String>(nodes);
	}
	
	public static ArrayList<ArrayList<String>> printPaths(GNode node) {
		List<ArrayList<String>> paths = new ArrayList<ArrayList<String>>();
		List<String> currentPath = new ArrayList<String>();
		Queue<GNode> queue = new LinkedList<>();
		Set<GNode> visited = new HashSet<>();
		
		queue.add(node);
		while(!queue.isEmpty()) {
			currentPath.add(queue.peek().getName());
			List<GNode> childrenList = Arrays.asList(queue.peek().getChildren());
			for(GNode gNode:childrenList) {

			}
		}
		
		//from the starting node add to the current path list and get the children
		//go down to the first child add to the current path list and get the children 
		//go further until no more children obtained and add this to a visited list
		//add current path to the paths list
		//
		return (ArrayList<ArrayList<String>>) paths;
	}

}
