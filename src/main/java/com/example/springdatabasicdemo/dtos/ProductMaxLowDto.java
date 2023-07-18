package com.example.springdatabasicdemo.dtos;

public class ProductMaxLowDto {
    
    private Long id;
    
    private Integer maxPrice;
    
    private Integer lowPrice;
    
    public ProductMaxLowDto(Long id, Integer maxPrice, Integer lowPrice) {}
    
    protected ProductMaxLowDto() {}
    
    public Long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public Integer getMaxPrice() {
        return maxPrice;
    }
    
    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }
    
    public Integer getLowPrice() {
        return lowPrice;
    }
    
    public void setLowPrice(Integer lowPrice) {
        this.lowPrice = lowPrice;
    }
}
