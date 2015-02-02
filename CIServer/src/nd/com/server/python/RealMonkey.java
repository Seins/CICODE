package nd.com.server.python;

import nd.com.db.model.Element;

public class RealMonkey {
	
	public RealMonkey(){}
	
	public String scriptInit(){
		StringBuilder sb = new StringBuilder();
		sb.append("# -*- coding: utf-8 -*-\n");
		sb.append("import os,sys,unittest\n");
		sb.append("from automatormonkey.monkeyrunnercore.MonkeyRunner import rMonkeyRunner\n");
		sb.append("from automatormonkey.monkeyrunnercore.info.Enum import *\n");
		sb.append("from automatormonkey.realunittest.RealJunit import *\n\n");
	
		sb.append("ROOTPATH = 'D:/reports/android-test'\n");
		sb.append("filename = __file__.split('\\\\')[-1].split('.')[0]\n");
		sb.append("package_name = __file__.replace('\\\\','/',10)\n");
		sb.append("modelname = '%s/html/%s'%(ROOTPATH, filename)\n");
		sb.append("if os.path.exists(modelname)==False:\n");
		sb.append("    os.mkdir(modelname)\n");

		return sb.toString();
	}
	
	public String startApp(String packageName, String activityName){
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("device.clearAppData('%s')\n", packageName));
		sb.append(String.format("device.startActivity('%s/%s')\n", packageName, activityName));
		sb.append("device.sleep(2.0)\n");
		return sb.toString();
	}
	
	public String click(Element element){
		String code = "";
		if (element.getText() != null) {
			return clickCode("UIELEMENT.TEXT", element.getText(), element.getElementIndex());
		}
		switch (element.getClassType()) {
		case "sid":
			code = clickCode("UIELEMENT.SID", element.getSourceId(), element.getElementIndex());
			break;
		default:
			code = clickCode("UIELEMENT.CLASSNAME", "android.widget."+element.getClassType(), element.getElementIndex());
			break;
		}
		return code;
	}
	
	public String sleep(String sleepTime){
		return String.format("device.sleep(%s)\n", sleepTime);
	}
	
	private String clickCode(String type, String value, int match){
		String code = String.format("device.click(%s, \"%s\", %d)\n", type, value, match);
		return code;
	}
}
