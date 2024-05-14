package com.amaap.BookInventoryDemo.model;

import org.springframework.data.annotation.Id;

public class Book {
    @Id
    private String name;
    private String author;
    private String publication;
    private double price;
    private int quantity;

    public Book(String name, String author, String publication, double price, int quantity) {
        this.name = name;
        this.author = author;
        this.publication = publication;
        this.price = price;
        this.quantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
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
