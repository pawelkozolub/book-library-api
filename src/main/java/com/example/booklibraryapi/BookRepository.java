package com.example.booklibraryapi;

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
}
