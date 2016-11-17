import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * file util
 * @author xutao
 */
public class FileUtil {
	/** 
	 * 去除字符串中的非中文字符
	 * @param src source string contains illegal char
	 * @return split string
	 * */
	public static String splitIllegalCharacter(String src) {
		 final String reg = "[^\u4e00-\u9fa5]";
	   	 Pattern pattern = Pattern.compile(reg);  
	   	 Matcher mat = pattern.matcher(src); 
	   	 String res = mat.replaceAll("");
	   	 return res;
	}
	
	/** 
	 * 递归获取指定文件夹下的文件列表
	 * @param src source string contains illegal char
	 * @return split string
	 * */
	public static List<String> TraverseTheFolder(String folderPath) {
		List<String> res = new ArrayList<String>();
		File rootDir = new File(folderPath);
		File[] files = rootDir.listFiles();

		if (files == null) {
			System.out.println("No such dir");
			return null;
		}

		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				TraverseTheFolder(files[i].getAbsolutePath());
			} else {
				String filePath = files[i].getAbsolutePath();
				res.add(filePath);
			}
		}
		
		return res;
	}
	
	public static void main(String[] args) {
		String str = "蝌?;_ @_    ";
		System.out.println(splitIllegalCharacter(str));
	}
}
