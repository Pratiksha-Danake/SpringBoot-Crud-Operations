package com.amaap.BookInventoryDemo.controller;

import com.amaap.BookInventoryDemo.model.Book;
import com.amaap.BookInventoryDemo.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {
    @MockBean
    BookService bookService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

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
                .andExpect(jsonPath("$.price").value(500))
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

    @Test
    void shouldGetBookByNameIfBookPresent() throws Exception {
        // arrange
        Book book = new Book("Programming In Java", "James Gosling", "Tech Publication", 500, 100);
        when(bookService.getBookByName("Programming In Java")).thenReturn(book);

        // act & assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/book")
                        .param("name", "Programming In Java")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Programming In Java"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value("James Gosling"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.publication").value("Tech Publication"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(500))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity").value(100));
    }

    @Test
    void shouldReturnResponseNotFoundIfBookByNameIsNotPresent() throws Exception {
        // arrange
        when(bookService.getBookByName("Programming In Java")).thenReturn(null);

        // act & assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/book")
                        .param("name", "Programming In Java")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void shouldUpdateBookDetails() throws Exception {
        // arrange
        Book existingBook = new Book("Programming In Java", "James Gosling", "Tech Publication", 500, 100);
        Book updatedBook = new Book("Programming In Java", "Pratiksha Danake", "My Publication", 600, 200);
        when(bookService.updateBookDetails(eq("Programming In Java"), any(Book.class))).thenReturn(updatedBook);

        // act & assert
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/updateBook")
                        .param("name", "Programming In Java")
                        .param("book", String.valueOf(updatedBook))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedBook)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Programming In Java"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value("Pratiksha Danake"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.publication").value("My Publication"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(600))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity").value(200));
    }
}
