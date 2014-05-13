package com.xiu.uuc.core.schedule;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import com.xiu.uuc.common.util.DateUtil;
import com.xiu.uuc.core.service.AbnormalLogService;

public class AuditAccountBalancesSchedule implements  ApplicationContextAware {

	public void auditAccountBalances() {
		long stime = System.currentTimeMillis();
		logger.info("auditAccountBalances : executeTime ={}",new Object[] { DateUtil.dateToDateString(new Date(),DateUtil.TIMEF_FORMAT) });
    	abnormalLogService.auditAccountBalanceEfficient();
    	logger.info("auditAccountBalances success: costTime={}ms",new Object[] { System.currentTimeMillis() - stime });
	}
	
	@Override
	 public void setApplicationContext(ApplicationContext ctx) throws BeansException {
	    this.ctx = ctx;
	}
	
	@Autowired
	public void setAbnormalLogService(AbnormalLogService abnormalLogService) {
		this.abnormalLogService = abnormalLogService;
	}

	private static final Logger logger = LoggerFactory.getLogger(AuditAccountBalancesSchedule.class);
    private AbnormalLogService abnormalLogService;    
    @SuppressWarnings("unused")
	private ApplicationContext ctx;
}
