package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.model.CategoryNotFoundException;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Iterable<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getOne(Integer id) {
        Category category = categoryRepository.findById(id).get();
        if(category == null) {
            throw new CategoryNotFoundException(id);
        }
        return category;
    }

    @Override
    public Category getOne(String name) {
        Category category = categoryRepository.findByName(name);
        return category;
    }

    @Override
    public Category addNew(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category updatedCategory, Integer id) {
        Category category = categoryRepository.findById(id).get();
        if(category != null) {
            category.setName(updatedCategory.getName());
        } else {
            category = updatedCategory;
        }
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }
}
