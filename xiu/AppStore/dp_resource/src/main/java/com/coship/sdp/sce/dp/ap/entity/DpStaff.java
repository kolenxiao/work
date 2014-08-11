/*
 * 文件名称：DpStaff.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：存储开发者的基本信息,合作资料信息和支付信息.
 * 创 建 人：jiangjinfeng/906974
 * 创建时间：2012-9-3
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.ap.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.coship.sdp.access.entity.EntityObject;

/**
 * 开发者信息实体类
 * @author 905323
 * @version [版本号, 2011-8-29]
 * @since [产品/模块版本]
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DpStaff extends EntityObject
{

    /**
     * 序列号
     */
    private static final long serialVersionUID = 2998972826383553174L;

    /**
     * 开发者类型，分为企业和个人 ,"0":为企业,"1":为个人.
     */
    private String developerTypeCode;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 昵称/公司简称
     */
    private String nickName;

    /**
     * 密码
     */
    private String passWord;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 公司名称
     */
    private String corpName;

    /**
     * 营业执照号码
     */
    private String bizLicense;

    /**
     * 个人真实姓名
     */
    private String realName;

    /**
     * 性别 "0":为男,"1":为女
     */
    private String sex;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 个人/公司简介
     */
    private String briefIntro;

    /**
     * 个人头像/企业logo
     */
    private String headIcon;

    /**
     * 证件类型
     */
    private String identityCardType;

    /**
     * 证件号码
     */
    private String identityCard;

    /**
     * 证件扫描件
     */
    private String identityImg;

    /**
     * 手机号码
     */
    private String mobileNum;

    /**
     * 固定电话号码
     */
    private String phoneNum;

    /**
     * 邮编
     */
    private String postCode;

    /**
     * 通信地址
     */
    private String address;

    /**
     * 支付方式 "0":银行支付,"1":第三方支付
     */
    private String payMethod;

    /**
     * 银行名称/支付平台名称
     */
    private String bankName;

    /**
     * 账户名称/支付账号名称
     */
    private String bankAccountName;

    /**
     * 账户号码
     */
    private String bankCardNum;

    /**
     * 状态（1001=审核不通过，1002=待审核，1003=审核通过,1004=草稿）
     */
    private String staffStatus;

    /**
     * 创建时间
     */
    private Date beginDpStaffTime;

    /**
     * 更新时间
     */
    private Date updateDpStaffTime;

    /**
     * 创建开始时间 < 作用描述：该字段是用于页面列表查询时的开始时间字段值,不保存数据库>
     */
    private String beginDpStaffCtime;

    /**
     * 创建结束时间 < 作用描述：该字段是用于页面列表查询时的开始时间字段值,不保存数据库>
     */
    private String endDpStaffCtime;

    /**
     * 开户行实名(用于保存查数据字典后得到的值)
     */
    private String saveBankName;

    public Date getBeginDpStaffTime()
    {
        Date date = beginDpStaffTime;
        return date;
    }

    public void setBeginDpStaffTime(Date beginDpStaffTime)
    {
        if (null != beginDpStaffTime)
        {
            this.beginDpStaffTime = (Date) beginDpStaffTime.clone();
        }
        else
        {
            this.beginDpStaffTime = new Date();
        }

    }

    public Date getUpdateDpStaffTime()
    {
        Date date = updateDpStaffTime;
        return date;
    }

    public void setUpdateDpStaffTime(Date updateDpStaffTime)
    {
        if (null != updateDpStaffTime)
        {
            this.updateDpStaffTime = (Date) updateDpStaffTime.clone();
        }
        else
        {
            this.updateDpStaffTime = new Date();
        }
    }

    @Transient
    public String getEndDpStaffCtime()
    {
        return endDpStaffCtime;
    }

    public void setEndDpStaffCtime(String endDpStaffCtime)
    {
        this.endDpStaffCtime = endDpStaffCtime;
    }

    @Transient
    public String getSaveBankName()
    {
        return saveBankName;
    }

    public void setSaveBankName(String saveBankName)
    {
        this.saveBankName = saveBankName;
    }

    @Transient
    public String getBeginDpStaffCtime()
    {
        return beginDpStaffCtime;
    }

    public void setBeginDpStaffCtime(String beginDpStaffCtime)
    {
        this.beginDpStaffCtime = beginDpStaffCtime;
    }

    @Column(length = 20, nullable = false)
    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    @Column(length = 64, nullable = false)
    public String getPassWord()
    {
        return passWord;
    }

    public void setPassWord(String passWord)
    {
        this.passWord = passWord;
    }

    @Column(length = 40)
    public String getRealName()
    {
        return realName;
    }

    public void setRealName(String realName)
    {
        this.realName = realName;
    }

    @Column(length = 255)
    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    @Column(length = 255)
    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    @Column(length = 20)
    public String getNickName()
    {
        return nickName;
    }

    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    public String getIdentityCardType()
    {
        return identityCardType;
    }

    public void setIdentityCardType(String identityCardType)
    {
        this.identityCardType = identityCardType;
    }

    @Column(length = 30)
    public String getIdentityCard()
    {
        return identityCard;
    }

    public void setIdentityCard(String identityCard)
    {
        this.identityCard = identityCard;
    }

    @Column(length = 64)
    public String getIdentityImg()
    {
        return identityImg;
    }

    public void setIdentityImg(String identityImg)
    {
        this.identityImg = identityImg;
    }

    @Column(length = 64)
    public String getHeadIcon()
    {
        return headIcon;
    }

    public void setHeadIcon(String headIcon)
    {
        this.headIcon = headIcon;
    }

    @Column(length = 128)
    public String getBankAccountName()
    {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName)
    {
        this.bankAccountName = bankAccountName;
    }

    @Column(length = 32)
    public String getBankName()
    {
        return bankName;
    }

    public void setBankName(String bankName)
    {
        this.bankName = bankName;
    }

    @Column(length = 128)
    public String getBankCardNum()
    {
        return bankCardNum;
    }

    public void setBankCardNum(String bankCardNum)
    {
        this.bankCardNum = bankCardNum;
    }

    @Column(length = 16)
    public String getMobileNum()
    {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum)
    {
        this.mobileNum = mobileNum;
    }

    @Column(length = 4)
    public String getStaffStatus()
    {
        return staffStatus;
    }

    public void setStaffStatus(String staffStatus)
    {
        this.staffStatus = staffStatus;
    }

    public String getDeveloperTypeCode()
    {
        return developerTypeCode;
    }

    public void setDeveloperTypeCode(String developerTypeCode)
    {
        this.developerTypeCode = developerTypeCode;
    }

    public String getCorpName()
    {
        return corpName;
    }

    public void setCorpName(String corpName)
    {
        this.corpName = corpName;
    }

    public String getBizLicense()
    {
        return bizLicense;
    }

    public void setBizLicense(String bizLicense)
    {
        this.bizLicense = bizLicense;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public Date getBirthday()
    {
        return birthday;
    }

    public void setBirthday(Date birthday)
    {
        this.birthday = birthday;
    }

    public String getPhoneNum()
    {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum)
    {
        this.phoneNum = phoneNum;
    }

    public String getPostCode()
    {
        return postCode;
    }

    public void setPostCode(String postCode)
    {
        this.postCode = postCode;
    }

    public String getPayMethod()
    {
        return payMethod;
    }

    public void setPayMethod(String payMethod)
    {
        this.payMethod = payMethod;
    }

    @Column(length = 1000)
    public String getBriefIntro()
    {
        return briefIntro;
    }

    public void setBriefIntro(String briefIntro)
    {
        this.briefIntro = briefIntro;
    }

}
