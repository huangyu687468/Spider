import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

public class FileReader {
	public static boolean writeIntoFile(String content, String filePath, boolean isAppend)
	{
		boolean success=true;
		int index = filePath.lastIndexOf("/");
		String dir = filePath.substring(0,index);
		File fileDir = new File(dir);
		fileDir.mkdirs();
		File file = null;
		try
		{
			file = new File(filePath);
			file.createNewFile();
		} catch (IOException e) {
			success=false;
			e.printStackTrace();
			// TODO: handle exception
		}
		//写入文件
		FileWriter fileWriter = null;
		try
		{
			fileWriter = new FileWriter(file ,isAppend);
			fileWriter.write(content);
			fileWriter.flush();
		} catch (IOException e) {
			success = false;
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			try 
			{
				if(fileWriter != null)
					fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		return success;
	}
}
