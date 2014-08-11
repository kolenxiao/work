/*
 * 文件名称：DpStaffServiceImpl.java.
 * 版    权：Shenzhen Coship Electronics Co.,
 * Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：luhuanwen/905323
 * 创建时间：2011-9-21
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.ap.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coship.sdp.exception.ServiceException;
import com.coship.sdp.sce.dp.ap.dao.DpAppInfoDao;
import com.coship.sdp.sce.dp.ap.dao.DpStaffDao;
import com.coship.sdp.sce.dp.ap.entity.DpStaff;
import com.coship.sdp.sce.dp.ap.service.DpStaffService;
import com.coship.sdp.sce.dp.audit.entity.DpAuditRecord;
import com.coship.sdp.sce.dp.audit.service.DpAuditRecordService;
import com.coship.sdp.sce.dp.common.Constants;
import com.coship.sdp.sce.dp.utils.MailSenderInfo;
import com.coship.sdp.sce.dp.utils.SimpleMailSenderUtil;
import com.coship.sdp.sce.dp.utils.SqlUtil;
import com.coship.sdp.utils.DateUtil;
import com.coship.sdp.utils.Debug;
import com.coship.sdp.utils.Page;

/**
 * ap信息服务层实现类.
 * @author 905735
 *
 */
@Service("dpStaffService")
@Transactional
public class DpStaffServiceImpl implements DpStaffService
{
    /**
     * <注释内容>.
     */
    private static final long serialVersionUID = 8544773314944756948L;

    /**
     * 查询所有的开发者的标识
     */
    private static int ALL_SEARCH_FLAG = 0;

    /**
     * 查询待审核的开发者的标识
     */
    private static int WAIT_SEARCH_FLAG = 1;

    /**
     * dpStaffDao接口.
     */
    @Autowired
    private DpStaffDao dpStaffDao;

    /**
     * dpAppInfoDao接口.
     */
    @Autowired
    private DpAppInfoDao dpAppInfoDao;

    /**
     * dpAppInfoDao接口.
     */
    @Autowired
    private DpAuditRecordService dpAuditRecordService;

    /**
     * 查询全部的hql语句.
     */
    private static final String QUERY_HQL = "from DpStaff dp where 1=1 ";

    /**
     * 查询绑定应用hql语句.
     */
    private static final String FIND_APPINFO_COUNT_HQL = " from DpAppInfo da where da.dpStaffId ='";

    /**
     * 查询绑定应用hql语句.
     */
    private static final String FIND_APPINFO_COUNT_BY_ADMIN = " from DpAppInfo da where da.userId ='";

    /**
     * 根据名称查询hql语句.
     */
    private static final String FIND_UNIQUE_BY_NAME_HQL = "from DpStaff ds where ds.userName =?";

    /**
     * 类变量.
     */
    private static final String MODULE = DpStaffServiceImpl.class.getName();

    /**
     * 修改状态.
     * @param staffStatus 状态 * @param id 查询的id
     * @throws Exception 对象
     */
    @Override
    public void updateStaffStatus(String staffStatus, String id)
            throws Exception
    {
        String hql = "update DpStaff set staffStatus=" + staffStatus
                + " where id='" + id + "'";
        this.dpStaffDao.executeUpdate(hql, new Object[0]);
    }

    /**
     * 更新.
     * @param dpStaff 对象
     * @throws Exception 对象
     */
    @Override
    public void updateDpStaff(DpStaff dpStaff) throws Exception
    {
        dpStaffDao.saveOrUpdate(dpStaff);
    }

    /**
     * 查询.
     */
    @Override
    public DpStaff findDpStaff(String id) throws Exception
    {
        return dpStaffDao.get(id);
    }

    /**
     * 保存新增实体对象.
     * @param dpStaff
     * @throws Exception
     */
    @Override
    public void saveDpStaff(DpStaff dpStaff) throws Exception
    {
        // 判断注册用户名是否唯一
        uniqueUspServName(dpStaff);

        // 判断用户注册邮箱地址是否唯一
        uniqueRegisterEmail(dpStaff);

        dpStaffDao.save(dpStaff);
    }

    /**
     * Description :用户名称是否唯一.
     * @param entity 实体对象
     * @return DpStaff 对象
     */
    private DpStaff uniqueUspServName(DpStaff entity)
    {
        try
        {
            DpStaff ds = (DpStaff) dpStaffDao.findUnique(
                    FIND_UNIQUE_BY_NAME_HQL, entity.getUserName());

            if (ds != null && ds.getId() != null)
            {
                throw new ServiceException(
                        Constants.WARNING_USERNAME_ALREADY_EXIST);
            }
            return ds;
        }
        catch (Exception e)
        {
            Debug.logWarning(e, "userName[" + entity.getUserName()
                    + "] is already exist.", MODULE);
            throw new ServiceException(Constants.WARNING_USERNAME_ALREADY_EXIST);
        }
    }

    /**
     * 判断用户注册邮箱是否唯一
     * @param entity 实体对象
     * @return DpStaff 对象
     */
    private DpStaff uniqueRegisterEmail(DpStaff entity)
    {
        try
        {
            String hql = "from DpStaff dp where dp.email=?";
            DpStaff ds = (DpStaff) dpStaffDao
                    .findUnique(hql, entity.getEmail());

            if (ds != null && ds.getId() != null)
            {
                throw new ServiceException(
                        Constants.WARNING_USERNAME_ALREADY_EXIST);
            }
            return ds;
        }
        catch (Exception e)
        {
            Debug.logWarning(e, "email[" + entity.getEmail()
                    + "] is already exist.", MODULE);
            throw new ServiceException("注册邮箱有重复");
        }
    }

    /**
     * 删除.
     * @param dpStaff ap对象
     * @throws Exception 异常
     */
    @Override
    public void deleteDpStaff(DpStaff dpStaff) throws Exception
    {
        dpStaffDao.delete(dpStaff);
    }

    /**
     * 分页查询.
     * @param page 分页对象
     * @param dpStaff 实体对象
     * @param flag 标识
     * @return Page<DpStaff> 分页列表
     * @throws Exception 异常
     */
    @Override
    public Page<DpStaff> searchDpStaff(Page<DpStaff> page, DpStaff dpStaff,
            int flag) throws Exception
    {
        Map<String, Object> map = new HashMap<String, Object>();
        return dpStaffDao.queryPage(page, buildHQL(dpStaff, flag, map), map);

    }

    /**
     * 拼装hql语句.
     * @param dpStaff ap对象
     * @param map map对象
     * @param flag 是审核列表还是所有列表
     * @return String hql 语句
     */
    private String buildHQL(DpStaff dpStaff, int flag, Map<String, Object> map)
    {
        StringBuilder hql = new StringBuilder(QUERY_HQL);
        // 判断查询分类（0是查询所有，1查询待审核）
        if (flag == WAIT_SEARCH_FLAG)
        {
            hql.append(" and dp.staffStatus=" + Constants.AP_AUDIT_TO_WAIT);
        }
        else
        {
            // 去掉草稿状态的用户
            hql.append(" and dp.staffStatus!=" + Constants.AP_DRAFT);
        }
        // 用户名
        if (StringUtils.isNotEmpty(dpStaff.getUserName()))
        {
            hql.append(" and dp.userName like'%"
                    + SqlUtil.escapeSQLLike(dpStaff.getUserName().trim())
                    + "%' escape'/'");
        }
        // 昵称
        if (StringUtils.isNotEmpty(dpStaff.getNickName()))
        {
            hql.append(" and dp.nickName like'%"
                    + SqlUtil.escapeSQLLike(dpStaff.getNickName().trim())
                    + "%' escape'/'");
        }
        // 状态
        if (StringUtils.isNotEmpty(dpStaff.getStaffStatus()))
        {
            hql.append(" and dp.staffStatus=" + dpStaff.getStaffStatus());
        }

        // 所在地
        if (StringUtils.isNotEmpty(dpStaff.getAddress()))
        {
            hql.append(" and dp.localAdd like'%"
                    + SqlUtil.escapeSQLLike(dpStaff.getAddress().trim())
                    + "%' escape'/'");
        }

        // 创建时间段
        if (StringUtils.isNotEmpty(dpStaff.getBeginDpStaffCtime())
                || StringUtils.isNotEmpty(dpStaff.getEndDpStaffCtime()))
        {
            if (StringUtils.isNotEmpty(dpStaff.getBeginDpStaffCtime())
                    && StringUtils.isEmpty(dpStaff.getEndDpStaffCtime()))
            {

                hql.append(" and dp.beginDpStaffTime >=:beginDpStaffTime");
                map.put("beginDpStaffTime",
                        DateUtil.strToDate(dpStaff.getBeginDpStaffCtime()
                                + " 00:00:00", DateUtil.C_TIME_PATTON_DEFAULT));

            }
            else if (StringUtils.isEmpty(dpStaff.getBeginDpStaffCtime())
                    && StringUtils.isNotEmpty(dpStaff.getEndDpStaffCtime()))
            {
                map.put("endDpStaffTime",
                        DateUtil.strToDate(dpStaff.getEndDpStaffCtime()
                                + " 23:59:59", DateUtil.C_TIME_PATTON_DEFAULT));
            }
            else
            {
                hql.append(" and dp.beginDpStaffTime >= :beginDpStaffTime"
                        + " and dp.beginDpStaffTime <= :endDpStaffTime");
                map.put("beginDpStaffTime",
                        DateUtil.strToDate(dpStaff.getBeginDpStaffCtime()
                                + " 00:00:00", DateUtil.C_TIME_PATTON_DEFAULT));
                map.put("endDpStaffTime",
                        DateUtil.strToDate(dpStaff.getEndDpStaffCtime()
                                + " 23:59:59", DateUtil.C_TIME_PATTON_DEFAULT));
            }
        }
        return hql.toString();

    }

    /**
     * 根据用户名和密码查询.
     * @param userName 用户名
     * @param passWord 密码
     * @throws Exception 异常
     */
    @Override
    public DpStaff findDpStaff(String userName, String passWord)
            throws Exception
    {
        StringBuffer hql = new StringBuffer("from DpStaff where userName = '");
        hql.append(userName);
        hql.append("' and passWord = '");
        hql.append(passWord);
        hql.append("'");
        List<DpStaff> result = dpStaffDao.query(hql.toString());

        if (null != result && 0 < result.size())
        {
            return result.get(0);
        }
        else
        {
            return null;
        }
    }

    /**
     * 根据用户名查询.
     * @param userName 用户名
     * @throws Exception 异常处理
     */
    @Override
    public DpStaff findDpStaffByName(String userName) throws Exception
    {
        String hql = "from DpStaff dp where dp.userName=?";
        List<DpStaff> dpStaffList = dpStaffDao.query(hql, userName);
        if (CollectionUtils.isNotEmpty(dpStaffList))
        {
            return dpStaffList.get(0);
        }
        else
        {
            return null;
        }
    }

    /**
     * 根据注册邮箱查询.
     * @param email 注册邮箱
     * @return List<DpStaff> 列表
     * @throws Exception 异常处理
     */
    @Override
    public List<DpStaff> findDpStaffByEmail(String email) throws Exception
    {
        String hql = "from DpStaff dp where dp.email=?";
        return dpStaffDao.query(hql, email);
    }
    
    /**
     * 根据营业执照查询记录
     * @param bizLicense 营业执照
     * @return List<DpStaff>列表
     * @throws Exception
     */
    @Override
    public List<DpStaff> findDpStaffByBizLicense(String bizLicense) throws Exception
    {
        String hql = "from DpStaff dp where dp.bizLicense=?";
        return dpStaffDao.query(hql, bizLicense);
    }

    /**
     * 根据id组删除ap信息.
     * @param ids id组
     * @return String 被删除的id
     * @throws Exception 异常处理
     */
    @Override
    @Transactional(rollbackFor =
    { ServiceException.class, Exception.class })
    public String deleteDpStaffByIds(String ids) throws Exception
    {
        if (ids == null || "".equals(ids.trim()))
        {
            return null;
        }
        DpStaff entity = null;
        // 切割id数组
        String[] idArray = ids.split(",");
        for (String id : idArray)
        {
            id = id.trim();
            entity = findDpStaff(id.trim());
            // 查询dp账户是否绑定了应用
            String appInfoHQL = FIND_APPINFO_COUNT_HQL + id + "'";
            // 查询管理员是否绑定了应用
            String appInfoHQL1 = FIND_APPINFO_COUNT_BY_ADMIN + id + "'";
            int countApp = dpAppInfoDao.countHqlResult(appInfoHQL);
            int countApp1 = dpAppInfoDao.countHqlResult(appInfoHQL1);
            int total = countApp + countApp1;
            if (total != 0)
            {
                Debug.logWarning("dpStaffId[" + id + "] contain [" + countApp
                        + "]DpAppInfo,don't remove DpStaff ", this.getClass()
                        .getName());
                throw new ServiceException(entity.getUserName());
            }
            // 删除审核记录
            dpAuditRecordService.deleteAuditRecord(id.trim(), "3");
            this.dpStaffDao.delete(entity);

        }
        return ids;
    }

    /**
     * 根据hql查询.
     * @param hql hql语句
     * @return 列表
     * @throws Exception 异常
     */
    @Override
    public List<DpStaff> findByHQL(String hql) throws Exception
    {
        return this.dpStaffDao.query(hql);
    }

    /**
     * 审核开发者信息
     * @param dpStaff 开发者实体对象
     * @param dpAuditRecord 审核记录实体对象
     * @throws Exception 异常类
     * @return void
     * @exception throws [违例类型] [违例说明]
     */
    @Override
    public void auditDpStaffAndSaveRecore(DpStaff dpStaff,
            DpAuditRecord dpAuditRecord) throws Exception
    {
        // 更新审核状态
        String hql = "update DpStaff set staffStatus="
                + dpStaff.getStaffStatus() + " where id='" + dpStaff.getId()
                + "'";

        this.dpStaffDao.executeUpdate(hql);

        // 保存审核信息
        dpAuditRecordService.saveAuditRecord(dpAuditRecord);

    }

    /**
     * 重置开发者密码
     * @param dpStaff 开发者信息实体对象
     * @return void [返回类型说明]
     * @throws Exception
     * @exception throws [违例类型] [违例说明]
     */
    @Override
    public void resetDpStaffPwd(DpStaff dpStaff) throws Exception
    {
        String temp = dpStaff.getPassWord().replace("-", "").substring(0, 15);
        MailSenderInfo mailInfo = MailSenderInfo.getSystemMailSenderInfo();
        mailInfo.setToAddress(new String[]
        { dpStaff.getEmail() });
        mailInfo.setSubject("密码重置");
        mailInfo.setContent("尊敬的用户" + dpStaff.getUserName()
                + ":  您好，你的密码已重置成功。重置后的密码为：" + temp);
        SimpleMailSenderUtil.sendTextMail(mailInfo);
        // 对密码进行加密
        dpStaff.setPassWord(DigestUtils.md5Hex(temp));
        DpStaff dpStaffTemp = findDpStaff(dpStaff.getId());
        dpStaffTemp.setPassWord(dpStaff.getPassWord());
        dpStaffDao.saveOrUpdate(dpStaffTemp);
    }

	@Override
	public List<DpStaff> findDpStaffByEmailAndUserName(String email,
			String userName) throws Exception {
		 String hql = "from DpStaff dp where dp.email=? and dp.userName not in(?)";
		 return dpStaffDao.query(hql, email, userName);
	}
}
