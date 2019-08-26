package com.example.demo.dto;

import java.io.Serializable;

public class PutCategoryViewDTO implements Serializable {

    private String name;

    public PutCategoryViewDTO() {
    }

    public PutCategoryViewDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
