package com.example.demo.controller;

import com.example.demo.dto.CartDTO;
import com.example.demo.dto.SimpleCategoryViewDTO;
import com.example.demo.model.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("")
public class AppController {

    private CategoryService categoryService;
    private OrderService orderService;
    private OrderedProductService orderedProductService;

    @Autowired
    public AppController(CategoryService categoryService, OrderService orderService, OrderedProductService orderedProductService) {
        this.categoryService = categoryService;
        this.orderService = orderService;
        this.orderedProductService = orderedProductService;
    }

    @GetMapping("")
    public @ResponseBody List<SimpleCategoryViewDTO> start() {
        List<SimpleCategoryViewDTO> result = new ArrayList<>();
        Iterable<Category> listCategories = categoryService.getAll();
        for (Category category: listCategories) {
            SimpleCategoryViewDTO simpleCategoryViewDTO = new SimpleCategoryViewDTO(category);
            result.add(simpleCategoryViewDTO);
        }
        return result;
    }

    @PostMapping("cart/save")
    @Transactional
    public String saveCart(@RequestBody CartDTO cartDTO){
        Integer orderId = orderService.addNew(cartDTO);
        orderedProductService.addAll(cartDTO.getOrderedProductsList(), orderId);
        return "Order saved. Id: " + orderId;
    }
}
