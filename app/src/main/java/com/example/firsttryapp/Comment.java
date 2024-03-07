package com.example.firsttryapp;

public class Comment {

    private String id, postTime, content;
    private User postOwner;

    public Comment(String id, String content, String postTime, User postOwner) {
        this.id = id;
        this.content = content;
        this.postTime = postTime;
        this.postOwner = postOwner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
