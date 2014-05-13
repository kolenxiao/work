package com.xiu.uuc.dal.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xiu.uuc.common.util.JsonUtils;
import com.xiu.uuc.dal.dao.CommonDAO;
import com.xiu.uuc.dal.po.BusiLogPO;
import com.xiu.uuc.dal.po.InterfaceLogPO;

public class LogHelper {
	
	
	/**
	 * 单例模式
	 */
	private static LogHelper instance;
	
	private static CommonDAO commonDAO;
	
	public static synchronized LogHelper getInstance() {  
	    if (instance == null) {  
	        instance = new LogHelper();
	    }  
	    return instance;  
	}
	
	public LogHelper(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				new String[] { "spring-uuc-jdbc.xml", "spring-uuc-dao.xml" });
		commonDAO = (CommonDAO) ctx.getBean("commonDAO");
	}
	

	/**
	 * 插入业务日志
	 * @Title: insertBusiLog 
	 * @param  busiLogPO 
	 * @return Long    返回类型 
	 * @throws
	 */
	public Long insertBusiLog(BusiLogPO busiLogPO){
		return commonDAO.insertBusiLog(busiLogPO);
	}
	
	/**
	 * 插入业务日志
	 * @Title: insertBusiLog 
	 * @param  operatorId 操作者ID
	 * @param  operatorName 操作者名称
	 * @param  operatorIp 操作者ip地址
	 * @param  operationName 对象名称
	 * @param  content 内容描述
	 * @param  type 日志类型
	 * @param  remark 日志备注
	 * @return Long    返回类型 
	 * @throws
	 */
	public Long insertBusiLog(String operatorId, String operatorName,
			String operatorIp, String operationName, String content,
			String type, String remark) {
		BusiLogPO busiLogPO = new BusiLogPO();
		busiLogPO.setOperatorId(operatorId);
		busiLogPO.setOperationName(operationName);
		busiLogPO.setOperatorIp(operatorIp);
		busiLogPO.setOperationName(operationName);
		busiLogPO.setContent(content);
		busiLogPO.setType(type);
		busiLogPO.setRemark(remark);
		return this.insertBusiLog(busiLogPO);
	}
	
	
	

	/**
	 * 插入接口日志
	 * @Title: insertInterfaceLog 
	 * @param  interfaceLogPO 
	 * @return Long    返回类型 
	 * @throws
	 */
	public Long insertInterfaceLog(InterfaceLogPO interfaceLogPO){
		return commonDAO.insertInterfaceLog(interfaceLogPO);
	}
	
	/**
	 * 插入接口日志
	 * @Title: insertInterfaceLog 
	 * @param  method 对象名称
	 * @param  parameters 入参
	 * @param  results 返回结果
	 * @return Long    返回类型 
	 * @throws
	 */
	public Long insertInterfaceLog(String method, Object parameters, Object results) {
		JsonUtils jsonUtils = new JsonUtils();
		InterfaceLogPO interfaceLogPO = new InterfaceLogPO();
		interfaceLogPO.setMethod(method);
		interfaceLogPO.setParameters(jsonUtils.toJson(parameters));
		interfaceLogPO.setResults(jsonUtils.toJson(results));
		return this.insertInterfaceLog(interfaceLogPO);
	}

}
