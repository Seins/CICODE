package nd.com.server.robotium;

import nd.com.db.model.Element;

public class Robotium {
	public String scriptInit(String modelName,String scriptName, String packagename, String activityName) {
		String initStr = "package %s.test;\n"
				+ "import com.robotium.solo.*;\n"
				+ "import android.test.ActivityInstrumentationTestCase2;\n"
				+ "@SuppressWarnings(\"rawtypes\")\n"
				+ "//%s\n"
				+ "public class %s extends ActivityInstrumentationTestCase2 {\n"
				+ "    private Solo solo;\n"
				+ "    private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = \"%s\";\n"
				+ "    private static Class<?> launcherActivityClass;\n"
				+ "    static{\n"
				+ "        try {\n"
				+ "            launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);\n"
				+ "        } catch (ClassNotFoundException e) {\n"
				+ "            throw new RuntimeException(e);\n"
				+ "        }\n"
				+ "    }\n"
				+ "    @SuppressWarnings(\"unchecked\")\n"
				+ "    public %s() throws ClassNotFoundException {\n"
				+ "        super(launcherActivityClass);\n"
				+ "    }\n"
				+ "    public void setUp() throws Exception {\n"
				+ "        super.setUp();\n"
				+ "	       solo = new Solo(getInstrumentation());\n"
				+ "	       getActivity();\n"
				+ "        solo.sleep(2000);"
				+ "    }\n"
				+ "    @Override\n"
				+ "    public void tearDown() throws Exception {\n"
				+ "        solo.finishOpenedActivities();\n"
				+ "        super.tearDown();\n"
				+ "    }";
		return String.format(initStr, packagename, modelName,scriptName,  activityName, scriptName);
	}
	
	public String sleep(int time){
		return String.format("solo.sleep(%d);\n", time);
	}
	
	public String clickOnWebElement(Element element){
		String code = "";
		switch (element.getClassType()) {
		case "TextView":
			code = "solo.clickOnWebElement(By.textContent(\"%s\"));\n";
			break;
		case "TagName":
			code = "solo.clickOnWebElement(By.tagName(\"%s\"));\n";
			break;
		default:
			break;
		}
		return String.format(code, element.getText());
	}
	
	public String enterTextInWebElement(Element element) {
		String code = "";
		switch (element.getClassType()) {
		case "TextView":
			code = "solo.enterTextInWebElement(By.textContent(\"%s\"), \"%s\");\n";
			break;
		case "TagName":
			code = "solo.enterTextInWebElement(By.tagName(\"%s\"), \"%s\");\n";
			break;
		case "Xpath":
			code = "solo.enterTextInWebElement(By.xpath(\"//input[%s]\"), \"%s\");\n";
			break;
		default:
			break;
		}
		return String.format(code, element.getElementIndex()+1, element.getText());
	}
}
