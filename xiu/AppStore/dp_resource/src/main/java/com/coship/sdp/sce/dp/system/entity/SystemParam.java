package com.coship.sdp.sce.dp.system.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.coship.sdp.access.entity.EntityObject;

/**
 * 系统参数
 * 
 * @author 908618
 * 
 */
@Entity
public class SystemParam extends EntityObject {

	private static final long serialVersionUID = -6586741865275594779L;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 参数编码
	 */
	private String code;

	/**
	 * 参数值
	 */
	private String value;

	/**
	 * 参数类型 1:文字; 2:图片
	 */
	private Integer type;
	
	/**
	 * 对参数值进行split,间隔符为#
	 */
	private List<String> valueList = new ArrayList<String>();;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	@Transient
	public List<String> getValueList() {
		if(null != value && !"".equals(value)){
			String[] valueArr = value.split(";");
			for (String s : valueArr) {
				valueList.add(s);
			}
		}
		return this.valueList;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
