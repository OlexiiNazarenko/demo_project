package com.example.demo.dto;

import com.example.demo.model.Category;
import org.springframework.hateoas.ResourceSupport;

public class SimpleCategoryViewDTO extends ResourceSupport {

    private Integer id;
    private String name;

    public SimpleCategoryViewDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public SimpleCategoryViewDTO(Category category) {
        this.id = category.getCategoryId();
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
