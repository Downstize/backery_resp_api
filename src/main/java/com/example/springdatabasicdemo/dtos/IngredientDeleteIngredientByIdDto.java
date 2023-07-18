package com.example.springdatabasicdemo.dtos;

public class IngredientDeleteIngredientByIdDto {

    private Long id;

    public IngredientDeleteIngredientByIdDto(Long id){
        this.id = id;
    }

    public IngredientDeleteIngredientByIdDto(){}

    public Long getId(){return id;}

    public void setId(Long id){this.id = id;}
}
