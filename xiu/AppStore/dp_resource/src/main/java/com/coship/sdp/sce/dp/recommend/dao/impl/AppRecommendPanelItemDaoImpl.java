/*
 * 文件名称：AppRecommendPanelItemDaoImpl.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：Xiezhengrong/907948
 *
 */
package com.coship.sdp.sce.dp.recommend.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.coship.sdp.access.dao.impl.GenericDaoImpl;
import com.coship.sdp.sce.dp.recommend.dao.AppRecommendPanelItemDao;
import com.coship.sdp.sce.dp.recommend.entity.AppRecommendPanelItem;
import com.coship.sdp.utils.Page;

/**
 * 精选页数据访问接口
 * 
 * @author Xiezhengrong/907948
 */
@Repository("appRecommendPanelItemDao")
public class AppRecommendPanelItemDaoImpl extends GenericDaoImpl<AppRecommendPanelItem, String> implements
		AppRecommendPanelItemDao {
	private static final long serialVersionUID = -8691542518233667066L;

	@Override
	@SuppressWarnings("unchecked")
	public List<AppRecommendPanelItem> getAllAppRecommendPanelItemList() {
		String hql = "select item from AppRecommendPanelItem item left join item.appRecommendPanel p "
				+ "where item.status > 0 and p.status > 0 order by p.sortNum, item.sortNum ";

		Query query = getSession().createQuery(hql);

		List<AppRecommendPanelItem> result = query.list();

		return result;
	}
	
    public Page<AppRecommendPanelItem> getAppRecommendPanelItemListByParam(
            Page<AppRecommendPanelItem> page, Map<String, Object> map){
        StringBuffer hql = new StringBuffer("from AppRecommendPanelItem ap where ap.status=1");
        
        Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
        
        while (iterator.hasNext())
        {
            Entry<String, Object> entry = iterator.next();
            if ("itemName".equals(entry.getKey()))
            {
                hql.append(" and ap.").append(entry.getKey()).append(" like :")
                        .append(entry.getKey());
            }
            else if ("typeValue".equals(entry.getKey()))
            {
                hql.append(" and ap.typeValue").append("=:")
                        .append(entry.getKey());
            }
            else if ("appRecommendPanel".equals(entry.getKey()))
            {
                hql.append(" and ap.appRecommendPanel.id").append("=:")
                        .append(entry.getKey());
            }
            else
            {
                hql.append(" and ap.").append(entry.getKey()).append("=:")
                        .append(entry.getKey());
            }

        }
       
        hql.append(" order by ap.sortNum");//排序
        
        return queryPage(page, hql.toString(), map);
    }

    
    public Page<AppRecommendPanelItem> queryAppRecommendPanelItemList(
            Page<AppRecommendPanelItem> page, Criterion[] criterions, Order order)
    {
        Criteria c = createCriteria(criterions);

        if (order == null)
        {
            order = Order.asc("sortNum");
        }

        c.addOrder(order);// 排序

        if (page.isAutoCount())
        {
            int totalCount = countCriteriaResult(c);
            page.setTotalCount(totalCount);
        }

        setPageParameter(c, page);
        c.setFirstResult(page.getBeginCount());
        c.setMaxResults(page.getPageSize());
        List result = c.list();
        page.setResultList(result);
        return page;
    }

    @Override
    public List<AppRecommendPanelItem> getAppRecommendPanelItemList(String panelId) {
        String hql = "select item from AppRecommendPanelItem item where item.appRecommendPanel.id=? and item.status=1 order by item.sortNum ";
        return this.query(hql, panelId);
    }
}
