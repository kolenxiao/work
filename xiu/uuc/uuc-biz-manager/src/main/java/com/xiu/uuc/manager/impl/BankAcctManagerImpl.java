package com.xiu.uuc.manager.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.xiu.uuc.common.util.BeanUtilEx;
import com.xiu.uuc.core.service.BankAcctService;
import com.xiu.uuc.core.service.UserManageService;
import com.xiu.uuc.dal.param.BankAcctParam;
import com.xiu.uuc.dal.po.BankAcctPO;
import com.xiu.uuc.dal.po.UserInfoPO;
import com.xiu.uuc.facade.dto.BankAcctDTO;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.util.FacadeConstant;
import com.xiu.uuc.facade.util.TypeEnum.logicDeleteFlag;
import com.xiu.uuc.manager.BankAcctManager;
import com.xiu.uuc.manager.validate.BankAcctManagerValidate;

/**
 * @ClassName: BankAcctManagerImpl 
 * @Description: 银行卡账户管理流程控制层实现类 
 * @author menglei
 * @date Jul 20, 2011 8:26:59 PM 
 */
public class BankAcctManagerImpl implements BankAcctManager {

	@Override
	public Result insertBankAcct(BankAcctDTO bankAcctDTO) throws Exception{
		Result result;
	    BankAcctManagerValidate.validateInsertBankAcct(bankAcctDTO); //新增银行卡账户信息 输入参数合法性校验 
		BankAcctPO bankAcctPO = new BankAcctPO();
	    BeanUtilEx.copyProperties(bankAcctPO, bankAcctDTO);
	    UserInfoPO userInfoPO = getUserManageService().getUserById(bankAcctPO.getUserId());
	    BankAcctManagerValidate.validateGetUserById(userInfoPO); //通过用户id获取用户信息 校验
		bankAcctPO.setCustId(userInfoPO.getCustId());	
		Long bankAcctId  = getBankAcctService().insertBankAcct(bankAcctPO); //返回新增对象主键ID
		result = new Result(FacadeConstant.SUCCESS,bankAcctId);
		return result;
	}
	
	@Override
	public Result deleteBankAcct(BankAcctDTO bankAcctDTO) throws Exception{
		Result result ;
		BankAcctManagerValidate.validateDeleteBankAcct(bankAcctDTO); //删除银行卡账户信息 输入参数合法性校验
		BankAcctPO bankAcctPO = new BankAcctPO();
		BeanUtilEx.copyProperties(bankAcctPO, bankAcctDTO);
		bankAcctPO.setDeleteFlag(logicDeleteFlag.YES_DELETE.getKey()); //设置逻辑删除标志
		Integer deleteCount  = getBankAcctService().updateBankAcct(bankAcctPO);//这里是逻辑删除，修改删除标志位
		result = new Result(FacadeConstant.SUCCESS,deleteCount);
		return result;
	}
	
	@Override
	public Result updateBankAcct(BankAcctDTO bankAcctDTO) throws Exception{
		Result result ;
		BankAcctManagerValidate.validateUpdateBankAcct(bankAcctDTO); //修改银行卡账户信息 输入参数合法性校验
		BankAcctPO bankAcctPO = new BankAcctPO();
		BeanUtilEx.copyProperties(bankAcctPO, bankAcctDTO);
		Integer updateCount  = getBankAcctService().updateBankAcct(bankAcctPO);//返回修改成功条数
		result = new Result(FacadeConstant.SUCCESS,updateCount);
		return result;
	}

	@Override
	public Result listBankAcct(BankAcctDTO bankAcctDTO) throws Exception{
		Result result ;
		BankAcctManagerValidate.validateListBankAcct(bankAcctDTO); //查询银行卡账户信息列表 输入参数合法性校验
		if (StringUtils.isBlank(String.valueOf(bankAcctDTO.getBankAcctId())) 
		     && StringUtils.isBlank(String.valueOf(bankAcctDTO.getUserId()))
		     && StringUtils.isBlank(String.valueOf(bankAcctDTO.getCustId()))
		     && StringUtils.isBlank(String.valueOf(bankAcctDTO.getBankAcctNo()))
		     && StringUtils.isBlank(bankAcctDTO.getBankAcctName())
		     && StringUtils.isBlank(bankAcctDTO.getMobile())
		     && StringUtils.isBlank(bankAcctDTO.getPhone()) ) {
			return new Result(FacadeConstant.SUCCESS,new ArrayList<BankAcctDTO>());
		}
		BankAcctParam bankAcctParam = new BankAcctParam();
		BeanUtilEx.copyProperties(bankAcctParam, bankAcctDTO);
		bankAcctParam.setDeleteFlag(logicDeleteFlag.NO_DELETE.getKey()); //设置逻辑删除标志
		List<BankAcctDTO> bankAcctDTOList = new ArrayList<BankAcctDTO>();
		for(BankAcctPO bankAcctPO : getBankAcctService().listBankAcct(bankAcctParam)){
			bankAcctDTO = new BankAcctDTO();
			BeanUtilEx.copyProperties(bankAcctDTO, bankAcctPO);
			bankAcctDTOList.add(bankAcctDTO);
		}
		result = new Result(FacadeConstant.SUCCESS,bankAcctDTOList);
		return result;
	}
	
	@Override
	public Result findBankAcct(BankAcctDTO bankAcctDTO) throws Exception{
		Result result ;
		BankAcctManagerValidate.validateFindBankAcct(bankAcctDTO); //查询特定提现银行账号详情 输入参数合法性校验
		BankAcctParam bankAcctParam = new BankAcctParam();
		BeanUtilEx.copyProperties(bankAcctParam, bankAcctDTO);
		BankAcctPO findBankAcctPO = getBankAcctService().findBankAcct(bankAcctParam);
		BeanUtilEx.copyProperties(bankAcctDTO, findBankAcctPO);
		result = new Result(FacadeConstant.SUCCESS,bankAcctDTO);
		return result;
	}

	public BankAcctService getBankAcctService() {
		return bankAcctService;
	}

	@Autowired
	public void setBankAcctService(BankAcctService bankAcctService) {
		this.bankAcctService = bankAcctService;
	}
	
	public UserManageService getUserManageService() {
		return userManageService;
	}

	@Autowired
	public void setUserManageService(UserManageService userManageService) {
		this.userManageService = userManageService;
	}
	
	/**
	 * 银行卡账户管理业务服务层对象
	 */
	private BankAcctService bankAcctService;
	
	/**
	 * 用戶管理业务服务层对象
	 */
	private UserManageService userManageService;
}
