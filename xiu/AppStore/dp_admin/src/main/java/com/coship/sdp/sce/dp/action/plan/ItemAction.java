/*
 * 文件名称：ItemAction.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：909194
 * 创建时间：2014-04-01
 *
 */
package com.coship.sdp.sce.dp.action.plan;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.coship.sdp.sce.dp.common.BaseAction;
import com.coship.sdp.sce.dp.common.Constants;
import com.coship.sdp.sce.dp.plan.entity.Item;
import com.coship.sdp.sce.dp.plan.service.ItemService;
import com.coship.sdp.sce.dp.utils.FileUtil;
import com.coship.sdp.utils.Debug;
import com.coship.sdp.utils.Page;

/*
 * 处理精细化管理_查询类项 操作请求的action类
 * @author 909194
 * @version [版本号, 2014-04-01]
 * @since [产品/模块版本]
 */
@Controller
public class ItemAction extends BaseAction{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 4658099075249308781L;

	/**
	 * 模块的名称.
	 */
	private static final String MODULE_NAME = ItemAction.class.getName();

	/**
	 * 分页对象
	 */
	private Page<Item> page;
	
	/**
	 * 专用于查询
	 */
	private Item queryItem;

    /**
     * 上传获取焦点图片文件
     */
    private File uploadFocus;

    /**
     * 上传获取焦点图片文件名.
     */
    private String uploadFocusFileName;

    /**
     * 上传获取焦点图片类型.
     */
    private String uploadFocusContentType;

    /**
     * 上传失去焦点图片文件
     */
    private File uploadLoseFocus;

    /**
     * 上传失去焦点图片文件名.
     */
    private String uploadLoseFocusFileName;

    /**
     * 上传失去焦点图片类型.
     */
    private String uploadLoseFocusContentType;
    
    @Autowired
    private ItemService itemService;
    

	/**
	 * 查询全部类项列表.
	 *
	 * @return 返回列表页面配置字符串
	 */
	public String list(){
		if( null == queryItem){
			queryItem = new Item();
			//查询所有类项的数据
			queryItem.setItemType(-2);
			//查询所有状态的数据
			queryItem.setStatus(-2);
		}
		try{
			page = itemService.list(this.start,this.limit,queryItem);
		}catch (Exception e){
			Debug.logError(e, e.getMessage(), MODULE_NAME);
			exception_msg = "方案类项查询出错";
			return ERROR;
		}
		return "list";
	}
	
	/**
	 * 根据 ids 删除数据
	 *
	 */
	public void delete(){
		String strIds = request.getParameter("ids");
		if (!StringUtils.isNotBlank(strIds)) {
			Debug.logWarning("ids is null", MODULE_NAME);
			write("error");
			return;
		}

		List<String> lstIds = new ArrayList<String>();
		String[] objIds = strIds.split(","); 
		for (String strId : objIds) {
			lstIds.add(strId);
		}
		
		try{
			itemService.delete(lstIds);
		}catch (Exception e){
			Debug.logError(e, e.getMessage(), MODULE_NAME);
			write("error");
			return;
		}
		write("success");
	}
	
	/**
	 * 根据 ids 启用数据
	 *
	 */
	public void enable(){
		String strIds = request.getParameter("ids");
		if (!StringUtils.isNotBlank(strIds)) {
			Debug.logWarning("ids is null", MODULE_NAME);
			write("error");
			return;
		}

		List<String> lstIds = new ArrayList<String>();
		String[] objIds = strIds.split(","); 
		for (String strId : objIds) {
			lstIds.add(strId);
		}
		
		try{
			itemService.enable(lstIds);
		}catch (Exception e){
			Debug.logError(e, e.getMessage(), MODULE_NAME);
			write("error");
			return;
		}
		write("success");
	}
	
	/**
	 * 根据 ids 禁用数据
	 *
	 */
	public void disable(){
		String strIds = request.getParameter("ids");
		if (!StringUtils.isNotBlank(strIds)) {
			Debug.logWarning("ids is null", MODULE_NAME);
			write("error");
			return;
		}

		List<String> lstIds = new ArrayList<String>();
		String[] objIds = strIds.split(","); 
		for (String strId : objIds) {
			lstIds.add(strId);
		}
		
		try{
			itemService.disable(lstIds);
		}catch (Exception e){
			Debug.logError(e, e.getMessage(), MODULE_NAME);
			write("error");
			return;
		}
		write("success");
	}
	
	
    /**
    *
    * 跳转到新增页面
    */
   public String toAdd(){
       try{
    	   this.queryItem = new Item();
       }catch (Exception e){
           Debug.logError(e, "toAdd()" + e.getMessage(), MODULE_NAME);
           exception_msg = this.getText("sdp.sce.dp.admin.common.jump.error");
           return ERROR;
       }
       return "edit";
   }
   
   /**
   *
   * 跳转到修改页面
   */
  public String toEdit(){
	  String strId = request.getParameter("id");
      try{
    	  queryItem = itemService.findItem(strId);
      }catch (Exception e){
          Debug.logError(e, "toEdit()" + e.getMessage(), MODULE_NAME);
          exception_msg = this.getText("sdp.sce.dp.admin.common.jump.error");
          return ERROR;
      }
      return "edit";
  }
  
	  /**
	  *
	  * 新增类项
	  */
	 public String save(){
	     try{
			if (uploadFocus != null) {
				doUpload(uploadFocus, uploadFocusFileName, 1);
			}
			if (uploadLoseFocus != null) {
				doUpload(uploadLoseFocus, uploadLoseFocusFileName, 2);
			}

			itemService.save(queryItem);
	     }catch (Exception e){
	         Debug.logError(e, "save()" + e.getMessage(), MODULE_NAME);
	         exception_msg = "类项新增保存出错";
	         return ERROR;
	     }
	     return "Success";
	 }
 
	 /**
	 *
	 * 修改类项
	 */
	public String update(){
	    try{
			if (uploadFocus != null) {
				doUpload(uploadFocus, uploadFocusFileName, 1);
			}
			if (uploadLoseFocus != null) {
				doUpload(uploadLoseFocus, uploadLoseFocusFileName, 2);
			}
			
			Item item = itemService.findItem(queryItem.getId());
			if (StringUtils.isNotEmpty(queryItem.getTypeImg1())) {
				item.setTypeImg1(queryItem.getTypeImg1());
			}
			if (StringUtils.isNotEmpty(queryItem.getTypeImg2())) {
				item.setTypeImg2(queryItem.getTypeImg2());
			}
			item.setName(queryItem.getName());
			item.setParentTypeCode(queryItem.getParentTypeCode());
			item.setDescription(queryItem.getDescription());
			item.setUpdateTime(new Date());

	        itemService.update(item);
	    }catch (Exception e){
	        Debug.logError(e, "update()" + e.getMessage(), MODULE_NAME);
	        exception_msg = "类项修改保存出错";
	        return ERROR;
	    }
	    return "Success";
	}
	
    /**
     * 上传图片
     * @param upload 上传的文件
     * @param uploadFileName 上传的文件名
     * @param i 上传图片的index
     * @return [参数说明]
     * @return String [返回类型说明]
     * @throws IOException 
     * @throws FileNotFoundException 
     * @exception throws [违例类型] [违例说明]
     */
	private String doUpload(File upload, String uploadFileName, int i) throws FileNotFoundException, IOException {
		String uploadPath = Constants.APP_IMAGES_SAVE_PATH;
		String saveuploadFileName = FileUtil.resetFileName(uploadFileName);
		if (i == 1) {
			queryItem.setTypeImg1(saveuploadFileName);
		} else {
			queryItem.setTypeImg2(saveuploadFileName);
		}
		
		FileUtil.uploadFile(upload, uploadPath, saveuploadFileName);
		return null;
	}
 

	public Page<Item> getPage() {
		return page;
	}


	public void setPage(Page<Item> page) {
		this.page = page;
	}


	public Item getQueryItem() {
		return queryItem;
	}


	public void setQueryItem(Item queryItem) {
		this.queryItem = queryItem;
	}

	public File getUploadFocus() {
		return uploadFocus;
	}

	public void setUploadFocus(File uploadFocus) {
		this.uploadFocus = uploadFocus;
	}

	public String getUploadFocusFileName() {
		return uploadFocusFileName;
	}

	public void setUploadFocusFileName(String uploadFocusFileName) {
		this.uploadFocusFileName = uploadFocusFileName;
	}

	public String getUploadFocusContentType() {
		return uploadFocusContentType;
	}

	public void setUploadFocusContentType(String uploadFocusContentType) {
		this.uploadFocusContentType = uploadFocusContentType;
	}

	public File getUploadLoseFocus() {
		return uploadLoseFocus;
	}

	public void setUploadLoseFocus(File uploadLoseFocus) {
		this.uploadLoseFocus = uploadLoseFocus;
	}

	public String getUploadLoseFocusFileName() {
		return uploadLoseFocusFileName;
	}

	public void setUploadLoseFocusFileName(String uploadLoseFocusFileName) {
		this.uploadLoseFocusFileName = uploadLoseFocusFileName;
	}

	public String getUploadLoseFocusContentType() {
		return uploadLoseFocusContentType;
	}

	public void setUploadLoseFocusContentType(String uploadLoseFocusContentType) {
		this.uploadLoseFocusContentType = uploadLoseFocusContentType;
	}
	
    
}
