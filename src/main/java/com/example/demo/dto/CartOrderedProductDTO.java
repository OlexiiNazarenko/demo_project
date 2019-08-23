package com.example.demo.dto;

import javax.persistence.Column;

public class CartOrderedProductDTO {

    private String productName;
    private Double sellPrice;
    private Integer quantity;

    public CartOrderedProductDTO(String name, Double sellPrice, Integer quantity) {
        this.productName = name;
        this.sellPrice = sellPrice;
        this.quantity = quantity;
    }

    public String getName() {
        return productName;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.productName = name;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
