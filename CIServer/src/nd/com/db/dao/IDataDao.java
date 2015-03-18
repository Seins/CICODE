package nd.com.db.dao;

import java.util.List;
import java.util.Map;

import nd.com.db.model.Element;
import nd.com.db.model.ElementOperational;
import nd.com.db.model.ProgramPage;

public interface IDataDao {
	List<ProgramPage> getPagesForDepth(int programId, int depth);
	
	Element getElement(int elementId);
	List<Element> getElements(List<Integer> elementIdList);
	
	ElementOperational getElementOperational(int targetPageId);
	
	Map<Integer, String> getOperational();
	Map<Integer, String> getPage();
	Map<Integer, String> getResult();
	Map<Integer, String> getProgram();
	
	int getMaxDepth(int programId);
	int getPageForElement(int elementId);
	int getDepthForPages(int pageId);
	List<Integer> getElementForPage(int pageId);
	int getProgramIdForName(String name);
	List<String> getProgramNameForProjectName(String projectName);
	String createProjectScript(String projectName, int flag);
	
}
