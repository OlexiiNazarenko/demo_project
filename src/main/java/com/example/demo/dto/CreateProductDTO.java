package com.example.demo.dto;

public class CreateProductDTO {

    private String name;
    private String description;
    private Double price;
    private String photo;
    private Integer quantity;
    private Integer categoryId;

    public CreateProductDTO() {
    }

    public CreateProductDTO(String name, String description, Double price, String photo, Integer quantity, Integer categoryId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.photo = photo;
        this.quantity = quantity;
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
