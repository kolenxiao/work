package com.xiu.uuc.core.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.xiu.uuc.common.exception.BusinessException;
import com.xiu.uuc.core.service.AssetsManageService;
import com.xiu.uuc.dal.dao.AssetsManageDAO;
import com.xiu.uuc.dal.param.AcctChangeExtParam;
import com.xiu.uuc.dal.param.AcctChangeParam;
import com.xiu.uuc.dal.param.AcctItemParam;
import com.xiu.uuc.dal.param.IntegeralRuleParam;
import com.xiu.uuc.dal.param.UserAcctParam;
import com.xiu.uuc.dal.param.VirtualAcctItemParam;
import com.xiu.uuc.dal.po.AcctChangeExtPO;
import com.xiu.uuc.dal.po.AcctChangePO;
import com.xiu.uuc.dal.po.AcctItemPO;
import com.xiu.uuc.dal.po.IntegeralRulePO;
import com.xiu.uuc.dal.po.UserAcctPO;
import com.xiu.uuc.dal.po.VirtualAcctItemPO;

/**
 * @ClassName: AssetsManageServiceImpl 
 * @Description: 资产管理业务服务层实现类
 * @author menglei
 * @date Jul 19, 2011 3:01:37 PM 
 */
public class AssetsManageServiceImpl implements AssetsManageService {
	
	@Override
	public Integer updateAcctItem(AcctItemPO acctItemPO)throws BusinessException {
		return getAssetsManageDAO().updateAcctItem(acctItemPO);
	}

	@Override
    public List<AcctItemPO> listAcctItem(AcctItemParam acctItemParam) throws BusinessException{
		return getAssetsManageDAO().listAcctItem(acctItemParam);
	}
	
	@Override
    public AcctItemPO findAcctItem(AcctItemParam acctItemParam) throws BusinessException{
		return getAssetsManageDAO().findAcctItem(acctItemParam);
	}
    
	@Override
	public Integer listAcctItemCount(AcctItemParam acctItemParam) throws BusinessException {
		return getAssetsManageDAO().listAcctItemCount(acctItemParam);
	}
    
	@Override
	public Long insertAcctChange(AcctChangePO acctChangePO) throws BusinessException{
		 return getAssetsManageDAO().insertAcctChange(acctChangePO);
	}

	@Override
	public List<AcctChangePO> listAcctChange(AcctChangeParam acctChangeParam) throws BusinessException {
		return getAssetsManageDAO().listAcctChange(acctChangeParam);
	}
	
	@Override
	public List<AcctChangeExtPO> listAcctChangeExt(AcctChangeExtParam acctChangeExtParam) throws BusinessException {
		return getAssetsManageDAO().listAcctChangeExt(acctChangeExtParam);
	}
    
	@Override
	public Integer listAcctChangeCount(AcctChangeParam acctChangeParam) throws BusinessException {
		return getAssetsManageDAO().listAcctChangeCount(acctChangeParam);
	}
	
	@Override
	public Integer listAcctChangeCountExt(AcctChangeExtParam acctChangeExtParam) throws BusinessException {
		return getAssetsManageDAO().listAcctChangeCountExt(acctChangeExtParam);
	}
	
	
	@Override
	public Integer updateUserAcct(UserAcctPO userAcctPO) throws BusinessException {
		return getAssetsManageDAO().updateUserAcct(userAcctPO);
	}

	@Override
	public UserAcctPO findUserAcct(UserAcctParam userAcctParam) throws BusinessException {
		return getAssetsManageDAO().findUserAcct(userAcctParam);
	}
    
	@Override
	public IntegeralRulePO findIntegeralRule(IntegeralRuleParam integeralRuleParam) throws BusinessException {
		return getAssetsManageDAO().findIntegeralRule(integeralRuleParam);
	}
    
	@Override
	public List<VirtualAcctItemPO> getVirtualAccountInfo(VirtualAcctItemParam virtualAcctItemParam) throws BusinessException {
		return getAssetsManageDAO().getVirtualAccountInfo(virtualAcctItemParam);
	}
	
	@Override
	public Integer getVirtualAccountInfoCount(VirtualAcctItemParam virtualAcctItemParam) throws BusinessException {
		return getAssetsManageDAO().getVirtualAccountInfoCount(virtualAcctItemParam);
	}

	public AssetsManageDAO getAssetsManageDAO() {
		return assetsManageDAO;
	}

	@Autowired
	public void setAssetsManageDAO(AssetsManageDAO assetsManageDAO) {
		this.assetsManageDAO = assetsManageDAO;
	}
	
	/**
	 * 资产管理数据访问对象DAO 
	 */
	private AssetsManageDAO assetsManageDAO;
}
