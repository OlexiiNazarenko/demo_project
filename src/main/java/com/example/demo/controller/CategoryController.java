package com.example.demo.controller;

import com.example.demo.model.Category;

import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Value("${url}")
    private String path;

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("{id}")
    public @ResponseBody Category getCategory (@RequestParam Integer id) {
        Category category = categoryService.getOne(id);
        Link link = new Link(path + "/category/" + category.getCategoryId()).withSelfRel();
        category.add(link);
        return category;
    }

    @PostMapping("")
    public Category createNewCategory(@RequestBody Category category) {
        return categoryService.addNew(category);
    }

    @PutMapping("/{id}")
    public Category updateCategory( @RequestBody Category updatedCategory, @PathVariable Integer id) {
        return categoryService.update(updatedCategory, id);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Integer id) {
        categoryService.delete(id);
    }
}
