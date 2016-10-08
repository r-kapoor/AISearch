

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class BFS extends Search {
	
	LinkedList<Node> queue = new LinkedList<Node>();
	HashMap<String,Node> visited = new HashMap<String,Node>();

	public LinkedList<Node> getQueue(){
		return queue;
	}

	@Override
	public void addLastToQueue(Node node) {
		queue.addLast(node);
		
	}

	@Override
	public Node removeFirstFromQueue() {
		return queue.removeFirst();
	}
	
	public BFS(Input input) {
		super(input);
	}

	@Override
	public void expand(Node currentNode) {
		Iterator<LiveTraffic> iterator = liveTrafficList.iterator();
		while(iterator.hasNext()){
			LiveTraffic liveTraffic = iterator.next();
			if(liveTraffic.source.equals(currentNode.intersection)){
				Node node = new Node(liveTraffic.destination, currentNode, 1);
				if(!visited.containsKey(node.intersection)){
					//This node is not visited
					queue.addLast(node);
					visited.put(node.intersection, node);
				}
			}
		}
	}

}