package com.coship.sdp.sce.dp.utils;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.apache.commons.beanutils.converters.SqlTimeConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;

/**
 * 对BeanUtils.copyProperties(target, source)方法的封装
 * 
 * @ClassName: BeanUtilEx
 * @Description: 解决 copy时封装类型初始化的问题
 * @author xiu
 * @date 2011-8-16 下午08:59:38
 * 
 */
public class BeanUtilEx extends BeanUtils {

	private BeanUtilEx() {

	}

	static {
		ConvertUtils.register(new IntegerConverter(), Integer.class);
		ConvertUtils.register(new LongConverter(), Long.class);
		ConvertUtils.register(new DateConverter(), java.util.Date.class);
		ConvertUtils.register(new SqlDateConverter(), java.sql.Date.class);
		ConvertUtils.register(new SqlTimestampConverter(), java.sql.Timestamp.class);
		ConvertUtils.register(new SqlTimeConverter(), java.sql.Time.class);
		ConvertUtils.register(new SqlTimeConverter(), java.sql.Time.class);
	}

	public static void copyProperties(Object target, Object source)
			throws IllegalAccessException, InvocationTargetException {
		new BeanUtilEx();
		BeanUtils.copyProperties(target, source);
	}

}
