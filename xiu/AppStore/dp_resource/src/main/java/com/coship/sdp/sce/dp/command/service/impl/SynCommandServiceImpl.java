package com.coship.sdp.sce.dp.command.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coship.sdp.sce.dp.ap.entity.AppInfoDetail;
import com.coship.sdp.sce.dp.command.dao.SynCommandDao;
import com.coship.sdp.sce.dp.command.entity.SynCommand;
import com.coship.sdp.sce.dp.command.service.SynCommandService;
import com.coship.sdp.sce.dp.common.AppStatusConstants;
import com.coship.sdp.sce.dp.plan.entity.Plan;
import com.coship.sdp.sce.dp.plan.service.PlanConditionService;
import com.coship.sdp.sce.dp.plan.service.PlanService;

/**
 * <客户端用户操作记录服务层接口>.
 * @author  Huangliufei/907632
 * @version  [版本号, September 5, 2013]
 * @since  [产品/模块版本]
 */
@Service("SynCommandService")
@Transactional
public class SynCommandServiceImpl implements SynCommandService
{
	private static final long serialVersionUID = -10551508618484031L;
    
	@Autowired
	private SynCommandDao synCommandDao;
	
	@Autowired
	private PlanService planService;
	
	@Autowired
	private PlanConditionService planConditionService;
	
	public void updateStauts(int synCount,String flag) throws Exception
	{
		List<String> appList = queryIssuedCommand();
		addCommand(appList);
		updateToBeIssued();
		updateToBeDelete();
		signWhichSendNow(synCount,flag);
		
		
	}

	
	public void updateToBeIssued() throws Exception
	{
		synCommandDao.updateToBeIssued();
		
	}

	
	public void updateToBeDelete() throws Exception
	{
		
		synCommandDao.updateToBeDelete();
	}

	
	public List<String> queryIssuedCommand() throws Exception
	{
		
		return synCommandDao.queryIssuedCommand();
	}
	
	public List<String> queryUnIssuedCommand(String appType) throws Exception
	{
		
		return synCommandDao.queryUnIssuedCommand(appType);
	}


	@Override
	public void addCommand(final List<String> appList) throws Exception
	{
		if (appList != null)
		{
			for (String id : appList)
			{
				SynCommand aSynCommand = new SynCommand();
				aSynCommand.setAppId(id.trim());
				aSynCommand.setFailCount(0);
				aSynCommand.setInitTime(new Date());
				aSynCommand.setLastTime(new Date());
				aSynCommand.setStatus(1);
				synCommandDao.save(aSynCommand);
			}
		}
		
	}
	
	@Override
	public List<AppInfoDetail> getSynAppInfos() throws Exception
	{
		//获取需要同步到搜索引擎的应用
		List<AppInfoDetail> appList = synCommandDao.getSynAppInfos();
		
		for (AppInfoDetail appInfoDetail : appList) {
			// 获取关联应用的方案
			Set<Plan> planSet = planService.getPlanSetByApp(appInfoDetail.getAppFilePackage());
			Set<String> planIdSet = new HashSet<String>();
			for (Plan e : planSet) {
				planIdSet.add(e.getId());
			}

			// 设置方案id属性值
			appInfoDetail.setPlan(StringUtils.join(planIdSet, ";"));
		}
		return appList;
	}
	
	public void signWhichSendNow(int synCount,String flag){
		synCommandDao.updateWhichIssued(synCount, flag);
	}


	@Override
	public void updateStatusAfterSend(final int status, final String flag,final boolean isFail)
	{
		synCommandDao.updateStatusAfterSend(status, flag, isFail);
	}

	@Override
	public void updateStatusToModify(String appId, String appStatus) {
		//只有应用是上架的状态才同步
		if(StringUtils.equals(appStatus, AppStatusConstants.APP_STATUS_ONLINE)){
			String hql = "from SynCommand where appId =?";
			SynCommand synCommand = null;
			if (StringUtils.isNotBlank(appId)) {
				synCommand = (SynCommand)synCommandDao.createQuery(hql, appId).setMaxResults(1).uniqueResult();
				if (null != synCommand) {
					synCommand.setStatus(1);
					synCommand.setParam("1");
					synCommandDao.update(synCommand);
				}
			}
		}
	}

}
