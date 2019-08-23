package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class AppController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("")
    public @ResponseBody Page<Product> getProductsForHomePage() {
        return productRepository.findAll(PageRequest.of(1, 4));
    }
}
