package com.smola.demo.model.library;

import javax.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "isbn_id")
    private ISBN isbn;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tittle_id")
    private Tittle tittle;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author;

    public Book() {
    }

    public Long getId() {
        return id;
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
