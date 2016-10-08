

public class LiveTraffic {
	public String source;
	public String destination;
	public int time;

	public LiveTraffic(String liveTrafficLine){
		String[] liveTrafficSplit = liveTrafficLine.split(" ");
		source = liveTrafficSplit[0];
		destination = liveTrafficSplit[1];
		time = Integer.parseInt(liveTrafficSplit[2]);
	}
	
	public String toString(){
		return "Source:"+source+", Destination:"+destination+", time:"+time;
	}
}
