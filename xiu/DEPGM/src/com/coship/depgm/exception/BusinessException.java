package com.coship.depgm.exception;

/**
 * 业务异常
 * @author 908618
 *
 */
public class BusinessException extends RuntimeException{

	/**
	 * @Fields serialVersionUID
	 */
	
	private static final long serialVersionUID = -5290798039864539530L;
	private String code;
	
    public BusinessException(String msg){
        super(msg);
    }
    
    public BusinessException(String code, String msg){
        super(msg);
        this.code = code;
    }
    
    public BusinessException(String msg, Throwable t){
        super(msg, t);
        setStackTrace(t.getStackTrace());
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
    
    
}