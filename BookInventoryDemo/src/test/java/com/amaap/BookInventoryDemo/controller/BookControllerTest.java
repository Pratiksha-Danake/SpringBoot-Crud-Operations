package com.amaap.BookInventoryDemo.controller;

import com.amaap.BookInventoryDemo.model.Book;
import com.amaap.BookInventoryDemo.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {
    @MockBean
    BookService bookService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateABookInDatabase() throws Exception {
        // arrange
        Book book = new Book("Programming In Java", "James Gosling", "Tech Publication", 500, 100);

        when(bookService.createBook(any(Book.class))).thenAnswer(invocation -> {
            Book createdBook = invocation.getArgument(0);
            return createdBook;
        });

        // act && assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/addBook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Programming In Java\",\"author\":\"James Gosling\",\"publication\":\"Tech Publication\",\"price\":500,\"quantity\":100}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Programming In Java"))
                .andExpect(jsonPath("$.author").value("James Gosling"))
                .andExpect(jsonPath("$.publication").value("Tech Publication"))
                .andExpect(jsonPath( "$.price").value(500))
                .andExpect(jsonPath("$.quantity").value(100));
    }

    @Test
    void shouldReturnAllBooksFromDatabase() throws Exception {
        // arrange
        List<Book> books = new ArrayList<>();
        books.add(new Book("Programming In Java", "James Gosling", "Tech Publication", 500, 100));
        books.add(new Book("Clean Code", "Uncle Bob", "Info Publication", 300, 50));

        when(bookService.getAllBooks()).thenReturn(books);

        // act && assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Programming In Java"))
                .andExpect(jsonPath("$[0].author").value("James Gosling"))
                .andExpect(jsonPath("$[0].publication").value("Tech Publication"))
                .andExpect(jsonPath("$[0].price").value(500))
                .andExpect(jsonPath("$[0].quantity").value(100))
                .andExpect(jsonPath("$[1].name").value("Clean Code"))
                .andExpect(jsonPath("$[1].author").value("Uncle Bob"))
                .andExpect(jsonPath("$[1].publication").value("Info Publication"))
                .andExpect(jsonPath("$[1].price").value(300))
                .andExpect(jsonPath("$[1].quantity").value(50));
    }

    @Test
    void shouldDeleteAllBookDetailsFromDatabase() throws Exception {
        // arrange
        Book book1 = new Book("Programming In Java", "James Gosling", "Tech Publication", 500, 100);
        Book book2 = new Book("Clean Code", "Uncle Bob", "Info Publication", 300, 50);
        bookService.createBook(book1);
        bookService.createBook(book2);

        // act
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/deleteAllBooks"))
                .andExpect(status().isOk());

        // assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isEmpty());
    }
}
