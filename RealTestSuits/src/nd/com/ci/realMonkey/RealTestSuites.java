package nd.com.ci.realMonkey;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RealTestSuites {

	/**
	 * 扫描所以要执行的文件
	 * 
	 * @param rootPath
	 * @return
	 */
	private List<String> scanTestFile(String rootPath) {
		List<String> fileList = new ArrayList<String>();
		File rootDir = new File(rootPath);
		if (!rootDir.isDirectory()) {
			String filePath = rootDir.getAbsolutePath();
			if (filePath.substring(filePath.lastIndexOf(".") + 1).equals("py")) {
				fileList.add(rootDir.getAbsolutePath());
			}
		} else {
			String[] files = rootDir.list();
			for (int i = 0; i < files.length; i++) {
				rootPath = rootDir.getAbsolutePath() + "\\" + files[i];
				fileList.addAll(scanTestFile(rootPath));
			}
		}
		return fileList;
	}

	private List<String> getFileNames(List<String> fileList) {
		List<String> fileName = new ArrayList<String>();
		for (String filepath : fileList) {
			String[] info = filepath.split("\\\\");
			fileName.add(info[info.length - 1].split("\\.")[0]);
		}
		return fileName;
	}

	private void runTest(String testFilePath) {
		try {
			String cmd = "python " + testFilePath;
			System.out.println(cmd);
			Process p = Runtime.getRuntime().exec(cmd);
			p.waitFor();
			System.out.println("end");
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void collectHtml(List<String> fileNameList) {
		String name = "";
		for (String string : fileNameList) {
			name += string + " ";
		}
		try {
			String cmd = String.format(
					"python %s/src/ToHtml.py D:/reports/android-test %s",
					System.getProperty("user.dir"), name);
			System.out.println(cmd);
			Process p = Runtime.getRuntime().exec(cmd);
			p.waitFor();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void startRun(String scriptRootPath) {
		List<String> fileList = scanTestFile(scriptRootPath);
		for (String string : fileList) {
			runTest(string);
		}
		collectHtml(getFileNames(fileList));
	}
}
