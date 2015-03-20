package nd.com.ci.robotium;

import java.io.File;

import nd.com.ci.PropertiesManager;
import nd.com.server.FileUtil;

public class CompileApk {
	FileUtil fileUtil = new FileUtil();
	String scriptRootPath = null;
	String packageName = null;
	String classPath = null;
	String binPath = null;
	String genPath = null;
	String libsPath = null;
	String srcPath = null;
	String resPath = null;
	String manifastXmlPath = null;

	public CompileApk(String scriptRootPath, String packageName) {
		this.scriptRootPath = scriptRootPath;
		this.packageName = packageName;
		this.binPath = this.scriptRootPath + "/bin";
		this.genPath = this.scriptRootPath + "/gen";
		this.libsPath = this.scriptRootPath + "/libs";
		this.srcPath = this.scriptRootPath + "/src";
		this.resPath = this.scriptRootPath + "/res";
		this.manifastXmlPath = this.scriptRootPath + "/AndroidManifest.xml";
		this.classPath = packageName.replaceAll("\\.", "/");
	}

	// 初始化,创建gen bin目录
	private void init() {
		fileUtil.deleteDir(new File(binPath));
		fileUtil.deleteDir(new File(genPath));
		fileUtil.createDir(binPath);
		fileUtil.createDir(genPath);
	}

	public void compileApk() {

		// 初始化
		init();
		// 生成R.JAVA文件
		createR();
		// 编译java文件
		compileJava();
		// 生成dex文件，添加相关jar文件
		createDex();
		// 打包资源文件
		createResources();
		// 生成apk
		createApk();
		// 对apk进行签名
		signApk();
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

	private void signApk() {
		// TODO Auto-generated method stub
		String cmd = String
				.format("jarsigner -digestalg SHA1 -sigalg MD5withRSA -keystore %s -storepass android -keypass android %s androiddebugkey",
						PropertiesManager.debugKeyStorePath, binPath
								+ "/test.apk");
		System.out.println(cmd);
		runCommond(cmd);
	}

	private void createApk() {
		// TODO Auto-generated method stub
		String cmd = String.format(
				"cmd /c apkbuilder %s -v -u -z %s -f %s -rf %s -nf %s -rj %s ",
				binPath + "/test.apk", binPath + "/resources.ap_", binPath
						+ "/classes.dex", srcPath, libsPath, libsPath);
		System.out.println(cmd);
		runCommond(cmd);
	}

	private void createResources() {
		// TODO Auto-generated method stub
		String cmd = String.format(
				"aapt package -f -S %s -I %s -M %s -F %s",resPath,
				PropertiesManager.androidJarPath,manifastXmlPath, binPath + "/resources.ap_");
		System.out.println(cmd);
		runCommond(cmd);
	}

	private void createDex() {
		// TODO Auto-generated method stub
		String cmd = String.format("cmd /c dx --dex --output=%s %s %s", binPath
				+ "/classes.dex", binPath, libsPath + "/*.jar");
		System.out.println(cmd);
		runCommond(cmd);
	}

	private void compileJava() {
		// TODO Auto-generated method stub

		String cmd = String
				.format("javac -encoding UTF-8 -target %s -bootclasspath %s -d %s %s/*.java %s/*.java  -classpath %s",
						PropertiesManager.javaTarget,PropertiesManager.androidJarPath,binPath,
						srcPath + "/" + classPath, genPath + "/" + classPath,
						libsPath + "/robotium.jar");
		System.out.println(cmd);
		runCommond(cmd);
	}

	private void createR() {
		// TODO Auto-generated method stub
		String cmd = String.format("aapt p -f -m -J %s -S %s -I %s -M %s", genPath, resPath, PropertiesManager.androidJarPath, manifastXmlPath);
		System.out.println(cmd);
		runCommond(cmd);
	}
}
