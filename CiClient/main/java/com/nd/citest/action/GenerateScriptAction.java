package com.nd.citest.action;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import nd.com.server.ScriptElement;

import com.nd.citest.model.Project_program;
import com.nd.citest.service.IProgramService;
import com.opensymphony.xwork2.ActionSupport;

public class GenerateScriptAction extends ActionSupport {

	/**
	 * 生成脚本
	 */
	private static final long serialVersionUID = 1L;
	private String programid;
	private IProgramService programService;
	
	public IProgramService getProgramService() {
		return programService;
	}

	public void setProgramService(IProgramService programService) {
		this.programService = programService;
	}

	public String getProgramid() {
		return programid;
	}

	public void setProgramid(String programid) {
		this.programid = programid;
	}

	public void GenerateScript() throws Exception{
		HttpServletResponse response=ServletActionContext.getResponse();
		int id=Integer.parseInt(programid);
		Project_program project_program=programService.getProjectNameByProgramid(id);
		String scriptpath=new ScriptElement().createProjectScript(project_program.getProjectname());
		response.getWriter().write(scriptpath);
	}

}
