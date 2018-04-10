package com.smola.demo.model.library;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    @Test
    void shouldCreateBookWithCorrectProperties() {
        Author author = new Author("Adam Mickiewicz");
        Tittle tittle = new Tittle("Pan Tadeusz");
        ISBN isbn = new ISBN("978-1-56619-909-4 ");

        Book bookUsingBuilder = new Book.BookBuilder(author,tittle).setIsbn(isbn).build();
        assertEquals(bookUsingBuilder.getAuthor(),author);
        assertEquals(bookUsingBuilder.getTittle(),tittle);
        assertEquals(bookUsingBuilder.getIsbn(),isbn);
    }
}