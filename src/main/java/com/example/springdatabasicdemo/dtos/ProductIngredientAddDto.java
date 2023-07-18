package com.example.springdatabasicdemo.dtos;

public class ProductIngredientAddDto {
    private Long ingredientId;

    private Integer productId;

    public ProductIngredientAddDto(Long ingredientId, Integer productId) {
        this.ingredientId = ingredientId;
        this.productId = productId;
    }
    
    public ProductIngredientAddDto() {}

    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
