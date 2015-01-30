package com.nd.citest.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nd.citest.model.Program;
import com.nd.citest.service.IProgramService;
import com.opensymphony.xwork2.ActionSupport;

public class ShowProgramAction extends ActionSupport {

	/**
	 * 展示左边模块树
	 */
	private static final long serialVersionUID = 1L;
	
	private IProgramService programService;

	public IProgramService getProgramService() {
		return programService;
	}

	public void setProgramService(IProgramService programService) {
		this.programService = programService;
	}
	public void ShowProgram() throws Exception{
		HttpServletResponse response=ServletActionContext.getResponse();
		PrintWriter out = response.getWriter();
		StringBuffer sb = new StringBuffer();
		List<Program> programList=programService.getProgramList();
		if(programList!=null&&programList.size()>0){
	    	sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
	    	sb.append("<nodes>");
	    	for(int i=0;i<programList.size();i++){
	    		Program program = programList.get(i);
	    		sb.append("<node nodeId='"+program.getProgramid()+"'>"+program.getProgramname()+"</node>");	    	}
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
