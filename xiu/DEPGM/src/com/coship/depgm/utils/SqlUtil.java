package com.coship.depgm.utils;

import org.apache.commons.lang.StringUtils;


public class SqlUtil {
	/**
     * 转义like语句中的'_' '%'
     * 将'?'转成sql的'/_'
     * 将'%'转成sql的'/%'
     *
     * 例如搜索?aa*bb?c_d%f将转化成_aa%bb_c/_d/%f
     *
     * @param likeStr
     * @return
     * @author
     */
    public static String escapeSQLLike(String likeStr) {
        String str = StringUtils.replace(likeStr, "_", "/_");
        str = StringUtils.replace(str, "%",    "/%");
        //str = StringUtils.replace(str, "?", "_");
        //str = StringUtils.replace(str, "*", "%");
        return str;
    }
    
    /**
     * 获得like查询的字符串，格式%字符串%.
     * 
     * @param str 字符串     
     * @return String like查询字符串
     */
    public static String getLikeSql(String str) {
        return "%" + escapeSQLLike(str) + "%";
    }

}
