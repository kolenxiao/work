/*
 * 文件名称：DpAppInfo.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：FuJian/906126
 * 创建时间：2011-9-5
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.ap.entity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.OrderBy;

import com.coship.sdp.access.entity.EntityObject;
import com.coship.sdp.permission.entity.User;
import com.coship.sdp.sce.dp.atachmentfile.entity.AttachmentFile;
import com.coship.sdp.sce.dp.audit.entity.DpAuditRecord;
import com.coship.sdp.sce.dp.comment.entity.AppCommentInfo;
import com.coship.sdp.sce.dp.recommend.entity.AppRecommend;
import com.coship.sdp.sce.dp.sign.entity.AppSign;
import com.coship.sdp.sce.dp.type.entity.DpType;

/**
 * 应用信息类
 * @author FuJian/906126
 * @version [版本号, 2011-9-5]
 * @since [产品/模块版本]
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DpAppInfo extends EntityObject
{

    /**
     * 序列号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 应用名称拼音.
     */
    private String appNamePinyin;

    /**
     * 应用简介
     */
    private String appDesc;

    /**
     * 应用包名称.
     */
    private String appFilePackage;
    
    private int sortNum;

    /**
     * 开发者ID
     */
    private String dpStaffId;

    /**
     * 开发者对象
     */
    private DpStaff dpStaff;

    /**
     * 应用上传的管理员Id
     */
    private String userId;

    /**
     * 开发者基本信息
     */
    private User user;

    /**
     * 应用状态
     * 草稿：1000
     * 待审核：1001
     * 审核未通过：1002
     * 审核通过：1003
     * 已上架：1004
     * 已下架：1005
     * 更新版本：1006
     */
    private String appStatus;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 入库时间
     */
    private Date createTime;

    /**
     * 创建开始时间
     */
    private String beginAppInfoCTime;

    /**
     * 创建结束时间
     */
    private String endAppInfoCTime;

    /**
     * 应用价格
     */
    private double price;

    /**
     * 版本名称
     */
    private String version;

    /**
     * 存储apk的xml中android:versionCode字段
     */
    private int versionCode;

    /**
     * 平均评分
     */
    private double averageScore;

    /**
     * 平均星值
     */
    private double averageStar;

    /**
     * 应用语言
     */
    private String language;
    
    /**
     * 应用语言类别
     */
    private DpType languageDpType;

    /**
     * 系统要求
     */
    private String system;
    /**
     * 系统要求类别
     */
    private DpType systemDpType;

    /**
     * 开发商
     */
    private String developer;

    /**
     * 应用类别
     */
    private DpType dpType;

    /**
     * 应用推荐信息
     */
    private AppRecommend appRecommend;

    /**
     * 应用的评论信息
     */
    private List<AppCommentInfo> appCommentInfoList;

    /**
     * 审核记录信息
     */
    private List<DpAuditRecord> appAuditList;

    /**
     * 附件列表
     */
    private List<AttachmentFile> attachmentList;

    /**
     * 应用签名
     */
    private AppSign appSign;

    /**
     * 下载次数
     */
    private long downloadCount;

    /**
     * 评论数
     */
    private long commentCount;

    /**
     * 应用信息对于的apk大小，不记录数据库表
     */
    private double size;
    
    /**
     * 应用专题海报
     */
    private String subjectPoster;
    
    /**
     * 操作类型
     */
    private Integer opMode;
    private List<String> opModeList;
    
    /**
     * 人工增加的下载次数
     */
    private int handDownCount;
    /**
     * 人工增加的平均评分
     */
    private double handAvgScore;
    
    /**
     * 人工增加的评分人数
     */
    private int handScoreCount;

    @Transient
    public long getCommentCount()
    {
        return commentCount;
    }

    public void setCommentCount(long commentCount)
    {
        this.commentCount = commentCount;
    }

    @Transient
    public String getBeginAppInfoCTime()
    {
        return beginAppInfoCTime;
    }

    public void setBeginAppInfoCTime(String beginAppInfoCTime)
    {
        this.beginAppInfoCTime = beginAppInfoCTime;
    }

    @Transient
    public String getEndAppInfoCTime()
    {
        return endAppInfoCTime;
    }

    public void setEndAppInfoCTime(String endAppInfoCTime)
    {
        this.endAppInfoCTime = endAppInfoCTime;
    }

    @Transient
    public List<DpAuditRecord> getAppAuditList()
    {
        return appAuditList;
    }

    public void setAppAuditList(List<DpAuditRecord> appAuditList)
    {
        this.appAuditList = appAuditList;
    }

    @Column(length = 64)
    public String getAppName()
    {
        return appName;
    }

    public void setAppName(String appName)
    {
        this.appName = appName;
    }

    @Column(length = 64)
    public String getAppNamePinyin()
    {
        return appNamePinyin;
    }

    public void setAppNamePinyin(String appNamePinyin)
    {
        this.appNamePinyin = appNamePinyin;
    }

    @Column(length = 512)
    public String getAppDesc()
    {
        return appDesc;
    }

    public void setAppDesc(String appDesc)
    {
        this.appDesc = appDesc;
    }

    @Column(length = 256)
    public String getAppFilePackage()
    {
        return appFilePackage;
    }

    public void setAppFilePackage(String appFilePackage)
    {
        this.appFilePackage = appFilePackage;
    }

    public String getDpStaffId()
    {
        return dpStaffId;
    }

    @Transient
    public DpStaff getDpStaff()
    {
        return dpStaff;
    }

    public void setDpStaff(DpStaff dpStaff)
    {
        this.dpStaff = dpStaff;
    }

    public void setDpStaffId(String dpStaffId)
    {
        this.dpStaffId = dpStaffId;
    }

    @Column(length = 4)
    public String getAppStatus()
    {
        return appStatus;
    }

    public void setAppStatus(String appStatus)
    {
        this.appStatus = appStatus;
    }

    @Column
    public Date getUpdateTime()
    {
        Date date = updateTime;
        return date;
    }

    public void setUpdateTime(Date updateTime)
    {

        if (updateTime == null)
        {
            this.updateTime = new Date();
        }
        else
        {
            this.updateTime = (Date) updateTime.clone();
        }

    }

    @Column
    public Date getCreateTime()
    {
        Date date = createTime;
        return date;
    }

    public void setCreateTime(Date createTime)
    {
        if (null != createTime)
        {
            this.createTime = (Date) createTime.clone();
        }
        else
        {
            this.createTime = new Date();
        }
    }

    @ManyToOne
    @JoinColumn(name = "C_TYPE_ID")
    public DpType getDpType()
    {
        return dpType;
    }

    public void setDpType(DpType dpType)
    {
        this.dpType = dpType;
    }

    @Transient
    public AppRecommend getAppRecommend()
    {
        return appRecommend;
    }

    public void setAppRecommend(AppRecommend appRecommend)
    {
        this.appRecommend = appRecommend;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public double getAverageScore()
    {
        return averageScore;
    }

    public void setAverageScore(double averageScore)
    {
        this.averageScore = averageScore;
    }

    @Transient
	public double getAverageStar() {
		return averageStar;
	}

	public void setAverageStar(double averageStar) {
		this.averageStar = averageStar;
	}

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }

    public String getSystem()
    {
        return system;
    }

    public void setSystem(String system)
    {
        this.system = system;
    }

    @Transient
    public DpType getSystemDpType()
    {
        return systemDpType;
    }

    public void setSystemDpType(DpType systemDpType)
    {
        this.systemDpType = systemDpType;
    }

    public String getDeveloper()
    {
        return developer;
    }

    public void setDeveloper(String developer)
    {
        this.developer = developer;
    }

    @OneToMany
    @Cascade(value =
    { CascadeType.SAVE_UPDATE, CascadeType.REMOVE })
    @JoinColumn(name = "C_DPAPPINFO_ID")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @OrderBy(clause = "C_CREATE_DATE")
    public List<AttachmentFile> getAttachmentList()
    {
        return attachmentList;
    }

    public void setAttachmentList(List<AttachmentFile> attachmentList)
    {
        this.attachmentList = attachmentList;
    }

    @Transient
    public List<AppCommentInfo> getAppCommentInfoList()
    {
        return appCommentInfoList;
    }

    public void setAppCommentInfoList(List<AppCommentInfo> appCommentInfoList)
    {
        this.appCommentInfoList = appCommentInfoList;
    }

    @Transient
    public AppSign getAppSign()
    {
        return appSign;
    }

    public void setAppSign(AppSign appSign)
    {
        this.appSign = appSign;
    }

    @Transient
    public long getDownloadCount()
    {
        return downloadCount;
    }

    public void setDownloadCount(long downloadCount)
    {
        this.downloadCount = downloadCount;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    @Transient
    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    @Transient
    public double getSize()
    {
        return size;
    }

    public void setSize(double size)
    {
        this.size = size;
    }

    public int getVersionCode()
    {
        return versionCode;
    }

    public void setVersionCode(int versionCode)
    {
        this.versionCode = versionCode;
    }

    public String getSubjectPoster()
    {
        return subjectPoster;
    }

    public void setSubjectPoster(String subjectPoster)
    {
        this.subjectPoster = subjectPoster;
    }

    @Transient
	public DpType getLanguageDpType() {
		return languageDpType;
	}

	public void setLanguageDpType(DpType languageDpType) {
		this.languageDpType = languageDpType;
	}

	public int getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}



	public int getHandDownCount() {
		return handDownCount;
	}

	public void setHandDownCount(int handDownCount) {
		this.handDownCount = handDownCount;
	}

	public double getHandAvgScore() {
		return handAvgScore;
	}

	public void setHandAvgScore(double handAvgScore) {
		this.handAvgScore = handAvgScore;
	}

	public int getHandScoreCount() {
		return handScoreCount;
	}

	public void setHandScoreCount(int handScoreCount) {
		this.handScoreCount = handScoreCount;
	}

	public Integer getOpMode() {
		return opMode;
	}

	public void setOpMode(int opMode) {
		this.opMode = opMode;
	}

	@Transient
	public List<String> getOpModeList() {
		List<String> list = new ArrayList<String>();
		if(null != opMode){
			int maxLen = 12;
			String opModeStr = new BigInteger(String.valueOf(opMode)).toString(2);
			int opModeLen = opModeStr.length();
			opModeStr = StringUtils.leftPad(opModeStr, maxLen, "0");

			String temp = "";
			for (int i = 0; i < opModeLen; i++) {
				temp = String.valueOf(opModeStr.charAt(maxLen-(i+1)));
				if (temp.equals("1")) {
					temp = StringUtils.leftPad(temp, maxLen-i, "0");
					temp = StringUtils.rightPad(temp, maxLen, "0");
					list.add(temp);
				}
			}
		}
		return list;
	}

	public void setOpModeList(List<String> opModeList) {
		BigInteger sum = new BigInteger("0");
		for (String s : opModeList) {
			BigInteger num = new BigInteger(s, 2);
			sum = sum.add(num);
		}
		this.setOpMode(sum.intValue());
	}

	
	
    
    
}
