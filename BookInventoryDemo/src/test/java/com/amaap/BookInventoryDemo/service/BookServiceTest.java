package com.amaap.BookInventoryDemo.service;

import com.amaap.BookInventoryDemo.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BookServiceTest {
    @Autowired
    BookService bookService = mock(BookService.class);

    @Test
    void shouldAddABookToDatabase() {
        // arrange
        Book book = new Book("Programming In Java", "James Gosling", "Tech Publication", 500, 100);

        when(bookService.createBook(any(Book.class))).thenAnswer(invocation -> {
            Book createdBook = invocation.getArgument(0);
            return createdBook;
        });

        // act && assert
        Book actual = bookService.createBook(book);
        assertEquals("Programming In Java", actual.getName());
        assertEquals("James Gosling", actual.getAuthor());
        assertEquals("Tech Publication", actual.getPublication());
        assertEquals(500, actual.getPrice());
        assertEquals(100, actual.getQuantity());
    }

    @Test
    void shouldGetAllBooksFromDatabase() {
        // arrange
        List<Book> expected = new ArrayList<>();
        expected.add(new Book("Programming In Java", "James Gosling", "Tech Publication", 500, 100));
        expected.add(new Book("Clean Code", "Uncle Bob", "Info Publication", 300, 50));

        when(bookService.getAllBooks()).thenReturn(expected);

        // act && assert
        List<Book> actual = bookService.getAllBooks();
        assertEquals(expected, actual);
    }

    @Test
    void shouldDeleteAllBooksFromDatabase() {
        // arrange
        String expected = "Deleted All Data";
        when(bookService.deleteAll()).thenReturn("Deleted All Data");

        // act && assert
        String actual = bookService.deleteAll();
        assertEquals(expected, actual);
    }

    @Test
    void shouldGetBookByNameFromDatabase() {
        // arrange
        String name = "Clean Code";
        Book expected = new Book("Clean Code", "Uncle Bob", "Info Publication", 100, 50);
        when(bookService.getBookByName(name)).thenReturn(Optional.of(expected));

        // act && assert
        Optional<Book> actual = bookService.getBookByName(name);
        assertEquals(Optional.of(expected), actual);
    }

    @Test
    void shouldUpdateBookDetailsByBookName() {
        // arrange
        String name = "Programming In Java";
        Book existingBook = new Book("Programming In Java", "James Gosling", "Tech Publications", 500, 50);
        Book updatedBook = new Book("Programming In Java", "Pratiksha Danake", "My Publications", 500, 50);

        when(bookService.updateBookDetails(name, existingBook)).thenReturn(Optional.of(updatedBook));

        // act
        Optional<Book> actual = bookService.updateBookDetails(name, existingBook);

        // assert
        assertEquals(Optional.of(updatedBook), actual);
    }
}