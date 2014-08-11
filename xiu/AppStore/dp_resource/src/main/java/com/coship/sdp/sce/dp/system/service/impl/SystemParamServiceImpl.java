package com.coship.sdp.sce.dp.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coship.sdp.sce.dp.common.Constants;
import com.coship.sdp.sce.dp.exception.BusinessException;
import com.coship.sdp.sce.dp.system.dao.SystemParamDao;
import com.coship.sdp.sce.dp.system.entity.SystemParam;
import com.coship.sdp.sce.dp.system.service.SystemParamService;
import com.coship.sdp.sce.dp.utils.SqlUtil;
import com.coship.sdp.utils.Page;

@Service("systemParamService")
@Transactional
public class SystemParamServiceImpl implements SystemParamService {


	private static final long serialVersionUID = -8460533955509035542L;
	
	@Autowired
	private SystemParamDao systemParamDao;

	@Override
	public void create(SystemParam systemParam) {
		SystemParam oldParam = this.getByCode(systemParam.getCode());
		if(null != oldParam){
			throw new BusinessException("编码为" + systemParam.getCode() + "已经存在！");
		}
		systemParamDao.save(systemParam);
	}

	@Override
	public void update(SystemParam systemParam) {
		SystemParam oldParam = systemParamDao.get(systemParam.getId());
		if(null != oldParam){
			if (oldParam.getType().equals(Constants.PARAM_TYPE_PHOTO)) {
				if(StringUtils.isNotBlank(systemParam.getValue())){
					oldParam.setValue(systemParam.getValue());
				}
			}else{
				oldParam.setValue(systemParam.getValue());
			}
			
			systemParamDao.update(oldParam);
		}
	}

	@Override
	public void delete(String[] idArr) {
		for (String id : idArr) {
			SystemParam systemParam = systemParamDao.get(id);
			if (null != systemParam) {
				systemParamDao.delete(systemParam);
			}
		}
	}

	@Override
	public Page<SystemParam> search(Page<SystemParam> page, SystemParam systemParam, Map<String, Object> params) {
		//构造查询条件
        StringBuffer sbf = new StringBuffer("from SystemParam where 1 = 1 ");

        if(null != systemParam){
            String name = systemParam.getName();
            if(StringUtils.isNotBlank(name)){
            	sbf.append( " and name like '");
            	sbf.append(SqlUtil.getLikeSql(name));
            	sbf.append("' escape '/' ");
            }
            
            String code = systemParam.getCode();
            if(StringUtils.isNotBlank(code)){
            	sbf.append( " and code = '" + code + "'");
            }
            
            String value = systemParam.getValue();
            if(StringUtils.isNotBlank(value)){
            	sbf.append( " and value = '" + value);
            	sbf.append("'");
            }
        }

        sbf.append(" order by code");
        
        //查询
        String hql = sbf.toString(); 
		page = systemParamDao.queryPage(page, hql, params);
		return page;
	}


	@Override
	public SystemParam getById(String id) {
		if (StringUtils.isNotBlank(id)) {
			return systemParamDao.get(id);
		} else {
			return null;
		}
	}
	
	@Override
	public SystemParam getByCode(String code) {
		String hql = new String("from SystemParam where code = ?");
		return systemParamDao.findUnique(hql, code);
	}
	
	@Override
	public List<SystemParam> getAll(){
		String hql = new String("from SystemParam");
		return systemParamDao.query(hql, new HashMap<String, Object>());
	}

}
