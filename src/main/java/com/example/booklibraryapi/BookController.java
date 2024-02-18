package com.example.booklibraryapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/test")
    public int test() {
        return 0;
    }

    @GetMapping("/books")
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @GetMapping("/books/{id}")
    public Book getById(@PathVariable("id") int id) {
        return bookRepository.getById(id);
    }
}
