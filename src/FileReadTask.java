import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

/**
 * file read task for mutiThreading
 * @author xutao
 * */
public class FileReadTask implements Runnable {
	private String filePath = "";

	public FileReadTask(String filePath) {
		this.filePath = filePath;
	}

	@SuppressWarnings("static-access")
	public void readFile(String filePath) throws InterruptedException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filePath));
			String str = "";
			while ((str = br.readLine()) != null) {
				String legelString = FileUtil.splitIllegalCharacter(str);
				Map<String, Integer> map = SingletonMap.getInstance()
						.getGlobalMap();
				if (map.containsKey(legelString)) {
					map.put(legelString, map.get(legelString) + 1);
				} else {
					map.put(legelString, 1);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			readFile(filePath);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
