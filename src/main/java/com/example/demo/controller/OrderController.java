package com.example.demo.controller;

import com.example.demo.dto.CustomerDetailsDTO;
import com.example.demo.dto.OrderDisplayDTO;
import com.example.demo.model.Order;
import com.example.demo.model.OrderNotFoundException;
import com.example.demo.model.OrderedProduct;
import com.example.demo.service.intefaces.OrderService;
import com.example.demo.service.intefaces.OrderedProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Value("${url}")
    private String path;

    private OrderService orderService;
    private OrderedProductService orderedProductService;

    @Autowired
    public OrderController(OrderService orderService, OrderedProductService orderedProductService) {
        this.orderService = orderService;
        this.orderedProductService = orderedProductService;
    }

    @GetMapping("/all")
    public @ResponseBody List<OrderDisplayDTO> getAll(){
        List<OrderDisplayDTO> response = new ArrayList<>();

        List<Order> orders = orderService.getAll();
        for (Order order: orders) {
            Link linkSelfOrder = new Link(path+ "/order/" + order.getOrderId());
            order.add(linkSelfOrder);

            List<OrderedProduct> orderedProductList = orderedProductService.getAllOfOrder(order.getOrderId());
            for(OrderedProduct orderedProduct : orderedProductList) {
                Link linkProduct = new Link(path + "/product/" + orderedProduct.getProductId()).withRel("product");
                orderedProduct.add(linkProduct);
            }
            response.add(new OrderDisplayDTO(order, orderedProductList));
        }

        return response;
    }

    @GetMapping("/{id}")
    public @ResponseBody OrderDisplayDTO getOneOrderById(@PathVariable Integer id) {
        try {
            Order order = orderService.getOne(id);
            Link linkSelfOrder = new Link(path + "/order/" + order.getOrderId());
            order.add(linkSelfOrder);

            List<OrderedProduct> orderedProductList = orderedProductService.getAllOfOrder(id);
            for (OrderedProduct orderedProduct : orderedProductList) {
                Link linkProduct = new Link(path + "/product/" + orderedProduct.getProductId()).withRel("product");
                orderedProduct.add(linkProduct);
            }

            return new OrderDisplayDTO(order, orderedProductList);
        } catch (NoSuchElementException e) {
            throw new OrderNotFoundException(id);
        }
    }

    @PutMapping("/{id}")
    public Order updateCustomerDetails(@RequestBody CustomerDetailsDTO customerDetailsDTO,
                                       @PathVariable Integer id) throws OrderNotFoundException{
        try {
            return orderService.update(customerDetailsDTO, id);
        }catch (NoSuchElementException e) {
            throw new OrderNotFoundException(id);
        }
    }
}
