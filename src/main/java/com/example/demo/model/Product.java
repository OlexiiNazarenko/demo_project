package com.example.demo.model;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.io.Serializable;

@Entity
@Table(name="products")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String category;
    private String description;
    private Double price;
    private String photo;
    private Integer quantity;

    public Product() {
    }

    public Product(int id, String name, String category, String description, double price, String photo, int quantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.photo = photo;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getPhoto() {
        return photo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", photo='" + photo + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
