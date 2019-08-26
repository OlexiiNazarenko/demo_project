package com.example.demo.controller;

import com.example.demo.dto.CartDTO;
import com.example.demo.dto.SimpleCategoryViewDTO;
import com.example.demo.model.*;
import com.example.demo.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.Link;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("")
public class AppController {

    @Value("${url}")
    private String path;

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
            Link link = new Link(path + "/shop/category/" + category.getName());
            simpleCategoryViewDTO.add(link);
            result.add(simpleCategoryViewDTO);
        }

        return result;
    }

    @GetMapping("/shop/category/{name}")
    public @ResponseBody Category getCategory (@PathVariable String name) {
        Category category = categoryService.getOne(name);
        List<Product> productList = category.getProducts();

        for(Product product : productList) {
            Link link = new Link(path + "/product/" + product.getProductId());
            product.add(link);
        }
        category.setProducts(productList);

        return category;
    }

    @PostMapping("cart/save")
    @Transactional
    public String saveCart(@RequestBody CartDTO cartDTO){
        Integer orderId = orderService.addNew(cartDTO);
        return orderedProductService.addAll(cartDTO.getOrderedProductsList(), orderId);
    }
}
