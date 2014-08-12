package com.coship.depgm.sync;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.coship.depgm.model.Poster;
import com.coship.mse.core.MseException;
import com.coship.mse.opcode.OpCodeXStreamService;
import com.thoughtworks.xstream.XStream;

/**
 * 海报同步
 * 
 * @author 907900
 * 
 */
@Scope("prototype")
@Component("CMS_ADD_POSTER,CMS_UPDATE_POSTER,CMS_DELETE_POSTER")
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class PosterSyncService extends OpCodeXStreamService {
	protected ResourcePoster resourcePoster;

	@Autowired
	protected HibernateTemplate database;

	@Override
	protected void executeService() throws MseException {
		if (resourcePoster == null) {
			return;
		}

		String opCode = message.getOpCode();
		List<Poster> posterList = resourcePoster.getPosterList();
		if (posterList != null && posterList.size() > 0) {
			for (Poster po : posterList) {
				po.setResourceId(resourcePoster.getResourceCode());
			}

			// 保存入库，根据posterID修改删除
			if ("CMS_ADD_POSTER".equals(opCode) || "CMS_UPDATE_POSTER".equals(opCode)) {
				database.saveOrUpdateAll(posterList);
			} else if ("CMS_DELETE_POSTER".equals(opCode)) {
				database.deleteAll(posterList);
			}
		}
	}

	@Override
	protected void init() throws MseException {
		resourcePoster = (ResourcePoster) getXmlEntity(ResourcePoster.class, new XStreamHandler() {
			@Override
			public void handle(XStream xs) {
				xs.alias("resourcePoster", ResourcePoster.class);
				xs.alias("posterList", ArrayList.class);
				xs.alias("poster", Poster.class);
				xs.aliasField("posterID", Poster.class, "id");
			}
		});
	}

	@SuppressWarnings("unused")
	private class ResourcePoster {
		private String resourceCode;
		private List<Poster> posterList;

		public String getResourceCode() {
			return resourceCode;
		}

		public void setResourceCode(String resourceCode) {
			this.resourceCode = resourceCode;
		}

		public List<Poster> getPosterList() {
			return posterList;
		}

		public void setPosterList(List<Poster> posterList) {
			this.posterList = posterList;
		}
	}

}