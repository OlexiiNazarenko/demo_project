package com.example.demo.model;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(Integer id) {
        super("Couldn't find order with id " + id);
    }
}
