package com.example.demo.model;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(Integer id) {
        super("Couldn't find category with id " + id);
    }

    public CategoryNotFoundException(String name) {
        super("Couldn't find category " + name);
    }
}
