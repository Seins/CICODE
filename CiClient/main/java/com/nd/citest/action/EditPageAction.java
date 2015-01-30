package com.nd.citest.action;

import java.io.UnsupportedEncodingException;

import com.nd.citest.model.Page;
import com.nd.citest.model.Program;
import com.nd.citest.service.IProgramService;
import com.opensymphony.xwork2.ActionSupport;

public class EditPageAction extends ActionSupport {

	/**
	 * 编辑page信息
	 */
	private static final long serialVersionUID = 1L;
	
	private IProgramService programService;
	private int pageid;
	private String programid;

	public String getProgramid() {
		return programid;
	}
	public void setProgramid(String programid) {
		this.programid = programid;
	}

	private String pagename;
	public IProgramService getProgramService() {
		return programService;
	}
	public void setProgramService(IProgramService programService) {
		this.programService = programService;
	}
	public int getPageid() {
		return pageid;
	}
	public void setPageid(int pageid) {
		this.pageid = pageid;
	}
	public String getPagename() {
		return pagename;
	}
	public void setPagename(String pagename) {
		this.pagename = pagename;
	}
	
	public void updataPage() throws Exception{
		String name=null;
		if(!"".equals(pagename)){
			name=new String(pagename.getBytes("ISO-8859-1"),"GBK");
		}
		if(pageid==0){
			Program program=new Program();
			int id=Integer.parseInt(programid);
			program.setProgramid(id);
			program.setProgramname(name);
			programService.updateProgram(program);
		}else{
			Page page=new Page();
			page.setPageid(pageid);
			page.setPagename(name);
			programService.updataPage(page);
		}
	}

}
