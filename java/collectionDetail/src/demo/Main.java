package demo;

import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		Map<String, String> properties = new HashMap<String, String>(3);
		properties.put("abc1", "efg");
		properties.keySet().toString();
		properties.keySet();
		properties.put("abc2", "efg");
		properties.keySet();
		properties.put("abc3", "efg");
		properties.put("abc4", "efg");
	}
}
