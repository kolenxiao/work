package com.coship.sdp.sce.dp.exception;

import javax.xml.ws.WebFault;

/**
 *
 *
 * File Name : WSException.java
 * @Description : WebService异常类
 * @author 杜峰 903819
 */
@WebFault(name = "WSException")
public class WSException extends Exception
{

    private static final long serialVersionUID = 1L;

    private WSFault faultInfo;

    public WSException(String s, WSFault faultInfo)
    {
        super(s);
        this.faultInfo = faultInfo;
    }

    public WSFault getFaultInfo()
    {
        return faultInfo;
    }

    public void setFaultInfo(WSFault faultInfo)
    {
        this.faultInfo = faultInfo;
    }

}
