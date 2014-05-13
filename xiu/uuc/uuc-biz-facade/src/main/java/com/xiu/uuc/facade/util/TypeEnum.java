package com.xiu.uuc.facade.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 所有的枚举类型
 * @ClassName: TypeEnum 
 * @author xiaoyingping
 * @date 2011-8-11 下午03:45:22 
 *
 */
public class TypeEnum {

	/**
	 * 是否XXX
	 * @ClassName: YesOrNo 
	 * @author xiaoyingping
	 * @date 2011-8-11 下午04:09:44 
	 */
	public enum YesOrNo {
		
		YES("Y", "是"), NO("N", "否");

		private final String key;
		private final String value;
		
		private YesOrNo(String key, String value) {
			this.key = key;
			this.value = value;
		}
		
		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

		public static Map<String, String> getList() {
			Map<String, String> map = new HashMap<String, String>();
			for (YesOrNo e : YesOrNo.values()) {
				map.put(String.valueOf(e.getKey()), e.getValue());
			}
			return map;
		}
	}

	/**
	 * 渠道类型
	 * @ClassName: ChannelType 
	 * @author xiaoyingping
	 * @date 2011-8-11 下午04:09:44 
	 */
	public enum ChannelType {
		
		XIU_OFFICIAL(Integer.valueOf(System.getProperty("channelType.xiuOfficial")), "秀官网"), 
		XIU_GROUP(Integer.valueOf(System.getProperty("channelType.xiuGroup")), "秀团"), 
		GROUP_GOODS(Integer.valueOf(System.getProperty("channelType.groupGoods")), "团货"),
		PART_SELL(15, "分销");

		private final Integer key;
		private final String value;
		
		private ChannelType(Integer key, String value) {
			this.key = key;
			this.value = value;
		}
		
		public Integer getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

		public static Map<Integer, String> getList() {
			Map<Integer, String> map = new HashMap<Integer, String>();
			for (ChannelType e : ChannelType.values()) {
				map.put(Integer.valueOf(e.getKey()), e.getValue());
			}
			return map;
		}
	}

	/**
	 * 联盟渠道类型
	 * @ClassName: PartnerChannelType 
	 * @author xiaoyingping
	 * @date 2011-8-11 下午04:09:44 
	 */
	public enum PartnerChannelType {
		
		MOBILE139(501, "秀官网:139"), 
		ALIPAY(502, "秀官网:支付宝"),
		MANGOCITY(503, "秀官网:芒果网"), 
		WANLITONG(504, "秀官网:万里通"),
		SINA_WEIBO_XIU(507, "秀官网:新浪微博"),
		FAN_LI_51(508, "秀官网:51返利"),
		NETEASE(509,"秀官网:网易"),
		TENCENT_CAIBEI(510,"秀官网:qq彩贝"),
		
		ACCT360(601, "秀团:360账号"), 
		SINA_WEIBO(602, "秀团:新浪微博"), 
		USERGROUP800(603, "秀团:用团800"), 
		BAIDU(604, "秀团:百度"), 
		ACCT2345(605, "秀团:2345账号"), 
		NET163(606, "秀团:网易"), 
		ALIPAY_XIUTUAN(607, "秀团:支付宝"),
		XIUTUAN139(608,"秀团:139"),
		XIUTUANQQ(609,"秀团:QQ");

		private final Integer key;
		private final String value;
		
		private PartnerChannelType(int key, String value) {
			this.key = key;
			this.value = value;
		}
		
		public Integer getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

		public static Map<Integer, String> getList() {
			Map<Integer, String> map = new HashMap<Integer, String>();
			for (PartnerChannelType e : PartnerChannelType.values()) {
				map.put(Integer.valueOf(e.getKey()), e.getValue());
			}
			return map;
		}
	}

	/**
	 * 客户类型
	 * @ClassName: CustType 
	 * @author xiaoyingping
	 * @date 2011-8-11 下午04:09:44 
	 */
	public enum CustType {
		
		XIU("01", "秀用户"), PARTNER("02", "联盟用户");

		private final String key;
		private final String value;
		
		private CustType(String key, String value) {
			this.key = key;
			this.value = value;
		}
		
		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

		public static Map<String, String> getList() {
			Map<String, String> map = new HashMap<String, String>();
			for (CustType e : CustType.values()) {
				map.put(String.valueOf(e.getKey()), e.getValue());
			}
			return map;
		}
	}

	/**
	 * 注册类型
	 * @ClassName: RegisterType 
	 * @author xiaoyingping
	 * @date 2011-8-15 下午02:46:04 
	 */
	public enum RegisterType {
		
		EMAIL("01", "邮箱注册"), MOBILE("02", "手机号注册"), PETNAME("03", "昵称注册");

		private final String key;
		private final String value;
		
		private RegisterType(String key, String value) {
			this.key = key;
			this.value = value;
		}
		
		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

		public static Map<String, String> getList() {
			Map<String, String> map = new HashMap<String, String>();
			for (RegisterType e : RegisterType.values()) {
				map.put(String.valueOf(e.getKey()), e.getValue());
			}
			return map;
		}
	}

	/**
	 * 客户状态
	 * @ClassName: CustState 
	 * @author xiaoyingping
	 * @date 2011-8-15 下午02:46:04 
	 */
	public enum CustState {
		
		NOT_ACTIVATION("00", "未激活"), NATURAL("01", "正常"), DELETED("04", "已删除");

		private final String key;
		private final String value;
		
		private CustState(String key, String value) {
			this.key = key;
			this.value = value;
		}
		
		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

		public static Map<String, String> getList() {
			Map<String, String> map = new HashMap<String, String>();
			for (CustState e : CustState.values()) {
				map.put(String.valueOf(e.getKey()), e.getValue());
			}
			return map;
		}
	}

	/**
	 * 用户状态
	 * @ClassName: UserState 
	 * @author xiaoyingping
	 * @date 2011-8-15 下午02:46:04 
	 */
	public enum UserState {
		
		NATURAL("01", "正常"), FORBID("03", "已禁用"), DELETED("04", "已删除");

		private final String key;
		private final String value;
		
		private UserState(String key, String value) {
			this.key = key;
			this.value = value;
		}
		
		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

		public static Map<String, String> getList() {
			Map<String, String> map = new HashMap<String, String>();
			for (UserState e : UserState.values()) {
				map.put(String.valueOf(e.getKey()), e.getValue());
			}
			return map;
		}
	}

	/**
	 * 用户帐户状态
	 * @ClassName: UserAcctState 
	 * @author xiaoyingping
	 * @date 2011-8-15 下午02:46:04 
	 */
	public enum UserAcctState {
		
		NATURAL("01", "正常"), FORBID("02", "冻结");

		private final String key;
		private final String value;
		
		private UserAcctState(String key, String value) {
			this.key = key;
			this.value = value;
		}
		
		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

		public static Map<String, String> getList() {
			Map<String, String> map = new HashMap<String, String>();
			for (UserAcctState e : UserAcctState.values()) {
				map.put(String.valueOf(e.getKey()), e.getValue());
			}
			return map;
		}
	}

	/**
	 * 帐目状态
	 * @ClassName: AcctItemState 
	 * @author xiaoyingping
	 * @date 2011-8-15 下午02:46:04 
	 */
	public enum AcctItemState {
		
		NATURAL("01", "正常"), FORBID("02", "禁用");

		private final String key;
		private final String value;
		
		private AcctItemState(String key, String value) {
			this.key = key;
			this.value = value;
		}
		
		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

		public static Map<String, String> getList() {
			Map<String, String> map = new HashMap<String, String>();
			for (AcctItemState e : AcctItemState.values()) {
				map.put(String.valueOf(e.getKey()), e.getValue());
			}
			return map;
		}
	}

	/**
	 * 账目类型
	 * @ClassName: AcctItemType 
	 * @author xiaoyingping
	 * @date 2011-8-15 下午02:46:04 
	 */
	public enum AcctItemType {
		
		WITHDRAWAL_YES("01", "可提现"), WITHDRAWAL_NO("02", "不可提现"), INTEGRAL("03", "积分") , CHANGE_PAY("04", "换货金");

		private final String key;
		private final String value;
		
		private AcctItemType(String key, String value) {
			this.key = key;
			this.value = value;
		}
		
		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

		public static Map<String, String> getList() {
			Map<String, String> map = new HashMap<String, String>();
			for (AcctItemType e : AcctItemType.values()) {
				map.put(String.valueOf(e.getKey()), e.getValue());
			}
			return map;
		}
	}

	/**
	 * 账目金额类型
	 * @ClassName: MoneyType 
	 * @author xiaoyingping
	 * @date 2011-8-15 下午02:46:04 
	 */
	public enum MoneyType {
		
		DRAW("01", "可提现金额"), UNDRAW("02", "不可提现金额"), FREEZE("03", "冻结金额"), UNSABLE("04", "账户余额");

		private final String key;
		private final String value;
		
		private MoneyType(String key, String value) {
			this.key = key;
			this.value = value;
		}
		
		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

		public static Map<String, String> getList() {
			Map<String, String> map = new HashMap<String, String>();
			for (MoneyType e : MoneyType.values()) {
				map.put(String.valueOf(e.getKey()), e.getValue());
			}
			return map;
		}
	}

	/**
	 * 用户来源
	 * @ClassName: UserSource 
	 * @author xiaoyingping
	 * @date 2011-8-15 下午02:46:04 
	 */
	public enum UserSource {
		
		REGISTER("source_01", "用户注册");

		private final String key;
		private final String value;
		
		private UserSource(String key, String value) {
			this.key = key;
			this.value = value;
		}
		
		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

		public static Map<String, String> getList() {
			Map<String, String> map = new HashMap<String, String>();
			for (UserSource e : UserSource.values()) {
				map.put(String.valueOf(e.getKey()), e.getValue());
			}
			return map;
		}
	}

	
	/**
	 * @ClassName: integralDetailCompareType 
	 * @Description: 积分明细变更比较类型
	 * @author menglei
	 * @date Aug 19, 2011 3:20:33 PM 
	 */
	public enum integralDetailCompareType {
		
		GREATER_THAN(1, "大于"), LESS_THAN(2, "小于"), EQUAL_TO(3, "等于"), GREATER_EQUAL(4, "大于等于"), LESS_EQUAL(5, "小于等于");

		private final int key;
		private final String value;
		
		private integralDetailCompareType(int key, String value) {
			this.key = key;
			this.value = value;
		}
		
		public int getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

		public static Map<String, String> getList() {
			Map<String, String> map = new HashMap<String, String>();
			for (integralDetailCompareType e : integralDetailCompareType.values()) {
				map.put(String.valueOf(e.getKey()), e.getValue());
			}
			return map;
		}
	}
	
	/**
	 * @ClassName: virtualAccountBusiType 
	 * @Description: 虚拟账户变更业务类型
	 * @author menglei
	 * @date Aug 19, 2011 3:20:33 PM 
	 */
	public enum virtualAccountBusiType {
		
		/**
		 * 官网秀团 虚拟账户 业务类型
		 */
		ORDER_PAY("va1101", "订单支付"),
		APPLY_DRAW("va1102", "申请提现"),
		VERIFY_NOT_PASS("va1103", "提现申请审核不通过"), 
		RETURN_MONEY_SUCCESS("va1104", "返现成功"), 
		RETURN_MONEY_FAIL("va1105", "返现失败"),
		REJECT_RECEIVE("va1106", "拒收"),
		RETURN_CHANGE_GOODS("va1107", "退/换货"),
		APPLY_DRAW_BACKOUT("va1108","取消申请提现"),
		INVITE_REBATE("va1109", "邀请返利"),
		BACKOUT_ORDER("va1110", "撤消订单"),
		RECHARGE("va1111", "在线充值"),
		BACKOUT_GOODS("va1112", "撤消商品"),
		CHANGE_PAYMENT_MODE("va1113", "取消虚拟帐户支付"),
		HANDWORK_RDJUST("va1114", "手工调节"),
		
		/**
		 * 官网送积分 业务类型
		 */
		USER_REGISTER("ia1201", "注册送积分"), 
		MOBILE_CHECKING("ia1202", "手机验证送积分"), 
		EMAIL_CHECKING("ia1203", "邮箱验证送积分"),
		GOODS_BUY("ia1204", "购买送积分"), 
		USER_COMMENT("ia1205", "评论送积分"), 
		INVITE_REGISTER("ia1206", "邀请注册送积分"), 
		REPLY_ADVISORY("ia1207", "回复其他会员咨询送积分"),
		BASK_ORDER("ia1208","晒单获取送积分"),
		ACTIVITY("ia1209", "活动送积分"), 
		IDEA_FEEDBACK("ia1210", "意见反馈送积分"),
		PUBLISH_ARTICLE("ia1211", "发表文章获取送积分"), 
		
	    /**
	     * 官网扣积分 业务类型
	     */
		POINTS("ir1212", "积分换购商品"),
		CASE_FOR("ir1213", "兑换优惠卡/券送"), 
		LOTTERY_DRAW("ir1214", "抽奖"),
		CHANGING_OR_REFUNDING("ir1215", "退换货退积分"), 
		WAS_EXPIRED("ir1216", "被过期");
		
		private final String key;
		private final String value;
		
		private virtualAccountBusiType(String key, String value) {
			this.key = key;
			this.value = value;
		}
		
		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

		public static Map<String, String> getList() {
			Map<String, String> map = new HashMap<String, String>();
			for (virtualAccountBusiType e : virtualAccountBusiType.values()) {
				map.put(String.valueOf(e.getKey()), e.getValue());
			}
			return map;
		}
	}
	
	/**
	 * @ClassName: acctItemIOType 
	 * @Description: 账目进出类型
	 * @author menglei
	 * @date Aug 19, 2011 3:20:33 PM 
	 */
	public enum acctItemIOType {
		
		ACCTITEM_INTO("01", "进账"), ACCTITEM_OUT("02", "出账"),ACCTITEM_FREEZE("03", "冻结"),ACCTITEM_UNFREEZE("04", "解冻");

		private final String key;
		private final String value;
		
		private acctItemIOType(String key, String value) {
			this.key = key;
			this.value = value;
		}
		
		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

		public static Map<String, String> getList() {
			Map<String, String> map = new HashMap<String, String>();
			for (acctItemIOType e : acctItemIOType.values()) {
				map.put(String.valueOf(e.getKey()), e.getValue());
			}
			return map;
		}
	}
	
	/**
	 * @ClassName: logicDeleteFlag 
	 * @Description: 
	 * @author menglei
	 * @date Aug 19, 2011 3:20:33 PM 
	 */
	public enum logicDeleteFlag {
		
		YES_DELETE("1", "已删除"), NO_DELETE("0", "未删除");

		private final String key;
		private final String value;
		
		private logicDeleteFlag(String key, String value) {
			this.key = key;
			this.value = value;
		}
		
		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

		public static Map<String, String> getList() {
			Map<String, String> map = new HashMap<String, String>();
			for (logicDeleteFlag e : logicDeleteFlag.values()) {
				map.put(String.valueOf(e.getKey()), e.getValue());
			}
			return map;
		}
	}
	
	/**
	 * @ClassName: createPointStateType 
	 * @Description: 积分规则是否启用状态
	 * @author menglei
	 * @date Aug 19, 2011 3:20:33 PM 
	 */
	public enum createPointStateType {
		
		CREATE_PONIT_STATE_START("01", "启用"), CREATE_PONIT_STATE_FORBIDDEN("02", "禁用");

		private final String key;
		private final String value;
		
		private createPointStateType(String key, String value) {
			this.key = key;
			this.value = value;
		}
		
		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

		public static Map<String, String> getList() {
			Map<String, String> map = new HashMap<String, String>();
			for (createPointStateType e : createPointStateType.values()) {
				map.put(String.valueOf(e.getKey()), e.getValue());
			}
			return map;
		}
	}
	

	/**
	 * 业务日志关键值类型
	 * @ClassName: KeyWordType 
	 * @author xiaoyingping
	 * @date 2012-2-21 下午04:09:44 
	 */
	public enum KeyWordType {
		
		CUST_ID("custId", "客户ID"),
		USER_ID("userId", "用户ID");

		private final String key;
		private final String value;
		
		private KeyWordType(String key, String value) {
			this.key = key;
			this.value = value;
		}
		
		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

		public static Map<String, String> getList() {
			Map<String, String> map = new HashMap<String, String>();
			for (KeyWordType e : KeyWordType.values()) {
				map.put(e.getKey(), e.getValue());
			}
			return map;
		}
	}
	
	
	

	/**
	 * 
	 *************************************************************** 
	 * <p> 
	 * @CLASS :com.xiu.uuc.facade.util.AuthenType
	 * @DESCRIPTION :认证类型(手机号，邮箱)    
	 * @AUTHOR :dick.xiao@xiu.com 
	 * @VERSION :v1.0 
	 * @DATE :2012-7-24 下午2:46:59             
	 * </p>   
	 ****************************************************************
	 */
	public enum AuthenType {

		EMAIL("0", "邮箱"), MOBILE("1", "手机");

		private final String key;
		private final String value;

		private AuthenType(String key, String value) {
			this.key = key;
			this.value = value;
		}

		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

		public static Map<String, String> getList() {
			Map<String, String> map = new HashMap<String, String>();
			for (AuthenType e : AuthenType.values()) {
				map.put(e.getKey(), e.getValue());
			}
			return map;
		}
	}
	
	

	/**
	 * 
	 *************************************************************** 
	 * <p> 
	 * @CLASS :com.xiu.uuc.common.util.AuthenStatus
	 * @DESCRIPTION :是否通过认证(手机号，邮箱)    
	 * @AUTHOR :dick.xiao@xiu.com 
	 * @VERSION :v1.0 
	 * @DATE :2012-7-24 下午2:47:54             
	 * </p>   
	 ****************************************************************
	 */
	public enum AuthenStatus {

		NOT("0", "未通过"), PASS("1", "已通过");

		private final String key;
		private final String value;

		private AuthenStatus(String key, String value) {
			this.key = key;
			this.value = value;
		}

		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

		public static Map<String, String> getList() {
			Map<String, String> map = new HashMap<String, String>();
			for (AuthenStatus e : AuthenStatus.values()) {
				map.put(e.getKey(), e.getValue());
			}
			return map;
		}
	}
	
	
	
	
	
	
	
	
}
