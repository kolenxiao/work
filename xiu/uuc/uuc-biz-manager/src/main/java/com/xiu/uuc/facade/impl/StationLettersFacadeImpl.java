package com.xiu.uuc.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.xiu.commons.core.BaseExternalService;
import com.xiu.uuc.common.exception.BusinessException;
import com.xiu.uuc.common.exception.ParameterException;
import com.xiu.uuc.facade.StationLettersFacade;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.dto.StationLettersDTO;
import com.xiu.uuc.manager.StationLettersManager;
import com.xiu.uuc.manager.util.ExceptionProcessor;

public class StationLettersFacadeImpl extends BaseExternalService implements StationLettersFacade {
	
	private StationLettersManager stationLettersManager = null;
	
	@Autowired
	public void setStationLettersManager(StationLettersManager stationLettersManager) {
		this.stationLettersManager = stationLettersManager;
	}

	@Override
	public Result sendLetters(StationLettersDTO stationLettersDTO) {
		try{
			return stationLettersManager.sendLetters(stationLettersDTO);
		}catch(ParameterException e){
			return ExceptionProcessor.getParameterExceptionResult(e);
		}catch(BusinessException e){
			return ExceptionProcessor.getBusinessExceptionResult(e);
		}catch(Exception e){
			return ExceptionProcessor.getExceptionResult(e);
		}
	}

	@Override
	public Result deleteLetters(String customerId, String lettersId) {
		try{
			return stationLettersManager.deleteLetters(customerId, lettersId);
		}catch(ParameterException e){
			return ExceptionProcessor.getParameterExceptionResult(e);
		}catch(BusinessException e){
			return ExceptionProcessor.getBusinessExceptionResult(e);
		}catch(Exception e){
			return ExceptionProcessor.getExceptionResult(e);
		}
	}

	@Override
	public Result findLetters(String lettersId) {
		try{
			return stationLettersManager.findLetters(lettersId);
		}catch(ParameterException e){
			return ExceptionProcessor.getParameterExceptionResult(e);
		}catch(BusinessException e){
			return ExceptionProcessor.getBusinessExceptionResult(e);
		}catch(Exception e){
			return ExceptionProcessor.getExceptionResult(e);
		}
	}

	@Override
	public Result lettersList(String customerId, int currentPage, int pageSize) {
		try{
			return stationLettersManager.lettersList(customerId, currentPage, pageSize);
		}catch(ParameterException e){
			return ExceptionProcessor.getParameterExceptionResult(e);
		}catch(BusinessException e){
			return ExceptionProcessor.getBusinessExceptionResult(e);
		}catch(Exception e){
			return ExceptionProcessor.getExceptionResult(e);
		}
	}

	@Override
	public Result noRead(String customerId, int currentPage, int pageSize) {
		try{
			return stationLettersManager.noRead(customerId, currentPage, pageSize);

		}catch(ParameterException e){
			return ExceptionProcessor.getParameterExceptionResult(e);
		}catch(BusinessException e){
			return ExceptionProcessor.getBusinessExceptionResult(e);
		}catch(Exception e){
			return ExceptionProcessor.getExceptionResult(e);
		}
	}

	@Override
	public Result read(String customerId, int currentPage, int pageSize) {
		try{
			return stationLettersManager.read(customerId, currentPage, pageSize);
		}catch(ParameterException e){
			return ExceptionProcessor.getParameterExceptionResult(e);
		}catch(BusinessException e){
			return ExceptionProcessor.getBusinessExceptionResult(e);
		}catch(Exception e){
			return ExceptionProcessor.getExceptionResult(e);
		}
	}

	@Override
	public Result size(String customerId) {
		try{
			return stationLettersManager.size(customerId);	
		}catch(ParameterException e){
			return ExceptionProcessor.getParameterExceptionResult(e);
		}catch(BusinessException e){
			return ExceptionProcessor.getBusinessExceptionResult(e);
		}catch(Exception e){
			return ExceptionProcessor.getExceptionResult(e);	
		}
	}
}
