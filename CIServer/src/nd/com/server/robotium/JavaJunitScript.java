package nd.com.server.robotium;

public class JavaJunitScript {

	public Object testCase(String caseName, String content) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("    public void test_%s(){%s}\n", caseName, content));
		return sb.toString();
	}

}
