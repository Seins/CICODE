package com.nd.citest.action;

import com.nd.citest.service.IProgramService;
import com.opensymphony.xwork2.ActionSupport;

public class DelPageAction extends ActionSupport {

	/**
	 * 删除page信息
	 */
	private static final long serialVersionUID = 1L;
	private String pageid;
	private String programid;
	public String getProgramid() {
		return programid;
	}
	public void setProgramid(String programid) {
		this.programid = programid;
	}

	private IProgramService programService;
	
	public String getPageid() {
		return pageid;
	}
	public void setPageid(String pageid) {
		this.pageid = pageid;
	}
	public IProgramService getProgramService() {
		return programService;
	}
	public void setProgramService(IProgramService programService) {
		this.programService = programService;
	}
	
	public void DelPage(){
		int id=Integer.parseInt(pageid);
		int programId=Integer.parseInt(programid);
		programService.delPageByPageid(id,programId);
		
	}

}
