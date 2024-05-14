package com.amaap.BookInventoryDemo.dao;

import com.amaap.BookInventoryDemo.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {

}
