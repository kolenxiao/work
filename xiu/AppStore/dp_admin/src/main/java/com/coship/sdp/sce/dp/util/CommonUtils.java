package com.coship.sdp.sce.dp.util;

import java.util.UUID;

/**
 * 存放一些公共的方法类
 * @author 907632
 * @since v01.02.06
 */
public class CommonUtils
{	
	/**
	 * @return
	 * 返回UUID,全球唯一标识码
	 * @since v01.02.06
	 */
	public static String getUniqueKey()
	{
		String key = UUID.randomUUID().toString();
		return key;
	}
	
}
