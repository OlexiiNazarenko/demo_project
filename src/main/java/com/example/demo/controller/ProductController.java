package com.example.demo.controller;

import com.example.demo.model.Product;

import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Value("${url}")
    private String path;

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public @ResponseBody List<Product> getAllProducts() {
        List<Product> products =(List<Product>) productService.getAll();
        for(Product product : products) {
            Link linkSelf = new Link(path + "/product/" + product.getProductId()).withSelfRel();
            product.add(linkSelf);
        }
        return products;
    }

    @GetMapping("/{id}")
    public @ResponseBody Product getOneProductById(@PathVariable Integer id) {
        Product product = productService.getOne(id);
        Link linkSelf = new Link(path + "/product/" + product.getProductId()).withSelfRel();
        product.add(linkSelf);
        return product;
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
