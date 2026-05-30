package com.microservice.blog.controller;

import com.microservice.blog.model.Author;
import com.microservice.blog.model.Blog;
import com.microservice.blog.model.BlogAuthorResponse;
import com.microservice.blog.proxy.AuthorProxy;
import com.microservice.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RefreshScope
public class BlogController {

    @Autowired
    private BlogService service;

    @Autowired
    private AuthorProxy authorProxy;

    @GetMapping("/blog/{id}")
    public BlogAuthorResponse getBlog(@PathVariable int id) {
        Author author = authorProxy.getAuthor(id).getBody();
        Blog blog = service.getBlog(id);
        BlogAuthorResponse blogAuthorResponse = service.getDataResponse(author, blog);
        return blogAuthorResponse;
    }

    @PutMapping("/configuration/change")
    public ResponseEntity<Boolean> changeConfiguration() {
        return new ResponseEntity<>(service.changeConfiguration(), HttpStatus.OK);
    }

    @PostMapping("/printDocument")
    public ResponseEntity<Resource> printDocument (String id) {
       Resource resource = service.printDocument(id);

       String fileName = Boolean.FALSE.equals(ObjectUtils.isEmpty(resource.getFilename())) ? resource.getFilename() : "lemon.pdf";

       return ResponseEntity.ok()
               .contentType(MediaType.APPLICATION_PDF)
               .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\n" + fileName + "\n")
               .body(resource);
    }
}
