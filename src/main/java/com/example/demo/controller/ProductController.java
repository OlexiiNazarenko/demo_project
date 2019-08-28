package com.example.demo.controller;

import com.example.demo.dto.CreateProductDTO;
import com.example.demo.model.Product;
import com.example.demo.service.intefaces.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
            Link linkPhoto = new Link(path + "/images/" + product.getPhoto()).withRel("image");
            product.add(linkPhoto);
        }

        return products;
    }

    @GetMapping("/{id}")
    public @ResponseBody Product getOneProductById(@PathVariable Integer id) {
        Product product = productService.getOne(id);

        Link linkSelf = new Link(path + "/product/" + product.getProductId()).withSelfRel();
        product.add(linkSelf);
        Link linkPhoto = new Link(path + "/images/" + product.getPhoto()).withRel("image");
        product.add(linkPhoto);

        return product;
    }

    @PostMapping("/create")
    public @ResponseBody Product createNewProduct(@RequestBody CreateProductDTO newProduct){
        Product product = productService.addNew(newProduct);

        Link linkSelf = new Link(path + "/product/" + product.getProductId()).withSelfRel();
        product.add(linkSelf);
        Link linkPhoto = new Link(path + "/images/" + product.getPhoto()).withRel("image");
        product.add(linkPhoto);

        return product;
    }

    @PutMapping("/{id}")
    public Product updateProduct(@RequestBody CreateProductDTO updatedProduct, @PathVariable Integer id) {
        Product product = productService.updateProduct(updatedProduct, id);

        Link linkSelf = new Link(path + "/product/" + product.getProductId()).withSelfRel();
        product.add(linkSelf);
        Link linkPhoto = new Link(path + "/images/" + product.getPhoto()).withRel("image");
        product.add(linkPhoto);

        return product;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Integer id) {
        productService.delete(id);
    }
}
