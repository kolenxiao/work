package com.coship.sdp.sce.dp.utils.thumbnailator;

import java.io.Serializable;

/**
 * 裁剪POJO
 * 
 * @author 907948
 * 
 */
public class ThumbnailOption implements Serializable {
	private static final long serialVersionUID = 6190503725548536495L;

	private static final String resizeSpacer = "x";

	private int width;
	private int height;

	public ThumbnailOption() {
	}

	public ThumbnailOption(int width, int height) {
		this.width = width;
		this.height = height;
	}

	/**
	 * 解析格式.
	 * 
	 * @param resize
	 * @return
	 */
	public static ThumbnailOption parseResize(String resize) {
		if (resize == null) {
			return null;
		}
		String[] size = resize.split(resizeSpacer);

		if (size.length != 2) {
			throw new ThumbnailException("转换尺寸的格式错误!，格式应该为： widthxheight");
		}

		try {
			int width = new Integer(size[0]);
			int height = new Integer(size[1]);
			
			return new ThumbnailOption(width, height);
		} catch (Exception ex) {
			throw new ThumbnailException("尺寸格式");
		}
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
