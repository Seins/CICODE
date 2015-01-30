package com.nd.citest.action;

import java.io.UnsupportedEncodingException;

import com.nd.citest.service.IProgramService;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateElementAction extends ActionSupport {

	/**
	 * 修改element信息
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IProgramService programService;
	private String elementid;
	private String text;
	private String classtype;
	private String elementindex;
	public IProgramService getProgramService() {
		return programService;
	}
	public void setProgramService(IProgramService programService) {
		this.programService = programService;
	}
	public String getElementid() {
		return elementid;
	}
	public void setElementid(String elementid) {
		this.elementid = elementid;
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
	public String getElementindex() {
		return elementindex;
	}
	public void setElementindex(String elementindex) {
		this.elementindex = elementindex;
	}
	
	public void UpdateElement() throws Exception{
		
		int id=Integer.parseInt(elementid);
		String elementText="";
		if(text!=null){
			elementText=new String(text.getBytes("ISO-8859-1"),"GBK");	
		}
		if(elementindex!=null){
			int index=Integer.parseInt(elementindex);
			programService.updateElementById(id, elementText, classtype, index);
		}else{
			programService.updateElementById(id, elementText, classtype, 0);
		}
		
	}

}
