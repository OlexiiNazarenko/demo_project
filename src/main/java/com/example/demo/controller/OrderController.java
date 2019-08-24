package com.example.demo.controller;

import com.example.demo.dto.OrderDisplayDTO;
import com.example.demo.model.Order;
import com.example.demo.model.OrderedProduct;
import com.example.demo.service.OrderService;
import com.example.demo.service.OrderedProductService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
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

    @GetMapping("/{id}")
    public @ResponseBody OrderDisplayDTO getOneOrderById(@PathVariable Integer id) {
        Order order = orderService.getOne(id);
        Link linkSelfOrder = new Link(path+ "/order/" + order.getOrderId());
        order.add(linkSelfOrder);

        List<OrderedProduct> orderedProductList = orderedProductService.getAllOfOrder(id);
        for(OrderedProduct orderedProduct : orderedProductList) {
            Link linkProduct = new Link(path + "/product/" + orderedProduct.getProductId()).withRel("product");
            orderedProduct.add(linkProduct);
        }

        return new OrderDisplayDTO(order, orderedProductList);
    }


}
