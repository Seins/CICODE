package nd.com.db.dao;

import java.util.List;

import nd.com.db.model.Element;
import nd.com.db.model.ElementOperational;
import nd.com.db.model.ProgramPage;

public interface IDataDao {
	List<ProgramPage> getPagesForDepth(int programId, int depth);
	
	Element getElement(int elementId);
	List<Element> getElements(List<Integer> elementIdList);
	
	ElementOperational getElementOperational(int targetPageId);
	
	String getOperational(int operationalId);
	String getPage(int pageId);
	String getResult(int getResult);
	String getProgram(int programId);
	
	int getMaxDepth(int programId);
	int getPageForElement(int elementId);
	int getDepthForPages(int pageId);
	List<Integer> getElementForPage(int pageId);
	int getProgramIdForName(String name);
	List<String> getProgramNameForProjectName(String projectName);
	String createProjectScript(String projectName, int flag);
	
}
