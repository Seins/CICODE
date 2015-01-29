package nd.com.webservice;

import javax.xml.ws.Endpoint;

public class StartWebservice {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Endpoint.publish("http://192.168.6.182:8082/IGenerateScript", new GenerateScript());
		System.out.print("success");
	}

}
