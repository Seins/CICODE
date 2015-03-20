package nd.com.ci;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * 获取配置文件信息config.properties
 * @author wuqiaomin
 *
 */
public final class PropertiesManager {
	public static String debugKeyStorePath;
	public static String androidJarPath;
	public static String javaTarget;
	public static String reportPath;
	public static String rootPath;

	static {
		Properties prop = new Properties();

		try {
			rootPath = System.getProperty("user.dir");
			InputStream in = new FileInputStream(new File(
					rootPath + "\\config.properties"));
			prop.load(in);
			debugKeyStorePath = prop.getProperty("debugKeyStorePath");
			androidJarPath = prop.getProperty("androidJarPath");
			javaTarget = prop.getProperty("javaTarget");
			reportPath = prop.getProperty("reportPath");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}	

	private PropertiesManager() {
	}
}
