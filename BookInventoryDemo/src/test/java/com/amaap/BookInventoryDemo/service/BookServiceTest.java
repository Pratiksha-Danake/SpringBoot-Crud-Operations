//package com.amaap.BookInventoryDemo.service;
//
//import com.amaap.BookInventoryDemo.dao.BookRepository;
//import com.amaap.BookInventoryDemo.model.Book;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//class BookServiceTest {
//    @MockBean
//    BookRepository bookRepository;
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    void shouldCreateABook() throws Exception {
//        // arrange
//        Book book = new Book("Programming In Java", "James Gosling", "Tech Publication", 500, 100);
////        when(bookService.createBook(book)).thenReturn(book);
//
//        when(bookRepository.saveBook(any(Book.class))).thenAnswer(invocation -> {
//            Book createdBook = invocation.getArgument(0);
//            return createdBook;
//        });
//
//        // act && assert
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/book")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"name\":\"Programming In Java\",\"author\":\"James Gosling\",\"publication\":\"Tech Publication\",\"price\":500,\"quantity\":100}"))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.name").value("Programming In Java"))
//                .andExpect(jsonPath("$.author").value("James Gosling"))
//                .andExpect(jsonPath("$.publication").value("Tech Publication"))
//                .andExpect(jsonPath("$.price").value(500))
//                .andExpect(jsonPath("$.quantity").value(100));
//    }
//}