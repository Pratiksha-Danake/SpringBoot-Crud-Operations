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

    public Book updateBookDetails(String name, Book updatedBook) {
        Book existingBook = bookRepository.findByName(name);
        System.out.println(existingBook.getAuthor()+" "+existingBook.getName()+" "+existingBook.getQuantity());
        if (existingBook != null) {
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setPublication(updatedBook.getPublication());
            existingBook.setPrice(updatedBook.getPrice());
            existingBook.setQuantity(updatedBook.getQuantity());
            return bookRepository.save(existingBook);
        }
        return null;
    }
}
