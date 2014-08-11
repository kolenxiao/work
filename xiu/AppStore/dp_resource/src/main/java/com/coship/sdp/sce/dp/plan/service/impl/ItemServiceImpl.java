/*
 * 文件名称：ItemServiceImpl.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：909194
 * 创建时间：2014-04-01
 *
 */
package com.coship.sdp.sce.dp.plan.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coship.sdp.sce.dp.plan.dao.ItemDao;
import com.coship.sdp.sce.dp.plan.entity.Item;
import com.coship.sdp.sce.dp.plan.service.ItemService;
import com.coship.sdp.utils.Page;

/**
 * 应用管理服务实现类
 *
 * @author 909194
 * @version [版本号, 2014-03-27]
 * @since [产品/模块版本]
 */
@Service("itemService")
@Transactional
public class ItemServiceImpl implements ItemService{

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6413047885769106751L;
	
	/**
     * itemDao对象
     */
    @Autowired
    private ItemDao itemDao;

    /**
     * 分页查询,获取应用信息列表
     * @param start 起始页
     * @param limit 每页条数
     * @param queryItem 类项参数
     * @return Page<DpAppInfo> 类项分页列表
     * @exception throws [违例类型] [违例说明]
     */
	@Override
	public Page<Item> list(int start, int limit,Item queryItem) throws Exception{
		return itemDao.list(start, limit,queryItem);
	}

	@Override
	public void delete(List<String> lstIds) {
		itemDao.delete(lstIds);
	}

	@Override
	public void enable(List<String> lstIds) {
		itemDao.enable(lstIds);
		
	}

	@Override
	public void disable(List<String> lstIds) {
		itemDao.disable(lstIds);
	}

	@Override
	public Item findItem(String strId) {
		return itemDao.get(strId);
	}

	@Override
	public void save(Item item) {
		Date objDate = new Date();
		item.setCreateTime(objDate);
		item.setUpdateTime(objDate);
		itemDao.save(item);
	}
	
	@Override
	public void update(Item item) {
		item.setUpdateTime(new Date());
		itemDao.update(item);
	}

}
