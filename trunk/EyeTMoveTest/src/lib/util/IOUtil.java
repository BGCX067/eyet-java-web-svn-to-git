package lib.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class IOUtil {
	
	/**
	 * 将模板的格式赋值给新建的Excel
	 *
	 */
	public static void copyFile(String oldPath, String newPath) throws IOException{
		FileInputStream in = new FileInputStream(oldPath);
		File newfile = new File(newPath);
		if (newfile.exists()) newfile.delete();
		FileOutputStream out = new FileOutputStream(new File(newPath));
		byte[] buffer =new byte[512];
		while (in.read(buffer) != -1)
			out.write(buffer);
		in.close();
		out.close();	
	}
}