package com.example.springdatabasicdemo.dtos;

public class ProductUpdateStockDto {
    private Long backeryId;

    private String productName;

    private Double percentage;

    public ProductUpdateStockDto(Long backeryId, String productName, Double percentage) {
        this.backeryId = backeryId;
        this.productName = productName;
        this.percentage = percentage;
    }

    public ProductUpdateStockDto() {}

    public Long getBackeryId() {
        return backeryId;
    }

    public void setBackeryId(Long backeryId) {
        this.backeryId = backeryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}
