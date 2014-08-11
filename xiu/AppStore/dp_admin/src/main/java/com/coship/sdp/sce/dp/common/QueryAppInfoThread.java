package com.coship.sdp.sce.dp.common;

/**
 * 向appstore_service发送http请求，让appstore_service初始化存放到内存的应用信息
 */
public class QueryAppInfoThread extends Thread {

	@Override
	public void run() {

		// 初始化应用信息
		InitAppInfoInfo.executeInit(Constants.APPSTORE_DPPORTAL_IP, Constants.APPSTORE_DPPORTAL_PORT);
	}

}
