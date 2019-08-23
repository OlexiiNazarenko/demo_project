package com.example.demo.dto;

import com.example.demo.model.Customer;
import com.example.demo.model.OrderedProduct;

import java.util.List;

public class CartDTO {

    private Customer customer;
    private List<OrderedProduct> orderedProductList;

    public CartDTO(Customer customer, List<OrderedProduct> orderedProductList) {
        this.customer = customer;
        this.orderedProductList = orderedProductList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderedProduct> getOrderedProductList() {
        return orderedProductList;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setOrderedProductList(List<OrderedProduct> orderedProductList) {
        this.orderedProductList = orderedProductList;
    }
}
