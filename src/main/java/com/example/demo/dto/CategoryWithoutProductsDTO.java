package com.example.demo.dto;

import javax.persistence.Column;

public class CategoryWithoutProductsDTO {

    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    public CategoryWithoutProductsDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getCategoryId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
