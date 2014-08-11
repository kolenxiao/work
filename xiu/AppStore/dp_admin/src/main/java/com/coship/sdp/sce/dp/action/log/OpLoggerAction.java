/*
 * 文 件 名：OpLoggerAction.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<描述>
 * 修 改 人：sunwengang/903820
 * 修改时间：2011-8-6
 * 修改内容：<修改内容>
 */
package com.coship.sdp.sce.dp.action.log;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.coship.sdp.common.log.entity.OpLogger;
import com.coship.sdp.common.log.service.OpLoggerService;
import com.coship.sdp.permission.entity.Resource;
import com.coship.sdp.permission.entity.User;
import com.coship.sdp.permission.service.ResourceService;
import com.coship.sdp.sce.dp.common.BaseAction;
import com.coship.sdp.sce.dp.common.Constants;
import com.coship.sdp.utils.Debug;
import com.coship.sdp.utils.Page;

/**
 * 日志操作action.
 * 
 * @author sunwengang/903820
 * @version [版本号, 2011-8-6]
 * @since [产品/模块版本]
 */
public class OpLoggerAction extends BaseAction
{

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * 模块名称.
     */
    private static final String MODULE = OpLoggerAction.class.getName();

    /**
     * 操作日志服务接口.
     */
    private OpLoggerService opLoggerService;

    /**
     * 操作资源接口.
     */
    @Autowired
    private ResourceService resourceService;

    /**
     * 操作日志对象.
     */
    private OpLogger opLogger;

    /**
     * 分页对象.
     */
    private Page<OpLogger> page;

    /**
     * 操作类型集合.
     */
    private List<String> operationTypes;

    /**
     * 主菜单资源集合.
     */
    private List<Resource> mainMenuList;

    /**
     * 列表查询.
     * 
     * @return
     */
    public String doList()
    {
        try
        {
            if (this.opLogger == null)
            {
                this.opLogger = new OpLogger();
            }
            this.page = new Page<OpLogger>();
            page.setPageSize(this.limit);
            page.setCurrentPage(this.start);
            mainMenuList = resourceService.findMainMemu();
            Resource login = new Resource();
            login.setName("登录信息");
            mainMenuList.add(login);

            // 需将主菜单中加入一个其它的选项
            Resource other = new Resource();
            other.setName(getText("sdp.sce.dp.admin.global.other"));
            mainMenuList.add(other);

            operationTypes = new ArrayList<String>();

            for (String operationTypeTemp : new String[]
            { getText("sdp.sce.dp.admin.global.btn.add"),
                    getText("sdp.sce.dp.admin.global.btn.del"),
                    getText("sdp.sce.dp.admin.global.btn.modify"),
                    getText("sdp.sce.dp.admin.global.btn.audit"),
                    getText("sdp.sce.dp.admin.global.other"),
                    getText("sdp.sce.dp.admin.global.upload"),
                    getText("sdp.sce.dp.admin.log.shelves.operate"),
                    getText("sdp.sce.dp.admin.log.downline.operate"),
                    getText("sdp.sce.dp.admin.log.recommended.operate"),
                    getText("sdp.sce.dp.admin.log.cancel.recommended"),
                    getText("sdp.sce.dp.admin.log.reset.password"),
                    getText("sdp.sce.dp.admin.global.login") })
            {
                operationTypes.add(operationTypeTemp);
            }

            // 条件查询
            this.page = this.opLoggerService.listOpLogger(page, this.opLogger);
        }
        catch (RuntimeException re)
        {
            exception_msg = this.getText("sdp.sce.dp.admin.common.query.error");
            Debug.logError(re, re.getMessage(), MODULE);
            return ERROR;
        }
        catch (Exception e)
        {
            Debug.logError(e, e.getMessage(), MODULE);
            exception_msg = this.getText("sdp.sce.dp.admin.common.query.error");
            return ERROR;
        }
        return SUCCESS;
    }

    /**
     * 导出日志.
     * 
     * @return 返回页面
     */
    public String exportLog()
    {
        // 输出流
        OutputStream os = null;
        try
        {
            // 获得开始和结束时间
            String startTime = opLogger.getOperStartDate();
            String endTime = opLogger.getOperEndDate();
            // 获得输出流
            os = response.getOutputStream();
            // 日期格式
            SimpleDateFormat ymdSdf = new SimpleDateFormat("yyyy-MM-dd");
            // 获得当前时间
            Calendar now = Calendar.getInstance();
            // 如果开始时间为空开始时间设为前3个月的
            if (StringUtils.isEmpty(startTime))
            {
                now.add(Calendar.MONTH, 0 - 3);
                Date sTime = now.getTime();
                opLogger.setOperStartDate(ymdSdf.format(sTime));
            }
            // 如果结束时间为空则结束时间设为当前时间
            if (StringUtils.isEmpty(endTime))
            {
                opLogger.setOperEndDate(ymdSdf.format(new Date()));
            }
            // 设置响应类型和头域
            response.setContentType("octets/stream");
            response.addHeader("Content-Disposition",
                    "attachment;filename=log.csv");
            opLoggerService.exportLogForCsv(opLogger, os);
            os.flush();
            User user = (User) request.getSession().getAttribute("user");

            opLoggerService.info(getText("sdp.sce.dp.admin.system"),
                    getText("sdp.sce.dp.admin.log.export.success", new String[]
                    { user.getUserName() }), getText(Constants.OTHER));
        }
        catch (Exception e)
        {
            Debug.logError(e, e.getMessage(), MODULE);
        }
        finally
        {
            if (os != null)
            {
                try
                {
                    os.close();
                }
                catch (IOException e)
                {
                    Debug.logError(e, e.getMessage(), MODULE);
                }
            }
        }

        return null;
    }

    public OpLoggerService getOpLoggerService()
    {
        return opLoggerService;
    }

    public void setOpLoggerService(OpLoggerService opLoggerService)
    {
        this.opLoggerService = opLoggerService;
    }

    public OpLogger getOpLogger()
    {
        return opLogger;
    }

    public void setOpLogger(OpLogger opLogger)
    {
        this.opLogger = opLogger;
    }

    public Page<OpLogger> getPage()
    {
        return page;
    }

    public void setPage(Page<OpLogger> page)
    {
        this.page = page;
    }

    public ResourceService getResourceService()
    {
        return resourceService;
    }

    public void setResourceService(ResourceService resourceService)
    {
        this.resourceService = resourceService;
    }

    public List<String> getOperationTypes()
    {
        return operationTypes;
    }

    public void setOperationTypes(List<String> operationTypes)
    {
        this.operationTypes = operationTypes;
    }

    public List<Resource> getMainMenuList()
    {
        return mainMenuList;
    }

    public void setMainMenuList(List<Resource> mainMenuList)
    {
        this.mainMenuList = mainMenuList;
    }

}
