/*
 * 文件名称：AppSubjectAction.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-9-7
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.action.subject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.coship.sdp.common.log.service.OpLoggerService;
import com.coship.sdp.permission.entity.User;
import com.coship.sdp.sce.dp.ap.entity.DpAppInfo;
import com.coship.sdp.sce.dp.common.BaseAction;
import com.coship.sdp.sce.dp.common.Constants;
import com.coship.sdp.sce.dp.common.QueryAppInfoThread;
import com.coship.sdp.sce.dp.subject.entity.AppSubjectType;
import com.coship.sdp.sce.dp.subject.service.AppSubjectTypeService;
import com.coship.sdp.sce.dp.subject.service.SubjectAppinfoRelationService;
import com.coship.sdp.sce.dp.utils.FileUtil;
import com.coship.sdp.utils.Debug;
import com.coship.sdp.utils.Page;

/**
 * 专题应用管理action
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-9-7]
 * @since [产品/模块版本]
 */
public class AppSubjectAction extends BaseAction
{
    /**
     * 模块的名称.
     */
    private static final String MODULE_NAME = AppSubjectAction.class.getName();

    /**
     * 封装查询条件的应用专题对象
     */
    private AppSubjectType queryAppSubjectType;

    /**
     * 应用查询数据封装对象
     */
    private DpAppInfo queryAppInfo;

    /**
     * 应用专题对象
     */
    private AppSubjectType appSubjectType;

    /**
     * 应用专题列表对象
     */
    private Page<AppSubjectType> page;

    /**
     * 专题下的应用分类
     */
    private List<DpAppInfo> appInfoList;

    /**
     * 附件.
     */
    private File attachment;

    /**
     * 附件文件名称.
     */
    private String attachmentFileName;

    /**
     * 附件类型.
     */
    private String attachmentContentType;

    /**
     * 编辑操作类型
     */
    private final static String EIDIT_OPERATE_TYPE = "addApp";

    /**
     * 可见标志
     */
    private final static int VISIBLE = 1;

    /**
     * 编辑操作标识
     */
    private String operate;

    /**
     * id集合.
     */
    private String ids;

    /**
     * 应用专题服务类
     */
    @Autowired
    private AppSubjectTypeService appSubjectTypeService;

    /**
     * 应用专题服务类
     */
    @Autowired
    private SubjectAppinfoRelationService subjectAppinfoRelationService;

    /**
     * 操作日志对象.
     */
    @Autowired
    private OpLoggerService opLoggerService;

    /**
     * 序列号
     */
    private static final long serialVersionUID = -4033184156474183804L;

    /**
     * 查询应用专题列表列表
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public String doSearchAppSubjectTypeList()
    {
        Debug.logVerbose("AppSubjectAction.doSearchAppSubjectTypeList() start...");

        // 查询条件数据封装对象
        if (queryAppSubjectType == null)
        {
            queryAppSubjectType = new AppSubjectType();
        }

        page = new Page<AppSubjectType>();
        page.setPageSize(limit);
        page.setCurrentPage(start);
        try
        {
            // 获取应用专题分页列表数据
            appSubjectTypeService.searchAppSubjectType(page,
                    queryAppSubjectType);

            // 获取专题分类下的个数
            if (CollectionUtils.isNotEmpty(page.getResultList()))
            {
                for (AppSubjectType type : page.getResultList())
                {
                    int count = appSubjectTypeService.getAppTotalBySubjectTypeId(type.getId());
                    type.setAppTotal(count);
                }
            }

        }
        catch (Exception e)
        {
            Debug.logError(e, e.getMessage(), MODULE_NAME);
            exception_msg = getText("sdp.sce.dp.admin.common.query.error");
            return ERROR;
        }
        Debug.logVerbose("AppSubjectAction.doSearchAppSubjectTypeList() end...");
        return "appSubjectTypeList";
    }

    /**
     * 查看应用专题信息和专题下的应用分页列表
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public String doDisplay()
    {
        try
        {
            // 专题
            if (StringUtils.isNotEmpty(appSubjectType.getId()))
            {
                appSubjectType = appSubjectTypeService
                        .getAppSubjectTypeById(appSubjectType.getId());

                if (page == null)
                {
                    page = new Page<AppSubjectType>();
                    page.setPageSize(limit);
                    page.setCurrentPage(start);
                }
                appInfoList = appSubjectTypeService
                        .queryAppInfoPageBySubjectType(page, appSubjectType);
            }
            return "doDisplayDetail";
        }
        catch (Exception e)
        {
            exception_msg = "查看应用专题出错。";
            Debug.logError(e, e.getMessage(), MODULE_NAME);
            return ERROR;
        }
    }

    /**
     * 更改应用专题的显示状态
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public String doChangeVisibleStateType()
    {
    	if (Debug.verboseOn())
        {
            Debug.logVerbose("AppSubjectAction.doChangeVisibleStateType start...", MODULE_NAME);
        }
    	try {
    		AppSubjectType subType = appSubjectTypeService
    			.getAppSubjectTypeById(appSubjectType.getId());

    		subType.setVisibleFlag(appSubjectType.getVisibleFlag());

    		appSubjectTypeService.saveAppSubjectType(subType);

			setResult("success", "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			setResult("exception", "更改专题显示状态失败.");
            write(JSONObject.fromObject(getResult()).toString());
            Debug.logError(e, e.getMessage(), MODULE_NAME);
            exception_msg = "更改专题显示状态失败.";
		}
		new QueryAppInfoThread().start();
		write(JSONObject.fromObject(getResult()).toString());
        Debug.logVerbose("AppSubjectAction.doChangeVisibleStateType end...");
		return null;

    }

    /**
     * 跳转到应用专题信息编辑页面(新增/修改)
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public String toEditSubject()
    {
        Debug.logVerbose("AppSubjectAction.toEditSubject() start...");

        try
        {
            // 请求为修改专题
            if (appSubjectType != null
                    && StringUtils.isNotEmpty(appSubjectType.getId()))
            {
                // 获取修改的专题以及专题下的应用
                appSubjectType = appSubjectTypeService
                        .getAppSubjectTypeById(appSubjectType.getId());

                // 设置分页数据
                if (page == null)
                {
                    page = new Page<AppSubjectType>();
                    page.setPageSize(limit);
                    page.setCurrentPage(start);
                }
                appInfoList = appSubjectTypeService
                        .queryAppInfoPageBySubjectType(page, appSubjectType);

                Debug.logVerbose("AppSubjectAction.toEditSubject() end...");
            }
            new QueryAppInfoThread().start();
            return "toEdit";
        }
        catch (Exception e)
        {
            exception_msg = "跳转编辑页面出错。";
            Debug.logError(e, e.getMessage(), MODULE_NAME);
            return ERROR;
        }

    }

    /**
     * 保存编辑应用专题
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public String doSaveEidt()
    {
        User userSession = (User) request.getSession().getAttribute("user");
        if (StringUtils.isEmpty(appSubjectType.getId()))
        {
            appSubjectType.setCreateUser(userSession.getUserName());
            appSubjectType.setCreateDate(new Date());
        }
        appSubjectType.setUpdateDate(new Date());
        appSubjectType.setVisibleFlag(VISIBLE);

        try
        {
            if (StringUtils.isNotEmpty(appSubjectType.getId()))
            {
            	AppSubjectType subType = appSubjectTypeService.getAppSubjectTypeById(
                        appSubjectType.getId());
                String oldImg = subType.getSubjectImg();

                appSubjectType.setVisibleFlag(subType.getVisibleFlag());
                appSubjectType.setSubjectImg(oldImg);
            }
            // 处理上传的专题图片
            processSaveSubjectImg();

            appSubjectTypeService.saveAppSubjectType(appSubjectType);
            List<String> deployLogParam = initLogParame(
                    userSession.getUserName(), "sdp.sce.dp.admin.ap.subject",
                    "sdp.sce.dp.admin.ap.subject.saveedit",
                    appSubjectType.getSubjectName());
            opLoggerService.info(
                    this.getText("sdp.sce.dp.admin.ap.subject"),
                    getText("sdp.sce.dp.admin.log.app.subject.log",
                            deployLogParam), this
                            .getText("sdp.sce.dp.admin.ap.subject.saveedit"));
            
            new QueryAppInfoThread().start();
            return doSearchAppSubjectTypeList();
        }
        catch (Exception e)
        {
            exception_msg = "保存应用专题出错。";
            Debug.logError(e, e.getMessage(), MODULE_NAME);
            return ERROR;
        }
    }

    /**
     * 跳转到新增专题应用列表页面
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
//    public String toAddAppToSubjectType()
//    {
//        try
//        {
//            if (StringUtils.isNotEmpty(appSubjectType.getId()))
//            {
//
//                appSubjectType = appSubjectTypeService
//                        .getAppSubjectTypeById(appSubjectType.getId());
//
//                if (page == null)
//                {
//                    page = new Page<AppSubjectType>();
//                    page.setPageSize(limit);
//                    page.setCurrentPage(start);
//                }
//
//                // 获取未被该专题关联的上架应用
//                appInfoList = appSubjectTypeService
//                        .queryAppInfoPageBySubjectTypeNoRel(page,
//                                appSubjectType, queryAppInfo);
//                page.setTotalCount(appSubjectTypeService
//                        .queryAppInfoPageBySubjectTypeNoRelCount(
//                                appSubjectType, queryAppInfo));
//            }
//            return "toAddAppToSubject";
//        }
//        catch (Exception e)
//        {
//            exception_msg = "跳转编辑页面出错。";
//            Debug.logError(e, e.getMessage(), MODULE_NAME);
//            return ERROR;
//        }
//    }

    /**
     * 向专题上新增应用
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public String doAddAppToSubjectType()
    {
        Debug.logVerbose("AppSubjectAction.doAddAppToSubjectType() start...");
        if (ids == null)
        {
            Debug.logWarning("ids is null", MODULE_NAME);
            return doSearchAppSubjectTypeList();
        }
        String[] idsStr = ids.split(",");
        try
        {
            // 向主题中添加应用
            subjectAppinfoRelationService.addAppToSubjectType(appSubjectType, idsStr);

			// 更新appstore_service存放在内存的应用信息
			new QueryAppInfoThread().start();
        }
        catch (Exception e)
        {
            setResult("exception", "向专题中添加应用失败.");
            write(JSONObject.fromObject(getResult()).toString());
            Debug.logError(e, e.getMessage(), MODULE_NAME);
            exception_msg = "向专题中添加应用失败.";
        }
        setResult("success", true);
        write(JSONObject.fromObject(getResult()).toString());
        Debug.logVerbose("AppSubjectAction.doAddAppToSubjectType() end...");
        return null;
    }

    /**
     * 移除专题上关联的应用
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public String doRemoveAppToSubjectType()
    {
        Debug.logVerbose("AppSubjectAction.doRemoveAppToSubjectType() start...");
        if (ids == null)
        {
            Debug.logWarning("ids is null", MODULE_NAME);
            return doSearchAppSubjectTypeList();
        }
        String[] idsStr = ids.split(","); // 获取要删除的id
        try
        {
            subjectAppinfoRelationService.deleteAppSubjectRelationByIds(appSubjectType,
                    idsStr);

			// 更新appstore_service存放在内存的应用信息
			new QueryAppInfoThread().start();
        }
        catch (Exception e)
        {
            setResult("exception", "从专题中移除应用失败.");
            write(JSONObject.fromObject(getResult()).toString());
            Debug.logError(e, e.getMessage(), MODULE_NAME);
            exception_msg = "从专题中移除应用失败.";
        }
        setResult("success", true);
        write(JSONObject.fromObject(getResult()).toString());
        Debug.logVerbose("AppSubjectAction.doRemoveAppToSubjectType() end...");
        return null;
    }

    /**
     * 删除应用主题
     * @return [参数说明]
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public String deleteSubject()
    {
        Debug.logVerbose("AppSubjectAction.deleteSubject() start...");
        User userSession = (User) request.getSession().getAttribute("user");
        if (ids == null)
        {
            Debug.logError("ids is null", MODULE_NAME);
            return doSearchAppSubjectTypeList();
        }
        String[] idsStr = ids.split(","); // 获取要删除的id
        try
        {
            // 获取专题图片
            List<String> images = new ArrayList<String>();
            for (String id : idsStr)
            {
                id = StringUtils.trim(id);
                images.add(appSubjectTypeService.getAppSubjectTypeById(id)
                        .getSubjectImg());

            }
            // 调用服务接口删除专题信息
            appSubjectTypeService.deleteAppSubjectTypeByIds(idsStr);

            // 删除专题图片
            deleteSubjectImg(images);
            List<String> deployLogParam = initLogParame(
                    userSession.getUserName(), "sdp.sce.dp.admin.ap.subject",
                    "sdp.sce.dp.admin.ap.subject.delete", ids);

            opLoggerService.info(
                    this.getText("sdp.sce.dp.admin.ap.subject"),
                    getText("sdp.sce.dp.admin.log.app.subject.log",
                            deployLogParam), this
                            .getText("sdp.sce.dp.admin.ap.subject.delete"));
        }
        catch (Exception e)
        {
            setResult("exception",
                    getText("sdp.sce.dp.admin.common.data.delete.exception"));
            write(JSONObject.fromObject(getResult()).toString());
            Debug.logError(e, e.getMessage(), MODULE_NAME);
            exception_msg = getText("sdp.sce.dp.admin.common.data.delete.exception");
        }
        setResult("success", true);
        write(JSONObject.fromObject(getResult()).toString());
        Debug.logVerbose("AppSubjectAction.deleteSubject() end...");
        return null;
    }

    /**
     * 主题图片.
     */
    private void processSaveSubjectImg()
    {
        String fileName = null;
        try
        {
            if (attachmentFileName != null)
            {
                fileName = FileUtil.resetFileName(this.attachmentFileName);

                FileUtil.uploadFile(this.attachment,
                        Constants.APP_IMAGES_SAVE_PATH, fileName);
                appSubjectType.setSubjectImg(fileName);
            }
        }
        catch (FileNotFoundException e)
        {
            Debug.logError(e, e.getMessage(), MODULE_NAME);
        }
        catch (IOException e)
        {
            Debug.logError(e, e.getMessage(), MODULE_NAME);
        }
    }

    /**
     * 删除专题图片
     * @param images
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    private void deleteSubjectImg(List<String> images)
    {
        for (String subjectImg : images)
        {
            FileUtil.deleteFile(Constants.APP_IMAGES_MAPPE_PATH + "/"
                    + subjectImg);
        }
    }
    
    /**
     * 查询显示的
     * @return
     */
    public String doSearchAppSubjectTypeListByItem()
    {
        Debug.logVerbose("AppSubjectAction.doSearchAppSubjectTypeList() start...");

        // 查询条件数据封装对象
        if (queryAppSubjectType == null)
        {
            queryAppSubjectType = new AppSubjectType();
        }

        page = new Page<AppSubjectType>();
        page.setPageSize(limit);
        page.setCurrentPage(start);
        try
        {
            queryAppSubjectType.setVisibleFlag(1);
            // 获取应用专题分页列表数据
            appSubjectTypeService.searchAppSubjectType(page,
                    queryAppSubjectType);

            // 获取专题分类下的个数
            if (CollectionUtils.isNotEmpty(page.getResultList()))
            {
                for (AppSubjectType type : page.getResultList())
                {
                    int count = appSubjectTypeService.getAppTotalBySubjectTypeId(type.getId());
                    type.setAppTotal(count);
                }
            }

        }
        catch (Exception e)
        {
            Debug.logError(e, e.getMessage(), MODULE_NAME);
            exception_msg = getText("sdp.sce.dp.admin.common.query.error");
            return ERROR;
        }
        Debug.logVerbose("AppSubjectAction.doSearchAppSubjectTypeList() end...");
        return "doSearchAppSubjectTypeListByItem";
    }
    
    public AppSubjectType getQueryAppSubjectType()
    {
        return queryAppSubjectType;
    }

    public void setQueryAppSubjectType(AppSubjectType queryAppSubjectType)
    {
        this.queryAppSubjectType = queryAppSubjectType;
    }

    public AppSubjectType getAppSubjectType()
    {
        return appSubjectType;
    }

    public void setAppSubjectType(AppSubjectType appSubjectType)
    {
        this.appSubjectType = appSubjectType;
    }

    public Page<AppSubjectType> getPage()
    {
        return page;
    }

    public void setPage(Page<AppSubjectType> page)
    {
        this.page = page;
    }

    public List<DpAppInfo> getAppInfoList()
    {
        return appInfoList;
    }

    public void setAppInfoList(List<DpAppInfo> appInfoList)
    {
        this.appInfoList = appInfoList;
    }

    public String getIds()
    {
        return ids;
    }

    public void setIds(String ids)
    {
        this.ids = ids;
    }

    public DpAppInfo getQueryAppInfo()
    {
        return queryAppInfo;
    }

    public void setQueryAppInfo(DpAppInfo queryAppInfo)
    {
        this.queryAppInfo = queryAppInfo;
    }

    public File getAttachment()
    {
        return attachment;
    }

    public void setAttachment(File attachment)
    {
        this.attachment = attachment;
    }

    public String getAttachmentFileName()
    {
        return attachmentFileName;
    }

    public void setAttachmentFileName(String attachmentFileName)
    {
        this.attachmentFileName = attachmentFileName;
    }

    public String getAttachmentContentType()
    {
        return attachmentContentType;
    }

    public void setAttachmentContentType(String attachmentContentType)
    {
        this.attachmentContentType = attachmentContentType;
    }

    public String getOperate()
    {
        return operate;
    }

    public void setOperate(String operate)
    {
        this.operate = operate;
    }

}
