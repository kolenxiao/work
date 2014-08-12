package com.coship.depgm.job;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.coship.depgm.action.DepgmFilter;
import com.coship.depgm.service.TvSouService;

public class TvSouProgram implements Job{
	protected static Logger logger = Logger.getLogger(TvSouProgram.class);
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			TvSouService tvSouService = (TvSouService)DepgmFilter.getBean("tvSouService");
			tvSouService.catchProgram();
		} catch (Exception ex) {
			logger.error("获取搜视网节目单异常", ex);
		}
	}
}