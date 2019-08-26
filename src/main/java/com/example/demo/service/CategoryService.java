package com.example.demo.service;

import com.example.demo.model.Category;

import java.util.NoSuchElementException;

public interface CategoryService {

    Iterable<Category> getAll();

    Category getOne(Integer id) throws NoSuchElementException;

    Category getOne(String name) throws NoSuchElementException;

    Category addNew(Category category);

    Category update(String name, Integer id) throws NoSuchElementException;

    void delete(Integer id);
}
