package nd.com.ci.robotium;

import java.util.ArrayList;

public class TestSuite {
	private String packageName = null;
	private String className = null;
	private ArrayList<String> methodName = null;

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public ArrayList<String> getMethodName() {
		return methodName;
	}

	public void setMethodName(ArrayList<String> methodName) {
		this.methodName = methodName;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str = "packageName = " + packageName + " className = " + className;
		String tempStr = "";
		for (String methodStr : methodName) {
			tempStr += str + " method =" + methodStr + "\n";
		}

		return tempStr;
	}
}
