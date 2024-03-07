package com.example.firsttryapp;

import androidx.annotation.Nullable;

import java.util.List;

public class Post {

    private String id, postTime, content;

    private List<Comment> comments;
    private User postOwner;

    public Post(String id, @Nullable List<Comment> comments, String postTime, String content, User postOwner) {
        this.id = id;
        this.comments = comments;
        this.postTime = postTime;
        this.content = content;
        this.postOwner = postOwner;
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

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getPostOwner() {
        return postOwner;
    }

    public void setPostOwner(User postOwner) {
        this.postOwner = postOwner;
    }
}
