/*
 * 文件名称：NewsTypeDaoImpl.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：FuJian/906126
 * 创建时间：2011-8-31
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.type.dao.impl;

import org.springframework.stereotype.Repository;

import com.coship.sdp.access.dao.impl.GenericDaoImpl;
import com.coship.sdp.sce.dp.type.dao.DpTypeDao;
import com.coship.sdp.sce.dp.type.entity.DpType;

/**
 * <功能描述>
 * @author  FuJian/906126
 * @version  [版本号, 2011-8-31]
 * @since  [产品/模块版本]
 */
@Repository("dpTypeDao")
public class DpTypeDaoImpl extends GenericDaoImpl<DpType, String> implements DpTypeDao {

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2803752959071806085L;

}
