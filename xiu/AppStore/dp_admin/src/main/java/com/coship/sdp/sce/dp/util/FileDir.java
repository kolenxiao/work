package com.coship.sdp.sce.dp.util;

import com.coship.sdp.sce.dp.common.Constants;

/**
 * 上传路径枚举类型
 * @author 907632
 * @since v01.02.06
 */
public enum FileDir
{
	appFile(1, Constants.APP_FILE_SAVE_PATH), logosFile(2, Constants.APP_LOGO_SAVE_PATH),
	imgsFile(3, Constants.APP_IMAGES_SAVE_PATH), postersFile(4, Constants.APP_IMAGES_SAVE_PATH), gameLogosFile(5, Constants.APP_LOGO_SAVE_PATH);
	
	private final int key;
	private final String value;
	

	public String getValue()
	{
		return value;
	}
	
	public int getKey()
	{
		return key;
	}

	FileDir(int key,String value)
	{
		this.key = key;
		this.value = value;
	}
	
    // 普通方法  
    public static String getDir(int key) {  
        for (FileDir dir : FileDir.values()) {  
            if (key == dir.getKey()) {  
                return dir.getValue();  
            }  
        }  
        return null;  
    } 
}
