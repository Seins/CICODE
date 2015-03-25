package nd.com.db.dao;

import java.util.ArrayList;
import java.util.List;

import nd.com.db.model.Element;
import nd.com.db.model.ElementOperational;
import nd.com.db.model.ProgramPage;
import nd.com.server.ScriptElement;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Service;

@SuppressWarnings("unchecked")
@Service(value = "IDataDao")
public class DataDao extends SqlMapClientDaoSupport implements IDataDao {

	/**
	 * 获取模块下，指定深度的页面
	 */
	@Override
	public List<ProgramPage> getPagesForDepth(int programId, int depth) {
		// TODO Auto-generated method stub
		ProgramPage programPage = new ProgramPage();
		programPage.setProgramId(programId);
		programPage.setDepth(depth);
		List<ProgramPage> programPageList = (List<ProgramPage>) getSqlMapClientTemplate()
				.queryForList("ProgramPage.getPagesForDepth", programPage);
		return programPageList;
	}

	/**
	 * 获取元素详细信息
	 */
	@Override
	public Element getElement(int elementId) {
		// TODO Auto-generated method stub
		Element element = (Element) getSqlMapClientTemplate().queryForObject("Element.getElement", elementId);
		return element;
	}
	
	/**
	 * 获取元素详细信息
	 */
	@Override
	public List<Element> getElements(List<Integer> elementIdList) {
		// TODO Auto-generated method stub
		if (elementIdList.size() <= 0) {
			return new ArrayList<Element>();
		}
		return getSqlMapClientTemplate().queryForList("Element.getElements", elementIdList);
	}
	
	/**
	 * 通过targerPageId查找该页面是触发哪个元素后跳转过来的
	 */
	@Override
	public ElementOperational getElementOperational(int targetPageId) {
		// TODO Auto-generated method stub
		ElementOperational elementOperational = (ElementOperational) getSqlMapClientTemplate()
				.queryForObject("ElementOperational.getElementOperational",
						targetPageId);
		return elementOperational;
	}

	/**
	 * 映射operational表
	 */
	@Override
	public String getOperational(int operationalId) {
		// TODO Auto-generated method stub
		String operational = (String)getSqlMapClientTemplate().queryForObject("Operational.getOperational",operationalId);
		return operational;
	}

	/**
	 * 映射page表
	 */
	@Override
	public String getPage(int pageId) {
		// TODO Auto-generated method stub
		String page = (String)getSqlMapClientTemplate().queryForObject("Page.getPage",pageId);
		return page;
	}

	/**
	 * 映射result表
	 */
	@Override
	public String getResult(int resultId) {
		// TODO Auto-generated method stub
		String result = (String)getSqlMapClientTemplate().queryForObject("Result.getResult",resultId);
		return result;
	}

	/**
	 * 映射program表
	 */
	@Override
	public String getProgram(int programId) {
		// TODO Auto-generated method stub
		String program = (String)getSqlMapClientTemplate().queryForObject("Program.getProgram", programId);
		return program;
	}
	
	/**
	 * 通过元素ID查找元素所属的页面
	 */
	@Override
	public int getPageForElement(int elementId) {
		// TODO Auto-generated method stub
		int pageId = (Integer)getSqlMapClientTemplate().queryForObject("Element.getPageForElement", elementId);
		return pageId;
	}
	
	/**
	 * 通过页面ID查找，属于页面的所有元素
	 */
	public List<Integer> getElementForPage(int pageId) {
		// TODO Auto-generated method stub
		List<Integer> elementIdList = (List<Integer>)getSqlMapClientTemplate().queryForList("Element.getElementForPage", pageId);
		return elementIdList;
	}
	
	/**
	 * 获取模块的深度
	 */
	@Override
	public int getMaxDepth(int programId) {
		// TODO Auto-generated method stub 
		return (Integer)getSqlMapClientTemplate().queryForObject("ProgramPage.getMaxDepth", programId);
	}

	/**
	 * 查找指定页面的深度
	 */
	@Override
	public int getDepthForPages(int pageId) {
		// TODO Auto-generated method stub
		return (Integer)getSqlMapClientTemplate().queryForObject("ProgramPage.getDepthForPages", pageId);
	}

	/**
	 * 根据模块名获取模块ID
	 */
	@Override
	public int getProgramIdForName(String name) {
		// TODO Auto-generated method stub
		return (Integer)getSqlMapClientTemplate().queryForObject("Program.getProgramIdForName", name);
	}

	/**
	 * 根据项目ID获取项目下的所有模块
	 */
	@Override
	public List<String> getProgramNameForProjectName(String projectName) {
		// TODO Auto-generated method stub
		return (List<String>)getSqlMapClientTemplate().queryForList("ProjectProgram.getProgramNameForProjectName", projectName);
	}

	@Override
	public String createProjectScript(String projectName, int flag) {
		// TODO Auto-generated method stub
		return new ScriptElement().createProjectScript(projectName, flag);
	}

}
