package com.nd.citest.action;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.nd.citest.model.Project;
import com.nd.citest.service.IProgramService;
import com.opensymphony.xwork2.ActionSupport;

public class AddProgramAction extends ActionSupport {

	/**
	 * 添加program信息
	 */
	private static final long serialVersionUID = 1L;
	
	private IProgramService programService;
	private String programname;
	private String projectid;
	public String getProjectid() {
		return projectid;
	}
	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}
	public IProgramService getProgramService() {
		return programService;
	}
	public void setProgramService(IProgramService programService) {
		this.programService = programService;
	}
	public String getProgramname() {
		return programname;
	}
	public void setProgramname(String programname) {
		this.programname = programname;
	}
	
	
	public String AddProgram() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest();
		String name=new String(programname.getBytes("ISO-8859-1"),"GBK");
		int projectId=Integer.parseInt(projectid);
		if(projectId==0){
			Project project=new Project();
			project.setProjectname(name);
			programService.insertProject(project);
		}else{
			int programid=programService.insertProgram(name,projectId);
			request.setAttribute("programid", programid);
		}
		return "success";
	}

}
