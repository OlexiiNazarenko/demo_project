package com.example.demo.controller;

import com.example.demo.dto.PutCategoryViewDTO;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.service.intefaces.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/shop/category/{name}")
    public @ResponseBody Category getCategory (@PathVariable String name) {
        Category category = categoryService.getOne(name);
        List<Product> productList = category.getProducts();

        for(Product product : productList) {
            Link link = new Link(path + "/product/" + product.getProductId());
            product.add(link);
        }
        category.setProducts(productList);

        return category;
    }

    @PostMapping("/create")
    public Category createNewCategory(@RequestBody PutCategoryViewDTO putCategoryViewDTO) {
        Category category = new Category(null, putCategoryViewDTO.getName());

        return categoryService.addNew(category);
    }

    @PutMapping("/{id}")
    public @ResponseBody Category updateCategory(@RequestBody PutCategoryViewDTO putCategoryViewDTO, @PathVariable Integer id) {

        return categoryService.update(putCategoryViewDTO.getName(), id);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Integer id) {

        categoryService.delete(id);
    }
}
