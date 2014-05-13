package com.xiu.uuc.core.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.xiu.uuc.common.exception.BusinessException;
import com.xiu.uuc.common.util.Page;
import com.xiu.uuc.core.service.StationLettersService;
import com.xiu.uuc.dal.dao.StationLettersDAO;
import com.xiu.uuc.dal.param.StationLettersParameter;
import com.xiu.uuc.dal.po.StationLettersPO;
import com.xiu.uuc.facade.dto.StationLettersDTO;

public class StationLettersServiceImpl implements StationLettersService {
	
	/**
	 *  起始页
	 */
	private int firstRow = 0;
	
	/**
	 *  结束页
	 */
	private int lastRow = 0;
	
	/**
	 * 临时保存各个状态的总记录数
	 * 1.未读记录
	 * 2.已读记录
	 * 3.所有记录
	 */
	private int totalRecord = 0;
	
	/**
	 * PO保存执行结果返回的实体对象DTO的数据
	 */
	private StationLettersPO stationLettersPO = new StationLettersPO();
	private StationLettersDTO stationLettersDTO = new StationLettersDTO();
	
	/**
	 * 把sql所需要的参数封装在StationLettersParameter参数对象中
	 */
	private StationLettersParameter stationLettersParameter = new StationLettersParameter();
	
	/**
	 * 注入DAO
	 */
	@Autowired
	private StationLettersDAO stationLettersDAO = null;

	/**
	 * 注入
	 */
	public void setStationLettersDAO(StationLettersDAO stationLettersDAO) throws BusinessException {
		this.stationLettersDAO = stationLettersDAO;
	}

	@Override
	public void sendLetters(StationLettersDTO stationLettersDTO) throws BusinessException {
		// 数据传输对象转换成实体对象
		BeanUtils.copyProperties(stationLettersDTO, stationLettersPO);
		// sendChannel=1代表是管理员发送给客户的信件
		stationLettersPO.setSendChannel("1");
		stationLettersDAO.sendLetters(stationLettersPO);
	}

	@Override
	public void deleteLetters(String letters_id) throws BusinessException {
		stationLettersDAO.deleteLetters(letters_id);
	}

	@Override
	public StationLettersDTO findLetters(String lettersId) throws BusinessException {
		stationLettersParameter.setLettersId(lettersId);
		
		// 调用dao接口，获取查询结果
		StationLettersPO statLettersPO = stationLettersDAO.findLetters(stationLettersParameter);
		
		// PO转换成DTO
		if(null != statLettersPO){
			BeanUtils.copyProperties(statLettersPO, stationLettersDTO);
		}
		return stationLettersDTO;
	}

	@Override
	public List<StationLettersDTO> lettersList(String customerId, int currentPage,int pageSize) 
		throws BusinessException {
		
		// 获得所有记录数
		totalRecord = stationLettersDAO.allSize(customerId);
		
		// 获得参数对象
		getStationLettersParameter(customerId, currentPage, pageSize);

		// 查处当前客户的所有信件
		List<StationLettersPO> stationLettersPOList= stationLettersDAO.lettersList(stationLettersParameter);

		// PO转换成DTO
		List<StationLettersDTO> stationLettersDTOList = poMappingDto(stationLettersPOList);
		return stationLettersDTOList;
	}

	@Override
	public List<StationLettersDTO> noRead(String customerId, int currentPage,int pageSize) 
		throws BusinessException {
		// 获得未读记录数
		totalRecord = stationLettersDAO.noReadSize(customerId);
		
		// 获得参数对象
		getStationLettersParameter(customerId, currentPage, pageSize);
				
		// 未读信件
		List<StationLettersPO> stationLettersPOList= stationLettersDAO.noRead(stationLettersParameter);
		List<StationLettersDTO> stationLettersDTOList = poMappingDto(stationLettersPOList);
		return stationLettersDTOList;
	}

	@Override
	public List<StationLettersDTO> read(String customerId, int currentPage,int pageSize) 
		throws BusinessException {
		
		// 获得已读记录数
		totalRecord = stationLettersDAO.readSize(customerId);
		
		// 获得参数对象
		getStationLettersParameter(customerId, currentPage, pageSize);
		
		// 已读信件
		List<StationLettersPO> stationLettersPOList= stationLettersDAO.read(stationLettersParameter);
		List<StationLettersDTO> stationLettersDTOList = poMappingDto(stationLettersPOList);
		return stationLettersDTOList;
	}
	
	@Override
	public List<Integer> size(String customerId) throws BusinessException{
		List<Integer> sizeList = new ArrayList<Integer>();
		
		// 未阅读的信件条数
		Integer noReadSize = stationLettersDAO.noReadSize(customerId);
		
		// 已阅读的信件条数
		Integer readSize = stationLettersDAO.readSize(customerId);
		
		// 所有的信件条数
		Integer allSize = stationLettersDAO.allSize(customerId);
		
		sizeList.add(noReadSize);
		sizeList.add(readSize);
		sizeList.add(allSize);
		
		return sizeList;
	}
	
	@Override
	public void update(StationLettersDTO stationLettersDTO){
		// 数据传输对象转换成实体对象
		BeanUtils.copyProperties(stationLettersDTO, stationLettersPO);
		stationLettersDAO.update(stationLettersPO);
	}
	
	/**
	 * PO列表转换成DTO列表
	 * 
	 * @Title: poMappingDto 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @return List<StationLettersDTO>    返回类型 
	 * @throws
	 */
	public List<StationLettersDTO> poMappingDto(List<StationLettersPO> stationLettersPOList){
		List<StationLettersDTO> stationLettersDTOList = new ArrayList<StationLettersDTO>();
		for(Iterator<StationLettersPO> iter = stationLettersPOList.iterator();iter.hasNext();){
			StationLettersDTO stationLettersDTO = new StationLettersDTO();
			StationLettersPO stationLettersPO = iter.next();
			stationLettersDTO.setAddressee(stationLettersPO.getAddressee());
			stationLettersDTO.setContent(stationLettersPO.getContent());
			stationLettersDTO.setCustomerId(stationLettersPO.getCustomerId());
			stationLettersDTO.setCustomerName(stationLettersPO.getCustomerName());
			stationLettersDTO.setEmail(stationLettersPO.getEmail());
			stationLettersDTO.setLettersId(stationLettersPO.getLettersId());
			stationLettersDTO.setOperation(stationLettersPO.getOperation());
			stationLettersDTO.setRead(stationLettersPO.getRead());
			stationLettersDTO.setReceivChannel(stationLettersPO.getReceivChannel());
			stationLettersDTO.setReceivTime(stationLettersPO.getReceivTime());
			stationLettersDTO.setSendChannel(stationLettersPO.getSendChannel());
			stationLettersDTO.setSender(stationLettersPO.getSender());
			stationLettersDTO.setSendTime(stationLettersPO.getSendTime());
			stationLettersDTO.setTelphone(stationLettersPO.getTelphone());
			stationLettersDTO.setTitle(stationLettersPO.getTitle());
			stationLettersDTOList.add(stationLettersDTO);
		}
		return stationLettersDTOList;
	}
	
	/**
	 * 组装参数对象
	 * 
	 * @Title: getStationLettersParameter 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @return StationLettersParameter    返回类型 
	 * @throws
	 */
	public StationLettersParameter getStationLettersParameter(String customerId,int currentPage,int pageSize){	

		// 计算起始行和结束
		Page page = Page.getPage(currentPage, totalRecord, pageSize);
		firstRow = page.getStartRec();
		lastRow = page.getEndRec();
		
		// 把传入sql的参数封装在参数对象中
		stationLettersParameter.setCustomerId(customerId);
		stationLettersParameter.setFirstRow(firstRow);
		stationLettersParameter.setLastRow(lastRow);
		
		return stationLettersParameter;
	}
}
