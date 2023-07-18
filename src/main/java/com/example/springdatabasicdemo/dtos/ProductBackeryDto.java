package com.example.springdatabasicdemo.dtos;

import com.example.springdatabasicdemo.models.Backery;
import com.example.springdatabasicdemo.models.Ingredient;
import com.example.springdatabasicdemo.models.Product;
import com.example.springdatabasicdemo.models.ProductBackery;

public class ProductBackeryDto {

    Product product;
    Backery backery;
    private Integer price;
    Integer amount;

    private ProductBackeryDto() {}

    public ProductBackeryDto(Product product, Backery backery,Integer price, Integer amount) {
        this.product = product;
        this.backery = backery;
        this.price = price;
        this.amount = amount;
    }
    
    public void setBackery(Backery backery) {
        this.backery = backery;
    }
    
    public Backery getBackery() {
        return backery;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }
    
    public Product getProduct() {
        return product;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getAmount() {
        return amount;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
