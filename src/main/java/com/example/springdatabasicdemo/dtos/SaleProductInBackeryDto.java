package com.example.springdatabasicdemo.dtos;

import com.example.springdatabasicdemo.models.Backery;
import com.example.springdatabasicdemo.models.Ingredient;
import com.example.springdatabasicdemo.models.Order;

import java.util.List;
import java.util.Set;

public class SaleProductInBackeryDto {

    private Long id;
    private String name;
    private String description;
    private Long backeryId;
    private Integer amount;

    public SaleProductInBackeryDto(Long id, String name, String description, Long backeryId, Integer amount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.backeryId = backeryId;
        this.amount = amount;
    }

    public SaleProductInBackeryDto() {}

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

    public Long getBackeryId() {return backeryId;}

    public void setBackeryId(Long backeryId) {
        this.backeryId = backeryId;
    }

    public Integer getAmount() {return amount;}

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

}
