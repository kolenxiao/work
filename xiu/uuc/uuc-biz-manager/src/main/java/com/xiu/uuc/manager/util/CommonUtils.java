package com.xiu.uuc.manager.util;

import java.util.Collection;
import java.util.Map;

import org.apache.commons.beanutils.BeanToPropertyValueTransformer;
import org.apache.commons.collections.CollectionUtils;

/**
 * 
 *************************************************************** 
 * <p> 
 * @CLASS :com.xiu.uuc.manager.util.CommonUtils
 * @DESCRIPTION :TODO    
 * @AUTHOR :dick.xiao@xiu.com 
 * @VERSION :v1.0 
 * @DATE :2012-5-31 下午4:50:39             
 * </p>   
 ****************************************************************
 */
public final class CommonUtils {
    @SuppressWarnings("rawtypes")
    public static Collection collectByPropertyName(Collection collection, String propertyName) {
        return CollectionUtils.collect(collection, new BeanToPropertyValueTransformer(propertyName));
    }
    
    public static String map2String(Map<String, String> map){
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> m : map.entrySet()) {
            sb.append(m.getValue());
        }
        return sb.toString();
    }
}
