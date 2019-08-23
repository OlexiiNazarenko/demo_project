package com.example.demo.controller;

import com.example.demo.dto.CartDTO;
import com.example.demo.dto.CartOrderedProductDTO;
import com.example.demo.dto.CategoryWithoutProductsDTO;
import com.example.demo.dto.ProductSimpleDTO;
import com.example.demo.model.*;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.OrderedProductRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("")
public class AppController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderedProductRepository orderedProductRepository;

    @GetMapping("")
    public @ResponseBody
    List<CategoryWithoutProductsDTO> start() {
        List<CategoryWithoutProductsDTO> result = new ArrayList<>();
        List<Category> listCategories = categoryRepository.findAll();
        for (Category category:
             listCategories) {
            CategoryWithoutProductsDTO categoryWithoutProductsDTO = new CategoryWithoutProductsDTO(
                    category.getId(),
                    category.getName()
            );
            result.add(categoryWithoutProductsDTO);
        }
        return result;
    }

    @GetMapping("/shop/{categoryName}")
    public @ResponseBody
    List<ProductSimpleDTO> getProductsOfCategory(@PathVariable String categoryName){
        List<ProductSimpleDTO> result = new ArrayList<>();
        List<Product> products = categoryRepository.findByName(categoryName).getProducts();

        for (Product product:
             products) {
            ProductSimpleDTO productSimpleDTO = new ProductSimpleDTO(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getPhoto()
            );
            result.add(productSimpleDTO);
        }

        return result;
    }

    @PostMapping("cart/save")
    public String saveCart(@RequestBody CartDTO cartDTO){
        String orderId = UUID.randomUUID().toString();
        Order order = new Order(
                orderId,
                cartDTO.getCustomerFirstName(),
                cartDTO.getCustomerLastName(),
                cartDTO.getCustomerAddress(),
                cartDTO.getCustomerPhone(),
                cartDTO.getCustomerEmail()
        );
        orderRepository.save(order);

        List<CartOrderedProductDTO> orderedProductList = cartDTO.getOrderedProductsList();
        for(CartOrderedProductDTO p : orderedProductList) {
            OrderedProduct orderedProduct = new OrderedProduct(
                    0,
                    p.getName(),
                    p.getSellPrice(),
                    p.getQuantity(),
                    orderId
            );
            orderedProductRepository.save(orderedProduct);

            Product product = productRepository.findByName(p.getName());
            int newQuantity = product.getQuantity() - p.getQuantity();
            System.out.println(newQuantity);
            product.setQuantity(newQuantity);
            productRepository.save(product);
        }

        return "Order " + orderId + " saved";
    }
}
