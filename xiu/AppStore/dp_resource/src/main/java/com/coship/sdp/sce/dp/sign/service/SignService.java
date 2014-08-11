package com.coship.sdp.sce.dp.sign.service;

import java.io.Serializable;

import com.coship.sdp.sce.dp.ap.entity.DpAppInfo;
import com.coship.sdp.sce.dp.certificate.entity.AppCertificate;
import com.coship.sdp.sce.dp.sign.entity.AppSign;
import com.coship.sdp.utils.Page;

/**
 * 应用签名信息服务层接口.
 * @author wangchenbo/906055
 * @version [版本号, 2012-9-22]
 * @since [产品/模块版本]
 */
public interface SignService extends Serializable
{

    /**
     * 保存应用签名信息.
     * 
     * @param sign 应用签名信息对象
     * @throws Exception [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public void saveSign(AppSign sign) throws Exception;

    /**
     * 更新应用签名信息.
     * 
     * @param sign 应用签名信息对象
     * @throws Exception [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public void updateSign(AppSign sign) throws Exception;

    /**
     * 刪除应用签名信息.
     * 
     * @param sign 应用签名信息对象
     * @throws Exception [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public void deleteSign(AppSign sign) throws Exception;

    /**
     * 分页查询应用签名信息.
     * 
     * @param page 分页对象
     * @param hql 查询语句
     * @param objects 参数数组
     * @throws Exception [参数说明]
     * @return Page<AppSign> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public Page<AppSign> pageAppSign(Page<AppSign> page, String hql,
            Object[] objects) throws Exception;

    /**
     * 根据应用id获得签名证书、签名私钥
     * 
     * @param dpAppInfo 应用对象
     * @throws Exception
     * @return AppSign 应用签名信息
     * @exception throws [违例类型] [违例说明]
     */
    public AppSign findSignByAppId(DpAppInfo dpAppInfo) throws Exception;

    /**
     * 根据应用id删除签名信息
     * 
     * @param dpAppInfo 应用对象
     * @throws Exception
     * @return AppSign 应用签名信息
     * @exception throws [违例类型] [违例说明]
     */
    public void deltelSignByAppId(DpAppInfo dpAppInfo) throws Exception;

    /**
     * 根据证书id查询应用签名信息数量.
     * 
     * @param appCertificate 证书对象
     * @throws Exception [参数说明]
     * @return void
     * @exception throws [违例类型] [违例说明]
     */
    public int countSignByCertId(AppCertificate appCertificate)
            throws Exception;
}
