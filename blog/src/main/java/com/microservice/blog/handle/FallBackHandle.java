//package com.microservice.blog.handle;
//
//import com.microservice.blog.model.Author;
//import com.microservice.blog.proxy.AuthorProxy;
//import org.springframework.cloud.client.circuitbreaker.NoFallbackAvailableException;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//
//@Component
//public class FallBackHandle implements AuthorProxy {
//    @Override
//    public ResponseEntity<Author> getAuthor(int id) {
//        System.out.println("Time out happened");
//        throw new NoFallbackAvailableException("Boom!", new RuntimeException());
//    }
//}
