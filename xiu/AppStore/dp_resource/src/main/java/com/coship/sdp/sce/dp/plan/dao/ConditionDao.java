/*
 * 文件名称：ConditionDao.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：909194
 * 创建时间：2014-03-27
 *
 */
package com.coship.sdp.sce.dp.plan.dao;

import java.util.List;
import java.util.Map;

import com.coship.sdp.access.dao.IGenericDao;
import com.coship.sdp.sce.dp.plan.entity.Condition;
import com.coship.sdp.utils.Page;

/**
 * 应用数据访问接口
 * @author 909194
 * @version [版本号, 2014-03-27]
 * @since [产品/模块版本]
 */
public interface ConditionDao extends IGenericDao<Condition, String>{

	Page<Condition> list(int start, int limit, Condition queryCondition);

	void delete(List<String> lstIds);

	void enable(List<String> lstIds);

	void disable(List<String> lstIds);

	List<Map<String, Object>> listConditionInfoByPlanIds(List<String> planIds);
	
}
