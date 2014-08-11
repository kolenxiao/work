package com.coship.sdp.sce.dp.comment.service.impl;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import com.coship.sdp.sce.dp.comment.entity.AppScoreSummaryEntity;

public class AppCommentHelper {

	/**
	 * 对平均分进行四舍五入处理
	 * @param avgScore
	 * @return
	 */
	public static double processAvgScore(Double avgScore) {
		BigDecimal avgScoreDecimal = new BigDecimal(avgScore);
		avgScoreDecimal = avgScoreDecimal.setScale(1, BigDecimal.ROUND_HALF_UP);
		double avgScoreDouble = avgScoreDecimal.doubleValue();
		if (avgScoreDouble > 5) {
			avgScoreDouble = 5;
		}
		return avgScoreDouble;
	}

	
	/**
	 * 对平均分进行星值计算
	 * @param avgScore
	 * @return
	 */
	public static double processAvgStar(Double avgScore) {
		int zhengshu = (int) avgScore.doubleValue();
		String xiaoshuStr = StringUtils.substringAfter(String.valueOf(avgScore), ".");
		int xiaoshu = Integer.valueOf(xiaoshuStr).intValue();

		double avgStar = zhengshu;
		if (xiaoshu >= 8) {
			avgStar = avgStar + 1;
		} else if (xiaoshu >= 4) {
			avgStar = avgStar + 0.5;
		}
		if (avgStar > 5) {
			avgStar = 5;
		}else if(avgStar < 3){
			avgStar = 3;
		}
		return avgStar;
	}


}
