/*
 * 文件名称：PageDTO.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：JiangJinfeng/906974
 * 创建时间：2012-9-23
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.appstore.service.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 分页DTO
 * @author ShaoZhuyu/903588
 * @version [版本号, 2012-9-23]
 * @since [产品/模块版本]
 */
public class PageDTO<T> implements Serializable
{
    private static final long serialVersionUID = 1L;

    private int totalCount;

    private int pageSize;

    private int totalPage;

    private int currentPage;

    private boolean hasPrePage;

    private boolean hasNextPage;

    private List<T> resultList;

    /**
     * DVB付费套餐鉴权使用字段, OTT需忽略本字段.本字段目前使用在获取专题下的应用列表接口中.其他接口需忽略本字段.
     */
    private int hasPrivilege = 1;

    public int getTotalCount()
    {
        return totalCount;
    }

    public void setTotalCount(int totalCount)
    {
        this.totalCount = totalCount;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public int getTotalPage()
    {
        return totalPage;
    }

    public void setTotalPage(int totalPage)
    {
        this.totalPage = totalPage;
    }

    public int getCurrentPage()
    {
        return currentPage;
    }

    public void setCurrentPage(int currentPage)
    {
        this.currentPage = currentPage;
    }

    public boolean isHasPrePage()
    {
        return hasPrePage;
    }

    public void setHasPrePage(boolean hasPrePage)
    {
        this.hasPrePage = hasPrePage;
    }

    public boolean isHasNextPage()
    {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage)
    {
        this.hasNextPage = hasNextPage;
    }

    public List<T> getResultList()
    {
        return resultList;
    }

    public void setResultList(List<T> resultList)
    {
        this.resultList = resultList;
    }

    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }

    public int getHasPrivilege() {
        return hasPrivilege;
    }

    public void setHasPrivilege(int hasPrivilege) {
        this.hasPrivilege = hasPrivilege;
    }

}
