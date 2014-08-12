package com.coship.depgm.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Index;



@Entity
@Table(name = "depg_program_chapter")
public class ProgramChapter implements Serializable{
	private static final long serialVersionUID = 3436960463255977490L;
	@Id
	private String id;
	//节目ID
	@Index(name="contentId")
	private String contentId;
	@Transient
	private String contentName;
	//海报
	private String poster;
	//集数
	@Index(name="chapter")
	private int chapter;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public int getChapter() {
		return chapter;
	}

	public void setChapter(int chapter) {
		this.chapter = chapter;
	}

	public String getContentName() {
		return contentName;
	}

	public void setContentName(String contentName) {
		this.contentName = contentName;
	}
}