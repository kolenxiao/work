package com.coship.sdp.sce.dp.action.permission;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.coship.sdp.common.log.service.OpLoggerService;
import com.coship.sdp.permission.DelFailException;
import com.coship.sdp.permission.entity.Resource;
import com.coship.sdp.permission.entity.User;
import com.coship.sdp.permission.service.ResourceService;
import com.coship.sdp.sce.dp.common.BaseAction;
import com.coship.sdp.sce.dp.common.Constants;
import com.coship.sdp.utils.Debug;
import com.coship.sdp.utils.Page;

/**
 * 资源管理action
 *
 * @author
 *
 */
public class ResourceAction extends BaseAction
{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模块名称.
     */
    private static final String MODULE = ResourceAction.class.getName();

    /**
     * 资源业务逻辑接口.
     */
    @Autowired
    private ResourceService resService;

    /**
     * 资源实体.
     */
    private Resource resource;

    /**
     * 查询结果集合.
     */
    private Page<Resource> page;

    /**
     * 默认初始值为-1.
     */
    private String id;

    /**
     * 父资源ID.
     */
    private String parentId;

    /**
     * 错误信息.
     */
    private String errorMsg;

    /**
     * 资源列表.
     */
    private List<Resource> res = new ArrayList<Resource>();

    /**
     * id列表字符串 eg：1_12_33.
     */
    private String idList;

    /**
     * 操作日志.
     */
    private OpLoggerService opLoggerService;

    /**
     * 菜单、按钮标记.
     */
    private int menuButtonFlag = -1;

    /**
     * 资源名称.
     */
    private String resName;

    /**
     * 资源名称.
     */
    private String resEnName;

    /**
     * 一级菜单.
     */
    private List<Resource> resLevelOne;

    /**
     * 子菜单.
     */
    private List<Resource> resChildren;

    /**
     * get一级菜单.
     *
     * @return
     */
    public List<Resource> getResLevelOne()
    {
        return resLevelOne;
    }

    /**
     * set一级菜单.
     *
     * @param resLevelOne
     */
    public void setResLevelOne(List<Resource> resLevelOne)
    {
        this.resLevelOne = resLevelOne;
    }

    /**
     * get子菜单.
     * @return
     */
    public List<Resource> getResChildren()
    {
        return resChildren;
    }

    /**
     * 设置子菜单.
     * @param resChildren
     */
    public void setResChildren(List<Resource> resChildren)
    {
        this.resChildren = resChildren;
    }

    /**
     * resource!searchRes.action 查询资源列表 返回到页面显示.
     *
     * @return
     * @throws Exception
     */
    public String searchRes() throws Exception
    {
        if (Debug.verboseOn())
        {
            Debug.logVerbose("searchRes start...", MODULE);
        }

        try
        {

            resLevelOne = resService
                    .findByHQL("from Resource r where r.level = 1");
            resChildren = resService
                    .findByHQL("from Resource r where r.level > 1");
        }
        catch (Exception e)
        {
            Debug.logError(e, e.getMessage(), MODULE);
            throw new RuntimeException(e);
        }

        return "resList";
    }

    /**
     * 跳转到添加资源页面 传入父资源ID并返回.
     *
     * @return
     */
    public String showAddRes()
    {
        if (Debug.verboseOn())
        {
            Debug.logVerbose("showAddRes start...", MODULE);
        }

        // 修改问题单号：【E2011091000】
        // 修改人：906092
        // 修改日期：2011-9-30
        // 修改原因：导航栏只显示2级菜单其它都为按钮，在新增服务时
        // 也只能添加2级菜单其它都为按钮
        // 修该开始：
        List<Resource> resList;
        try
        {
            // 判断新增服务时菜单还是按钮现阶段把一、二级导航都默认为菜单，其它默认为按钮
            menuButtonFlag = 0;
            if (id != null && id.length() > 0)
            {
                resList = resService.findManiMemuByID(id);
                if (resList == null || resList.size() == 0)
                {
                    menuButtonFlag = 1;
                }
            }
        }
        catch (Exception e)
        {

            msg = getText("sdp.sce.dp.admin.resources.find.failed");
            Debug.logError(e, e.getMessage(), MODULE);
            return ERROR;
        }
        // 修改结束：

        return "resAdd";
    }

    /**
     * 添加新资源 并根据传入父资源写入关系.
     *
     * @return
     * @throws Exception
     */
    public String addRes() throws Exception
    {
        try
        {
            if (Debug.verboseOn())
            {
                Debug.logVerbose("addRes start...", MODULE);
            }

            User loginUser = (User) request.getSession().getAttribute("user");

            if (loginUser == null)
            {
                Debug.logWarning("addRes loginUser is null");
                msg = getText("sdp.sce.dp.admin.resources.add.user.doesnot.exist");
                return ERROR;
            }

            // add by 906092 对资源名相同的资源进行过滤
            List<Resource> allResource = resService.searchAllResource();

            for (Resource resTemp : allResource)
            {
                if (resTemp == null)
                {
                    continue;
                }
                if (resource == null || resource.getName() == null)
                {
                    Debug.logWarning("resource is null");
                    msg = getText("sdp.sce.dp.admin.resources.add.null");
                    return ERROR;
                }
                if (resource.getEnName().equals(resTemp.getEnName()))
                {
                    Debug.logWarning("The resouce name is already exist");
                    msg = getText("sdp.sce.dp.admin.resources.identifier.exists");
                    return ERROR;
                }
            }
            // end

            resource.setCreatedUser(loginUser.getUserName());
            resource.setCreatedDate(new Date());
            // add 设置修改时间
            resource.setUpdatedDate(new Date());

            resService.addRes(resource, parentId);
            // 日志参数
            List<String> logParamList = initLogParame(loginUser.getUserName(),
                    "sdp.sce.dp.admin.resources",
                    "sdp.sce.dp.admin.resources.add", resource.getName());

            opLoggerService.info(
                    getText("sdp.sce.dp.admin.permission"),
                    getText("sdp.sce.dp.admin.log.permission.add.log",
                            logParamList), getText(Constants.ADD));
        }
        catch (Exception e)
        {
            Debug.logError(e.getMessage());
            Debug.logError(e.getCause());

        }

        return searchRes();
    }

    /**
     * 查询需修改资源信息 转向修改页面.
     *
     * @return
     * @throws Exception
     */
    public String showModify() throws Exception
    {
        if (Debug.verboseOn())
        {
            Debug.logVerbose("showModify start...", MODULE);
        }
        if (id == null)
        {
            msg = getText("sdp.sce.dp.admin.resources.add.null");
            return ERROR;
        }
        resource = resService.findResource(id);

        if (Debug.verboseOn())
        {
            Debug.logVerbose("showModify end...", MODULE);
        }
        return "resModify";
    }

    /**
     * 修改资源信息.
     *
     * @return
     * @throws Exception
     */
    public String modifyRes() throws Exception
    {
        User user = (User) this.request.getSession().getAttribute(
                Constants.LOGIN_SESSION_USER);
        resource.setUpdatedDate(new Date());
        resService.modifyResource(resource, parentId);
        // 日志参数
        List<String> logParamList = initLogParame(user.getUserName(),
                "sdp.sce.dp.admin.resources",
                "sdp.sce.dp.admin.resources.update", resource.getName());

        opLoggerService.info(
                getText("sdp.sce.dp.admin.permission"),
                getText("sdp.sce.dp.admin.log.permission.modify.log",
                        logParamList), getText(Constants.MOD));
        return searchRes();
    }

    /**
     * 删除资源信息.
     *
     * @return
     * @throws Exception
     */
    public String deleteRes() throws Exception
    {
        User user = (User) this.request.getSession().getAttribute(
                Constants.LOGIN_SESSION_USER);
        if (null == idList || "" == idList)
        { // 为空则返回
            Debug.logWarning("deleteRes idList is null ");
            errorMsg = getText("sdp.sce.dp.admin.resources.select.del.data");
        }
        String[] idArray = idList.split("_");

        for (int i = 0; i < idArray.length; i++)
        { // 处理传入的id
            id = idArray[i];
            errorMsg = resService.deleteResource(id);
            if ("SUCCESS" != errorMsg && null != errorMsg && "" != errorMsg)
            {
                // 无法删除则跳出
                break;
            }
        }
        String resNames = "";
        // 保证页面跳转统一
        if (!"SUCCESS".equals(errorMsg))
        {
            msg = errorMsg;
            return ERROR;
        }
        else
        {
            // 进行删除
            try
            {
                resNames = resService.delResources(idList);
                if (resNames == null || "".equals(resNames.trim()))
                {
                    msg = getText("sdp.sce.dp.admin.resources.del.failed");
                    return ERROR;
                }
                id = parentId;
                // 日志参数
                List<String> logParamList = initLogParame(user.getUserName(),
                        "sdp.sce.dp.admin.resources",
                        "sdp.sce.dp.admin.resources.delete", resNames);

                opLoggerService.info(
                        getText("sdp.sce.dp.admin.permission"),
                        getText("sdp.sce.dp.admin.log.permission.delete.log",
                                logParamList), getText(Constants.DEL));
            }
            catch (Exception e)
            {
                Debug.logError(e, e.getMessage(), MODULE);
                msg = getText("sdp.sce.dp.admin.resources.del.failed");
                if (e instanceof DelFailException)
                {
                    msg = getText("sdp.sce.dp.admin.resources.del.failed")
                            + (e.getMessage() == null ? "" : e.getMessage());
                }
                return ERROR;
            }

        }

        return searchRes();
    }

    /**
     * 资源名称相同的验证.
     *
     * @return
     */
    public String isResourceNameSame()
    {
        try
        {
            List<Resource> resList = resService
                    .findByHQL("from Resource r where r.enName = '" + resEnName
                            + "'");
            if (resList != null && resList.size() > 0)
            {
                this.setResult("msg",
                        getText("sdp.sce.dp.admin.resources.identifier.exists"));
            }
            else
            {
                this.setResult("success", true);
            }
        }
        catch (Exception e)
        {
            Debug.logError(e, e.getMessage(), MODULE);
            this.setResult("msg", getText("sdp.sce.dp.admin.query.failed"));
        }

        this.write(JSONObject.fromObject(this.getResult()).toString());
        return null;
    }

    /**
     * resource!searchAllRes.action 查询全部资源列表 返回到页面显示.
     *
     * @return
     * @throws Exception 异常
     */
    public String searchAllRes() throws Exception
    {
        // 查询资源列表
        res = resService.searchResource();
        return "resList";
    }

    public int getMenuButtonFlag()
    {
        return menuButtonFlag;
    }

    public void setMenuButtonFlag(int menuButtonFlag)
    {
        this.menuButtonFlag = menuButtonFlag;
    }

    public ResourceService getResService()
    {
        return resService;
    }

    public void setResService(ResourceService resService)
    {
        this.resService = resService;
    }

    public String getResName()
    {
        return resName;
    }

    public void setResName(String resName)
    {
        this.resName = resName;
    }

    public Resource getResource()
    {
        return resource;
    }

    public void setResource(Resource resource)
    {
        this.resource = resource;
    }

    public Page<Resource> getPage()
    {
        return page;
    }

    public void setPage(Page<Resource> page)
    {
        this.page = page;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getParentId()
    {
        return parentId;
    }

    public void setParentId(String parentId)
    {
        this.parentId = parentId;
    }

    public String getErrorMsg()
    {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg)
    {
        this.errorMsg = errorMsg;
    }

    public List<Resource> getRes()
    {
        return res;
    }

    public void setRes(List<Resource> res)
    {
        this.res = res;
    }

    public String getIdList()
    {
        return idList;
    }

    public void setIdList(String idList)
    {
        this.idList = idList;
    }

    public OpLoggerService getOpLoggerService()
    {
        return opLoggerService;
    }

    public void setOpLoggerService(OpLoggerService opLoggerService)
    {
        this.opLoggerService = opLoggerService;
    }

    public String getResEnName()
    {
        return resEnName;
    }

    public void setResEnName(String resEnName)
    {
        this.resEnName = resEnName;
    }

}
