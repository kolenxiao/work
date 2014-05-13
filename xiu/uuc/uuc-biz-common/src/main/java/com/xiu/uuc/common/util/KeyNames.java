package com.xiu.uuc.common.util;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 公用常量类
 * @ClassName: KeyNames 
 * @author xiaoyingping
 * @date 2011-7-19 上午11:23:23 
 *
 */
public class KeyNames {
	
	/**
	 * 账户状态
	 */
	public static final String ACCT_STATE_NATURAL = "01";//账户正常
	public static final String ACCT_STATE_FROZEN = "02";//账户已冻结
	
	/**
	 * 积分规则
	 */
	public static final String INTEGER_RULE_STATE = "01";  //积分规则正常
	public static final String INTEGER_RULE_FROZEN = "02"; //积分规则禁止
	
	/**
	 * 账目类型
	 */
	public static final String ACCT_ITEM_WITHDRAWAL_YES = "01";//可提现账户
	public static final String ACCT_ITEM_WITHDRAWAL_NO = "02";//不可提现账户
	public static final String ACCT_ITEM_INTEGRAL = "03";//积分账户
	
	/**
	 * 账目操作类型
	 */
	public static final String ACCT_ITEM_OPERATER_MONEY = "01";    //对可提现或者不可提现账目进行金额操作
	public static final String ACCT_ITEM_OPERATER_INTEGRAL = "02"; //对积分账目进行积分操作
	
	/**
	 * 账目金额类型
	 */
	public static final String ACCT_ITEM_MONEY_TYPE_DRAW = "01";    //可提现金额
	public static final String ACCT_ITEM_MONEY_TYPE_UNDRAW = "02";  //不可提现金额
	public static final String ACCT_ITEM_TYPE_INTEGRAL = "03";      //积分余额
	public static final String ACCT_ITEM_MONEY_TYPE_UNSABLE = "04"; //账户余额
	
	/**
	 * 账目状态
	 */
	public static final String ACCT_ITEM_STATE_NATURAL = "01";//账目正常
	public static final String ACCT_ITEM_STATE_FROZEN = "02";//账目已禁用
	
	/**
	 * 账目进出类型
	 */
	public static final String ACCT_ITEM_IO_TYPE_IN = "01";//进账
	public static final String ACCT_ITEM_IO_TYPE_OUT = "02";//出账
	public static final String ACCT_ITEM_IO_TYPE_FREEZE = "03";//冻结
	public static final String ACCT_ITEM_IO_TYPE_UNFREEZE = "04";//解冻
	
	
	/**
	 * 同一IP能够注册用户的最大数目
	 */
	public static final int MAX_REGISTER_IP_COUNT = Integer.parseInt(System.getProperty("max_register_ip_count"));
	
	/**
	 * 限制注册IP数量的间隔时间(距离系统当前时间,单位:秒)
	 */
	public static final int MAX_REGISTER_IP_INTERVAL = Integer.parseInt(System.getProperty("max_register_ip_interval"));
	
	/**
	 * 默认级别
	 */
	public static final String DEFAULT_LEVEL = "1";
	
	/**
	 * 默认是否订阅消息
	 */
	public static final String DEFAULT_SUBSCIBE_INFO = "0";
		
	/**
	 * 默认客户扩展属性类型
	 */
	public static final String DEFAULT_CUST_ATTR_TYPE = "0";
	
	/**
	 * 默认身份值类型
	 * S:单选 M:复选 T:文本
	 */
	public static final String WORK_TYPE_STYLE_SINGLE = "S";
	public static final String WORK_TYPE_STYLE_TEXT = "T";
	
	/**
	 * 秀团，团货MD5加密时的混入值
	 */
	public static final String PASSWORD_MIX = "@4!@#$%@";
	
	/**
	 * 记录业务操作日志时的混入值 
	 */
	public static final String BUSI_LOG_MIX = "@#%$$@#!*@!!@";
	
	/**
	 * 字符串分隔符
	 */
	public static final String stringSplitFlag = ";";
	
	/**
	 * 调用SSO接口地址
	 */
	public static final String SSO_SERVER_URL = System.getProperty("sso.server.url");
	
	
	/**
	 * 虚拟账户异动审计 支付额度伐值 缺省值为1000000 单位：分 1000000分=10000元
	 */
	public static final int pay_sum_ioAmount_max = Integer.parseInt(System.getProperty("pay_sum_ioAmount_max","1000000")) ;
	
	/**
	 * 虚拟账户异动审计 同一个用户 一种账目类型（比如可提现），在指定时间范围内 支付次数伐值 缺省值为5次
	 */
	public static final int pay_sum_count_max = Integer.parseInt(System.getProperty("pay_sum_count_max","5")) ;
	
	/**
	 * 虚拟账户异动审计 当用户昵称在下面列表中出现的时候，列表中间用逗号分割，此昵称用户的交易频率不受系统设置限制
	 */
	public static final String[] petNameArray = System.getProperty("pay_sum_count_max_not_limited_when_petName_list") != null 
	                                           ? System.getProperty("pay_sum_count_max_not_limited_when_petName_list").split(stringSplitFlag)
			                                   : new String[0];
	
	/**
	 * 虚拟账户异动审计 当用户手机在下面列表中出现的时候，列表中间用逗号分割，此手机用户的交易频率不受系统设置限制
	 */
	public static final String[] moblieArray = System.getProperty("pay_sum_count_max_not_limited_when_moblie_list") != null 
                                               ? System.getProperty("pay_sum_count_max_not_limited_when_moblie_list").split(stringSplitFlag)
                                               : new String[0];
	
	/**
	 * 虚拟账户异动审计 当用户邮箱在下面列表中出现的时候，列表中间用逗号分割，此邮箱用户的交易频率不受系统设置限制
	 */
	public static final String[] emailList = System.getProperty("pay_sum_count_max_not_limited_when_email_list") != null 
                                             ? System.getProperty("pay_sum_count_max_not_limited_when_email_list").split(stringSplitFlag)
                                             : new String[0];
	
	/**
	 * 账目平衡 待审计账目修改时间 相对于系统当前时间 下限值 缺省值为-1 单位：天(这里指待审计账目时间为相对于系统时间一天前账目变化的账目)
	 */
	public static final int balance_updateTime_validTime_beg_date ;
	
	/**
	 * 账目平衡 时间段下限配置
	 */
	public static final String balance_updateTime_validTime_beg_time ;
	
	/**
	 * 账目平衡 待审计账目修改时间 相对于系统当前时间 上限值 缺省值为0 单位：天
	 */
	public static final int balance_updateTime_expireTime_end_date ;
	
	/**
	 * 账目平衡 时间段上限配置
	 */
	public static final String balance_updateTime_expireTime_end_time ;
	
	/**
	 * 账目流水支付次数 待审计账目流水创建时间 相对于系统当前时间 前多少分钟 单位：分钟 缺省值为180分钟=3小时
	 */
	public static final int paycount_updateTime_validTime_beg_dateTime = Integer.parseInt(System.getProperty("paycount_updateTime_validTime_beg_dateTime","180")) ;
	
	/**
	 * 虚拟账户异动审计 调用邮件服务接口地址
	 */
	public static final String email_server_url = System.getProperty("email.server.url") ;
	
	/**
	 * 虚拟账户异动审计 配置邮件接收人地址列表，中间用逗号分割
	 */
	public static final String receiver_email_url = System.getProperty("receiver.email") ;
	
	/**
	 * 虚拟账户异动审计 异动类型
	 * 00：账目不平 01：超过支付额的限制 02：超过支付次数限制
	 */
	public enum AbnormalType {
		
		AUDIT_NOT_BALANCE("00", "账目不平"), GREAT_PAY_IOAMOUNT_LIMIT("01", "超过支付额的限制"), GREATE_PAY_COUNT_LIMIT("02", "超过支付次数限制");

		private final String key;
		private final String value;
		
		private AbnormalType(String key, String value) {
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
			for (AbnormalType e : AbnormalType.values()) {
				map.put(String.valueOf(e.getKey()), e.getValue());
			}
			return map;
		}
	}
	
	/**
	 * 虚拟账户增加业务日志写表功能 模块类型
	 * 0：未知类型 1：虚拟账户(可提现，不可提现) 2：积分
	 */
	public enum LogType {
		
		LOG_TYPE_USER("1", "用户资料"),
		LOG_TYPE_ADDRESS("2", "收货地址 "),		 
		LOG_TYPE_VIRTUAL("3", "虚拟账户"), 
		LOG_TYPE_INTEGER("4", "积分"),
		LOG_TYPE_UNKNOWN("5", "未知类型");

		private final String key;
		private final String value;
		
		private LogType(String key, String value) {
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
			for (LogType e : LogType.values()) {
				map.put(String.valueOf(e.getKey()), e.getValue());
			}
			return map;
		}
	}
	
	/**
	 * 虚拟账户增加业务日志写表功能 写业务日志表 中 关键字类型
	 * 0：未知类型 1：用户ID
	 */
	public enum LogKeyWordType {
		
		LOG_KEY_WORD_TYPE_UNKNOWN("0", "未知类型"), LOG_KEY_WORD_TYPE_USERID("1", "用户ID"),
		LOG_KEY_WORD_TYPE_ACCTID("2", "账户ID"),LOG_KEY_WORD_TYPE_ACCTITEMID("3", "账目ID");

		private final String key;
		private final String value;
		
		private LogKeyWordType(String key, String value) {
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
			for (LogKeyWordType e : LogKeyWordType.values()) {
				map.put(String.valueOf(e.getKey()), e.getValue());
			}
			return map;
		}
	}
	
	public static final Map<String, String> logTypeCheckBox = new LinkedHashMap<String, String>(LogType.getList());
	public static final Map<String, String> logKeyWordTypeCheckBox = new LinkedHashMap<String, String>(LogKeyWordType.getList());
	
	/**
	 * 账目平衡 拼接后的审计时间下限
	 */
	public static Date getBalanceUpdateTimeValidTimeBeg(){
		String dateString = DateUtil.dateToDateString(new Date(),DateUtil.TIMEF_FORMAT).substring(0, 11)+ balance_updateTime_validTime_beg_time;
		Date date = DateUtil.getDateByFormatStr(dateString, DateUtil.TIMEF_FORMAT);
		Date dateNext = DateUtil.getNextDay(date, balance_updateTime_validTime_beg_date);
		return dateNext;
	}
	
	/**
	 * 账目平衡 拼接后的审计时间上限
	 */
	public static Date getBalanceUpdateTimeExpireTimeEnd(){
		String dateString = DateUtil.dateToDateString(new Date(),DateUtil.TIMEF_FORMAT).substring(0, 11)+ balance_updateTime_expireTime_end_time;
		Date date = DateUtil.getDateByFormatStr(dateString, DateUtil.TIMEF_FORMAT);
		Date dateNext = DateUtil.getNextDay(date, balance_updateTime_expireTime_end_date);
		return dateNext;
	}
	
	/**
	 * 账目流水支付次数 时间间隔（相对于系统当前时间前多少分钟）
	 */
	public static final int getPayCountUpdateTimeValidTimeBegDateTime(){
		return paycount_updateTime_validTime_beg_dateTime;
	}
	
	static {
		balance_updateTime_validTime_beg_date = Integer.parseInt(System.getProperty("balance_updateTime_validTime_beg_date","-1")) ;
		balance_updateTime_validTime_beg_time = System.getProperty("balance_updateTime_validTime_beg_time") ;
		balance_updateTime_expireTime_end_date = Integer.parseInt(System.getProperty("balance_updateTime_expireTime_end_date","0")) ;
		balance_updateTime_expireTime_end_time = System.getProperty("balance_updateTime_expireTime_end_time") ;
	}
}
