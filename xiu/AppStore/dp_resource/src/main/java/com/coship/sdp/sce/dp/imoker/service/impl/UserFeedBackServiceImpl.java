package com.coship.sdp.sce.dp.imoker.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coship.sdp.sce.dp.ap.entity.AppInfoDetail;
import com.coship.sdp.sce.dp.imoker.dao.UserFeedbackDao;
import com.coship.sdp.sce.dp.imoker.entity.UserFeedback;
import com.coship.sdp.sce.dp.imoker.service.UserFeedbackService;
import com.coship.sdp.utils.Page;

@Service("userFeedbackService")
@Transactional
public class UserFeedBackServiceImpl implements UserFeedbackService
{
	private static final long serialVersionUID = 8744702470068495799L;
	
	@Autowired
    private UserFeedbackDao userFeedbackDao;
    
	@Override
	public void saveImokerFeedback(UserFeedback entity) throws Exception
	{
		userFeedbackDao.save(entity);
		
	}
   
    /**
     * 查询用户反馈列表
     */
    @Override
    public Page<UserFeedback> findFeedback(Page<UserFeedback> page, Object[] values) throws Exception
    {
        String hql = "from UserFeedback a order by a.feedbackTime desc ";
        Page<UserFeedback> newPage = this.userFeedbackDao.queryPage(page, hql, values);
        return newPage;
    }
    
    @Override
    public UserFeedback findFeedbackDetail(String id) throws Exception
    {
       return userFeedbackDao.get(id);
    }
}
