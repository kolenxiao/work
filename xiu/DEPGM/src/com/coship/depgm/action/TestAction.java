package com.coship.depgm.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.coship.depgm.service.BtvService;
import com.coship.depgm.service.MscpService;
import com.coship.depgm.service.ProgramService;
import com.coship.depgm.service.TvSouService;

public class TestAction extends BaseAction {
	private static final long serialVersionUID = -2719471122521332491L;
	@Autowired
	private BtvService btvService;
	@Autowired
	private TvSouService tvSouService;
	@Autowired
	private MscpService mscpService;
	@Autowired
	private ProgramService programService;
	
	public void content() throws Exception{
		tvSouService.catchFullContent("93295");		
	}
	
	public void program() throws Exception{
		tvSouService.catchProgram("2014-08-11");
		tvSouService.catchProgram("2014-08-12");
		tvSouService.catchProgram("2014-08-13");
		tvSouService.catchProgram("2014-08-14");
		//programService.clearProgram();
	}
	
	public void export() throws Exception{
		mscpService.exportProgramToMscp();
	}
	
	public void btv() throws Exception{
		btvService.caculateAvailableBtv();
	}
}