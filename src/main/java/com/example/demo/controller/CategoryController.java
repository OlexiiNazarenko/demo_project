package com.example.demo.controller;

import com.example.demo.model.Category;

import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public @ResponseBody Category getCategory (@RequestParam Integer id) {
        return categoryService.getOne(id);
    }

    @GetMapping("/{name}")
    public @ResponseBody Category getCategory (@RequestParam String name) {
        return categoryService.getOne(name);
    }

    @PostMapping("")
    public Category createNewCategory(@RequestBody Category category) {
        return categoryService.addNew(category);
    }

    @PutMapping("/{id}")
    public Category updateCategory( @RequestBody Category updatedCategory) {
        return categoryService.update(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Integer id) {
        categoryService.delete(id);
    }
}
