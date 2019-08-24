package com.example.demo.dto;

import com.example.demo.model.Category;

import javax.persistence.Column;

public class SimpleCategoryViewDTO {

    private Integer id;
    private String name;

    public SimpleCategoryViewDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public SimpleCategoryViewDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
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
