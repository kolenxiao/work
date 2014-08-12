package com.coship.depgm.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * MSCP 媒资对象
 * 
 * @author 907900
 * 
 */
@Entity
@Table(name = "t_asset")
public class Asset implements Serializable {
	private static final long serialVersionUID = 7071644533339845299L;

	@Id
	private String id;

	private String assetName;
	private String assetCode;
	private String mlName;
	private String assetENName;
	private String captionName;
	private String originName;
	private String issuerName;
	private String assetTypes;
	private Integer chapters;
	private String director;
	private String leadingActor;
	private String screenWriter;
	@Column(length = 2000)
	private String keyWord;
	private String prize;
	private Integer status;
	private Integer recommendationLevel;
	private Date publishDate;
	private Integer type;
	private String assetTypeIds;
	private String providerID;
	private String assetID;
	private Integer videoType;
	@Column(length = 2000)
	private String tag;
	private Integer viewLevel;
	private String letter;
	private String letterAb;
	private String showType;
	@Column(length = 2000)
	private String summaryLong;
	@Column(length = 2000)
	private String summaryMedium;
	@Column(length = 2000)
	private String summaryShort;
	private Date createTime;
	private Integer platform;
	private Integer assetOrigin;
	private Integer score;
	private Integer series;
	@Transient
	private List<AssetPackageMap> resourceCodeList; // 子资源列表

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public String getAssetCode() {
		return assetCode;
	}

	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}

	public String getMlName() {
		return mlName;
	}

	public void setMlName(String mlName) {
		this.mlName = mlName;
	}

	public String getAssetENName() {
		return assetENName;
	}

	public void setAssetENName(String assetENName) {
		this.assetENName = assetENName;
	}

	public String getCaptionName() {
		return captionName;
	}

	public void setCaptionName(String captionName) {
		this.captionName = captionName;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getIssuerName() {
		return issuerName;
	}

	public void setIssuerName(String issuerName) {
		this.issuerName = issuerName;
	}

	public String getAssetTypes() {
		return assetTypes;
	}

	public void setAssetTypes(String assetTypes) {
		this.assetTypes = assetTypes;
	}

	public Integer getChapters() {
		return chapters;
	}

	public void setChapters(Integer chapters) {
		this.chapters = chapters;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getLeadingActor() {
		return leadingActor;
	}

	public void setLeadingActor(String leadingActor) {
		this.leadingActor = leadingActor;
	}

	public String getScreenWriter() {
		return screenWriter;
	}

	public void setScreenWriter(String screenWriter) {
		this.screenWriter = screenWriter;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getPrize() {
		return prize;
	}

	public void setPrize(String prize) {
		this.prize = prize;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getRecommendationLevel() {
		return recommendationLevel;
	}

	public void setRecommendationLevel(Integer recommendationLevel) {
		this.recommendationLevel = recommendationLevel;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getAssetTypeIds() {
		return assetTypeIds;
	}

	public void setAssetTypeIds(String assetTypeIds) {
		this.assetTypeIds = assetTypeIds;
	}

	public String getProviderID() {
		return providerID;
	}

	public void setProviderID(String providerID) {
		this.providerID = providerID;
	}

	public String getAssetID() {
		return assetID;
	}

	public void setAssetID(String assetID) {
		this.assetID = assetID;
	}

	public Integer getVideoType() {
		return videoType;
	}

	public void setVideoType(Integer videoType) {
		this.videoType = videoType;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Integer getViewLevel() {
		return viewLevel;
	}

	public void setViewLevel(Integer viewLevel) {
		this.viewLevel = viewLevel;
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public String getLetterAb() {
		return letterAb;
	}

	public void setLetterAb(String letterAb) {
		this.letterAb = letterAb;
	}

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

	public String getSummaryLong() {
		return summaryLong;
	}

	public void setSummaryLong(String summaryLong) {
		this.summaryLong = summaryLong;
	}

	public String getSummaryMedium() {
		return summaryMedium;
	}

	public void setSummaryMedium(String summaryMedium) {
		this.summaryMedium = summaryMedium;
	}

	public String getSummaryShort() {
		return summaryShort;
	}

	public void setSummaryShort(String summaryShort) {
		this.summaryShort = summaryShort;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}

	public Integer getAssetOrigin() {
		return assetOrigin;
	}

	public void setAssetOrigin(Integer assetOrigin) {
		this.assetOrigin = assetOrigin;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getSeries() {
		return series;
	}

	public void setSeries(Integer series) {
		this.series = series;
	}

	public List<AssetPackageMap> getResourceCodeList() {
		return resourceCodeList;
	}

	public void setResourceCodeList(List<AssetPackageMap> resourceCodeList) {
		this.resourceCodeList = resourceCodeList;
	}

}