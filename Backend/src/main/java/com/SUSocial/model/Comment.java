package com.SUSocial.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Comment {
	
	@Id private String id;
	
	@DBRef
	private User commentOwner;

	private LocalDateTime  commentTime;
	private String content;
	
	public Comment() {
		// TODO Auto-generated constructor stub
	}

	public Comment(String id, User commentOwner, LocalDateTime commentTime, String content) {
		super();
		this.id = id;
		this.commentOwner = commentOwner;
		this.commentTime = commentTime;
		this.content = content;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", commentOwner=" + commentOwner + ", commentTime=" + commentTime + ", content=" + content
				+ "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getCommentOwner() {
		return commentOwner;
	}

	public void setCommentOwner(User commentOwner) {
		this.commentOwner = commentOwner;
	}

	public LocalDateTime getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(LocalDateTime commentTime) {
		this.commentTime = commentTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
