package com.nd.citest.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.nd.citest.service.IProgramService;
import com.opensymphony.xwork2.ActionSupport;

public class DelEleOperationalAction extends ActionSupport {

	/**
	 * 删除页面元素信息
	 */
	private static final long serialVersionUID = 1L;
	
	private IProgramService programService;
	private String pageid;
	private String elementid;
	private String operationalid;
	public IProgramService getProgramService() {
		return programService;
	}
	public void setProgramService(IProgramService programService) {
		this.programService = programService;
	}
	public String getPageid() {
		return pageid;
	}
	public void setPageid(String pageid) {
		this.pageid = pageid;
	}
	public String getElementid() {
		return elementid;
	}
	public void setElementid(String elementid) {
		this.elementid = elementid;
	}
	public String getOperationalid() {
		return operationalid;
	}
	public void setOperationalid(String operationalid) {
		this.operationalid = operationalid;
	}
	
	public String DelEleOperational(){
		HttpServletRequest request=ServletActionContext.getRequest();
		int pageID=Integer.parseInt(pageid);
		int elementID=Integer.parseInt(elementid);
		int operationalID=Integer.parseInt(operationalid);
		request.setAttribute("pageid", pageID);
		programService.delEleOperational(pageID, elementID, operationalID);
		return "success";
	}

}
