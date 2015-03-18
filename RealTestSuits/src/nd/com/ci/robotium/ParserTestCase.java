package nd.com.ci.robotium;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class ParserTestCase {
	private ArrayList<TestSuite> list = new ArrayList<TestSuite>();

	public ArrayList<TestSuite> findAllTestClass(File root){
		TestSuite testSuites = null;
		File[] files = root.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				findAllTestClass(files[i]);
			} else if (files[i].getName().endsWith("Test.java")) {
				String className = files[i].getAbsolutePath();
				className = className.substring(className.indexOf("src") + 4);
				className = className.replaceAll("\\\\", "\\.");
				className = className.substring(0,
						className.lastIndexOf(".java"));
				testSuites = findTestCaseInClass(files[i]);
				testSuites.setClassName(className);
				testSuites.setPackageName(className.substring(0, className.lastIndexOf(".")));
				list.add(testSuites);
			}
		}
		return list;
	}

	private TestSuite findTestCaseInClass(File file){
		FileReader fr = null;
		BufferedReader br = null;
		String line = "";
		TestSuite testSuites = new TestSuite();
		ArrayList<String> strList = new ArrayList<String>();
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);

			line = br.readLine();

			while (line != null) {
				if (isAnnotation(line)) {
					line = br.readLine();
					continue;
				}

				if (line.contains("void test")) {
					strList.add(getCaseName(line));
				}
				line = br.readLine();
			}
			testSuites.setMethodName(strList);
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block	
			e.printStackTrace();
		} 
		return testSuites;
	}

	private boolean isAnnotation(String line) {
		if (line.startsWith("/") || line.startsWith("*")) {
			return true;
		}
		return false;
	}

	private String getCaseName(String line) {
		int start = line.indexOf(" test") + 1;
		int end = line.indexOf("(");
		return line.substring(start, end).trim();
	}
}
