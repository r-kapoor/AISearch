

public class SundayTraffic {

	public String source;
	public int time;
	
	public SundayTraffic(String sundayTrafficLine){
		String[] sundayTrafficSplit = sundayTrafficLine.split(" ");
		source = sundayTrafficSplit[0];
		time = Integer.parseInt(sundayTrafficSplit[1]);
	}
}
