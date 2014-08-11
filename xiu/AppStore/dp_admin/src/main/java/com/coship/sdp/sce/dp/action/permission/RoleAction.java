package com.coship.sdp.sce.dp.action.permission;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.coship.sdp.common.log.service.OpLoggerService;
import com.coship.sdp.permission.DelFailException;
import com.coship.sdp.permission.entity.Resource;
import com.coship.sdp.permission.entity.Role;
import com.coship.sdp.permission.entity.User;
import com.coship.sdp.permission.service.ResourceService;
import com.coship.sdp.permission.service.RoleService;
import com.coship.sdp.sce.dp.common.BaseAction;
import com.coship.sdp.sce.dp.common.Constants;
import com.coship.sdp.utils.Page;

/**
 * 角色管理action.
 *
 * @author
 *
 */
public class RoleAction extends BaseAction
{

    /**
     * 日志对象.
     */
    private static final Logger LOGGER = Logger.getLogger(RoleAction.class);

    /**
	 *
	 */
    private static final long serialVersionUID = -6819749400375865813L;

    /**
     * 分页集合类.
     */
    private Page<Role> page;

    /**
     * 角色实体.
     */
    private Role role;

    /**
     * 角色ID.
     */
    private String roleId;

    /**
     * 多个角色ID组成的字符串以","分隔.
     */
    private String ids;

    /**
     * 资源列表.
     */
    private List<Resource> res = new ArrayList<Resource>();

    /**
     * 角色对应资源关系集合.
     */
    private Map<String, Boolean> rolePerMap = new HashMap<String, Boolean>();

    /**
     * RoleService 角色操作接口.
     */
    @Autowired
    private RoleService roleService;

    /**
     * ResourceService 资源操作接口.
     */
    @Autowired
    private ResourceService resService;

    /**
     * 错误信息.
     */
    private String errorMsg;

    /**
     * 操作日志.
     */
    private OpLoggerService opLoggerService;

    /**
     * 角色名.
     */
    private String roleName;

    /**
     * 分页查询角色信息列表.
     *
     * @return
     * @throws Exception
     */
    public String searchRole() throws Exception
    {
        if (LOGGER.isDebugEnabled())
        {
            LOGGER.debug("searchRole start role is " + role);
        }

        this.page = new Page<Role>();
        page.setPageSize(this.limit);
        page.setCurrentPage(this.start);

        // 查询条件
        this.page = this.roleService.listRole(page, role, null);
        if (LOGGER.isDebugEnabled())
        {
            LOGGER.debug("searchRole end page.getResultList().size() is "
                    + page.getResultList().size());
        }

        return "roleList";
    }

    /**
     * 进入角色授权页面，提供初始化数据.
     *
     * @return
     * @throws Exception
     */
    public String rolePermission() throws Exception
    {
        if (LOGGER.isDebugEnabled())
        {
            LOGGER.debug("rolePermission start roleId is " + roleId);
        }

        // 查询资源列表
        res = resService.searchResource();
        // 用户可以操作的资源
        rolePerMap = roleService.searchRoleRes(roleId);

        if (LOGGER.isDebugEnabled())
        {
            LOGGER.debug("rolePermission end rolePerMap is " + rolePerMap);
        }
        return "rolePermission";
    }

    /**
     * 给角色添加资源操作权限.
     *
     * @return
     * @throws Exception
     */
    public String addPermission() throws Exception
    {
        if (LOGGER.isDebugEnabled())
        {
            LOGGER.debug("addPermission start roleId is " + roleId + "\r\n"
                    + "rolePerMap is " + rolePerMap);
        }

        roleService.addPermission(rolePerMap, roleId);

        if (LOGGER.isDebugEnabled())
        {
            LOGGER.debug("addPermission end");
        }
        return rolePermission();
    }

    /**
     * 删除角色信息.
     *
     * @return
     * @throws Exception
     */
    public String deleteRole() throws Exception
    {
        if (LOGGER.isDebugEnabled())
        {
            LOGGER.debug("deleteRole start ids is " + ids);
        }
        User user = (User) this.request.getSession().getAttribute(
                Constants.LOGIN_SESSION_USER);

        String[] idsStrArray = ids.split(",");
        List<String> idsStr = new ArrayList<String>(idsStrArray.length);
        for (int i = 0; i < idsStrArray.length; i++)
        {
            idsStr.add(idsStrArray[i]);
        }
        String msg = roleService.deleteRole(idsStr);
        String roleNames = "";
        // 提示信息统一
        if (!"SUCCESS".equals(msg))
        {
            exception_msg = msg;
            return ERROR;
        }
        else
        {
            try
            {
                roleNames = roleService.delRoles(idsStr);
                if (roleNames == null || "".equals(roleNames.trim()))
                {
                    exception_msg = getText("sdp.sce.dp.admin.role.del.failed");
                    return ERROR;
                }
                // 日志参数
                List<String> logParamList = initLogParame(user.getUserName(),
                        "sdp.sce.dp.admin.role",
                        "sdp.sce.dp.admin.log.delete.operate", roleNames);
                // 记录日志
                opLoggerService.info(this
                        .getText("sdp.sce.dp.admin.permission"), this.getText(
                        "sdp.sce.dp.admin.log.role.delete.log", logParamList),
                        this.getText(Constants.DEL));

            }
            catch (Exception e)
            {
                exception_msg = getText("sdp.sce.dp.admin.role.del.failed");
                if (e instanceof DelFailException)
                {
                    exception_msg = getText("sdp.sce.dp.admin.role.del.failed")
                            + (e.getMessage() == null ? "" : e.getMessage());
                }

                return ERROR;

            }
        }

        // errorMsg = msg;

        if (LOGGER.isDebugEnabled())
        {
            LOGGER.debug("deleteRole end errorMsg is " + errorMsg);
        }
        return searchRole();
    }

    /**
     * 转向添加角色页面,同时取出资源列表.
     *
     * @return
     * @throws Exception
     */
    public String showAdd() throws Exception
    {
        if (LOGGER.isDebugEnabled())
        {
            LOGGER.debug("showAdd start ");
        }
        // 查询资源列表
        res = resService.searchResource();

        if (LOGGER.isDebugEnabled())
        {
            LOGGER.debug("showAdd end res is " + res);
        }
        return "roleAdd";
    }

    /**
     * 添加新角色.
     *
     * @return
     * @throws Exception
     */
    public String addRole() throws Exception
    {
        if (LOGGER.isDebugEnabled())
        {
            LOGGER.debug("addRole start");
        }

        User user = (User) request.getSession().getAttribute("user");
        role.setCreatedUser(user.getUserName());
        role.setCreatedDate(new Date());
        // add 添加修改时间
        role.setUpdatedDate(new Date());

        roleService.addRole(role);
        // Begin 2011-8-3 授予角色权限
        roleService.addPermission(rolePerMap, role.getId());

        // 日志参数
        List<String> logParamList = initLogParame(user.getUserName(),
                "sdp.sce.dp.admin.role", "sdp.sce.dp.admin.log.add.operate",
                role.getName());
        // 记录日志
        opLoggerService
                .info(this.getText("sdp.sce.dp.admin.permission"), this
                        .getText("sdp.sce.dp.admin.log.role.add.log",
                                logParamList), this.getText(Constants.ADD));

        // End 2011-8-3
        if (LOGGER.isDebugEnabled())
        {
            LOGGER.debug("addRole end ");
        }
        // 清除查询条件
        role = null;

        return this.searchRole();
    }

    /**
     * 转向角色修改页面.
     *
     * @return
     * @throws Exception
     */
    public String showModify() throws Exception
    {
        if (LOGGER.isDebugEnabled())
        {
            LOGGER.debug("showModify start roleId is " + roleId);
        }

        role = roleService.findRole(roleId);

        // Begin 2011-8-4 授予角色权限
        res = resService.searchResource(); // 查询资源列表
        rolePerMap = roleService.searchRoleRes(roleId); // 用户可以操作的资源
        // End 2011-8-4
        if (LOGGER.isDebugEnabled())
        {
            LOGGER.debug("showModify end res is " + res);
        }
        return "roleModify";
    }

    /**
     * 修改角色信息.
     *
     * @return
     * @throws Exception
     */
    public String modifyRole() throws Exception
    {
        if (LOGGER.isDebugEnabled())
        {
            LOGGER.debug("modifyRole start role is " + role);
        }
        User user = (User) request.getSession().getAttribute("user");
        role.setUpdatedDate(new Date());
        roleService.modifyRole(role);

        // Begin 2011-8-4授予角色权限
        roleService.addPermission(rolePerMap, role.getId());
        // 日志参数
        List<String> logParamList = initLogParame(user.getUserName(),
                "sdp.sce.dp.admin.role", "sdp.sce.dp.admin.log.update.operate",
                role.getName());
        // 记录日志
        opLoggerService.info(this.getText("sdp.sce.dp.admin.permission"), this
                .getText("sdp.sce.dp.admin.log.role.delete.log", logParamList),
                this.getText(Constants.MOD));

        // End 2011-8-4
        if (LOGGER.isDebugEnabled())
        {
            LOGGER.debug("modifyRole end ");
        }
        // 清除查询条件
        role = null;

        return this.searchRole();
    }

    /**
     * 显示角色详细信息 add by 906092.
     *
     * @return
     */
    public String showRoleDetail()
    {
        if (LOGGER.isDebugEnabled())
        {
            LOGGER.debug("showRoleDetail start roleId is " + roleId);
        }
        try
        {
            role = roleService.findRole(roleId);

            // 授予角色权限
            res = resService.searchResource(); // 查询资源列表
            rolePerMap = roleService.searchRoleRes(roleId); // 用户可以操作的资源
        }
        catch (Exception e)
        {
            LOGGER.error("showRoleDetail error", e);
            exception_msg = getText("sdp.sce.dp.admin.role.show.details.failed");
            return ERROR;
        }

        if (LOGGER.isDebugEnabled())
        {
            LOGGER.debug("showRoleDetail end res is " + res);
        }
        return "showRoleDetail";
    }

    /**
     * 判断角色名是否相同.
     *
     * @return
     */
    public String isRoleNameSame()
    {
        try
        {
            if (LOGGER.isDebugEnabled())
            {
                LOGGER.debug("isRoleNameSame start");
            }

            List<Role> roleList = roleService.findRoleByName(roleName);

            if (roleList != null && roleList.size() > 0)
            {
                this.setResult("msg", getText("sdp.sce.dp.admin.role.exists"));
            }
            else
            {
                this.setResult("success", true);
            }

        }
        catch (Exception e)
        {
            this.setResult("msg", getText("sdp.sce.dp.admin.query.failed"));
            LOGGER.error("findRoleByName error ", e);
        }
        this.write(JSONObject.fromObject(this.getResult()).toString());
        if (LOGGER.isDebugEnabled())
        {
            LOGGER.debug("isRoleNameSame end");
        }
        return null;
    }

    public Page<Role> getPage()
    {
        return page;
    }

    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    public void setPage(Page<Role> page)
    {
        this.page = page;
    }

    public Role getRole()
    {
        return role;
    }

    public void setRole(Role role)
    {
        this.role = role;
    }

    public String getRoleId()
    {
        return roleId;
    }

    public void setRoleId(String roleId)
    {
        this.roleId = roleId;
    }

    public List<Resource> getRes()
    {
        return res;
    }

    public void setRes(List<Resource> res)
    {
        this.res = res;
    }

    public Map<String, Boolean> getRolePerMap()
    {
        return rolePerMap;
    }

    public void setRolePerMap(Map<String, Boolean> rolePerMap)
    {
        this.rolePerMap = rolePerMap;
    }

    public RoleService getRoleService()
    {
        return roleService;
    }

    public void setRoleService(RoleService roleService)
    {
        this.roleService = roleService;
    }

    public ResourceService getResService()
    {
        return resService;
    }

    public void setResService(ResourceService resService)
    {
        this.resService = resService;
    }

    public String getErrorMsg()
    {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg)
    {
        this.errorMsg = errorMsg;
    }

    public String getIds()
    {
        return ids;
    }

    public void setIds(String ids)
    {
        this.ids = ids;
    }

    public OpLoggerService getOpLoggerService()
    {
        return opLoggerService;
    }

    public void setOpLoggerService(OpLoggerService opLoggerService)
    {
        this.opLoggerService = opLoggerService;
    }

}
