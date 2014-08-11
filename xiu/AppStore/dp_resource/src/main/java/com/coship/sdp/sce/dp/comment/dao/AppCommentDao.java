/*
 * 文件名称：AppCommentDao.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-9-7
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.comment.dao;

import org.hibernate.Session;
import org.hibernate.type.Type;

import com.coship.sdp.access.dao.IGenericDao;
import com.coship.sdp.sce.dp.comment.entity.AppCommentInfo;
import com.coship.sdp.utils.Page;

/**
 * 评论信息数据访问接口
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-9-7]
 * @since [产品/模块版本]
 */
public interface AppCommentDao extends IGenericDao<AppCommentInfo, String>
{

    /**
     * <功能描述>
     * @param page
     * @param objects [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    void queryPageBySql(Page<AppCommentInfo> page, String sql,
            Object[] objects, Type[] types);

    Session creatSqlSession();
}
