package com.example.springdatabasicdemo.dtos;

import com.example.springdatabasicdemo.models.Backery;
import com.example.springdatabasicdemo.models.Ingredient;
import com.example.springdatabasicdemo.models.Order;

import java.util.List;
import java.util.Set;

public class CookProductDto {

//    private Long id;
//    private String name;
//    private String description;
    private ProductDto product;
    private Long backeryId;
    private Integer amount;
    private Integer price;

    public CookProductDto(ProductDto product, Long backeryId, Integer amount, Integer price) {
        this.product = product;
        this.backeryId = backeryId;
        this.amount = amount;
        this.price = price;
    }

    public CookProductDto() {}

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public Long getBackeryId() {return backeryId;}

    public void setBackeryId(Long backeryId) {
        this.backeryId = backeryId;
    }

    public Integer getAmount() {return amount;}

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getPrice() {return price;}

    public void setPrice(Integer price) {
        this.price = price;
    }
}
