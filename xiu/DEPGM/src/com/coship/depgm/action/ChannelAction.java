package com.coship.depgm.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.coship.depgm.model.Channel;
import com.coship.depgm.service.ChannelService;

public class ChannelAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(ChannelAction.class);

	@Autowired
	private ChannelService channelService;

	private Channel channel;
	private String id;

	// 名称
	private String name;
	// 搜视网频道ID
	private String soutvId;
	// 视频清晰度
	private String videoType;
	// 是否回看
	private Boolean btv;
	// DVD频道三要素
	private Integer networkID;// 网络
	private Integer tsID;// 频点
	private Integer serviceID;// 频道
	//顺序
	private Integer rank;

	/**
	 * 新增
	 */
	public void create() {
		try {
			channel = new Channel();
			channel.setName(name);
			channel.setSoutvId(soutvId);
			channel.setVideoType(videoType);
			channel.setBtv(btv);
			channel.setVideoType(videoType);
			channel.setNetworkID(networkID);
			channel.setTsID(tsID);
			channel.setServiceID(serviceID);
			channel.setRank(rank);

			// 调用Service
			channelService.add(channel);
			success();
		} catch (Exception e) {
			logger.error("新增频道失败", e);
			jsonRet("1", e.getMessage());
		}
	}

	/**
	 * 修改
	 */
	public void update() {
		try {
			channel = new Channel();
			channel.setId(id);
			channel.setName(name);
			channel.setSoutvId(soutvId);
			channel.setVideoType(videoType);
			channel.setBtv(btv);
			channel.setNetworkID(networkID);
			channel.setTsID(tsID);
			channel.setServiceID(serviceID);
			channel.setRank(rank);
			channelService.update(channel);
			success();
		} catch (Exception e) {
			logger.error("修改频道失败", e);
			jsonRet("1", e.getMessage());
		}
	}

	/**
	 * 删除
	 */
	public void delete() {
		try {
			Assert.notNull(id, "id不能为空!");
			channelService.deleteById(id);
			success();
		} catch (Exception e) {
			logger.error("删除频道失败", e);
			jsonRet("1", e.getMessage());
		}
	}

	/**
	 * 查询
	 */
	public void list() throws Exception {
		try {
			channel = new Channel();
			channel.setName(name);
			channel.setVideoType(videoType);
			channel.setBtv(btv);
			channelService.getListForPage(pager, channel);
			jsonPage();
		} catch (Exception e) {
			logger.error("查询频道列表失败", e);
			jsonRet("1", e.getMessage());
		}
	}
	/**
	 * 不分页列出所有频道
	 * @return
	 */
	public void listNoPage() throws Exception {
		try {
			channel = new Channel();
			channel.setName(name);
			
			channelService.getListNoPage(pager, channel);
			jsonPage();
		} catch (Exception e) {
			logger.error("查询频道列表失败", e);
			jsonRet("1", e.getMessage());
		}
	}
	public ChannelService getChannelService() {
		return channelService;
	}

	public void setChannelService(ChannelService channelService) {
		this.channelService = channelService;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSoutvId() {
		return soutvId;
	}

	public void setSoutvId(String soutvId) {
		this.soutvId = soutvId;
	}

	public String getVideoType() {
		return videoType;
	}

	public void setVideoType(String videoType) {
		this.videoType = videoType;
	}

	public boolean isBtv() {
		return btv;
	}

	public void setBtv(boolean btv) {
		this.btv = btv;
	}

	public Integer getNetworkID() {
		return networkID;
	}

	public void setNetworkID(Integer networkID) {
		this.networkID = networkID;
	}

	public Integer getTsID() {
		return tsID;
	}

	public void setTsID(Integer tsID) {
		this.tsID = tsID;
	}

	public Integer getServiceID() {
		return serviceID;
	}

	public void setServiceID(Integer serviceID) {
		this.serviceID = serviceID;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

}
