package com.smola.demo.service;

import com.smola.demo.model.library.Book;
import com.smola.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class LibraryService {
    @Autowired
    private BookRepository bookRepository;

    public Book addBook(Book book) {
        return bookRepository.save(book);
//        return null;
    }

    public Collection<Book> getAllBooks() {
//        return bookRepository.findAll();
        return null;
    }
}
