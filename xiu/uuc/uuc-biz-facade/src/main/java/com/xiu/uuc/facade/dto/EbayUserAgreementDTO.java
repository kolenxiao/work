package com.xiu.uuc.facade.dto;

/**
 * eBay用户协议是否同意DTO
 * @ClassName: EbayUserAgreementDTO
 * @author andy.meng
 * @date
 */
public class EbayUserAgreementDTO extends BaseDTO {

	/**
	 * @Fields serialVersionUID : 
	 */
	private static final long serialVersionUID = -5494437276802559674L;

	/**
	 * 用户ID
	 */
	private Long userId ;

	/**
	 * 渠道标识
	 */
	private Integer channelId ;
	
	/**
	 * eBay用户协议是否同意  
	 * Y：同意 ,N：不同意,其它也表示不同意
	 */
	private String ebayUserAgreement;
	
	/**
     * ip地址
     */
    private String ipAddr ;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public String getEbayUserAgreement() {
		return ebayUserAgreement;
	}

	public void setEbayUserAgreement(String ebayUserAgreement) {
		this.ebayUserAgreement = ebayUserAgreement;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

}
