package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

//    List<Product> findByCategoryOrderById(String category);
}
