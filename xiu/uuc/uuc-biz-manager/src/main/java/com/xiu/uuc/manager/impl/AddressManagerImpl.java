package com.xiu.uuc.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.xiu.csp.facade.SysParamFacade;
import com.xiu.csp.facade.util.SysParamUtil;
import com.xiu.uuc.common.exception.BusinessException;
import com.xiu.uuc.common.util.BeanUtilEx;
import com.xiu.uuc.common.util.ExceptionEnum;
import com.xiu.uuc.core.service.AddressManageService;
import com.xiu.uuc.core.service.UserManageService;
import com.xiu.uuc.dal.param.RcvAddressParam;
import com.xiu.uuc.dal.po.RcvAddressPO;
import com.xiu.uuc.dal.po.UserInfoPO;
import com.xiu.uuc.facade.dto.RcvAddressDTO;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.util.FacadeConstant;
import com.xiu.uuc.facade.util.TypeEnum;
import com.xiu.uuc.manager.AddressManager;
import com.xiu.uuc.manager.util.AddressManagerLog;
import com.xiu.uuc.manager.validate.AddressManageValidate;

public class AddressManagerImpl implements AddressManager {

	@Autowired
	private AddressManageService addressManageService;	
	@Autowired
	private UserManageService userManageService;
	@Autowired
	private SysParamFacade sysParamFacade;
	@Autowired
	private AddressManagerLog addressManagerLog;


	@Override
	public Result addRcvAddressInfo(RcvAddressDTO rcvAddressDTO) throws Exception {
		//校验数据
		AddressManageValidate.validateAddRcvAddressInfo(rcvAddressDTO);

		//判断用户是否存在
		UserInfoPO userInfoPO = userManageService.getUserById(rcvAddressDTO.getUserId());
		if(null == userInfoPO){
			throw new BusinessException(ExceptionEnum.Busi.USER_NOT_EXIST.getKey(),"");
		}
		
		//组织数据
		RcvAddressPO rcvAddressPO = new RcvAddressPO();
		BeanUtilEx.copyProperties(rcvAddressPO, rcvAddressDTO);
		rcvAddressPO.setCustId(userInfoPO.getCustId());		
		if(StringUtils.isEmpty(rcvAddressPO.getIsMaster())){
			rcvAddressPO.setIsMaster(TypeEnum.YesOrNo.NO.getKey());
		}else if(TypeEnum.YesOrNo.YES.getKey().equals(rcvAddressPO.getIsMaster())){
			//将原来的默认地址设为非默认
			this.cancalMaster(rcvAddressPO.getCustId());
		}
		
		//插入数据
		Long addressId = addressManageService.addRcvAddress(rcvAddressPO);
		
		//插入业务日志
		addressManagerLog.logAddRcvAddressInfo(rcvAddressPO);
		
		//返回
		return this.getRcvAddressInfoById(addressId);
	}

	@Override
	public Result modifyRcvAddressInfo(RcvAddressDTO rcvAddressDTO) throws Exception {
		//校验数据
		AddressManageValidate.validateModifyRcvAddressInfo(rcvAddressDTO);

		//校验地址是否存在
		RcvAddressPO oldAddressPO = addressManageService.getRcvAddressInfoById(rcvAddressDTO.getAddressId());
		if(null == oldAddressPO){
			throw new BusinessException("收货地址不存在!");
		}
		
		//复制数据
		RcvAddressPO newAddressPO = new RcvAddressPO();
		BeanUtilEx.copyProperties(newAddressPO, rcvAddressDTO);
		newAddressPO.setCustId(oldAddressPO.getCustId());
		
		//判断是否需要设为默认地址
		if(StringUtils.isEmpty(newAddressPO.getIsMaster())){
			newAddressPO.setIsMaster(TypeEnum.YesOrNo.NO.getKey());
		}else if(TypeEnum.YesOrNo.YES.getKey().equals(newAddressPO.getIsMaster())){
			//将原来的默认地址设为非默认
			this.cancalMaster(newAddressPO.getCustId());
		}

		//修改地址
		addressManageService.modifyRcvAddress(newAddressPO);
		
		//插入业务日志
		addressManagerLog.logModifyRcvAddressInfo(oldAddressPO, newAddressPO);
		
		//返回结果
		Result result = new Result();
		result.setSuccess(FacadeConstant.SUCCESS);
		return result;
	}

	@Override
    public Result modifyRcvAddressMaster(Long addressId) {
        // 校验数据
        AddressManageValidate.validateModifyRcvAddressMaster(addressId);

        // 校验地址是否存在
        RcvAddressPO rcvAddressPO = addressManageService.getRcvAddressInfoById(addressId);
        if (null == rcvAddressPO) {
            throw new BusinessException("收货地址不存在!");
        }

        // 将原来的默认地址设为非默认
        this.cancalMaster(rcvAddressPO.getCustId());

        // 将传入的地址设为默认
        addressManageService.modifyRcvAddressMaster(null, addressId, TypeEnum.YesOrNo.YES.getKey());

        // 返回结果
        Result result = new Result();
        result.setSuccess(FacadeConstant.SUCCESS);
        return result;
    }

	@Override
	public Result deleteRcvAddressInfo(Long addressId) throws Exception {
		try {
			//校验数据
			AddressManageValidate.validateDeleteRcvAddressInfo(addressId);

			//校验地址是否存在
			RcvAddressPO rcvAddressPO = addressManageService.getRcvAddressInfoById(addressId);
			if(null == rcvAddressPO){
				throw new BusinessException("收货地址不存在!");
			}
			
			//删除 地址
			addressManageService.deleteRcvAddress(addressId);	
			
			//插入业务日志
			addressManagerLog.logDeleteRcvAddressInfo(rcvAddressPO);
			
			//返回结果
			Result result = new Result();
			result.setSuccess(FacadeConstant.SUCCESS);
			return result;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Result getRcvAddressInfoById(Long addressId) throws Exception {
		//校验数据
		AddressManageValidate.validateGetRcvAddressInfoById(addressId);
		
		//查询地址信息
		RcvAddressPO rcvAddressPO = addressManageService.getRcvAddressInfoById(addressId);
		if(null == rcvAddressPO){
			throw new BusinessException("收货地址不存在!");
		}
		RcvAddressDTO rcvAddressDTO = new RcvAddressDTO();
		BeanUtilEx.copyProperties(rcvAddressDTO, rcvAddressPO);
		this.getDescFromSysParam(rcvAddressDTO);
		//返回结果
		Result result = new Result();
		result.setSuccess(FacadeConstant.SUCCESS);
		result.setData(rcvAddressDTO);
		return result;
	}

	@Override
	public Result getRcvAddressListByUserId(Long userId) throws Exception {
		//校验数据
		AddressManageValidate.validateGetRcvAddressListByUserId(userId);
		
		//判断用户是否存在
		UserInfoPO userInfoPO = userManageService.getUserById(userId);
		if(null == userInfoPO){
			throw new BusinessException(ExceptionEnum.Busi.USER_NOT_EXIST.getKey(),"");
		}
		
		//查询地址列表
		RcvAddressParam rcvAddressParam = new RcvAddressParam();
		rcvAddressParam.setUserId(userId);
		List<RcvAddressPO> list = addressManageService.queryRcvAddressList(rcvAddressParam);
		List<RcvAddressDTO> resultList = new ArrayList<RcvAddressDTO>();
		for (RcvAddressPO rcvAddressPO : list) {
			RcvAddressDTO rcvAddressDTO = new RcvAddressDTO();
			BeanUtilEx.copyProperties(rcvAddressDTO, rcvAddressPO);
			this.getDescFromSysParam(rcvAddressDTO);
			resultList.add(rcvAddressDTO);
		}
	
		//返回结果
		Result result = new Result();
		result.setSuccess(FacadeConstant.SUCCESS);
		result.setData(resultList);
		return result;
	}

	@Override
	public Result getRcvAddressCountByUserId(Long userId) {
		//校验数据
		AddressManageValidate.validateGetRcvAddressCountByUserId(userId);
		
		//判断用户是否存在
		UserInfoPO userInfoPO = userManageService.getUserById(userId);
		if(null == userInfoPO){
			throw new BusinessException(ExceptionEnum.Busi.USER_NOT_EXIST.getKey(),"");
		}
		
		//得到该用户地址的总条数
		RcvAddressParam rcvAddressParam = new RcvAddressParam();
		rcvAddressParam.setUserId(userId);
		Long count = addressManageService.queryRcvAddressCount(rcvAddressParam);	
	
		//返回结果
		Result result = new Result();
		result.setSuccess(FacadeConstant.SUCCESS);
		result.setData(count);
		return result;
	}
	
	/**
	 * 取消默认地址
	 * @Title: cancalMaster 
	 * @param   
	 * @return void    返回类型 
	 * @throws
	 */
	private void cancalMaster(Long custId){
		addressManageService.modifyRcvAddressMaster(custId, null, TypeEnum.YesOrNo.NO.getKey());
	}
	
	//将Code翻译
	private void getDescFromSysParam(RcvAddressDTO rcvAddressDTO) throws Exception{
		SysParamUtil sysParamUtil = SysParamUtil.getInstance(sysParamFacade);
		if(StringUtils.isNotBlank(rcvAddressDTO.getProvinceCode())){
			rcvAddressDTO.setProvinceCodeDesc(sysParamUtil.getParamDescByCode(rcvAddressDTO.getProvinceCode()));
		}
		if(StringUtils.isNotBlank(rcvAddressDTO.getRegionCode())){
			rcvAddressDTO.setRegionCodeDesc(sysParamUtil.getParamDescByCode(rcvAddressDTO.getRegionCode()));
		}
		if(StringUtils.isNotBlank(rcvAddressDTO.getCityCode())){
			rcvAddressDTO.setCityCodeDesc(sysParamUtil.getParamDescByCode(rcvAddressDTO.getCityCode()));
			rcvAddressDTO.setCityCodeRemark(sysParamUtil.getParamRemarkByCode(rcvAddressDTO.getCityCode()));
		}
	}


}
