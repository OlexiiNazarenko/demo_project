package com.example.demo.repository;

import com.example.demo.model.OrderedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedProductRepository extends JpaRepository<OrderedProduct, Integer> {
}
