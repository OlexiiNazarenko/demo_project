package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.model.ProductNotFoundException;
import com.example.demo.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository mysqlRepository) {
        this.productRepository = mysqlRepository;
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody Product getOneProductById(@PathVariable Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @PostMapping("")
    public Product createNewProduct(@RequestBody Product newProduct) {
        return productRepository.save(newProduct);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Integer id, @RequestBody Product newProduct) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setId(newProduct.getId());
                    product.setName(newProduct.getName());
                    product.setDescription(newProduct.getDescription());
                    product.setPrice(newProduct.getPrice());
                    product.setPhoto(newProduct.getPhoto());
                    product.setQuantity(newProduct.getQuantity());
                    return productRepository.save(product);
        }).orElseGet(() -> {
            return productRepository.save(newProduct);
                });
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        productRepository.deleteById(id);
    }
}
