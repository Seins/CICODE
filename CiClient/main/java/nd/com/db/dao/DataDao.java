package nd.com.db.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nd.com.db.model.Element;
import nd.com.db.model.ElementOperational;
import nd.com.db.model.ProgramPage;

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
		Element element = (Element) getSqlMapClientTemplate().queryForObject("ele.getElement", elementId);
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
		return getSqlMapClientTemplate().queryForList("ele.getElements", elementIdList);
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
	public Map<Integer, String> getOperational() {
		// TODO Auto-generated method stub
		Map<Integer, String> operational = (Map<Integer, String>)getSqlMapClientTemplate().queryForMap("Operational.getOperational",null, "operationalid", "content");
		return operational;
	}

	/**
	 * 映射page表
	 */
	@Override
	public Map<Integer, String> getPage() {
		// TODO Auto-generated method stub
		Map<Integer, String> page = (Map<Integer, String>)getSqlMapClientTemplate().queryForMap("Page.getPage",null, "pageid", "pagename");
		return page;
	}

	/**
	 * 映射result表
	 */
	@Override
	public Map<Integer, String> getResult() {
		// TODO Auto-generated method stub
		Map<Integer, String> result = (Map<Integer, String>)getSqlMapClientTemplate().queryForMap("Result.getResult",null, "resultid", "resulttype");
		return result;
	}

	/**
	 * 映射program表
	 */
	@Override
	public Map<Integer, String> getProgram() {
		// TODO Auto-generated method stub
		Map<Integer, String> program = (Map<Integer, String>)getSqlMapClientTemplate().queryForMap("Program.getProgram",null, "programid", "name");
		return program;
	}
	
	/**
	 * 通过元素ID查找元素所属的页面
	 */
	@Override
	public int getPageForElement(int elementId) {
		// TODO Auto-generated method stub
		int pageId = (Integer)getSqlMapClientTemplate().queryForObject("ele.getPageForElement", elementId);
		return pageId;
	}
	
	/**
	 * 通过页面ID查找，属于页面的所有元素
	 */
	public List<Integer> getElementForPage(int pageId) {
		// TODO Auto-generated method stub
		List<Integer> elementIdList = (List<Integer>)getSqlMapClientTemplate().queryForList("ele.getElementForPage", pageId);
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

}
