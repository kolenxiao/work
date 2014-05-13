package com.xiu.uuc.facade.dto;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @ClassName: BaseDTO
 * @Description: DTO基类
 * @author xiu
 * @date 2011-7-19 上午11:24:18
 */
public class BaseDTO implements Serializable {

    /**
     * @Fields serialVersionUID : 序列化序列号
     */

    private static final long serialVersionUID = 1L;

    /**
     * 创建时间
     */
    protected Date createTime;

    /**
     * 修改时间
     */
    protected Date updateTime;

    /**
     * 操作人员ID
     */
    protected String operId = "";

    public String getOperId() {
        return operId;
    }

    public void setOperId(String operId) {
        this.operId = operId;
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

//    @Override
//    public String toString() {
//        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
//    }

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
				methodName = methodName.substring(0, 1).toUpperCase()+ methodName.substring(1);
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
