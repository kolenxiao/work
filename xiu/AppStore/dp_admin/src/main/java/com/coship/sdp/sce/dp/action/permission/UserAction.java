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
package com.coship.sdp.sce.dp.action.permission;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.coship.sdp.common.log.service.OpLoggerService;
import com.coship.sdp.permission.DelFailException;
import com.coship.sdp.permission.QueryUserCondition;
import com.coship.sdp.permission.entity.Resource;
import com.coship.sdp.permission.entity.Role;
import com.coship.sdp.permission.entity.User;
import com.coship.sdp.permission.entity.UserRole;
import com.coship.sdp.permission.service.RoleService;
import com.coship.sdp.permission.service.UserService;
import com.coship.sdp.sce.dp.common.BaseAction;
import com.coship.sdp.sce.dp.common.Constants;
import com.coship.sdp.utils.Page;
import com.coship.sdp.utils.SystemConfig;

/**
 * 用户管理action.
 *
 * @author chenjiawei/903903
 * @version [版本号, 2011-7-5]
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
    private static final String USER_STR1 = "sdp.sce.dp.portal.ap.login.error";

    /**
     * 用户名已失效!请联系管理员.
     */
    private static final String USER_STR2 = "sdp.sce.dp.admin.user.expired";

    /**
     * 验证码不正确.
     */
    private static final String USER_STR3 = "sdp.sce.dp.admin.user.verification.code.incorrect";

    /**
     * 同一浏览器登录.
     */
    private static final String USER_STR4 = "sdp.sce.dp.admin.user.login.same.window";

    /**
     * 已过期，请重新登录.
     */
    private static final String USER_STR5 = "sdp.sce.dp.admin.user.login.expired";

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
     * 分页.
     */
    private Page<User> page;

    /**
     * id集合.
     */
    private String ids;

    /**
     * 角色id集合.
     */
    private Long[] checkbox;

    /**
     * 用户旧密码.
     */
    private String oldPassword;

    /**
     * 用户名.
     */
    private String userName;

    /**
     * 存放用户所拥有的角色集合.
     */
    private Map<Long, Boolean> userSelectedRoleMap = new HashMap<Long, Boolean>();

    /**
     * 所有用户列表.
     */
    private List<Role> allRoleList = new ArrayList<Role>();

    /**
     * 用户增加时保存用户角色id.
     */
    private List<String> selectRole = new ArrayList<String>();

    /**
     * 查询用户条件.
     */
    private QueryUserCondition qureyCon;

    /**
     * 用户角色映射.
     */
    private Map<String, String> usrRoleMap = new HashMap<String, String>();

    /**
     * 错误消息.
     */
    private String errorMsg;

    /**
     * 该用户已经存在.
     */
    private static final String ERROR_CODE = "sdp.sce.dp.admin.user.exists";

    /**
     * 用户id编号.
     */
    private Long userID;

    /**
     * 角色集合.
     */
    private Page<Role> role;

    /**
     * 用户角色列表.
     */
    private List<Role> userRoleList = new ArrayList<Role>();

    /**
     * 角色服务对象.
     */
    @Autowired
    private RoleService roleService;

    /**
     * 操作日志.
     */
    private OpLoggerService opLoggerService;

    /**
     * 服务操作接口.
     */
    private UserService userService = null;

    /**
     * 用户登录，将用户信息和显示菜单列表放入session.
     * @return
     */
    public String login() {
        // 给密码加密
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("login start");
        }
        User userLogined = userService.getUser(user.getUserName(),
                DigestUtils.md5Hex(user.getPassword()));
        if (userLogined == null) {
            this.setResult("msg", getText(USER_STR1));
        } else if (!"Y".equals(userLogined.getStatus())) {
            this.setResult("msg", getText(USER_STR2));
		} else {
			// 同一浏览器不同tab页签导致不同用户登录session一样的bug，修复
			HttpSession loginSession = request.getSession();
			String tempUserName = (String) loginSession
					.getAttribute("userName");
			if (StringUtils.isNotEmpty(tempUserName)
					&& !StringUtils.equals(tempUserName,
							userLogined.getUserName())) {
				this.setResult("msg", getText(USER_STR4));
				this.write(JSONObject.fromObject(this.getResult()).toString());
				return null;
			}
			loginSession.setAttribute("user", userLogined);
			loginSession.setAttribute("userName", userLogined.getUserName());

			List<Resource> res = userService
					.searchUserMenu(userLogined.getId());
			loginSession.setAttribute("resList", res);
			Map<String, Resource> userResMap = userService
					.searchResByUser(userLogined.getId());
			loginSession.setAttribute("userResMap", userResMap);
			// 版本信息
			String version = (String) SystemConfig.getInstance().getProperty(
					"version");
			String buildDate = (String) SystemConfig.getInstance().getProperty(
					"builddate");

			loginSession.setAttribute("version", version);
			loginSession.setAttribute("buildDate", buildDate);
			this.setResult("success", true);
			try {
				opLoggerService.info(
						this.getText("sdp.sce.dp.admin.login"),
						getText("sdp.sce.dp.admin.log.login",
								new String[] { userLogined.getUserName() }),
						getText(Constants.LOGIN));
			} catch (Exception e) {
				LOGGER.error("login exception", e);
				this.setResult("msg",
						getText("sdp.sce.dp.admin.log.exceptions"));
			}
			// 2011-12-21 update start
			// 为从登录界面取得的用户名，把它加入到cookie里面
			Cookie cookie = new Cookie("jforumSSOCookieNameUser",
					userLogined.getUserName());
			cookie.setPath("/");
			// 设置cookie的生命周期为：会话级，即浏览器关闭，该cookie就消失了
			cookie.setMaxAge(-1);
			response.addCookie(cookie);
			// 2011-12-21 update end
		}
        this.write(JSONObject.fromObject(this.getResult()).toString());
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("login end");
        }
        return null;

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
        User loginUser = (User) request.getSession().getAttribute("user");
        try {

            opLoggerService.info(
                    // this.getText("sdp.sce.dp.admin.log.other.operate"),
                    this.getText("sdp.sce.dp.admin.login"),
                    getText("sdp.sce.dp.admin.log.logout", new String[]
                    { loginUser.getUserName() }), getText(Constants.LOGIN));
        } catch (Exception e) {
            exception_msg = getText("sdp.sce.dp.admin.log.exceptions");
            LOGGER.error("logout record log error", e);
            return ERROR;
        }
        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("userName");
        request.getSession().removeAttribute("resList");
        // 2011-12-21 update start
        Cookie cookie = new Cookie("jforumSSOCookieNameUser", null);
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
     * 编辑用户.
     *
     * @return
     */
    public String editUser() {
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("editUser start id is " + id);
            }

            this.user = userService.getUser(id);
            userRoleList = roleService.listRole(userRoleList, id);
            allRoleList = roleService.listRole();
            
			if (null != allRoleList)
			{
				allRoleList.removeAll(userRoleList);
			}
            
            
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("editUser end user is " + user);
            }
            return "editUser";
        } catch (Exception e) {
            LOGGER.error("editUser error", e);
            exception_msg = getText("sdp.sce.dp.admin.user.edit.fail");
            return ERROR;

        }
    }

    /**
     * 更新用户信息.
     *
     * @return
     */
    public String updateUser() {
        if (user != null) {
            try {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("updateUser user is " + user);
                }

                // 设置用户的更新时间
                user.setUpdatedDate(new Date());

                // 更新用户表
                userService.updateUser(user);

                // 删除中间表t_user_role中关于该用户的的记录
                userService.deleteUserRole(user.getId());

                // 根据id找到对应的角色
                List<Role> roleList = new ArrayList<Role>();
                Role roleTemp = null;
                if (selectRole != null) {
                    for (String roleId : selectRole) {
                        if (roleId == null) {
                            continue;
                        }
                        roleTemp = roleService.findRole(roleId);
                        roleList.add(roleTemp);
                    }

                    // 向中间表t_user_role中插入用户角色记录
                    UserRole userRole;

                    for (Role r : roleList) {
                        userRole = new UserRole();
                        userRole.setUser(user);
                        userRole.setUserRole(r);
                        userService.saveUserRole(userRole);
                    }
                }
                // 如果修改用户为当前登录用户需要将session中的数据更新
                if (user.getUserName().equals(
                        ((User) request.getSession().getAttribute("user"))
                                .getUserName())) {
                    request.getSession().setAttribute("user", user);
                }
                User loginUser = (User) request.getSession().getAttribute(
                        "user");
                List<String> logParamList = initLogParame(
                        loginUser.getUserName(), "sdp.sce.dp.admin.user",
                        "sdp.sce.dp.admin.log.update.operate",
                        user.getUserName());
                opLoggerService.info(this
                        .getText("sdp.sce.dp.admin.permission"), this.getText(
                        "sdp.sce.dp.admin.log.user.operate.log", logParamList),
                        this.getText(Constants.MOD));

                return searchUser();
            } catch (RuntimeException re) {
                LOGGER.error("updateUser error ", re);
                exception_msg = getText("sdp.sce.dp.admin.user.update.fail");
                return ERROR;
            } catch (Exception e) {
                LOGGER.error("updateUser error ", e);
                exception_msg = getText("sdp.sce.dp.admin.user.update.fail");
                return ERROR;
            }
        } else {
            LOGGER.error("updateUser user is null");
            exception_msg = getText("sdp.sce.dp.admin.user.not.exist");
            return ERROR;
        }
    }

    /**
     * 跳转到修改登录用户密码界面.
     *
     * @return
     */
    public String gotoEditPassword() {
        this.user = (User) request.getSession().getAttribute("user");

        if (user != null) {
            return "gotoEditPassword";
        } else {
            setMessage(getText(USER_STR5));
            return "gotologin";
        }

    }

    /**
     * 修改密码.
     *
     * @return
     */
    public String editPassword() {
        try {
            User editUser = (User) request.getSession().getAttribute("user");
            editUser.setPassword(DigestUtils.md5Hex(user.getPassword()));
            userService.updateUser(editUser);
            message = getText("sdp.sce.dp.admin.user.change.passwd.success");
            // 将修改后的用户重新保存到缓存中
            request.getSession().setAttribute("user", editUser);
            List<String> logParamList = initLogParame(editUser.getUserName(),
                    "sdp.sce.dp.admin.user",
                    "sdp.sce.dp.admin.log.update.password.operate",
                    editUser.getUserName());
            opLoggerService.info(this
                    .getText("sdp.sce.dp.admin.log.other.operate"), this
                    .getText("sdp.sce.dp.admin.log.user.operate.log",
                            logParamList), this.getText(Constants.OTHER));

        } catch (Exception e) {
            message = getText("sdp.sce.dp.admin.user.change.passwd.fail");
            LOGGER.error("update user password error!", e);
        }

        return "editPassword";
    }

    /**
     * 判断原始密码是否正确.
     *
     * @return
     */
    @SuppressWarnings("unused")
    public String isOldPasswordRight() {
        try {
            User tempUser = (User) request.getSession().getAttribute("user");
            // 从数据库里重新取出密码
            User tempUser2 = this.userService.getUserByName(
                    tempUser.getUserName()).get(0);
            if (tempUser2 != null) {
                if (!tempUser2.getPassword().equals(
                        DigestUtils.md5Hex(uRLDecoder(oldPassword)))) {
                    setResult(
                            "msg",
                            getText("sdp.sce.dp.admin.user.original.passwd.incorrect"));
                } else {
                    this.setResult("success", true);
                }
            } else {
                LOGGER.error("session is out of date");
                this.setResult("msg",
                        getText("sdp.sce.dp.admin.user.hasExpired"));
            }
        } catch (Exception e) {
            LOGGER.error("isOldPasswordRight error", e);
            this.setResult("msg",
                    getText("sdp.sce.dp.admin.user.obtain.info.fail"));
        }

        this.write(JSONObject.fromObject(this.getResult()).toString());
        return null;
    }

    /**
     * 解码UTF8.
     *
     * @param str
     * @return
     */
    private String uRLDecoder(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        try {
            str = URLDecoder.decode(str, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 新增用户时判断用户名是否相同.
     *
     * @return
     */
    public String isUserNameSame() {
        List<User> userList = null;
        try {
            userList = userService.getUserByName(userName);

            if (userList != null && userList.size() > 0) {
                this.setResult("msg", getText(ERROR_CODE));
            } else {
                this.setResult("success", true);
            }

        } catch (Exception e) {
            LOGGER.error("isUserNameSame error", e);
            this.setResult("msg", getText("sdp.sce.dp.admin.query.failed"));
        }

        this.write(JSONObject.fromObject(this.getResult()).toString());
        return null;

    }

    /**
     * 跳转到用户修改页面.
     *
     * @return
     */
    public String gotoUserEdit() {
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("gotoUserEdit start");
            }

            if (request.getSession().getAttribute("user") != null) {
                id = ((User) request.getSession().getAttribute("user")).getId();

                user = userService.getUser(id);
                return "editUserPage";
            } else {
                setMessage(getText(USER_STR5));
                return "logout";
            }

        } catch (Exception e) {
            LOGGER.error("gotoUserEdit error ", e);
        }
        exception_msg = getText("sdp.sce.dp.admin.user.jump.modify.error");
        return ERROR;
    }

    /**
     * 查询用户.
     *
     * @return
     */
    public String searchUser() {
        try {
            // this.getUserPermission(this.getEnName());
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("searchUser start");
            }
            this.page = new Page<User>();
            page.setPageSize(this.limit);
            page.setCurrentPage(this.start);

            // 查询条件
            // this.page = this.userService.listUser(page, "from User", null);

            // 修改问题单号：E2011080640
            // 修改人：906092
            // 修改原因：没有按条件进行分页查询
            this.page = this.userService.searchUser(page, qureyCon);

            // 记录用户所选择的角色
            usrRoleMap = userService.getUserRole();
            // 记录所有的角色
            this.allRoleList = roleService.listRole();
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("searchUser end");
            }
        } catch (Exception e) {
            LOGGER.error("searchUser error ", e);
            exception_msg = getText("sdp.sce.dp.admin.user.find.fail");
            return ERROR;
        }
        return "userList";
    }

    /**
     *
     * 根据条件查询用户.
     *
     * @return
     */
    public String searchUserByCondition() {
        try {
            // this.getUserPermission(this.getEnName());
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("searchUserByCondition qureyCon is " + qureyCon);
            }

            this.page = new Page<User>();
            page.setPageSize(this.limit);
            page.setCurrentPage(this.start);

            // 查询条件
            this.page = this.userService.searchUser(page, qureyCon);
            // 记录用户所选择的角色
            usrRoleMap = userService.getUserRole();
            // 记录所有的角色
            this.allRoleList = roleService.listRole();

        } catch (Exception e) {
            LOGGER.error("searchUserByCondition error ", e);
            exception_msg = getText("sdp.sce.dp.admin.user.condition.query.error");
            return ERROR;
        }
        return "userList";
    }

    /**
     * 跳转到增加用户页面.
     *
     * @return
     */
    public String showAdd() {

        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("showAdd start");
            }
            userRoleList = roleService.listRole();
        } catch (Exception e) {
            LOGGER.error("showAdd error", e);
            exception_msg = getText("sdp.sce.dp.admin.user.jump.add.error");
            return ERROR;
        }
        return "userAdd";
    }

    /**
     * 增加用户.
     *
     * @return
     */
    public String addUser() {

        try {
            if (user == null) {
                exception_msg = getText("sdp.sce.dp.admin.user.add.empty");
                return ERROR;
            }

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("addUser user is " + user);
            }

            // 设置 用户的创建时间和用户创建者
            user.setCreatedDate(new Date());

            // 设置用户的最近修改时间
            user.setUpdatedDate(new Date());

            // add by 906092 on 2011-9-1
            // 为用户设置生效状态
            user.setStatus("Y");

            // 对密码进行加密
            user.setPassword(DigestUtils.md5Hex(user.getPassword()));
            if (request.getSession().getAttribute("user") != null
                    && request.getSession().getAttribute("user") instanceof User) {
                user.setCreatedUser(((User) request.getSession().getAttribute(
                        "user")).getUserName());
            }

            // 将用户保存到数据库中
            userService.savaUser(user);

            // 将用户和角色的关系保存到数据库中
            // 根据id找到对应的角色
            List<Role> roleList = new ArrayList<Role>();
            Role roleObj = null;
            if (selectRole != null) {
                for (String roleId : selectRole) {
                    if (roleId == null) {
                        continue;
                    }
                    roleObj = roleService.findRole(roleId);
                    roleList.add(roleObj);
                }

                // 向中间表t_user_role中插入用户角色记录
                UserRole userRole;

                for (Role r : roleList) {
                    userRole = new UserRole();
                    userRole.setUser(user);
                    userRole.setUserRole(r);
                    userService.saveUserRole(userRole);
                }
            }
            User loginUser = (User) request.getSession().getAttribute("user");

            List<String> logParamList = initLogParame(loginUser.getUserName(),
                    this.getText("sdp.sce.dp.admin.user"),
                    this.getText("sdp.sce.dp.admin.log.add.operate"),
                    user.getUserName());

            opLoggerService.info(this.getText("sdp.sce.dp.admin.permission"),
                    this.getText("sdp.sce.dp.admin.log.user.operate.log",
                            logParamList), this.getText(Constants.ADD));

            return searchUser();
        } catch (RuntimeException re) {
            LOGGER.error("addUser error", re);
            exception_msg = getText("sdp.sce.dp.admin.user.add.fail");
            return ERROR;
        } catch (Exception e) {
            LOGGER.error("addUser error", e);
            exception_msg = getText("sdp.sce.dp.admin.user.add.fail");
            return ERROR;
        }
    }

    /**
     * 重置密码.
     *
     * @return
     */
    public String passwordReset() {
        try {
            User userObj = userService.getUser(id);

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("passwordReset user is " + userObj);
            }

            if (userObj != null) {
                userObj.setPassword(DigestUtils.md5Hex("123456"));
                userService.updateUser(userObj);

                // 如果密码重置用户为当前登录用户需要将session中的数据更新
                if (userObj.getUserName().equals(
                        ((User) request.getSession().getAttribute("user"))
                                .getUserName())) {
                    request.getSession().setAttribute("user", userObj);
                }

                User loginUser = (User) request.getSession().getAttribute(
                        "user");
                List<String> logParamList = initLogParame(
                        loginUser.getUserName(), "sdp.sce.dp.admin.user",
                        "sdp.sce.dp.admin.log.reset.password",
                        userObj.getUserName());
                opLoggerService.info(this
                        .getText("sdp.sce.dp.admin.permission"), this.getText(
                        "sdp.sce.dp.admin.log.user.operate.log", logParamList),
                        this.getText(Constants.MOD));

            }

            this.setResult("success", true);
        } catch (Exception e) {
            LOGGER.error("passwordReset error ", e);
            exception_msg = getText("sdp.sce.dp.admin.user.passwd.reset.error");
            this.setResult("exception", true);
            return ERROR;

        }

        this.write(JSONObject.fromObject(this.getResult()).toString());

        return null;
    }

    /**
     * 删除用户.
     *
     * @return
     */

    public String doDelete() {
        StringBuffer userNames = new StringBuffer();
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("doDelete start ids is " + ids);
            }
            if (ids == null) {
                LOGGER.warn("ids is null");
                return searchUser();
            }
            // userNames = userService.deleteUsers(ids);

            String[] ids_Str = ids.split(",");

            for (int i = 0; i < ids_Str.length; i++) {
                User user_toDelete = userService.getUser(ids_Str[i]);
                user_toDelete.setStatus("N");
                userService.updateUser(user_toDelete);

                if (userNames.length() == 0) {
                    userNames.append(user_toDelete.getUserName());
                } else {
                    userNames.append(",");
                    userNames.append(user_toDelete.getUserName());
                }
            }

            user = ((User) request.getSession().getAttribute("user"));

            if (user == null) {
                LOGGER.warn("doDelete user is null");
                return "logout";
            }
            List<String> logParamList = initLogParame(user.getUserName(),
                    "sdp.sce.dp.admin.user",
                    "sdp.sce.dp.admin.log.delete.operate", userNames.toString());
            opLoggerService.info(this.getText("sdp.sce.dp.admin.permission"),
                    this.getText("sdp.sce.dp.admin.log.user.operate.log",
                            logParamList), this.getText(Constants.DEL));
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("doDelete end " + ids);
            }
            return searchUser();
        } catch (Exception e) {
            LOGGER.error("doDelete error ", e);
            if (e instanceof DelFailException) {
                exception_msg = getText("sdp.sce.dp.admin.user.del.fail")
                        + (e.getMessage() == null ? "" : e.getMessage());
            } else {
                exception_msg = getText("sdp.sce.dp.admin.log.exceptions");
            }

            return ERROR;
        }
    }

    /**
     * 显示登录用户详细信息.
     *
     * @return
     */
    public String userDetail() {
        user = ((User) request.getSession().getAttribute("user"));

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("userDetail user is " + user);
        }

        if (user == null) {
            LOGGER.warn("userDetail user is null");
            return "logout";
        }
        try {
            userRoleList = roleService.listRole(userRoleList, user.getId());
        } catch (Exception e) {
            LOGGER.error("userDetail error", e);
            exception_msg = getText("sdp.sce.dp.admin.user.show.info.error");
            return ERROR;
        }
        return "userDetail";
    }

    /**
     * 显示选择用户的详细信息.
     *
     * @return
     */
    public String viewUserDetail() {
        try {
            user = userService.getUser(id);
            userRoleList = roleService.listRole(userRoleList, id);
            allRoleList = roleService.listRole();

        } catch (Exception e) {
            LOGGER.error("query user error", e);
            exception_msg = getText("sdp.sce.dp.admin.user.show.info.error");
            return ERROR;
        }

        return "viewUserDetail";
    }

    /**
     * 跳转到登录用户修改自己信息的页面.
     *
     * @return
     */
    public String gotoUserselfEdit() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("gotoUserselfEdit start");
        }
        if ("userDetail".equals(userDetail())) {
            return "gotoUserselfEdit";
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("gotoUserselfEdit end userDetail() is " + userDetail());
        }
        return userDetail();
    }

    /**
     * 更新用户个人信息.
     *
     * @return
     */
    public String updateUserselfInformation() {
        if (user == null) {
            LOGGER.error("updateUserselfInformation user is null");
            exception_msg = getText("sdp.sce.dp.admin.user.not.exist");
            return ERROR;
        }
        try {
            user.setUpdatedDate(new Date());
            userService.updateUser(user);
            // 更新session中用户信息
            request.getSession().setAttribute("user", user);
            List<String> logParamList = initLogParame(user.getUserName(),
                    "sdp.sce.dp.admin.user",
                    "sdp.sce.dp.admin.log.update.userMsg.operate",
                    user.getUserName());
            opLoggerService.info(this
                    .getText("sdp.sce.dp.admin.log.other.operate"), this
                    .getText("sdp.sce.dp.admin.log.user.operate.log",
                            logParamList), this.getText(Constants.MOD));

        } catch (Exception e) {
            LOGGER.error("updateUserselfInformation error", e);
            exception_msg = getText("sdp.sce.dp.admin.user.update.info.error");
            return ERROR;
        }

        return userDetail();
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

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void deleteUser() throws Exception {
        userService.deleteUser(id);
    }

    public OpLoggerService getOpLoggerService() {
        return opLoggerService;
    }

    public void setOpLoggerService(OpLoggerService opLoggerService) {
        this.opLoggerService = opLoggerService;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public List<Role> getAllRoleList() {
        return allRoleList;
    }

    public void setAllRoleList(List<Role> allRoleList) {
        this.allRoleList = allRoleList;
    }

    public QueryUserCondition getQureyCon() {
        return qureyCon;
    }

    public void setQureyCon(QueryUserCondition qureyCon) {
        this.qureyCon = qureyCon;
    }

    public Map<String, String> getUsrRoleMap() {
        return usrRoleMap;
    }

    public void setUsrRoleMap(Map<String, String> usrRoleMap) {
        this.usrRoleMap = usrRoleMap;
    }

    public List<String> getSelectRole() {
        return selectRole;
    }

    public void setSelectRole(List<String> selectRole) {
        this.selectRole = selectRole;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Map<Long, Boolean> getUserSelectedRoleMap() {
        return userSelectedRoleMap;
    }

    public void setUserSelectedRoleMap(Map<Long, Boolean> userSelectedRoleMap) {
        this.userSelectedRoleMap = userSelectedRoleMap;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public Long[] getCheckbox() {
        Long[] checkboxTemp = checkbox;
        return checkboxTemp;
    }

    public void setCheckbox(Long[] checkbox) {

        this.checkbox = (Long[]) checkbox.clone();
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Page<Role> getRole() {
        return role;
    }

    public void setRole(Page<Role> role) {
        this.role = role;
    }

    public List<Role> getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(List<Role> userRoleList) {
        this.userRoleList = userRoleList;
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public Page<User> getPage() {
        return page;
    }

    public void setPage(Page<User> page) {
        this.page = page;
    }

}
