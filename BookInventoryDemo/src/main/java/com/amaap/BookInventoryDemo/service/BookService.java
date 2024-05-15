package com.amaap.BookInventoryDemo.service;

import com.amaap.BookInventoryDemo.dao.BookRepository;
import com.amaap.BookInventoryDemo.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Book> getBookByName(String name) {
        return Optional.ofNullable(bookRepository.findByName(name).orElse(null));
    }

    public Optional<Book> updateBookDetails(String name, Book updatedBook) {
        return Optional.ofNullable(bookRepository.findByName(name)
                .map(existingBook -> {
                    existingBook.setAuthor(updatedBook.getAuthor());
                    existingBook.setPublication(updatedBook.getPublication());
                    existingBook.setPrice(updatedBook.getPrice());
                    existingBook.setQuantity(updatedBook.getQuantity());
                    return bookRepository.save(existingBook);
                })
                .orElse(null));
    }

    public boolean isBookExist(Book book) {
        if (book != null && book.getName() != null) {
            Optional<Book> existingBook = bookRepository.findByName(book.getName());
            return existingBook.isPresent();
        }
        return false;
    }

    public String deleteBookByName(String name) {
        return bookRepository.deleteByName(name);
    }
}
