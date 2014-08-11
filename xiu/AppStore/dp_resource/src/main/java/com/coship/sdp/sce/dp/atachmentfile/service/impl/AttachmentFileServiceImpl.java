/*
 * 文 件 名：AttachmentFileServiceImpl.java
 * 版    权：Shenzhen Coship Electronics Co.,
 * Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<描述>
 * 修 改 人：sunwengang/903820
 * 修改时间：2011-7-25
 * 修改内容：<修改内容>
 */
package com.coship.sdp.sce.dp.atachmentfile.service.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coship.sdp.sce.dp.ap.entity.AppInfoDetail;
import com.coship.sdp.sce.dp.ap.entity.DpAppInfo;
import com.coship.sdp.sce.dp.atachmentfile.dao.AttachmentFileDao;
import com.coship.sdp.sce.dp.atachmentfile.entity.AttachmentFile;
import com.coship.sdp.sce.dp.atachmentfile.entity.AttachmentFileEntity;
import com.coship.sdp.sce.dp.atachmentfile.service.AttachmentFileService;
import com.coship.sdp.utils.Debug;

/**
 * <一句话功能简述>. <功能详细描述>
 * @author sunwengang/903820
 * @version [版本号, 2011-7-25]
 * @since [产品/模块版本]
 */
@Service("attachmentFileService")
@Transactional
public class AttachmentFileServiceImpl implements AttachmentFileService
{

    /**
     * <注释内容>.
     */
    private static final long serialVersionUID = 1L;

    /**
     * dao层接口.
     */
    @Autowired
    private AttachmentFileDao attachmentFileDao;

    /**
     * 添加附件.
     * @param entity 附件实体
     * @throws Exception 异常处理类
     */
    public void saveAttachmentFile(AttachmentFile entity) throws Exception
    {
        this.attachmentFileDao.save(entity);
    }

    /**
     * 删除附件.
     * @param entity 附件实体类
     * @throws Exception 异常处理类
     */
    public void deleteAttachmentFile(AttachmentFile entity) throws Exception

    {
        this.attachmentFileDao.delete(entity);
    }

    /**
     * 修改附件.
     * @param entity 附件实体类
     * @throws Exception 异常处理类
     */
    public void updateAttachmentFil(AttachmentFile entity) throws Exception
    {
        this.attachmentFileDao.update(entity);
    }

    /**
     * 根据ID查找附件.
     * @param id 附件id
     * @return 附件实体对象
     * @throws Exception 异常处理类
     */
    public AttachmentFile findAttachmentFileById(String id) throws Exception
    {
        return this.attachmentFileDao.get(id);
    }

    /**
     * 根据fileName查找附件.
     * @param fileName
     * @return AttachmentFile
     * @throws Exception
     */
    public AttachmentFile findAttachmentFile(String fileName) throws Exception
    {

        StringBuffer HQL = new StringBuffer(
                "from AttachmentFile where fileName = '");
        HQL.append(fileName);
        HQL.append("'");

        List<AttachmentFile> result = this.attachmentFileDao.query(HQL
                .toString());

        if (result != null && result.size() > 0)
        {
            return result.get(0);
        }
        else
        {
            return null;
        }
    }

    @Override
    public void deleteAttachmentRelationByAppId(DpAppInfo dpAppInfo)
            throws Exception
    {
        String hql = "delete from AttachmentFile where dpAppInfo =?";
        attachmentFileDao.executeUpdate(hql, new Object[]
        { dpAppInfo });

    }

    @Override
    public void deleteAttachmentRelationByAppId(DpAppInfo dpAppInfo,
            String fileDesc) throws Exception
    {
        String hql = "delete from AttachmentFile where dpAppInfo =? and fileDesc =?";
        attachmentFileDao.executeUpdate(hql, new Object[]
        { dpAppInfo, fileDesc });

    }

	@Override
	public List<AttachmentFile> findAttachmentByAppIdFileDesc(
			DpAppInfo dpAppInfo, String fileDesc) {
		Map param = new HashMap();
		StringBuffer hql = new StringBuffer("from AttachmentFile where 1 = 1");
		if (null != dpAppInfo) {
			hql.append(" and dpAppInfo = :dpAppInfo ");
			param.put("dpAppInfo", dpAppInfo);
		}
		if (StringUtils.isNotBlank(fileDesc)) {
			hql.append(" and fileDesc = :fileDesc");
			param.put("fileDesc", fileDesc);
		}
		hql.append(" ORDER BY dpAppInfo.id");

		List<AttachmentFile> list = attachmentFileDao.query(hql.toString(), param);
		if(null == list){
			list = new ArrayList<AttachmentFile>();
		}
		return list;
	}
    
	@Override
	public Map<String, String> findAttachByAppIdsAndFileDesc(
			List<AppInfoDetail> appList, String fileDesc) throws Exception {
		StringBuffer sbf = new StringBuffer();
		for (AppInfoDetail app : appList) {
			sbf.append("'").append(app.getId()).append("',");
		}
		String ids = sbf.toString();
		
		Map<String, String> resultMap = new HashMap<String, String>();
		if(!"".equals(ids)){
			ids = ids.substring(0, ids.length()-1);
			String sql = "select a.C_DPAPPINFO_ID, a.C_FILE_SAVE_NAME from t_attachment_file a where a.C_FILE_DESC = 'logo' and a.C_DPAPPINFO_ID in ("
					   + ids
					   +") order by a.C_CREATE_DATE";
			
			List<Object> list = attachmentFileDao.findAttachmentListBySql(sql);
			for (Object props : list) {
				Object[] propArray = (Object[]) props;
				resultMap.put((String)propArray[0], (String)propArray[1]);
			}
		}
		return resultMap;
	}

    @Override
    public void deleteAttachmentFileById(String AttachmentFileId)
            throws Exception
    {
        String hql = "delete from AttachmentFile where id =? ";
        attachmentFileDao.executeUpdate(hql, new Object[]
        { AttachmentFileId });

    }

    /**
     * 根据应用的id获取附件
     * @param id
     * @return
     */
    @Override
    public List<AttachmentFileEntity> findAttachmentByAppId(String id)
    {
        List<AttachmentFileEntity> attachmentList = new ArrayList<AttachmentFileEntity>();

        Session session = attachmentFileDao.creatSqlSession();
        Connection conn = session.connection();

        ResultSet rs =null;
    	CallableStatement call = null;
    	try {
			call = conn.prepareCall("{Call QUERY_ATTA_FILE(?)}");
			call.setString(1, id);
			rs = call.executeQuery();
			while (rs.next())
			{
				String fileSaveName = rs.getString(1);
				int size = rs.getInt(2);
				String fileDesc = rs.getString(3);
				AttachmentFileEntity tempEntity = new AttachmentFileEntity();
				tempEntity.setFileSaveName(fileSaveName);
	            tempEntity.setFileSize(size);
	            tempEntity.setFileDesc(fileDesc);
	            attachmentList.add(tempEntity);
			}

		} catch (SQLException e) {
			Debug.logError(e.getMessage());
		} finally {
			try {
				if (rs != null)
				{
					rs.close();
				}
				if (call != null)
				{
					call.close();
				}
				if (conn != null)
				{
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
        return attachmentList;
    }

}
