package com.amaap.BookInventoryDemo.controller;

import com.amaap.BookInventoryDemo.model.Book;
import com.amaap.BookInventoryDemo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BookController {
    @Autowired
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/addBook")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book savedBook = bookService.createBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> allBooks = bookService.getAllBooks();
        return ResponseEntity.status(HttpStatus.OK).body(allBooks);
    }

    @DeleteMapping("/deleteAllBooks")
    public String deleteAllBooks() {
        bookService.deleteAll();
        return "Deleted All Data";
    }

    @GetMapping("/book")
    public ResponseEntity<Book> getBookByName(@RequestParam(required = false) String name) {
        if (name == null || name.isEmpty())
            return ResponseEntity.badRequest().build();
        Book book = bookService.getBookByName(name);
        if (book != null)
            return ResponseEntity.ok(book);
        else
            return ResponseEntity.notFound().build();
    }
}
