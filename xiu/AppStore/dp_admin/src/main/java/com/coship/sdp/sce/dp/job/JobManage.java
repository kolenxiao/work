package com.coship.sdp.sce.dp.job;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coship.sdp.sce.dp.ap.entity.AppThumbnail;
import com.coship.sdp.sce.dp.ap.service.AppThumbnailService;
import com.coship.sdp.sce.dp.atachmentfile.entity.AttachmentFile;
import com.coship.sdp.sce.dp.atachmentfile.service.AttachmentFileService;
import com.coship.sdp.sce.dp.common.Constants;
import com.coship.sdp.sce.dp.system.entity.SystemParam;
import com.coship.sdp.sce.dp.system.service.SystemParamService;
import com.coship.sdp.sce.dp.utils.thumbnailator.ThumbnailTool;

@Service("jobManage")
public class JobManage {
	
	private Logger logger = LoggerFactory.getLogger(JobManage.class);
	
	@Autowired
	private SystemParamService systemParamService;
	
	@Autowired
	private AttachmentFileService attachmentFileService;
	
	@Autowired		
	private AppThumbnailService appThumbnailService;

	/**
	 * 生成缩略图Job
	 * @throws Exception
	 */
	public void appThumbnailJob() throws Exception {
		long t1 = System.currentTimeMillis();
		logger.info("appThumbnail job star------------");
		
		try {
			//处理应用截图
			this.judgeThumbnail(Constants.IMG);
			
			//处理应用截图logo
			//this.judgeThumbnail(Constants.LOGO);
		} catch (Exception e) {
			logger.error("appThumbnail job fail", e);
		}
		
		long t2 = System.currentTimeMillis();
		logger.info("appThumbnail job success, costTime={}ms...", t2 - t1);
		
	}
	
	
	
	private void judgeThumbnail(String srcType){
		//获取缩略尺寸
		SystemParam dimensionParam = systemParamService.getByCode("picsize_" + srcType);
		if(null == dimensionParam){
			return;
		}
		String[] dimensionArr = StringUtils.split(dimensionParam.getValue(), ";");
		
		
		//获取源图Set
		List<AttachmentFile> attachAllList = attachmentFileService.findAttachmentByAppIdFileDesc(null, srcType);
		List<AttachmentFile> attachList = new ArrayList<AttachmentFile>();
		HashSet<String> srcImgSet = new HashSet<String>();
		//去重
		for (AttachmentFile e : attachAllList) {
			if(!srcImgSet.contains(e.getFileSaveName())){
				srcImgSet.add(e.getFileSaveName());
				attachList.add(e);
			}
		}
		
		//获取已缩略图List
		Map<String,List<AppThumbnail>> thumMap = appThumbnailService.searchByType(srcType);
		
		//判断是否存在缩略图，如果不存在则生成缩略
		List<AppThumbnail> thumist = new ArrayList<AppThumbnail>();
		for (AttachmentFile attach : attachList) {
			String srcImg = attach.getFileSaveName();
			
			List<AppThumbnail> thumList = thumMap.get(srcImg);
			for (int i = 0; i < dimensionArr.length; i++){
				String dimension = StringUtils.trim(dimensionArr[i]);
				if(null != thumList){
					Boolean isThum = Boolean.FALSE;
					
					AppThumbnail appThumbnail = null;
					for (AppThumbnail thum : thumList) {
						if (thum.getDimension().equals(dimension)) {
							if (thum.getStatus().equals(1)) {
								isThum = Boolean.TRUE;
							}else if(thum.getStatus().equals(0)){
								if(thum.getFailCount() >= 5){
									isThum = Boolean.TRUE;
								}else{
									appThumbnail = thum;
								}
							}
							break;
						}
					}
					if(!isThum){
						if(null == appThumbnail){
							appThumbnail = new AppThumbnail();
							appThumbnail.setSrcImg(srcImg);
							appThumbnail.setSrcType(srcType);
							appThumbnail.setDimension(dimension);
						}
						thumist.add(appThumbnail);
					}
				}else{
					AppThumbnail appThumbnail = new AppThumbnail();
					appThumbnail.setSrcImg(srcImg);
					appThumbnail.setSrcType(srcType);
					appThumbnail.setDimension(dimension);
					thumist.add(appThumbnail);
				}
			}
		}
		
		for (AppThumbnail appThumbnail : thumist) {
			this.generateThumbnail(appThumbnail);
		}
		logger.info("appThumbnail job generate 【{}】 Photo thumbnail, type is {}", new Object[] {thumist.size(), srcType});
	}
	
	
	private void generateThumbnail(AppThumbnail appThumbnail){
		//生成缩略图
		String outputDir = "";
		if(StringUtils.equals(appThumbnail.getSrcType(), Constants.IMG)){
			outputDir = Constants.APP_IMAGES_SAVE_PATH;
		}
		if(null == appThumbnail.getFailCount()){
			appThumbnail.setFailCount(0);
		}
		
		String srcImg = appThumbnail.getSrcImg();
		String realSrcImgUrl = outputDir + "/" + srcImg;
		String thumImg = StringUtils.substringBeforeLast(srcImg, ".") 
				+ "_" + appThumbnail.getDimension() 
				+ "."  + StringUtils.substringAfterLast(srcImg, ".");
		
		try {
			ThumbnailTool.resize(realSrcImgUrl, appThumbnail.getDimension(), outputDir);
			appThumbnail.setStatus(Constants.THUMBNAIL_SUCCESS);
			appThumbnail.setThumbImg(thumImg);
		} catch (Exception e) {
			appThumbnail.setStatus(Constants.THUMBNAIL_FAIL);
			appThumbnail.setFailCount(appThumbnail.getFailCount() + 1);
			logger.error("generateThumbnail fail, srcImg="+srcImg, e);
		}
		
		//插入数据库
		appThumbnailService.save(appThumbnail);

	}
	
	
}
