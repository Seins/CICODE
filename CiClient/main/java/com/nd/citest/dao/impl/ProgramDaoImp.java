package com.nd.citest.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.nd.citest.dao.IProgramDao;
import com.nd.citest.model.Ele_op_page;
import com.nd.citest.model.Element;
import com.nd.citest.model.Element_operational;
import com.nd.citest.model.Element_page;
import com.nd.citest.model.Operational;
import com.nd.citest.model.Page;
import com.nd.citest.model.Program;
import com.nd.citest.model.Program_page;
import com.nd.citest.model.Project;
import com.nd.citest.model.Project_program;
@SuppressWarnings("unchecked")
public class ProgramDaoImp extends SqlMapClientDaoSupport implements IProgramDao {

	@Transactional
	public List<Program> getProgramList() {
		List<Program> programList=getSqlMapClientTemplate().queryForList("program.getProgram", null);
		return programList;
	}

	@Transactional
	public List<Program_page> getPageByProgramid(int programid) {
		List<Program_page> programList=getSqlMapClientTemplate().queryForList("Program_page.getProgramPage", programid);
		return programList;
	}
	@Transactional
	public int insertPage(Page page) {
		int pageid=(Integer)getSqlMapClientTemplate().insert("Page.insertPage", page);
		return pageid;
	}
	@Transactional
	public void insertProgramPage(Program_page programPage) {
		getSqlMapClientTemplate().insert("Program_page.insertProgramPage", programPage);
		
	}
	@Transactional
	public int insertProgramPage(String pagename, int programid,int parentpageid,String text,String content,String classtype,int elementindex) {
		int pageid=0;
		if(parentpageid==0){
			Page page=new Page();
			page.setPagename(pagename);
			pageid=insertPage(page);
			Program_page model=null;
			Program_page newModel=new Program_page();
			newModel.setPageid(pageid);
			newModel.setProgramid(programid);
			newModel.setEndpage(0);
			newModel.setParentpageid(parentpageid);
			if(parentpageid==0){
				newModel.setDepth(0);
			}else{
				Program_page programpage=new Program_page();
				programpage.setPageid(parentpageid);
				programpage.setProgramid(programid);
				model=getDepthById(programpage);
				newModel.setDepth(model.getDepth()+1);
			}
			this.insertProgramPage(newModel);
		}else{
			Page page=new Page();
			page.setPagename(pagename);
			pageid=insertPage(page);
			Program_page model=null;
			Program_page newModel=new Program_page();
			newModel.setPageid(pageid);
			newModel.setProgramid(programid);
			newModel.setEndpage(0);
			newModel.setParentpageid(parentpageid);
			if(parentpageid==0){
				newModel.setDepth(0);
			}else{
				Program_page programpage=new Program_page();
				programpage.setPageid(parentpageid);
				programpage.setProgramid(programid);
				model=getDepthById(programpage);
				newModel.setDepth(model.getDepth()+1);
			}
			this.insertProgramPage(newModel);
			Element ele=new Element();
			ele.setClasstype(classtype);
			ele.setElementindex(elementindex);
			ele.setText(text);
			//插入元素信息
			int elementid=this.insertElement(ele);
			Operational oper=new Operational();
			oper.setContent(content);
			//插入操作信息
			int operationalid=this.insertOperational(oper);
			Element_operational ele_oper=new Element_operational();
			ele_oper.setElementid(elementid);
			ele_oper.setOperationalid(operationalid);
			ele_oper.setResultid(2);
			//插入元素操作信息
			int ele_oper_id=this.insertEleOperational(ele_oper);
			Element_page ele_page=new Element_page();
			ele_page.setElementid(elementid);
			ele_page.setPageid(parentpageid);
			int id=this.insertElement_page(ele_page);
			//插入元素指向页面信息
			Ele_op_page ele_op_page=new Ele_op_page();
			ele_op_page.setElem_opid(ele_oper_id);
			ele_op_page.setTargetpageid(pageid);
			this.insertEle_op_page(ele_op_page);
	   }
		return pageid;
	}

	public Program_page getDepthById(Program_page programpage) {
		Program_page model=(Program_page)getSqlMapClientTemplate().queryForObject("Program_page.getDepthById", programpage);
		return model;
	}
	@Transactional
	public void updataPage(Page page) {
		getSqlMapClientTemplate().update("Page.updataPage", page);
	}
	@Transactional
	public void updateProgramPage(Program_page programPage) {
		getSqlMapClientTemplate().update("Program_page.updateProgramPage", programPage);
		
	}
	@Transactional
	public Page getPageByPageid(String pagename) {
		Page page=(Page)getSqlMapClientTemplate().queryForObject("Page.getPageByPageid", pagename);
		return page;
		
	}

	public Program getProgramByProgramid(int programid) {
		Program program=(Program)getSqlMapClientTemplate().queryForObject("program.getProgramByProgramid",programid);
		return program;
	}
	@Transactional
	public void updateProgramPage(Program_page programPage, String[] pageids,int elementindex,String text,String content,String classtype) {
		Ele_op_page ele_op_page=this.getEleOpPageByPageid(programPage.getPageid());
		Program_page program_page=new Program_page();
		program_page.setPageid(programPage.getPageid());
		program_page.setProgramid(programPage.getProgramid());
		Program_page model=this.getProgramPageById(programPage);
		Element_operational element_operational=this.getEle_operational(ele_op_page.getElem_opid());
		this.delElePageById(element_operational.getElementid(), model.getParentpageid());
		this.delEleOpPageByPageid(programPage.getPageid());
		this.delEleOpertional(ele_op_page.getElem_opid());
		this.delOperational(element_operational.getOperationalid());
		this.delElementById(element_operational.getElementid());
		getSqlMapClientTemplate().update("Program_page.updateProgramPage", programPage);
		if(!pageids[0].equals("")){
			for(int i=0;i<pageids.length;i++){
				Program_page programpage=new Program_page();
				programpage.setProgramid(programPage.getProgramid());
				programpage.setPageid(Integer.parseInt(pageids[i]));
				programpage.setDepth(programPage.getDepth()+(i+1));
				getSqlMapClientTemplate().update("Program_page.updateDepthById", programpage);
			}
		}
		Element element=new Element();
		element.setClasstype(classtype);
		element.setElementindex(elementindex);
		element.setText(text);
		int elementid=this.insertElement(element);
		//插入页面元素信息
		Element_page ele_page=new Element_page();
		ele_page.setElementid(elementid);
		ele_page.setPageid(programPage.getParentpageid());
		this.insertElement_page(ele_page);
		//插入操作信息
		int operationalid=0;
		Operational oper=new Operational();
		oper.setContent(content);
		operationalid=this.insertOperational(oper);
		//插入元素操作
		Element_operational ele_oper=new Element_operational();
		ele_oper.setElementid(elementid);
		ele_oper.setOperationalid(operationalid);
		ele_oper.setResultid(2);
		int ele_oper_id=this.insertEleOperational(ele_oper);
		//插入元素指向页面信息
		Ele_op_page eleop_page=new Ele_op_page();
		eleop_page.setElem_opid(ele_oper_id);
		eleop_page.setTargetpageid(program_page.getPageid());
		this.insertEle_op_page(eleop_page);
		
	}
	@Transactional
	public int insertElement(Element ele) {
		int elementid=(Integer)getSqlMapClientTemplate().insert("Element.insertElement", ele);
		return elementid;
		
	}
	@Transactional
	public int insertOperational(Operational oper) {
		int operationalid=(Integer)getSqlMapClientTemplate().insert("Operational.insertOperational", oper);
		return operationalid;
	}
	@Transactional
	public int insertEleOperational(Element_operational eleOperational) {
		int ele_oper_id=(Integer)getSqlMapClientTemplate().insert("Element_operational.insertElement_operational", eleOperational);
		return ele_oper_id;
	}
	@Transactional
	public int insertElement_page(Element_page elePage) {
		int id=(Integer)getSqlMapClientTemplate().insert("Element_page.insertElement_page", elePage);
		return id;
	}
	@Transactional
	public int insertEle_op_page(Ele_op_page eleOpPage) {
		int id=(Integer)getSqlMapClientTemplate().insert("Ele_op_page.insertEle_op_page", eleOpPage);
		return id;
		
	}
	@Transactional
	public List<Element> getPageElementByPageid(int pageid) {
		List<Element> elementlist=(List<Element>)getSqlMapClientTemplate().queryForList("Element.getPageElementByPageid", pageid);
		return elementlist;
	}
	@Transactional
	public void insertElement_oper_page(String classtype, String elmentText,int elementindex, String content, int pageid,int resultid) {
		Element element=new Element();
		element.setClasstype(classtype);
		element.setElementindex(elementindex);
		element.setText(elmentText);
		int elementid=this.insertElement(element);
		//插入页面元素信息
		Element_page ele_page=new Element_page();
		ele_page.setElementid(elementid);
		ele_page.setPageid(pageid);
		this.insertElement_page(ele_page);
		//插入操作信息
		int operationalid=0;
		Operational oper=new Operational();
		oper.setContent(content);
		operationalid=this.insertOperational(oper);
		//插入元素操作
		Element_operational ele_oper=new Element_operational();
		ele_oper.setElementid(elementid);
		ele_oper.setOperationalid(operationalid);
		ele_oper.setResultid(resultid);
		this.insertEleOperational(ele_oper);
	}

	public Operational getOperationalByContent(String content) {
		Operational oper=(Operational)getSqlMapClientTemplate().queryForObject("Operational.getOperationalByContent", content);
		return oper;
	}
	@Transactional
	public void updateElementById(Element element) {
		getSqlMapClientTemplate().update("Element.updateElementById", element);
	}
	@Transactional
	public void updateOperationalById(Operational oper) {
		getSqlMapClientTemplate().update("Operational.updateOperationalById", oper);
		
	}
	@Transactional
	public Element getElementById(int elementid) {
		Element element=(Element)getSqlMapClientTemplate().queryForObject("Element.getElementById", elementid);
		return element;
	}
	public void delElementById(int elementid) {
		getSqlMapClientTemplate().delete("Element.delElement", elementid);
	}
	@Transactional
	public void delOperational(int operationalid) {
		getSqlMapClientTemplate().delete("Operational.delOperational",operationalid);
		
	}
	@Transactional
	public void delEleOpertionalById(int elementid, int operationalid) {
		Element_operational element_operational=new Element_operational();
		element_operational.setElementid(elementid);
		element_operational.setOperationalid(operationalid);
		getSqlMapClientTemplate().delete("Element_operational.delEleOpertionalById", element_operational);
	}
	@Transactional
	public void delElePageById(int elementid, int pageid) {
		Element_page element_page=new Element_page();
		element_page.setElementid(elementid);
		element_page.setPageid(pageid);
		getSqlMapClientTemplate().delete("Element_page.delElePageById",element_page );
	}
	@Transactional
	public void delEleOperational(int pageid, int elementid, int operationalid) {
		this.delEleOpertionalById(elementid, operationalid);
		this.delElePageById(elementid, pageid);
		this.delElementById(elementid);
		this.delOperational(operationalid);
		
	}
	@Transactional
	public Ele_op_page getEleOpPageByPageid(int targetpageid) {
		Ele_op_page ele_op_page=(Ele_op_page)getSqlMapClientTemplate().queryForObject("Ele_op_page.getEleOpPageByPageid", targetpageid);
		return ele_op_page;
	}
	@Transactional
	public Element_operational getEle_operational(int id) {
		Element_operational element_operational=(Element_operational)getSqlMapClientTemplate().queryForObject("Element_operational.getEle_operational", id);
		return element_operational;
	}
	@Transactional
	public void delEleOpPageByPageid(int targetpageid) {
		getSqlMapClientTemplate().delete("Ele_op_page.delEleOpPageByPageid", targetpageid);
	}
	@Transactional
	public void delEleOpertional(int id) {
		getSqlMapClientTemplate().delete("Element_operational.delEleOpertional", id);
		
	}
	@Transactional
	public Program_page getProgramPageById(Program_page programPage) {
		Program_page program_page=(Program_page)getSqlMapClientTemplate().queryForObject("Program_page.getProgramPageById", programPage);
		return program_page;
	}
	@Transactional
	public int insertProgram(Program program,int projectid) {
		int programid=(Integer)getSqlMapClientTemplate().insert("program.insertProgram", program);
		Project_program project_program=new Project_program();
		project_program.setProgramid(programid);
		project_program.setProjectid(projectid);
		int project_program_id=(Integer)getSqlMapClientTemplate().insert("Project_program.insertProjectProgram",project_program);
		return programid;
	}
	@Transactional
	public void delProgramByProgramid(int programid) {
		getSqlMapClientTemplate().delete("program.delProgramByProgramid", programid);
	}
	@Transactional
	public List<Program_page> getProgramPageByProgramid(int programid) {
		List<Program_page> programPageList=getSqlMapClientTemplate().queryForList("Program_page.getProgramPageByProgramid", programid);
		return programPageList;
	}
	@Transactional
	public void delProgramById(int programid,int projectid){
		//删除项目、模块信息
		if(projectid==0){
			List<Project_program> projectProgramList=this.getProjectProgramByProjectid(programid);
			for(int j=0;j<projectProgramList.size();j++){
				Project_program project_program=projectProgramList.get(j);
				List<Program_page> programPageList=this.getProgramPageByProgramid(project_program.getProgramid());
				if(programPageList.size()>0){
					for(int i=0;i<programPageList.size();i++){
						Program_page programPage=programPageList.get(i);
						this.delPageByPageid(programPage.getPageid(), programPage.getProgramid());
					}
				}
				this.delProjectProgram(project_program.getProgramid());
				this.delProgramByProgramid(project_program.getProgramid());
			}
			this.delProject(programid);
			
		//删除项目模块关联信息
		}else{
			List<Program_page> programPageList=this.getProgramPageByProgramid(programid);
			if(programPageList.size()>0){
				for(int i=0;i<programPageList.size();i++){
					Program_page programPage=programPageList.get(i);
					this.delPageByPageid(programPage.getPageid(), programPage.getProgramid());
				}
			}
			this.delProjectProgram(programid);
			this.delProgramByProgramid(programid);
		}
		
	}

	public List<Element_page> getElement_pageByPageid(int pageid) {
		List<Element_page> elementPageList=getSqlMapClientTemplate().queryForList("Element_page.getElement_pageByPageid", pageid);
		return elementPageList;
	}
	@Transactional
	public void delPageByPageid(int pageid,int programid) {
		Program_page programpage=new Program_page();
		programpage.setPageid(pageid);
		List<Program_page> programPageList=this.getProgramPageByProgramid(programpage);
		List<Element_page> ele_page_list=this.getElement_pageByPageid(pageid);
		if(ele_page_list.size()>0){
			getSqlMapClientTemplate().delete("Element_page.delElePageByPageId",pageid);
		}
		for(int i=0;i<programPageList.size();i++){
			Program_page model=programPageList.get(i);
			Ele_op_page elem_op_page=null;
			elem_op_page=this.getEleOpPageByPageid(model.getPageid());
			if(elem_op_page!=null){
				this.delEleOpPageByPageid(elem_op_page.getTargetpageid());
				Element_operational ele_operational=this.getEle_operational(elem_op_page.getElem_opid());
				this.delEleOpertional(elem_op_page.getElem_opid());
				this.delOperational(ele_operational.getOperationalid());
				this.delElePageById(ele_operational.getElementid(), model.getParentpageid());
				this.delElementById(ele_operational.getElementid());
			}else{
				for(int j=0;j<ele_page_list.size();j++){
					Element_page element_page=ele_page_list.get(j);
					Element_operational ele_operational=(Element_operational)getSqlMapClientTemplate().queryForObject("Element_operational.getEle_operationalByElementid",element_page.getElementid());
						getSqlMapClientTemplate().delete("Element_operational.delEleOpertionalByElementid",element_page.getElementid());
						if(ele_operational!=null){
							this.delOperational(ele_operational.getOperationalid());
						}
						this.delElePageById(element_page.getElementid(), model.getParentpageid());
						this.delElementById(element_page.getElementid());
				}
			}
			getSqlMapClientTemplate().delete("Program_page.delProgram_PageByPageid",model.getPageid());
			getSqlMapClientTemplate().delete("Page.delPageByPageid", model.getPageid());
		}
	}
	@Transactional
	public Program getProgramById(int programid) {
		Program program=(Program)getSqlMapClientTemplate().queryForObject("program.getProgramById", programid);
		return program;
	}
	@Transactional
	public Page getPageNameByName(String pagename) {
		Page page=(Page)getSqlMapClientTemplate().queryForObject("Page.getPageNameByName", pagename);
		return page;
	}
	@Transactional
	public int copyProgramPage(String pagename, int programid,int parentpageid, String text, String content, String classtype,int elementindex) {
		Page page=this.getPageNameByName(pagename);
		List<Program_page> programPageList=this.getProgramPageById(page.getPageid());
		Program_page programPage=programPageList.get(0);
		Program_page program_page=new Program_page();
		program_page.setPageid(page.getPageid());
		program_page.setProgramid(programPage.getProgramid());
		List<Program_page> programpageList=this.getProgramPageByProgramid(program_page);
		int depth=0;
		for(int i=0;i<programpageList.size();i++){
			Program_page model=programpageList.get(i);
			if(page.getPageid()== model.getPageid()){
				//原来的顶层节点深度
				depth=model.getDepth();
			}
		  }
		//父节点为模块节点情况
		if(parentpageid==0){
			for(int j=0;j<programpageList.size();j++){
				Program_page model=programpageList.get(j);
				Program_page newModel=null;
				if(page.getPageid()== model.getPageid()){
					newModel=new Program_page();
					newModel.setPageid(model.getPageid());
					newModel.setDepth(0+(model.getDepth()-depth));
					newModel.setEndpage(0);
					newModel.setProgramid(programid);
					newModel.setParentpageid(0);
				}else{
					newModel=new Program_page();
					newModel.setPageid(model.getPageid());
					newModel.setDepth(0+(model.getDepth()-depth));
					newModel.setEndpage(0);
					newModel.setProgramid(programid);
					newModel.setParentpageid(model.getParentpageid());
				}
				this.insertProgramPage(newModel);
			}
		}else{
			//查询当前节点的深度
			Program_page depthModel=new Program_page();
			depthModel.setProgramid(programid);
			depthModel.setPageid(parentpageid);
			Program_page pPage=this.getDepthById(depthModel);
			for(int k=0;k<programpageList.size();k++){
				Program_page model=programpageList.get(k);
				Program_page newModel=null;
				if(page.getPageid()== model.getPageid()){
					newModel=new Program_page();
					newModel.setPageid(model.getPageid());
					newModel.setDepth((pPage.getDepth()+1)+(model.getDepth()-depth));
					newModel.setEndpage(0);
					newModel.setProgramid(programid);
					newModel.setParentpageid(parentpageid);
				}else{
					newModel=new Program_page();
					newModel.setPageid(model.getPageid());
					newModel.setDepth((pPage.getDepth()+1)+(model.getDepth()-depth));
					newModel.setEndpage(0);
					newModel.setProgramid(programid);
					newModel.setParentpageid(model.getParentpageid());
				}
				this.insertProgramPage(newModel);
			}
			Element ele=new Element();
			ele.setClasstype(classtype);
			ele.setElementindex(elementindex);
			ele.setText(text);
			//插入元素信息
			int elementid=this.insertElement(ele);
			Operational oper=new Operational();
			oper.setContent(content);
			//插入操作信息
			int operationalid=this.insertOperational(oper);
			Element_operational ele_oper=new Element_operational();
			ele_oper.setElementid(elementid);
			ele_oper.setOperationalid(operationalid);
			ele_oper.setResultid(2);
			//插入元素操作信息
			int ele_oper_id=this.insertEleOperational(ele_oper);
			Element_page ele_page=new Element_page();
			ele_page.setElementid(elementid);
			ele_page.setPageid(parentpageid);
			int id=this.insertElement_page(ele_page);
			//插入元素指向页面信息
			Ele_op_page ele_op_page=new Ele_op_page();
			ele_op_page.setElem_opid(ele_oper_id);
			ele_op_page.setTargetpageid(page.getPageid());
			this.insertEle_op_page(ele_op_page);
			
		}
		return 0;
	}
	@Transactional
	public List<Program_page> getProgramPageByParentpageid(int parentpageid,int programid) {
		Program_page programPage=new Program_page();
		programPage.setProgramid(programid);
		programPage.setParentpageid(parentpageid);
		List<Program_page> programPageList=getSqlMapClientTemplate().queryForList("Program_page.getProgramPageByParentpageid", programPage);
		return programPageList;
	}
	@Transactional
	public List<Program_page> getProgramidByParentpageid(int parentpageid) {
		List<Program_page> programPageList=getSqlMapClientTemplate().queryForList("Program_page.getProgramidByParentpageid", parentpageid);
		return programPageList;
	}
	@Transactional
	public List<Program_page> getProgramPageById(int pageid) {
		List<Program_page> programPageList=getSqlMapClientTemplate().queryForList("Program_page.getProgramPageId", pageid);
		return programPageList;
	}
	@Transactional
	public List<Program_page> getProgramPageByParentpageid(int parentpageid) {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional
	public List<Program_page> getProgramPageByProgramid(Program_page programPage) {
		List<Program_page> programPageList=getSqlMapClientTemplate().queryForList("Program_page.getProgramPageByPageidAndProgramid", programPage);
		return programPageList;
	}
	@Transactional
	public List<Program_page> getProgramPageByIds(Program_page programPage) {
		List<Program_page> programPageList=getSqlMapClientTemplate().queryForList("Program_page.getProgramPageByIds", programPage);
		return programPageList;
	}
	@Transactional
	public List<Element> getElementClasstype(String classtype) {
		List<Element> elementlist=getSqlMapClientTemplate().queryForList("Element.getElementClasstype",classtype);
		return elementlist;
	}
	@Transactional
	public List<Operational> getOperationalContent(String content) {
		List<Operational> operationallist=getSqlMapClientTemplate().queryForList("Operational.getOperationalContent",content);
		return operationallist;
	}
	@Transactional
	public void updateProgram(Program program) {
		getSqlMapClientTemplate().update("program.updateProgram", program);
	}
	@Transactional
	public int insertProject(Project project) {
		int projectid=(Integer)getSqlMapClientTemplate().insert("Project.insertProject", project);
		return projectid;
	}
	@Transactional
	public List<Project_program> getProjectProgram() {
		List<Project_program> projectProgramList=getSqlMapClientTemplate().queryForList("Project_program.getProjectProgram",null);
		return projectProgramList;
	}
	@Transactional
	public List<Project> getProjects() {
		List<Project> projectList=getSqlMapClientTemplate().queryForList("Project.getProjects", null);
		return projectList;
	}
	@Transactional
	public void delProjectProgram(int programid) {
		getSqlMapClientTemplate().delete("Project_program.delProjectProgram", programid);
		
	}
	@Transactional
	public void delProject(int projectid) {
		getSqlMapClientTemplate().delete("Project.delProject", projectid);
		
	}
	@Transactional
	public List<Project_program> getProjectProgramByProjectid(int projectid) {
		List<Project_program> projectProgramList=getSqlMapClientTemplate().queryForList("Project_program.getProjectProgramByProjectid", projectid);
		return projectProgramList;
		
	}

	@Transactional
	public Project_program getProjectNameByProgramid(int programid) {
		Project_program project_program=(Project_program)getSqlMapClientTemplate().queryForObject("Project_program.getProjectNameByProgramid",programid);
		return project_program;
	}

}
