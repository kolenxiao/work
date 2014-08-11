package com.coship.sdp.sce.dp.job;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coship.sdp.sce.dp.comment.entity.AppCommentInfo;
import com.coship.sdp.sce.dp.comment.service.AppCommentService;
import com.coship.sdp.sce.dp.operation.entity.UserOperation;
import com.coship.sdp.sce.dp.operation.service.UserOperationService;
import com.coship.sdp.utils.DateUtil;
import com.coship.sdp.utils.Page;

@Service("logScheduleJob")
public class LogScheduleJob {
	private Logger logger = LoggerFactory.getLogger(LogScheduleJob.class);
	private Logger biLogger = LoggerFactory.getLogger("BI");
	
	@Autowired
	private UserOperationService userOperationService;
	
	@Autowired
	private AppCommentService appCommentService;
	
	private static String systemCode = "03";
	private String dateFormat = "yyyyMMddHHmmss";

	public void logForBI() {
		try {
			// 动态改变日志文件名
			changeFileName();
			// 打印有变动的用户操作信息
			printLogForUserOperation();
			// 打印有变动的用户评分信息
			printLogForAppComment();

		} catch (Exception e) {
			logger.error("写入日志异常：", e);
		}
	}
	
	
	private void changeFileName(){
		Appender appender = LogManager.getLoggerRepository().getLogger("BI").getAppender("A1");  
		if(appender instanceof FileAppender)     
		{   
		      FileAppender fileAppender = (FileAppender)appender; 
		      
		      String fileName = "";
		      int idx = fileAppender.getFile().indexOf("_");
		      if(idx >= 0){
		    	  fileName = fileAppender.getFile().substring(0, idx);
		      }else{
		    	  idx = fileAppender.getFile().indexOf(".");
		    	  fileName = fileAppender.getFile().substring(0, idx);
		      }
		      fileAppender.setFile( fileName + "_" + DateUtil.getNowTime("yyyyMMddHH") + ".log");   
		      fileAppender.activateOptions();   
		}
	}
	
	private void printLogForUserOperation(){
		logger.info("写入UserOperation日志开始------------");
		long beginTime = System.currentTimeMillis();
		
		String logCode = "001";
		List<Object> list = null;
		
		List<UserOperation> resultList = this.getUserOperationList();
		for (UserOperation userOperation : resultList) {
			list = new ArrayList<Object>();
			list.add(userOperation.getId());
			list.add(userOperation.getDeviceMac());
			list.add(userOperation.getOptType());
			list.add(DateUtil.dateToString(userOperation.getOptTime(), dateFormat));
			list.add(userOperation.getOptContentId());
			list.add(userOperation.getOptContent());
			
			this.printLog(logCode, list);
		}
		
		long endTime = System.currentTimeMillis();
		logger.info("写入UserOperation日志结束,共耗时：" + (endTime - beginTime) + "毫秒");
	}
	
	private void printLogForAppComment(){
		logger.info("写入AppComment日志开始------------");
		long beginTime = System.currentTimeMillis();
		
		String logCode = "002";
		List<Object> list = null;
		
		List<AppCommentInfo> resultList = this.getAppCommentInfoList();
		for (AppCommentInfo appCommentInfo : resultList) {
			list = new ArrayList<Object>();
			list.add(appCommentInfo.getId());
			list.add(appCommentInfo.getDpAppInfo().getId());
			list.add(appCommentInfo.getAppPackageName());
			list.add(appCommentInfo.getScore());
			list.add(appCommentInfo.getCommentContent());
			list.add(appCommentInfo.getCreateDate());
			list.add(appCommentInfo.getCommentUserId());
			list.add(appCommentInfo.getCommentUserName());

			this.printLog(logCode, list);
		}
		
		long endTime = System.currentTimeMillis();
		logger.info("写入AppComment日志结束,共耗时：" + (endTime - beginTime) + "毫秒");
	}
	
	
	private <T> void printLog(String logCode, List<T> list){
		String separator = "@_@";
		String optTime = DateUtil.dateToString(new Date(), dateFormat);

		StringBuffer sbf = new StringBuffer();
		sbf.append(optTime);
		sbf.append(separator);
		sbf.append(systemCode+logCode);

		for (T t : list) {
			sbf.append(separator);
			if(null == t){
				sbf.append("");
			}else{
				sbf.append(t);
			}
			
		}
		biLogger.info(sbf.toString());
	}

	private List<UserOperation> getUserOperationList() {
		Page<UserOperation> page = new Page<UserOperation>();
		page.setPageSize(Integer.MAX_VALUE);
		page.setCurrentPage(0);

		Date starDate = getBeginDate();
		Date endDate = getEndDate();
		try {
			String hql = "from UserOperation uo where uo.optTime >= ?"
					+ " and uo.optTime < ?"
					+ " order by uo.optTime";
			
			Object[] param = new Object[] { starDate,endDate };
			page = userOperationService.listUserOperation(page, hql, param);
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<UserOperation> list = page.getResultList();
		if (null != list) {
			return list;
		} else {
			return new ArrayList<UserOperation>();
		}
	}

	private List<AppCommentInfo> getAppCommentInfoList() {
		Page<AppCommentInfo> page = new Page<AppCommentInfo>();
		page.setPageSize(Integer.MAX_VALUE);
		page.setCurrentPage(0);

		Date starDate = getBeginDate();
		Date endDate = getEndDate();
		try {
			String hql = "from AppCommentInfo ac where ac.createDate >= ?"
					+ " and ac.createDate < ?"
					+ " order by ac.createDate";

			Object[] param = new Object[] { starDate,endDate };
			page = appCommentService.listAppCommentInfo(page, hql, param);
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<AppCommentInfo> list = page.getResultList();
		if (null != list) {
			return list;
		} else {
			return new ArrayList<AppCommentInfo>();
		}
	}


	private Date getBeginDate() {
		Date endDate = new Date();
		Date yesterday = getPrevDate(endDate, -1);
		Date beginDate = DateUtil.strToDate(DateUtil.dateToString(yesterday,
				DateUtil.C_DATA_PATTON_YYYYMMDD),
				DateUtil.C_DATA_PATTON_YYYYMMDD);
		return beginDate;
	}

	private Date getEndDate() {
		Date today = new Date();
		Date endDate = DateUtil.strToDate(
				DateUtil.dateToString(today, DateUtil.C_DATA_PATTON_YYYYMMDD),
				DateUtil.C_DATA_PATTON_YYYYMMDD);
		return endDate;
	}

	private Date getPrevDate(Date date, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, day);
		return calendar.getTime();
	}

}
