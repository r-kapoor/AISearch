

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Astar extends Search {

	private Comparator<Node> comparator = new NodeAstarComparator();
	PriorityQueue<Node> open = new PriorityQueue<Node>(10, comparator);
//	LinkedList<Node> open = new LinkedList<Node>();
	HashMap<String,Node> closed = new HashMap<String,Node>();
	static int enqueueId = 0;
	
	public Collection<Node> getQueue(){
		return open;
	}
	
	public Astar(Input input) {
		super(input);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addLastToQueue(Node node) {
		addNodeToOpen(node);
	}

	private void addNodeToOpen(Node node) {
		node.enqueueId = enqueueId;
		enqueueId++;
		open.add(node);		
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
				addNodeToOpen(child);
			}
			else if(closedNode == null){
				//This state is not in closed but in open
				if(child.pathCost < openNode.pathCost){
					//This is lesser cost. Replace in queue
					open.remove(openNode);
					addNodeToOpen(child);
				}
			}
			else if(openNode == null){
				//This state is not in open but in closed
				if(child.pathCost < closedNode.pathCost){
					//Delete from closed and add in open
					closed.remove(closedNode.intersection);
					addNodeToOpen(child);
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
