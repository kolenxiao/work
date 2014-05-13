package com.xiu.uuc.core.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.xiu.uuc.common.exception.BusinessException;
import com.xiu.uuc.core.service.ChangePayService;
import com.xiu.uuc.dal.dao.AssetsManageDAO;
import com.xiu.uuc.dal.dao.ChangePayDAO;
import com.xiu.uuc.dal.param.AcctItemParam;
import com.xiu.uuc.dal.param.TractParam;
import com.xiu.uuc.dal.param.UserAcctParam;
import com.xiu.uuc.dal.po.AcctChangePO;
import com.xiu.uuc.dal.po.AcctItemPO;
import com.xiu.uuc.dal.po.TractPO;
import com.xiu.uuc.dal.po.UserAcctPO;

public class ChangePayServiceImpl implements ChangePayService {

	@Override
	public AcctItemPO getAcctItemPoByUserIdAndAcctTypeCode(Long userId,String acctTypeCode) throws BusinessException {
		AcctItemParam acctItemParam = new AcctItemParam(userId, acctTypeCode);
		return assetsManageDAO.findAcctItem(acctItemParam);
	}

	@Override
	public UserAcctPO getUserAcctPOByUserId(Long userId) throws BusinessException{
		UserAcctParam userAcctParam = new UserAcctParam();
		userAcctParam.setUserId(userId);
		return assetsManageDAO.findUserAcct(userAcctParam);
	}
	
	@Override
	public Integer updateAcctItem(AcctItemPO acctItemPO) throws BusinessException{
		return assetsManageDAO.updateAcctItem(acctItemPO);
	}

	@Override
	public Long insertAcctChange(AcctChangePO acctChangePO) throws BusinessException{
		return assetsManageDAO.insertAcctChange(acctChangePO);
	}
	
	@Override
	public Long insertAcctItemPO(AcctItemPO acctItemPO) throws BusinessException{
		return changePayDAO.insertAcctItemPO(acctItemPO);
	}

	@Override
	public Long insertAcctTract(TractPO tractPO) throws BusinessException{
		return changePayDAO.insertTractPO(tractPO);
	}
	
	@Override
    public List<TractPO> getTractRefundDetailList(TractParam tractParam)throws BusinessException {
		return changePayDAO.getTractRefundDetailList(tractParam);
	}
	
	@Override
	public Integer getTractRefundDetailListCount(TractParam tractParam) throws BusinessException {
		return changePayDAO.getTractRefundDetailListCount(tractParam);
	}
    
	@Override
    public List<TractPO> getTractRefundNotEndDetailList(TractParam tractParam)throws BusinessException {
		return changePayDAO.getTractRefundNotEndDetailList(tractParam);
	}
	
	@Override
	public Integer getTractRefundNotEndDetailListCount(TractParam tractParam) throws BusinessException {
		return changePayDAO.getTractRefundNotEndDetailListCount(tractParam);
	}
	
	@Autowired
	public void setAssetsManageDAO(AssetsManageDAO assetsManageDAO) {
		this.assetsManageDAO = assetsManageDAO;
	}
	
	@Autowired
	public void setChangePayDAO(ChangePayDAO changePayDAO) {
		this.changePayDAO = changePayDAO;
	}

	private AssetsManageDAO assetsManageDAO;
	private ChangePayDAO changePayDAO;

}
