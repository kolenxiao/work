package com.coship.depgm.sync;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.coship.depgm.model.ColumnResource;
import com.coship.mse.core.MseException;
import com.coship.mse.opcode.OpCodeXStreamService;
import com.thoughtworks.xstream.XStream;

/**
 * 上下架信息同步
 * 
 * @author 907900
 * 
 */
@Scope("prototype")
@Component("PORTALMS_SYNC_PART_UPSHELF_RESOURCE,PORTALMS_DEL_COLUMN_RESOURCE")
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ColumnResourceSyncService extends OpCodeXStreamService {
	protected List<ColumnResource> list;

	@Autowired
	protected HibernateTemplate database;

	@Override
	protected void executeService() throws MseException {
		if (list == null || list.size() == 0) {
			return;
		}

		String opCode = message.getOpCode();
		if ("PORTALMS_SYNC_PART_UPSHELF_RESOURCE".equals(opCode)) {
			database.saveOrUpdateAll(list);
		} else if ("PORTALMS_DEL_COLUMN_RESOURCE".equals(opCode)) {
			database.deleteAll(list);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void init() throws MseException {
		list = (List<ColumnResource>) getXmlEntity(ColumnResource.class, new XStreamHandler() {
			@Override
			public void handle(XStream xs) {
				xs.alias("resourceList", ArrayList.class);
				xs.alias("resource", ColumnResource.class);
				xs.aliasField("columnRefResourceID", ColumnResource.class, "id");
				xs.aliasField("resourceCode", ColumnResource.class, "resourceId");
				xs.aliasField("resourceType", ColumnResource.class, "resourceType");
				xs.aliasField("resourceRank", ColumnResource.class, "rank");
				xs.aliasField("columnID", ColumnResource.class, "columnId");
			}
		});
	}

}
