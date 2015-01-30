package com.nd.citest.action;

import java.io.UnsupportedEncodingException;

import com.nd.citest.service.IProgramService;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateOperationalAction extends ActionSupport {

	/**
	 * 修改操作信息
	 */
	private static final long serialVersionUID = 1L;
	private IProgramService programService;
	private String operationalid;
	private String content;
	public IProgramService getProgramService() {
		return programService;
	}
	public void setProgramService(IProgramService programService) {
		this.programService = programService;
	}
	public String getOperationalid() {
		return operationalid;
	}
	public void setOperationalid(String operationalid) {
		this.operationalid = operationalid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public void UpdateOperational() throws Exception{
		int id=Integer.parseInt(operationalid);
		String text=new String(content.getBytes("ISO-8859-1"),"GBK");
		programService.updateOperationalById(id, text);
	}

}
