package com.xiu.uuc.web.action;

import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.caucho.hessian.client.HessianProxyFactory;
import com.opensymphony.xwork2.ActionSupport;
import com.xiu.uuc.facade.StationLettersFacade;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.dto.StationLettersDTO;
import com.xiu.uuc.web.util.OperateConfig;

/**
 * 站内信
 * 
 * @ClassName: StationLettersAction 
 * @author liuzhiyong
 * @date 2011-8-17 下午02:53:35 
 *
 */
public class StationLettersAction extends ActionSupport implements ParameterAware,ServletRequestAware {
	private static final long serialVersionUID = 4296924341522894823L;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	private String uucStationLettersURL = OperateConfig.getInstance().getString("uucStationLettersURL");
	
	private Map<String,String[]> parammeters;
	
	private StationLettersFacade statLettersFacade;
	
	private HttpServletRequest request;
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setParameters(Map<String, String[]> parameters) {
		this.parammeters = parameters;
		
	}

	public Map<String, String[]> getParammeters() {
		return parammeters;
	}

	public StationLettersFacade getStatLettersFacade(){
		HessianProxyFactory factory = new HessianProxyFactory();
		try {
			statLettersFacade = (StationLettersFacade) factory.
					create(StationLettersFacade.class, uucStationLettersURL);
			return statLettersFacade;
		} catch (MalformedURLException e) {	
			e.printStackTrace();
			return null;
		}
	} 
	
	public String sendLetters(){
		
		if(null == parammeters){
			return "error";
		}
		StationLettersDTO stationLettersDTO = new StationLettersDTO();
		String sender = parammeters.get("sendName")[0];
		String addressee = parammeters.get("revName")[0];
		
		// 发送时间
		String sendTime = parammeters.get("sendTime")[0];
		String df =  "yyyy-MM-dd hh:mm:ss";

		SimpleDateFormat sdf = new SimpleDateFormat(df);
		Date time = null;
		try {
			time = sdf.parse(sendTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String customerId = parammeters.get("customerId")[0];
		String title = parammeters.get("title")[0];
		String customerName = parammeters.get("customerName")[0];
		String telphone = parammeters.get("telphone")[0];
		String email = parammeters.get("email")[0];
		String content = parammeters.get("txt")[0];
		
		logger.debug("method:StationLettersAction.sendLetters(),parameter:sender=" + sender);
		logger.debug("method:StationLettersAction.sendLetters(),parameter:addressee=" + addressee);
		logger.debug("method:StationLettersAction.sendLetters(),parameter:title=" + title);
		logger.debug("method:StationLettersAction.sendLetters(),parameter:content=" + content);

		stationLettersDTO.setAddressee(addressee);
		stationLettersDTO.setSender(sender);
		stationLettersDTO.setTitle(title);
		stationLettersDTO.setContent(content);
		stationLettersDTO.setSendTime(time);
		stationLettersDTO.setCustomerId(customerId);
		stationLettersDTO.setCustomerName(customerName);
		stationLettersDTO.setEmail(email);
		stationLettersDTO.setTelphone(telphone);
		/**
		 * 发送信件
		 */
		Result result = getStatLettersFacade().sendLetters(stationLettersDTO);
		
		logger.debug("method:StationLettersAction.sendLetters(),result:success=" + result.getSuccess());
		logger.debug("method:StationLettersAction.sendLetters(),result:errorCode" + result.getErrorCode());
		
		if("1".equals(result.getSuccess())){
			return "success";
		}
		return "error";
	}
	
	public String deleteLetters(){
		/**
		 * 删除信件测试
		 */
		String customerId = parammeters.get("customerId")[0];
		String lettersId = parammeters.get("lettersId")[0];

		Result result = this.getStatLettersFacade().deleteLetters(customerId, lettersId);

		if("1".equals(result.getSuccess())){
			return "success";
		}
		return "error";
	}
	
	public String findLetters(){
		/**
		 * 查询某封信件
		 */
		String lettersId = parammeters.get("lettersId")[0];
		
		Result result = this.getStatLettersFacade().findLetters(lettersId);

		
		StationLettersDTO statLettersDTO = (StationLettersDTO)result.getData();
		
		request.setAttribute("statLettersDTO", statLettersDTO);
		
		if(null != statLettersDTO){
			return "success";
		}	
		return "error";
	}
	
	public String lettersList(){
		/**
		 * 所有信件列表测试
		 */
		String customerId = parammeters.get("customerId")[0];
		String currentPage = parammeters.get("currentPage")[0];
		String pageSize = parammeters.get("pageSize")[0];
		
		Result result = this.getStatLettersFacade().lettersList(customerId, new Integer(currentPage), new Integer(pageSize));
		
		@SuppressWarnings("rawtypes")
		List list = (List)result.getData();
		for(@SuppressWarnings("rawtypes")
		Iterator iter = list.iterator();iter.hasNext();){
			System.out.println("所有信件:letters="+((StationLettersDTO)iter.next()).getSendTime());	
		}
		System.out.println("所有信件:list=="+list.size());
		System.out.println("------------------------------");
		request.setAttribute("list", list);
		
		return "lettersList";
	}
	
	public String noRead(){
		/**
		 * 未读信件测试
		 */
		String customerId = parammeters.get("customerId")[0];
		String currentPage = parammeters.get("currentPage")[0];
		String pageSize = parammeters.get("pageSize")[0];
		
		// 打印入参
		System.out.println("------未读信件列表的入参------");
		System.out.println("customerId=" + customerId);
		System.out.println("currentPage=" + currentPage);
		System.out.println("pageSize=" + pageSize);
		
		Result result = this.getStatLettersFacade().noRead(customerId, new Integer(currentPage), new Integer(pageSize));
		System.out.println("未读信件:结果=="+result.getSuccess());
		System.out.println("未读信件:错误码=="+result.getErrorCode());
		@SuppressWarnings("rawtypes")
		List list2 = (List)result.getData();
		for(@SuppressWarnings("rawtypes")
		Iterator iter2 = list2.iterator();iter2.hasNext();){
			StationLettersDTO statLettersDTO =(StationLettersDTO)iter2.next();
			System.out.println("未读信件:sendTime="+statLettersDTO.getSendTime());
			System.out.println("未读信件:read="+statLettersDTO.getRead());
		}
		System.out.println("未读信件:list2=="+list2.size());
		System.out.println("------------------------------");
		request.setAttribute("noReadList", list2);
		if("1".equals(result.getSuccess())){
			return "success";
		}
		return "error";
	}
	
	public String read(){
		/**
		 * 已读信件测试
		 */
		String customerId = parammeters.get("customerId")[0];
		String currentPage = parammeters.get("currentPage")[0];
		String pageSize = parammeters.get("pageSize")[0];
		
		Result result = this.getStatLettersFacade().read(customerId, new Integer(currentPage), new Integer(pageSize));
		
		request.setAttribute("readList", result.getData());
		
		if("1".equals(result.getSuccess())){
			return "success";
		}
		return "error";
	}
	
	public String size(){
		/**
		 * 查询信件条数
		 * List[0]未读
		 * List[1]已读
		 * List[2]所有
		 */
		String customerId = parammeters.get("customerId")[0];
		
		Result result = this.getStatLettersFacade().size(customerId);
		System.out.println("信件条数:结果=="+result.getSuccess());
		System.out.println("信件条数:错误码=="+result.getErrorCode());
		
		
		@SuppressWarnings("rawtypes")
		List sizeList = (List)result.getData();		
		
		request.setAttribute("sizeList", sizeList);
		
		if(null != sizeList){
			System.out.println("未读条数:noReadSize="+sizeList.get(0));
			System.out.println("已读信件:readSize="+sizeList.get(1));
			System.out.println("所有信件:readSize="+sizeList.get(2));
		}
		if("1".equals(result.getSuccess())){
			return "success";
		}
		return "error";
	}
}
