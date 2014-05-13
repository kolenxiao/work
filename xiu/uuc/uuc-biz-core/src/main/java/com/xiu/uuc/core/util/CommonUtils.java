package com.xiu.uuc.core.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.BeanToPropertyValueTransformer;
import org.apache.commons.collections.CollectionUtils;
import com.xiu.uuc.dal.po.AbnormalLogPO;

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
   
    @SuppressWarnings("unchecked")
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
    
    @SuppressWarnings("unchecked")
	public static void main(String[] args) {
    	List<AbnormalLogPO> abnormalLogPOList = new ArrayList<AbnormalLogPO>();
    	AbnormalLogPO AbnormalLogPO1= new AbnormalLogPO();
    	AbnormalLogPO1.setAbnormalId(1L);
    	AbnormalLogPO AbnormalLogPO2= new AbnormalLogPO();
    	AbnormalLogPO2.setAbnormalId(2L);
    	abnormalLogPOList.add(AbnormalLogPO1);
    	abnormalLogPOList.add(AbnormalLogPO2);
    	Collection<Long> abnormalIdCoction = (Collection<Long>)CommonUtils.collectByPropertyName(abnormalLogPOList, "abnormalId");
    	Long[] abnormalIdArray = (abnormalIdCoction!=null && abnormalIdCoction.size()>0 )
    	                         ? abnormalIdCoction.toArray(new Long[abnormalIdCoction.size()]):new Long[0];
		@SuppressWarnings("unused")
		List<Long> abnormalIdList = Arrays.asList(abnormalIdArray);
	}
}
