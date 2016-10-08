

public class TrafficTime {

	public String source;
	public String destination;
	public int time;
	
	public TrafficTime(String source, String destination, int time){
		this.source = source;
		this.destination = destination;
		this.time = time;
	}
	
	public TrafficTime(String currentIntersection, TrafficTime lastTrafficTime, int timeToThisIntersection){
		source = lastTrafficTime.destination;
		destination = currentIntersection;
		time = lastTrafficTime.time + timeToThisIntersection;
	}
	
	public String toString(){
		return "source:"+source+", destination:"+destination+", time:"+time;
	}
	
}
