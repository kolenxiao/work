package com.xiu.uuc.facade;

import com.xiu.commons.core.IServiceStatusHealthyChecking;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.dto.StationLettersDTO;

/**
 * 站内信的发送，删除，查看，取列表等功能接口类
 * @ClassName: StationLettersFacade 
 * @author LiuZhiYong
 * @date 2011-7-20 上午11:47:32 
 */
public interface StationLettersFacade extends IServiceStatusHealthyChecking{
	
	/**
	 * 发送信件
	 * 
	 * @Title: sendLetters 
	 * @Description:
	 * @return Result    返回类型 
	 * @throws
	 */
	public Result sendLetters(StationLettersDTO stationLettersDTO);
	
	/**
	 * 删除信件
	 * @Title: deleteLetters 
	 * @Description: 参数描述,customerId 客户ID,letters_id 信件ID
	 * @return Result    返回类型 
	 * @throws
	 */
	public Result deleteLetters(String customerId,String lettersId);
	
	/**
	 * 查询单封信件
	 * 
	 * @Title: findLetters 
	 * @param customerId 客户ID
	 * @param title 信件标题 
	 * @return Result    返回类型 
	 * @throws
	 */
	public Result findLetters(String lettersId);
	
	/**
	 * 获取所有信件
	 * 
	 * @Title: lettersList 
	 * @param customerId 客户ID
	 * @param page 当前页
	 * @param count 每页显示的条数
	 * @param totalRecord 总记录数
	 * @return Result    返回类型 
	 * @throws
	 */
	public Result lettersList(String customerId,int currentPage,int pageSize);
	
	/**
	 * 获取未读信件列表
	 * 
	 * @Title: noRead 
	 * @param customerId 客户ID
	 * @param page 当前页
	 * @param count 每页显示的条数
	 * @param totalRecord 总记录数
	 * @return Result    返回类型 
	 * @throws
	 */
	public Result noRead(String customerId,int currentPage,int pageSize);
	
	/**
	 * 获取已经阅读过的信件列表
	 * 
	 * @Title: read 
	 * @param customerId 客户ID
	 * @param page 当前页
	 * @param count 每页显示的条数
	 * @param totalRecord 总记录数 
	 * @return Result    返回类型 
	 * @throws
	 */
	public Result read(String customerId,int currentPage,int pageSize);
	
	/**
	 * 查询没有阅读信件的条数
	 * 
	 * @Title: noReadSize 
	 * @return Result    返回类型
	 * 			 Result.data属性中存放List
	 * 				List中存放信件的条数
	 * 				   list[0]:未阅读的条数
	 * 			       list[1]:未阅读的条数:已阅读
	 * 			       list[2]:所有的条数
	 */					
	public Result size(String customerId);
}
