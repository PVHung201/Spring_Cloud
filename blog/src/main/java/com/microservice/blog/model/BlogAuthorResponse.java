package com.microservice.blog.model;

import java.util.Date;

public class BlogAuthorResponse {
    private String name;
    private Date postDate;
    private String content;
    private Author author;

    public BlogAuthorResponse(String name, Date postDate, String content, Author author) {
        this.name = name;
        this.postDate = postDate;
        this.content = content;
        this.author = author;
    }

    public BlogAuthorResponse(Author author, Blog blog) {
        this.name = blog.getName();
        this.postDate = blog.getPostDate();
        this.content = blog.getContent();
        this.author = author;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
