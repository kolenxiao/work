package com.xiu.uuc.common.exception;

/**
 * 参数异常
 * @ClassName: ParameterException 
 * @author xiaoyingping
 * @date 2011-8-16 上午10:32:13 
 *
 */
public class ParameterException extends RuntimeException{

    public ParameterException(String msg){
        super(msg);
    }

    public ParameterException(String msg, Throwable t){
        super(msg, t);
        setStackTrace(t.getStackTrace());
    }

    private static final long serialVersionUID = 1L;
}