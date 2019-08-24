package com.example.demo.service;

import com.example.demo.dto.CartOrderedProductDTO;
import com.example.demo.model.OrderedProduct;

import java.util.List;

public interface OrderedProductService {

    List<OrderedProduct> getAllOfOrder(Integer orderId);

    OrderedProduct addNew(OrderedProduct orderedProduct);

    void addAll(List<CartOrderedProductDTO> list, Integer orderId);

    OrderedProduct update(OrderedProduct updatedOrderedProduct);

    void delete(String orderId);
}
