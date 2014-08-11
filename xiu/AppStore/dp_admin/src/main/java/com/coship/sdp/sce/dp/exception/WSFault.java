package com.coship.sdp.sce.dp.exception;

import java.io.Serializable;

/**
 *
 *
 * File Name : WSFault.java
 * @Description : WebService 异常错误信息类
 * @author 杜峰 903819
 */
public class WSFault implements Serializable
{

    private static final long serialVersionUID = 1L;

    // 错误信息
    private String errMsg;

    // 错误代码
    private int errCode;

    public String getErrMsg()
    {
        return errMsg;
    }

    public void setErrMsg(String errMsg)
    {
        this.errMsg = errMsg;
    }

    public int getErrCode()
    {
        return errCode;
    }

    public void setErrCode(int errCode)
    {
        this.errCode = errCode;
    }
}
