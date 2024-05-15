package com.amaap.BookInventoryDemo.dao;

import com.amaap.BookInventoryDemo.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String> {
    Optional<Book> findByName(String name);

    String deleteByName(String name);
}
