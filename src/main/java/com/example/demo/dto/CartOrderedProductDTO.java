package com.example.demo.dto;

public class CartOrderedProductDTO {

    private Integer productId;
    private Double sellPrice;
    private Integer quantity;

    public CartOrderedProductDTO() {
    }

    public CartOrderedProductDTO(Integer productId, Double sellPrice, Integer quantity) {
        this.productId = productId;
        this.sellPrice = sellPrice;
        this.quantity = quantity;
    }

    public Integer getProductId() {
        return productId;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
