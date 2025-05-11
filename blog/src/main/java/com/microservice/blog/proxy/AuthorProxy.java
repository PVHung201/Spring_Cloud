package com.microservice.blog.proxy;

import com.microservice.blog.configuration.FeignClientConfig;
import com.microservice.blog.handle.FallbackFactoryAuthor;
import com.microservice.blog.model.Author;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "author", url = "http://localhost:8080"
        , configuration = FeignClientConfig.class
        ,fallbackFactory= FallbackFactoryAuthor.class
        )
public interface AuthorProxy {
    @GetMapping("/author/get/{id}")
    public ResponseEntity<Author> getAuthor(@PathVariable int id);

}
