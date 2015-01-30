package com.nd.citest.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.nd.citest.model.Element;
import com.nd.citest.service.IProgramService;
import com.opensymphony.xwork2.ActionSupport;

public class GetElementClasstypeAction extends ActionSupport {

	/**
	 * 查询无重复classtype
	 */
	private static final long serialVersionUID = 1L;
	
	private IProgramService programService;
	private String classtype;

	public String getClasstype() {
		return classtype;
	}

	public void setClasstype(String classtype) {
		this.classtype = classtype;
	}

	public IProgramService getProgramService() {
		return programService;
	}

	public void setProgramService(IProgramService programService) {
		this.programService = programService;
	}
	
	public void GetElementClasstype() throws Exception{
		HttpServletResponse response=ServletActionContext.getResponse();
		List<Element>  elementList=programService.getElementClasstype(classtype);
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("elementList", elementList);
		JSONArray jsonArray=new JSONArray();
		jsonArray.add(jsonObject);
		System.out.println(jsonArray.toString());
		response.getWriter().write(jsonArray.toString());
	}

}
