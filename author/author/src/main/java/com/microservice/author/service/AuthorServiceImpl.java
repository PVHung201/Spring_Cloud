package com.microservice.author.service;

import com.microservice.author.model.Author;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService{

    Author lemon = new Author(1, "Lemon", 23, "Male");
    Author jack = new Author(2, "Jack", 27, "Male");
    Author martin = new Author(3, "George RR Martin ", 26, "FeMale");

    @Override
    public List<Author> getAll() {

        return List.of(lemon, jack, martin);
    }

    @Override
    public Author get(int id) {
        List<Author> authorList = this.getAll();
        return authorList.stream().filter(e -> e.getId() == id).findAny().orElse(null);
    }
}
