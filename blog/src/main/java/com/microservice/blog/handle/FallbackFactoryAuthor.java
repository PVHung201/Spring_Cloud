package com.microservice.blog.handle;

import com.microservice.blog.model.Author;
import com.microservice.blog.proxy.AuthorProxy;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class FallbackFactoryAuthor implements FallbackFactory<AuthorProxy> {
    @Override
    public AuthorProxy create(Throwable cause) {
        return id -> {
            System.out.println("⚠️ Fallback triggered. Cause: " + cause);
            Author fallbackAuthor = new Author(id, "Don't know", 0, null);
            return ResponseEntity
                    .status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body(fallbackAuthor);
        };
    }
}
