package com.coship.depgm.service;

import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.coship.core.dal.sync.CacheClusterSync;
import com.coship.core.dal.sync.SyncObjectList;
import com.coship.depgm.common.HibernateBase;
import com.coship.depgm.common.UID;
import com.coship.depgm.model.Channel;
import com.coship.depgm.model.ChannelModify;
import com.coship.depgm.model.Program;
import com.coship.depgm.model.ProgramChapter;
import com.coship.depgm.model.ProgramContent;
import com.coship.depgm.model.ProgramType;

@Component
@Transactional
@SuppressWarnings("unchecked")
public class TvSouService extends HibernateBase{
	protected static Logger logger = Logger.getLogger(TvSouService.class);
	
	@Autowired
	private PosterService posterService;
	
	public void catchFullContent(String tvSouContentId) throws Exception{
		ProgramContent content = catchContent(tvSouContentId);
		if(content != null){			
			catchChapter(content.getId(), tvSouContentId);
		}
	}
	
	public ProgramContent catchContent(String tvSouContentId) throws Exception{
		String url = "http://hz.tvsou.com/jm/TZYS/F_t5dajK.asp?id=" + tvSouContentId;
		Element root = getDocument(url, "GBK").getRootElement();
		if(root.elements().size() == 0){
			return null;
		}
		ProgramContent content = (ProgramContent)from(ProgramContent.class).eq("tvSouId", tvSouContentId).unique();
		if (content != null) {
			getSession().delete(content);
			CacheClusterSync.syncDeleteEntity(content.getId(), ProgramContent.class);
		}
		content = new ProgramContent();
		content.setId(UID.create());
		content.setTvSouId(root.elementText("ID"));
		content.setName(root.elementText("title"));
		content.setDirector(root.elementText("Director"));
		content.setLeadingActor(root.elementText("Presenter"));
		List<ProgramType> types = getSession().createCriteria(ProgramType.class).list();
		content.setTvSouTypes(root.elementText("pp"));
		if (content.getTvSouTypes() != null) {
			content.caculateTypeId(types);
		}
		String desc = root.elementText("Content").replace("<BR>", "\r\n").trim();
		desc = desc.length() > 4000 ? desc.substring(0, 4000) : desc;
		content.setDescription(desc);
		String js = root.elementText("js");
		if(!StringUtils.isBlank(js)){			
			content.setChapter(Integer.parseInt(js));
		}
		logger.info("获取节目:" + content);
		catchPoster(content);
		getSession().save(content);
		CacheClusterSync.syncAddEntity(content);
		return content;
	}
	
	public void catchChapter(String contentId, String tvSouContentId) throws Exception{
		String url = "http://hz.tvsou.com/jm/TZYS/Images_ntY5a7E.asp?id=" + tvSouContentId;
		Element root = getDocument(url, "GBK").getRootElement();
		List<ProgramChapter> chapters =  from(ProgramChapter.class).eq("contentId", contentId).list();
		SyncObjectList syncList = new SyncObjectList();
		for(ProgramChapter chapter : chapters){
			getSession().delete(chapter);
			syncList.syncDeleteEntity(chapter.getId(), ProgramChapter.class);
		}
		Set<Integer> jss = new HashSet<Integer>();
		for (Element element : (List<Element>) root.elements("image")) {
			String type = element.elementText("typeid");
			if ("1".equals(type)) {
				int js = Integer.parseInt(element.elementText("js"));
				if (js == 0 || jss.contains(js)) {
					continue;
				}
				ProgramChapter chapter = new ProgramChapter();
				chapter.setId(UID.create());
				chapter.setChapter(js);
				chapter.setContentId(contentId);
				String name = downImage(contentId, element.elementText("images_url"));
				chapter.setPoster(name);
				getSession().save(chapter);
				syncList.syncAddEntity(chapter);
				logger.info("获取节目集:" + chapter);
				jss.add(js);
			}
		}
		CacheClusterSync.sync(syncList);
	}

	public void catchPoster(ProgramContent content) throws Exception{
		String url = "http://hz.tvsou.com/jm/TZYS/Images_ntY5a7E.asp?id=" + content.getTvSouId();
		Element root = getDocument(url, "GBK").getRootElement();
		content.clearPoster();
		for (Element element : (List<Element>) root.elements("image")) {
			String type = element.elementText("typeid");
			if ("2".equals(type)) {
				int width = Integer.parseInt(element.elementText("imagewidth"));
				int height = Integer.parseInt(element.elementText("imageheight"));
				String poster = downImage(content.getId(), element.elementText("images_url"));
				logger.info("获取节目海报:poster=" + poster + "width=" + width + ";height=" + height);
				if (width > height) {
					content.setHoriPoster(poster);
				}else{
					content.setPoster(poster);
				}
				if(content.hasAllPoster()){
					return;
				}
			}
		}
		if(!content.hasAllPoster()){
			logger.warn("未能获取节目海报:" + content.toPoster());
		}
	}
	
	private String downImage(String contentId, String url) throws Exception{
		return posterService.upload(new URL(url).openStream(), contentId, UID.create()+".jpg");
	}
	
	public void catchProgram(String channelId, String tvSouChannelId, String date) throws Exception{
		String url = "http://hz.tvsou.com/jm/tz/data_ajJ9orFatvsouepg.asp?id=" + tvSouChannelId + "&Date=" + date;
		Element root = getDocument(url).getRootElement().element("tz");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<Program> programs = from(Program.class).eq("channelId", channelId).eq("eventDate", sdf.parse(date)).list();
		SyncObjectList syncList = new SyncObjectList();
		for(Program program : programs){
			getSession().delete(program);
			syncList.syncDeleteEntity(program.getId(), Program.class);
		}
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		for(Element element : (List<Element>)root.elements("C")){
			if(element.elements().size()==0){
				return;
			}
			Program program = new Program();
			program.setId(UID.create());
			program.setName(element.elementText("pn"));
			String beginTime = element.elementText("pt");
			program.setBeginTime(sdf.parse(beginTime));
			program.setEndTime(sdf.parse(element.elementText("et")));
			program.setChannelId(channelId);
			logger.info("获取节目单：" + beginTime + " " + program.getName());
			String tvSouContentId = element.elementText("fid");
			if(StringUtils.isBlank(tvSouContentId)){
				tvSouContentId = element.elementText("fid2");
			}
			if(!StringUtils.isBlank(tvSouContentId)){
				ProgramContent content = (ProgramContent) getSession().createCriteria(ProgramContent.class)
						.add(Restrictions.eq("tvSouId", tvSouContentId)).uniqueResult();
				if(content == null){
					content = catchContent(tvSouContentId);
				}
				if(content != null){
					program.setContentId(content.getId());
					program.setTypeId(content.getTypeId());
				}else{
					logger.warn("节目单无对应节目：" + program.getName());
				}
			}
			String js = element.elementText("js");
			if(!StringUtils.isBlank(js)){				
				program.setChapter(Integer.parseInt(js));
			}
			getSession().save(program);
			syncList.syncAddEntity(program);
		}
		CacheClusterSync.sync(syncList);
	}
	
	public void initCatchChannel() throws Exception{
		String url = "http://hz.tvsou.com/jm/tz/channellist.asp";
		Document document = getDocument(url);
		for(Element element : (List<Element>)document.getRootElement().elements()){
			Channel channel = new Channel();
			channel.setId(UID.create());
			channel.setName(element.elementText("ChannelName"));
			channel.setSoutvId(element.elementText("id"));
			logger.info("获取频道:" + channel.getName());
			getSession().save(channel);
		}
	}
	
	public void catchProgram() throws Exception{
		List<Channel> channels = from(Channel.class).list();
		Map<String, Channel> channelMap = new HashMap<String, Channel>();
		for(Channel channel : channels){
			if(!StringUtils.isBlank(channel.getSoutvId())){				
				channelMap.put(channel.getSoutvId(), channel);
			}
		}
		String url = "http://hz.tvsou.com/jm/tz/catchindex.asp";
		Document document = getDocument(url);
		for(Element element : (List<Element>)document.getRootElement().elements()){
			String tvSouChannelId = element.elementText("ChannelID");
			Channel channel = channelMap.get(tvSouChannelId);
			if(channel == null){
				continue;
			}
			String md5 = element.elementText("MD5");
			String date = element.elementText("CatchDate");
			ChannelModify channelModify = (ChannelModify)from(ChannelModify.class)
					.eq("tvSouChannelId", tvSouChannelId).eq("date", date).unique();
			if (channelModify == null || !channelModify.getMd5().equals(md5)) {
				logger.info("开始获取频道节目单,channel=" + channel.getName() + ",date=" + date);
				if(channelModify != null){					
					getSession().delete(channelModify);
				}
				catchProgram(channel.getId(), channel.getSoutvId(), date);
				channelModify = new ChannelModify();
				channelModify.setId(UID.create());
				channelModify.setDate(date);
				channelModify.setMd5(md5);
				channelModify.setTvSouChannelId(tvSouChannelId);
				getSession().save(channelModify);
			}else{
				logger.info("节目单已经获取,channel=" + channel.getName() + ",date=" + date);
			}
		}
	}
	
	public void catchProgram(String date) throws Exception{
		List<Channel> channels = from(Channel.class).list();
		for(Channel channel : channels){
			if(StringUtils.isBlank(channel.getSoutvId())){
				continue;
			}
			logger.info("开始获取频道节目单,channel=" + channel.getName() + ",date=" + date);
			catchProgram(channel.getId(), channel.getSoutvId(), date);
		}
	}
	
	public void clearModify() throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sqlUpdate("delete from depg_channel_modify where date<'"
				+ sdf.format(new Date()) + "'");
	}
	
	private static Document getDocument(String url) throws Exception{
		return getDocument(url, "UTF-8");
	}
	
	private static Document getDocument(String url, String encoding) throws Exception{
		URL theUrl = new URL(url);
		StringWriter sw = new StringWriter();
		IOUtils.copy(new InputStreamReader(theUrl.openStream(), encoding), sw);
		Document document = new SAXReader().read(new StringReader(sw.toString()));	
		return document;
	}
}