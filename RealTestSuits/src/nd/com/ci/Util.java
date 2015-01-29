package nd.com.ci;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class Util {
	
	private String reportRootPath = "D:/CIServers";

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
					&& !rootDir.getAbsolutePath()
							.contains("TEST-TestSuits.xml")) {
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
		StringBuilder sb = new StringBuilder();
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				if (!tempString.contains("xml version")) {
					sb.append(tempString+"\n");
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

	private void createTestSuitsXml(List<String> contents) {
		File file = new File(reportRootPath+"/TEST-TestSuits.xml");
		
		try {
			if (file.exists()) {
				file.delete();
			}
			file.createNewFile();
			
			StringBuilder content = new StringBuilder();
			content.append("<?xml version=\"1.0\" encoding=\"gbk\"?>");
			content.append("<testsuits>\n");
			for (String string : contents) {
				content.append(string);
			}
			content.append("</testsuits>");
			Writer xmlFile = new FileWriter(file, true);
			xmlFile.write(content.toString());
			xmlFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}
	}
	
	public String StartRun(String scriptRootPath){
		List<String> contents = new ArrayList<String>();
		for (String string : scanTestFile(scriptRootPath)) {
			runTest(string);
		}
		for (String testFilePath : scanXMLFile(reportRootPath)) {		
			contents.add(readFileByLines(testFilePath));	
		}
		createTestSuitsXml(contents);
		return reportRootPath;
	}
}
