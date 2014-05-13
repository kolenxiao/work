package com.xiu.uuc.facade.dto;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 
 *************************************************************** 
 * <p>
 * 
 * @CLASS :com.xiu.uuc.facade.dto.LeadRegisterDTO
 * @DESCRIPTION :引导联盟用户注册传输对象
 * @AUTHOR :dick.xiao@xiu.com
 * @VERSION :v1.0
 * @DATE :2012-5-30 上午10:35:39
 *       </p>
 **************************************************************** 
 */
public class LeadRegisterDTO implements Serializable {

    /**    
     *    
     */

    private static final long serialVersionUID = 7902875279806953361L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户登录密码
     */
    private String password;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 渠道ID
     */
    private Integer channelId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
