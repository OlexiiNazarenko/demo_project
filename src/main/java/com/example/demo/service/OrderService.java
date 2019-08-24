package com.example.demo.service;

import com.example.demo.model.Order;

import java.util.List;

public interface OrderDetails {

    List<Order> getAll();

    Order getOne(String id);

    Order addNew(Order order);

    Order update(Order order);

    void delete(String id);
}
