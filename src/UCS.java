

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class UCS extends Search {

	PriorityQueue<Node> open = new PriorityQueue<Node>();
//	LinkedList<Node> open = new LinkedList<Node>();
	HashMap<String,Node> closed = new HashMap<String,Node>();
	
	public Collection<Node> getQueue(){
		return open;
	}
	
	public UCS(Input input) {
		super(input);
	}
	
	@Override
	public void addLastToQueue(Node node) {
		open.add(node);
		//test(node);
	}
	
	public void test(Node oldNode){
		Node node = new Node("A", oldNode, 3);
		open.add(node);
		 node = new Node("B", oldNode, 4);
		open.add(node);
		node = new Node("C", oldNode, 10);
		open.add(node);
		node = new Node("D", oldNode, 3);
		open.add(node);
		node = new Node("E", oldNode, 4);
		open.add(node);
		node = new Node("F", oldNode, 9);
		open.add(node);
		node = new Node("G", oldNode, 6);
		open.add(node);
		node = new Node("H", oldNode, 10);
		open.add(node);
		
		while(!open.isEmpty()){
			node = open.remove();
			System.out.println(node);
		}
		
	}

	@Override
	public Node removeFirstFromQueue() {
		return open.remove();
	}

	@Override
	public void expand(Node currentNode) {
		LinkedList<Node> children = new LinkedList<Node>();
		Iterator<LiveTraffic> iterator = liveTrafficList.iterator();
		while(iterator.hasNext()){
			LiveTraffic liveTraffic = iterator.next();
			if(liveTraffic.source.equals(currentNode.intersection)){
				Node node = new Node(liveTraffic.destination, currentNode, liveTraffic.time);
				children.addLast(node);
			}
		}
		//Now children has all children of currentNode
		while(!children.isEmpty()){
			Node child = children.removeFirst();
			Node openNode = getOpenNodeWithState(child);
			Node closedNode = getClosedNodeWithState(child);
			if(openNode == null && closedNode == null){
				//This state is neither in open or closed
				open.add(child);
			}
			else if(closedNode == null){
				//This state is not in closed but in open
				if(child.pathCost < openNode.pathCost){
					//This is lesser cost. Replace in queue
					open.remove(openNode);
					open.add(child);
				}
			}
			else if(openNode == null){
				//This state is not in open but in closed
				if(child.pathCost < closedNode.pathCost){
					//Delete from closed and add in open
					closed.remove(closedNode.intersection);
					open.add(child);
				}
			}
		}
		closed.put(currentNode.intersection, currentNode);
	}

	private Node getClosedNodeWithState(Node child) {
		if(closed.containsKey(child.intersection)){
			return closed.get(child.intersection);
		}
		return null;
	}

	private Node getOpenNodeWithState(Node child) {
		Iterator<Node> iterator = open.iterator();
		while(iterator.hasNext()){
			Node openNode = iterator.next();
			if(openNode.intersection.equals(child.intersection)){
				return openNode;
			}
		}
		return null;
	}
}
