package com.smola.demo.controller;

import com.smola.demo.model.library.Book;
import com.smola.demo.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        return libraryService.addBook(book);
    }

    @GetMapping("/books/{authorName}")
    ResponseEntity<Book> getBookByAuthor(@PathVariable String authorName){
        return libraryService.findByAuthor(authorName);
    }
}
