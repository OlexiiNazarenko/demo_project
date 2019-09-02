package com.example.demo.dto;

import com.example.demo.model.Order;
import com.example.demo.model.OrderedProduct;

import java.util.List;

public class OrderDisplayDTO {

    private Order order;
    private List<OrderedProduct> orderedProductList;
    private Double sum;
    private Integer totalProducts;

    public OrderDisplayDTO(Order order, List<OrderedProduct> orderedProductList) {
        this.order = order;
        this.orderedProductList = orderedProductList;
        sum = 0.0;
        totalProducts = 0;
        for (OrderedProduct orderedProduct: orderedProductList) {
            sum += (orderedProduct.getSellPrice() * orderedProduct.getQuantity());
            totalProducts += orderedProduct.getQuantity();
        }
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

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }
}
