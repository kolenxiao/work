/*
 * 文件名称：ItemService.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：909194
 * 创建时间：2014-04-01
 *
 */
package com.coship.sdp.sce.dp.plan.service;

import java.io.Serializable;
import java.util.List;

import com.coship.sdp.sce.dp.plan.entity.Item;
import com.coship.sdp.utils.Page;

/**
 * 应用管理服务接口
 * @author 909194
 * @version [版本号, 2014-04-01]
 * @since [产品/模块版本]
 */
public interface ItemService extends Serializable{

    /**
     * 分页查询,获取应用信息列表
     * @param start 起始页
     * @param limit 每页条数
     * @param queryItem 类项参数
     * @return Page<DpAppInfo> 类项分页列表
     * @exception throws [违例类型] [违例说明]
     */
	public Page<Item> list(int start, int limit, Item queryItem)throws Exception;

	public void delete(List<String> lstIds);

	public void enable(List<String> lstIds);

	public void disable(List<String> lstIds);

	public Item findItem(String strId);

	public void save(Item item);
	
	public void update(Item item);

}
