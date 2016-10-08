

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Input {
	
	public String algorithm;
	public String startState;
	public String goalState;
	public ArrayList<LiveTraffic> liveTrafficList = new ArrayList<LiveTraffic>();
	public static HashMap<String, Integer> sundayTrafficMap = new HashMap<String, Integer>();
	
	public Input() throws IOException{
		File file = new File("inputUCS3.txt");
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line;
		int numberOfLiveTraffic = 0, numberOfSundayTraffic = 0;
		algorithm = bufferedReader.readLine();//1st line
		startState = bufferedReader.readLine();//2nd line
		goalState = bufferedReader.readLine();//3rd line
		numberOfLiveTraffic = Integer.parseInt(bufferedReader.readLine());
		for(int i=0;i<numberOfLiveTraffic;i++){
			line = bufferedReader.readLine();
			LiveTraffic liveTraffic = new LiveTraffic(line);
			liveTrafficList.add(liveTraffic);
		}
		String sundayTrafficLine = bufferedReader.readLine();
		if(sundayTrafficLine != null){
			numberOfSundayTraffic = Integer.parseInt(sundayTrafficLine);
			for(int i=0;i<numberOfSundayTraffic;i++){
				line = bufferedReader.readLine();
				String[] lineSplit = line.split(" ");
				sundayTrafficMap.put(lineSplit[0], Integer.parseInt(lineSplit[1]));
			}
		}
		fileReader.close();
	}
}
