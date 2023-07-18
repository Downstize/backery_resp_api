package com.example.springdatabasicdemo.dtos;

import com.example.springdatabasicdemo.models.Backery;
import com.example.springdatabasicdemo.models.Ingredient;
import com.example.springdatabasicdemo.models.Order;

import java.util.List;
import java.util.Set;

public class ProductFindByNameDto {
    private String name;

    public ProductFindByNameDto(String name) {
        this.name = name;
    }

    public ProductFindByNameDto() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
