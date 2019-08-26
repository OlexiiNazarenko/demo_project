package com.example.demo.service;

public class ExceedProductStorageOrderException extends RuntimeException {

    public ExceedProductStorageOrderException(Integer id) {
        super("Product with id " + id + ": order quantity is bigger that the quantity in storage");
    }

    public ExceedProductStorageOrderException(Integer id, Integer quantity, Integer storageQuantuty) {
        super("Product with id " + id + ": order quantity (" + quantity + ") is bigger that the quantity in storage: " + storageQuantuty);
    }
}
