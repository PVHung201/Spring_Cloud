package com.microservice.blog.service;

import com.microservice.blog.model.Author;
import com.microservice.blog.model.Blog;
import com.microservice.blog.model.BlogAuthorResponse;
import com.microservice.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService{

    @Autowired
    private BlogRepository repository;

    @Override
    public Blog getBlog(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public BlogAuthorResponse getDataResponse(Author author, Blog blog) {
        return new BlogAuthorResponse(author, blog);
    }
}
