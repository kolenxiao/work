package com.coship.depgm.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.coship.depgm.service.BtvService;
import com.coship.depgm.service.ProgramService;

public class ProgramAction extends BaseAction {
	private static final long serialVersionUID = -5876261219603691053L;
	private String id;
	private String beginTime;
	private String endTime;
	private String date;
	
	private String channelId;
	private String contentId;
	@Autowired
	private ProgramService programService;

	public void getTypeList() throws Exception {
		list = programService.getProgramTypeList();
		jsonList();
	}

	public void getProgramList() throws Exception {
		programService.getProgramList(pager, channelId, beginTime);
		jsonPage();
	}

	public void editShip() throws Exception {
		boolean boo = programService.updateProgramContent(id, contentId);
		jsonRet(boo ? "1" : "0", boo ? "success" : "failed");
	}

	@Autowired
	private BtvService btvService;
	public void btv(){
		btvService.caculateDisableBtv();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
