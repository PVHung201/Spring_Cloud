package com.microservice.blog.service;

import com.microservice.blog.model.Author;
import com.microservice.blog.model.Blog;
import com.microservice.blog.model.BlogAuthorResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.resource.HttpResource;

import java.util.Optional;

public interface BlogService {
    Blog getBlog(int id);
    BlogAuthorResponse getDataResponse(Author author, Blog blog);
    boolean changeConfiguration();
    HttpResource printDocument(String id);
}
