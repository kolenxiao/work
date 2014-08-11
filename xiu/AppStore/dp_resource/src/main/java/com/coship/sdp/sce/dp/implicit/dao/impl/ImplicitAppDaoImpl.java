package com.coship.sdp.sce.dp.implicit.dao.impl;

import org.springframework.stereotype.Repository;

import com.coship.sdp.access.dao.impl.GenericDaoImpl;
import com.coship.sdp.sce.dp.implicit.dao.ImplicitAppDao;
import com.coship.sdp.sce.dp.implicit.entity.ImplicitApp;

/**
 * <功能描述>
 * @author zhengxinlian/906976
 * @version [版本号, 2013-02-25]
 * @since [产品/模块版本]
 */
@Repository("implicitAppDao")
public class ImplicitAppDaoImpl extends GenericDaoImpl<ImplicitApp, String> implements
		ImplicitAppDao {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

}
