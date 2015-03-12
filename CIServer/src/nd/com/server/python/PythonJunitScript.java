package nd.com.server.python;

public class PythonJunitScript {
	
	public String setUp(String content){
		StringBuilder sb = new StringBuilder();
		sb.append("    def setUp(self):\n");
		formatContent(content, sb);
		return sb.toString();
	}

	public String tearDown(String content){
		StringBuilder sb = new StringBuilder();
		sb.append("    def tearDown(self):\n");
		formatContent(content, sb);
		return sb.toString();
	}
	
	public String testCase(String methodName, String content){
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("    def test_%s(self):\n", methodName));
		formatContent(content, sb);
		return sb.toString();
	}
	
	public String mainMethod() {
		String main = "if __name__ == \"__main__\":\n";
		main += "    unittest.main(testRunner=RealTestRunner)";
		return main;
	}
	
	private void formatContent(String content, StringBuilder sb) {
		String space8 = "        ";
		sb.append(space8 + content.replaceAll("\n", "\n"+space8));
		sb.append("\n");
	}


}
