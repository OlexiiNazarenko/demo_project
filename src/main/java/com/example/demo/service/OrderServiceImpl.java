package com.example.demo.service;

import com.example.demo.dto.CartDTO;
import com.example.demo.model.Order;
import com.example.demo.model.OrderNotFoundException;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.OrderedProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private OrderedProductRepository orderedProductRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOne(Integer id) {
        Order order = orderRepository.findById(id).get();
        if(order == null) {
            throw new OrderNotFoundException(id);
        }
        return order;
    }

    @Override
    public Order addNew(Order order) {

        return orderRepository.save(order);
    }

    @Override
    public Integer addNew(CartDTO cartDTO) {
        Order order = new Order(
                0,
                cartDTO.getCustomerFirstName(),
                cartDTO.getCustomerLastName(),
                cartDTO.getCustomerAddress(),
                cartDTO.getCustomerPhone(),
                cartDTO.getCustomerEmail()
        );
        return orderRepository.save(order).getId();
    }

    @Override
    public Order update(Order updatedOrder) {
        Order order = orderRepository.findById(updatedOrder.getId()).get();
        if(order != null) {
            order.setFirstName(updatedOrder.getFirstName());
            order.setLastName(updatedOrder.getLastName());
            order.setAddress(updatedOrder.getAddress());
            order.setPhone(updatedOrder.getPhone());
            order.setEmail(updatedOrder.getEmail());
        } else {
            order = updatedOrder;
        }
        return orderRepository.save(order);
    }

    @Override
    public void delete(String id) {
        orderRepository.deleteById(id);
    }
}
