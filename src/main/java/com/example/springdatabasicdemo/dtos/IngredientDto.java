package com.example.springdatabasicdemo.dtos;

import com.example.springdatabasicdemo.models.Product;

import java.util.List;

public class IngredientDto {

    private Long id;
    private String name;
    private Integer amount;

    public IngredientDto(Long id, String name, Integer amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    public IngredientDto() {}

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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

}
