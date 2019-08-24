package com.example.demo.dto;

import com.example.demo.model.Order;
import com.example.demo.model.OrderedProduct;

import java.util.List;

public class OrderDisplayDTO {

    private Order order;
    private List<OrderedProduct> orderedProductList;

    public OrderDisplayDTO(Order order, List<OrderedProduct> orderedProductList) {
        this.order = order;
        this.orderedProductList = orderedProductList;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderedProduct> getOrderedProductList() {
        return orderedProductList;
    }

    public void setOrderedProductList(List<OrderedProduct> orderedProductList) {
        this.orderedProductList = orderedProductList;
    }
}
