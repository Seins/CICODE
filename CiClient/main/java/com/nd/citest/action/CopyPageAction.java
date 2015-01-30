package com.nd.citest.action;

import java.io.UnsupportedEncodingException;

import com.nd.citest.service.IProgramService;
import com.opensymphony.xwork2.ActionSupport;

public class CopyPageAction extends ActionSupport {

	/**
	 * 复用模板
	 */
	private static final long serialVersionUID = 1L;
	
	private IProgramService programService;
	private String pagename;
	private int programid;
	private int parentpageid;
	private String text;
	private String classtype;
	private String content;
	private String elementindex;
	private int num;
	public IProgramService getProgramService() {
		return programService;
	}
	public void setProgramService(IProgramService programService) {
		this.programService = programService;
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
	
	public String getElementindex() {
		return elementindex;
	}
	public void setElementindex(String elementindex) {
		this.elementindex = elementindex;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	public void CopyPage() throws Exception{
		String name=null;
		String operContent=null;
		String type=null;
		int index=0;
		if("".equals(elementindex)){
			index=0;
		}else{
			index=Integer.parseInt(elementindex);
		}
		if(!"".equals(pagename)){
			name=new String(pagename.getBytes("ISO-8859-1"),"GBK");
		}
		if(!"".equals(content)){
			operContent=new String(content.getBytes("ISO-8859-1"),"GBK");
		}
		if(!"".equals(pagename)){
			name=new String(pagename.getBytes("ISO-8859-1"),"GBK");
		}
		String elementText=null;
		if(!"".equals(text)){
			elementText=new String(text.getBytes("ISO-8859-1"),"GBK");
		}else{
			elementText=null;
		}
		if("".equals(classtype)){
			type=null;
		}else{
			type=new String(classtype.getBytes("ISO-8859-1"),"GBK");
		}
		programService.copyProgramPage(name, programid, parentpageid, elementText, operContent, type, index);
	}

}
