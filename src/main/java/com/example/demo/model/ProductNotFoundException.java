package com.example.demo.model;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Integer id) {
        super("Couldn't find product with id " + id);
    }
}
