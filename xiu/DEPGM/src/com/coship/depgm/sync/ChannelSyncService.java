package com.coship.depgm.sync;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.coship.depgm.model.Channel;
import com.coship.mse.core.MseException;
import com.coship.mse.opcode.OpCodeXStreamService;
import com.thoughtworks.xstream.XStream;

/**
 * 频道同步
 * 
 * @author 907900
 * 
 */
@Scope("prototype")
@Component("CMS_ADD_CHANNEL,CMS_UPDATE_CHANNEL")
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ChannelSyncService extends OpCodeXStreamService {
	protected ChannelDto channel;

	@Autowired
	protected HibernateTemplate database;

	@Override
	protected void executeService() throws MseException {
		if (channel == null) {
			return;
		}

		Channel po = (Channel) database.get(Channel.class, channel.getResourceCode());
		if (po == null) {
			po = new Channel();
		}

		po.setName(channel.getChannelName());
		po.setStatus(channel.getStatus());
		po.setTsID(channel.gettSID());
		po.setServiceID(channel.getServiceid());
		if ("1".equals(channel.getVideoType())) {
			po.setVideoType("HD");
		} else if ("0".equals(channel.getVideoType())) {
			po.setVideoType("SD");
		}
		po.setNetworkID(0);
		// channelSpec为数字组合 10111111111111 1TTV 2BTV
		if (channel.getChannelSpec() != null && channel.getChannelSpec().length() > 1) {
			char c = channel.getChannelSpec().charAt(1);// 取第二位
			po.setBtv(c == '0' ? true : false); // 0表示开通 1表示未开通
		}

		// 只保存mscp字段，不覆盖depg已有字段
		if (po.getId() != null) {
			database.update(po);
		} else {
			po.setId(channel.getResourceCode());
			database.save(po);
		}
	}

	@Override
	protected void init() throws MseException {
		channel = (ChannelDto) getXmlEntity(ChannelDto.class, new XStreamHandler() {
			@Override
			public void handle(XStream xs) {
				xs.alias("channel", ChannelDto.class);
			}
		});
	}

	@SuppressWarnings("unused")
	private class ChannelDto {
		private String resourceCode;
		private String channelID;
		private String channelName;
		private String videoType;
		private Integer tSID;
		private Integer serviceid;
		private Integer status;
		private String channelSpec;

		public String getResourceCode() {
			return resourceCode;
		}

		public void setResourceCode(String resourceCode) {
			this.resourceCode = resourceCode;
		}

		public String getChannelID() {
			return channelID;
		}

		public void setChannelID(String channelID) {
			this.channelID = channelID;
		}

		public String getChannelName() {
			return channelName;
		}

		public void setChannelName(String channelName) {
			this.channelName = channelName;
		}

		public String getVideoType() {
			return videoType;
		}

		public void setVideoType(String videoType) {
			this.videoType = videoType;
		}

		public Integer gettSID() {
			return tSID;
		}

		public void settSID(Integer tSID) {
			this.tSID = tSID;
		}

		public Integer getServiceid() {
			return serviceid;
		}

		public void setServiceid(Integer serviceid) {
			this.serviceid = serviceid;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		public String getChannelSpec() {
			return channelSpec;
		}

		public void setChannelSpec(String channelSpec) {
			this.channelSpec = channelSpec;
		}

	}
}