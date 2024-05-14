package com.amaap.BookInventoryDemo.service;

import com.amaap.BookInventoryDemo.dao.BookRepository;
import com.amaap.BookInventoryDemo.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public String deleteAll() {
        bookRepository.deleteAll();
        return "Deleted All Data";
    }

    public Book getBookByName(String name) {
        return bookRepository.findByName(name);
    }
}
