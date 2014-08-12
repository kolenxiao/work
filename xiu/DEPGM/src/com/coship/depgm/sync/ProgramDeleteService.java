package com.coship.depgm.sync;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.coship.mse.core.MseException;

@Scope("prototype")
@Component("CMS_DELETE_PROGRAM_GUIDE")
@Transactional(propagation=Propagation.NOT_SUPPORTED)
public class ProgramDeleteService extends ProgramSyncService {	
	@Override
	protected void executeService() throws MseException {
//		SyncObjectList list = new SyncObjectList();
//		for(Program program : programList){
//			database.delete(program);
//			list.syncDeleteEntity(program.getId(), Program.class);
//		}
//		CacheClusterSync.sync(list);
	}
}