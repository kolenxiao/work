package com.xiu.uuc.web.action.base;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.xiu.uuc.web.util.BeanLocator;

public class BaseAction extends ActionSupport implements ServletRequestAware, ServletResponseAware{

    
    /**
     * @SuppressWarnings("unchecked")
     */
	public BaseAction(){
        success = true;
        result = null;
        errorReason = new HashMap();
        start = 0;
        totalCount = 0;
    }

    public final Object getBean(String key){
        return BeanLocator.getInstance().getBean(key);
    }

    public Map getErrorReason(){
        return errorReason;
    }

    public void setErrorReason(Map errorReason){
        this.errorReason = errorReason;
    }

    public void setErrorReason(String errorMsg){
        if(errorReason == null)
            errorReason = new HashMap();
        setSuccess(false);
        errorReason.put("msg", errorMsg);
        errorReason.put("errorStack", "");
    }

    public void setErrorReason(String errorMsg, Exception e){
        if(errorReason == null)
            errorReason = new HashMap();
        setSuccess(false);
        errorReason.put("msg", errorMsg);
        errorReason.put("errorStack", generateStackTrace(e));
    }

    public void setAlertMsg(String alertMsg){
        setResult("msg", alertMsg);
    }

    private String generateStackTrace(Exception e){
        StringBuffer stringBuffer;
        ByteArrayOutputStream byteArrayOutputStream;
        if(e == null)
            return null;
        stringBuffer = new StringBuffer();
        byteArrayOutputStream = null;
        try{
            byteArrayOutputStream = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(byteArrayOutputStream));
            stringBuffer.append(byteArrayOutputStream.toString());
        }
        catch(Exception exception){
            if(byteArrayOutputStream != null)
                try{
                    byteArrayOutputStream.close();
                }
                catch(IOException ioexception) { 
                	
                }
        }
        if(byteArrayOutputStream != null){
            try{
                byteArrayOutputStream.close();
            } catch(IOException ioexception1) {
            	//
            }
        }
        if(byteArrayOutputStream != null){
            try{
                byteArrayOutputStream.close();
            }catch(IOException ioexception2) {
            	//
            }
        }
        return stringBuffer.toString();
    }

    public int getStart(){
        return start;
    }

    public void setStart(int start){
        this.start = start;
    }

    public int getTotalCount(){
        return totalCount;
    }

    public void setTotalCount(int totalCount){
        this.totalCount = totalCount;
    }

    public String getSort(){
        return sort;
    }

    public void setSort(String sort){
        this.sort = sort;
    }

    public String getDir(){
        return dir;
    }

    public void setDir(String dir){
        this.dir = dir;
    }

    public boolean isSuccess(){
        return success;
    }

    public void setSuccess(boolean success){
        this.success = success;
    }

    public Map getResult(){
        return result;
    }

    public void setResult(Map result){
        this.result = result;
    }

    public void setResult(Object key, Object value){
        if(result == null)
            result = new HashMap();
        result.put(key, value);
    }
    
    @Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

    private static final long serialVersionUID = 0x95a907690ca202bL;
    @SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(BaseAction.class);
    private static final String ERROR_MSG = "msg";
    private static final String ERROR_STACK = "errorStack";
    private boolean success;
    private Map result;
    private Map errorReason;
    private int start;
    private int totalCount;
    private String sort;
    private String dir;
    protected HttpServletRequest request;
	protected HttpServletResponse response;
}