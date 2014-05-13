package com.xiu.uuc.core.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.xiu.uuc.common.util.KeyNames;
import com.xiu.uuc.common.util.SecurityMD5;
import com.xiu.uuc.core.service.LogService;
import com.xiu.uuc.dal.dao.LogDAO;
import com.xiu.uuc.facade.dto.BusiLogDTO;
import com.xiu.uuc.facade.dto.BusiLogQueryDTO;
import com.xiu.uuc.facade.dto.BusiTypeDTO;

public class LogServiceImpl implements LogService {
	
	
	@Autowired
	private LogDAO logDAO;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public void insertBusiLog(BusiLogDTO busiLogDTO) {
	    try {
	        //生成ID
	        String uuid = UUID.randomUUID().toString();
	        busiLogDTO.setLogId(uuid.replace("-", ""));
	        busiLogDTO.setCreateTime(sdf.parse(sdf.format(new Date())));
	        
	        //生成防修改值
	        StringBuilder sbd = new StringBuilder();
	        sbd.append(null == busiLogDTO.getBusiTypeCode() ? "":busiLogDTO.getBusiTypeCode());
	        sbd.append(null == busiLogDTO.getBeforeContent() ? "":busiLogDTO.getBeforeContent());
	        sbd.append(null == busiLogDTO.getAfterContent() ? "":busiLogDTO.getAfterContent());
	        sbd.append(null == busiLogDTO.getKeyWord() ? "":busiLogDTO.getKeyWord());
	        sbd.append(null == busiLogDTO.getOperId() ? "":busiLogDTO.getOperId());
	        sbd.append(null == busiLogDTO.getOperIp() ? "":busiLogDTO.getOperIp());
	        sbd.append(null == busiLogDTO.getCreateTime() ? "":sdf.format(busiLogDTO.getCreateTime()));
	        sbd.append(KeyNames.BUSI_LOG_MIX);
	        busiLogDTO.setXstr(SecurityMD5.MD5Encode(sbd.toString()));
	        logDAO.insertBusiLog(busiLogDTO);   
        } catch (Exception e) {
            throw new RuntimeException(e);
        }	
	}

	@Override
	public List<BusiLogDTO> queryBusiLogList(BusiLogQueryDTO busiLogQueryDTO) {
		return logDAO.queryBusiLogList(busiLogQueryDTO);
	}

	@Override
	public Long countBusiLogList(BusiLogQueryDTO busiLogQueryDTO) {
		return logDAO.countBusiLogList(busiLogQueryDTO);
	}

	@Override
	public List<BusiTypeDTO> queryBusiTypeList(BusiTypeDTO busiTypeDTO) {
		return logDAO.queryBusiTypeList(busiTypeDTO);
	}

}
