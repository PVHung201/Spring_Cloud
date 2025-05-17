package com.microservice.author.service;

import com.microservice.author.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAll();

    Author get(int id);
}
