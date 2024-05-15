package com.amaap.BookInventoryDemo.controller;

import com.amaap.BookInventoryDemo.model.Book;
import com.amaap.BookInventoryDemo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class BookController {
    @Autowired
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/addBook")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        if (bookService.isBookExist(book))
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        else {
            Book savedBook = bookService.createBook(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
        }
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

    @DeleteMapping("/deleteByName")
    public String deleteBookByName(@RequestParam String name) {
        bookService.deleteBookByName(name);
        return "Deleted Book";
    }


    @GetMapping("/book")
    public ResponseEntity<Optional<Book>> getBookByName(@RequestParam(required = false) String name) {
        if (name == null || name.isEmpty())
            return ResponseEntity.badRequest().build();
        Optional<Book> book = bookService.getBookByName(name);
        if (book == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(book);
    }

    @PutMapping("/updateBook")
    public ResponseEntity<Optional<Book>> updateBookDetails(@RequestParam(required = false) String name, @RequestBody Book book) {
        Optional<Book> updatedBook = bookService.updateBookDetails(name, book);
        if (updatedBook != null)
            return ResponseEntity.ok(updatedBook);
        else
            return ResponseEntity.notFound().build();
    }
}
