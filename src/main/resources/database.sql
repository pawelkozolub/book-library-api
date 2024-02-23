# Create a database
CREATE DATABASE book_library_api
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

# Create a table to store book entries
CREATE TABLE books (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(255),
                       author VARCHAR(255)
);

# Insert exemplary book entries
INSERT INTO books (title, author) VALUES
                                      ('The Snowman', 'Jo Nesbo'),
                                      ('The Girl with the Dragon Tattoo', 'Stieg Larsson'),
                                      ('The Redbreast', 'Jo Nesbo'),
                                      ('The Leopard', 'Jo Nesbo'),
                                      ('The Bat', 'Jo Nesbo'),
                                      ('The Redeemer', 'Jo Nesbo');

# Display inserted book entries
SELECT * FROM books;