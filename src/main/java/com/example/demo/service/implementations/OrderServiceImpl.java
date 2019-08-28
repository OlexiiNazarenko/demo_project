package com.example.demo.service.implementations;

import com.example.demo.dto.CartDTO;
import com.example.demo.dto.CustomerDetailsDTO;
import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.OrderedProductRepository;
import com.example.demo.service.intefaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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
    public Order getOne(Integer id) throws NoSuchElementException {
        Order order = orderRepository.findById(id).get();
        return order;
    }

    @Override
    public Integer addNew(CartDTO cartDTO) {
        Order order = new Order(
                null,
                cartDTO.getCustomerFirstName(),
                cartDTO.getCustomerLastName(),
                cartDTO.getCustomerAddress(),
                cartDTO.getCustomerPhone(),
                cartDTO.getCustomerEmail()
        );

        return orderRepository.save(order).getOrderId();
    }

    @Override
    public Order update(CustomerDetailsDTO customerDetailsDTO, Integer id) throws NoSuchElementException {
        Order order = orderRepository.findById(id).get();

        order.setFirstName(customerDetailsDTO.getFirstName());
        order.setLastName(customerDetailsDTO.getLastName());
        order.setAddress(customerDetailsDTO.getAddress());
        order.setPhone(customerDetailsDTO.getPhone());
        order.setEmail(customerDetailsDTO.getEmail());

        return orderRepository.save(order);
    }
}
