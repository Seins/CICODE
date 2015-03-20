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

import nd.com.ci.realMonkey.RealTestSuites;
import nd.com.ci.robotium.CompileApk;
import nd.com.ci.robotium.RobotiumTestSuites;
import nd.com.server.FileUtil;

public class TestSuites {
	private String reportRootPath = PropertiesManager.reportPath;
	private String testPath = reportRootPath + "/android-test";
	private String xmlPath = testPath + "/xml";
	private String htmlPath = testPath + "/html";
	private FileUtil fileUtil = new FileUtil();
	
	public String startRun(String scriptRootPath){
		fileUtil.deleteDir(new File(reportRootPath));
		fileUtil.createDir(xmlPath);
		fileUtil.createDir(htmlPath);
		List<String> contents = new ArrayList<String>();
		File file = new File(scriptRootPath+"/AndroidManifest.xml");
		if (file.exists()) {
			new RobotiumTestSuites(this.xmlPath).startRun(scriptRootPath);
		}else {
			new RealTestSuites().startRun(scriptRootPath);
		}
		for (String testFilePath : scanXMLFile(xmlPath)) {
			contents.add(readFileByLines(testFilePath));
		}
		createTestSuitesXml(contents);
		return reportRootPath;
	}

	private String readFileByLines(String fileName) {
		System.out.println(fileName);
		StringBuilder sb = new StringBuilder();
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			InputStreamReader isr = new InputStreamReader(new FileInputStream(
					file), "UTF-8");
			reader = new BufferedReader(isr);
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				
				if (!tempString.contains("xml version")) {	
					sb.append(tempString + "\n");
					System.out.println(tempString);
				}else {			
					tempString = tempString.substring(tempString.indexOf(">")+1,tempString.length());
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
		File file = new File(xmlPath + "/TESTS-TestSuites.xml");
		try {
			if (file.exists()) {
				file.delete();
			}
			file.createNewFile();

			StringBuilder content = new StringBuilder();
			content.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
			content.append("<testsuites>\n");
			for (String string : contents) {
				content.append(string);
			}
			content.append("</testsuites>");
			OutputStreamWriter writerStream = new OutputStreamWriter(
					new FileOutputStream(file), "UTF-8");
			writerStream.write(content.toString());
			writerStream.close();
		} catch (IOException e) {
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


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TestSuites().startRun("E:/CISCRIPT/Demo");
		//CompileApk compile = new CompileApk("E:/CISCRIPT/Demo", "qa.demo.test");
		//compile.compileApk();
		//String s = "ccc.cc.ssdf";
		//System.out.println(s.lastIndexOf("."));
	}

}
