package com.nd.citest.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.nd.citest.service.IProgramService;
import com.opensymphony.xwork2.ActionSupport;

public class AddEleOperAction extends ActionSupport {

	/**
	 * 插入元素操作信息
	 */
	private static final long serialVersionUID = 1L;
	
	private IProgramService programService;
	private String classtype;
	private String elmentText;
	private String pageid;
	private String content;
	private String elementindex;
	private String resultid;
	public IProgramService getProgramService() {
		return programService;
	}
	public void setProgramService(IProgramService programService) {
		this.programService = programService;
	}
	public String getClasstype() {
		return classtype;
	}
	public void setClasstype(String classtype) {
		this.classtype = classtype;
	}
	public String getElmentText() {
		return elmentText;
	}
	public void setElmentText(String elmentText) {
		this.elmentText = elmentText;
	}
	public String getPageid() {
		return pageid;
	}
	public void setPageid(String pageid) {
		this.pageid = pageid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getElementindex() {
		return elementindex;
	}
	public void setElementindex(String elementindex) {
		this.elementindex = elementindex;
	}
	public String getResultid() {
		return resultid;
	}
	public void setResultid(String resultid) {
		this.resultid = resultid;
	}
	
	public String AddEleOper() throws Exception{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		int resultID=Integer.parseInt(resultid);
		int elementIndex=0;
		if("".equals(elementindex)){
			elementIndex=0;
		}else{
			elementIndex=Integer.parseInt(elementindex);
		}
		int pageID=Integer.parseInt(pageid);
		request.setAttribute("pageid", pageID);
		String text=null;
		if("".equals(elmentText)){
			text=null;
		}else{
			text=new String(elmentText.getBytes("ISO-8859-1"),"GBK");
		}
		if("".equals(classtype)){
			classtype=null;
		}
		programService.insertElement_oper_page(classtype, text, elementIndex, content, pageID, resultID);
		return "success";
		//request.getRequestDispatcher("ShowElementOperByPageid?pageid="+pageID).forward(request, response);
	}

}
