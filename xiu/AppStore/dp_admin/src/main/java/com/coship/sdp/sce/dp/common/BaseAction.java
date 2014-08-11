/*
 * 文 件 名：BaseAction.java
 * 版    权：Shenzhen Coship Electronics Co.,
 * Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<描述>
 * 修 改 人：sunwengang/903820
 * 修改时间：2011-7-25
 * 修改内容：<修改内容>
 */
package com.coship.sdp.sce.dp.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.coship.sdp.sce.dp.dto.AppFilePath;
import com.coship.sdp.utils.Page;
import com.opensymphony.xwork2.ActionSupport;

/**
 *
 * <一句话功能简述>. <功能详细描述>
 * @author sunwengang/903820
 * @version [版本号, 2011-7-25]
 * @since [产品/模块版本]
 */
public class BaseAction extends ActionSupport implements ServletRequestAware,
        ServletResponseAware
{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * action执行结果（true|flase）.
     */
    private boolean success = true;

    /**
     * action执行结果（json插件返回客户端）.
     */
    private Map<Object, Object> result = null;

    /**
     * request对象.
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings(value = "SE", justification = "this is not a bug!")
    protected HttpServletRequest request;

    /**
     * response对象.
     */
    @edu.umd.cs.findbugs.annotations.SuppressWarnings(value = "SE", justification = "this is not a bug!")
    protected HttpServletResponse response;

    /**
     * 起始 页号.
     */
    protected int start = 1;

    /**
     * 每页行数.
     */
    protected int limit = 10;

    /**
     * 前台提示信息.
     */
    private String message = null;

    /**
     * 返回信息.
     */
    public String msg = "";

    /**
     * 记录异常信息.
     */
    public String exception_msg = "";

    /**
     * 初始化日志参数.
     * @param userName 操作用户名
     * @param name 操作名称
     * @param operate 操作动作
     * @param operateModel 操作模块
     * @return List<String> 日志值
     */
    public List<String> initLogParame(String userName, String operateModel,
            String operate, String name)
    {
        // 日志参数
        List<String> logParamList = new ArrayList<String>();
        logParamList.add(userName);
        logParamList.add(this.getText(operateModel));
        logParamList.add(this.getText(operate));
        logParamList.add(this
                .getText("sdp.sce.dp.admin.log.operate.result.success"));
        logParamList.add(name);
        return logParamList;
    }

    /**
     * 设置输出接果.
     *
     * @param key 键
     * @param value 值
     */
    public void setResult(Object key, Object value)
    {
        if (result == null)
        {
            result = new HashMap<Object, Object>();
        }
        result.put(key, value);
    }

    public void setResult(Map<Object, Object> map)
    {
        if (result == null)
        {
            result = new HashMap<Object, Object>();
        }
        this.result = map;
    }

    /**
     *
     * Description : 输出内容.
     *
     * @param value
     *
     */
    public void write(String value)
    {
        try
        {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(value);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public int getStart()
    {
        return start;
    }

    public void setStart(int start)
    {
        this.start = start;
    }

    public int getLimit()
    {
        return limit;
    }

    public void setLimit(int limit)
    {
        this.limit = limit;
    }

    public HttpServletRequest getRequest()
    {
        return request;
    }

    public void setRequest(HttpServletRequest request)
    {
        this.request = request;
    }

    public HttpServletResponse getResponse()
    {
        return response;
    }

    public void setResponse(HttpServletResponse response)
    {
        this.response = response;
    }

    public boolean isSuccess()
    {
        return success;
    }

    public void setSuccess(boolean success)
    {
        this.success = success;
    }

    public Map<Object, Object> getResult()
    {
        return result;
    }

    public void setServletRequest(HttpServletRequest request)
    {
        this.request = request;
    }

    public void setServletResponse(HttpServletResponse response)
    {
        this.response = response;

    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }
    
    /**
     * 获取文件服务路径对象
     * @return
     */
	public AppFilePath getAppFilePath() 
	{
		return AppFilePath.getAppFilePath(request);
	}

	/**
	 * 如果aPage为null则新建一个Page对象，该方法会改变Page对象的两个引用pageSize和currentPage.
	 * @param <T>   分页对象存储类型
	 * @param aPage 分页对象
	 * @param limit 分页跨度
	 * @param currentPage 当前页数
	 */
	public <T> Page<T> setUpPage(Page<T> aPage, int limit, int currentPage) 
	{
		if (null == aPage)
		{
			aPage = new Page<T>();
		}
		aPage.setPageSize(limit);
		aPage.setCurrentPage(currentPage);
		return aPage;
	}

}
