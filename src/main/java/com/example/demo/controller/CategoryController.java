package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.model.CategoryNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.repository.CategoryRepository;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/{id}")
    public @ResponseBody Category getCategory (@RequestParam Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @GetMapping("/{name}")
    public @ResponseBody Category getCategory (@RequestParam String name) {
        return categoryRepository.findByName(name);
    }

    @PostMapping("")
    public Category createNewCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @PutMapping("/{id}")
    public Category updateCategoryById(@PathVariable Integer id, @RequestBody Category updatedCategory) {
        return categoryRepository.findById(id)
                .map(category -> {
                    category.setName(updatedCategory.getName());
                    return categoryRepository.save(category);
                }).orElseGet(() ->
                        {return categoryRepository.save(updatedCategory);
                        });
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Integer id) {
        categoryRepository.deleteById(id);
    }
}
