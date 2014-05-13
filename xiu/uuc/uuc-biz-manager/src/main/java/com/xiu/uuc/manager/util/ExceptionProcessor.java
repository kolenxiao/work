package com.xiu.uuc.manager.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiu.uuc.common.exception.BusinessException;
import com.xiu.uuc.common.exception.ParameterException;
import com.xiu.uuc.common.util.ExceptionEnum;
import com.xiu.uuc.facade.dto.Result;
import com.xiu.uuc.facade.util.FacadeConstant;
import com.xiu.uuc.facade.util.ResultKeys;

public class ExceptionProcessor {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionProcessor.class);

    /**
     * 参数异常处理
     * 
     * @Title: getParameterExceptionResult
     * @param
     * @return Result 返回类型
     * @throws
     */
    public static Result getParameterExceptionResult(ParameterException e) {
        Result result = new Result();
        result.setSuccess(FacadeConstant.FALSE);
        result.setErrorCode(ResultKeys.ERROE_CODE_PARAMETER);
        result.setErrorMessage(e.getMessage());

        logger.error("ParameterException: errorCode={}, errorMessage={}",
                new Object[] { result.getErrorCode(), result.getErrorMessage() });
        return result;
    }

    /**
     * 业务异常处理
     * 
     * @Title: getBusinessExceptionResult
     * @param
     * @return Result 返回类型
     * @throws
     */
    public static Result getBusinessExceptionResult(BusinessException e) {
        String code = e.getCode();
        String msg = e.getMessage();

        Result result = new Result();
        result.setSuccess(FacadeConstant.FALSE);
        if (StringUtils.isNotBlank(code)) {
            result.setErrorCode(code);
            if (StringUtils.isNotBlank(msg)) {
                result.setErrorMessage(msg);
            } else {
                result.setErrorMessage(ExceptionEnum.Busi.getList().get(code));
            }
        } else {
            result.setErrorCode(ResultKeys.ERROE_CODE_BUSINESS);
            result.setErrorMessage(msg);
        }

        logger.info("BusinessException: errorCode={}, errorMessage={}",
                new Object[] { result.getErrorCode(), result.getErrorMessage() });
        return result;
    }

    /**
     * 系统异常处理
     * 
     * @Title: getExceptionResult
     * @param
     * @return Result 返回类型
     * @throws
     */
    public static Result getExceptionResult(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));

        Result result = new Result();
        result.setSuccess(FacadeConstant.FALSE);
        result.setErrorCode(ResultKeys.ERROE_CODE_SYSTEM);
        result.setErrorMessage("系统发生异常，请联系客服人员!");

        logger.error("Exception: errorCode={}, errorMessage={}",
                new Object[] { result.getErrorCode(), sw.toString() });
        return result;
    }

}
