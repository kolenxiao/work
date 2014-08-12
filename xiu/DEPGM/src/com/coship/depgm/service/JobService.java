package com.coship.depgm.service;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;

import com.coship.depgm.common.DepgmConfig;
import com.coship.depgm.job.CaculateBtv;
import com.coship.depgm.job.ClearBtv;
import com.coship.depgm.job.ClearProgram;
import com.coship.depgm.job.TvSouProgram;

public class JobService {
	protected static Logger logger = Logger.getLogger(JobService.class);
	
	public static void startBtvJob() throws Exception{
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		
		//计算回看节目任务
		JobDetail btvDetail = new JobDetail("btvJob", "btvGroup", CaculateBtv.class);
		CronTrigger btvTrigger = new CronTrigger("btvTrigger", "btvGroup");
		btvTrigger.setCronExpression("0 0/" + DepgmConfig.getBtvInterval() / 60 + " * * * ?");
		scheduler.scheduleJob(btvDetail, btvTrigger);
		
		//清理回看节目任务
		JobDetail clearDetail = new JobDetail("clearBtvJob", "btvGroup", ClearBtv.class);
		CronTrigger clearTrigger = new CronTrigger("clearBtvTrigger", "btvGroup");
		clearTrigger.setCronExpression("0 0 0 * * ?");
		scheduler.scheduleJob(clearDetail, clearTrigger);
		
		scheduler.start();
		logger.info("启动回看计算任务");
	}
	
	
	public static void startTvSouJob() throws Exception{
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		
		//获取搜视网节目单任务
		JobDetail tvSouDetail = new JobDetail("tvSouJob", "tvSouGroup", TvSouProgram.class);
		CronTrigger tvSouTrigger = new CronTrigger("tvSouTrigger", "tvSouGroup");
		tvSouTrigger.setCronExpression("0 0/" + DepgmConfig.getProgramInterval() + " * * * ?");
		scheduler.scheduleJob(tvSouDetail, tvSouTrigger);
		
		//清理搜视网更新记录任务
		JobDetail clearDetail = new JobDetail("clearJob", "tvSouGroup", ClearProgram.class);
		CronTrigger clearTrigger = new CronTrigger("clearTrigger", "tvSouGroup");
		clearTrigger.setCronExpression("0 0 0/24 * * ?");
		scheduler.scheduleJob(clearDetail, clearTrigger);
		scheduler.start();
		
		
		logger.info("启动搜视网数据获取任务");
	}
}
