package com.coship.depgm.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;

public class DateService {
	public static Date getDate(int days){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, days);
		calendar = DateUtils.truncate(calendar, Calendar.DATE);
		return calendar.getTime();
	}
	
	public static Week getNextWeek(){
		int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		int addDayToMon = (9 - day) % 7;
		Week week = new Week();
		week.setBeginDate(getDate(addDayToMon));
		week.setEndDate(DateService.getDate(addDayToMon + 6));
		return week;
	}
	
	public static class Week {
		private Date beginDate;
		private Date endDate;

		public Date getBeginDate() {
			return beginDate;
		}

		public void setBeginDate(Date beginDate) {
			this.beginDate = beginDate;
		}

		public Date getEndDate() {
			return endDate;
		}

		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}
		
		public List<Date> getDays() {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(beginDate);
			List<Date> days = new ArrayList<Date>();
			for (int i = 1; i <= 7; i++) {
				days.add(calendar.getTime());
				calendar.add(Calendar.DAY_OF_WEEK, 1);
			}
			return days;
		}
	}
}