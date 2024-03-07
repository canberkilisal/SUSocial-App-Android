package com.SUSocial.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Post {

	@Id private String id;
	
	@DBRef
	private User postOwner;
	
	@DBRef
	private List<Comment> comments;

	private LocalDateTime  postTime;
	private String content;
	
	public Post() {
		// TODO Auto-generated constructor stub
	}

	public Post(String id, User postOwner, List<Comment> comments, LocalDateTime  postTime, String content, int likeCount) {
		super();
		this.id = id;
		this.postOwner = postOwner;
		this.comments = comments;
		this.postTime = postTime;
		this.content = content;
	}
	
	
	
	@Override
	public String toString() {
		return "Post [id=" + id + ", postOwner=" + postOwner + ", comments=" + comments + ", postTime=" + postTime + ", content=" + 
				content + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getPostOwner() {
		return postOwner;
	}

	public void setPostOwner(User postOwner) {
		this.postOwner = postOwner;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime  getPostTime() {
		return postTime;
	}

	public void setPostTime(LocalDateTime  postTime) {
		this.postTime = postTime;
	}
		
}
