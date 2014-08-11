package com.coship.sdp.sce.dp.recommend.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.coship.sdp.access.dao.impl.GenericDaoImpl;
import com.coship.sdp.sce.dp.recommend.dao.AppRecommendPanelDao;
import com.coship.sdp.sce.dp.recommend.entity.AppRecommendPanel;
import com.coship.sdp.utils.Page;

@Repository("appRecommendPanelDao")
public class AppRecommendPanelDaoImpl extends
        GenericDaoImpl<AppRecommendPanel, String> implements
        AppRecommendPanelDao
{
    private static final long serialVersionUID = 7511457859095816776L;

    public Page<AppRecommendPanel> queryAppRecommendPanelList(
            Page<AppRecommendPanel> page, Criterion[] criterions,Order order)
    {
        Criteria c = createCriteria(criterions);

        if (order == null)
        {
            order = Order.asc("sortNum");
        }

        c.addOrder(order);//排序

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

    @SuppressWarnings("unchecked")
    @Override
    public List<AppRecommendPanel> getAllEnabledPanelList() {
        String hql = "select p from AppRecommendPanel p where p.status=1 ";
        return this.getSession().createQuery(hql).list();
    }
}
