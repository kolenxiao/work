/*
 * 文件名称：ConditionService.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：909194
 * 创建时间：2014-03-27
 *
 */
package com.coship.sdp.sce.dp.plan.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.coship.sdp.sce.dp.plan.entity.Condition;
import com.coship.sdp.utils.Page;

/**
 * 应用管理服务接口
 * @author 909194
 * @version [版本号, 2014-03-27]
 * @since [产品/模块版本]
 */
public interface ConditionService extends Serializable{

    /**
     * 分页查询,获取应用信息列表
     * @param start 起始页
     * @param limit 每页条数
     * @param queryCondition 条件参数
     * @return Page<DpAppInfo> 条件分页列表
     * @exception throws [违例类型] [违例说明]
     */
	public Page<Condition> list(int start, int limit, Condition queryCondition)throws Exception;

	public void delete(List<String> lstIds);

	public void enable(List<String> lstIds);

	public void disable(List<String> lstIds);

	public Condition findCondition(String strId);

	public void save(Condition condition);
	
	public void update(Condition condition);

	public List<Map<String, Object>> listConditionInfoByPlanIds(List<String> planIds);

}
