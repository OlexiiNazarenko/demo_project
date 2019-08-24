package com.example.demo.service;

import com.example.demo.dto.CartDTO;
import com.example.demo.model.Order;

import java.util.List;

public interface OrderService {

    List<Order> getAll();

    Order getOne(Integer id);

    Order addNew(Order order);

    Integer addNew(CartDTO cartDTO);

    Order update(Order updatedOrder);

    void delete(String id);
}
