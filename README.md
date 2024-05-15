# Book Inventory Demo

This is a simple RESTful API for managing a book inventory. It allows you to perform CRUD operations (Create, Read, Update, Delete) on books.

## Features

- Add a new book.
- Retrieve all books.
- Retrieve a specific book by its name.
- Update details of a book.
- Delete a book.

## Technologies Used

- Java
- Spring Boot
- MongoDB
# Book Inventory Demo

## BookController

The `BookController` class handles HTTP requests related to book management in the inventory.

### Attributes

- **bookService**: An instance of the `BookService` class to interact with the book-related business logic.

### Behavior

- **Add Book**:
  - Endpoint: POST /api/v1/addBook
  - Description: Adds a new book to the inventory.
  
- **Get All Books**:
  - Endpoint: GET /api/v1/books
  - Description: Retrieves all books from the inventory.
  
- **Delete All Books**:
  - Endpoint: DELETE /api/v1/deleteAllBooks
  - Description: Deletes all books from the inventory.
  
- **Delete Book by Name**:
  - Endpoint: DELETE /api/v1/deleteByName
  - Parameters: name (String)
  - Description: Deletes a book from the inventory by its name.
  
- **Get Book by Name**:
  - Endpoint: GET /api/v1/book
  - Parameters: name (String)
  - Description: Retrieves a specific book from the inventory by its name.
  
- **Update Book Details**:
  - Endpoint: PUT /api/v1/updateBook
  - Parameters: name (String), book (Request Body)
  - Description: Updates details of a book in the inventory.

## Book Model

The `Book` class represents a book entity with attributes such as name, author, publication, price, and quantity.

### Attributes

- **name**: The name of the book.
- **author**: The author of the book.
- **publication**: The publication of the book.
- **price**: The price of the book.
- **quantity**: The quantity of the book.

### Constructors

- **Book(String name, String author, String publication, double price, int quantity)**: Initializes a new instance of the `Book` class with the specified attributes.

### Methods
- Getters and Setters

## BookService

The `BookService` class provides methods to interact with the book repository.

### Dependencies

- **bookRepository**: An instance of the `BookRepository` interface for accessing book data.

### Methods

- **createBook(Book book)**:
  - Description: Creates a new book in the inventory.
  - Parameters: 
    - `book` (Book): The book object to be created.
  - Returns: 
    - The created book object.
  
- **getAllBooks()**:
  - Description: Retrieves all books from the inventory.
  - Returns: 
    - A list of all books.
  
- **deleteAll()**:
  - Description: Deletes all books from the inventory.
  - Returns: 
    - A message indicating the deletion status.
  
- **getBookByName(String name)**:
  - Description: Retrieves a book from the inventory by its name.
  - Parameters: 
    - `name` (String): The name of the book to retrieve.
  - Returns: 
    - An optional containing the book object, or empty if not found.
  
- **updateBookDetails(String name, Book updatedBook)**:
  - Description: Updates details of a book in the inventory.
  - Parameters: 
    - `name` (String): The name of the book to update.
    - `updatedBook` (Book): The updated book object.
  - Returns: 
    - An optional containing the updated book object, or empty if not found.
  
- **isBookExist(Book book)**:
  - Description: Checks if a book exists in the inventory.
  - Parameters: 
    - `book` (Book): The book object to check.
  - Returns: 
    - `true` if the book exists, `false` otherwise.
  
- **deleteBookByName(String name)**:
  - Description: Deletes a book from the inventory by its name.
  - Parameters: 
    - `name` (String): The name of the book to delete.
  - Returns: 
    - A message indicating the deletion status.
   
## BookRepository

The `BookRepository` interface extends `MongoRepository` to provide CRUD operations for the `Book` entity.

### Methods

- **findByName(String name)**:
  - Description: Finds a book in the database by its name.
  - Parameters: 
    - `name` (String): The name of the book to find.
  - Returns: 
    - An optional containing the book object, or empty if not found.
  
- **deleteByName(String name)**:
  - Description: Deletes a book from the database by its name.
  - Parameters: 
    - `name` (String): The name of the book to delete.
  - Returns: 
    - A message indicating the deletion status.

