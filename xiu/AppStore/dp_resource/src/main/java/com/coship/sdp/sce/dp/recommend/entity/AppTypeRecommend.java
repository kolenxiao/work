package com.coship.sdp.sce.dp.recommend.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.coship.sdp.access.entity.EntityObject;
import com.coship.sdp.sce.dp.ap.entity.DpAppInfo;
import com.coship.sdp.sce.dp.type.entity.DpType;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppTypeRecommend extends EntityObject
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6715228628621725503L;
	
    /**
     * 应用信息
     */
    private DpAppInfo dpAppInfo;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建开始时间
     */
    private Date appRecommendCTime;

    /**
     * 推荐分类
     */
    private DpType dpType;
    
    /**
     * 分类排序
     */
    private Double sort;
    


    @ManyToOne
    @JoinColumn(name = "C_APP_ID")
    @NotFound(action=NotFoundAction.IGNORE) 
    public DpAppInfo getDpAppInfo()
    {
        return dpAppInfo;
    }

    public void setDpAppInfo(DpAppInfo dpAppInfo)
    {
        this.dpAppInfo = dpAppInfo;
    }

    public String getCreateUser()
    {
        return createUser;
    }

    public void setCreateUser(String createUser)
    {
        this.createUser = createUser;
    }

    public Date getAppRecommendCTime()
    {
        return appRecommendCTime;
    }

    public void setAppRecommendCTime(Date appRecommendCTime)
    {
        this.appRecommendCTime = appRecommendCTime;
    }

	public Double getSort() {
		return sort;
	}

	public void setSort(Double sort) {
		this.sort = sort;
	}

	@ManyToOne
    @JoinColumn(name = "C_TYPE_ID")
    @NotFound(action=NotFoundAction.IGNORE) 
	public DpType getDpType() {
		return dpType;
	}

	public void setDpType(DpType dpType) {
		this.dpType = dpType;
	}
}
