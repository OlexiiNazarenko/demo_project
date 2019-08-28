package com.example.demo.service.implementations;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.intefaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

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
    public Category getOne(Integer id) throws NoSuchElementException {
        Category category = categoryRepository.findById(id).get();
        return category;
    }

    @Override
    public Category getOne(String name) throws NoSuchElementException {
        Category category = categoryRepository.findByName(name);
        return category;
    }

    @Override
    public Category addNew(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(String name, Integer id) throws NoSuchElementException {
        Category category = categoryRepository.findById(id).get();
        category.setName(name);
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }
}
