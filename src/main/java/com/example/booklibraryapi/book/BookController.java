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
        return "New books added: \n" + books;
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable("id") int id) {
        return bookRepository.getById(id);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") int id, @RequestBody Book updatedBook) {
        Book book = bookRepository.getById(id);
        if (book != null) {
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            bookRepository.update(book);
            return "Updated book: \n" + book;
        }
        return "Book is not found. Cannot update.";
    }

    @PatchMapping("/{id}")
    public String partialUpdate(@PathVariable("id") int id, @RequestBody Book updatedBook) {
        Book book = bookRepository.getById(id);
        if (book != null) {
            if (updatedBook.getTitle() != null) book.setTitle(updatedBook.getTitle());
            if (updatedBook.getAuthor() != null) book.setAuthor(updatedBook.getAuthor());
            bookRepository.update(book);
            return "Updated book: \n" + book;
        }
        return "Book is not found. Cannot update.";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        Book book = bookRepository.getById(id);
        if (book != null) {
            bookRepository.delete(id);
            return "Book deleted: \n" + book;
        }
        return "Book is not found. Cannot delete.";
    }
}
