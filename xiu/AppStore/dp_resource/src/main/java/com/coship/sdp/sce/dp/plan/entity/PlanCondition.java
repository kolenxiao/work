/**
 * 
 */
package com.coship.sdp.sce.dp.plan.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.coship.sdp.access.entity.EntityObject;

/**
 * 精细化运营_方案\条件_关系表
 * 
 * @author 909194
 */
@Entity
public class PlanCondition extends EntityObject {
	
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4572655009050928955L;
    
    /**
     * 方案优先级，asc方式，默认新增方案排最后。默认值10000
     */
    private Integer sortNum = 10000;
    
    /**
     * 是否为默认方案，该字段扩展用（针对某个渠道存在默认方案时使用）。默认值false
     */
    private Boolean defaulted = false;
    
    /**
     * 状态，-1=已删除；0=禁用；1=有效 默认值1
     */
    private Integer status = 1;
	
	/**
	 * 方案实体
	 */
	private Plan plan;
	
	/**
	 * 条件实体
	 */
	private Condition condition;
	
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 修改时间
     */
    private Date updateTime;

    /* ==================非数据库字段============== */
    private String planId;
    private String conditionCode;
    private String conditionValue;
    private Integer conditionStatus;

    public PlanCondition() {
    }

    public PlanCondition(String planId, String conditionCode, String conditionValue, Integer conditionStatus) {
        this.planId = planId;
        this.conditionCode = conditionCode;
        this.conditionValue = conditionValue;
        this.conditionStatus = conditionStatus;
    }

	@ManyToOne
	@JoinColumn(nullable = false,name = "C_PLAN_ID")
    public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	@ManyToOne
	@JoinColumn(nullable = false,name = "C_CONDITION_ID")
	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	@Column(nullable = false, columnDefinition = " int(11) default 1 ")
	public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	@Column(nullable = false, columnDefinition = " int(11) default 10000 ")
	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	@Column(nullable = false, columnDefinition = " boolean default false ")
	public Boolean getDefaulted() {
		return defaulted;
	}

	public void setDefaulted(Boolean defaulted) {
		this.defaulted = defaulted;
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
    public String getConditionCode() {
        return conditionCode;
    }

    public void setConditionCode(String conditionCode) {
        this.conditionCode = conditionCode;
    }

    @Transient
    public String getConditionValue() {
        return conditionValue;
    }

    public void setConditionValue(String conditionValue) {
        this.conditionValue = conditionValue;
    }

    @Transient
    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    @Transient
    public Integer getConditionStatus() {
        return conditionStatus;
    }

    public void setConditionStatus(Integer conditionStatus) {
        this.conditionStatus = conditionStatus;
    }
}
