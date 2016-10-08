

import java.util.LinkedList;

public class Node implements Comparable<Node>{
	
	public String intersection;
	public LinkedList<TrafficTime> searchPath = new LinkedList<TrafficTime>();
	public int pathCost;
	public int enqueueId = 0;

	public Node(String currentState, Node previousNode, int timeFromPrevToCurrent) {
		intersection = currentState;
		TrafficTime latestTrafficTime;
		if(previousNode != null){
			latestTrafficTime = new TrafficTime(intersection, previousNode.searchPath.getLast(), timeFromPrevToCurrent);
			searchPath = (LinkedList<TrafficTime>) previousNode.searchPath.clone();
		}
		else {
			latestTrafficTime = new TrafficTime(null , intersection, 0);
		}
		pathCost = latestTrafficTime.time;
		searchPath.add(latestTrafficTime);
	}

	public String toString(){
		String text = "";
		text += "intersection:"+intersection;
		text += ", pathCost:"+pathCost;
		text += ", enqueueId:"+enqueueId;
		text += ", searchPath:"+searchPath;
		return text;
	}

	@Override
	public int compareTo(Node o) {
		if(this.pathCost == o.pathCost){
			if(this.enqueueId>o.enqueueId){
				return -1;
			}
			else{
				return 1;
			}
		}
		return this.pathCost-o.pathCost;
	}
}
