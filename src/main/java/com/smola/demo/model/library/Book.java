package com.smola.demo.model.library;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Book {
    private long id;
    private ISBN isbn;
    private Tittle tittle;
    private Author author;

    public Book() {
    }

    private Book(BookBuilder bookBuilder) {
        this.isbn = bookBuilder.isbn;
        this.tittle = bookBuilder.tittle;
        this.author = bookBuilder.author;
    }

    public ISBN getIsbn() {
        return isbn;
    }

    public Tittle getTittle() {
        return tittle;
    }

    public Author getAuthor() {
        return author;
    }

    public static class BookBuilder {
        private Author author;
        private Tittle tittle;
        private ISBN isbn;

        public BookBuilder(Author author, Tittle tittle) {
            this.author = author;
            this.tittle = tittle;
        }

        public BookBuilder setIsbn(ISBN isbn) {
            this.isbn = isbn;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }
}
