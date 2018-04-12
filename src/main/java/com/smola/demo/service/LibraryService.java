package com.smola.demo.service;

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

    public ResponseEntity<Book> addBook(Book book) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookRepository.save(book));
    }

    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public ResponseEntity<Book> findByAuthor(String authorName) {
        Optional<Book> bookToFind = bookRepository.findByAuthor_Name(authorName);
        if (bookToFind.isPresent()) {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .body(bookRepository.findByAuthor_Name(authorName).get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
}
