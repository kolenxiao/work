/*
 * 文 件 名：BaseAction.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<描述>
 * 修 改 人：wangchenbo/906055
 * 修改时间：2011-9-7
 * 修改内容：<修改内容>
 */
package com.coship.appstore.service.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.coship.sdp.utils.Debug;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * action基类，定义公用的常量和方法.
 * 
 * @author wangchenbo/906055
 * @version V100R001B010, 2012-9-7
 * @since 应用商店/V100R001B010
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
    protected HttpServletRequest request = null;

    /**
     * response对象.
     */
    protected HttpServletResponse response = null;

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
     * 记录异常信息.
     */
    public String msgException = "";

    /**
     * 设置输出接果.
     * @param key 键
     * @param value 值
     */
    public void setResult(Object key, Object value)
    {
        if (null == result)
        {
            result = new HashMap<Object, Object>();
        }
        result.put(key, value);
    }

    public void setResult(Map<Object, Object> map)
    {
        if (null == result)
        {
            result = new HashMap<Object, Object>();
        }
        this.result = map;
    }

    /**
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
            Debug.logError(e, e.getMessage());
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

}
