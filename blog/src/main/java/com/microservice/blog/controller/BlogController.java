package com.microservice.blog.controller;

import com.microservice.blog.model.Author;
import com.microservice.blog.model.Blog;
import com.microservice.blog.model.BlogAuthorResponse;
import com.microservice.blog.proxy.AuthorProxy;
import com.microservice.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RefreshScope
public class BlogController {

    @Value("${book}")
    private String author;

    @Autowired
    private BlogService service;


    @Autowired
    private AuthorProxy authorProxy;

    @GetMapping("/test")
    public String getAuthor() {
        return author;
    }

    @GetMapping("/blog/{id}")
    public BlogAuthorResponse getBlog(@PathVariable int id) {
        //Author author = authorProxy.getAuthor(id).getBody();
        Blog blog = service.getBlog(id);
        BlogAuthorResponse blogAuthorResponse = service.getDataResponse(null, blog);
        return blogAuthorResponse;
    }
}
