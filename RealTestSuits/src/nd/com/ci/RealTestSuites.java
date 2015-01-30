package nd.com.ci;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import nd.com.server.FileUtil;

public class RealTestSuites {

	private String reportRootPath = "D:/report";
	private String xmlPath = "D:/report/xml";
	private String htmlPath = "D:/report/html";

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
			if (rootDir.getAbsolutePath().contains(".py")) {
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
	
	private List<String> getFileNames(List<String> fileList){
		List<String> fileName = new ArrayList<String>();
		for (String filepath : fileList) {
			String[] info = filepath.split("\\\\");	
			fileName.add(info[info.length-1].split("\\.")[0]);
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

	private List<String> scanXMLFile(String path) {
		List<String> fileList = new ArrayList<String>();
		File rootDir = new File(path);
		if (!rootDir.isDirectory()) {
			if (rootDir.getAbsolutePath().contains(".xml")
					&& !rootDir.getAbsolutePath().contains(
							"TEST-TestSuites.xml")) {
				fileList.add(rootDir.getAbsolutePath());
			}
		} else {
			String[] files = rootDir.list();
			for (int i = 0; i < files.length; i++) {
				path = rootDir.getAbsolutePath() + "\\" + files[i];
				fileList.addAll(scanXMLFile(path));
			}
		}
		return fileList;
	}

	private String readFileByLines(String fileName) {
		System.out.println(fileName);
		StringBuilder sb = new StringBuilder();
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
			reader = new BufferedReader(isr);
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				if (!tempString.contains("xml version")) {
					sb.append(tempString + "\n");
					System.out.println(tempString);
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return sb.toString();
	}

	private void createTestSuitesXml(List<String> contents) {
		File file = new File(xmlPath + "/TEST-TestSuites.xml");
		try {
			if (file.exists()) {
				file.delete();
			}
			file.createNewFile();

			StringBuilder content = new StringBuilder();
			content.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			content.append("<testsuites>\n");
			for (String string : contents) {
				content.append(string);
			}
			content.append("</testsuites>");
			OutputStreamWriter writerStream = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
			writerStream.write(content.toString());
			writerStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void collectHtml(List<String> fileNameList){
		String name = "";
		for (String string : fileNameList) {
			name += string+" ";
		}
		try {
			String cmd = String.format("python %s/src/ToHtml.py D:/report %s", System.getProperty("user.dir"), name);
			Process p = Runtime.getRuntime().exec(cmd);
			p.waitFor();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String StartRun(String scriptRootPath) {
		FileUtil.createDir(xmlPath);
		FileUtil.createDir(htmlPath);
		List<String> contents = new ArrayList<String>();
		List<String> fileList = scanTestFile(scriptRootPath);
		for (String string : fileList) {
			runTest(string);
		}
		for (String testFilePath : scanXMLFile(xmlPath)) {
			contents.add(readFileByLines(testFilePath));
		}
		createTestSuitesXml(contents);
		collectHtml(getFileNames(fileList));
		return reportRootPath;
	}
}
