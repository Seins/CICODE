package com.nd.citest.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.nd.citest.service.IProgramService;
import com.opensymphony.xwork2.ActionSupport;

public class AddPageAction extends ActionSupport {

	/**
	 * 添加页面节点
	 */
	private static final long serialVersionUID = 1L;

	private Logger log4j = Logger.getLogger(ShowTreeAction.class);
		
		public IProgramService getProgramService() {
		return programService;
	}
	
	public void setProgramService(IProgramService programService) {
		this.programService = programService;
	}

	private IProgramService programService;
	private String pagename;
	private int programid;
	private int parentpageid;
	private String text;
	private String classtype;
	private String content;
	private int elementindex;
	private int num;
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getClasstype() {
		return classtype;
	}

	public void setClasstype(String classtype) {
		this.classtype = classtype;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getElementindex() {
		return elementindex;
	}

	public void setElementindex(int elementindex) {
		this.elementindex = elementindex;
	}

	public String getPagename() {
		return pagename;
	}

	public void setPagename(String pagename) {
		this.pagename = pagename;
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

	public void AddPage() throws IOException{
		HttpServletResponse response=ServletActionContext.getResponse();
		String name=null;
		if(!"".equals(pagename)){
			name=new String(pagename.getBytes("ISO-8859-1"),"GBK");
		}
		String elementText=null;
		if(!"".equals(text)){
			elementText=new String(text.getBytes("ISO-8859-1"),"GBK");
		}else{
			text=null;
		}
		if("".equals(classtype)){
			classtype=null;
		}
		int pageid=programService.insertProgramPage(name, programid, parentpageid,elementText,content,classtype,elementindex);
		response.getWriter().write(String.valueOf(pageid));
	}

}
