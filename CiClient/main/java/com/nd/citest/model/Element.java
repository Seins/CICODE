package com.nd.citest.model;

public class Element {
	
	private int elementid;
	private String text;
	private String classtype;
	private int elementindex;
	private int pageid;
	private String content;
	private String pagename;
	private int operationalid;
	private int resultid;
	public int getResultid() {
		return resultid;
	}
	public void setResultid(int resultid) {
		this.resultid = resultid;
	}
	public int getOperationalid() {
		return operationalid;
	}
	public void setOperationalid(int operationalid) {
		this.operationalid = operationalid;
	}
	public String getPagename() {
		return pagename;
	}
	public void setPagename(String pagename) {
		this.pagename = pagename;
	}
	public int getPageid() {
		return pageid;
	}
	public void setPageid(int pageid) {
		this.pageid = pageid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getElementid() {
		return elementid;
	}
	public void setElementid(int elementid) {
		this.elementid = elementid;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getClasstype() {
		return classtype;
	}
	public void setClasstype(String classtype) {
		this.classtype = classtype;
	}
	public int getElementindex() {
		return elementindex;
	}
	public void setElementindex(int elementindex) {
		this.elementindex = elementindex;
	}

}
