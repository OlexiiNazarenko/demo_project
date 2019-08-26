package com.example.demo.model;

import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category extends ResourceSupport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "categoryId")
    private List<Product> products;

    public Category() {
    }

    public Category(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getCategoryId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", products=" + products +
                '}';
    }
}
