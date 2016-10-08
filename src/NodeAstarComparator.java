

import java.util.Comparator;

public class NodeAstarComparator implements Comparator<Node> {

	@Override
	public int compare(Node node1, Node node2) {
		int param1 = getParam(node1);
		int param2 = getParam(node2);
		if(Integer.compare(param1, param2) != 0){
			return Integer.compare(param1, param2);
		}
		return Long.compare(node1.enqueueId, node2.enqueueId);
	}

	private int getParam(Node node) {
		return node.pathCost + Input.sundayTrafficMap.get(node.intersection);
	}

}
