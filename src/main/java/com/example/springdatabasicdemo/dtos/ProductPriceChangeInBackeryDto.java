package com.example.springdatabasicdemo.dtos;

public class ProductPriceChangeInBackeryDto {
    private Long backeryId;

    private Integer productId;

    private Integer price;

    public ProductPriceChangeInBackeryDto(Long backeryId, Integer productId, Integer price) {
        this.backeryId = backeryId;
        this.productId = productId;
        this.price = price;
    }
    
    public ProductPriceChangeInBackeryDto() {}

    public Long getBackeryId() {
        return backeryId;
    }

    public void setBackeryId(Long backeryId) {
        this.backeryId = backeryId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
