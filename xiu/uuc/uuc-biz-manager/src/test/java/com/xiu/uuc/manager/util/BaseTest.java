package com.xiu.uuc.manager.util;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import org.junit.BeforeClass;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * Junit测试基类
 * @ClassName: BaseTest 
 * @author xiaoyingping
 * @date 2011-8-30 上午09:43:25
 */

@ContextConfiguration(locations = { "classpath:spring-uuc-manager.xml",
		                            "classpath:spring-uuc-service.xml",
		                            "classpath:spring-uuc-jdbc.xml",
		                            "classpath:spring-uuc-dao.xml" })
public abstract class BaseTest extends AbstractTransactionalJUnit4SpringContextTests {

	@BeforeClass
	public static void setUp() throws Exception {
		InputStream is = null;
		boolean propertiesFinded = false;
		try {
			long start = System.currentTimeMillis();
			System.out.println("正在加载配置文件...");
			//下面的方法可以加载config.properties文件
			Properties prop = System.getProperties();
			// File directory = new File(".");
			// File file = new
			// File(directory.getCanonicalFile().getParentFile().getCanonicalPath()+"/uuc-web/src/main/webconfig/uuc.properties");
			// prop.load(new FileInputStream(file));
			is = ClassLoader.getSystemResourceAsStream("config.properties");
			prop.load(is);
			if (!prop.isEmpty()) {
				propertiesFinded = true;
				Iterator<Object> it = prop.keySet().iterator();
				while (it.hasNext()) {
					String key = (String) it.next();
					System.setProperty(key, prop.getProperty(key));
					System.out.println(key + " = " + prop.getProperty(key));
				}
			}
			System.out.println("配置文件加载完毕,耗时："+ (System.currentTimeMillis() - start));
		} catch (Exception e) {
			throw new RuntimeException("解释 资源文件时出错");
		} finally {
			if (is != null) {
				is.close();
			}
		}
		if (!propertiesFinded) {
			throw new RuntimeException("未找到资源文件 ");
		}
	}

	protected boolean setProtected() {
		return false;
	}

}
