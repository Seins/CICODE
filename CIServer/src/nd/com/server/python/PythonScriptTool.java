package nd.com.server.python;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import nd.com.db.model.Element;
import nd.com.server.FileUtil;
import nd.com.server.Util;

public class PythonScriptTool {
	Writer python = null;
	StringBuilder content = null;
	String modelName = null;
	String modelDirPath = null;
	Map<Integer, String> operational = null;
	RealMonkey realMonkey = new RealMonkey();
	PythonJunitScript junitScript = new PythonJunitScript();
	FileUtil fileUtil = new FileUtil();

	public PythonScriptTool(String modelName, String projectDirPath) {
		this.modelName = modelName;
		this.operational = Util.dao.getOperational();
		this.modelDirPath = projectDirPath;
	}

	private void createScript(String scriptName) {
		content = new StringBuilder();
		String fileName = this.modelDirPath + scriptName;
		
		File file = fileUtil.createFile(fileName, ".py");

		try {
			python = new FileWriter(file, true);
			python.write(realMonkey.scriptInit());
			python.write(createClass("ModelName"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String setUp() {
		StringBuilder testCase = new StringBuilder();
		testCase.append("print 'xml_file_path#'+ROOTPATH+'/xml'\n");
		testCase.append("print 'TestModelName#', package_name\n");
		return junitScript.setUp(testCase.toString());
	}

	private String createClass(String className) {
		return String.format("\nclass %s(unittest.TestCase):\n", className);
	}

	private String setComponent(String packageName, String activityName) {
		return realMonkey.startApp(packageName, activityName);
	}

	private String writeCode(Element element) {
		StringBuilder sb = new StringBuilder();
		String operate = operational.get(element.getOperationalId());
		switch (operate) {
		case "click":
			sb.append(realMonkey.click(element));
			sb.append(realMonkey.sleep("2.0"));
			break;
		default:
			break;
		}
		return sb.toString();
	}

	private void endWrite() {
		if (python != null) {
			try {
				content.append(junitScript.mainMethod());
				python.write(content.toString());
				python.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void generateScript(List<List<Element>> scriptList) {
		createScript(this.modelName);
		try {
			content.append(setUp());
			for (List<Element> list : scriptList) {
				StringBuilder sb = new StringBuilder();
				Element element = list.get(0);
				String caseName = String.format("%s%s", element.getClassType(), element.getElementId());
				sb.append(String.format("logPath = modelname+'/%s'\n", caseName));
				sb.append("device=rMonkeyRunner(logPath)\n");
				sb.append(setComponent("qa.demo",
						"qa.demo.MainActivity"));
				System.out.println("-----------------------------------------");
				int size = list.size();
				for (int i = size - 1; i >= 0; i--) {
					sb.append(writeCode(list.get(i)));
					System.out.println(list.get(i).toString());
				}
				content.append(junitScript.testCase(caseName, sb.toString()));
				System.out.println("-----------------------------------------");
			}
			endWrite();
		} catch (Exception e) {
			endWrite();
			e.printStackTrace();
		}
	}
}
