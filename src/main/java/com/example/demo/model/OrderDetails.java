package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "order_details")
@Data
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "ordered_product_id")
    private Integer orderedProductId;

    @OneToMany(mappedBy = "orderDetails")
    private List<OrderedProduct> orderedProductList;
}
