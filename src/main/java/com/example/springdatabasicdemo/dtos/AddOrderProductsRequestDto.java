package com.example.springdatabasicdemo.dtos;

public class AddOrderProductsRequestDto {
    private Integer id;
    private ProductDto product;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }
}

