package com.example.demo.service.intefaces;

import com.example.demo.dto.CartOrderedProductDTO;
import com.example.demo.model.OrderedProduct;

import java.util.List;

public interface OrderedProductService {

    List<OrderedProduct> getAllOfOrder(Integer orderId);

//    OrderedProduct addNew(OrderedProduct orderedProduct);

    String addAll(List<CartOrderedProductDTO> list, Integer orderId);

//    OrderedProduct update(OrderedProduct updatedOrderedProduct);

//    void delete(String orderId);
}
