package com.coship.sdp.sce.dp.common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.sf.json.JSONObject;

import com.coship.sdp.sce.dp.util.AsynMsg;
import com.coship.sdp.sce.dp.util.CommonUtils;
import com.coship.sdp.sce.dp.util.FileDir;
import com.coship.sdp.sce.dp.utils.FileUtil;
import com.coship.sdp.utils.Debug;

/**
 * 单个文件上传工具类
 * 
 * @author 907632
 * @since v01.02.06
 */
public class UploadAction extends BaseAction
{
	private static final long serialVersionUID = 7815760956796535103L;
	private String MODULE = this.getClass().getName();
	
	//上传的文件
	private File file;
	
	//文件类型 如apk 、img
	private String fileType;
	
	//标记 1 代表apk
	private int flag;
	
	//图片的尺寸
	private int pictrueWidth;
	private int pictrueHeight;
	
	private final static int APK = 1;
	
	public String doUpload()
	{	
		//异步消息对象
		AsynMsg aAsynMsg = null;
		
		//状态码
		int statusCode = 1;
		String fileSaveName = "";
		
		//空指针校验
		if(null != file){
			try {
				if(!isPictureSizeTrue(flag, file)){
					statusCode = 2;
					msg = "请严格按照规定的尺寸上传图片, 正确的尺寸为: " + pictrueWidth + " * " + pictrueHeight;
				}else{
					fileSaveName = CommonUtils.getUniqueKey()+fileType;
					FileUtil.uploadFile(file, FileDir.getDir(flag), fileSaveName);
					statusCode = 0;
				}
			} catch (FileNotFoundException e) {
				Debug.logError(e, e.getMessage(), MODULE);
			} catch (IOException e) {
				Debug.logError(e, e.getMessage(), MODULE);
			}
		}

		//实例化对象
		aAsynMsg = new AsynMsg(statusCode,msg,fileSaveName);
	
		//输出JSON消息到客户端
		write(JSONObject.fromObject(aAsynMsg).toString());
		
		return null;
	}
	
	private boolean isPictureSizeTrue(int pictrueType, File pictrueFile) throws FileNotFoundException, IOException {
		boolean result = true;
//		if (pictrueType != APK && pictrueWidth > 0 && pictrueHeight > 0) {
//			BufferedImage image = null;
//			image = ImageIO.read(new FileInputStream(pictrueFile));
//			int width = image.getWidth();
//			int height = image.getHeight();
//			if (pictrueWidth != width || pictrueHeight != height) {
//				result = false;
//			}
//		}
		return result;
	}

	
	public File getFile()
	{
		return file;
	}

	public void setFile(File file)
	{
		this.file = file;
	}

	public String getFileType()
	{
		return fileType;
	}

	public void setFileType(String fileType)
	{
		this.fileType = fileType;
	}

	public int getFlag()
	{
		return flag;
	}

	public void setFlag(int flag)
	{
		this.flag = flag;
	}

	public int getPictrueWidth() {
		return pictrueWidth;
	}

	public void setPictrueWidth(int pictrueWidth) {
		this.pictrueWidth = pictrueWidth;
	}

	public int getPictrueHeight() {
		return pictrueHeight;
	}

	public void setPictrueHeight(int pictrueHeight) {
		this.pictrueHeight = pictrueHeight;
	}

}
