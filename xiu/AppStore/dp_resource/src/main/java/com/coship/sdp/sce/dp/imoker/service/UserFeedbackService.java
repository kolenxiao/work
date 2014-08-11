package com.coship.sdp.sce.dp.imoker.service;

import java.io.Serializable;
import java.util.List;

import com.coship.sdp.sce.dp.imoker.entity.UserFeedback;
import com.coship.sdp.utils.Page;

public interface UserFeedbackService extends Serializable
{
    /**
     * 保存UserFeedback信息.
     * @param entity 应用信息对象
     * @throws Exception [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public void saveImokerFeedback(UserFeedback entity) throws Exception;
    
    public Page<UserFeedback> findFeedback(Page<UserFeedback> page, Object[] values) throws Exception;
    
    public UserFeedback findFeedbackDetail(String id) throws Exception;
}
