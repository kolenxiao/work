package com.coship.depgm.action;

import java.io.File;
import java.io.FileInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.coship.depgm.common.DepgmConfig;
import com.coship.depgm.common.UID;
import com.coship.depgm.model.ProgramContent;
import com.coship.depgm.service.PosterService;
import com.coship.depgm.service.ProgramContentService;
import com.coship.depgm.service.ProgramTypeService;

public class ProgramContentAction extends BaseAction {
	private static final long serialVersionUID = -7015245275542477457L;

	private static final Logger logger = LoggerFactory.getLogger(ProgramContentAction.class);

	@Autowired
	private ProgramContentService programContentService;
	@Autowired
	private ProgramTypeService programTypeService;
	@Autowired
	private PosterService posterService; 

	private ProgramContent programContent;

	private String id;
	private String name;
	private String description;
	private String typeId;
	private String typeName;
	private int chapter=0;

    /**
     * 上传竖版海报
     */
    private File attachment;
    private String attachmentFileName;
    private String attachmentContentType;
    
    /**
     * 上传横版海报
     */
    private File hattachment;
    private String hattachmentFileName;
    private String hattachmentContentType;
    
    private String imgContextPath;

	/**
	 * 新增
	 */
	public void create() {
		try {
			Assert.notNull(name, "名称不能为空!");
			Assert.notNull(typeId, "所属分类不能为空!");
			Assert.notNull(attachment, "竖版海报不能为空!");
			Assert.notNull(hattachment, "横版海报不能为空!");

			programContent = new ProgramContent();
			programContent.setId(UID.create());
			programContent.setName(name);
			programContent.setTypeId(typeId);
			programContent.setBtv(false);
			programContent.setDescription(description);
			programContent.setChapter(chapter);
			//竖版海报
			if(null != attachment){
				String saveUploadFileName = doUpload(attachment, attachmentFileName);
				programContent.setPoster(saveUploadFileName);
			}
			//横版海报
			if(null != hattachment){
				String saveUploadHFileName = doUpload(hattachment, hattachmentFileName);
				programContent.setHoriPoster(saveUploadHFileName);
			}
			programContentService.add(programContent);
			success();
		} catch (Exception e) {
			logger.error("新增节目失败", e);
			jsonRet("1", e.getMessage());
		}
	}

	/**
	 * 修改
	 */
	public void update() {
		try {
			Assert.notNull(id, "id不能为空!");

			programContent = new ProgramContent();
			programContent.setId(id);
			programContent.setName(name);
			programContent.setTypeId(typeId);
			programContent.setChapter(chapter);
			programContent.setDescription(description);

			//竖版海报
			if(null != attachment){
				String saveUploadFileName = doUpload(attachment, attachmentFileName);
				programContent.setPoster(saveUploadFileName);
			}
			//横版海报
			if(null != hattachment){
				String saveUploadHFileName = doUpload(hattachment, hattachmentFileName);
				programContent.setHoriPoster(saveUploadHFileName);
			}

			programContentService.update(programContent);
			success();
		} catch (Exception e) {
			logger.error("修改节目失败", e);
			jsonRet("1", e.getMessage());
		}
	}

	/**
	 * 删除
	 */
	public void delete() {
		try {
			Assert.notNull(id, "id不能为空!");
			programContentService.delete(id);
			success();
		} catch (Exception e) {
			logger.error("删除节目失败", e);
			jsonRet("1", e.getMessage());
		}
	}

	/**
	 * 查询
	 */
	public void list() throws Exception {
		try {
			programContent = new ProgramContent();
			programContent.setName(name);
			programContent.setTypeId(typeId);

			programContentService.queryPagerList(pager,programContent);
			jsonPage();
		} catch (Exception e) {
			logger.error("查询节目列表失败", e);
			jsonRet("1", e.getMessage());
		}
	}
	
    /**
     * 上传FTP图片
     */
	private String doUpload(File upload, String uploadFileName) throws Exception {
		uploadFileName = UID.create();
		return posterService.upload(new FileInputStream(upload),programContent.getId(),uploadFileName);
	}

	public ProgramContent getProgramContent() {
		return programContent;
	}

	public void setProgramContent(ProgramContent programContent) {
		this.programContent = programContent;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public File getAttachment() {
		return attachment;
	}

	public void setAttachment(File attachment) {
		this.attachment = attachment;
	}

	public String getAttachmentFileName() {
		return attachmentFileName;
	}

	public void setAttachmentFileName(String attachmentFileName) {
		this.attachmentFileName = attachmentFileName;
	}

	public String getAttachmentContentType() {
		return attachmentContentType;
	}

	public void setAttachmentContentType(String attachmentContentType) {
		this.attachmentContentType = attachmentContentType;
	}

	public String getImgContextPath() {
		return imgContextPath;
	}

	public File getHattachment() {
		return hattachment;
	}

	public void setHattachment(File hattachment) {
		this.hattachment = hattachment;
	}

	public String getHattachmentFileName() {
		return hattachmentFileName;
	}

	public void setHattachmentFileName(String hattachmentFileName) {
		this.hattachmentFileName = hattachmentFileName;
	}

	public String getHattachmentContentType() {
		return hattachmentContentType;
	}

	public void setHattachmentContentType(String hattachmentContentType) {
		this.hattachmentContentType = hattachmentContentType;
	}

	public void setImgContextPath(String imgContextPath) {
		this.imgContextPath = "http://"+DepgmConfig.getPosterHost()+"/"+DepgmConfig.getPosterDir()+"/";
	}

	public int getChapter() {
		return chapter;
	}

	public void setChapter(int chapter) {
		this.chapter = chapter;
	}
}
