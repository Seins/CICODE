package nd.com.server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileUtil {
	public void createNew(String dirPath) {
		File dir = new File(dirPath);
		if (!dir.exists()) {
			dir.mkdir();
		}
	}

	public File createFile(String fileName, String Suffix) {
		File file = new File(fileName + Suffix);
		try {
			int repeatNum = 0;
			while (file.exists()) {
				repeatNum++;
				file = new File(fileName + repeatNum + Suffix);
			}
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file;
	}

	public void createDir(String dirPath) {
		String[] info = null;
		if (dirPath.contains("/")) {
			info = dirPath.split("/");
		} else {
			info = dirPath.split("\\");
		}
		String path = info[0];
		for (int i = 1; i < info.length; i++) {
			path += "/" + info[i];
			if (i == 1) {
				mkDir(path, false);
			} else {
				mkDir(path, true);
			}
		}
	}

	private void mkDir(String dirPath, boolean flag) {
		File dir = new File(dirPath);
		if (dir.exists()) {
			if (flag) {
				deleteDir(dir);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dir.mkdir();
			}
		} else {
			dir.mkdir();
		}
	}

	public boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		// 目录此时为空，可以删除
		boolean delete = dir.delete();
		// try {
		// Thread.sleep(500);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		return delete;
	}

	public void writeFile(File file, String content) {
		OutputStreamWriter writerStream;
		try {
			file.createNewFile();
			writerStream = new OutputStreamWriter(new FileOutputStream(file),
					"UTF-8");
			writerStream.write(content);
			writerStream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteFile(String filePath){
		File file = new File(filePath);
		deleteFile(file);
	}
	
	public void deleteFile(File file){
		if (file.exists()) {
			file.delete();
		}
	}
}
