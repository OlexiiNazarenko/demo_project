package com.example.demo.model;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.hateoas.ResourceSupport;

@Entity
@Table(name="products")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Product extends ResourceSupport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "photo")
    private String photo;

    @Column(name = "quantity")
    private Integer quantity;

    private Integer categoryId;

    public Product() {
    }

    public Product(Integer id, String name, String description, Double price, String photo, Integer quantity, Integer categoryId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.photo = photo;
        this.quantity = quantity;
        this.categoryId = categoryId;
    }

    public Integer getProductId() {
        return id;
    }

    public String getName() {
        return name;
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

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", photo='" + photo + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
