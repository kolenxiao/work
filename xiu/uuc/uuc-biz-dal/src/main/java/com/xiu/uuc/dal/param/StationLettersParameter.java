package com.xiu.uuc.dal.param;

/**
 * 站内信参数对象
 * @ClassName: StationLettersParameter 
 * @author temp
 * @date 2011-7-20 上午11:43:45 
 */
public class StationLettersParameter {
	/**
	 * 客户ID
	 */
	private String customerId;
	
	/**
	 * 信件标题
	 */
	private String title;
	
	/**
	 * 信件ID
	 */
	private String lettersId;
	
	/**
	 * 是否阅读
	 */
	private int read;
	
	/**
	 * 分页起始行
	 */
	private int firstRow;
	
	/**
	 * 分页结束行
	 */
	private int lastRow;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLettersId() {
		return lettersId;
	}

	public void setLettersId(String lettersId) {
		this.lettersId = lettersId;
	}

	public int getRead() {
		return read;
	}

	public void setRead(int read) {
		this.read = read;
	}

	public int getFirstRow() {
		return firstRow;
	}

	public void setFirstRow(int firstRow) {
		this.firstRow = firstRow;
	}

	public int getLastRow() {
		return lastRow;
	}

	public void setLastRow(int lastRow) {
		this.lastRow = lastRow;
	}	
}
