package com.xiu.uuc.manager.validate;

import org.apache.commons.lang.StringUtils;

import com.xiu.uuc.common.exception.BusinessException;
import com.xiu.uuc.common.exception.ParameterException;
import com.xiu.uuc.common.util.CommonValidationUtil;
import com.xiu.uuc.facade.dto.EbayUserAgreementDTO;
import com.xiu.uuc.facade.dto.LeadRegisterDTO;
import com.xiu.uuc.facade.dto.ModifyUserAuthenDTO;
import com.xiu.uuc.facade.dto.RegisterUserDTO;
import com.xiu.uuc.facade.dto.UserBaseDTO;
import com.xiu.uuc.facade.dto.UserDetailDTO;
import com.xiu.uuc.facade.util.TypeEnum;

/**
 * UserManageImpl的验证类
 * 
 * @ClassName: UserManageValidate
 * @author xiaoyingping
 * @date 2011-8-18 下午12:01:53
 * 
 */
public class UserManageValidate {

	public static void validateIsLogonNameExist(String logonName, Integer channelId) {
		if (StringUtils.isBlank(logonName)) {
			throw new ParameterException("用户登录名不能为空!");
		}
		if (CommonValidationUtil.isEmpty(channelId)) {
			throw new ParameterException("渠道标识不能为空!");
		}
	}
	
	public static void validateGetUserDetailInfoByLogonName(String logonName, Integer channelId) {
		if (StringUtils.isBlank(logonName)) {
			throw new ParameterException("用户登录名不能为空!");
		}
		if (CommonValidationUtil.isEmpty(channelId)) {
            throw new ParameterException("渠道标识不能为空!");
        }
	}

	public static void validateModifyUserState(UserBaseDTO userBaseDTO) {
		if (null == userBaseDTO) {
			throw new ParameterException("参数不能为空!");
		}
		if (CommonValidationUtil.isEmpty(userBaseDTO.getUserId())
				&& (StringUtils.isBlank(userBaseDTO.getLogonName()) || CommonValidationUtil
						.isEmpty(userBaseDTO.getChannelId()))) {
			throw new ParameterException("参数不能为空!");
		}
	}

	public static void validateModifyUserBaseInfo(UserBaseDTO userBaseDTO) {
		if (userBaseDTO == null) {
			throw new ParameterException("参数不能为空!");
		}
		String email = userBaseDTO.getEmail();
		String mobile = userBaseDTO.getMobile();
		if (!CommonValidationUtil.checkExactingLength(email, 0, 100)) {
			throw new ParameterException("Email长度不能大于100!");
		}
		if (!CommonValidationUtil.checkExactingLength(mobile, 0, 100)) {
			throw new ParameterException("手机号长度不能大于100!");
		}
		if (null != userBaseDTO.getCustLevel()) {
			if (!CommonValidationUtil.checkExactingLength(
					userBaseDTO.getCustLevel(), 1, 3)) {
				throw new ParameterException("客户等级不能为空或长度大于3!");
			}
		}
		if (!CommonValidationUtil.checkExactingLength(
				userBaseDTO.getCustName(), 0, 100)) {
			throw new ParameterException("客户姓名长度不能大于100!");
		}
		if (!CommonValidationUtil.checkExactingLength(userBaseDTO.getPetName(),
				0, 100)) {
			throw new ParameterException("呢称长度不能大于100!");
		}

		if (!StringUtils.isBlank(email) && !CommonValidationUtil.isEmail(email)) {
			throw new ParameterException("Email格式不正确!");
		}
		if (!StringUtils.isBlank(mobile) && !CommonValidationUtil.isMobileNO(mobile)) {
			throw new ParameterException("手机号格式不正确!");
		}
		if (!StringUtils.isBlank(userBaseDTO.getPetName())) {
			if (CommonValidationUtil.isEmail(userBaseDTO.getPetName())) {
				throw new ParameterException("呢称不能为Email格式!");
			}
			if (CommonValidationUtil.isMobileNO(userBaseDTO.getPetName())) {
				throw new ParameterException("呢称不能为手机号");
			}
		}
		if (StringUtils.isNotBlank(userBaseDTO.getCustLevel())
				&& !StringUtils.isNumeric(userBaseDTO.getCustLevel())) {
			throw new ParameterException("客户等级必须为数字!");
		}
		if (!CommonValidationUtil.isEmpty(userBaseDTO.getLastLogonChannelId())
				&& !TypeEnum.ChannelType.getList().containsKey(
						userBaseDTO.getLastLogonChannelId())) {
			throw new ParameterException("登录渠道标识不正确!");
		}
	}

	public static void validateModifyUserDetailInfo(UserDetailDTO userDetailDTO) {
		if (userDetailDTO == null) {
			throw new ParameterException("参数不能为空!");
		}
		String email = userDetailDTO.getEmail();
		String mobile = userDetailDTO.getMobile();
		if (!CommonValidationUtil.checkExactingLength(email, 0, 100)) {
			throw new ParameterException("Email长度不能大于100!");
		}
		if (!CommonValidationUtil.checkExactingLength(mobile, 0, 100)) {
			throw new ParameterException("手机号长度不能大于100!");
		}
		if (null != userDetailDTO.getCustLevel()) {
			if (!CommonValidationUtil.checkExactingLength(
					userDetailDTO.getCustLevel(), 1, 3)) {
				throw new ParameterException("客户等级不能为空或长度大于3!");
			}
		}
		if (!CommonValidationUtil.checkExactingLength(
				userDetailDTO.getCustName(), 0, 100)) {
			throw new ParameterException("客户姓名长度不能大于100!");
		}
		if (!CommonValidationUtil.checkExactingLength(
				userDetailDTO.getPetName(), 0, 100)) {
			throw new ParameterException("呢称长度不能大于100!");
		}
		if (!CommonValidationUtil.checkExactingLength(
				userDetailDTO.getProvinceCode(), 0, 50)) {
			throw new ParameterException("省长度不能大于50!");
		}
		if (!CommonValidationUtil.checkExactingLength(
				userDetailDTO.getRegionCode(), 0, 50)) {
			throw new ParameterException("市长度不能大于50!");
		}
		if (!CommonValidationUtil.checkExactingLength(
				userDetailDTO.getCityCode(), 0, 50)) {
			throw new ParameterException("县长度不能大于50!");
		}
		if (!CommonValidationUtil.checkExactingLength(
				userDetailDTO.getAddressInfo(), 0, 100)) {
			throw new ParameterException("街道地址长度不能大于100!");
		}
		if (!CommonValidationUtil.checkExactingLength(
				userDetailDTO.getWorkType(), 0, 100)) {
			throw new ParameterException("身份长度不能大于100!");
		}
		if (!CommonValidationUtil.checkExactingLength(userDetailDTO.getSex(),
				0, 30)) {
			throw new ParameterException("性别长度不能大于30!");
		}
		if (!CommonValidationUtil.checkExactingLength(
				userDetailDTO.getDegree(), 0, 30)) {
			throw new ParameterException("学历长度不能大于30!");
		}
		if (!CommonValidationUtil.checkExactingLength(
				userDetailDTO.getMonthIncome(), 0, 30)) {
			throw new ParameterException("月收入长度不能大于30!");
		}
		if (!CommonValidationUtil.checkExactingLength(userDetailDTO.getMarry(),
				0, 30)) {
			throw new ParameterException("婚姻状态长度不能大于30!");
		}
		if (!CommonValidationUtil.checkExactingLength(
				userDetailDTO.getRecommendId(), 0, 100)) {
			throw new ParameterException("邀请人ID长度不能大于100!");
		}
		if (!CommonValidationUtil.checkExactingLength(
				userDetailDTO.getIdCard(), 0, 100)) {
			throw new ParameterException("身份证号码长度不能大于50!");
		}
		if (!CommonValidationUtil.checkExactingLength(
				userDetailDTO.getInterest(), 0, 1024)) {
			throw new ParameterException("兴趣爱好长度不能大于1024!");
		}
		if (!CommonValidationUtil.checkExactingLength(
				userDetailDTO.getFromId(), 0, 50)) {
			throw new ParameterException("统计ID长度不能大于50!");
		}

		if (!StringUtils.isBlank(email) && !CommonValidationUtil.isEmail(email)) {
			throw new ParameterException("Email格式不正确!");
		}
		if (!StringUtils.isBlank(mobile)
				&& !CommonValidationUtil.isMobileNO(mobile)) {
			throw new ParameterException("手机号格式不正确!");
		}
		if (!StringUtils.isBlank(userDetailDTO.getPetName())) {
			if (CommonValidationUtil.isEmail(userDetailDTO.getPetName())) {
				throw new ParameterException("呢称不能为Email!");
			}
			if (CommonValidationUtil.isMobileNO(userDetailDTO.getPetName())) {
				throw new ParameterException("呢称不能为手机号");
			}
		}
		if (StringUtils.isNotBlank(userDetailDTO.getCustLevel())
				&& !StringUtils.isNumeric(userDetailDTO.getCustLevel())) {
			throw new ParameterException("客户等级必须为数字!");
		}
		if (!StringUtils.isBlank(userDetailDTO.getSubscibeInfo())
				&& !"0".equals(userDetailDTO.getSubscibeInfo())
				&& !"1".equals(userDetailDTO.getSubscibeInfo())) {
			throw new ParameterException("消息订阅不正确!");
		}
	}

	public static void validateModifyUserPassword(Long userId,
			String oldPassword, String newPassword) {
		if (CommonValidationUtil.isEmpty(userId)) {
			throw new ParameterException("用户ID不能为空!");
		}
		if (StringUtils.isBlank(oldPassword)) {
			throw new ParameterException("登录密码不能为空!");
		}
		if (StringUtils.isBlank(newPassword)) {
			throw new ParameterException("新密码不能为空!");
		}
	}

	public static void validateResetUserPassword(Long userId,
			String newPassword, String operId) {
		if (CommonValidationUtil.isEmpty(userId)) {
			throw new ParameterException("用户ID不能为空!");
		}
		if (StringUtils.isBlank(newPassword)) {
			throw new ParameterException("新密码不能为空!");
		}
		if (StringUtils.isBlank(operId)) {
			throw new ParameterException("操作员ID不能为空!");
		}
	}

	public static void validateGetPasswordByLogonNameAndChannelId(
			String logonName, Integer channelId) throws BusinessException {
		if (StringUtils.isBlank(logonName)) {
			throw new ParameterException("登陆名不能为空!");
		}
		if (!TypeEnum.ChannelType.getList().containsKey(channelId)) {
			throw new ParameterException("渠道标识不正确!");
		}
	}

	public static void validateIsRightPassword(Long userId, String password)
			throws BusinessException {
		if (CommonValidationUtil.isEmpty(userId)) {
			throw new ParameterException("用户ID不能为空!");
		}
		if (StringUtils.isBlank(password)) {
			throw new ParameterException("登录密码不能为空!");
		}
	}

	public static void validateGetUserInTargetChannel(Long custId,
			Integer targetChannelId) throws BusinessException {
		if (CommonValidationUtil.isEmpty(custId)) {
			throw new ParameterException("客户ID不能为空!");
		}
		if (!TypeEnum.ChannelType.getList().containsKey(targetChannelId)) {
			throw new ParameterException("目标渠道标识不正确!");
		}
	}

	// 校验用户注册的参数
	public static RegisterUserDTO validateRegisterUser(RegisterUserDTO registerUserDTO) {
		
		if (registerUserDTO == null) {
			throw new ParameterException("参数不能为空!");
		}
		
		String logonName = registerUserDTO.getLogonName();
		String password = registerUserDTO.getPassword();
		Integer channelId = registerUserDTO.getChannelId();
		String registerType = registerUserDTO.getRegisterType();
		String custType = registerUserDTO.getCustType();

		if (StringUtils.isBlank(logonName)) {
			throw new ParameterException("用户登录名不能为空!");
		}
		if (StringUtils.isBlank(password)) {
			throw new ParameterException("用户登录密码不能为空!");
		}
		if (!CommonValidationUtil.checkExactingLength(logonName, 1, 100)) {
			throw new ParameterException("用户登录名长度不能大于100!");
		}
		if (!CommonValidationUtil.checkExactingLength(password, 1, 100)) {
			throw new ParameterException("用户登录密码长度不能大于100!");
		}
		if (registerType.equals(TypeEnum.RegisterType.EMAIL.getKey())
				&& !CommonValidationUtil.isEmail(logonName)) {
			throw new ParameterException("Email格式不正确!");
		} else if (registerType.equals(TypeEnum.RegisterType.MOBILE.getKey())
				&& !CommonValidationUtil.isMobileNO(logonName)) {
			throw new ParameterException("手机号格式不正确!");
		} else if (registerType.equals(TypeEnum.RegisterType.PETNAME.getKey())) {
			if (CommonValidationUtil.isEmail(logonName)) {
				throw new ParameterException("呢称不能为Email!");
			}
			if (CommonValidationUtil.isMobileNO(logonName)) {
				throw new ParameterException("呢称不能为手机号!");
			}
		}
		if (!TypeEnum.ChannelType.getList().containsKey(channelId)) {
			throw new ParameterException("渠道标识不正确!");
		}
		if (!TypeEnum.RegisterType.getList().containsKey(registerType)) {
			throw new ParameterException("注册类型不正确!");
		}
		if (!TypeEnum.CustType.getList().containsKey(custType)) {
			throw new ParameterException("客户类型不正确!");
		}
		if (custType.equals(TypeEnum.CustType.PARTNER.getKey())) {
			Integer partnerChannelId = registerUserDTO.getPartnerChannelId();
			if (StringUtils.isBlank(registerUserDTO.getPartnerId())) {
				throw new ParameterException("联盟客户ID不能为空!");
			}
			if (CommonValidationUtil.isEmpty(partnerChannelId)) {
				throw new ParameterException("联盟渠道标识不能为空!");
			}
			if (!CommonValidationUtil.checkExactingLength(
					registerUserDTO.getPartnerId(), 1, 50)) {
				throw new ParameterException("联盟客户ID长度不能大于50!");
			}
			if (!TypeEnum.PartnerChannelType.getList().containsKey(
					partnerChannelId)) {
				throw new ParameterException("联盟渠道标识不正确!");
			}
		}
		if (StringUtils.isBlank(registerUserDTO.getRegisterIp())) {
			throw new ParameterException("注册IP不能为空!");
		}
		if (!CommonValidationUtil.isIp(registerUserDTO.getRegisterIp())) {
			throw new ParameterException("注册IP格式不正确!");
		}
		return registerUserDTO;
	}

	public static void validateLeadPartnerRegister(
			LeadRegisterDTO leadRegisterDTO) {
		if (CommonValidationUtil.isEmpty(leadRegisterDTO.getUserId())) {
			throw new ParameterException("用户ID不能为空!");
		}
		if (StringUtils.isBlank(leadRegisterDTO.getPassword())) {
			throw new ParameterException("登录密码不能为空!");
		}

		String email = leadRegisterDTO.getEmail();
		String mobile = leadRegisterDTO.getMobile();
		if (StringUtils.isNotBlank(email)) {
			if (!CommonValidationUtil.isEmail(email)) {
				throw new ParameterException("邮箱格式不正确!");
			}
		} else if (StringUtils.isNotBlank(mobile)) {
			if (!CommonValidationUtil.isMobileNO(mobile)) {
				throw new ParameterException("手机号格式不正确!");
			}
		} else {
			throw new ParameterException("用户名不能为空!");
		}
	}

	public static void validateModifyUserAuthen(ModifyUserAuthenDTO modifyUserAuthenDTO) {
		if (CommonValidationUtil.isEmpty(modifyUserAuthenDTO.getUserId())) {
			throw new ParameterException("用户ID不能为空!");
		}
		if (!TypeEnum.AuthenType.getList().containsKey(modifyUserAuthenDTO.getAuthenType())) {
			throw new ParameterException("认证类型不正确!");
		}
		if (StringUtils.isBlank(modifyUserAuthenDTO.getAuthenValue())) {
			throw new ParameterException("认证值不能为空!");
		}
		if (!CommonValidationUtil.checkExactingLength(modifyUserAuthenDTO.getAuthenValue(), 1, 100)) {
			throw new ParameterException("认证值长度不能大于100!");
		}
		if (modifyUserAuthenDTO.getAuthenType().equals(TypeEnum.AuthenType.EMAIL.getKey())
				&& !CommonValidationUtil.isEmail(modifyUserAuthenDTO.getAuthenValue())) {
			throw new ParameterException("Email格式不正确!");
		} else if (modifyUserAuthenDTO.getAuthenType().equals(TypeEnum.AuthenType.MOBILE.getKey())
				&& !CommonValidationUtil.isMobileNO(modifyUserAuthenDTO.getAuthenValue())) {
			throw new ParameterException("手机号格式不正确!");
		}
	}
	
	public static void validateModifyEbayUserAgreement(EbayUserAgreementDTO ebayUserAgreementDTO) {
		if (CommonValidationUtil.isEmpty(ebayUserAgreementDTO.getUserId())) {
			throw new ParameterException("用户ID不能为空!");
		}
		if (StringUtils.isBlank(ebayUserAgreementDTO.getEbayUserAgreement())) {
			throw new ParameterException("eBay用户协议是否同意标志不能为空!");
		}
	}

	public static void validateIsAuthentic(Long userId, String authenType) {
		if (CommonValidationUtil.isEmpty(userId)) {
			throw new ParameterException("用户ID不能为空!");
		}
		if (StringUtils.isBlank(authenType)) {
			throw new ParameterException("参数异常!");
		}
	}

}
