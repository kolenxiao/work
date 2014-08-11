package com.coship.appstore.iuc;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coship.appstore.service.common.AppConstants;
import com.coship.appstore.service.common.Global;
import com.coship.mse.opcode.OpCodeMessage;
import com.coship.mse.opcode.OpCodeSender;
import com.coship.mse.opcode.OpCodeXStreamConverter;
import com.coship.sdp.sce.dp.system.entity.SystemParam;
import com.thoughtworks.xstream.XStream;

/**
 * DVB鉴权工具类.
 * 
 * @author xiezhengrong/907948
 * 
 * @since 2014年6月21日
 */
public class DvbAuthUtils {
    private static final Logger logger = LoggerFactory.getLogger(DvbAuthUtils.class);
    // iuc中鉴权定义的资源类型，3表示应用；4表示游戏.
    private static final int iucResourceType = 3;
    // iuc中鉴权定义的资源类型id，我们这边暂时不处理按单个应用收费的情况。该参数必填，所以传入一个无意义的值。
    private static final String resourceTypeId = "0";

	
	/**
	 * 调用IUC接口对productCode进行鉴权验证
	 * @param userCode
	 * @param productCodeList
	 * @return
	 */
	public static boolean dvbAuthority(String userCode, String cityCode, List<String> productCodeList){
		if(null == productCodeList){
			productCodeList = new ArrayList<String>();
		}
		
		String[] productCodes = new String[productCodeList.size()];
        return dvbAuthority(userCode, cityCode, productCodeList.toArray(productCodes));
	}

    /**
     * DVB鉴权.
     * 
     * @param userCode
     * @param productCodeList
     * @param result
     * @param productCodes
     * @return
     */
    public static boolean dvbAuthority(String userCode, String cityCode, String[] productCodes) {
        boolean needDvbAuth = isNeedDvbAuth(cityCode);
        //不是辽宁阜新的盒子，不需要鉴权.
        if(!needDvbAuth) {
        	return true;
        }
        //辽宁阜新区域的盒子，如果没有传userCode，则无权限访问.
        if(userCode == null || "".equals(userCode)) {
        	return false;
        }
        //未设置产品编码.表示为免费类产品，不需要鉴权.
        if(productCodes == null || productCodes.length == 0) {
        	return true;
        }
        
        boolean result = false;
        
        ProductOption option = new ProductOption();
        option.setResourceType(iucResourceType);
        option.setResourceTypeId(resourceTypeId);
        option.setUserCode(userCode);
        option.setProductCodes(productCodes);

        OpCodeSender sender = new OpCodeSender(new OpCodeXStreamConverter("IUC_QUERY_AUTHORITY") {
            protected void send(XStream xs) {
                xs.alias("UserOption", ProductOption.class);
                xs.alias("productCode", String.class);
            }
            protected void response(XStream xs) {
                xs.alias("AuthResultOption", AuthResultOption.class);
            }
        }, true);

        try {
            OpCodeMessage op = sender.send("IUC", option);
            String returnCode = op.getReturnCode();
            if ("0".equals(returnCode)) { // 请求成功.
                AuthResultOption resultOption = (AuthResultOption) op.getData();
                result = resultOption.getSuccess();
            }
        } catch (Exception e) {
            logger.error("调用IUC鉴权接口失败，参数：userCode:[{}], productCodes: [{}]", new Object[] { userCode, productCodes }, e);
            result = false;
        }
        return result;
    }
    
    
	
	/**
	 * 判断cityCode是否需要进行dvb鉴权
	 * @param cityCode
	 * @return
	 */
	public static boolean isNeedDvbAuth(String cityCode){
		boolean isNeed = false;//默认不需要鉴权
		if(StringUtils.isNotBlank(cityCode)){
			SystemParam cityCodeParam = Global.systemParamMap.get(AppConstants.DVB_AUTH);
			if (null != cityCodeParam && StringUtils.isNotBlank(cityCodeParam.getValue())) {
				if(cityCodeParam.getValueList().contains(cityCode)){
					isNeed = true;
				}
			}
		}

		return isNeed;
	}
    
}
