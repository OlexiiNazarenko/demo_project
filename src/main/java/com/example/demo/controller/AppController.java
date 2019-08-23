package com.example.demo.controller;

import com.example.demo.dto.CartDTO;
import com.example.demo.dto.CategoryWithoutProductsDTO;
import com.example.demo.dto.ProductSimpleDTO;
import com.example.demo.model.Category;
import com.example.demo.model.Customer;
import com.example.demo.model.OrderedProduct;
import com.example.demo.model.Product;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("")
public class AppController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

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

    @GetMapping("cart/save")
    public String saveCart(@RequestBody CartDTO cartDTO){
        Customer customer = cartDTO.getCustomer();
        List<OrderedProduct> orderedProductList = cartDTO.getOrderedProductList();
        return "Cart saved";
    }
}
