package com.smola.demo.repository;

import com.smola.demo.model.library.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends MongoRepository<Book, Long> {
}
