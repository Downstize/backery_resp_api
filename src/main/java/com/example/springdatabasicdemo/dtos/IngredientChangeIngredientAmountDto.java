package com.example.springdatabasicdemo.dtos;

public class IngredientChangeIngredientAmountDto {

    private Long id;
    private Integer amount;

    public IngredientChangeIngredientAmountDto(Long id, Integer amount){
        this.id = id;
        this.amount = amount;
    }

    public IngredientChangeIngredientAmountDto(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id){this.id = id;}

    public Integer getAmount() {
            return amount;
        }

    public void setAmount(Integer amount) {
            this.amount = amount;
        }

}
