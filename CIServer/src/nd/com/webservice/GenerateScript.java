package nd.com.webservice;

import javax.jws.WebService;

import nd.com.server.ScriptElement;


@WebService(endpointInterface = "nd.com.webservice.IGenerateScript",serviceName = "IGenerateScript")
public class GenerateScript implements IGenerateScript {

	public boolean generateScript(String projectName) {
		ScriptElement scriptElement = new ScriptElement();
		return scriptElement.createProjectScript(projectName);
	}

}
