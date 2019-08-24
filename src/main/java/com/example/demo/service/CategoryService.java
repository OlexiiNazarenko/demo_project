package com.example.demo.service;

import com.example.demo.model.Category;

public interface CategoryService {

    Iterable<Category> getAll();

    Category getOne(Integer id);

    Category getOne(String name);

    Category addNew(Category category);

    Category update(Category updatedCategory);

    void delete(Integer id);
}
