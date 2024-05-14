package com.amaap.BookInventoryDemo.model;

import org.springframework.data.annotation.Id;

public class Book {
    @Id
    private final String name;
    private final String author;
    private final String publication;
    private final double price;
    private final int quantity;

    public Book(String name, String author, String publication, double price, int quantity) {
        this.name = name;
        this.author = author;
        this.publication = publication;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublication() {
        return publication;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
