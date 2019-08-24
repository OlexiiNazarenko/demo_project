package com.example.demo.controller;

import com.example.demo.dto.OrderDisplayDTO;
import com.example.demo.model.Order;
import com.example.demo.model.OrderedProduct;
import com.example.demo.service.OrderService;
import com.example.demo.service.OrderedProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;
    private OrderedProductService orderedProductService;

    @Autowired
    public OrderController(OrderService orderService, OrderedProductService orderedProductService) {
        this.orderService = orderService;
        this.orderedProductService = orderedProductService;
    }

    @GetMapping("/{id}")
    public @ResponseBody
    OrderDisplayDTO getOneOrderById(@PathVariable Integer id) {
        return new OrderDisplayDTO(
                orderService.getOne(id),
                orderedProductService.getAllOfOrder(id)
        );

    }


}
