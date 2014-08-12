package com.coship.depgm.action;

import java.io.File;
import java.io.FileInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.coship.depgm.common.UID;
import com.coship.depgm.model.ProgramChapter;
import com.coship.depgm.model.ProgramContent;
import com.coship.depgm.service.PosterService;
import com.coship.depgm.service.ProgramChapterService;
import com.coship.depgm.service.ProgramContentService;

public class ProgramContentChapterAction extends BaseAction {
	private static final long serialVersionUID = 6964001415610063679L;

	private static final Logger logger = LoggerFactory.getLogger(ProgramContentChapterAction.class);
	
	@Autowired
	private ProgramChapterService programChapterService;
	
	@Autowired
	private ProgramContentService programContentService;
	
	@Autowired
	private PosterService posterService;
	
	private String id;
	
	private int chapter;
	
	private String contentId;

	/**
	 * 上传海报
	 */
	private File attachment;
	private String attachmentFileName;
	private String attachmentContentType;

	/**
	 * 节目下面所有剧集列表
	 */
	public void chapterList() throws Exception {
		try {
			programChapterService.queryChapterPagerList(pager, contentId);
			jsonPage();
		} catch (Exception e) {
			logger.error("查询剧集列表失败", e);
			jsonRet("1", e.getMessage());
		}
	}

	/**
	 * 删除节目下面剧集
	 */
	public void deleteChapter() {
		try {
			Assert.notNull(id, "id不能为空!");
			programChapterService.deleteChapter(id);
			success();
		} catch (Exception e) {
			logger.error("删除节目失败", e);
			jsonRet("1", e.getMessage());
		}
	}

	/**
	 * 修改剧集内容
	 */
	public void updateChapter() {
		try {
			Assert.notNull(id, "id不能为空!");

			ProgramChapter programChapter = new ProgramChapter();
			programChapter.setId(id);
			programChapter.setChapter(chapter);
			ProgramContent content = programContentService.get(contentId);
			// 新增剧集不能超过总集数
			if (chapter <= content.getChapter()) {
				// 竖版海报
				if (null != attachment) {
					String saveUploadFileName = doUpload(attachment,
							attachmentFileName);
					programChapter.setPoster(saveUploadFileName);
				}
				programChapterService.updateChapter(programChapter);
				success();
			} else {
				jsonRet("1", "不能超过总集数" + content.getChapter());
			}
		} catch (Exception e) {
			logger.error("修改节目失败", e);
			jsonRet("1", e.getMessage());
		}
	}

	/**
	 * 新增剧集
	 */
	public void createChapter() {
		try {
			Assert.notNull(chapter, "集数不能为空!");
			Assert.notNull(attachment, "海报不能为空!");

			ProgramChapter programChapter = new ProgramChapter();
			programChapter.setChapter(chapter);
			programChapter.setContentId(contentId);
			ProgramContent content = programContentService.get(contentId);
			// 新增剧集不能超过总集数
			if (chapter <= content.getChapter()) {
				if (null != attachment) {
					String saveUploadFileName = doUpload(attachment, attachmentFileName);
					programChapter.setPoster(saveUploadFileName);
				}
				programChapterService.addChapter(programChapter);
				success();
			} else {
				jsonRet("1", "不能超过总集数" + content.getChapter());
			}
		} catch (Exception e) {
			logger.error("新增节目失败", e);
			jsonRet("1", e.getMessage());
		}
	}

	private String doUpload(File upload, String uploadFileName) throws Exception {
		uploadFileName = UID.create();
		return posterService.upload(new FileInputStream(upload), contentId, uploadFileName);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getChapter() {
		return chapter;
	}

	public void setChapter(int chapter) {
		this.chapter = chapter;
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
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
}