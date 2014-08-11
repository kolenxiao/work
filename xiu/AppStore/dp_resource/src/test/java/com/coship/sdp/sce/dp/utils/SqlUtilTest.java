package com.coship.sdp.sce.dp.utils;

import junit.framework.Assert;

import org.junit.Test;
/**
 * 测试sql语句过滤.
 * @author 906055
 *
 */
public class SqlUtilTest {

	/**
	 * 测试sql语句过滤.
	 */
	@Test
	public final void testEscapeSQLLike() {
		new SqlUtil();
		Assert.assertEquals("/_/%?*", SqlUtil.escapeSQLLike("_%?*"));
	}

}
