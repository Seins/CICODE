package nd.com.server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import nd.com.db.model.Element;
import nd.com.db.model.ElementOperational;
import nd.com.db.model.ProgramPage;
import nd.com.server.python.PythonScriptTool;
import nd.com.server.robotium.CreateProject;
import nd.com.server.robotium.JavaScriptTool;

public class ScriptElement {
	FileUtil fileUtil = new FileUtil();
	
	public String createProjectScript(String projectName, int flag){
		List<String> modelList = Util.dao.getProgramNameForProjectName(projectName);
		String projectDirPath = Util.rootPath + projectName;
		fileUtil.createDir(projectDirPath);
		if (flag != 0) {
			new CreateProject().createTestProject(projectName, Util.packageName);
		}
		for (String modelName : modelList) {
			createModelScript(modelName, projectDirPath+"/", flag);
		}
		String zipPath = Util.rootPath + projectName+".zip";
		FileZip zc = new FileZip(zipPath);
		zc.compress(Util.rootPath, projectName);
		return zipPath;
	}

	/**
	 * 生成一个模块的测试脚本
	 * 
	 * @param modelName
	 */
	private boolean createModelScript(String modelName, String projectDirPath, int flag) {
		List<List<Element>> scriptList = new ArrayList<List<Element>>();
		scriptList = getModelScripts(modelName);
		if (flag == 0) {
			PythonScriptTool pst = new PythonScriptTool(modelName, projectDirPath);
			pst.generateScript(scriptList);
		}else {
			JavaScriptTool jst = new JavaScriptTool(modelName, projectDirPath);
			jst.generateScript(scriptList);
		}
		return true;
	}

	/**
	 * 获取指定模块的所有脚本
	 * 
	 * @param modelName
	 * @return
	 */
	private List<List<Element>> getModelScripts(String modelName) {
		List<List<Element>> scriptList = new ArrayList<List<Element>>();
		int programId = Util.dao.getProgramIdForName(modelName);

		int depth = Util.dao.getMaxDepth(programId);

		// 获取模块所有根页面
		for (int i = depth; i >= 0; i--) {
			// ProgramPage rootPage = pages.get(0);
			scriptList.addAll(getScriptForDepth(scriptList, programId, i));
		}

		return scriptList;
	}

	/**
	 * 获取指定深度回溯的脚本
	 * 
	 * @param programId
	 * @param depth
	 * @param rootPageId
	 * @return
	 */
	private List<List<Element>> getScriptForDepth(
			final List<List<Element>> scriptLists, int programId, int depth) {

		List<List<Element>> scriptList = new ArrayList<List<Element>>();
		List<ProgramPage> endPages = Util.dao
				.getPagesForDepth(programId, depth);

		if (depth == 0) {
			return getScriptList(endPages, scriptLists);
		}

		// 回溯页面，组合脚本
		for (ProgramPage endPage : endPages) {

			List<Element> endElements = searchElements(scriptLists, endPage);

			for (Element element : endElements) {
				List<Element> script = new ArrayList<Element>();
				script.add(element);
				script.addAll(scriptPath(endPage));
				scriptList.add(script);
			}
		}
		return scriptList;
	}

	/**
	 * 查找根页面上的所有元素并去掉已经使用过的元素
	 * @param scriptLists
	 * @param endPage
	 * @return
	 */
	private List<Element> searchElements(final List<List<Element>> scriptLists,
			ProgramPage endPage) {
		List<Integer> endElementIds = Util.dao.getElementForPage(endPage
				.getPageId());
		List<Element> endElements = Util.dao.getElements(endElementIds);
		List<Element> endElementsCopy = deepCopy(endElements);

		// 回溯操作路线
		for (List<Element> elems : scriptLists) {
			for (Element elem : endElementsCopy) {
				if (elems.contains(elem)) {
					endElements.remove(elem);
				}
			}
		}
		return endElements;
	}

	/**
	 * 获取根的脚本
	 * @param endPages
	 * @param scriptLists
	 * @return
	 */
	private List<List<Element>> getScriptList(List<ProgramPage> endPages,
			final List<List<Element>> scriptLists) {
		// TODO Auto-generated method stub
		List<List<Element>> scriptList = new ArrayList<List<Element>>();
		for (ProgramPage endPage : endPages) {
			List<Element> endElements = searchElements(scriptLists, endPage);
			for (Element element : endElements) {
				List<Element> script = new ArrayList<Element>();
				script.add(element);
				scriptList.add(script);
			}
		}
		return scriptList;
	}

	/**
	 * 获取能拼一个脚本的所有元素
	 * 
	 * @param endPage
	 * @param rootPageId
	 * @return
	 */
	private List<Element> scriptPath(ProgramPage endPage) {
		List<Element> script = new ArrayList<Element>();
		ElementOperational elemOperate = Util.dao.getElementOperational(endPage
				.getPageId());
		int pageId = Util.dao.getPageForElement(elemOperate.getElementId());
		Element element = Util.dao.getElement(elemOperate.getElementId());
		script.add(element);

		while (Util.dao.getDepthForPages(pageId) != 0) {
			elemOperate = Util.dao.getElementOperational(pageId);
			pageId = Util.dao.getPageForElement(elemOperate.getElementId());
			element = Util.dao.getElement(elemOperate.getElementId());
			script.add(element);
		}
		return script;
	}

	/**
	 * 通过value获取key
	 * 
	 * @param map
	 * @param value
	 * @return
	 */
//	private int getKey(Map<Integer, String> map, String value) {
//		for (int key : map.keySet()) {
//			String var = map.get(key);
//			System.out.println(var);
//			if (var.equals(value)) {
//				return key;
//			}
//		}
//		return -1;
//	}

	/**
	 * 深度复制
	 * @param src
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<Element> deepCopy(List<Element> src) {
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ObjectOutputStream out;
		try {
			out = new ObjectOutputStream(byteOut);
			out.writeObject(src);
			ByteArrayInputStream byteIn = new ByteArrayInputStream(
					byteOut.toByteArray());
			ObjectInputStream in = new ObjectInputStream(byteIn);
			List<Element> dest;
			dest = (List<Element>) in.readObject();
			return dest;
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
