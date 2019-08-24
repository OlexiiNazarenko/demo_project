package com.example.demo.controller;

import com.example.demo.model.Product;

import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<Product> getAllProducts() {
        return productService.getAll();
    }

    @GetMapping("/shop/{categoryId}")
    public @ResponseBody
    List<Product> getProductsOfCategory(@PathVariable Integer categoryId){
        return productService.getAllOfCategory(categoryId);
    }

    @GetMapping("/{id}")
    public @ResponseBody Product getOneProductById(@PathVariable Integer id) {
        return productService.getOne(id);
    }

    @PostMapping("")
    public Product createNewProduct(@RequestBody Product product){
        return productService.addNew(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@RequestBody Product updatedProduct) {
        return productService.updateProduct(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        productService.delete(id);
    }
}
