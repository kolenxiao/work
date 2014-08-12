package com.coship.depgm.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coship.core.dal.sync.CacheClusterSync;
import com.coship.depgm.action.Pager;
import com.coship.depgm.common.UID;
import com.coship.depgm.dao.ChannelDao;
import com.coship.depgm.exception.BusinessException;
import com.coship.depgm.model.Channel;

@Service
@Transactional
public class ChannelService {
	@Autowired
	private ChannelDao channelDao;

	/**
	 * 通过id获取频道信息
	 * 
	 * @param id
	 * @return
	 */
	public Channel get(String id) {
		Channel channel = channelDao.get(id);
		if (null == channel) {
			throw new BusinessException("频道id【" + id + "】不存在！");
		}
		return channel;
	}

	/**
	 * 通过名称获取频道信息
	 * 
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Channel getByName(String name) {
		DetachedCriteria detachedCriteria = channelDao.createDetachedCriteria();
		detachedCriteria.add(Restrictions.eq("name", name));
		List<Channel> list = channelDao.findByCriteria(detachedCriteria);

		Channel result = null;
		if (CollectionUtils.isNotEmpty(list)) {
			result = list.get(0);
		}
		return result;
	}

	/**
	 * 保存
	 * 
	 * @param channel
	 * @throws Exception 
	 */
	public void add(Channel channel) throws Exception {
		// 判断分类名是否有重复
		String name = channel.getName();
		Channel exist = this.getByName(name);
		if (null != exist) {
			throw new BusinessException("频道【" + name + "】已存在！");
		}

		// 生成id
		channel.setId(UID.create());

		// 插入数据库
		channelDao.save(channel);

		//同步
		CacheClusterSync.syncAddEntity(channel);
	}

	/**
	 * 更新
	 * 
	 * @param channel
	 * @throws Exception 
	 */
	public void update(Channel channel) throws Exception {
		// 判断名称是否有重复
		String id = channel.getId();
		String name = channel.getName();
		Channel current = this.get(id);
		if (!StringUtils.equals(current.getName(), name)) {
			Channel temp = this.getByName(name);
			if (null != temp) {
				throw new BusinessException("频道【" + name + "】已存在！");
			}
			current.setName(name);
		}
		if (null != channel.getSoutvId()) {
			current.setSoutvId(channel.getSoutvId());
		}
		if (StringUtils.isNotBlank(channel.getVideoType())) {
			current.setVideoType(channel.getVideoType());
		}
		if (null != channel.getBtv()) {
			current.setBtv(channel.getBtv());
		}
		if (null != channel.getNetworkID()) {
			current.setNetworkID(channel.getNetworkID());
		}
		if (null != channel.getTsID()) {
			current.setTsID(channel.getTsID());
		}
		if (null != channel.getServiceID()) {
			current.setServiceID(channel.getServiceID());
		}
		if (null != channel.getRank()) {
			current.setRank(channel.getRank());
		}

		// 修改数据库
		channelDao.update(current);

		//同步
		CacheClusterSync.syncModifyEntity(current);
	}

	/**
	 * 删除
	 * 
	 * @param channel
	 * @throws Exception 
	 */
	public void deleteById(String id) throws Exception {
		//删除频道信息
		channelDao.deleteByKey(id);

		//同步
		CacheClusterSync.syncDeleteEntity(id, Channel.class);
	}

	/**
	 * 分页查询
	 * 
	 * @param pager
	 * @param channel
	 */
	public void getListForPage(Pager pager, Channel channel) {
		channelDao.getListForPage(pager, channel);
	}
	
	/**
	 * 不分页列出所有频道
	 */
	public void getListNoPage(Pager pager, Channel channel){
		channelDao.getListNoPage(pager, channel);
	}
}