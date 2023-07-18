package com.example.springdatabasicdemo.dtos;

public class IngredientGetIngredientsByProductDto {

    private ProductDto products;

    public IngredientGetIngredientsByProductDto(ProductDto products) {
        this.products = products;
    }

    public IngredientGetIngredientsByProductDto() {
    }

    public ProductDto getIngredientsOfProduct() {
        return products;
    }

    public void setIngredientsOfProduct(ProductDto products) {
        this.products = products;
    }

}
