package com.example.demo.repository;

import com.example.demo.model.OrderedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderedProductRepository extends JpaRepository<OrderedProduct, Integer> {

    List<OrderedProduct> findAllByOrderId(Integer orderId);

    void deleteByOrderId(String orderId);
}
