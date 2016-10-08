

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class DFS extends Search {

	LinkedList<Node> queue = new LinkedList<Node>();
	HashMap<String,Node> visited = new HashMap<String,Node>();
	
	public LinkedList<Node> getQueue(){
		return queue;
	}
	
	public DFS(Input input) {
		super(input);
	}
	
	@Override
	public void addLastToQueue(Node node) {
		queue.addLast(node);
		
	}

	@Override
	public Node removeFirstFromQueue() {
		return queue.removeFirst();
	}

	@Override
	public void expand(Node currentNode) {
		//TODO: Check if node is already covered. Note when to check this
		ListIterator<LiveTraffic> reverseIterator = liveTrafficList.listIterator(liveTrafficList.size());
		while(reverseIterator.hasPrevious()){
			LiveTraffic liveTraffic = reverseIterator.previous();
			if(liveTraffic.source.equals(currentNode.intersection)){
				Node node = new Node(liveTraffic.destination, currentNode, 1);
				if(!visited.containsKey(node.intersection)){
					//This node is not visited
					queue.addFirst(node);
					visited.put(node.intersection, node);
				}
			}
		}
	}

}
