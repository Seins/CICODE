package nd.com.server;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import nd.com.db.model.Element;
import nd.com.server.python.JunitScript;
import nd.com.server.python.RealMonkey;

public class ScriptTool {
	Writer python = null;
	StringBuilder content = null;
	String modelName = null;
	String modelDirPath = null;
	Map<Integer, String> operational = null;
	RealMonkey realMonkey = new RealMonkey();
	JunitScript junitScript = new JunitScript();

	public ScriptTool(String modelName, String projectDirPath) {
		this.modelName = modelName;
		this.operational = Util.dao.getOperational();
		this.modelDirPath = projectDirPath;
	}


	private void createScript(String scriptName) {
		content = new StringBuilder();
		String fileName = this.modelDirPath + scriptName;
		
		File file = FileUtil.createFile(fileName, ".py");

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
		testCase.append("print 'xml_file_path#'+ROOTPATH\n");
		testCase.append("print 'TestModelName#', filename\n");
		return junitScript.setUp(testCase.toString());
	}

	private String createClass(String className) {
		return String.format("\nclass %s(unittest.TestCase):\n", className);
	}

	private String startApp(String packageName, String activityName) {
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

	public void generateScript(String modelName, List<List<Element>> scriptList) {
		createScript(modelName);
		try {
			content.append(setUp());
			for (List<Element> list : scriptList) {
				StringBuilder sb = new StringBuilder();
				Element element = list.get(0);
				String caseName = String.format("%s%s", element.getClassType(), element.getElementId());
				sb.append(String.format("logPath = modelname+'/%s'\n", caseName));
				sb.append("device=rMonkeyRunner(logPath)\n");
				sb.append(startApp("com.up91.pocket",
						"com.nd.k12.app.pocketlearning.view.activity.SplashActivity"));
				System.out.println("-----------------------------------------");
				int size = list.size();
				for (int i = size - 1; i >= 0; i--) {
					sb.append(writeCode(list.get(i)));
					System.out.println(list.get(i).toString());
				}
				System.out.println(sb.toString());
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
