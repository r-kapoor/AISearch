

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public abstract class Search {
	
	public String startState;
	public String goalState;
	public ArrayList<LiveTraffic> liveTrafficList = new ArrayList<LiveTraffic>();
	public Node successNode;
	
	public abstract Collection<Node> getQueue();
	public abstract void addLastToQueue(Node initialNode);
	public abstract Node removeFirstFromQueue();
	
	public Search(Input input){
		startState = input.startState;
		goalState = input.goalState;
		liveTrafficList = input.liveTrafficList;
		successNode = null;
	}
	
	public boolean runAlgorithm() {
		Node initialNode = new Node(startState, null, 0);
		addLastToQueue(initialNode);
		while(true){
			if(getQueue().isEmpty()){
				return false;
			}
			Node currentNode = removeFirstFromQueue();
			if(isGoal(currentNode)){
				successNode = currentNode;
				return true;
			}
			else {
				expand(currentNode);
			}
		}
	}

	public void printCompletePath() throws IOException{
		Iterator<TrafficTime> iterator = successNode.searchPath.iterator();
		PrintWriter pw = new PrintWriter(new FileWriter("output.txt"));
		while(iterator.hasNext()){
			TrafficTime trafficTime = iterator.next();
			System.out.println(trafficTime.destination+" "+trafficTime.time);
			pw.println(trafficTime.destination+" "+trafficTime.time);
		}
		pw.close();
	}
	
	public boolean isGoal(Node node){
		if(goalState.equals(node.intersection)){
			return true;
		}
		return false;
	}
	
	public abstract void expand(Node currentNode);
}
