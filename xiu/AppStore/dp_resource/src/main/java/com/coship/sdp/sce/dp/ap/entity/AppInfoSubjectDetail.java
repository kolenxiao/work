/*
 * 文件名称：AppInfoDetail.java
 * 版    权：Shenzhen Coship Electronics Co.,Ltd. Copyright 2010-2020,  All rights reserved
 * 描    述：<概要描述>
 * 创 建 人：zhengxilian/906976
 * 创建时间：2013-04-22
 *
 * 修改记录：1.<修改时间>  <修改人>  <修改描述>
 *           2.<修改时间>  <修改人>  <修改描述>
 */
package com.coship.sdp.sce.dp.ap.entity;


/**
 * <功能描述>
 * @author ZhengXinlian/906976
 * @version [版本号, 2013-04-22]
 * @since [产品/模块版本]
 */
public class AppInfoSubjectDetail
{

	/**
	 *
	 */
	private static final long serialVersionUID = -464178267818448880L;

    private String subjectId;

    private AppInfoDetail appDetail;

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public AppInfoDetail getAppDetail() {
		return appDetail;
	}

	public void setAppDetail(AppInfoDetail appDetail) {
		this.appDetail = appDetail;
	}


}
