package com.nd.citest.action;

import java.io.UnsupportedEncodingException;

import com.nd.citest.model.Page;
import com.nd.citest.model.Program_page;
import com.nd.citest.service.IProgramService;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateProgramPageAction extends ActionSupport {

	/**
	 * 拖动节点之后修改父节点
	 */
	private static final long serialVersionUID = 1L;
	
	private IProgramService programService;
	private int pageid;
	private int programid;
	private int parentpageid;
	private String parentpagename;
	private String pagename;
	private String childs;
	private String classtype;
	private String elementindex;
	private String elementText;
	private String operational;
	public String getClasstype() {
		return classtype;
	}
	public void setClasstype(String classtype) {
		this.classtype = classtype;
	}
	public String getElementindex() {
		return elementindex;
	}
	public void setElementindex(String elementindex) {
		this.elementindex = elementindex;
	}
	public String getElementText() {
		return elementText;
	}
	public void setElementText(String elementText) {
		this.elementText = elementText;
	}
	public String getOperational() {
		return operational;
	}
	public void setOperational(String operational) {
		this.operational = operational;
	}
	public String getPagename() {
		return pagename;
	}
	public void setPagename(String pagename) {
		this.pagename = pagename;
	}
	public String getChilds() {
		return childs;
	}
	public void setChilds(String childs) {
		this.childs = childs;
	}
	public String getParentpagename() {
		return parentpagename;
	}
	public void setParentpagename(String parentpagename) {
		this.parentpagename = parentpagename;
	}
	public IProgramService getProgramService() {
		return programService;
	}
	public void setProgramService(IProgramService programService) {
		this.programService = programService;
	}
	public int getPageid() {
		return pageid;
	}
	public void setPageid(int pageid) {
		this.pageid = pageid;
	}
	public int getProgramid() {
		return programid;
	}
	public void setProgramid(int programid) {
		this.programid = programid;
	}
	public int getParentpageid() {
		return parentpageid;
	}
	public void setParentpageid(int parentpageid) {
		this.parentpageid = parentpageid;
	}
	
	public void updateProgramPage() throws Exception{
		String parentname=new String(parentpagename.getBytes("ISO-8859-1"),"GBK");
		String name=new String(pagename.getBytes("ISO-8859-1"),"GBK");
		String text=new String(elementText.getBytes("ISO-8859-1"),"GBK");
		String content=new String(operational.getBytes("ISO-8859-1"),"GBK");
		int index=Integer.parseInt(elementindex);
		Page parentpage=programService.getPageByPageid(parentname);
		Page page=programService.getPageByPageid(name);
		Program_page program_page=new Program_page();
		if(parentpage==null){
			program_page.setDepth(0);
			program_page.setParentpageid(0);
		}else{
			Program_page programPage=new Program_page();
			programPage.setProgramid(programid);
			programPage.setPageid(parentpage.getPageid());
			Program_page model=programService.getDepthById(programPage);
			program_page.setDepth(model.getDepth()+1);
			program_page.setParentpageid(parentpage.getPageid());	
		}
		program_page.setPageid(page.getPageid());
		program_page.setProgramid(programid);
		String[] pageids=childs.split(",");
		programService.updateProgramPage(program_page,pageids,index,text,content,classtype);
		
		
		
//		Program_page program_page=new Program_page();
//		if(page==null){
//			program_page.setDepth(0);
//			program_page.setParentpageid(0);
//		}else{
//			Program_page programPage=new Program_page();
//			programPage.setProgramid(programid);
//			programPage.setPageid(pageid);
//			Program_page model=programService.getDepthById(programPage);
//			program_page.setDepth(model.getDepth()+1);
//			program_page.setParentpageid(page.getPageid());
//		}
//		program_page.setPageid(pageid);
//		program_page.setProgramid(programid);
//		programService.updateProgramPage(program_page);
	}

}
