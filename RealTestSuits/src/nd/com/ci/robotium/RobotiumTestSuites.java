package nd.com.ci.robotium;

import java.io.File;
import java.util.ArrayList;

public class RobotiumTestSuites {
	AdbCommand adb = new AdbCommand();
	private String xmlPath = null;
	public RobotiumTestSuites(String xmlPath){
		this.xmlPath = xmlPath;
	}

	public void startRun(String scriptRootPath) {
		ArrayList<TestSuite> testSuites = new ParserTestCase()
				.findAllTestClass(new File(scriptRootPath));
		CompileApk compile = new CompileApk(scriptRootPath, testSuites.get(0).getPackageName());
		compile.compileApk();
		adb.installApk(scriptRootPath+"/bin/test.apk");
		for (TestSuite testSuite : testSuites) {
			adb.runTest(null, testSuite.getClassName(),
					testSuite.getPackageName());
			String appPackageName = testSuite.getPackageName().substring(0,
					testSuite.getPackageName().lastIndexOf("."));
			adb.pullFile("/data/data/" + appPackageName
					+ "/files/junit-report.xml", String.format("%s/TEST-%s.xml", xmlPath, testSuite.getClassName()));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
