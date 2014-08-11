package com.coship.appstore.service;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coship.sdp.sce.dp.common.Constants;
import com.coship.sdp.sce.dp.common.InitAppInfoInfo;

/**
 * 缓存刷新定时器. 根据设定的时间频率刷新本地缓存.
 * 
 * @author xiezhengrong/907948
 * 
 * @since 2014年4月18日
 */
public class AppStoreCacheFlushTask {
	private static final Logger logger = LoggerFactory.getLogger(AppStoreCacheFlushTask.class);

    public void start() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                logger.info("------------executeInit begin-------------");
                long t1 = System.currentTimeMillis();

                // 初始化应用信息
                InitAppInfoInfo.executeInit(Constants.APPSTORE_DPPORTAL_IP, Constants.APPSTORE_DPPORTAL_PORT);

                long t2 = System.currentTimeMillis();
                logger.info("------------executeInit end, costTime={}ms-------------", (t2 - t1));
            }
        };

        Timer timer = new Timer("缓存刷新定时器", true);
        timer.scheduleAtFixedRate(task, new Date(),
                com.coship.appstore.service.common.Constants.INIT_MINUTE * 60 * 1000);
	}
}
