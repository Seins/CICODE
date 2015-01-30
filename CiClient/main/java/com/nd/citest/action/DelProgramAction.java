package com.nd.citest.action;

import com.nd.citest.service.IProgramService;
import com.opensymphony.xwork2.ActionSupport;

public class DelProgramAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int programid;
	private int projectid;
	public int getProjectid() {
		return projectid;
	}
	public void setProjectid(int projectid) {
		this.projectid = projectid;
	}

	private IProgramService programService;
	public int getProgramid() {
		return programid;
	}
	public void setProgramid(int programid) {
		this.programid = programid;
	}
	public IProgramService getProgramService() {
		return programService;
	}
	public void setProgramService(IProgramService programService) {
		this.programService = programService;
	}
	
	public String DelProgram(){
		programService.delProgramById(programid,projectid);
		return "success";
	}

}
