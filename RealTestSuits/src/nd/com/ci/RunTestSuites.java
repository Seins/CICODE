package nd.com.ci;

import java.io.File;
import java.util.ArrayList;

import nd.com.ci.robotium.ParserTestCase;
import nd.com.ci.robotium.TestSuite;

public class RunTestSuites {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<TestSuite> testsuites = new ParserTestCase().findAllTestClass(new File("E:/CISCRIPT/Demo"));
		for (TestSuite testSuites2 : testsuites) {
			System.out.println(testSuites2.toString());
		}
	}

}
