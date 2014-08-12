package com.coship.core.dal.sync;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class SyncObjectList implements java.io.Serializable{
	private static final long serialVersionUID = -7881671791378094070L;
	private List<CacheSyncObject> syncList = new ArrayList<CacheSyncObject>();
	
	public void syncAddEntity(Object entity) {
		sync(CacheSyncObject.OPERATE_INSERT, null, entity, entity.getClass());
	}

	public void syncModifyEntity(Object entity) {
		sync(CacheSyncObject.OPERATE_UPDATE, null, entity, entity.getClass());
	}

	public void syncDeleteEntity(Object entityId, Class<?> entityClass){
		sync(CacheSyncObject.OPERATE_DELETE, entityId, null, entityClass);
	}

	public void syncReloadEntity(Class<?> entityClass) throws Exception {
		sync(CacheSyncObject.OPERATE_RELOAD, null, null, entityClass);
	}
	
	public void sync(String operate,Object id, Object entity,Class<?> entityClass){
		CacheSyncObject sync = new CacheSyncObject(operate);
		sync.setSyncDataId(id);
		sync.setSyncClassName(entityClass.getName());
		if (entity != null) {
			sync.setDataText(new Gson().toJson(entity));
		}
		syncList.add(sync);
	}

	public List<CacheSyncObject> getSyncList() {
		return syncList;
	}

	public void addSyncList(CacheSyncObject syncObject) {
		this.syncList.add(syncObject);
	}
}