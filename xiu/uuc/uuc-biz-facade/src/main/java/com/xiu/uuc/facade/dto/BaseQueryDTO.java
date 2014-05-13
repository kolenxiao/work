package com.xiu.uuc.facade.dto;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 查询DTO基类
 * @ClassName: BaseQueryDTO 
 * @author xiaoyingping
 * @date 2011-9-3 下午03:12:44 
 *
 */
public class BaseQueryDTO implements Serializable {


	/**
	 * @Fields serialVersionUID
	 */
	
	private static final long serialVersionUID = 2481023398971836133L;

	/**
	 * 排序字段(例taskInfoSeqid).
	 */
	private String sort;

	/**
	 * 正序|反序(例DESC|ASC)
	 */
	private String order;

	/**
	 * 用于排序起始行,缺省为1
	 */
	private int firstRow = 1;

	/**
	 * 用于排序结束行，缺省为10
	 */
	private int lastRow = 10;

	/**
	 * 当前页
	 */
	private int currentPage = 0;

	/**
	 * 每页显示的条数
	 */
	private int pageSize = 10;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 修改时间
	 */
	private Date updateTime;

	/**
	 * 操作人员ID
	 */
	private String operId;
	
	/**
	 * 是否精确查询(默认为精确) Y:精确  N:模糊
	 */
	private String exactQuery = "Y";

	public String getOperId() {
		return operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public int getFirstRow() {
		return firstRow;
	}

	public void setFirstRow(int firstRow) {
		this.firstRow = firstRow;
	}

	public int getLastRow() {
		return lastRow;
	}

	public void setLastRow(int lastRow) {
		this.lastRow = lastRow;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public String getExactQuery() {
		return exactQuery;
	}

	public void setExactQuery(String exactQuery) {
		this.exactQuery = exactQuery;
	}

//  @Override
//  public String toString() {
//      return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
//  }

  @Override
  public String toString() {
      String s = "";
      try {
          s = getPropertyString(this);
      } catch (Exception e) {
    	//e.printStackTrace();
      	return s;
      }
      return s;
  }

  @SuppressWarnings("unchecked")
  public String getPropertyString(Object entityName) throws Exception {
      StringBuffer sb = new StringBuffer();
      sb.append("\n------begin ------\n");
      if (entityName != null) {
          Class c = entityName.getClass();
          Field field[] = c.getDeclaredFields();
          if (null != field) {
        	  for (Field f : field) {
                  Object obj = invokeMethod(entityName, f.getName(), null);
                  sb.append(f.getName());
                  sb.append(" : ");
                  sb.append(obj);
                  sb.append("\n");
              }
		  }
      }
      sb.append("------end ------\n");
      return sb.toString();
  }

  @SuppressWarnings("unchecked")
  public Object invokeMethod(Object owner, String methodName, Object[] args) throws Exception {
      Class ownerClass = owner.getClass();
      Method method = null;
      try {
    	  if (methodName != null) {
              methodName = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
              method = ownerClass.getMethod("get" + methodName);
              if (null != method) {
					return method.invoke(owner);
			  }
          }
      } catch (SecurityException e) {
          //e.printStackTrace();
    	  return null;
      } catch (NoSuchMethodException e) {
          return null;
      }
      return null;
  }
  
}
