package com.xiu.uuc.manager;


import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.dto.StationLettersDTO;

public interface StationLettersManager {
	/**
	 * 发送信件
	 * 
	 * @param stationLettersPO 实体对象
	 * @return Result对象
	 */
	public Result sendLetters(StationLettersDTO stationLettersDTO) throws Exception;
	
	/**
	 * 删除信件
	 * 
	 * @param customerId 客户ID
	 * @param lettersId 信件ID
	 * @return Result对象
	 */
	public Result deleteLetters(String customerId,String lettersId) throws Exception;
	
	/**
	 * 查询单封信件
	 * 
	 * @param customerId 客户ID
	 * @param title 信件标题
	 * @return Result对象
	 */
	public Result findLetters(String lettersId) throws Exception;
	
	/**
	 * 功能：获取所有信件
	 * 
	 * @param customerId 客户ID
	 * @param page 当前页
	 * @param count 每页显示的条数
	 * @param totalRecord 总记录数
	 * @return Result对象
	 */
	public Result lettersList(String customerId,int currentPage,int pageSize) throws Exception;
	
	/**
	 * 获取未读信件列表
	 * 
	 * @param customerId 客户ID
	 * @param page 当前页
	 * @param count 每页显示的条数
	 * @param totalRecord 总记录数
	 * @return Result对象
	 */
	public Result noRead(String customerId,int currentPage,int pageSize) throws Exception;
	
	/**
	 * 获取已经阅读过的信件列表
	 * 
	 * @param customerId 客户ID
	 * @param page 当前页
	 * @param count 每页显示的条数
	 * @param totalRecord 总记录数
	 * @return Result对象
	 */
	public Result read(String customerId,int currentPage,int pageSize) throws Exception;
	
	/**
	 * 查询信件的条数（已阅读，未阅读，所有信件的条数）
	 * 
	 * @Title: noReadSize 
	 * @return Result    返回类型 
	 */
	public Result size(String customerId) throws Exception;
}
