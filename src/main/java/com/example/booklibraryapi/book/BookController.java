package com.example.booklibraryapi.book;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

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
        return bookRepository.getById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") int id, @RequestBody Book updatedBook) {
        Optional<Book> book = bookRepository.getById(id);
        if (book.isPresent()) {
            book.get().setTitle(updatedBook.getTitle());
            book.get().setAuthor(updatedBook.getAuthor());
            bookRepository.update(book.get());
            return "Updated book: \n" + book.get();
        }
        return "Book is not found. Cannot update.";
    }

    @PatchMapping("/{id}")
    public String partialUpdate(@PathVariable("id") int id, @RequestBody Book updatedBook) {
        Optional<Book> book = bookRepository.getById(id);
        if (book.isPresent()) {
            if (updatedBook.getTitle() != null) book.get().setTitle(updatedBook.getTitle());
            if (updatedBook.getAuthor() != null) book.get().setAuthor(updatedBook.getAuthor());
            bookRepository.update(book.get());
            return "Updated book: \n" + book.get();
        }
        return "Book is not found. Cannot update.";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        Optional<Book> book = bookRepository.getById(id);
        if (book.isPresent()) {
            bookRepository.delete(id);
            return "Book deleted: \n" + book.get();
        }
        return "Book is not found. Cannot delete.";
    }
}
