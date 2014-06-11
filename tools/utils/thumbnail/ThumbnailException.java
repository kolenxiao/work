package com.coship.sdp.sce.dp.utils.thumbnailator;

/**
 * 图片裁剪缩放异常.
 * 
 * @author 907948
 *
 */
public class ThumbnailException extends RuntimeException {
	private static final long serialVersionUID = -2313524053405178454L;
	
	public ThumbnailException() {
		super();
	}
	
	public ThumbnailException(String msg){
		super(msg);
	}
	
	public ThumbnailException(String msg, Exception e) {
		super(msg, e);
	}
	
	public ThumbnailException(String msg, Throwable t) {
		super(msg,t);
	}
}
