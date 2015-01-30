package com.nd.citest.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import nd.com.ci.RealTestSuites;

import com.nd.citest.service.IProgramService;
import com.opensymphony.xwork2.ActionSupport;

public class RunCaseAction extends ActionSupport {
	
	/**
	 * 执行脚本
	 */
	private static final long serialVersionUID = 1L;
	private String scriptpath;
	private IProgramService programService;
	public String getScriptpath() {
		return scriptpath;
	}
	public void setScriptpath(String scriptpath) {
		this.scriptpath = scriptpath;
	}
	public IProgramService getProgramService() {
		return programService;
	}
	public void setProgramService(IProgramService programService) {
		this.programService = programService;
	}
	
	public void RunCase() throws Exception{
		HttpServletResponse response=ServletActionContext.getResponse();
		String path=new String(scriptpath.getBytes("ISO-8859-1"),"GBK");
		RealTestSuites test=new RealTestSuites();
		String reportPath=test.StartRun(path);
		response.getWriter().write(reportPath);
	}
}
