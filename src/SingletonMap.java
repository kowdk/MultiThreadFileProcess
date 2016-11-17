import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * global instance for multiThreading, singleton, double check.
 * @author xutao
 */
public class SingletonMap {
	
	private static SingletonMap instance = null;
	
	private static Map<String, Integer> globalMap = new ConcurrentHashMap<String, Integer>();
	
	private SingletonMap() {

	}

	public static SingletonMap getInstance() {
		if (instance == null) {
			synchronized (SingletonMap.class) {
				if (instance == null) {
					instance = new SingletonMap();
					globalMap = new ConcurrentHashMap<String, Integer>();
				}
			}
		}
		return instance;
	}

	public static Map<String, Integer> getGlobalMap() {
		return globalMap;
	}
}
