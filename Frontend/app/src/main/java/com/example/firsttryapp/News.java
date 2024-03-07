package com.example.firsttryapp;

import java.util.Date;

public class News {

    private String id, title, content, comments, newsDate;

    public News(String id, String title, String content, String comments, String newsDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.comments = comments;
        this.newsDate = newsDate;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(String newsDate) {
        this.newsDate = newsDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
