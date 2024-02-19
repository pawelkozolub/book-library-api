package com.example.booklibraryapi.book;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {

    final JdbcTemplate jdbcTemplate;

    public BookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getAll() {
        return jdbcTemplate.query("SELECT id, title, author FROM books",
                BeanPropertyRowMapper.newInstance(Book.class));
    }

    public Book getById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT id, title, author FROM books WHERE id = ?",
                BeanPropertyRowMapper.newInstance(Book.class),
                id);
    }

    public void save(List<Book> books) {
        books.forEach(book -> jdbcTemplate
                .update("INSERT INTO books(title, author) VALUES (?, ?)", book.getTitle(), book.getAuthor())
        );
    }

    public void update(Book book) {
        jdbcTemplate.update("UPDATE books SET title=?, author=? WHERE id=?",
                book.getTitle(), book.getAuthor(), book.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM books WHERE id=?", id);
    }
}
