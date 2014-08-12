package com.coship.depgm.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.coship.depgm.common.DepgmConfig;
import com.coship.depgm.common.HibernateBase;
import com.coship.depgm.model.Channel;
import com.coship.depgm.model.Program;
import com.coship.depgm.service.DateService.Week;

@Component
@Transactional
@SuppressWarnings("unchecked")
public class MscpService extends HibernateBase{
	@Autowired
	private FtpService ftpService;
	
	public void exportProgramToMscp() throws Exception{
		List<Channel> channels = from(Channel.class).eq("btv", true).notNull("soutvId").list();
		for(Channel channel : channels){
			exportProgramToMscp(channel.getServiceID());
		}
	}
	
	public void exportProgramToMscp(int serviceId) throws Exception{
		FTPClient ftpClient = null;
		try {
			String programContent = exportProgram(serviceId);
			if(StringUtils.isBlank(programContent)){
				return;
			}
			ftpClient = ftpService.connect(DepgmConfig.getMscpDir(),
					DepgmConfig.getMscpHost(), DepgmConfig.getMscpPort(),
					DepgmConfig.getMscpUser(), DepgmConfig.getMscpPwd());
			if (ftpClient != null) {
				ftpService.upload(ftpClient, "service" + serviceId + ".txt",
						new ByteArrayInputStream(programContent.getBytes("UTF-8")));
				logger.info("上传给MSCP节目单,serviceId=" + serviceId);
			}
		} catch (IOException e) {
			logger.error("upload poster with error", e);
		} finally {
			ftpService.close(ftpClient);
		}
	}
	
	public String exportProgram(int serviceId){
		Week nextWeek = DateService.getNextWeek();
		Map<String, List<Program>> weekPrograms = new LinkedHashMap<String, List<Program>>();
		for(Date date : nextWeek.getDays()){
			weekPrograms.put(getDayFmt(date), new ArrayList<Program>());
		}
		for(Program program : getWeekProgram(serviceId, nextWeek)){
			String dayFmt = getDayFmt(program.getEventDate());
			List<Program> programs = weekPrograms.get(dayFmt);
			programs.add(program);
		}
		
		StringBuffer sb = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		for(String dateFmt : weekPrograms.keySet()){
			sb.append(dateFmt	+ "\r\n\r\n");
			List<Program> programs = weekPrograms.get(dateFmt);
			if(programs.size() == 0){
				return null;
			}
			for(Program program : programs){
				sb.append(sdf.format(program.getBeginTime()) + "	"	+ program.getName() +"\r\n");
			}
			sb.append("\r\n");
		}
		return sb.toString().trim();
	}
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
	private static String[] week = {"日","一","二","三","四","五","六"};
	private String getDayFmt(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int index = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		return sdf.format(date) + " " + "星期" + week[index];
	}
	
	private List<Program> getWeekProgram(int serviceId, Week week){
		Query query = getSession().createSQLQuery("select p.id,p.beginTime,p.eventDate,p.name from depg_program p join " +
				"depg_channel c on p.channelId=c.id where c.serviceId=? and eventDate>=? and eventDate<=? order by p.beginTime");
		query.setInteger(0, serviceId);
		query.setDate(1, week.getBeginDate());
		query.setDate(2, week.getEndDate());
		query.setResultTransformer(Transformers.aliasToBean(Program.class));
		List<Program> programs = query.list();
		return programs;
	} 
}