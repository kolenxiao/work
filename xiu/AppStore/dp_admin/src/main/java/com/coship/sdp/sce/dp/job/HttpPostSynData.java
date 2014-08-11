package com.coship.sdp.sce.dp.job;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.coship.sdp.sce.dp.ap.entity.AppInfoDetail;
import com.coship.sdp.sce.dp.atachmentfile.service.AttachmentFileService;
import com.coship.sdp.sce.dp.command.service.SynCommandService;
import com.coship.sdp.sce.dp.common.Constants;
import com.coship.sdp.sce.dp.common.DefaultTypeCodeConstants;
import com.coship.sdp.sce.dp.common.HttpUtil;
import com.coship.sdp.sce.dp.type.entity.DpType;
import com.coship.sdp.sce.dp.type.service.DpTypeService;
import com.coship.sdp.sce.dp.xstream.dto.SyncAppInfoDataList;
import com.coship.sdp.sce.dp.xstream.dto.UnusedCommand;
import com.coship.sdp.sce.dp.xstream.service.ServiceSend;
import com.coship.sdp.utils.Debug;

/**
 * 使用HTTP POST方式进行数据同步
 * @author 907632
 *
 */
public class HttpPostSynData implements SynData
{
    @Autowired
	private SynCommandService synCommandService;
    @Autowired
    private DpTypeService dpTypeService;
    @Autowired
    private AttachmentFileService attachmentFileService;
    
    private static final String ISUSSED = "1";
    private static final String MODULE_NAME = HttpPostSynData.class.getName();
    private static final int SYN_UPDATE_FAIL = 1;
    private static final int SYN_UPDATE_SUCCESS = 2;
    private static final int SYN_DELETE_FAIL = 3;
    private static final int SYN_DELETE_SUCCESS = 4;
    private static final String SUCCESS = "0";
    private int syncCountOnce = 100;
  
	
	public void syncDataToPlatform() throws Exception
	{
		synCommandService.updateStauts(syncCountOnce,ISUSSED);
		
		syncUpdateData();
		
		syncDeleteData();
	}
	
	public void syncUpdateData() {
		try {
			List<AppInfoDetail> allList = synCommandService.getSynAppInfos();
			if (null != allList && allList.size() != 0) {
				// 应用类型List
				List<DpType> appTypeList = dpTypeService
						.findVisibleTypeByParentTypeCode(DefaultTypeCodeConstants.APP_TYPE_CODE);
				// 游戏类型List
				List<DpType> gameTypeList = dpTypeService
						.findVisibleTypeByParentTypeCode(DefaultTypeCodeConstants.GAME_TYPE_CODE);
				//获取Logo图片存储路径
				Map<String, String> attachMap = attachmentFileService.findAttachByAppIdsAndFileDesc(allList, Constants.LOGO);
				//初始化图片存储路径
				String url = HttpUtil.initURL(Constants.APPSTORE_UPLOAD_IP, Integer.valueOf(Constants.APPSTORE_UPLOAD_PORT));
				url += Constants.APP_LOGO_MAPPE_PATH;
				//url = "http://172.30.25.245:8921/upload/applogo/";

				// 将应用按App和game分类
				List<AppInfoDetail> appList = new ArrayList<AppInfoDetail>();
				List<AppInfoDetail> gameList = new ArrayList<AppInfoDetail>();
				for (AppInfoDetail app : allList) {
					Boolean isAppType = Boolean.FALSE;
					for (DpType dpType : appTypeList) {
						// 添加到应用列表
						if (app.getTypeId().equals(dpType.getId())) {
							appList.add(app);
							isAppType = Boolean.TRUE;
							break;
						}
					}
					if(!isAppType){
						for (DpType dpType : gameTypeList) {
							// 添加到游戏列表
							if (app.getTypeId().equals(dpType.getId())) {
								gameList.add(app);
								break;
							}
						}
					}
					// 添加logo信息
					if(attachMap.containsKey(app.getId())){
						app.setLogoUrl(url + attachMap.get(app.getId()));
					}
				}

				// 推送到搜索系统
				this.syncUpdateData("appInfo", appList);
				this.syncUpdateData("gameInfo", gameList);
			}

		} catch (Exception e) {
			Debug.logError(e, e.getMessage(), MODULE_NAME);
		}
	}

	
	public void syncDeleteData() throws Exception
	{
		try
		{
			List<String> appList = synCommandService.queryUnIssuedCommand(DefaultTypeCodeConstants.APP_TYPE_CODE);
			List<UnusedCommand> sendAppList = initDeleteList(appList);
			this.syncDeleteData("appInfo", sendAppList);
			
			List<String> gameList = synCommandService.queryUnIssuedCommand(DefaultTypeCodeConstants.GAME_TYPE_CODE);
			List<UnusedCommand> sendGameList = initDeleteList(gameList);
			this.syncDeleteData("gameInfo", sendGameList);
		} catch (Exception  e)
		{
			Debug.logError(e, e.getMessage(), MODULE_NAME);
		}
		
	}

    private List<UnusedCommand> initDeleteList(List<String> list){
    	List<UnusedCommand> commandList = new ArrayList<UnusedCommand>();
    	
		if (list != null && list.size() > 0)
		{
			for(String id : list){
				UnusedCommand obj = new UnusedCommand();
				obj.setId(id);
				commandList.add(obj);
			}
		}
		
		return commandList;
    }
    
    private void syncUpdateData(String syncName, List<AppInfoDetail> list){
		if (list != null && list.size() != 0) {
			SyncAppInfoDataList<AppInfoDetail> dataList = new SyncAppInfoDataList<AppInfoDetail>(syncName);
			dataList.setDataList(list);
			String reuslt = ServiceSend.sendData(dataList, "SEARCH_SYNC_DATA", "SEARCH", AppInfoDetail.class);

			if (SUCCESS.equals(reuslt)) {
				Debug.logInfo("sync update data to platform success, syncName:"+syncName+", size:"+list.size(), MODULE_NAME);
				synCommandService.updateStatusAfterSend(SYN_UPDATE_SUCCESS, ISUSSED, false);
			} else {
				Debug.logInfo("sync update data to platform fail, syncName:"+syncName, MODULE_NAME);
				synCommandService.updateStatusAfterSend(SYN_UPDATE_FAIL, ISUSSED, true);
			}
		}
    }
    
	private void syncDeleteData(String syncName, List<UnusedCommand> list) {
		if (list != null && list.size() != 0) {
			SyncAppInfoDataList<UnusedCommand> dataList = new SyncAppInfoDataList<UnusedCommand>(syncName);
			dataList.setDataList(list);
			String reuslt = ServiceSend.sendData(dataList, "SEARCH_DELETE_DATA", "SEARCH", UnusedCommand.class);

			if (SUCCESS.equals(reuslt)) {
				Debug.logInfo("sync delete data to platform success, syncName:"+syncName+", size:"+list.size(), MODULE_NAME);
				synCommandService.updateStatusAfterSend(SYN_DELETE_SUCCESS, ISUSSED, false);
			} else {
				Debug.logInfo("sync delete data to platform fail, syncName:"+syncName, MODULE_NAME);
				synCommandService.updateStatusAfterSend(SYN_DELETE_FAIL, ISUSSED, true);
			}
		}
	}
	
	
	
	public SynCommandService getSynCommandService()
	{
		return synCommandService;
	}


	public void setSynCommandService(SynCommandService synCommandService)
	{
		this.synCommandService = synCommandService;
	}


	public int getSyncCountOnce()
	{
		return syncCountOnce;
	}


	public void setSyncCountOnce(int syncCountOnce)
	{
		this.syncCountOnce = syncCountOnce;
	}
	
}
