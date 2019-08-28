package com.example.demo.service.intefaces;

import com.example.demo.dto.CartDTO;
import com.example.demo.dto.CustomerDetailsDTO;
import com.example.demo.model.Order;

import java.util.List;
import java.util.NoSuchElementException;

public interface OrderService {

    List<Order> getAll();

    Order getOne(Integer id) throws NoSuchElementException;

    Integer addNew(CartDTO cartDTO);

    Order update(CustomerDetailsDTO customerDetailsDTO, Integer id) throws NoSuchElementException;
}
