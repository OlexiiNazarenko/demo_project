package com.example.demo.dto;

import javax.persistence.Column;

public class ProductSimpleDTO {

//    @Column(name = "id")
    private Integer id;

//    @Column(name = "name")
    private String name;

//    @Column(name = "price")
    private Double price;

//    @Column(name = "photo")
    private String photo;

    public ProductSimpleDTO() {
    }

    public ProductSimpleDTO(Integer id, String name, Double price, String photo) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.photo = photo;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
