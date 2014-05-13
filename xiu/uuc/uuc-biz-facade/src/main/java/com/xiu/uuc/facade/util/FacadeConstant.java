package com.xiu.uuc.facade.util;

public class FacadeConstant {
	
	/**
	 * 成功
	 */
	public static final String SUCCESS = "1";
	
	/**
	 * 失败
	 */
	public static final String FALSE = "0";
	
	/**
	 * 站内信URL
	 */
	public static final String LETTERS_RUL = "http://127.0.0.1:8080/UUC/stattionLetters";
	
	/**
	 * 账目进行金额冻结 加操作
	 */
	public static final String ACCT_ITEM_FREEZE_MONEY_ADD = "01";
	
	/**
	 * 账目进行金额冻结 减操作
	 */
	public static final String ACCT_ITEM_FREEZE_MONEY_DEC = "02";
	
	/**
	 * 对当前账目进行冻结操作的时候 是否仅仅对账目冻结数量进行清零操作 
	 * 比如：申请提现前     可提现总金额（包括冻结部分）为1000，可提现冻结为0 ，申请提现 300
	 * 0：表示不是仅仅对冻结数量做清零操作  可提现总金额（包括冻结部分）为700， 可提现冻结为0
	 * 1：表示仅仅对冻结数量做清零操作  可提现总金额（包括冻结部分）为1000，可提现冻结为0
	 */
	public static final String ACCT_ITEM_FREEZE_MONEY_DEC_NOT_ONLY_CLEAR_ZERO = "0";
	public static final String ACCT_ITEM_FREEZE_MONEY_DEC_ONLY_CLEAR_ZERO = "1";

}
