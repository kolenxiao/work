package com.xiu.uuc.facade.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.xiu.commons.core.BaseExternalService;
import com.xiu.uuc.common.exception.BusinessException;
import com.xiu.uuc.common.exception.ParameterException;
import com.xiu.uuc.common.util.CommonEnum;
import com.xiu.uuc.common.util.JsonUtils;
import com.xiu.uuc.facade.UserManageFacade;
import com.xiu.uuc.facade.dto.EbayUserAgreementDTO;
import com.xiu.uuc.facade.dto.LeadRegisterDTO;
import com.xiu.uuc.facade.dto.ModifyUserAuthenDTO;
import com.xiu.uuc.facade.dto.RegisterUserDTO;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.dto.UserBaseDTO;
import com.xiu.uuc.facade.dto.UserDetailDTO;
import com.xiu.uuc.facade.dto.UserQueryDTO;
import com.xiu.uuc.manager.UserManager;
import com.xiu.uuc.manager.util.CommonUtils;
import com.xiu.uuc.manager.util.ExceptionProcessor;

public class UserManageFacadeImpl extends BaseExternalService implements
		UserManageFacade {

	private static final Logger logger = LoggerFactory.getLogger(UserManageFacadeImpl.class);

	@Autowired
	private UserManager userManager;
	private JsonUtils jsonUtils = new JsonUtils();

	@Override
	public Result isLogonNameExist(String logonName, Integer channelId) {
		logger.info("isLogonNameExist entry: logonName={}, channelId={}",
				new Object[] { logonName, channelId });
		long stime = System.currentTimeMillis();
		
		Result result = null;
		try {
			result = userManager.isLogonNameExist(logonName, channelId);
			logger.info("isLogonNameExist success: logonName={}, userId={}, costTime={}ms",
					new Object[] { logonName, result.getData(), System.currentTimeMillis() - stime });
			return result;
		} catch (ParameterException e) {
			return ExceptionProcessor.getParameterExceptionResult(e);
		} catch (BusinessException e) {
			return ExceptionProcessor.getBusinessExceptionResult(e);
		} catch (Exception e) {
			return ExceptionProcessor.getExceptionResult(e);
		}
	}

	@Override
	public Result isLogonNameExist(String logonName) {
		logger.info("isLogonNameExist entry: logonName={}", logonName);
		long stime = System.currentTimeMillis();
		
		Result result = null;
		try {
			result = userManager.isLogonNameExist(logonName);
			logger.info("isLogonNameExist success: logonName={}, isExist={}, costTime={}ms",
					new Object[] { logonName, result.getData(), System.currentTimeMillis() - stime });
			return result;
		} catch (ParameterException e) {
			return ExceptionProcessor.getParameterExceptionResult(e);
		} catch (BusinessException e) {
			return ExceptionProcessor.getBusinessExceptionResult(e);
		} catch (Exception e) {
			return ExceptionProcessor.getExceptionResult(e);
		}
	}

	@Override
	public Result registerUser(RegisterUserDTO registerUserDTO) {
		long stime = System.currentTimeMillis();
		Result result = null;
		try {
			result = userManager.registerUser(registerUserDTO);
			logger.info("registerUser success: logonName={}, userId={}, costTime={}ms",
					new Object[] { registerUserDTO.getLogonName(), result.getData(), System.currentTimeMillis() - stime });
			return result;
		} catch (ParameterException e) {
			return ExceptionProcessor.getParameterExceptionResult(e);
		} catch (BusinessException e) {
			return ExceptionProcessor.getBusinessExceptionResult(e);
		} catch (Exception e) {
			return ExceptionProcessor.getExceptionResult(e);
		}
	}

	@Override
	public Result activateUserState(UserBaseDTO userBaseDTO) {
		logger.info("activateUserState entry: userId={}",
				userBaseDTO.getUserId());
		long stime = System.currentTimeMillis();

		Result result = null;
		try {
			result = userManager.modifyUserState(userBaseDTO, CommonEnum.BusiOperType.ACTIVATE_USER.getKey());
			logger.info("activateUserState success: userId={}, costTime={}ms",
					new Object[] { userBaseDTO.getUserId(), System.currentTimeMillis() - stime });
			return result;
		} catch (ParameterException e) {
			return ExceptionProcessor.getParameterExceptionResult(e);
		} catch (BusinessException e) {
			return ExceptionProcessor.getBusinessExceptionResult(e);
		} catch (Exception e) {
			return ExceptionProcessor.getExceptionResult(e);
		}
	}

	@Override
	public Result forbidUser(UserBaseDTO userBaseDTO) {
		logger.info("forbidUser entry: userId={}", userBaseDTO.getUserId());
		long stime = System.currentTimeMillis();

		Result result = null;
		try {
			result = userManager.modifyUserState(userBaseDTO, CommonEnum.BusiOperType.FORBIT_USER.getKey());
			logger.info("forbidUser success: userId={}, costTime={}ms",
					new Object[] { userBaseDTO.getUserId(), System.currentTimeMillis() - stime });
			return result;
		} catch (ParameterException e) {
			return ExceptionProcessor.getParameterExceptionResult(e);
		} catch (BusinessException e) {
			return ExceptionProcessor.getBusinessExceptionResult(e);
		} catch (Exception e) {
			return ExceptionProcessor.getExceptionResult(e);
		}
	}

	@Override
	public Result unForbidUser(UserBaseDTO userBaseDTO) {
		logger.info("unForbidUser entry: userId={}", userBaseDTO.getUserId());
		long stime = System.currentTimeMillis();

		Result result = null;
		try {
			result = userManager.modifyUserState(userBaseDTO, CommonEnum.BusiOperType.UNFORBIT_USER.getKey());
			logger.info("unForbidUser success: userId={}, costTime={}ms",
					new Object[] { userBaseDTO.getUserId(), System.currentTimeMillis() - stime });
			return result;
		} catch (ParameterException e) {
			return ExceptionProcessor.getParameterExceptionResult(e);
		} catch (BusinessException e) {
			return ExceptionProcessor.getBusinessExceptionResult(e);
		} catch (Exception e) {
			return ExceptionProcessor.getExceptionResult(e);
		}
	}

	@Override
	public Result deleteUser(UserBaseDTO userBaseDTO) {
		logger.info("deleteUser entry: userId={}", userBaseDTO.getUserId());
		long stime = System.currentTimeMillis();

		Result result = null;
		try {
			result = userManager.modifyUserState(userBaseDTO, CommonEnum.BusiOperType.DELETE_USER.getKey());
			logger.info("deleteUser success: userId={}, costTime={}ms",
					new Object[] { userBaseDTO.getUserId(), System.currentTimeMillis() - stime });
			return result;
		} catch (ParameterException e) {
			return ExceptionProcessor.getParameterExceptionResult(e);
		} catch (BusinessException e) {
			return ExceptionProcessor.getBusinessExceptionResult(e);
		} catch (Exception e) {
			return ExceptionProcessor.getExceptionResult(e);
		}
	}

	@Override
	public Result modifyUserBaseInfo(UserBaseDTO userBaseDTO) {
		logger.info("modifyUserBaseInfo entry: userBaseDTO={}",
				jsonUtils.toJson(userBaseDTO));
		long stime = System.currentTimeMillis();

		Result result = null;
		try {
			result = userManager.modifyUserBaseInfo(userBaseDTO);
			logger.info("modifyUserBaseInfo success: userId={}, costTime={}ms",
					new Object[] { userBaseDTO.getUserId(), System.currentTimeMillis() - stime });
			return result;
		} catch (ParameterException e) {
			return ExceptionProcessor.getParameterExceptionResult(e);
		} catch (BusinessException e) {
			return ExceptionProcessor.getBusinessExceptionResult(e);
		} catch (Exception e) {
			return ExceptionProcessor.getExceptionResult(e);
		}
	}

	@Override
	public Result modifyUserDetailInfo(UserDetailDTO userDetailDTO) {
		logger.info("modifyUserDetailInfo entry: userDetailDTO={}",
				jsonUtils.toJson(userDetailDTO));
		long stime = System.currentTimeMillis();

		Result result = null;
		try {
			result = userManager.modifyUserDetailInfo(userDetailDTO);
			logger.info("modifyUserDetailInfo success: userId={}, costTime={}ms",
					new Object[] { userDetailDTO.getUserId(), System.currentTimeMillis() - stime });
			return result;
		} catch (ParameterException e) {
			return ExceptionProcessor.getParameterExceptionResult(e);
		} catch (BusinessException e) {
			return ExceptionProcessor.getBusinessExceptionResult(e);
		} catch (Exception e) {
			return ExceptionProcessor.getExceptionResult(e);
		}
	}

	@Override
	public Result modifyUserPassword(Long userId, String oldPassword, String newPassword) {
		logger.info("modifyUserPassword entry: userId={}", userId);
		long stime = System.currentTimeMillis();

		Result result = null;
		try {
			result = userManager.modifyUserPassword(userId, oldPassword, newPassword);
			logger.info("modifyUserPassword success: userId={}, costTime={}ms",
					new Object[] { userId, System.currentTimeMillis() - stime });
			return result;
		} catch (ParameterException e) {
			return ExceptionProcessor.getParameterExceptionResult(e);
		} catch (BusinessException e) {
			return ExceptionProcessor.getBusinessExceptionResult(e);
		} catch (Exception e) {
			return ExceptionProcessor.getExceptionResult(e);
		}
	}

	@Override
	public Result resetUserPassword(Long userId, String newPassword, String operId, String remark) {
		logger.info("resetUserPassword entry: userId={}, operId={}, remark={}",
				new Object[] { userId, operId, remark });
		long stime = System.currentTimeMillis();

		Result result = null;
		try {
			result = userManager.resetUserPassword(userId, newPassword, operId, remark);
			logger.info("resetUserPassword success: userId={}, costTime={}ms",
					new Object[] { userId, System.currentTimeMillis() - stime });
			return result;
		} catch (ParameterException e) {
			return ExceptionProcessor.getParameterExceptionResult(e);
		} catch (BusinessException e) {
			return ExceptionProcessor.getBusinessExceptionResult(e);
		} catch (Exception e) {
			return ExceptionProcessor.getExceptionResult(e);
		}
	}

	@Override
	public Result modifyUserAuthen(ModifyUserAuthenDTO modifyUserAuthenDTO) {        
        long stime = System.currentTimeMillis();
        logger.info("modifyUserAuthen entry: {}", jsonUtils.toJson(modifyUserAuthenDTO));

		Result result = null;
		try {
            result = userManager.modifyUserAuthen(modifyUserAuthenDTO);
            logger.info("modifyUserAuthen success: {}, costTime={}ms",
                    new Object[] { jsonUtils.toJson(modifyUserAuthenDTO), System.currentTimeMillis() - stime });
            return result;
		} catch (ParameterException e) {
			return ExceptionProcessor.getParameterExceptionResult(e);
		} catch (BusinessException e) {
			return ExceptionProcessor.getBusinessExceptionResult(e);
		} catch (Exception e) {
			return ExceptionProcessor.getExceptionResult(e);
		}
	}

	@Override
	public Result isAuthentic(Long userId, String authenType) {
		logger.info("isAuthentic entry: userId={}, authenType={}",
				new Object[] { userId, authenType });
		long stime = System.currentTimeMillis();

		Result result = null;
		try {
			result = userManager.isAuthentic(userId, authenType);
			logger.info("isAuthentic success: userId={}, authenType={}, costTime={}ms",
					new Object[] { userId, authenType, System.currentTimeMillis() - stime });
			return result;
		} catch (ParameterException e) {
			return ExceptionProcessor.getParameterExceptionResult(e);
		} catch (BusinessException e) {
			return ExceptionProcessor.getBusinessExceptionResult(e);
		} catch (Exception e) {
			return ExceptionProcessor.getExceptionResult(e);
		}
	}

	@Override
	public Result getUserBasicInfoByUserId(Long userId) {
		logger.info("getUserBasicInfoByUserId entry: userId={}", userId);
		long stime = System.currentTimeMillis();

		try {
			Result result = userManager.getUserBasicInfoByUserId(userId);
			if (logger.isDebugEnabled()) {
				logger.debug("getUserBasicInfoByUserId success: {}, costTime={}ms",
						new Object[] { result.getData(), System.currentTimeMillis() - stime });
			} else if (logger.isInfoEnabled()) {
				logger.info("getUserBasicInfoByUserId success: userId={}, costTime={}ms",
						new Object[] { userId, System.currentTimeMillis() - stime });
			}
			return result;
		} catch (ParameterException e) {
			return ExceptionProcessor.getParameterExceptionResult(e);
		} catch (BusinessException e) {
			return ExceptionProcessor.getBusinessExceptionResult(e);
		} catch (Exception e) {
			return ExceptionProcessor.getExceptionResult(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Result getUserBaseInfoList(UserQueryDTO userQueryDTO) {
		logger.info("getUserBaseInfoList entry: userQueryDTO={} ",
				jsonUtils.toJson(userQueryDTO));
		long stime = System.currentTimeMillis();

		try {
			Result result = userManager.getUserBaseInfoList(userQueryDTO);
			List<UserBaseDTO> resultList = (List<UserBaseDTO>) result.getData();
			logger.info("getUserBaseInfoList success: count={}, userId[]={}, costTime={}ms",
					new Object[] {resultList.size(),
							CommonUtils.collectByPropertyName(resultList, "userId"),
							System.currentTimeMillis() - stime });
			return result;
		} catch (ParameterException e) {
			return ExceptionProcessor.getParameterExceptionResult(e);
		} catch (BusinessException e) {
			return ExceptionProcessor.getBusinessExceptionResult(e);
		} catch (Exception e) {
			return ExceptionProcessor.getExceptionResult(e);
		}
	}

	@Override
	public Result getUserDetailInfoByUserId(Long userId) {
		logger.info("getUserDetailInfoByUserId entry: userId={}", userId);
		long stime = System.currentTimeMillis();

		Result result = null;
		try {
			result = userManager.getUserDetailInfoByUserId(userId);
			if (logger.isDebugEnabled()) {
				logger.debug("getUserDetailInfoByUserId success: {}, costTime={}ms",
						new Object[] { result.getData(), System.currentTimeMillis() - stime });
			} else if (logger.isInfoEnabled()) {
				logger.info("getUserDetailInfoByUserId success: userId={}, costTime={}ms",
						new Object[] { userId, System.currentTimeMillis() - stime });
			}
			return result;
		} catch (ParameterException e) {
			return ExceptionProcessor.getParameterExceptionResult(e);
		} catch (BusinessException e) {
			return ExceptionProcessor.getBusinessExceptionResult(e);
		} catch (Exception e) {
			return ExceptionProcessor.getExceptionResult(e);
		}
	}
	
	public Result getUserDetailInfoByLogonName(String logonName, Integer channelId){
		logger.info("getUserDetailInfoByLogonName entry: logonName={}, channelId={}",
				new Object[] { logonName, channelId });
		long stime = System.currentTimeMillis();

		Result result = null;
		try {
			result = userManager.getUserDetailInfoByLogonName(logonName, channelId);
			if (logger.isDebugEnabled()) {
				logger.debug("getUserDetailInfoByLogonName success: {}, costTime={}ms",
						new Object[] { result.getData(), System.currentTimeMillis() - stime });
			} else if (logger.isInfoEnabled()) {
				logger.info("getUserDetailInfoByLogonName success: logonName={}, channelId={}, costTime={}ms",
						new Object[] { logonName, channelId, System.currentTimeMillis() - stime });
			}
			return result;
		} catch (ParameterException e) {
			return ExceptionProcessor.getParameterExceptionResult(e);
		} catch (BusinessException e) {
			return ExceptionProcessor.getBusinessExceptionResult(e);
		} catch (Exception e) {
			return ExceptionProcessor.getExceptionResult(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Result getUserDetailInfoList(UserQueryDTO userQueryDTO) {
		logger.info("getUserDetailInfoList entry: userQueryDTO={}",
				jsonUtils.toJson(userQueryDTO));
		long stime = System.currentTimeMillis();

		try {
			Result result = userManager.getUserDetailInfoList(userQueryDTO);
			List<UserDetailDTO> resultList = (List<UserDetailDTO>) result.getData();
			logger.info("getUserDetailInfoList success: count={}, userId[]={}, costTime={}ms",
					new Object[] {resultList.size(),
							CommonUtils.collectByPropertyName(resultList, "userId"),
							System.currentTimeMillis() - stime });
			return result;
		} catch (ParameterException e) {
			return ExceptionProcessor.getParameterExceptionResult(e);
		} catch (BusinessException e) {
			return ExceptionProcessor.getBusinessExceptionResult(e);
		} catch (Exception e) {
			return ExceptionProcessor.getExceptionResult(e);
		}
	}

	@Override
	public Result getPasswordByLogonNameAndChannelId(String logonName,
			Integer channelId) {
		logger.info("getPasswordByLogonNameAndChannelId entry: logonName={}, channelId={}",
				new Object[] { logonName, channelId });
		long stime = System.currentTimeMillis();

		Result result = null;
		try {
			result = userManager.getPasswordByLogonNameAndChannelId(logonName, channelId);
			logger.info("getPasswordByLogonNameAndChannelId success: logonName={}, channelId={},  password={}, costTime={}ms",
					new Object[] { logonName, channelId, result.getData(), System.currentTimeMillis() - stime });
			return result;
		} catch (ParameterException e) {
			return ExceptionProcessor.getParameterExceptionResult(e);
		} catch (BusinessException e) {
			return ExceptionProcessor.getBusinessExceptionResult(e);
		} catch (Exception e) {
			return ExceptionProcessor.getExceptionResult(e);
		}
	}

	@Override
	public Result isRightPassword(Long userId, String password) {
		logger.info("isRightPassword entry: userId={}", userId);
		long stime = System.currentTimeMillis();

		Result result = null;
		try {
			result = userManager.isRightPassword(userId, password);
			logger.info("isRightPassword success: userId={}, data={}, costTime={}ms",
					new Object[] { userId, result.getData(), System.currentTimeMillis() - stime });
			return result;
		} catch (ParameterException e) {
			return ExceptionProcessor.getParameterExceptionResult(e);
		} catch (BusinessException e) {
			return ExceptionProcessor.getBusinessExceptionResult(e);
		} catch (Exception e) {
			return ExceptionProcessor.getExceptionResult(e);
		}
	}

	@Override
	public Result getUserInTargetChannel(Long userId, Integer targetChannelId) {
		logger.info("getUserInTargetChannel entry: userId={}, targetChannelId={}",
				new Object[] { userId, targetChannelId });
		long stime = System.currentTimeMillis();

		Result result = null;
		try {
			result = userManager
					.getUserInTargetChannel(userId, targetChannelId);
			if (logger.isDebugEnabled()) {
				logger.debug("getUserInTargetChannel success: userId={}, targetChannelId={}, {}, costTime={}ms",
						new Object[] { userId, targetChannelId, result.getData(), System.currentTimeMillis() - stime });
			} else if (logger.isInfoEnabled()) {
				logger.info("getUserInTargetChannel success: userId={}, targetChannelId={}, costTime={}ms",
						new Object[] { userId, targetChannelId, System.currentTimeMillis() - stime });
			}
			return result;
		} catch (ParameterException e) {
			return ExceptionProcessor.getParameterExceptionResult(e);
		} catch (BusinessException e) {
			return ExceptionProcessor.getBusinessExceptionResult(e);
		} catch (Exception e) {
			return ExceptionProcessor.getExceptionResult(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Result getUserChannelList(UserQueryDTO userQueryDTO) {
		logger.info("getUserChannelList entry: userQueryDTO={}", jsonUtils.toJson(userQueryDTO));
		long stime = System.currentTimeMillis();

		Result result = null;
		try {
			result = userManager.getUserChannelList(userQueryDTO);
			List<UserDetailDTO> resultList = (List<UserDetailDTO>) result
					.getData();
			logger.info(
					"getUserChannelList success: count={}, userId[]={}, costTime={}ms",
					new Object[] {
							resultList.size(),
							CommonUtils.collectByPropertyName(resultList,
									"userId"),
							System.currentTimeMillis() - stime });
			return result;
		} catch (ParameterException e) {
			return ExceptionProcessor.getParameterExceptionResult(e);
		} catch (BusinessException e) {
			return ExceptionProcessor.getBusinessExceptionResult(e);
		} catch (Exception e) {
			return ExceptionProcessor.getExceptionResult(e);
		}
	}

	@Override
	public Result leadPartnerRegister(LeadRegisterDTO leadRegisterDTO) {
		long stime = System.currentTimeMillis();
		Result result = null;
		try {
			result = userManager.leadPartnerRegister(leadRegisterDTO);
			logger.info("leadPartnerRegister success: userId={}, costTime={}ms",
					new Object[] { leadRegisterDTO.getUserId(), System.currentTimeMillis() - stime });
			return result;
		} catch (ParameterException e) {
			return ExceptionProcessor.getParameterExceptionResult(e);
		} catch (BusinessException e) {
			return ExceptionProcessor.getBusinessExceptionResult(e);
		} catch (Exception e) {
			return ExceptionProcessor.getExceptionResult(e);
		}
	}
	
	@Override
	public Result modifyEbayUserAgreement(EbayUserAgreementDTO ebayUserAgreementDTO){
		long stime = System.currentTimeMillis();
        logger.info("modifyEbayUserAgreement entry: {}", jsonUtils.toJson(ebayUserAgreementDTO));
		Result result = null;
		try {
            result = userManager.modifyEbayUserAgreement(ebayUserAgreementDTO);
            logger.info("modifyEbayUserAgreement success: {}, costTime={}ms",
                    new Object[] { jsonUtils.toJson(ebayUserAgreementDTO), System.currentTimeMillis() - stime });
            return result;
		} catch (ParameterException e) {
			return ExceptionProcessor.getParameterExceptionResult(e);
		} catch (BusinessException e) {
			return ExceptionProcessor.getBusinessExceptionResult(e);
		} catch (Exception e) {
			return ExceptionProcessor.getExceptionResult(e);
		}
	}

}
