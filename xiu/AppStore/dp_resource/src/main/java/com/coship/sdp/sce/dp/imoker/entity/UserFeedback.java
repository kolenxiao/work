package com.coship.sdp.sce.dp.imoker.entity;

import java.util.Date;

import javax.persistence.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.coship.sdp.access.entity.EntityObject;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserFeedback extends EntityObject
{
	private static final long serialVersionUID = -464072057385853188L;
	private String userEmail;
	private Date feedbackTime;
	private String context;
	
	public String getUserEmail()
	{
		return userEmail;
	}
	public void setUserEmail(String userEmail)
	{
		this.userEmail = userEmail;
	}
	public Date getFeedbackTime()
	{
		return feedbackTime;
	}
	public void setFeedbackTime(Date feedbackTime)
	{
		this.feedbackTime = feedbackTime;
	}
	public String getContext()
	{
		return context;
	}
	public void setContext(String context)
	{
		this.context = context;
	}
}
