package com.SUSocial.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class SUNew {

	@Id private String id;
	
	@DBRef
	private List<Comment> comments;
	
	private LocalDateTime  sunewTime;
	private String title;
	private String content;
	

	public SUNew() {
		// TODO Auto-generated constructor stub
	}


	public SUNew(String id, List<Comment> comments, LocalDateTime sunewTime, String title, String content) {
		super();
		this.id = id;
		this.comments = comments;
		this.sunewTime = sunewTime;
		this.title = title;
		this.content = content;
	}


	@Override
	public String toString() {
		return "SUNew [id=" + id + ", comments=" + comments + ", sunewTime=" + sunewTime + ", title=" + title
				+ ", content=" + content + "]";
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public List<Comment> getComments() {
		return comments;
	}


	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}


	public LocalDateTime getSunewTime() {
		return sunewTime;
	}


	public void setSunewTime(LocalDateTime sunewTime) {
		this.sunewTime = sunewTime;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}
	
}
