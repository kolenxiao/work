package com.coship.sdp.sce.dp.imoker.dao.impl;

import org.springframework.stereotype.Repository;
import com.coship.sdp.access.dao.impl.GenericDaoImpl;
import com.coship.sdp.sce.dp.imoker.dao.UserFeedbackDao;
import com.coship.sdp.sce.dp.imoker.entity.UserFeedback;

@Repository("userFeedbackDao")
public class UserFeedbackDaoImpl extends
GenericDaoImpl<UserFeedback, String> implements UserFeedbackDao
{

}
