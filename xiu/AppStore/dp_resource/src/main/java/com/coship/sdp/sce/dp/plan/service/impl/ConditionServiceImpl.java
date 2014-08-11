/*
 * 文件名称：ConditionServiceImpl.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：909194
 * 创建时间：2014-03-27
 *
 */
package com.coship.sdp.sce.dp.plan.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coship.sdp.sce.dp.plan.dao.ConditionDao;
import com.coship.sdp.sce.dp.plan.entity.Condition;
import com.coship.sdp.sce.dp.plan.service.ConditionService;
import com.coship.sdp.utils.Page;

/**
 * 应用管理服务实现类
 *
 * @author 909194
 * @version [版本号, 2014-03-27]
 * @since [产品/模块版本]
 */
@Service("conditionService")
@Transactional
public class ConditionServiceImpl implements ConditionService{

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1401795695816478041L;
	
	/**
     * conditionDao对象
     */
    @Autowired
    private ConditionDao conditionDao;

    /**
     * 分页查询,获取应用信息列表
     * @param start 起始页
     * @param limit 每页条数
     * @param queryCondition 条件参数
     * @return Page<DpAppInfo> 条件分页列表
     * @exception throws [违例类型] [违例说明]
     */
	@Override
	public Page<Condition> list(int start, int limit,Condition queryCondition) throws Exception{
		return conditionDao.list(start, limit,queryCondition);
	}

	@Override
	public void delete(List<String> lstIds) {
		conditionDao.delete(lstIds);
	}

	@Override
	public void enable(List<String> lstIds) {
		conditionDao.enable(lstIds);
		
	}

	@Override
	public void disable(List<String> lstIds) {
		conditionDao.disable(lstIds);
	}

	@Override
	public Condition findCondition(String strId) {
		return conditionDao.get(strId);
	}

	@Override
	public void save(Condition condition) {
		Date objDate = new Date();
		condition.setCreateTime(objDate);
		condition.setUpdateTime(objDate);
		conditionDao.save(condition);
	}
	
	@Override
	public void update(Condition condition) {
		condition.setUpdateTime(new Date());
		conditionDao.update(condition);
	}

	@Override
	public List<Map<String, Object>> listConditionInfoByPlanIds(List<String> planIds) {
		return conditionDao.listConditionInfoByPlanIds(planIds);
	}

}
