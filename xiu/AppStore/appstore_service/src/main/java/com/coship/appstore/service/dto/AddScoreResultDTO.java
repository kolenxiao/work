package com.coship.appstore.service.dto;

public class AddScoreResultDTO {
	
	/**
	 * 是否重复评分
	 */
	private Integer isRepeatScore;
	
	/**
	 * 平均评分
	 */
	private double avgScore;
	
    /**
     * 评分人数
     */
    private long scoreCount;

	public Integer getIsRepeatScore() {
		return isRepeatScore;
	}

	public void setIsRepeatScore(Integer isRepeatScore) {
		this.isRepeatScore = isRepeatScore;
	}

	public double getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(double avgScore) {
		this.avgScore = avgScore;
	}

	public long getScoreCount() {
		return scoreCount;
	}

	public void setScoreCount(long scoreCount) {
		this.scoreCount = scoreCount;
	}
	

}
