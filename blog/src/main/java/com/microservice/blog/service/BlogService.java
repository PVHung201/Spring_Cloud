package com.microservice.blog.service;

import com.microservice.blog.model.Author;
import com.microservice.blog.model.Blog;
import com.microservice.blog.model.BlogAuthorResponse;

import java.util.Optional;

public interface BlogService {
    Blog getBlog(int id);
    BlogAuthorResponse getDataResponse(Author author, Blog blog);
}
