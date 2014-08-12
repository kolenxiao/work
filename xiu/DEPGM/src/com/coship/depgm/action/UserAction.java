/*
 * 文件名称：LoginAction.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd.
 *  Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：chenjiawei/903903
 * 创建时间：2011-7-5
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.depgm.action;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.coship.depgm.common.DepgmConfig;
import com.coship.depgm.model.User;

/**
 * 用户管理action.
 *
 * @author hanqinghong/909190
 * @version [版本号, 2014-7-31]
 * @since [产品/模块版本]
 */
public class UserAction extends BaseAction {

    /**
     * 日志对象.
     */
    private static final Logger LOGGER = Logger.getLogger(UserAction.class);

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 用户名或密码不正确.
     */
    private static final String USER_STR1 = " 用户名或密码不正确";

    /**
     * 用户id.
     */
    private String id = null;

    /**
     * 用户.
     */
    private User user = null;

    /**
     * 信息.
     */
    private String message = null;

    /**
     * id集合.
     */
    private String ids;

    /**
     * 用户名.
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 错误消息.
     */
    private String errorMsg;



    /**
     * 用户id编号.
     */
    private Long userID;


    /**
     * 用户登录，将用户信息和显示菜单列表放入session.
     * @return
     */
    public String login() {
        // 给密码加密
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("login start");
        }
        String user=DepgmConfig.getDefaultUser();
        String pwd=DepgmConfig.getDefaultPwd();
        if(user.equals(userName)&&pwd.equals(password)){

        	HttpSession loginSession = request.getSession();

			loginSession.setAttribute("userName", userName);
			loginSession.setAttribute("buildDate", new Date());

			// 为从登录界面取得的用户名，把它加入到cookie里面
			Cookie cookie = new Cookie("jformSSOCookieNameUser",
					userName);
			cookie.setPath("/");
			// 设置cookie的生命周期为：会话级，即浏览器关闭，该cookie就消失了
			cookie.setMaxAge(-1);
			response.addCookie(cookie);
			
			return "loginSuccess";
        }else{
        	message=USER_STR1;
        	return "loginFail";
        }

    }

    /**
     * 注销 清除中session的用户登录信息.
     *
     * @return
     */
    public String logout() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("logout start");
        }
        request.getSession().removeAttribute("userName");

        // 2011-12-21 update start
        Cookie cookie = new Cookie("jformSSOCookieNameUser", null);
        cookie.setMaxAge(0); // delete the cookie.
        cookie.setPath("/");
        response.addCookie(cookie);
        // 2011-12-21 update end
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("logout end");
        }
        return "logout";
    }

    /**
     * 返回 id.
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * 对id进行赋值.
     * @param
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 返回 user.
     * @return
     */
    public User getUser() {
        return user;
    }

    /**
     * 对user进行赋值.
     * @param
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * 返回 message.
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     * 对message进行赋值.
     * @param
     */
    public void setMessage(String message) {
        this.message = message;
    }

   
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

   
    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
}
