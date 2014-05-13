package com.xiu.uuc.manager.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.xiu.uuc.common.exception.ParameterException;
import com.xiu.uuc.core.service.StationLettersService;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.dto.StationLettersDTO;
import com.xiu.uuc.facade.util.ErrorCodeConstant;
import com.xiu.uuc.facade.util.FacadeConstant;
import com.xiu.uuc.manager.StationLettersManager;

public class StationLettersManagerImpl implements StationLettersManager {
	/**
	 *  结果集
	 */
	private Result result = new Result();
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private StationLettersService stationLettersService = null;	
	
	public void setStationLettersService(StationLettersService stationLettersService) {	
		this.stationLettersService = stationLettersService;
	}

	@Override
	public Result sendLetters(StationLettersDTO stationLettersDTO) throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("class:StationLettersManagerImpl,method:sendLetters," +
					"parameters:StationLettersDTO = " + stationLettersDTO);
		}
		
		/**
		 *  判断入参是否合法
		 */
		if(null == stationLettersDTO){
			throw new ParameterException(ErrorCodeConstant.PARAMETER_OBJECT);
		}
		if(null == stationLettersDTO.getSendTime() || "".equals(stationLettersDTO.getSendTime())){
			throw new ParameterException(ErrorCodeConstant.PARAMETER_VALUE);
		}
		if(StringUtils.isEmpty(stationLettersDTO.getAddressee())){
			throw new ParameterException(ErrorCodeConstant.PARAMETER_VALUE);
		}
		if(StringUtils.isEmpty(stationLettersDTO.getSender())){
			throw new ParameterException(ErrorCodeConstant.PARAMETER_VALUE);
		}
		if(StringUtils.isEmpty(stationLettersDTO.getTitle())){
			throw new ParameterException(ErrorCodeConstant.PARAMETER_VALUE);
		}
		if(StringUtils.isEmpty(stationLettersDTO.getCustomerId())){
			throw new ParameterException(ErrorCodeConstant.PARAMETER_VALUE);
		}
		if(stationLettersDTO.getCustomerId().length() > 12){
			throw new ParameterException(ErrorCodeConstant.PARAMETER_VALUE);
		}
		if(StringUtils.isEmpty(stationLettersDTO.getContent())){
			throw new ParameterException(ErrorCodeConstant.PARAMETER_VALUE);
		}
		try{
			/**
			 * 核心业务调用
			 */
			handleDTO(stationLettersDTO);
			stationLettersService.sendLetters(stationLettersDTO);
			result.setSuccess(FacadeConstant.SUCCESS);
			result.setErrorCode(null);
			result.setData(null);
			
			logger.info("class:StationLettersManagerImpl,method:sendLetters," +
					"result:" + FacadeConstant.SUCCESS);
			return result;
		}catch(Exception e){
			throw e;
		}
	}

	@Override
	public Result deleteLetters(String customerId, String lettersId) throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("class:StationLettersManagerImpl,method:deleteLetters," +
					"parameter:customerId = " + customerId + "," +
					"parameter:lettersId = " + lettersId);
		}
		/**
		 * 判断入参是否合法
		 */
		if(StringUtils.isEmpty(customerId)){
			throw new ParameterException(ErrorCodeConstant.PARAMETER_VALUE);
		}
		if(StringUtils.isEmpty(lettersId)){
			throw new ParameterException(ErrorCodeConstant.PARAMETER_VALUE);
		}
		try{
			/**
			 * 核心业务调用
			 */
			stationLettersService.deleteLetters(lettersId);
			result.setSuccess(FacadeConstant.SUCCESS);
			result.setErrorCode(null);
			result.setData(null);
			
			logger.info("class:StationLettersManagerImpl,method:deleteLetters," +
					"result:" + FacadeConstant.SUCCESS);
			return result;
			
		}catch(Exception e){
			throw e;
		}
	} 

	@Override
	public Result findLetters(String lettersId) throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("class:StationLettersManagerImpl,method:deleteLetters," + 
					"parameter:lettersId = " + lettersId);
		}
		
		/**
		 * 判断入参是否合法
		 */
		if(StringUtils.isEmpty(lettersId)){
			throw new ParameterException(ErrorCodeConstant.PARAMETER_VALUE);
		}
		
		try{
			/**
			 * 核心业务调用
			 */
			StationLettersDTO stationLettersDTO = stationLettersService.findLetters(lettersId);// 通过客户ID和信件ID查出结果
			result.setSuccess(FacadeConstant.SUCCESS);
			result.setErrorCode(null);
			
			// 把结果对象存放在Result中
			result.setData(stationLettersDTO);
			// 调用改变信件状态的服务
			stationLettersService.update(stationLettersDTO);
			
			logger.info("class:StationLettersManagerImpl,method:findLetters," +
					"result:" + FacadeConstant.SUCCESS);
			return result;			
		}catch(Exception e){
			throw e;
		}
	}

	@Override
	public Result lettersList(String customerId, int currentPage, int pageSize) throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("class:StationLettersManagerImpl,method:deleteLetters," + 
					"parameter:customerId = " + customerId + "," + 
					"parameter:currentPage = " + currentPage + "," +
					"parameter:pageSize = " + pageSize);
		}
		
		/**
		 * 判断入参是否合法
		 */
		if(StringUtils.isEmpty(customerId)){
			throw new ParameterException(ErrorCodeConstant.PARAMETER_VALUE);
		}
		if(customerId.length() > 12){
			throw new ParameterException(ErrorCodeConstant.PARA_VALUE);
		}
		if(currentPage == 0){
			throw new ParameterException(ErrorCodeConstant.PARA_VALUE);
		}
		if(pageSize == 0){
			throw new ParameterException(ErrorCodeConstant.PARA_VALUE);
		}
		try{
			/**
			 * 核心业务调用
			 */
			List<StationLettersDTO> stationLettersDTOList =stationLettersService.// 通过客户ID和信件ID查出结果
					lettersList(customerId,currentPage,pageSize);
			result.setSuccess(FacadeConstant.SUCCESS);
			result.setErrorCode(null);
			
			// 把结果对象存放在Result中
			result.setData(stationLettersDTOList);
			
			logger.info("class:StationLettersManagerImpl,method:lettersList," +
					"result:" + FacadeConstant.SUCCESS);
			return result;		
		}catch(Exception e){
			throw e;
		}
	}

	@Override
	public Result noRead(String customerId, int currentPage, int pageSize) throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("class:StationLettersManagerImpl,method:deleteLetters," + 
					"parameter:customerId = " + customerId + "," + 
					"parameter:currentPage = " + currentPage + "," +
					"parameter:pageSize = " + pageSize);
		}
		
		/**
		 * 判断入参是否合法
		 */
		if(StringUtils.isEmpty(customerId)){
			throw new ParameterException(ErrorCodeConstant.PARAMETER_VALUE);
		}
		if(customerId.length() > 12){
			throw new ParameterException(ErrorCodeConstant.PARA_VALUE);
		}
		if(currentPage == 0){
			throw new ParameterException(ErrorCodeConstant.PARA_VALUE);
		}
		if(pageSize == 0){
			throw new ParameterException(ErrorCodeConstant.PARA_VALUE);
		}
		try{
			/**
			 * 核心业务调用
			 */
			List<StationLettersDTO> stationLettersDTOList =stationLettersService.// 通过客户ID和信件ID查出结果
					noRead(customerId, currentPage,pageSize);
			result.setSuccess(FacadeConstant.SUCCESS);
			result.setErrorCode(null);
			
			// 把结果对象存放在Result中
			result.setData(stationLettersDTOList);
			
			logger.info("class:StationLettersManagerImpl,method:noRead," +
					"result:" + FacadeConstant.SUCCESS);
			return result;
		}catch(Exception e){
			throw e;
		}
	}

	@Override
	public Result read(String customerId, int currentPage, int pageSize) throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("class:StationLettersManagerImpl,method:deleteLetters," + 
					"parameter:customerId = " + customerId + "," + 
					"parameter:currentPage = " + currentPage + "," +
					"parameter:pageSize = " + pageSize);
		}
		
		/**
		 * 判断入参是否合法
		 */
		if(StringUtils.isEmpty(customerId)){
			throw new ParameterException(ErrorCodeConstant.PARAMETER_VALUE);
		}
		if(customerId.length() > 12){
			throw new ParameterException(ErrorCodeConstant.PARA_VALUE);
		}
		if(currentPage == 0){
			throw new ParameterException(ErrorCodeConstant.PARA_VALUE);
		}
		if(pageSize == 0){
			throw new ParameterException(ErrorCodeConstant.PARA_VALUE);
		}
		try{
			/**
			 * 核心业务调用
			 */
			List<StationLettersDTO> stationLettersDTOList =stationLettersService.// 通过客户ID和信件ID查出结果
					read(customerId, currentPage,pageSize);
			result.setSuccess(FacadeConstant.SUCCESS);
			result.setErrorCode(null);
			
			// 把结果对象存放在Result中
			result.setData(stationLettersDTOList);
			
			logger.info("class:StationLettersManagerImpl,method:read," +
					"result:" + FacadeConstant.SUCCESS);
			return result;		
		}catch(Exception e){
			throw e;
		}
	}
	
	@Override
	public Result size(String customerId) throws Exception {
		if(logger.isInfoEnabled()){
			logger.info("class:StationLettersManagerImpl,method:deleteLetters," + 
					"parameter:customerId = " + customerId);
		}
		
		/**
		 * 判断入参是否合法
		 */
		if(StringUtils.isEmpty(customerId)){
			result.setSuccess(FacadeConstant.FALSE);
			// 客户ID不能为空
			result.setErrorCode("0001");
			result.setData(null);
			
			logger.info("deleteLetters(),errorCode:" + "0001");
			return result;
		}
		if(customerId.length() > 12){
			result.setSuccess(FacadeConstant.FALSE);
			// 客户ID长度超限
			result.setErrorCode("0002");
			result.setData(null);
			
			logger.info("deleteLetters(),errorCode:" + "0002");
			return result;
		}
		
		/**
		 * 核心业务调用
		 */
		List<Integer> sizeList = stationLettersService.size(customerId);
		
		result.setSuccess(FacadeConstant.SUCCESS);
		result.setErrorCode(null);
		result.setData(sizeList);
		
		logger.info("class:StationLettersManagerImpl,method:size," +
				"result:" + FacadeConstant.SUCCESS);
		return result;
	}
	
	/**
	 * 处理DTO的每个属性书否为null，如果为null设置为""
	 * 
	 * @Title: handleDTO 
	 * @return StationLettersDTO    返回类型 
	 * @throws
	 */
	public StationLettersDTO handleDTO(StationLettersDTO stationLettersDTO){
		if(null == stationLettersDTO.getReceivChannel()){
			stationLettersDTO.setReceivChannel("");
		}
		if(null == stationLettersDTO.getSendChannel()){
			stationLettersDTO.setSendChannel("");
		}
		if(null == stationLettersDTO.getReceivTime()){
			stationLettersDTO.setReceivTime(new Date());
		}
		if(null == stationLettersDTO.getOperation()){
			stationLettersDTO.setOperation("");
		}
		if(null == stationLettersDTO.getCustomerName()){
			stationLettersDTO.setCustomerName("");
		}
		if(null == stationLettersDTO.getTelphone()){
			stationLettersDTO.setTelphone("");
		}
		if(null == stationLettersDTO.getEmail()){
			stationLettersDTO.setEmail("");
		}
		if(null == stationLettersDTO.getRead()){
			stationLettersDTO.setRead(0);
		}
		return stationLettersDTO;
	}
}
