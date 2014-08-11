package com.coship.sdp.sce.dp.ap.dao.impl;

import org.springframework.stereotype.Repository;

import com.coship.sdp.access.dao.impl.GenericDaoImpl;
import com.coship.sdp.sce.dp.ap.dao.DpStaffDao;
import com.coship.sdp.sce.dp.ap.entity.DpStaff;

@Repository("dpStaffDao")
public class DpStaffDaoImpl extends GenericDaoImpl<DpStaff, String> implements
        DpStaffDao
{

    /**
     * <注释内容>
     */
    private static final long serialVersionUID = 1L;
}
