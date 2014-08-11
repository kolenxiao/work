/*
 * 文件名称：SignDao.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：应用签名信息dao层接口
 * 创 建 人：wangchenbo/906055
 * 创建时间：2012-9-22
 * 
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.sign.dao;

import com.coship.sdp.access.dao.IGenericDao;
import com.coship.sdp.sce.dp.sign.entity.AppSign;
/**
 * 应用签名信息dao层接口
 * @author  wangchenbo/906055
 * @version  [版本号, 2012-9-22]
 * @since  [产品/模块版本]
 */
public interface SignDao extends IGenericDao<AppSign, String>
{

}
