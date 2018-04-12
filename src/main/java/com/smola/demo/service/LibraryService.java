package com.smola.demo.service;

import com.smola.demo.model.library.Author;
import com.smola.demo.model.library.Book;
import com.smola.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibraryService {
    @Autowired
    private BookRepository bookRepository;

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public ResponseEntity<Book> findByAuthor(String authorName) {
        Author author = new Author(authorName);
        Optional<Book> bookToFind = bookRepository.findByAuthor(author);
        if (bookToFind.isPresent()) {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .body(bookRepository.findByAuthor(author).get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(bookRepository.findByAuthor(author).get());
        }

    }
}
