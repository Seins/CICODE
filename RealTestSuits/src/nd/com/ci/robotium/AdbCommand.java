package nd.com.ci.robotium;


public class AdbCommand {

	public Process pullFile(String sourcePath, String destPath) {
		String cmd = "adb pull " + sourcePath + " " + destPath;
		System.out.println(cmd);
		return runCommond(cmd);
	}

	public Process pushFile(String sourcePath, String destPath) {
		String cmd = "adb push " + sourcePath + " " + destPath;
		System.out.println(cmd);
		return runCommond(cmd);
	}

	public Process runTest(String deviceName, String className,
			String packageName) {
		String cmd = "";
		String before = "";
		if (deviceName == null) {
			before = "adb shell am instrument";
		} else {
			before = "adb -s " + deviceName + " shell am instrument";
		}
		cmd = String
				.format("%s -e class %s -w %s/com.zutubi.android.junitreport.JUnitReportTestRunner",
						before, className, packageName);
		System.out.println(cmd);
		return runCommond(cmd);

	}

	private Process runCommond(String cmd) {
		Process p = null;
		try {
			p = Runtime.getRuntime().exec(cmd);
			p.waitFor();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}

	public Process installApk(String apkName) {
		String cmd = "";
		try {
			cmd = "adb install -r " + apkName;
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println(cmd);
		return runCommond(cmd);
	}

	public Process uninstallApk(String packgeName) {
		String cmd = "";
		try {
			cmd = "adb uninstall " + packgeName;
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println(cmd);
		return runCommond(cmd);
	}
}
