package com.amaap.BookInventoryDemo.service;

import com.amaap.BookInventoryDemo.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

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
}