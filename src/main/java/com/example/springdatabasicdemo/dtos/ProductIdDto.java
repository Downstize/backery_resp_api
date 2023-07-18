package com.example.springdatabasicdemo.dtos;

import com.example.springdatabasicdemo.models.Backery;
import com.example.springdatabasicdemo.models.Ingredient;
import com.example.springdatabasicdemo.models.Order;

import java.util.List;
import java.util.Set;

public class ProductIdDto {
    private Integer id;

    public ProductIdDto(Integer id) {
        this.id = id;
    }

    public ProductIdDto() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
