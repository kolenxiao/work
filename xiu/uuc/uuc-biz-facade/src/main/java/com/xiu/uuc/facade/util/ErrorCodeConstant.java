package com.xiu.uuc.facade.util;

/**
 * @ClassName: ErrorCodeConstant 
 * @Description: 统一用户中心 错误码常量类
 * @author menglei
 * @date Jul 22, 2011 3:48:53 PM 
 */
public class ErrorCodeConstant {
	
	public static final String PARAM_ERROR_CODE = "3";                       //参数输入错误
	public static final String BUSINESS_ERROR_CODE = "4";                    //业务操作错误
	public static final String SYSTEM_ERROR_CODE = "5";                      //系统操作错误
	public static final String ACCT_UNABLE_MONEY_NO_ENOUGH_CODE = "6";       //用戶账户可用金额不足
	public static final String ACCT_UNABLE_INTEGRAL_NO_ENOUGH_CODE = "7";    //用戶账户可用积分不足
	public static final String ACCT_ITEM_STATE_FROZEN_CODE = "8";            //用户账目已禁用
	public static final String ACCT_ITEM_STATE_NATURAL_CODE = "9";           //用户账目已正常
	public static final String ACCT_STATE_FROZEN_CODE = "10";                //用户账户已冻结，不需要再次进行冻结操作
	public static final String ACCT_STATE_NATURAL_CODE = "11";               //用户账户已解冻，不需要再次进行解冻操作
	public static final String USER_INFO_NOT_FOUND_CODE = "12";              //用户对应的客户信息不存在
	public static final String USER_ACCT_NOT_FOUND_CODE = "13";              //用户对应的用户账户信息不存在
	public static final String USER_ACCT_ITEM_NOT_FOUND_CODE = "14";         //用户对应的用户账目信息不存在
	public static final String INTEGRAL_RUAL_EXPRESS_NOT_FOUND_CODE = "15";  //积分点码对应的积分表达式不存在
	public static final String VIRTUAL_ACCOUNT_INFO_FAIL_CODE = "16";        //获取虚拟账户统计信息失败
	public static final String VIRTUAL_ACCOUNT_PAY_AMOUNT_ERROR_CODE = "17"; //虚拟账户扣款金额应该大于零
	public static final String INTEGRAL_PAY_AMOUNT_ZERO_ERROR_CODE = "18";   //当次支付的积分应该大于零个积分
	
	public static final String PARAM_ERROR_MESSAGE = "参数输入错误";                                 //参数输入错误
	public static final String BUSINESS_ERROR_MESSAGE = "业务操作错误";                              //业务操作错误
	public static final String SYSTEM_ERROR_MESSAGE = "系统操作错误";                                //系统操作错误
	public static final String ACCT_UNBALB_MONEY_NO_ENOUGH_MESSAGE = "用戶账户可用金额不足";           //用戶账户可用金额不足
	public static final String ACCT_UNABLE_INTEGRAL_NO_ENOUGH_MESSAGE = "用戶账户可用积分不足";        //用戶账户可用积分不足
	public static final String ACCT_ITEM_STATE_FROZEN_MESSAGE = "用户账目已禁用,不能进行账目冻结操作";    //用户账目已禁用,不能进行账目冻结操作
	public static final String ACCT_ITEM_STATE_NATURAL_MESSAGE = "用户账目已正常";                    //用户账目已正常
	public static final String ACCT_STATE_FROZEN_MESSAGE = "用户账户已冻结";                          //用户账户已冻结
	public static final String ACCT_STATE_NATURAL_MESSAGE = "用户账户已解冻";                         //用户账户已解冻错误
	public static final String USER_INFO_NOT_FOUND_MESSAGE = "用户对应的客户信息不存在";                //用户对应的客户信息不存在
	public static final String USER_ACCT_NOT_FOUND_MESSAGE = "用户对应的用户账户信息不存在";             //用户对应的用户账户信息不存在
	public static final String USER_ACCT_ITEM_NOT_FOUND_MESSAGE = "用户对应的用户账目信息不存在";        //用户对应的用户账目信息不存在
	public static final String INTEGRAL_RUAL_EXPRESS_NOT_FOUND_MESSAGE = "积分点码对应的积分表达式不存"; //积分点码对应的积分表达式不存在
	public static final String VIRTUAL_ACCOUNT_INFO_FAIL_MESSAGE = "获取虚拟账户统计信息失败";          //获取虚拟账户统计信息失败
	public static final String VIRTUAL_ACCOUNT_PAY_AMOUNT_ERROR_MESSAGE = "虚拟账户扣除金额应该大于零";  //虚拟账户扣除金额应该大于零
	public static final String VIRTUAL_ACCOUNT_PAY_INTEGRAL_ERROR_MESSAGE = "虚拟账户扣除积分应该大于零";//虚拟账户扣除积分应该大于零
	public static final String INTEGRAL_PAY_AMOUNT_ZERO_ERROR_MESSAGE = "当次支付的积分应该大于零个积分"; //当次支付的积分应该大于零个积分
	
	/**
	 * 入参对象为null
	 */
	public static final String PARAMETER_OBJECT = "入参对象为null";
	
	/**
	 * 入参对象的属性为null
	 */
	public static final String PARAMETER_VALUE = "入参对象的属性为null";
	
	/**
	 * 入参不合法
	 */
	public static final String PARA_VALUE = "入参不合法";
	
	
	
}
