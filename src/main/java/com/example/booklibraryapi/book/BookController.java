package com.example.booklibraryapi.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @PostMapping
    public String add(@RequestBody List<Book> books) {
        bookRepository.save(books);
        return "New books added";
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable("id") int id) {
        return bookRepository.getById(id);
    }
}
