package com.example.demo.service;

public class ZeroProductQuantityException extends RuntimeException {

    public ZeroProductQuantityException(Integer id) {
        super("Product with id: " + id + " has quantity: 0");
    }
}
