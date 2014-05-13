package com.xiu.uuc.core.service;

import java.util.List;

import com.xiu.uuc.common.exception.BusinessException;
import com.xiu.uuc.facade.dto.StationLettersDTO;


/**
 * 描述：站内信的发送，删除，信件列表
 * @author LiuZhiYong
 * @version1.0
 */
public interface StationLettersService {
		
	/**
	 * 发送信件
	 * 
	 * @Title: sendLetters 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @return
	 */
	public void sendLetters(StationLettersDTO stationLettersDTO) throws BusinessException;
	
	/**
	 * 删除信件
	 * 
	 * @param customerId 客户ID
	 * @param letters_id 信件ID
	 * @return
	 */
	public void deleteLetters(String lettersId) throws BusinessException;
	
	/**
	 * 查询单封信件
	 * 
	 * @param customerId 客户ID
	 * @param letters_id 信件ID
	 * @return StationLettersDTO对象
	 */
	public StationLettersDTO findLetters(String lettersId) throws BusinessException;
	
	/**
	 * 获取所有信件
	 * 
	 * @param customerId 客户ID
	 * @param page 当前页
	 * @param count 每页显示的条数
	 * @return Result对象
	 */
	public List<StationLettersDTO> lettersList(String customerId,int currentPage, int pageSize) throws BusinessException;
	
	/**
	 * 获取未读信件列表
	 * 
	 * @param customerId 客户ID
	 * @param page 当前页
	 * @param count 每页显示的条数
	 * @return StationLettersDTO列表
	 */
	public List<StationLettersDTO> noRead(String customerId,int currentPage,int pageSize) throws BusinessException;
	
	/**
	 * 获取已经阅读过的信件列表（分页）
	 * 
	 * @param customerId 客户ID
	 * @param page 当前页
	 * @param count 每页显示的条数
	 * @return StationLettersDTO列表
	 */
	public List<StationLettersDTO> read(String customerId,int currentPage,int pageSize) throws BusinessException;
	
	/**
	 * 查询信件的条数（已阅读，未阅读，所有）
	 * 
	 * @Title: noReadSize 
	 * @return List 
	 * 			list[0]:未阅读的条数
	 * 			list[1]:未阅读的条数:已阅读
	 * 			list[2]:所有的条数
	 */
	public List<Integer> size(String customerId);
	
	/**
	 * 查看过的信件改为已读
	 * 
	 * @Title: update 
	 * @return void    返回类型 
	 */
	public void update(StationLettersDTO stationLettersDTO);
}
