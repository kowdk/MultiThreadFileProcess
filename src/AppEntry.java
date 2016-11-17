import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * app entry to start
 * 
 * @author xutao
 */
public class AppEntry {

	private static final String FOLDER_PATH = "G://Experiment//wang//";
	private static final int READ_TASKS = 4;

	public AppEntry() {
		this.StartApp();
	}
	
	@SuppressWarnings("static-access")
	private void StartApp() {
		ExecutorService executor = Executors.newFixedThreadPool(READ_TASKS);
		List<String> fileList = FileUtil.TraverseTheFolder(FOLDER_PATH);

		for (int i = 0; i < fileList.size(); i++) {
			executor.submit(new FileReadTask(fileList.get(i)));
		}
		executor.shutdown();

		System.out.println("All tasks submitted.");

		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for (Entry<String, Integer> entry : SingletonMap.getInstance()
				.getGlobalMap().entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
		
		System.out.println("All tasks down.");
	}

	public static void main(String[] args) {
		new AppEntry();
	}
}
