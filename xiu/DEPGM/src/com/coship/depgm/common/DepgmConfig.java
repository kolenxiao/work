package com.coship.depgm.common;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.coship.core.dal.sync.CacheClusterSync;

public class DepgmConfig {
	protected static Logger logger = Logger.getLogger(DepgmConfig.class);

	private static int btvInterval;
	private static int programInterval;
	
	private static int btvDays;

	private static String posterHost;
	private static int posterPort;
	private static String posterUser;
	private static String posterPwd;
	private static String posterDir;
	
	private static String mscpHost;
	private static int mscpPort;
	private static String mscpUser;
	private static String mscpPwd;
	private static String mscpDir;
	
	private static String defaultUser;
	private static String defaultPwd;

	private static String depgUrl;
	
	@SuppressWarnings("unchecked")
	public static void initConfig(String location) {
		Document document;
		try {
			document = new SAXReader().read(new File(location));
			Element root = document.getRootElement();
			for (Element serverElement : (List<Element>) root.element(
					"depg-servers").elements()) {
				CacheClusterSync.addNodeIp(serverElement.getTextTrim());
				logger.info("配置DEPG集群节点IP=" + serverElement.getTextTrim());
			}
			btvInterval = Integer.parseInt(getConfig(root, "btv-interval"));
			programInterval = Integer.parseInt(getConfig(root, "program-interval"));
			btvDays = Integer.parseInt(getConfig(root, "btv-days"));

			posterHost = getConfig(root, "poster-host");
			posterPort = Integer.parseInt(getConfig(root, "poster-port"));
			posterUser = getConfig(root, "poster-user");
			posterPwd = getConfig(root, "poster-pwd");
			posterDir = getConfig(root, "poster-dir");
			posterDir = getConfig(root, "poster-dir");
			
			mscpHost = getConfig(root, "mscp-host");
			mscpPort = Integer.parseInt(getConfig(root, "mscp-port"));
			mscpUser = getConfig(root, "mscp-user");
			mscpPwd = getConfig(root, "mscp-pwd");
			mscpDir = getConfig(root, "mscp-dir");
			
			defaultUser = getConfig(root, "depgm_default_user");
			defaultPwd = getConfig(root, "depgm_default_pwd");
			
			depgUrl = getConfig(root, "depg-url");
		} catch (DocumentException e) {
			logger.error("DEPGM配置异常:", e);
		}
	}

	public static int getBtvInterval() {
		return btvInterval;
	}

	public static int getBtvDays() {
		return btvDays;
	}

	public static String getPosterHost() {
		return posterHost;
	}

	public static int getPosterPort() {
		return posterPort;
	}

	public static String getPosterUser() {
		return posterUser;
	}

	public static String getPosterPwd() {
		return posterPwd;
	}

	public static String getMscpHost() {
		return mscpHost;
	}

	public static int getMscpPort() {
		return mscpPort;
	}

	public static String getMscpUser() {
		return mscpUser;
	}

	public static String getMscpPwd() {
		return mscpPwd;
	}

	public static String getMscpDir() {
		return mscpDir;
	}

	public static String getPosterDir() {
		return posterDir;
	}
	
	public static String getPosterHttpUrl() {
		return "http://" + posterHost + "/" + posterDir + "/";
	}

	private static String getConfig(Element root, String name) {
		String value = root.elementText(name);
		logger.info("配置" + name + "=" + value);
		return value;
	}

	public static String getDefaultUser() {
		return defaultUser;
	}

	public static String getDefaultPwd() {
		return defaultPwd;
	}

	public static String getDepgUrl() {
		return depgUrl;
	}

	public static int getProgramInterval() {
		return programInterval;
	}

	public static void setProgramInterval(int programInterval) {
		DepgmConfig.programInterval = programInterval;
	}
}