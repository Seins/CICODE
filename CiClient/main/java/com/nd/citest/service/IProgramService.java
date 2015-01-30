package com.nd.citest.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.nd.citest.model.Element;
import com.nd.citest.model.Operational;
import com.nd.citest.model.Page;
import com.nd.citest.model.Program;
import com.nd.citest.model.Program_page;
import com.nd.citest.model.Project;
import com.nd.citest.model.Project_program;

public interface IProgramService {
	
	public List<Program> getProgramList();
	//查询program_page
	public List<Program_page> getPageByProgramid(int programid);
	//插入program_page信息
	public void insertProgramPage(Program_page program_page);
	//插入页面信息
	public int insertPage(Page page);
	//插入页面、模块信息
	public int insertProgramPage(String pagename,int programid,int parentpageid,String text,String content,String classtype,int elementindex);
	//根据programid、pageid查找depth
	public Program_page getDepthById(Program_page programpage);
	//修改page信息
	public void updataPage(Page page);
	//修改父节点
	public void updateProgramPage(Program_page programPage);
	public void updateProgramPage(Program_page programPage,String[] pageids,int elementindex,String text,String content,String classtype);
	//根据pagename查询page信息
	public Page getPageByPageid(String pagename);
	//根据programid查询program
	public Program getProgramByProgramid(int programid);
	//根据programid、pageid查询program_page信息
	public Program_page getProgramPageById(int programid,int pageid);
	//根据pageid查询页面元素操作信息
	public List<Element> getPageElementByPageid(int pageid);
	//插入页面元素操作信息
	public void insertElement_oper_page(String classtype, String elmentText,int elementindex, String content, int pageid,int resultid);
	//修改元素信息
	public void updateElementById(int elementid,String text,String classtype,int elementindex);
	//修改操作信息
	public void updateOperationalById(int operationalid,String content);
	//根据elementid查询元素信息
	public Element getElementById(int elementid);
	//删除页面元素操作信息
	public void delEleOperational(int pageid, int elementid, int operationalid);
	//插入program信息
	public int insertProgram(String programname,int projectid);
	//删除模块信息
	public void delProgramByProgramid(int programid);
	//查询program_page
	public List<Program_page> getProgramPageByProgramid(int programid);
	public void delProgramById(int programid,int projectid);
	public void delPageByPageid(int pageid,int programid);
	public Program getProgramById(int programid);
	//查询pagename是否存在
	public Page getPageNameByName(String pagename);
	//复用page模板
	public int copyProgramPage(String pagename, int programid,int parentpageid, String text, String content, String classtype,int elementindex);
	//查询无重复classtype
	public List<Element> getElementClasstype(String classtype);
	//查询无重复content
	public List<Operational> getOperationalContent(String content);
	//修改program信息
	public void updateProgram(Program program);
	//查询项目模块关联信息
	public List<Project_program> getProjectProgram();
	//查询项目信息
	public List<Project> getProjects();
	//插入project信息
	public int insertProject(Project project);
	//根据programid查找projectname
	public Project_program getProjectNameByProgramid(int programid);

}
