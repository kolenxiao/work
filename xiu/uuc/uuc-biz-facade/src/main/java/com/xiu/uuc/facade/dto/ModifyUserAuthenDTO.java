package com.xiu.uuc.facade.dto;

import java.io.Serializable;

/** 
 *************************************************************** 
 * <p>
 * @DESCRIPTION : 
 * @AUTHOR : kolenxiao@xiu.com 
 * @DATE :2012-8-10 上午9:18:09
 * </p>
 **************************************************************** 
 */
public class ModifyUserAuthenDTO implements Serializable {

    /**    
     *    
     */     
    
    private static final long serialVersionUID = -5345603454832323489L;

    /**
     * 用户ID
     */
    private Long userId ;

    /**
     * 认证类型(0:邮箱, 1:手机)
     */
    private String authenType ;

    /**
     * 认证值
     */
    private String authenValue ;

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

    public String getAuthenType() {
        return authenType;
    }

    public void setAuthenType(String authenType) {
        this.authenType = authenType;
    }

    public String getAuthenValue() {
        return authenValue;
    }

    public void setAuthenValue(String authenValue) {
        this.authenValue = authenValue;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }
    
    

}
