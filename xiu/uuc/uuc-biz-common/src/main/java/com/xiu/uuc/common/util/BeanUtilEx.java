package com.xiu.uuc.common.util;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.apache.commons.beanutils.converters.SqlTimeConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;

/**
 * 对BeanUtils.copyProperties(target, source)方法的封装
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
		ConvertUtils.register(new IntegerConverter(null), Integer.class);
		ConvertUtils.register(new LongConverter(null), Long.class);
		ConvertUtils.register(new SqlDateConverter(null), java.sql.Date.class);
		ConvertUtils.register(new SqlTimestampConverter(null), java.sql.Timestamp.class);
		ConvertUtils.register(new SqlTimeConverter(null), java.sql.Time.class);
		ConvertUtils.register(new SqlTimeConverter(null), java.sql.Time.class);
	}

	public static void copyProperties(Object target, Object source) throws IllegalAccessException, InvocationTargetException{
		new BeanUtilEx();
		BeanUtils.copyProperties(target, source);
	}

}
