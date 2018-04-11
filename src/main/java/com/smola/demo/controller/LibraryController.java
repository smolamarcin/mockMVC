package com.smola.demo.controller;

import com.smola.demo.model.library.Book;
import com.smola.demo.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @GetMapping("/books")
    ResponseEntity<Iterable<Book>> getAllBooks() {
        return ResponseEntity.status(HttpStatus.OK).body(libraryService.getAllBooks());
    }

    @PostMapping("/books")
    ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book toAdd = libraryService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(toAdd);
    }
}
