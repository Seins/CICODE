package nd.com.server.robotium;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import nd.com.db.model.Element;
import nd.com.server.FileUtil;
import nd.com.server.Util;

public class JavaScriptTool {
	Writer java = null;
	StringBuilder content = null;
	String modelName = null;
	String modelDirPath = null;
	String packageName = null;
	String activityName = null;
	Map<Integer, String> operational = null;
	JavaJunitScript junitScript = new JavaJunitScript();
	Robotium robotium = new Robotium();
	FileUtil fileUtil = new FileUtil();
	
	public JavaScriptTool(String modelName, String projectDirPath) {
		this.modelName = modelName;
		this.operational = Util.dao.getOperational();
		this.modelDirPath = projectDirPath;
	}
	
	private void setComponent(String packageName, String activityName){
		this.packageName = packageName;
		this.activityName = activityName;
	}
	
	private void createScript(String scriptName){
		content = new StringBuilder();
	
		String path = this.packageName.replaceAll("\\.", "/");
		System.out.println("path:"+path);
		String fileName = this.modelDirPath+"src/"+path+"/test/" + scriptName+"Test";
		File file = fileUtil.createFile(fileName, ".java");
		try {
			java = new FileWriter(file, true);
			java.write(initClass(this.modelName,file.getName().split("\\.")[0],packageName, activityName));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String initClass(String modelName, String scriptName,
			String packageName, String activityName) {
		// TODO Auto-generated method stub
		return robotium.scriptInit(modelName, scriptName, packageName, activityName);
	}
	
	private String writeCode(Element element) {
		StringBuilder sb = new StringBuilder();
		String operate = operational.get(element.getOperationalId());
		switch (operate) {
		case "click":
			sb.append(robotium.clickOnWebElement(element));
			sb.append(robotium.sleep(2000));
			break;
		default:
			break;
		}
		return sb.toString();
	}
	
	private void endWrite(){
		if (java != null) {
			try {
				java.write(content.toString());
				java.write("}");
				java.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void generateScript(List<List<Element>> scriptList) {
		// TODO Auto-generated method stub
		
		setComponent("qa.demo","qa.demo.MainActivity");
		createScript("MainActivity");
		try {
			for (List<Element> list : scriptList) {
				StringBuilder sb = new StringBuilder();
				Element element = list.get(0);
				String caseName = String.format("%s%s", element.getClassType(), element.getElementId());
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
