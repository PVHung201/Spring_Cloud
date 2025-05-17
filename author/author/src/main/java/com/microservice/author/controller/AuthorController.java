package com.microservice.author;

import com.microservice.author.model.Author;
import com.microservice.author.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class AuthorController {

    @Autowired
    private AuthorService service;

    @GetMapping("/author/get/list")
    public ResponseEntity<List<Author>> getAllAuthor() {

        List<Author> authorList = service.getAll();

        return new ResponseEntity<>(authorList, HttpStatus.OK);
    }

    @GetMapping("/author/get/{id}")
    public ResponseEntity<Author> getAuthor(@PathVariable int id) throws InterruptedException {

        Author author = service.get(id);

        Thread.sleep(9000);
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(author);

        return new ResponseEntity<>(author, HttpStatus.OK);
    }
}
