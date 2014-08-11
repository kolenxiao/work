/*
 * 文件名称：ItemDao.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：909194
 * 创建时间：2014-04-01
 *
 */
package com.coship.sdp.sce.dp.plan.dao;

import java.util.List;

import com.coship.sdp.access.dao.IGenericDao;
import com.coship.sdp.sce.dp.plan.entity.Item;
import com.coship.sdp.utils.Page;

/**
 * 应用数据访问接口
 * @author 909194
 * @version [版本号, 2014-04-01]
 * @since [产品/模块版本]
 */
public interface ItemDao extends IGenericDao<Item, String>{

	Page<Item> list(int start, int limit, Item queryItem);

	void delete(List<String> lstIds);

	void enable(List<String> lstIds);

	void disable(List<String> lstIds);
	
}
