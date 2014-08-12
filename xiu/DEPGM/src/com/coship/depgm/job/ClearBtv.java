package com.coship.depgm.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.coship.depgm.action.DepgmFilter;
import com.coship.depgm.service.BtvService;

public class ClearBtv implements Job{
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		BtvService btvService = (BtvService)DepgmFilter.getBean("btvService");
		btvService.caculateDisableBtv();
	}
}