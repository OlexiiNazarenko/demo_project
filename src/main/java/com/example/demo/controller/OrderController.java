package com.example.demo.controller;

import com.example.demo.dto.CartDTO;
import com.example.demo.dto.CartOrderedProductDTO;
import com.example.demo.model.Order;
import com.example.demo.model.OrderedProduct;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.OrderedProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderedProductRepository orderedProductRepository;

    @GetMapping("/{id}")
    public CartDTO getOrderById(@PathVariable String id) {
        Order order = orderRepository.findById(id);
        List<OrderedProduct> orderedProductList = orderedProductRepository.findAllByOrderId(id);
        List<CartOrderedProductDTO> cartOrderedProductDTOS = new ArrayList<>();

        for (OrderedProduct p:
             orderedProductList) {
            cartOrderedProductDTOS.add(new CartOrderedProductDTO(
              p.getName(),
              p.getSellPrice(),
              p.getQuantity()
            ));
        }

        return new CartDTO(
                order.getFirstName(),
                order.getLastName(),
                order.getAddress(),
                order.getPhone(),
                order.getEmail(),
                cartOrderedProductDTOS
        );
    }

}
