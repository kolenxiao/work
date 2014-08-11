/**
 * 
 */
package com.coship.sdp.sce.dp.plan.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.coship.sdp.access.entity.EntityObject;

/**
 * 精细化运营方案实体.
 * 
 * @author 907948
 */
@Entity
public class Plan extends EntityObject {
    private static final long serialVersionUID = -6741455621924206429L;

    /**
     * 方案名称
     */
    private String name = "";

    /**
     * 方案开始生效时间。
     */
    private Date startTime;

    /**
     * 方案截止生效时间。默认截止时间为：2034-12-31 23:59:59
     */
    private Date endTime;

    /**
     * 方案是否为全局默认方案。
     */
    private Boolean defaulted;

    /**
     * 方案规则串
     * rfc2445规范定义的日期字符串，参考：<a href="https://code.google.com/p/google-rfc-2445/" target="_blank">google-rfc-2445</a>
     */
    private String regulation = "";

    /**
     * 方案描述信息。
     */
    private String description = "";

    /**
     * 方案状态，-1=已删除；0=未启用；1=已启用
     */
    private Integer status = 0;

	/**
	 * 排序
	 */
	private Integer sortNum;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 修改时间
     */
    private Date updateTime;
    
    /**
     * 临时字段,用于应用关联方案页面
     */
    private List<Map<String,String>> itemInfo;
    
    /**
     * 临时字段,用于页面展示关联的条件信息
     */
    private String conditionInfo;
    
    
    public Plan() {
    }

    public Plan(String id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Column(nullable = false)
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Column(nullable = false)
    public Boolean getDefaulted() {
        return defaulted;
    }

    public void setDefaulted(Boolean defaulted) {
        this.defaulted = defaulted;
    }

    /**
     * rfc2445规范定义的日期字符串，参考：<a href="https://code.google.com/p/google-rfc-2445/" target="_blank">google-rfc-2445</a>
     * 
     * @return
     */
    public String getDescription() {
        return description;
    }

    public String getRegulation() {
		return regulation;
	}

	public void setRegulation(String regulation) {
		this.regulation = regulation;
	}

	public void setDescription(String description) {
        this.description = description;
    }

    @Column(nullable = false,columnDefinition = " int(11) default 0 ")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	@Column(nullable = false)
    public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(nullable = false)
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	@Transient
	public String getConditionInfo() {
		return conditionInfo;
	}

	public void setConditionInfo(String conditionInfo) {
		this.conditionInfo = conditionInfo;
	}

	@Transient
	public List<Map<String,String>> getItemInfo() {
		return itemInfo;
	}

	public void setItemInfo(List<Map<String,String>> itemInfo) {
		this.itemInfo = itemInfo;
	}
    
}
