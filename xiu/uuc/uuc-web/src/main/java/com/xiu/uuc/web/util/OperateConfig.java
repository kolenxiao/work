package com.xiu.uuc.web.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class OperateConfig {
	private static OperateConfig optConfig;
	private static Properties props;
	
	private OperateConfig() {
		init();
	}
	
	public static synchronized OperateConfig getInstance() {
		if (optConfig == null) {
			optConfig = new OperateConfig();
		}
		return optConfig;
	}
	
	public String getString(String key) {
		return props.getProperty(key);
	}
	
	private void init() {
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(
				"config.properties");
		props = new Properties();
		try {
			props.load(is);
		} catch (Exception e) {
			System.err.println("Can not read the properties file; "
					+ "Make sure config.properties is in the Classpath");
			return;
		}finally{
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String args[]) {
		String ss = OperateConfig.getInstance().getString("ssoURL");
		System.out.println(ss);
		
	}
}
