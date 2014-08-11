package com.coship.appstore.iuc;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("UserOption")
public class ProductOption implements Serializable {
	private static final long serialVersionUID = -4211659635487187163L;

	@XStreamAlias("productCodes")
	private String[] productCodes;

	@XStreamAlias("resourceType")
	private int resourceType;

	@XStreamAlias("resourceTypeId")
	private String resourceTypeId;

	@XStreamAlias("userCode")
	private String userCode;

	public String[] getProductCodes() {
		return productCodes;
	}

	public void setProductCodes(String[] productCodes) {
		this.productCodes = productCodes;
	}

	public int getResourceType() {
		return resourceType;
	}

	public void setResourceType(int resourceType) {
		this.resourceType = resourceType;
	}

	public String getResourceTypeId() {
		return resourceTypeId;
	}

	public void setResourceTypeId(String resourceTypeId) {
		this.resourceTypeId = resourceTypeId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
}
