/*
 * 文件名称：DpAppInfoService.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：FuJian/906126
 * 创建时间：2011-9-5
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.ap.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.coship.sdp.sce.dp.ap.entity.AppStatisticsInfo;
import com.coship.sdp.sce.dp.ap.entity.DpAppInfo;
import com.coship.sdp.sce.dp.ap.entity.DpStaff;
import com.coship.sdp.sce.dp.audit.entity.DpAuditRecord;
import com.coship.sdp.sce.dp.recommend.entity.AppRecommend;
import com.coship.sdp.sce.dp.subject.entity.AppSubjectType;
import com.coship.sdp.sce.dp.type.entity.DpType;
import com.coship.sdp.utils.Page;

/**
 * 应用管理服务接口
 * @author FuJian/906126
 * @version [版本号, 2011-9-5]
 * @since [产品/模块版本]
 */
public interface DpAppInfoService extends Serializable
{
    /**
     * 保存应用信息.
     * @param entity 应用信息对象
     * @throws Exception [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public void saveAppInfo(DpAppInfo entity) throws Exception;

    /**
     * 更新应用信息.
     * @param entity
     * @throws Exception [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public void updateAppInfo(DpAppInfo entity) throws Exception;

    /**
     * 删除应用信息和应用的审核记录数据
     * @param entity
     * @throws Exception [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public void deleteAppInfo(DpAppInfo entity) throws Exception;

    /**
     * 通过ID查找应用信息
     * @param id 应用的id
     * @return
     * @throws Exception [参数说明]
     * @return DpAppInfo [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public DpAppInfo findAppInfo(String id) throws Exception;

    /**
     * 通过应用ID和用户id查找应用信息
     *
     * @param appId 应用id
     * @param userId 用户id
     * @return
     * @throws Exception [参数说明]
     * @return DpAppInfo [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public DpAppInfo findAppInfo(String appId, String userId) throws Exception;

    /**
     * 通过应用ID和用户id查找应用信息
     *
     * @param appId 应用id
     * @param userId 用户id
     * @return
     * @throws Exception [参数说明]
     * @return DpAppInfo [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public DpAppInfo findAdminAppInfo(String appId, String userId) throws Exception;

    /**
     * 分页查询,获取应用信息列表
     * @param page 分页对象
     * @param hql 查询字符串
     * @param values hql中的参数
     * @return 应用信息分页列表
     * @throws Exception [参数说明]
     * @return Page<DpAppInfo> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public Page<DpAppInfo> listAppInfo(Page<DpAppInfo> page, String hql,
            Object[] values) throws Exception;

    /**
     * 条件分页查询,获取应用信息列表
     * @param page
     * @param appInfo 应用查询条件对象
     * @param name 应用关联的开发者或者管理员账号
     * @param flag 0：开发者和管理员都查；1：只查管理员；2：只查开发者
     * @return
     * @throws Exception [参数说明]
     * @return Page<DpAppInfo> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public Page<DpAppInfo> searchAppInfo(Page<DpAppInfo> page,
            DpAppInfo appInfo, String name, int flag) throws Exception;

    /**
     * 条件分页查询,获取上下架操作的应用信息列表
     * @param page
     * @param appInfo 应用查询条件对象
     * @param dpStaff 应用关联的开发者对象
     * @return
     * @throws Exception [参数说明]
     * @return Page<DpAppInfo> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public Page<DpAppInfo> searchOnOrOffLineOptAppInfo(Page<DpAppInfo> page,
            DpAppInfo appInfo, DpStaff dpStaff) throws Exception;

    /**
     * 审核应用信息
     * @param appInfo 应用信息对象
     * @param auditRecord 审核记录对象
     * @throws Exception [参数说明]
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public void auditAppInfo(DpAppInfo appInfo, DpAuditRecord auditRecord)
            throws Exception;

    /**
     * 获取所有待上架的应用
     * @return
     * @throws Exception [参数说明]
     * @return List<DpAppInfo> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public List<DpAppInfo> listAllPreOnlineApp() throws Exception;

    /**
     * 根据应用名字
     * @param appName 应用名称
     * @return
     * @throws Exception [参数说明]
     * @return List<DpAppInfo> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public List<DpAppInfo> findByAppName(String appName) throws Exception;
    
    /**
     * 通过应用包名得到应用
     * @param packageName
     * @return
     */
    public DpAppInfo findUniqueByPackageName(String packageName);

    /**
     * 根据hql查询应用信息列表
     * @param hql
     * @return
     * @throws Exception [参数说明]
     * @return List<DpAppInfo> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public List<DpAppInfo> listByHQL(String hql) throws Exception;

    /**
     * 根据hql查询应用信息列表
     *
     * @param hql 查询语句
     * @param objects 参数值
     * @throws Exception [参数说明]
     * @return List<DpAppInfo> 应用信息
     * @exception throws [违例类型] [违例说明]
     */
    public List<DpAppInfo> listByHQL(String hql, Object[] objects)
            throws Exception;

    /**
     * 上下架应用服务类
     * @param appInfo 应用信息对象
     * @return void [返回类型说明]
     * @throws Exception
     * @exception throws [违例类型] [违例说明]
     */
    public void onOrOffLineAppInfo(DpAppInfo appInfo) throws Exception;

    /**
     * 分页条件查询应用的统计信息
     * @param page 分页数据对象
     * @param appStatQuery 查询条件封装对象
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public void searchAppStatisticList(Page<AppStatisticsInfo> page,
            AppStatisticsInfo appStatQuery);

    /**
     * 根据应用id获取应用的统计信息
     * @param appId 应用id
     * @return [参数说明]
     * @return AppStatisticsInfo [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public AppStatisticsInfo searchAppStatisticInfoByAppId(String appId);

    /**
     * 获取唯一对象
     * @param getUpdateappListHql
     * @return [参数说明]
     * @return DpAppInfo [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    DpAppInfo uniqueByHQL(String getUpdateappListHql, Object[] param)
            throws Exception;

    /**
     * 查询已推荐的应用
     * @param page
     * @param queryAppInfo
     * @param queryDpStaff [参数说明]
     * @return void [返回类型说明]
     * @throws Exception
     * @exception throws [违例类型] [违例说明]
     */
    public void searchRecomentAppList(Page<DpAppInfo> page,
            DpAppInfo queryAppInfo) throws Exception;

    /**
     * <功能描述>
     * @param page
     * @param queryAppInfo [参数说明]
     * @return void [返回类型说明]
     * @throws Exception
     * @exception throws [违例类型] [违例说明]
     */
    public void searchPreRecomentAppList(Page<DpAppInfo> page,
            DpAppInfo queryAppInfo) throws Exception;

    /**
     * 根据包名查询应用信息表列表
     * @param pacName [参数说明]
     * @param appName [参数说明]
     * @return void [返回类型说明]
     * @throws Exception
     * @exception throws [违例类型] [违例说明]
     */
    public List<DpAppInfo> searchAppListByPacNameAndAppName(String pacName, String appName) throws Exception;

    /**
     * 根据专题Id和应用名称查询应用信息列表
     * @param subjectType [专题对象]
     * @param appInfo 应用信息对象
     * @param page 应用信息分页对象
     * @return page 应用信息分页对象
     * @throws Exception
     * @exception throws [Exception] [查询数据失败]
     */
    public Page<DpAppInfo> searchAppListBySubjectTypeAndApp(Page<DpAppInfo> page,
            AppSubjectType subjectType, DpAppInfo appInfo) throws Exception;

    /**
     * 根据专题Id和应用名称查询应用信息列表
     * @param subjectType [专题对象]
     * @param appInfo 应用信息对象
     * @param page 应用信息分页对象
     * @return page 应用信息分页对象
     * @throws Exception
     * @exception throws [Exception] [查询数据失败]
     */
    public Page<DpAppInfo> searchAppListByAppRecommendAndApp(Page<DpAppInfo> page,
            AppRecommend appRec, DpAppInfo appInfo) throws Exception;

    /**
     * 根据分类Id和应用名称查询应用信息列表
     * @param subjectType [专题对象]
     * @param appInfo 应用信息对象
     * @param page 应用信息分页对象
     * @return page 应用信息分页对象
     * @throws Exception
     * @exception throws [Exception] [查询数据失败]
     */
    public Page<DpAppInfo> searchAppListByAppTypeAndApp(Page<DpAppInfo> page,
            DpType type, DpAppInfo appInfo) throws Exception;

    /**
     * 根据名称查询开发者门户应用商店显示的最新、最热、收费、免费等应用信息
     * @param flag 1:免费应用；2：收费应用；3：最热应用；4：最新应用；
     * @param appInfo 应用信息对象
     * @param page 应用信息分页对象
     * @return page 应用信息分页对象
     * @throws Exception
     * @exception throws [Exception] [查询数据失败]
     */
    public Page<DpAppInfo> searchAppList(Page<DpAppInfo> page,int flag,
            DpAppInfo appInfo) throws Exception;
    /**
     * 根据hql、开始页数、每页最大记录数查询应用信息列表
     * @param hql
     * @return
     * @throws Exception [参数说明]
     * @return List<DpAppInfo> [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public List<DpAppInfo> listMaxResultByHQL(String hql, Object[] objects,int start,int max) throws Exception;

    /**
     * 根据应用Id获取下载次数
     * @param appId 应用的Id
     * @return int 下载次数
     * @throws Exception [参数说明]
     */
    public int getDownloadCountByAppId(String appId) throws Exception;

    /**
     * 根据包名获取最新应用的类型名和开发者
     */
    public Map<String, String> getStaffAndTypeByPacName(String packageName) throws Exception;
    
    
    /**
     * <功能描述>
     * @param page
     * @param queryAppInfo [参数说明]
     * @return void [返回类型说明]
     * @throws Exception
     * @exception throws [违例类型] [违例说明]
     */
    public void searchPreRecomentAppTypeList(Page<DpAppInfo> page,
            DpAppInfo queryAppInfo) throws Exception;


	List<DpAppInfo> listByHQL(String hql, List<String> pkgNames)
			throws Exception;
	
	/**
	 * 获取所有的APP和GAME分类下的应用(状态为非1006)
	 * @return
	 */
	public Map<String, DpAppInfo> searchAppAndGameMap();
	
	/**
	 * 获取所有的APP和GAME分类下的应用(状态为1004)
	 * @return
	 */
	public Map<String, DpAppInfo> searchAppAndGameOnlineMap();

}
