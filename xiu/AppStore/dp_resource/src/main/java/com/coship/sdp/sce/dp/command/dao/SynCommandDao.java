package com.coship.sdp.sce.dp.command.dao;

import java.util.List;

import com.coship.sdp.access.dao.IGenericDao;
import com.coship.sdp.sce.dp.ap.entity.AppInfoDetail;
import com.coship.sdp.sce.dp.command.entity.SynCommand;

public interface SynCommandDao extends IGenericDao<SynCommand, String>
{
    /**
     * <功能描述>
     */
	int updateToBeIssued();
	
    /**
     * <功能描述>
     */
	int updateToBeDelete();
	
	
    /**
     * <功能描述>
     * @return List<String> [返回类型说明]
     */
	List<String> queryIssuedCommand();
	
    /**
     * <功能描述>
     * @return List<AppInfoDetail> [返回类型说明]
     */
    List<AppInfoDetail> getSynAppInfos();
    
    /**
     * 
     * @param synCount
     * @return
     */
    int updateWhichIssued(int synCount,String flag);
    
    
    int updateStatusAfterSend(final int status, final String flag,final boolean isFail);
    
    
    List<String> queryUnIssuedCommand(String appType);
}
