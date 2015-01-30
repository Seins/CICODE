package com.nd.citest.dao;

import java.util.List;

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

public interface IProgramDao {
	
	//根据programid查询program信息
	public Program getProgramByProgramid(int programid);
	//查询所有模块的信息
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
	public void updateProgramPage(Program_page program_page);
	//根据pagename查找page信息
	public Page getPageByPageid(String pagename);
	//修改子节点信息
	public void updateProgramPage(Program_page program_page,String[] pageids,int elementindex,String text,String content,String classtyp);
	//插入element信息
	public int insertElement(Element ele);
	//插入operational信息
	public int insertOperational(Operational oper);
	//插入Element_operational信息
	public int insertEleOperational(Element_operational ele_operational);
	//插入元素页面信息
	public int insertElement_page(Element_page ele_page);
	//插入元素指向页面
	public int insertEle_op_page(Ele_op_page ele_op_page);
	//根据页面查询元素操作信息
	public List<Element> getPageElementByPageid(int pageid);
	//插入页面元素操作信息
	public void insertElement_oper_page(String classtype,String elmentText,int elementindex,String content,int pageid,int resultid);
	//查询操作信息
	public Operational getOperationalByContent(String content);
	//修改element信息
	public void updateElementById(Element element);
	//修改操作信息
	public void updateOperationalById(Operational oper);
	//根据elementid查询元素信息
	public Element getElementById(int elementid);
	//删除元素信息
	public void delElementById(int id);
	//删除操作信息
	public void delOperational(int operationalid);
	//删除元素操作关联信息
	public void delEleOpertionalById(int elementid,int operationalid);
	//查询页面元素信息
	public void delElePageById(int elementid,int pageid);
	//删除页面元素操作信息
	public void delEleOperational(int pageid,int elementid,int operationalid);
	//
	public Ele_op_page getEleOpPageByPageid(int targetpageid);
	public Element_operational getEle_operational(int id);
	public void delEleOpPageByPageid(int targetpageid);
	public void delEleOpertional(int id);
	public Program_page getProgramPageById(Program_page program_page);
	//插入program信息
	public int insertProgram(Program program,int projectid);
	//删除模块信息
	public void delProgramByProgramid(int programid);
	//删除模块页面信息
	public List<Program_page> getProgramPageByProgramid(int programid);
	//
	public List<Element_page> getElement_pageByPageid(int pageid);
	public void delProgramById(int programid,int projectid);
	public void delPageByPageid(int pageid,int programid);
	public Program getProgramById(int programid);
	//查询pagename是否存在
	public Page getPageNameByName(String pagename);
	//复用模板
	public int copyProgramPage(String pagename,int programid,int parentpageid,String text,String content,String classtype,int elementindex);
	public List<Program_page> getProgramPageByParentpageid(int parentpageid);
	public List<Program_page> getProgramidByParentpageid(int parentpageid);
	//递归查询树子节点
	public List<Program_page> getProgramPageById(int pageid);
	//根据programid、pageid查询program_page信息
	public List<Program_page> getProgramPageByProgramid(Program_page program_page);
	public List<Program_page> getProgramPageByIds(Program_page program_page);
	//查询无重复classtype
	public List<Element> getElementClasstype(String classtype);
	//查询无重复content
	public List<Operational> getOperationalContent(String content);
	//修改program
	public void updateProgram(Program program);
	//插入项目信息
	public int insertProject(Project project);
	//查询项目模块信息
	public List<Project_program> getProjectProgram();
	//查询项目信息
	public List<Project> getProjects();
	//删除项目模块关联信息
	public void delProjectProgram(int programid);
	//删除项目信息
	public void delProject(int projectid);
	//根据projectid获取模块list
	public List<Project_program> getProjectProgramByProjectid(int projectid);
	//根据模块名称查找projectname
	public Project_program getProjectNameByProgramid(int programid);

}
