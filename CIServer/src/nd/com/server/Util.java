package nd.com.server;

import nd.com.db.dao.IDataDao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Util {
	
	public static ApplicationContext factory = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	public static IDataDao dao = (IDataDao) factory.getBean("Dao");
	public static final String rootPath = "E:/CISCRIPT/";
	public static final String packageName = "com.dragon.android.pandaspace";
}
