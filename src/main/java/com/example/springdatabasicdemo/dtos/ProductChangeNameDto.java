package com.example.springdatabasicdemo.dtos;

import com.example.springdatabasicdemo.models.Backery;
import com.example.springdatabasicdemo.models.Ingredient;
import com.example.springdatabasicdemo.models.Order;

import java.util.List;
import java.util.Set;

public class ProductChangeNameDto {

    private Long id;
    private String name;
    private String description;

    public ProductChangeNameDto(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public ProductChangeNameDto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}
