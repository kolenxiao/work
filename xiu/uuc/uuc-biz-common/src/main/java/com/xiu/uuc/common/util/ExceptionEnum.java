package com.xiu.uuc.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 异常枚举类
 * 
 * @ClassName: ExceptionEnum
 * @Description:
 * @author xiaoyingping
 * @date 2011-9-16 上午09:33:24
 * 
 */
public class ExceptionEnum {

	public enum Busi {

		LOGON_NAME_EXIST("B001", "用户登录名已被注册过"),
		REGISTER_USER_IP_MAX("B002", "同一IP地址注册太过频繁"),
		USER_NOT_EXIST("B003", "用户不存在"),
		PARTNER_USER_EXIST("B004", "联盟用户已经存在"),
		FORBID_USER_STATE("B005", "用户状态已禁用");

		private final String key;
		private final String value;

		private Busi(String key, String value) {
			this.key = key;
			this.value = value;
		}

		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

		public static Map<String, String> getList() {
			Map<String, String> map = new HashMap<String, String>();
			for (Busi e : Busi.values()) {
				map.put(String.valueOf(e.getKey()), e.getValue());
			}
			return map;
		}
	}

}
