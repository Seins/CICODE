package com.nd.citest.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.nd.citest.model.Program;
import com.nd.citest.model.Program_page;
import com.nd.citest.service.IProgramService;
import com.opensymphony.xwork2.ActionSupport;

public class ShowTreeAction extends ActionSupport {

	/**
	 * 展示树形结构
	 */
	private static final long serialVersionUID = 1L;
	private Logger log4j = Logger.getLogger(ShowTreeAction.class);
	
	private IProgramService programService;
	private HttpServletRequest request;

	private String resultTree;
    private String programid;
	

	

	public String getProgramid() {
		return programid;
	}

	public void setProgramid(String programid) {
		this.programid = programid;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getResultTree() {
		return resultTree;
	}

	public void setResultTree(String resultTree) {
		this.resultTree = resultTree;
	}

	public IProgramService getProgramService() {
		return programService;
	}

	public void setProgramService(IProgramService programService) {
		this.programService = programService;
	}
	
	public String ShowTree() throws Exception{
		HttpServletResponse response=ServletActionContext.getResponse();
		List<Program_page> programPageList=programService.getPageByProgramid(Integer.parseInt(programid));
		Program program=programService.getProgramByProgramid(Integer.parseInt(programid));
		List<Program_page> programlist=new ArrayList<Program_page>();
		if(programPageList.size()==0){
			Program_page model=new Program_page();
			model.setDepth(0);
			model.setEndpage(0);
			model.setPageid(0);
			model.setPagename(program.getProgramname());
			model.setParentpageid(-1);
			model.setParentpagename("");
			model.setProgramid(program.getProgramid());
			model.setProgramname(program.getProgramname());
			programlist.add(model);
		}
		
		int num=0;
		for(int i=0;i<programPageList.size();i++){
			Program_page program_page=programPageList.get(i);
			Program_page newModel=new Program_page();
			if(program_page.getParentpageid()==0){
				num++;
				if(num==1){
					newModel.setDepth(program_page.getDepth());
					newModel.setEndpage(program_page.getEndpage());
					newModel.setPageid(0);
					newModel.setPagename(program_page.getProgramname());
					newModel.setParentpageid(-1);
					newModel.setParentpagename(program_page.getPagename());
					newModel.setProgramid(program_page.getProgramid());
					newModel.setProgramname(program_page.getProgramname());
					programlist.add(newModel);
				}
			}
			programlist.add(program_page);
		}

		JSONObject jsonObject=new JSONObject();
		jsonObject.put("programList", programlist);
		JSONArray jsonArray=new JSONArray();
		jsonArray.add(jsonObject);
		resultTree=jsonArray.toString();
		System.out.println(jsonArray.toString());
		return "success";
	}

}
