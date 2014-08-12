package com.coship.depgm.sync;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.coship.core.dal.sync.CacheClusterSync;
import com.coship.core.dal.sync.SyncObjectList;
import com.coship.depgm.common.UID;
import com.coship.depgm.dao.ChannelDao;
import com.coship.depgm.model.Channel;
import com.coship.depgm.model.Program;
import com.coship.mse.core.MseException;
import com.coship.mse.opcode.OpCodeXStreamService;
import com.thoughtworks.xstream.XStream;

@Scope("prototype")
@Component("CMS_ADD_PROGRAM_GUIDE")
@Transactional(propagation=Propagation.NOT_SUPPORTED)
public class ProgramSyncService extends OpCodeXStreamService {
	protected List<Program> programList;
	
	@Autowired
	protected HibernateTemplate database;
	
	@Autowired
	protected ChannelDao channelDao;
	
	@Override
	protected void executeService() throws MseException {
		int serviceId = Integer.parseInt(programList.get(0).getChannelId());
		Channel channel = channelDao.getByServiceId(serviceId);
		
		SyncObjectList list = new SyncObjectList();
		for(Program program : programList){
			Criteria criteria = database.getSessionFactory().openSession().createCriteria(Program.class);
			criteria.add(Restrictions.eq("channelId", channel.getId()));
			criteria.add(Restrictions.eq("beginTime", program.getBeginTime()));
			Program theProgram = (Program)criteria.uniqueResult();
			if(theProgram == null){
				program.setChannelId(channel.getId());
				program.setId(UID.create());
				database.save(program);
				list.syncAddEntity(program);
			}else{
				theProgram.setAssetId(program.getAssetId());
				database.update(theProgram);
				list.syncModifyEntity(program);
			}
		}
		CacheClusterSync.sync(list);
	}

	@Override
	protected void init() throws MseException {
		SyncChannel channel = (SyncChannel)getXmlEntity(Program.class, new XStreamHandler(){
			@Override
			public void handle(XStream xs) {
				xs.alias("channel", SyncChannel.class);
				xs.alias("programGuideList", ArrayList.class);
				xs.alias("programGuide", Program.class);
				xs.aliasField("programGuideID", Program.class, "id");
				xs.aliasField("channelID", Program.class, "channelId");
				xs.aliasField("eventName", Program.class, "name");
				xs.aliasField("eventDesc", Program.class, "desc");
				xs.aliasField("assetID", Program.class, "assetId");
			}
		});
		programList = channel.getProgramGuideList();
	}
	
	public static class SyncChannel {
		private String resourceCode;
		private List<Program> programGuideList;

		public String getResourceCode() {
			return resourceCode;
		}

		public void setResourceCode(String resourceCode) {
			this.resourceCode = resourceCode;
		}

		public List<Program> getProgramGuideList() {
			return programGuideList;
		}

		public void setProgramGuideList(List<Program> programGuideList) {
			this.programGuideList = programGuideList;
		}
	}
}