package cm.stealthwatch.problems.Solutions.gnode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class GNodeImpl implements GNode{

	private final GNode[] NO_CHILDREN = {};
	String name;
	GNode[] children;
	static Set<String> graphNodeList = new HashSet<String>();


	public GNodeImpl(String name, GNode... children) {
		this.name = name;
		this.children = children;
	}
	@Override
	public String getName() {
		return name;
	}

	@Override
	public GNode[] getChildren() {
		if(null == children || children.length == 0)
			return NO_CHILDREN;
		return children;
	}
	
	public static ArrayList<String> walkGraph(GNode node) {
		graphNodeList.add(node.getName());

		if(node.getChildren().length == 0)
			return new ArrayList<String>(graphNodeList);
		
		List<GNode> nodes = Arrays.asList(node.getChildren());
		for(GNode childNode : nodes)
			walkGraph(childNode);

		//get the first node
		//check to see if it has any children
		////if no children end the loop
		////else check each child
		return new ArrayList<String>(graphNodeList);
	}
	
	

}
