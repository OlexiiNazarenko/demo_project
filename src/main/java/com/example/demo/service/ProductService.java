package com.example.demo.service;

import com.example.demo.model.Product;

import java.util.List;

public interface ProductService {

    Iterable<Product> getAll();

    List<Product> getAllOfCategory(Integer categoryId);

    Product getOne(Integer id);

    Product addNew(Product product);

    Product updateProduct(Product updatedProduct);

    void delete(Integer id);
}
