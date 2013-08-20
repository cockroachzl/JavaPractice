import java.util.*;

public class TestMap {
	public static void main(String[] args){
		TreeMap<String, Integer> scores = new TreeMap<String, Integer>();
		scores.put("Kathy", 42);
		scores.put("Bert", 343);
		scores.put("Skyler", 430);
		System.out.println(scores);
		System.out.println(scores.get("Bert"));
	}
}
