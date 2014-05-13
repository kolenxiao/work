package com.xiu.uuc.common.util;

import java.util.HashMap;
import java.util.Map;

public class CommonEnum {


    
    /**
     * 业务操作类型
     * @ClassName: BusiOperType
     * @author xiaoyingping
     * @date 2011-8-15 下午02:46:04 
     */
    public enum BusiOperType {
    	
        RegisterUser("UUC101","用户注册"), 
        ACTIVATE_USER("UUC102", "激活用户"), 
        FORBIT_USER("UUC103", "禁用用户"), 
        UNFORBIT_USER("UUC104", "起用用户"), 
        DELETE_USER("UUC105", "删除用户"),
        ModifyUserPassword("UUC106","修改用户密码"), 
        ResetUserPassword("UUC107","重置用户密码"),
        ModifyUserInfo("UUC108","修改用户信息"), 

        ADD_RCV_ADDRESS("UUC109","新增收货地址"), 
        MODIFY_RCV_ADDRESS("UUC110","修改收货地址"), 
        DELETE_RCV_ADDRESS("UUC111","删除收货地址"),
        
        LeadPartnerRegister("UUC112","引导联盟用户注册"),
        ModifyEmailAuthen("UUC113","修改邮箱认证"),
        ModifyMobileAuthen("UUC114","修改手机认证"),
        
        ModifyEbayUserAgreement("UUC115","修改eBay用户协议是否同意标志");

        private final String key;
        private final String value;
        
        private BusiOperType(String key, String value) {
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
            for (BusiOperType e : BusiOperType.values()) {
                map.put(String.valueOf(e.getKey()), e.getValue());
            }
            return map;
        }
    }
	
	
	
	
	
}
