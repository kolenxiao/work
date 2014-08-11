package com.coship.appstore.service.common;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

import com.coship.sdp.utils.Debug;


public final class BeanUtils
{
	private static BeanUtils benUtils = new BeanUtils();
	
	private BeanUtils(){
		
	}
	
	public static BeanUtils getNewInstance()
	{

		if (null == benUtils)
		{
			benUtils = new BeanUtils();
		}

		return benUtils;
	}
	

	public <T,E> void setupBeforeInitDispatcher(T action,
			Map<String, E[]> params) throws Exception
	{
		injectField(action,action.getClass(),params);
	}
	
	
	public <T> void invokeMethod(T action, String method, Object[] arg)
			throws Exception
	{
		final Method invokeMethod = action.getClass().getDeclaredMethod(method);
		if (null != invokeMethod)
		{
			invokeMethod.setAccessible(true);
			invokeMethod.invoke(action, arg);
		}
	}

	private <T, E> void injectField(T obj, Class<?> cls,
			Map<String, E[]> params)
	{
		Iterator<Map.Entry<String, E[]>> it = params.entrySet().iterator();
		String key = "";
		E[] values = null;
		while (it.hasNext())
		{
			try
			{
				Map.Entry<String, E[]> param = it.next();
				values = param.getValue();
				key = param.getKey().toString();
				Field field = cls.getDeclaredField(key);

				if (field.getClass().isArray())
				{
					field.setAccessible(true);
                    field.set(obj, values);
				} else
				{
					field.setAccessible(true);
					
					if ("int".equals(field.getType().getName()))
					{
						field.set(obj, Integer.parseInt("" + values[0]));
					} else
					{
						field.set(obj, values[0]);
					}
				}

			} catch (Exception e)
			{

//				try
//				{
//					org.apache.commons.beanutils.BeanUtils.setProperty(obj,key, values[0]);
//				} catch (Exception ex)
//				{
//					Debug.logVerbose("invoke field error with parameter:" + key);
//				}
				Debug.logVerbose("invoke field error with parameter:" + key);
			}
		}
	}
}



