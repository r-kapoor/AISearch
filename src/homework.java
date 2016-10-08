

import java.io.IOException;

public class homework {

	public static void main(String[] args) throws IOException {
		Input input = new Input();//Read the input
		Search search;
		if(input.algorithm.equals("BFS")){
			search = new BFS(input);
		}
		else if(input.algorithm.equals("DFS")){
			search = new DFS(input);
		}
		else if(input.algorithm.equals("UCS")){
			search = new UCS(input);
		}
		else {
			search = new Astar(input);
		}
		boolean result = search.runAlgorithm();
		if(result){
			System.out.println(search.successNode);
			search.printCompletePath();
		}
	}

}
