package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ordered_products")
@Data
public class OrderedProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "sell_price")
    private Double sellPrice;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne()
    @JoinColumn(name = "orderdetails_id")
    private OrderDetails orderDetails;
}
