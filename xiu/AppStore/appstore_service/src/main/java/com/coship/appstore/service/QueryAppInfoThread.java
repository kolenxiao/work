package com.coship.appstore.service;

import com.coship.sdp.sce.dp.common.Constants;
import com.coship.sdp.sce.dp.common.InitAppInfoInfo;

public class QueryAppInfoThread extends Thread {

	@Override
	public void run() {
	    InitAppInfoInfo.executeInit(Constants.APPSTORE_DPPORTAL_IP, Constants.APPSTORE_DPPORTAL_PORT);
	}
}
