package com.nd.citest.action;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class ToTreePageAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String programid;
	
	public String getProgramid() {
		return programid;
	}

	public void setProgramid(String programid) {
		this.programid = programid;
	}

	public String toPage() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest();
		request.setAttribute("programid", programid);
		return "success";
	}

}
