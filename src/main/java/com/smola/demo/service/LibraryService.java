package com.smola.demo.service;

import com.smola.demo.model.library.Book;
import com.smola.demo.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class LibraryService {
    @Autowired
    private LibraryRepository libraryRepository;

    public Book addBook(Book book) {
//        return libraryRepository.save(book);
        return null;
    }

    public Collection<Book> getAllBooks() {
//        return libraryRepository.findAll();
        return null;
    }
}
