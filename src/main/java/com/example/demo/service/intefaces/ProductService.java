package com.example.demo.service.intefaces;

import com.example.demo.dto.CreateProductDTO;
import com.example.demo.model.Product;

import java.util.List;
import java.util.NoSuchElementException;

public interface ProductService {

    Iterable<Product> getAll();

    List<Product> getAllOfCategory(Integer categoryId);

    Product getOne(Integer id) throws NoSuchElementException;

    Product addNew(CreateProductDTO newProduct);

    Product updateProduct(CreateProductDTO updatedProduct, Integer id) throws NoSuchElementException;

    void delete(Integer id);
}
