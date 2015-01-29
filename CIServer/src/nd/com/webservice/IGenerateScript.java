package nd.com.webservice;

import javax.jws.WebService;

@WebService
public interface IGenerateScript {
	public boolean generateScript(String projectName);	

}
