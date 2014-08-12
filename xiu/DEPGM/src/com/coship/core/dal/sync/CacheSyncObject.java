package com.coship.core.dal.sync;



/**
 * 同步对象，包含操作类型，数据等同步信息
 * 
 * @author 906324
 *
 */
public class CacheSyncObject implements java.io.Serializable{
	private static final long serialVersionUID = 7001126211502491925L;
	public static String OPERATE_INSERT="insert";
	public static String OPERATE_UPDATE="update";
	public static String OPERATE_DELETE="delete";
	public static String OPERATE_RELOAD="reload";

	private String operateType;
	private Object syncDataId;
	private String syncClassName;
	private String dataText;
	
	public CacheSyncObject(String operateType){
		this.operateType = operateType;
	}
	
	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public boolean isInsert(){
		return OPERATE_INSERT.equals(operateType);
	}
	
	public boolean isUpdate(){
		return OPERATE_UPDATE.equals(operateType);
	}
	
	public boolean isDelete(){
		return OPERATE_DELETE.equals(operateType);
	}

	public Object getSyncDataId() {
		return syncDataId;
	}

	public void setSyncDataId(Object syncDataId) {
		this.syncDataId = syncDataId;
	}

	public String getSyncClassName() {
		return syncClassName;
	}

	public void setSyncClassName(String syncClassName) {
		this.syncClassName = syncClassName;
	}

	public String getDataText() {
		return dataText;
	}

	public void setDataText(String dataText) {
		this.dataText = dataText;
	}
}