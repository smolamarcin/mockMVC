package com.smola.demo.repository;

import com.smola.demo.model.library.Author;
import com.smola.demo.model.library.Book;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
    Optional<Book> findByAuthor(Author author);
}
