package com.example.demo.model;

import com.example.demo.dto.CartOrderedProductDTO;

import javax.persistence.*;

@Entity
@Table(name = "ordered_products")
public class OrderedProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "sell_price")
    private Double sellPrice;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "order_id")
    private Integer orderId;

    public OrderedProduct() {
    }

    public OrderedProduct(Integer id, Integer productId, Double sellPrice, Integer quantity, Integer orderId) {
        this.id = id;
        this.productId = productId;
        this.sellPrice = sellPrice;
        this.quantity = quantity;
        this.orderId = orderId;
    }

    public OrderedProduct(CartOrderedProductDTO dto, Integer orderId) {
        this.id = 0;
        this.productId = dto.getProductId();
        this.sellPrice = dto.getSellPrice();
        this.quantity = dto.getQuantity();
        this.orderId = orderId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
