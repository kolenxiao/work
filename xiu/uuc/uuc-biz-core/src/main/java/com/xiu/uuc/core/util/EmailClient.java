package com.xiu.uuc.core.util;

import java.net.MalformedURLException;
import java.util.List;
import com.xiu.email.bean.EmailBean;
import com.xiu.email.hessian.EmailServiceFactory;
import com.xiu.email.hessian.IEmailHessianService;
import com.xiu.email.hessian.SendingResult;
import com.xiu.uuc.common.util.DateUtil;
import com.xiu.uuc.common.util.KeyNames;
import com.xiu.uuc.dal.po.AbnormalLogPO;
import com.xiu.uuc.facade.util.TypeEnum;

public class EmailClient {

	public static boolean sendEmailList(List<AbnormalLogPO> abnormalLogPOList){
		EmailBean emailBean=new EmailBean();
		emailBean.setCreator("uuc server system");
		emailBean.setReceiverMail(KeyNames.receiver_email_url);
		emailBean.setSenderName("虚拟账户异动审计");
		emailBean.setSubject("虚拟账户异动审计邮件告警");
		StringBuffer bodySb = buildBodyHead();
		buildBodyAllLine(bodySb,abnormalLogPOList);
		emailBean.setBody(bodySb.toString());
		boolean sendResult;
		try {
		 sendResult = sendEmail(KeyNames.email_server_url, emailBean);
		} catch (Exception e) {
			sendResult = false;
		}
		return sendResult;
	}
	
	public static boolean sendEmail(String emailServerUrl,EmailBean emailBean)throws MalformedURLException{
		IEmailHessianService email = EmailServiceFactory.getService(emailServerUrl);
		SendingResult result = email.sendEmail(emailBean);
		return Boolean.TRUE.equals(result.isSuccess()) ? true : false;
	}

	private static void buildBodyAllLine(StringBuffer bodySb,List<AbnormalLogPO> abnormalLogPOList) {
		if (abnormalLogPOList != null && abnormalLogPOList.size() > 0) {
			for (int i = 0; i < abnormalLogPOList.size(); i++) {
				AbnormalLogPO abnormalLogPO = abnormalLogPOList.get(i);
				buildBodyLine(bodySb, i, abnormalLogPO);
			}
			bodySb.append("</table>");
		}
	}

	private static void buildBodyLine(StringBuffer bodySb, int i,AbnormalLogPO abnormalLogPO) {
		bodySb.append("<tr>");
		bodySb.append("<td>").append(i+1).append("</td>");
		bodySb.append("<td><font color='red'>").append(abnormalLogPO.getAbnormalDesc()).append("</font></td>");
		bodySb.append("<td>").append(DateUtil.dateToDateString(abnormalLogPO.getCreateTime(),DateUtil.TIMEF_FORMAT)).append("</td>");
		buildBodySbCommon(bodySb, abnormalLogPO);
		bodySb.append("<td>审计周期:");
		bodySb.append("<font color='red'>").append(DateUtil.dateToDateString(abnormalLogPO.getBegTime(),DateUtil.TIMEF_FORMAT)).append("</font>");
		bodySb.append("--");
		bodySb.append("<font color='red'>").append(DateUtil.dateToDateString(abnormalLogPO.getEndTime(),DateUtil.TIMEF_FORMAT)).append("</font>");
		bodySb.append(" ");
		getBodySbByAbnormalTypeAndAcctTypeCode(bodySb, abnormalLogPO);
		bodySb.append("</td></tr>");
	}

	private static void getBodySbByAbnormalTypeAndAcctTypeCode(StringBuffer bodySb, AbnormalLogPO abnormalLogPO) {
		if(KeyNames.AbnormalType.AUDIT_NOT_BALANCE.getKey().equals(abnormalLogPO.getAbnormalType())){
			if(TypeEnum.AcctItemType.WITHDRAWAL_YES.getKey().equals(abnormalLogPO.getAcctTypeCode())
			  || TypeEnum.AcctItemType.WITHDRAWAL_NO.getKey().equals(abnormalLogPO.getAcctTypeCode())){
				bodySb.append("当前账户余额：");
				bodySb.append("<font color='red'>").append(abnormalLogPO.getLastIoAmount()/100.00).append("</font>").append("(元)");
				bodySb.append(" ");
				bodySb.append("流水累计余额：");
				bodySb.append("<font color='red'>").append(abnormalLogPO.getSumIoAmount()/100.00).append("</font>").append("(元)");
			}
			if(TypeEnum.AcctItemType.INTEGRAL.getKey().equals(abnormalLogPO.getAcctTypeCode())){
				bodySb.append("当前账户积分：");
				bodySb.append("<font color='red'>").append(abnormalLogPO.getLastIoAmount()).append("</font>").append("(个)");
				bodySb.append(" ");
				bodySb.append("流水累计积分：");
				bodySb.append("<font color='red'>").append(abnormalLogPO.getSumIoAmount()).append("</font>").append("(个)");
			}
		}
		if(KeyNames.AbnormalType.GREAT_PAY_IOAMOUNT_LIMIT.getKey().equals(abnormalLogPO.getAbnormalType())){
			if(TypeEnum.AcctItemType.WITHDRAWAL_YES.getKey().equals(abnormalLogPO.getAcctTypeCode())
			 || TypeEnum.AcctItemType.WITHDRAWAL_NO.getKey().equals(abnormalLogPO.getAcctTypeCode())
			 || "04".equals(abnormalLogPO.getAcctTypeCode())){
				bodySb.append("当前交易金额：");
				bodySb.append("<font color='red'>").append(abnormalLogPO.getSumIoAmount()/100.00).append("</font>").append("(元)");
				bodySb.append(" ");
				bodySb.append("支付额度阀值：");
				bodySb.append("<font color='red'>").append(KeyNames.pay_sum_ioAmount_max/100.00).append("</font>").append("(元)");
			}
			if(TypeEnum.AcctItemType.INTEGRAL.getKey().equals(abnormalLogPO.getAcctTypeCode())){
				bodySb.append("当前交易积分：");
				bodySb.append("<font color='red'>").append(abnormalLogPO.getSumIoAmount()).append("</font>").append("(个)");
				bodySb.append(" ");
				bodySb.append("支付积分阀值：");
				bodySb.append("<font color='red'>").append(KeyNames.pay_sum_ioAmount_max).append("</font>").append("(个)");
			}
		}
		if(KeyNames.AbnormalType.GREATE_PAY_COUNT_LIMIT.getKey().equals(abnormalLogPO.getAbnormalType())){
			bodySb.append("当前交易次数：");
			bodySb.append("<font color='red'>").append(abnormalLogPO.getSumChangeCount()).append("</font>").append("(次)");
			bodySb.append(" ");
			bodySb.append("支付次数阀值：");
			bodySb.append("<font color='red'>").append(KeyNames.pay_sum_count_max).append("</font>").append("(次)");
		}
	}

	private static void buildBodySbCommon(StringBuffer bodySb,AbnormalLogPO abnormalLogPO) {
		if(abnormalLogPO.getPetName()!=null){
			bodySb.append("<td>").append(abnormalLogPO.getPetName()).append("</td>");
		}else{
			bodySb.append("<td>").append("").append("</td>");
		}
		if(abnormalLogPO.getMobile()!=null){
			bodySb.append("<td>").append(abnormalLogPO.getMobile()).append("</td>");
		}else{
			bodySb.append("<td>").append("").append("</td>");
		}
		if(abnormalLogPO.getEmail()!=null){
			bodySb.append("<td>").append(abnormalLogPO.getEmail()).append("</td>");
		}else{
			bodySb.append("<td>").append("").append("</td>");
		}
		if(abnormalLogPO.getChannelId()!=null && abnormalLogPO.getChannelIdDesc()!=null){
			bodySb.append("<td>").append(abnormalLogPO.getChannelIdDesc()).append("</td>");
		}else{
			bodySb.append("<td>").append("").append("</td>");
		}
		if(abnormalLogPO.getUserId()!=null){
			bodySb.append("<td>").append(abnormalLogPO.getUserId()).append("</td>");
		}else{
			bodySb.append("<td>").append("").append("</td>");
		}
		if(abnormalLogPO.getAcctId()!=null){
			bodySb.append("<td>").append(abnormalLogPO.getAcctId()).append("</td>");
		}else{
			bodySb.append("<td>").append("").append("</td>");
		}
		if(abnormalLogPO.getAcctItemId()!=null){
			bodySb.append("<td>").append(abnormalLogPO.getAcctItemId()).append("</td>");
		}else{
			bodySb.append("<td>").append("").append("</td>");
		}
		if(abnormalLogPO.getAcctTypeCode()!=null && abnormalLogPO.getAcctTypeCodeDesc()!=null){
			bodySb.append("<td>").append(abnormalLogPO.getAcctTypeCodeDesc()).append("</td>");
		}else{
			bodySb.append("<td>").append("").append("</td>");
		}
	}

	private static StringBuffer buildBodyHead() {
		StringBuffer bodySb = new StringBuffer();
		bodySb.append("<table style='BORDER-COLLAPSE: collapse' borderColor=#000000 border=1>");
		bodySb.append("<caption>虚拟账户异动审计邮件告警</caption>");
		bodySb.append("<tr>");
		bodySb.append("<th>序号</th>");
		bodySb.append("<th>异动类型</th>");
		bodySb.append("<th>审计时间</th>");
		bodySb.append("<th>用户昵称</th>");
		bodySb.append("<th>用户手机</th>");
		bodySb.append("<th>用户邮箱</th>");
		bodySb.append("<th>用户渠道</th>");
		bodySb.append("<th>用户ID</th>");
		bodySb.append("<th>账户ID</th>");
		bodySb.append("<th>账目ID</th>");
		bodySb.append("<th>账目类型</th>");
		bodySb.append("<th>异动详情描述</th>");
		bodySb.append("</tr>");
		return bodySb;
	}
	
	public static void main(String[] args) throws MalformedURLException {
		//host 192.168.80.82  email.xiu.com
		//String url ="http://email.xiu.com:10517/hessian/emailHessianService"; 
		
		//host 10.0.0.67 email.xiu.com
		String url ="http://email.xiu.com:9080/hessian/emailHessianService";  
		
		IEmailHessianService email = EmailServiceFactory.getService(url);
		EmailBean eb=new EmailBean();
		eb.setCreator("andy");
		eb.setReceiverMail("andy.meng@xiu.com;menglei302@163.com");
		eb.setSenderName("走秀email测试");
		eb.setSubject("催款通知单");
		eb.setBody("快交钱，不然拉你进黑名单。");
		SendingResult result = email.sendEmail(eb);
	    System.out.println(result.getComment());
		System.out.println(result.isSuccess());
	}
}

