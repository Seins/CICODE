package com.nd.citest.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nd.citest.model.Element;
import com.nd.citest.service.IProgramService;
import com.opensymphony.xwork2.ActionSupport;

public class ShowElementOperByPageidAction extends ActionSupport {

	/**
	 * 查询页面元素操作信息
	 */
	private static final long serialVersionUID = 1L;
	private IProgramService programService;
	private int pageid;
	private List<Element> elmentlist;
	public List<Element> getElmentlist() {
		return elmentlist;
	}
	public void setElmentlist(List<Element> elmentlist) {
		this.elmentlist = elmentlist;
	}
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
	public String  ShowElementOperByPageid() throws Exception{
		HttpServletResponse response=ServletActionContext.getResponse();
		HttpServletRequest request=ServletActionContext.getRequest();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		elmentlist=programService.getPageElementByPageid(pageid);
		request.setAttribute("pageid", pageid);
		request.setAttribute("elmentlist", elmentlist);
		return "success";
		
	}
	

}
