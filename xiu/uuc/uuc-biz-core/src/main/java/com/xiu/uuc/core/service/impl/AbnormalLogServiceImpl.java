package com.xiu.uuc.core.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.xiu.uuc.common.exception.BusinessException;
import com.xiu.uuc.common.util.BeanUtilEx;
import com.xiu.uuc.common.util.DateUtil;
import com.xiu.uuc.common.util.KeyNames;
import com.xiu.uuc.core.service.AbnormalLogService;
import com.xiu.uuc.core.util.CommonUtils;
import com.xiu.uuc.core.util.EmailClient;
import com.xiu.uuc.dal.dao.AbnormalLogDAO;
import com.xiu.uuc.dal.param.AbnormalLogParam;
import com.xiu.uuc.dal.param.AcctItemParam;
import com.xiu.uuc.dal.po.AbnormalLogPO;
import com.xiu.uuc.dal.po.AcctItemPO;
import com.xiu.uuc.facade.dto.AcctItemDTO;

public class AbnormalLogServiceImpl implements AbnormalLogService {
	
	@Override
	//1.账目平衡异动审计
	public void auditAccountBalanceEfficient() throws BusinessException {
		//1.1账目平衡审计的主业务逻辑处理
	    accountBalanceProcess();
	    //1.2扫描异动告警信息表，发送告警邮件(配置时间段内不平衡审计信息)
		processAlarm(null);
	}
	
	@Override
	//2.账目额度和频度审计
	public void auditAccountPayLimit(final AcctItemDTO acctItemDTO)throws BusinessException{
		executor.execute(new Runnable(){
            @Override
            public void run() {
            	long stime = System.currentTimeMillis();
        		logger.info("auditAccountPayLimit : executeTime ={}",
        				new Object[] { DateUtil.dateToDateString(new Date(),DateUtil.TIMEF_FORMAT) });
        		AcctItemPO acctItem = null;
    			try {
    				acctItem = new AcctItemPO();
					BeanUtilEx.copyProperties(acctItem, acctItemDTO);
					if(StringUtils.isBlank(acctItem.getAcctTypeCode())){
						//表示可提现或者不可提现
						acctItem.setAcctTypeCode("04"); 
					}
				} catch (Exception e) {
					logger.error("auditAccountPayLimit : BeanUtilEx.copyProperties error");
				}
            	
            	//2.1额度审计主业务逻辑处理
            	Long amoneyAbnormalId = auditAccountPayMoneyProcess(acctItem);
            	
            	//查询待频度审计账目列表
            	AcctItemParam acctItemParam = new AcctItemParam(payCountValidDateTime);
            	acctItemParam.setUserId(acctItemDTO.getUserId());
            	acctItemParam.setAcctTypeCode(acctItemDTO.getAcctTypeCode());
            	List<AcctItemPO> acctItemPOList = abnormalLogDAO.getlistAcctItemAudit(acctItemParam);
            	List<Long> abnormalIdList = new ArrayList<Long>();
        		for(AcctItemPO acctItemPO : acctItemPOList){
        			//2.2排除受限制用户
        			if(!checkAuditAccountPayCountProcess(acctItemPO)){
        				//2.3频度审计主业务逻辑处理
        				Long countAbnormalId = auditAccountPayCountProcess(acctItemPO);	
        				abnormalIdList.add(countAbnormalId);
        			}
        		}
        		//2.4扫描异动告警信息表，发送告警邮件(发送当前用户额度异常和频度异常审计信息)
        		AbnormalLogParam abnormalLogParam = new AbnormalLogParam();
        		abnormalIdList.add(amoneyAbnormalId);
            	abnormalLogParam.setAbnormalIdList(abnormalIdList);
            	abnormalLogParam.setUserId(acctItemDTO.getUserId());
			    processAlarm(abnormalLogParam);
			    logger.info("auditAccountPayLimit success: costTime={}ms",
			    		new Object[] { System.currentTimeMillis() - stime });
            }
		});
    }
	
	//1.1账目平衡审计的主业务逻辑处理
	//从数据库中一次性获取账目不平衡信息(更高效)
	@SuppressWarnings("unused")
	private void accountBalanceProcess(){
		Date balanceValidTime = getBalanceUpdateTimeValidTimeBeg();
		Date balanceExpireTime = getBalanceUpdateTimeExpireTimeEnd();
		AbnormalLogParam abnormalLogParam = new AbnormalLogParam(balanceValidTime,balanceExpireTime);
		List<AbnormalLogPO> abnormalLogPOList = abnormalLogDAO.getNotBalanceAlarmInfoList(abnormalLogParam);
		for(AbnormalLogPO abnormalLogPO:abnormalLogPOList){
			abnormalLogPO.setAbnormalType(KeyNames.AbnormalType.AUDIT_NOT_BALANCE.getKey());
			abnormalLogPO.setAbnormalDesc(KeyNames.AbnormalType.AUDIT_NOT_BALANCE.getValue());
			abnormalLogPO.setBegTime(balanceValidTime);
			abnormalLogPO.setEndTime(balanceExpireTime);
			abnormalLogPO.setSumChangeCount(null);
			abnormalLogDAO.insertAbnormalLog(abnormalLogPO);
		}
	}
	
	//2.1额度审计主业务逻辑处理
	private Long auditAccountPayMoneyProcess(final AcctItemPO acctItem) {
		Long amoneyAbnormalId = 0L;
		Long ioAmount = (acctItem != null && acctItem.getTotalAmount() != null) ? acctItem.getTotalAmount(): 0L;
		//当次进出账流水大于流水额度阀值
		if (Long.valueOf(ioAmount) >= Long.valueOf(KeyNames.pay_sum_ioAmount_max)) {
			Long userId = acctItem.getUserId();
			String acctTypeCode = acctItem.getAcctTypeCode();
			AbnormalLogPO abnormalLogPO = new AbnormalLogPO(
					KeyNames.AbnormalType.GREAT_PAY_IOAMOUNT_LIMIT.getKey(),
					KeyNames.AbnormalType.GREAT_PAY_IOAMOUNT_LIMIT.getValue(),
					null, null,
					userId, acctTypeCode, 
					acctItem.getTotalAmount(), 
					null,
					null);
			abnormalLogPO.setBegTime(new Date());
			abnormalLogPO.setEndTime(new Date());
			if (userId != null && StringUtils.isNotEmpty(acctTypeCode)) {
				AcctItemParam acctItemParam = new AcctItemParam(userId,acctTypeCode);
            	List<AcctItemPO> acctItemPOList = abnormalLogDAO.getlistAcctItemAudit(acctItemParam);
				if (acctItemPOList != null && acctItemPOList.size() > 0) {
					AcctItemPO findAcctItemPO = acctItemPOList.get(0);
					abnormalLogPO.setAcctId(findAcctItemPO.getAcctId());
					abnormalLogPO.setAcctItemId(findAcctItemPO.getAcctItemId());
					abnormalLogPO.setPetName(findAcctItemPO.getPetName());
					abnormalLogPO.setMobile(findAcctItemPO.getMobile());
					abnormalLogPO.setEmail(findAcctItemPO.getEmail());
					abnormalLogPO.setChannelId(findAcctItemPO.getChannelId().toString());
				}
			}
			if(StringUtils.isEmpty(acctTypeCode)){
				abnormalLogPO.setAcctTypeCode("04");
			}
			amoneyAbnormalId = abnormalLogDAO.insertAbnormalLog(abnormalLogPO);
		}
		return amoneyAbnormalId;
	}
	
	//2.3频度审计主业务逻辑处理
	private Long auditAccountPayCountProcess(AcctItemPO acctItemPO) {
		Long acctItemId = acctItemPO.getAcctItemId();
		String acctTypeCode = acctItemPO.getAcctTypeCode();
		AbnormalLogParam abnormalLogParam = new AbnormalLogParam(acctItemId,acctTypeCode,payCountValidDateTime);
		AbnormalLogPO findAbnormalLogPO = abnormalLogDAO.getChangeSumAudit(abnormalLogParam);
		Long sumChangeCount = (findAbnormalLogPO != null && findAbnormalLogPO.getSumChangeCount() != null) 
		                      ? findAbnormalLogPO.getSumChangeCount() : 0L;
		Long countAbnormalId = 0L;
		// 单个用户,当前账目类型在一段时间内支付次数>支付次数伐值
		if (Long.valueOf(sumChangeCount) >= Long.valueOf(KeyNames.pay_sum_count_max)) {
			AbnormalLogPO abnormalLogPO = new AbnormalLogPO(
					KeyNames.AbnormalType.GREATE_PAY_COUNT_LIMIT.getKey(),
					KeyNames.AbnormalType.GREATE_PAY_COUNT_LIMIT.getValue(),
					acctItemPO.getAcctId(), acctItemPO.getAcctItemId(),
					acctItemPO.getUserId(), acctItemPO.getAcctTypeCode(),
					null, sumChangeCount, 
					null);
			abnormalLogPO.setBegTime(DateUtil.getNextMinute(new Date(),-payCountValidDateTime));
			abnormalLogPO.setEndTime(new Date());
			abnormalLogPO.setPetName(acctItemPO.getPetName());
			abnormalLogPO.setMobile(acctItemPO.getMobile());
			abnormalLogPO.setEmail(acctItemPO.getEmail());
			abnormalLogPO.setChannelId(acctItemPO.getChannelId());
			countAbnormalId = abnormalLogDAO.insertAbnormalLog(abnormalLogPO);
		}
		return countAbnormalId;
	}
	
	//1.2扫描异动告警信息表，发送告警邮件(配置时间段内不平衡审计信息)
	//2.4扫描异动告警信息表，发送告警邮件(发送当前用户额度异常和频度异常审计信息)
	@SuppressWarnings("unchecked")
	private void processAlarm(AbnormalLogParam abnormalLogParam) {
		abnormalLogParam = (abnormalLogParam==null)? new AbnormalLogParam():abnormalLogParam;
		abnormalLogParam.setPayCountValidDateTime(payCountValidDateTime);
		List<AbnormalLogPO> abnormalLogPOList = abnormalLogDAO.listAbnormalLog(abnormalLogParam);
		if (abnormalLogPOList != null && abnormalLogPOList.size() > 0) {
			Boolean sendReslut = EmailClient.sendEmailList(abnormalLogPOList);
			if (Boolean.TRUE.equals(sendReslut)) {
				AbnormalLogPO abnormalLogPO = new AbnormalLogPO();
				Collection<Long> abnormalIdCoction = (Collection<Long>)CommonUtils.collectByPropertyName(abnormalLogPOList, "abnormalId");
		    	Long[] abnormalIdArray = (abnormalIdCoction!=null && abnormalIdCoction.size()>0 )
		    	                         ? abnormalIdCoction.toArray(new Long[abnormalIdCoction.size()]):new Long[0];
				List<Long> abnormalIdList = Arrays.asList(abnormalIdArray);
				abnormalLogPO.setAbnormalIdList(abnormalIdList);
				abnormalLogDAO.updateAbnormalLogPO(abnormalLogPO);
			}
		}
	}
	
	//2.2检查当前用户昵称，用户手机，用户邮箱是否在不受限制用户交易频率（指定时间段内交易次数）列表中，
	//若其中有一个在里面，返回true，否则返回fasle
	private boolean checkAuditAccountPayCountProcess(AcctItemPO acctItemPO) {
		String petName = acctItemPO.getPetName();
		String mobile = acctItemPO.getMobile();
		String email = acctItemPO.getEmail();
		if((KeyNames.petNameArray!=null && Arrays.asList(KeyNames.petNameArray).contains(petName))
		  ||(KeyNames.moblieArray!=null && Arrays.asList(KeyNames.moblieArray).contains(mobile))
		  ||(KeyNames.emailList!=null && Arrays.asList(KeyNames.emailList).contains(email))){
			return true;
		}
		return false;
	}
	
	// 账目平衡 拼接后的审计时间下限
	private Date getBalanceUpdateTimeValidTimeBeg() {
		return KeyNames.getBalanceUpdateTimeValidTimeBeg();
	}

	//账目平衡 拼接后的审计时间上限
	private Date getBalanceUpdateTimeExpireTimeEnd() {
		return KeyNames.getBalanceUpdateTimeExpireTimeEnd();
	}

	@Autowired
	public void setAbnormalLogDAO(AbnormalLogDAO abnormalLogDAO) {
		this.abnormalLogDAO = abnormalLogDAO;
	}
	
	private AbnormalLogDAO abnormalLogDAO;
	private static ExecutorService executor = Executors.newCachedThreadPool();
	private final Integer payCountValidDateTime = KeyNames.getPayCountUpdateTimeValidTimeBegDateTime();
	private static final Logger logger = LoggerFactory.getLogger(AbnormalLogServiceImpl.class);
}
