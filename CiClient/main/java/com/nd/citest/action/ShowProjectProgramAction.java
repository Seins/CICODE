package com.nd.citest.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nd.citest.model.Program;
import com.nd.citest.model.Project;
import com.nd.citest.model.Project_program;
import com.nd.citest.service.IProgramService;
import com.opensymphony.xwork2.ActionSupport;

public class ShowProjectProgramAction extends ActionSupport {

	/**
	 * 查询项目模块关联关系
	 */
	private static final long serialVersionUID = 1L;
	private IProgramService programService;

	public IProgramService getProgramService() {
		return programService;
	}

	public void setProgramService(IProgramService programService) {
		this.programService = programService;
	}
	public void ShowProjectProgram() throws Exception{
		HttpServletResponse response=ServletActionContext.getResponse();
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		List<Project_program> programList=programService.getProjectProgram();
		List<Project> projectList=programService.getProjects();
		if(projectList.size()>0){
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
	    	sb.append("<nodes>");
			for(int i=0;i<projectList.size();i++){
				Project project=projectList.get(i);
				sb.append("<node nodeId='"+project.getProjectid()+"' parentId='0'>"+project.getProjectname()+"</node>");
			}
			for(int j=0;j<programList.size();j++){
				Project_program project_program=programList.get(j);
				sb.append("<node nodeId='"+project_program.getProgramid()+"' parentId='"+project_program.getProjectid()+"'>"+project_program.getProgramname()+"</node>");
			}
			sb.append("</nodes>");
	    	System.out.println(sb);
	    	out.write(sb.toString());
		}else{
	    	sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
	    	sb.append("<nodes>");
	    	sb.append("</nodes>");
	    	System.out.println(sb);
	    	out.write(sb.toString());
	    }
	}
}
