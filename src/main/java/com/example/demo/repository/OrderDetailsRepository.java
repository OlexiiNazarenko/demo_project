package com.example.demo.repository;

import com.example.demo.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {
}
