package nd.com;


import nd.com.server.ScriptElement;

public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// System.out.println(Util.dao.getProgramIdForName("测试"));
		// System.out.println(Util.dao.getProgramNameForProjectName("91助手"));
		//new Robotium().scriptInit("com.nd.demo","com.nd.demo.MainActivity");
		//new CreateProject().createTestProject("TestCode", "com.nd.demo");
		//String str = "sdf.ddd.ddd";
		//System.out.println(str.replaceAll("\\.", "/"));
//		File file = new File("D:/code/log.log");
//		System.out.println(file.getName());
		new ScriptElement().createProjectScript("互动课堂", 1);
	}
}
