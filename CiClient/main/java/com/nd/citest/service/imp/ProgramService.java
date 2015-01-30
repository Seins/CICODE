package com.nd.citest.service.imp;

import java.util.List;

import com.nd.citest.dao.IProgramDao;
import com.nd.citest.model.Element;
import com.nd.citest.model.Operational;
import com.nd.citest.model.Page;
import com.nd.citest.model.Program;
import com.nd.citest.model.Program_page;
import com.nd.citest.model.Project;
import com.nd.citest.model.Project_program;
import com.nd.citest.service.IProgramService;

public class ProgramService  implements IProgramService {
	
	private IProgramDao programDao;

	public Program_page getDepthById(Program_page programpage) {
		Program_page program_page=programDao.getDepthById(programpage);
		return program_page;
	}

	public List<Program_page> getPageByProgramid(int programid) {
		List<Program_page> programpagelist=programDao.getPageByProgramid(programid);
		return programpagelist;
	}

	public List<Program> getProgramList() {
		List<Program> programlist=programDao.getProgramList();
		return programlist;
	}

	public int insertPage(Page page) {
		int pageid=programDao.insertPage(page);
		return pageid;
	}

	public IProgramDao getProgramDao() {
		return programDao;
	}

	public void setProgramDao(IProgramDao programDao) {
		this.programDao = programDao;
	}

	public void insertProgramPage(Program_page programPage) {
		programDao.insertProgramPage(programPage);
		
	}

	public int insertProgramPage(String pagename,int programid,int parentpageid,String text,String content,String classtype,int elementindex) {
		int pageid=programDao.insertProgramPage(pagename, programid, parentpageid,text,content,classtype,elementindex);
		return pageid;
	}

	public void updataPage(Page page) {
		programDao.updataPage(page);
		
	}

	public void updateProgramPage(Program_page programPage) {
		programDao.updateProgramPage(programPage);
		
	}
	public void updateProgramPage(Program_page programPage,String[] pageids,int elementindex,String text,String content,String classtyp) {
		programDao.updateProgramPage(programPage,pageids,elementindex,text,content,classtyp);
		
	}

	public Page getPageByPageid(String pagename) {
		Page page=programDao.getPageByPageid(pagename);
		return page;
	}

	public Program getProgramByProgramid(int programid) {
		Program program=programDao.getProgramByProgramid(programid);
		return program;
	}

	public List<Element> getPageElementByPageid(int pageid) {
		List<Element> elements=programDao.getPageElementByPageid(pageid);
		return elements;
	}

	public void insertElement_oper_page(String classtype, String elmentText,
			int elementindex, String content, int pageid, int resultid) {
		programDao.insertElement_oper_page(classtype, elmentText, elementindex, content, pageid, resultid);
	}

	public void updateElementById(int elementid, String text,
			String classtype, int elementindex) {
		Element element=new Element();
		element.setClasstype(classtype);
		element.setElementid(elementid);
		Element model=this.getElementById(elementid);
		if(elementindex!=0){
			element.setElementindex(elementindex);
		}else{
			element.setElementindex(model.getElementindex());
		}
		element.setText(text);
		programDao.updateElementById(element);
	}

	public void updateOperationalById(int operationalid, String content) {
		Operational oper=new Operational();
		oper.setContent(content);
		oper.setOperationalid(operationalid);
		programDao.updateOperationalById(oper);
		
	}

	public Element getElementById(int elementid) {
		Element element=programDao.getElementById(elementid);
		return element;
	}

	public void delEleOperational(int pageid, int elementid, int operationalid) {
		programDao.delEleOperational(pageid, elementid, operationalid);
		
	}

	public int insertProgram(String programname,int projectid) {
		Program program=new Program();
		program.setProgramname(programname);
		int id=programDao.insertProgram(program,projectid);
		return id;
	}

	public void delProgramByProgramid(int programid) {
		
		programDao.delProgramByProgramid(programid);
		
	}

	public List<Program_page> getProgramPageByProgramid(int programid) {
		List<Program_page> programPageList=programDao.getPageByProgramid(programid);
		return programPageList;
	}

	public void delProgramById(int programid,int projectid) {
		programDao.delProgramById(programid,projectid);
		
	}

	public void delPageByPageid(int pageid,int programid) {
		programDao.delPageByPageid(pageid,programid);
		
	}

	public Program getProgramById(int programid) {
		Program program=programDao.getProgramById(programid);
		return program;
	}

	public Page getPageNameByName(String pagename) {
		Page page=programDao.getPageNameByName(pagename);
		return page;
	}

	public int copyProgramPage(String pagename, int programid,int parentpageid, String text, String content, String classtype,int elementindex) {
		programDao.copyProgramPage(pagename, programid, parentpageid, text, content, classtype, elementindex);
		return 0;
	}

	public Program_page getProgramPageById(int programid, int pageid) {
		Program_page model=new Program_page();
		model.setPageid(pageid);
		model.setProgramid(programid);
		Program_page program_page=programDao.getProgramPageById(model);
		return program_page;
	}

	public List<Element> getElementClasstype(String classtype) {
		List<Element> elementList=programDao.getElementClasstype(classtype);
		return elementList;
	}

	public List<Operational> getOperationalContent(String content) {
		List<Operational> operationalList=programDao.getOperationalContent(content);
		return operationalList;
	}

	public void updateProgram(Program program) {
		programDao.updateProgram(program);
		
	}

	public List<Project_program> getProjectProgram() {
		List<Project_program> projectProgramList=programDao.getProjectProgram();
		return projectProgramList;
	}

	public List<Project> getProjects() {
		List<Project> projectList=programDao.getProjects();
		return projectList;
	}

	public int insertProject(Project project) {
		int projectid=programDao.insertProject(project);
		return projectid;
	}

	@Override
	public Project_program getProjectNameByProgramid(int programid) {
		Project_program project_program=programDao.getProjectNameByProgramid(programid);
		return project_program;
	}


}
