package com.nd.citest.model;

public class Program_page {
	
	private int id;
	private int endpage;
	private int programid;
	private int pageid;
	private int depth;
	private int parentpageid;
	private String programname;
	private String pagename;
	private String parentpagename;
	private int sumid;
	
	public int getSumid() {
		return sumid;
	}
	public void setSumid(int sumid) {
		this.sumid = sumid;
	}
	public String getProgramname() {
		return programname;
	}
	public void setProgramname(String programname) {
		this.programname = programname;
	}
	public String getPagename() {
		return pagename;
	}
	public void setPagename(String pagename) {
		this.pagename = pagename;
	}
	public String getParentpagename() {
		return parentpagename;
	}
	public void setParentpagename(String parentpagename) {
		this.parentpagename = parentpagename;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEndpage() {
		return endpage;
	}
	public void setEndpage(int endpage) {
		this.endpage = endpage;
	}
	public int getProgramid() {
		return programid;
	}
	public void setProgramid(int programid) {
		this.programid = programid;
	}
	public int getPageid() {
		return pageid;
	}
	public void setPageid(int pageid) {
		this.pageid = pageid;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getParentpageid() {
		return parentpageid;
	}
	public void setParentpageid(int parentpageid) {
		this.parentpageid = parentpageid;
	}
	

}
