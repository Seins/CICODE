package com.nd.citest.action;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.nd.citest.model.Element;
import com.nd.citest.model.Operational;
import com.nd.citest.service.IProgramService;
import com.opensymphony.xwork2.ActionSupport;

public class GetOperContentAction extends ActionSupport {

	/**
	 * 查询无重复操作
	 */
	private static final long serialVersionUID = 1L;
	
	private IProgramService programService;
	private String content;
	public IProgramService getProgramService() {
		return programService;
	}
	public void setProgramService(IProgramService programService) {
		this.programService = programService;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public void GetOperContent() throws Exception{
		HttpServletResponse response=ServletActionContext.getResponse();
		String oper=null;
		if(content!=null){
			oper=new String(content.getBytes("ISO-8859-1"),"GBK");
		}
		List<Operational>  operList=programService.getOperationalContent(oper);
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("operList", operList);
		JSONArray jsonArray=new JSONArray();
		jsonArray.add(jsonObject);
		System.out.println(jsonArray.toString());
		response.getWriter().write(jsonArray.toString());
	}

}
