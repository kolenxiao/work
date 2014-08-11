package com.coship.sdp.sce.dp.action.system;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.coship.sdp.sce.dp.common.BaseAction;
import com.coship.sdp.sce.dp.common.Constants;
import com.coship.sdp.sce.dp.system.entity.SystemParam;
import com.coship.sdp.sce.dp.system.service.SystemParamService;
import com.coship.sdp.sce.dp.utils.FileUtil;
import com.coship.sdp.utils.Debug;
import com.coship.sdp.utils.Page;

/**
 * 系统参数管理
 * @author 908618
 *
 */
@Controller
public class SystemParamAction extends BaseAction {

	private static final long serialVersionUID = -3064976371416359461L;
	private static final Logger logger = LoggerFactory.getLogger(SystemParamAction.class);
	private static final String MODULE_NAME = SystemParamAction.class.getName();

	@Autowired
	private SystemParamService systemParamService;
	
	private SystemParam systemParam;
	private String paramId;
	private Page<SystemParam> page;
	

    /**
     * 上传人图片文件
     */
    private File upload;
    private String uploadFileName;
    private String uploadContentType;
	
	/**
	 * 查询列表
	 * @return
	 */
	public String list(){
        page = new Page<SystemParam>();
        page.setPageSize(limit);
        page.setCurrentPage(start);
        
        if(null == systemParam){
        	systemParam = new SystemParam();
        }

        //构造查询条件
        Map<String, Object> specialParams = new HashMap<String, Object>();
        
        //查询
        page = systemParamService.search(page, systemParam, specialParams);
		
		// 返回
		return "list";
	}
	
	public String display(){
		String flag = request.getParameter("flag");
		if (StringUtils.equals(flag, "create")) {
			systemParam = new SystemParam();
		} else if (StringUtils.equals(flag, "modify")) {
			systemParam = systemParamService.getById(paramId);
		}
		return "info";
	}
	
	/**
	 * 新增/修改
	 * @return
	 */
	public String createOrModify(){
		logger.info("SystemParamAction.createOrModify start, systemParam={}", systemParam);
		try {
			if (Constants.PARAM_TYPE_PHOTO.equals(systemParam.getType())) {
				if(null != upload){
					String saveUploadFileName = doUpload(upload, uploadFileName);
					systemParam.setValue(saveUploadFileName);
				}
			}
			if(StringUtils.isBlank(systemParam.getId())){
				systemParamService.create(systemParam);
			}else{
				systemParamService.update(systemParam);
			}
		} catch (Exception e) {
			if (StringUtils.isNotBlank(e.getMessage())) {
				exception_msg = e.getMessage();
			} else {
				exception_msg = "操作失败！";
			}
			logger.error("SystemParamAction.createOrModify fail, systemParam={}", systemParam);
			Debug.logError(e, e.getMessage(), MODULE_NAME);
			return ERROR;
		}

		// 返回
		logger.info("SystemParamAction.createOrModify success, systemParam={}", systemParam);
		return "successToList";
	}
	
	
	/**
	 * 删除
	 * @return
	 */
	public String delete(){
		String ids = request.getParameter("paramIds");
		try {
			logger.info("SystemParamAction.delete start, ids={}", ids);
			if(StringUtils.isBlank(ids)){
				throw new IllegalArgumentException("传入的参数id不正确");
			}
			
			//修改方案状态
			String[] idArr = StringUtils.split(ids, ",");
			systemParamService.delete(idArr);
			write("success");
			logger.info("SystemParamAction.deletePlan success, ids={}", ids);
		} catch (Exception e) {
			if (StringUtils.isNotBlank(e.getMessage())) {
				exception_msg = e.getMessage();
			} else {
				exception_msg = "操作失败！";
			}
			logger.error("SystemParamAction.delete fail, ids={}", ids);
			Debug.logError(e, e.getMessage(), MODULE_NAME);
			write(exception_msg);
		}
		return NONE;
	}
	
	
	
	
	
	
    /**
     * 上传图片
     * @param upload 上传的文件
     * @param uploadFileName 上传的文件名
     * @return [参数说明]
     * @return String [返回类型说明]
     * @throws IOException 
     * @throws FileNotFoundException 
     * @exception throws [违例类型] [违例说明]
     */
	private String doUpload(File upload, String uploadFileName) throws FileNotFoundException, IOException {
		String uploadPath = Constants.SYSTEM_IMAGES_SAVE_PATH;
		String saveuploadFileName = FileUtil.resetFileName(uploadFileName);
		
		FileUtil.uploadFile(upload, uploadPath, saveuploadFileName);
		return saveuploadFileName;
	}


	

	public SystemParam getSystemParam() {
		return systemParam;
	}

	public void setSystemParam(SystemParam systemParam) {
		this.systemParam = systemParam;
	}

	public Page<SystemParam> getPage() {
		return page;
	}

	public void setPage(Page<SystemParam> page) {
		this.page = page;
	}

	public String getParamId() {
		return paramId;
	}

	public void setParamId(String paramId) {
		this.paramId = paramId;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}



	
}
