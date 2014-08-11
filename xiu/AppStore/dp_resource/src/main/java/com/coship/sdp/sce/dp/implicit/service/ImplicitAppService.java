package com.coship.sdp.sce.dp.implicit.service;

import java.io.Serializable;
import java.util.List;

import com.coship.sdp.sce.dp.implicit.entity.ImplicitApp;
import com.coship.sdp.utils.Page;

public interface ImplicitAppService extends Serializable {

	/**
	 * 根据条件获取隐式应用列表
	 */
	Page<ImplicitApp> getImplicitAppList(Page<ImplicitApp> page, ImplicitApp implicitApp);

	/**
	 * 根据ID查找隐式应用对象
	 */
	ImplicitApp findImplicitAppById(String id);

	 /**
     * 保存隐式应用信息.
     * @param entity 应用信息对象
     * @throws Exception [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    void saveImplicitApp(ImplicitApp entity) throws Exception;

	 /**
    * 更新隐式应用信息.
    * @param entity 应用信息对象
    * @throws Exception [参数说明]
    * @return void [返回类型说明]
    * @exception throws [违例类型] [违例说明]
    */
   void updateImplicitApp(ImplicitApp entity) throws Exception;

    /**
	 * 删除隐式应用对象
	 * @throws Exception [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
	 */
   void deleteImplicitApp(ImplicitApp entity) throws Exception;

	 /**
    * 隐式应用的启用和禁用操作.
    * @param entity 应用信息对象
    * @throws Exception [参数说明]
    * @return void [返回类型说明]
    * @exception throws [违例类型] [违例说明]
    */
   void onOrOffImplicitApp(ImplicitApp entity) throws Exception;

   /**
    * 将对应包名的隐式应用的状态置为停用
    * @param packageName 隐式应用包名
 * @param teminalNum
    * @throws Exception [参数说明]
    * @return void [返回类型说明]
    * @exception throws [违例类型] [违例说明]
    */
   void offImplicitApp(String packageName, String teminalNum) throws Exception;

   /**
    * 根据包名获取所有的隐式应用列表
    * @param packageName 隐式应用包名
    * @param teminalNum
    * @throws Exception [参数说明]
    * @return void [返回类型说明]
    * @exception throws [违例类型] [违例说明]
    */
   List<ImplicitApp> getImplicitAppListByPackageName(String packageName, String teminalNum) throws Exception;

   /**
    * 获取所有启用的隐式应用
    * @throws Exception [参数说明]
    * @return void [返回类型说明]
    * @exception throws [违例类型] [违例说明]
    */
   List<ImplicitApp> getImplicitAppListByTeminalNumAndOsVersion
   		(String teminalNum, int osVersion) throws Exception;

   /**
    * 根据包名和存储路径判断隐式应用是否存储
    * @throws Exception [参数说明]
    * @return boolean true:存在隐式应用记录；false：不存在的隐式应用记录
    * @exception throws [违例类型] [违例说明]
    */
   boolean isApkExistByPackageNameAndSavePath(ImplicitApp impApp) throws Exception;

   boolean isApkPackageExist(String packageName, String teminalNum);

   /**
    * 根据包名和存储路径判断隐式应用是否存储
    * @param packageName 包名
    * @param impType 隐式类型
    * @throws Exception [参数说明]
    * @return boolean true:存在隐式应用记录；false：不存在隐式应用记录
    * @exception throws [违例类型] [违例说明]
    */
   boolean isApkExistByPackageAndImpType(String packageName, String impType, String id);

   List<ImplicitApp> getAllImplicitAppList();
}
