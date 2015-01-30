package com.nd.citest.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nd.citest.model.Page;
import com.nd.citest.model.Program_page;
import com.nd.citest.service.IProgramService;
import com.opensymphony.xwork2.ActionSupport;

public class CheckPageNameAction extends ActionSupport {

	/**
	 * 验证pagename是否存在
	 */
	private static final long serialVersionUID = 1L;
	
	private String pagename;
	private int programid;
	public int getProgramid() {
		return programid;
	}
	public void setProgramid(int programid) {
		this.programid = programid;
	}

	private IProgramService programService;
	public String getPagename() {
		return pagename;
	}
	public void setPagename(String pagename) {
		this.pagename = pagename;
	}
	public IProgramService getProgramService() {
		return programService;
	}
	public void setProgramService(IProgramService programService) {
		this.programService = programService;
	}
	
	public void CheckPageName() throws Exception{
		HttpServletResponse response=ServletActionContext.getResponse();
		String result="";
		String name=new String(pagename.getBytes("ISO-8859-1"),"GBK");
		Page page=programService.getPageNameByName(name);
		if(page!=null){
			Program_page program_page=new Program_page();
			program_page=programService.getProgramPageById(programid, page.getPageid());
			if(program_page!=null){
				result="exist";
			}else{
				result="no";
			}
		}else{
			result="ok";
		}
		response.getWriter().write(result);
	}

}
